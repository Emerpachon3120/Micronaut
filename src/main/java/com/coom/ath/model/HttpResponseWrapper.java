package com.coom.ath.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Introspected
@SerdeImport(HttpResponseWrapper.class)
public class HttpResponseWrapper {

    private String responseBody;
    private int statusCode;
}