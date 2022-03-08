package com.pi.dev.service;

import com.pi.dev.serviceInterface.IFacebookService;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements IFacebookService {

    private String accessToken;

    private static final String facebookAppId = "624106572022453";
    private static final String facebookSecret = "941a03491cdfc49201508c5340117948";
    private FacebookConnectionFactory createConnection() {
        return new FacebookConnectionFactory(facebookAppId, facebookSecret);
    }
    @Override
    public String generateFacebookAuthorizeUrl() {
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:8085/facebook/connect");
        params.setScope("email");
        return createConnection().getOAuthOperations().buildAuthenticateUrl(params);
    }

    @Override
    public void generateFacebookAccessToken(String code) {
        accessToken = createConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:8085/facebook/connect", null).getAccessToken();
    }

    @Override
    public String getUserData() {
        Facebook facebook = new FacebookTemplate(accessToken);
        String[] fields = {"id", "first_name", "name", "email", "birthday", "gender", "age_range"};
        return facebook.fetchObject("me", String.class, fields);
    }
}
