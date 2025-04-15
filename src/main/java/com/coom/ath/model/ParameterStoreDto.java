package com.coom.ath.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterStoreDto {
    protected String urlDynamo;
    protected String region;
    protected String arnSecret;
    protected String tabla;
    protected String urlConnection;

}
