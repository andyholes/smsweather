package com.andyholes.smsweather.service.impl;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGenerator {

    public String generateCode() {
        Random rand = new Random();
        return rand.ints(48, 123)
                .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                .limit(6)
                .mapToObj(c -> (char) c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
    }
}
