package com.nextloop.nearlog.config;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;

@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("oauth2_request_cookie"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("쿠키에 authorization request가 없습니다"));
        String value = cookie.getValue();
        return (OAuth2AuthorizationRequest) SerializationUtils.deserialize(Base64.getUrlDecoder().decode(value));
    }

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        String encodedRequest = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(authorizationRequest));
        Cookie cookie = new Cookie("oauth2_request_cookie", encodedRequest);
        cookie.setPath("/");                // 추가사항
        cookie.setHttpOnly(true);           // 추가사항
        cookie.setMaxAge(60*3);
        response.addCookie(cookie);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }
}
