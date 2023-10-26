package com.lokesh.loginsignup.self_define;

import java.util.Objects;

public class hashCode {

    public String getHashCode(String password) {
        return String.valueOf(Objects.hash(password));

    }
}
