package com.coom.ath.mapper;

import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioMapperTest {

    private UsuarioMapper mapper;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        mapper = new UsuarioMapper();

        usuario = new Usuario();
        usuario.setTipI("CC");
        usuario.setNumero("123456");
        usuario.setTipoL("EMAIL");
        usuario.setValor("usuario@correo.com");
        usuario.setFechaHoraCreacion("2024-04-24T12:00:00");

        usuario.setTipoP("NATURAL");
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setEmail("juan.perez@correo.com");
        usuario.setTelefono("123456789");
    }

    @AfterEach
    public void tearDown() {
        mapper = null;
        usuario = null;
    }

    @Test
    public void testMappingUser() {
        UsuarioEntity resultado = mapper.mappingUser(usuario);

        assertNotNull(resultado);
        assertEquals("CC_123456", resultado.getId());
        assertEquals("CC_123456", resultado.getSk());

        assertNotNull(resultado.getIdentificacion());
        assertEquals("CC", resultado.getIdentificacion().getTipI());
        assertEquals("123456", resultado.getIdentificacion().getNumero());

        assertNotNull(resultado.getLlave());
        assertEquals("EMAIL", resultado.getLlave().getTipoL());
        assertEquals("usuario@correo.com", resultado.getLlave().getValor());
        assertEquals("2024-04-24T12:00:00", resultado.getLlave().getFechaHoraCreacion());

        assertNotNull(resultado.getPersona());
        assertEquals("NATURAL", resultado.getPersona().getTipoP());
        assertEquals("Juan", resultado.getPersona().getNombre());
        assertEquals("Pérez", resultado.getPersona().getApellido());
        assertEquals("juan.perez@correo.com", resultado.getPersona().getEmail());
        assertEquals("123456789", resultado.getPersona().getTelefono());
    }
}
