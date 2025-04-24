package com.coom.ath.service;

import com.coom.ath.mapper.UsuarioMapper;
import com.coom.ath.model.Usuario;
import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.repository.DynamoRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import static org.mockito.Mockito.*;

public class DynamoServiceTest {

    private DynamoRepository repositoryMock;
    private UsuarioMapper mapperMock;
    private DynamoService dynamoService;
    private DynamoDbEnhancedClient clientMock;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(DynamoRepository.class);
        mapperMock = mock(UsuarioMapper.class);
        clientMock = mock(DynamoDbEnhancedClient.class);

        // Creamos una subclase anónima para inyectar los mocks
        dynamoService = new DynamoService() {
            {
                this.repository = repositoryMock;
                this.usuarioMapper = mapperMock;
            }
        };
    }

    @Test
    public void testSaveUsuario_callsRepositorySaveWithMappedEntity() {
        // Arrange
        Usuario usuario = new Usuario();
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        String tableName = "usuarios-test";

        when(mapperMock.mappingUser(usuario)).thenReturn(usuarioEntity);

        // Act
        String result = dynamoService.saveUsuario(clientMock, usuario, tableName);

        // Assert
        verify(mapperMock).mappingUser(usuario);
        verify(repositoryMock).save(usuarioEntity, clientMock, tableName);
        assert result.equals("Guardado exitosamente");
    }
}
