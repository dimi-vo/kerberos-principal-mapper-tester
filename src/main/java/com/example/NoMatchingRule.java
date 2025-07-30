package com.example;

import java.io.IOException;

public class NoMatchingRule extends IOException {
    NoMatchingRule(String msg) {
        super(msg);
    }
}
