package com.coom.ath.service;




import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coom.ath.mapper.UsuarioMapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.repository.DynamoMapperRepository;
import com.coom.ath.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamoService  implements com.coom.ath.service.IDynamoService {

    DynamoMapperRepository repository = new DynamoMapperRepository();
    UsuarioMapper usuarioMapper = new UsuarioMapper();
    @Override
    public String saveUsuario(DynamoDBMapper mapper, Usuario usuario){
        UsuarioEntity usuarioEntity = usuarioMapper.mappingUser(usuario);
        log.info("Entidad a guardar: {}", Util.object2String(usuarioEntity));
        repository.save(mapper, usuarioEntity);
        log.info("Guardado completo en DynamoDB");

        return "Guardado exitosamente";
    }
    @Override
    public UsuarioEntity getUsuario(DynamoDBMapper mapper, Usuario usuario){
        String id = usuario.getTipI()+ "_" + usuario.getNumero();
        UsuarioEntity response = repository.load(mapper, id);
        return response;
    }


}
