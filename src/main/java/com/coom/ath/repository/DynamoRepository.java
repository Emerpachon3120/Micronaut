package com.coom.ath.repository;

import com.coom.ath.model.entity.UsuarioEntity;
import com.coom.ath.util.Util;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import java.io.PrintWriter;
import java.io.StringWriter;
import static com.coom.ath.model.entity.UsuarioEntity.SCHEMA_USUARIO_SPI;

@Slf4j
public class DynamoRepository {

    //Confguracion para hacer conexion a la base de datos

    public void save(UsuarioEntity usuarioEntity, DynamoDbEnhancedClient client, String nameTable) {
        log.info("Inicia guardado/actualizacion (save) en dynamoDb");
        log.info("table: " + nameTable);
        log.info("Registro a guardar: " + Util.object2String(usuarioEntity));

        try {
            var table = client.table(nameTable, SCHEMA_USUARIO_SPI);  // Usar 'table' en lugar de 'tablel'
            table.putItem(usuarioEntity);
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            log.error("error save: " + errors);
            throw new RuntimeException("No se pudo guardar el usuario", e);  // Lanzar la excepción correctamente
        }
    }


    public UsuarioEntity load(String id, String sk, DynamoDbEnhancedClient client, String nameTable){
        log.info("Inicia carga (load) desde DynamoDB");
        log.info("table: " + nameTable);
        log.info("ID: " + id + ", SK: " + sk);

        try {
            DynamoDbTable<UsuarioEntity> table = client.table(nameTable, SCHEMA_USUARIO_SPI);
            Key key = Key.builder()
                    .partitionValue(id)
                    .build();

            UsuarioEntity usuario = table.getItem(r -> r.key(key));
            log.info("Resultado obtenido: " + Util.object2String(usuario));
            return usuario;

        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            log.error("Error al cargar registro: " + errors);
            return null;
        }
    }

}
