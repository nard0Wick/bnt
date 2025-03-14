package com.leo.book.email;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ValidCode {
    public ValidCode(String code) {
        this.code = code;
    }
    private String code;
    private Date createdTime = new Date(System.currentTimeMillis());
    private Date expiredTime = new Date(createdTime.getTime() + 1000 * 5 * 60);
}
