package com.coom.ath.repository;

import com.coom.ath.model.entity.PersonaEntity;
import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.repository.DynamoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static com.coom.ath.model.entity.UsuarioEntity.SCHEMA_USUARIO_SPI;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.*;

public class DynamoRepositoryTest {

    @Test
    void testSave_shouldCallPutItem() {
        // Crear el mock para la tabla de DynamoDB
        DynamoDbTable<UsuarioEntity> mockTable = mock(DynamoDbTable.class);

        // Crear el mock del cliente de DynamoDB
        DynamoDbEnhancedClient mockClient = mock(DynamoDbEnhancedClient.class);

        // Configurar el mock para que el cliente retorne la tabla mockeada cuando se llama a 'table'
        when(mockClient.table(anyString(), eq(SCHEMA_USUARIO_SPI))).thenReturn(mockTable);

        // Crear el objeto de entidad para el usuario
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId("12345");
        PersonaEntity p = new PersonaEntity();
        p.setNombre("Emerson");
        usuarioEntity.setPersona(p);

        // Crear instancia del repositorio con el mock
        DynamoRepository repository = new DynamoRepository();

        // Llamar al método save con los parámetros necesarios
        String nameTable = "Usuarios";
        repository.save(usuarioEntity, mockClient, nameTable);

        // Verificar que el método putItem se haya llamado en la tabla mock con el usuarioEntity
        verify(mockTable, times(1)).putItem(usuarioEntity);
    }

    @Test
    void testSave() {
        // Mock del cliente y la tabla
        DynamoDbEnhancedClient clientMock = mock(DynamoDbEnhancedClient.class);
        DynamoDbTable<UsuarioEntity> tableMock = mock(DynamoDbTable.class);

        // Lo que retorna cuando se llama client.table(...)
        when(clientMock.table(eq("nombreTabla"), any(TableSchema.class)))
                .thenReturn(tableMock);

        // Crear un usuario de prueba
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId("12345");

        // Instanciar la clase real (¡no se mockea el repo!)
        DynamoRepository repository = new DynamoRepository();

        // Ejecutar el método
        assertDoesNotThrow(() -> repository.save(usuario, clientMock, "nombreTabla"));

        // Verificar que se llamó putItem con el usuario correcto
        verify(tableMock).putItem(usuario);
    }



}
