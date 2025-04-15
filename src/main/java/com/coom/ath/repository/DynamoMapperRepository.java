package com.coom.ath.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coom.ath.model.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DynamoMapperRepository {

    public  void save(DynamoDBMapper mapper, UsuarioEntity entity){

        log.info("Ingreso metodo guardar: ");
        mapper.save(entity);
        log.info("Guardo: " + entity);
    }
    public UsuarioEntity load(DynamoDBMapper mapper, String id){

        return mapper.load(UsuarioEntity.class, id,id);
    }
}
