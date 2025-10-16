package com.adcb.crypto_service.crypto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String cipherText) {
        return new String(Base64.getDecoder().decode(cipherText));
    }

    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }
}