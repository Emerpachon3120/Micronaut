package com.coom.ath.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@Getter
@Setter
@Introspected
@SerdeImport(IdentificacionEntity.class)
@DynamoDBDocument
public class IdentificacionEntity {

    @DynamoDBAttribute
    protected String numero;
    @DynamoDBAttribute
    protected String tipI;

    public static final TableSchema<IdentificacionEntity> SCHEMA =
            TableSchema.builder(IdentificacionEntity.class)
                    .newItemSupplier(IdentificacionEntity::new)
                    .addAttribute(String.class, a->a.name("numero")
                            .getter(IdentificacionEntity::getNumero)
                            .setter(IdentificacionEntity::setNumero))
                    .addAttribute(String.class, a->a.name("tipI")
                            .getter(IdentificacionEntity::getTipI)
                            .setter(IdentificacionEntity::setTipI))
                    .build();

}
