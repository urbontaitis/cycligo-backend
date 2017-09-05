package com.cycligo.backend.authorization;

import com.cycligo.backend.account.Account;
import com.cycligo.backend.account.AccountRepository;
import com.cycligo.backend.account.UsernameAlreadyInUseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@Controller
public class SignupController {

    private AccountRepository accountRepository;
    private final ProviderSignInUtils signInUtils;

    public SignupController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository
                            ) {
//        this.accountRepository = accountRepository;
        this.signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public RedirectView signup(WebRequest request) throws UsernameAlreadyInUseException {
        Connection<?> connection = signInUtils.getConnectionFromSession(request);
        if (connection != null) {
            Account account = createAccount(connection.fetchUserProfile());
            if (account != null) {
                SignInUtils.signin(account.getUsername());
                signInUtils.doPostSignUp(account.getUsername(), request);
                return (new RedirectView("/api/session/provider?username=" + account.getUsername(), true));
            }
        }
        // TODO Handle this kind of situation
        return (new RedirectView("/", true));
    }

    private Account createAccount(UserProfile providerUser) throws UsernameAlreadyInUseException {
        //TODO generate random username and password
        if (StringUtils.isEmpty(providerUser.getEmail())) {
            throw new IllegalArgumentException("User email cant be empty");
        }
        Account account = new Account(providerUser.getEmail(), "", providerUser.getFirstName(), providerUser.getLastName());
        accountRepository.createAccount(account);
        return account;
    }
}
