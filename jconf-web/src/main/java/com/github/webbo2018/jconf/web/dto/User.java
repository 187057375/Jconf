package com.github.webbo2018.jconf.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by shenwenbo on 2017/4/16.
 */
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
}