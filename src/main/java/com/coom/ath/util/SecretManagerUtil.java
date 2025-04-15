package com.coom.ath.util;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.JsonObject;

/**
 * Clase para el manejo de tareas con Secrets Manager
 * <p>
 * SecretManagerUtil
 * <p>
 * Desarrollo ATH - Aval Pay Center
 * <p>
 * Creado él: 5 de mayo de 2023
 *
 * @author Luis F Herreño
 * @version 1.0
 * @since 1.0
 * <p>
 * Requerimiento: Migración Aval Pay Center
 * <p>
 * Copyright © A Toda Hora S.A. Todos los derechos reservados
 * <p>
 * Este software es confidencial y es propiedad de ATH, queda prohibido
 * su uso, reproducción y copia de manera parcial o permanente salvo autorización
 * expresa de A Toda Hora S.A o de quién represente sus derechos.
 */
public class SecretManagerUtil {
    /**
     * Constructor
     */
    private SecretManagerUtil() {
    }

    /**
     * Método que devuelve un JSON String con los valores de los secretos
     * Creando una conexion con AWSSecretManager, hace la consulta de secretos segun el nombre del secreto
     * devuelve un JsonObject con los valores obtenidos
     * Este metodo recibe los parametros cuando se llama a la libreria
     *
     * @param secretName Nombre del secreto a obtener
     * @return String JSON String del secreto
     */
    public static JsonObject getSecret(String secretName) {

        //Creacion del cliente para poder conectarse y recuperar los secretos
        AWSSecretsManager secretsManager = AWSSecretsManagerClientBuilder.defaultClient();

        // Obtener el secreto
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        //Obtener toda la informacion que contenga el secreto manager
        GetSecretValueResult getSecretValueResult = secretsManager
                .getSecretValue(getSecretValueRequest);

        return (JsonObject) Util.string2object(getSecretValueResult.getSecretString(),
                JsonObject.class);

    }

    /**
     * Método que devuelve el valor de un secreto usando una clave que se le asigna asi como el JsonObject.
     * Obtiene el valor de un secreto usando la clave enviada
     * Este metodo recibe los parametros cuando se llama a la libreria
     * @param secret Secreto de donde obtener el valor
     * @param clave  Clave del valor a obtener
     * @return String JSON String del secreto
     */

    //Consultar la llave y retornar la informacion que tenga
    public static String getSecretValue(JsonObject secret, String clave) {
        if (secret.has(clave)) {
            return secret.get(clave).getAsString();
        } else {
            return null;
        }

    }
}
