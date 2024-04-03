package org.bank.bankv2.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class RequestBodyInterceptor implements HandlerInterceptor {

    private RestTemplate restTemplate = new RestTemplate();
    private String externalServiceUrl = "http://example.com/external-service";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean ReponseByAuthService;

        if (request.getRequestURI().equals("/login/")
                || request.getRequestURI().startsWith("/user/")
        )
        {
            ReponseByAuthService= true;
            if(ReponseByAuthService){
                // Penser a throw des exceptions personnalisé
                System.out.println("URI Acces Accepted ");
                return true;
            } else {
                System.out.println("URI Acces Denied");
                return false;
            }
        } else {
            String token = request.getHeader("Authorization");
            if (token != null && validateToken(token)) {
                return true;
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                System.out.println("Acces Denied : No Credence");
                return false;
            }
        }
    }

    private boolean validateToken(String token) {
        return token.equals("TOKEN_CODE_AND_CREDENCE");
    }

    private String generateToken() {
        return "TOKEN_CODE_AND_CREDENCE";
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        try (BufferedReader reader = request.getReader()) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

}
