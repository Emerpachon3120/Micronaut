package com.coom.ath.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HttpResponseWrapper {

    private String responseBody;
    private int statusCode;
}