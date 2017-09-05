package com.cycligo.backend.authorization;

import com.cycligo.backend.account.Account;
import com.cycligo.backend.account.AccountRepository;
import com.cycligo.backend.config.Constants;
import com.cycligo.backend.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class AuthenticationResource {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;

    AuthenticationResource(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
//        this.accountRepository = accountRepository;
    }

    @PostMapping(value = "session")
    public UserDto login(@RequestBody Credentials credentials, HttpSession httpSession) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));

        log.info("HttpSession id {}", httpSession.getId());

        UserDto user = new UserDto(credentials.getUsername(),true);
        httpSession.setAttribute("user", user);

        return user;
    }

    @RequestMapping(value = "session/provider", method = RequestMethod.GET)
    public RedirectView loginByProvider(String username, HttpSession httpSession) {
        //TODO check if user exists in the security context
        Account account = accountRepository.findAccountByUsername(username);
        if (null == account) {
            throw new UsernameNotFoundException("User was not found: " + username);
        }

        UserDto user = new UserDto(username,true);
        httpSession.setAttribute("user", user);

        return new RedirectView("/");
    }

    @RequestMapping(value = "session", method = RequestMethod.GET)
    public UserDto session(HttpSession session) {
        return (UserDto) session.getAttribute("user");
    }

    @RequestMapping(value = "session", method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping(value = "session/token", method = RequestMethod.GET)
    public Map<String,String> token(HttpSession session) {
        // TODO do I need provide session token to Frontend? return Collections.singletonMap("token", session.getId());
        return null;
    }
}
