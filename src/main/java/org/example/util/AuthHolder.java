package org.example.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthHolder {
    public String tokenUserName = null;
    public String tokenPassword = null;

    public void reset() {
        tokenUserName = null;
        tokenPassword = null;
    }
}