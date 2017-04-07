package com.cycligo.backend.authorisation;

import com.cycligo.backend.config.Constants;
import com.cycligo.backend.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class AuthenticationResource {

    private final Logger log = LoggerFactory.getLogger(getClass());

    AuthenticationManager authenticationManager;

    AuthenticationResource(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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

    @RequestMapping(value = "session", method = RequestMethod.GET)
    public UserDto session(HttpSession session) {
        return (UserDto) session.getAttribute("user");
    }

    @RequestMapping(value = "session", method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }

}
