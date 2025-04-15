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
@SerdeImport(LlaveEntity.class)
public class LlaveEntity {

    @DynamoDBAttribute
    protected String tipoL;

    @DynamoDBAttribute
    protected String valor;

    @DynamoDBAttribute
    protected String fechaHoraCreacion;

    public static final TableSchema<LlaveEntity> SCHEMA =
            TableSchema.builder(LlaveEntity.class)
                    .newItemSupplier(LlaveEntity::new)
                    .addAttribute(String.class, a->a.name("tipoL")
                            .getter(LlaveEntity::getTipoL)
                            .setter(LlaveEntity::setTipoL))
                    .addAttribute(String.class, a->a.name("valor")
                            .getter(LlaveEntity::getValor)
                            .setter(LlaveEntity::setValor))
                    .addAttribute(String.class, a->a.name("fechaHoraCreacion")
                            .getter(LlaveEntity::getFechaHoraCreacion)
                            .setter(LlaveEntity::setValor))
                    .build();

}


