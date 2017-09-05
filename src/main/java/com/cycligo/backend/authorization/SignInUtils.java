package com.cycligo.backend.authorization;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Mindaugas Urbontaitis on 06/04/2017.
 * cycligo-rest-api
 */
public class SignInUtils {

    public static void signin(String userId) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));
    }

}
