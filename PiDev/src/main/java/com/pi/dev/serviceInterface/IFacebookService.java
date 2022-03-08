package com.pi.dev.serviceInterface;

import org.springframework.ui.Model;

public interface IFacebookService {
    String generateFacebookAuthorizeUrl();

    void generateFacebookAccessToken(String code);

    Model getUserData(Model model);
}
