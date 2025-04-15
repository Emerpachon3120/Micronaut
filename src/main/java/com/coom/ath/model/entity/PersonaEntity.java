package com.coom.ath.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Getter
@Setter
@DynamoDBDocument
@Introspected
@SerdeImport(PersonaEntity.class)
public class PersonaEntity {

    @DynamoDBAttribute
    protected  String email;
    @DynamoDBAttribute
    protected String telefono;
    @DynamoDBAttribute
    protected String tipoP;
    @DynamoDBAttribute
    protected String nombre;
    @DynamoDBAttribute
    protected String apellido;

    public static final TableSchema<PersonaEntity> SCHEMA =
            TableSchema.builder(PersonaEntity.class)
                    .newItemSupplier(PersonaEntity::new)
                    .addAttribute(String.class, a->a.name("email")
                            .getter(PersonaEntity::getEmail)
                            .setter(PersonaEntity::setEmail))
                    .addAttribute(String.class, a->a.name("telefono")
                            .getter(PersonaEntity::getTelefono)
                            .setter(PersonaEntity::setTelefono))
                    .addAttribute(String.class, a->a.name("tipoP")
                            .getter(PersonaEntity::getTipoP)
                            .setter(PersonaEntity::setTipoP))
                    .addAttribute(String.class, a->a.name("nombre")
                            .getter(PersonaEntity::getNombre)
                            .setter(PersonaEntity::setNombre))
                    .addAttribute(String.class, a->a.name("apellido")
                            .getter(PersonaEntity::getApellido)
                            .setter(PersonaEntity::setApellido))
                    .build();
}
