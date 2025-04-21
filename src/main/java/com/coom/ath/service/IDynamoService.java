package com.coom.ath.service;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.UsuarioEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

public interface IDynamoService {
    String saveUsuario(DynamoDbEnhancedClient client, Usuario usuario, String tableName);

    UsuarioEntity getUsuario(DynamoDbEnhancedClient client, Usuario usuario, String tableName);
}
