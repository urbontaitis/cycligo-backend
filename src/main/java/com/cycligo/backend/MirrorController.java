package com.cycligo.backend;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Mindaugas Urbontaitis on 07/04/2017.
 * cycligo-rest-api
 */
@Controller
public class MirrorController {

    private String server = "localhost";
    private int port = 3000;

    @RequestMapping("/")
    public RedirectView mirrorRest(HttpMethod method, HttpServletRequest request,
                                   HttpServletResponse response) throws URISyntaxException
    {
        URI uri = new URI("http", null, server, port, request.getRequestURI(), request.getQueryString(), null);

//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<String> responseEntity =
//                restTemplate.exchange(uri, method, new HttpEntity<String>(body), String.class);

        return new RedirectView("http://localhost:3000", true);
//        return new ModelAndView("forward:/http://localhost:3000");
    }
}
