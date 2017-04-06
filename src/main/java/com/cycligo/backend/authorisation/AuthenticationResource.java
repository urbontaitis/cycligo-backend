package com.cycligo.backend.authorisation;

import com.cycligo.backend.config.Constants;
import com.cycligo.backend.user.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by Mindaugas Urbontaitis on 23/03/2017.
 * cycligo-rest-api
 */
@RestController
@RequestMapping(Constants.ROOT_API_PATH)
public class AuthenticationResource {

    AuthenticationManager authenticationManager;

    AuthenticationResource(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "session", method = RequestMethod.GET)
    public UserDto session(Principal user) {
        String name = user == null ? null : user.getName();
        return new UserDto(name);
    }

    @RequestMapping(value = "session", method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }

}
