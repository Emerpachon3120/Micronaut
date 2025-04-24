package com.coom.ath.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@SerdeImport(Usuario.class)
public class Usuario {
    protected String tipoL;
    protected String valor;
    protected String fechaHoraCreacion;
    protected String tipI;
    protected String numero;
    protected String tipoP;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String telefono;

}
