package com.coom.ath.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@SerdeImport(ParameterStoreDto.class)
//Clase donde sevan a guardar los parameters store que estan  en la rutas
public class ParameterStoreDto {
    protected String urlDynamo;
    protected String region;
    protected String arnSecret;
    protected String tabla;
    protected String urlConnection;

}
