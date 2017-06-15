package com.cycligo.backend.authorisation;

import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.inject.Inject;

/**
 * Created by Mindaugas Urbontaitis on 06/04/2017.
 * cycligo-rest-api
 */
public class SimpleSignInAdapter implements SignInAdapter {

    private final RequestCache requestCache;

    @Inject
    public SimpleSignInAdapter(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

    @Override
    public String signIn(String username, Connection<?> connection, NativeWebRequest request) {
        SignInUtils.signin(username);
        return "/api/session/provider?username="+username;
    }

//    private String extractOriginalUrl(NativeWebRequest request) {
//        HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
//        HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
//        SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
//        if (saved == null) {
//            return null;
//        }
//        requestCache.removeRequest(nativeReq, nativeRes);
//        removeAutheticationAttributes(nativeReq.getSession(false));
//        return saved.getRedirectUrl();
//    }
//
//    private void removeAutheticationAttributes(HttpSession session) {
//        if (session == null) {
//            return;
//        }
//        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//    }

}
