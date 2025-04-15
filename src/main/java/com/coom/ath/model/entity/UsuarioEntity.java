package com.coom.ath.model.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.SerdeImport;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTag;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags;

@Getter
@Setter
@Introspected
@SerdeImport(UsuarioEntity.class)
public class UsuarioEntity {

    @DynamoDBHashKey(attributeName = "id")
    protected String id;

    @DynamoDBRangeKey(attributeName = "sk")
    protected String sk;

    @DynamoDBAttribute(attributeName = "persona")
    protected PersonaEntity persona;

    @DynamoDBAttribute(attributeName = "llave")
    protected LlaveEntity llave;

    @DynamoDBAttribute(attributeName = "identificacion")
    protected IdentificacionEntity identificacion;

    public static final TableSchema<UsuarioEntity> SCHEMA_USUARIO_SPI =
            TableSchema.builder(UsuarioEntity.class)
                    .newItemSupplier(UsuarioEntity::new)
                    .addAttribute(String.class, a->a.name("id")
                            .getter(UsuarioEntity::getId)
                            .setter(UsuarioEntity::setId)
                            .tags(StaticAttributeTags.primaryPartitionKey()))
                    .addAttribute(String.class, a->a.name("sk")
                            .getter(UsuarioEntity::getSk)
                            .setter(UsuarioEntity::setSk)
                            .tags(StaticAttributeTags.primarySortKey()))
                    .addAttribute(
                            EnhancedType.documentOf(PersonaEntity.class, PersonaEntity
                                    .SCHEMA),
                            a->a.name("persona")
                                    .getter(UsuarioEntity::getPersona)
                                    .setter(UsuarioEntity::setPersona))
                    .addAttribute(
                            EnhancedType.documentOf(LlaveEntity.class, LlaveEntity
                                    .SCHEMA),
                            a->a.name("llave")
                                    .getter(UsuarioEntity::getLlave)
                                    .setter(UsuarioEntity::setLlave))
                    .addAttribute(
                            EnhancedType.documentOf(IdentificacionEntity.class, IdentificacionEntity
                                    .SCHEMA),
                            a->a.name("identificacion")
                                    .getter(UsuarioEntity::getIdentificacion)
                                    .setter(UsuarioEntity::setIdentificacion))
                    .build();
}




