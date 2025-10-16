package com.adcb.login_service.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/decrypt")
    public String login(@RequestParam String username, @RequestParam String cipherText) {
        String url = "http://localhost:8082/crypto/decrypt?cipherText=" + cipherText;
        String plainPwd = restTemplate.postForObject(url, null, String.class);

        if ("mypassword123".equals(plainPwd)) {
            return "✅ Login successful for " + username;
        }
        return "❌ Invalid credentials for " + username;
    }
}