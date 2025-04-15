package com.coom.ath.model;

import com.coom.ath.model.entity.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Introspected
@SerdeImport(EnrollmentRq.class)
public class EnrollmentRq {

    //Primer nombre del cliente
    @JsonProperty("nombre")
    private String nombre;
    //Primer apellido del cliente
    @JsonProperty("apellido")
    private String apellido;

    //Tipo de llave seleccionada por el cliente para identificarse en el spbvi
    @JsonProperty("numeroDocumento")
    private String numeroDocumento;

    //Llave utilizada para identificar a la persona o comercio
    @JsonProperty("tipoDocumento")
    private String tipoDocumento;


}
