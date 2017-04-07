package com.cycligo.backend.authorisation;

import com.cycligo.backend.account.Account;
import com.cycligo.backend.account.AccountRepository;
import com.cycligo.backend.account.UsernameAlreadyInUseException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@Controller
public class SignupController {

    private final AccountRepository accountRepository;
    private final ProviderSignInUtils signInUtils;

    public SignupController(ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository,
                            AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.signInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(WebRequest request) {
        Connection<?> connection = signInUtils.getConnectionFromSession(request);
        if (connection != null) {
            Account account = createAccount(connection.fetchUserProfile(), null);
            if (account != null) {
                SignInUtils.signin(account.getUsername());
                signInUtils.doPostSignUp(connection.getDisplayName(), request);
            }
        }
        return "redirect:/";
    }

    private Account createAccount(UserProfile providerUser, BindingResult formBinding) {
        try {
            //TODO generate random username and password
            Account account = new Account(providerUser.getUsername(), "", providerUser.getFirstName(), providerUser.getLastName());
            accountRepository.createAccount(account);
            return account;
        } catch (UsernameAlreadyInUseException e) {
            //formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
            return null;
        }
    }
}
