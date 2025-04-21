package com.coom.ath.service;




import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coom.ath.mapper.UsuarioMapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.repository.DynamoMapperRepository;
import com.coom.ath.repository.DynamoRepository;
import com.coom.ath.util.Util;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Slf4j
public class DynamoService implements IDynamoService {

    DynamoRepository repository = new DynamoRepository();
    UsuarioMapper usuarioMapper = new UsuarioMapper();

    @Override
    public String saveUsuario(DynamoDbEnhancedClient client, Usuario usuario, String tableName){
        log.info("Ingresa a save");
        UsuarioEntity usuarioEntity = usuarioMapper.mappingUser(usuario);
        log.info("Entidad a guardar: {}", Util.object2String(usuarioEntity));
        repository.save(usuarioEntity, client, tableName);
        log.info("Guardado completo en DynamoDB");

        return "Guardado exitosamente";
    }


    @Override
    public UsuarioEntity getUsuario(DynamoDbEnhancedClient client, Usuario usuario, String tableName){
        log.info("Ingresa a getUser");
        String id = usuario.getTipI() + "_" + usuario.getNumero();
        UsuarioEntity response = repository.load(id, id, client, tableName);

        return response;
    }


}
