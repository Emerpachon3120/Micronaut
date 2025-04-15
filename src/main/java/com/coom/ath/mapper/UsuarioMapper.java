package com.coom.ath.mapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.IdentificacionEntity;
import com.coom.ath.model.entity.LlaveEntity;
import com.coom.ath.model.entity.PersonaEntity;
import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.util.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsuarioMapper {

    public UsuarioEntity mappingUser (Usuario usuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        IdentificacionEntity identificacionEntity = new IdentificacionEntity();
        LlaveEntity llaveEntity = new LlaveEntity();
        PersonaEntity personaEntity = new PersonaEntity();

        identificacionEntity.setTipI(usuario.getTipI());
        identificacionEntity.setNumero(usuario.getNumero());
        String id = identificacionEntity.getTipI() + "_" + identificacionEntity.getNumero();

        log.info("Mapper", id);
        usuarioEntity.setId(id);
        usuarioEntity.setSk(id);

        llaveEntity.setTipoL(usuario.getTipoL());
        llaveEntity.setValor(usuario.getValor());
        llaveEntity.setFechaHoraCreacion(usuario.getFechaHoraCreacion());

        personaEntity.setTipoP(usuario.getTipoP());
        personaEntity.setNombre(usuario.getNombre());
        personaEntity.setApellido(usuario.getApellido());
        personaEntity.setEmail(usuario.getEmail());
        personaEntity.setTelefono(usuario.getTelefono());

        usuarioEntity.setLlave(llaveEntity);
        usuarioEntity.setIdentificacion(identificacionEntity);
        usuarioEntity.setPersona(personaEntity);

        log.info("Mapper", Util.object2String(usuarioEntity));
        return usuarioEntity;

     }
}