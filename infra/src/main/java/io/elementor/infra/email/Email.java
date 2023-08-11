package io.elementor.infra.email;

import lombok.Data;

import java.util.Date;

@Data
public class Email {
    private String from;
    private String body;
    private Date timestamp;

    public Email(String from, String body, Date timestamp) {
        this.from = from;
        this.body = body;
        this.timestamp = timestamp;
    }
}
