package com.coom.ath.service;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.UsuarioEntity;

public interface IDynamoService {
    String saveUsuario(DynamoDBMapper mapper, Usuario usuario);

    UsuarioEntity getUsuario(DynamoDBMapper mapper, Usuario usuario);
}
