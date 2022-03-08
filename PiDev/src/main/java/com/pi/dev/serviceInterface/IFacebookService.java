package com.pi.dev.serviceInterface;

public interface IFacebookService {
    String generateFacebookAuthorizeUrl();

    void generateFacebookAccessToken(String code);

    String getUserData();
}
