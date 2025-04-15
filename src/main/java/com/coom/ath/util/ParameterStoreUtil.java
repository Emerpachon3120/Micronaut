package com.coom.ath.util;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.*;
import lombok.extern.log4j.Log4j2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de conectarse con el parameter Store
 * <p>
 * ExecutePaymentRq
 * <p>
 * Desarrollo ATH - Aval Pay Center
 * <p>
 * Creado él: 05 de mayo de 2023
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
@Log4j2
public class ParameterStoreUtil {

    /**
     * Método getParameter trae el parámetro del parameterStore
     * Utiliza la clase de AWSSimpleSystemsManagement para crear una conexion con los servicios de AWS
     * luego con el objeto GetParameterRequest define el tipo de valor que debe obtener del parameterStore
     * al ginal en un objeto GetParameterResult registra los resultados de la consulta usando el cliente y el request
     * Este metodo recibe los parametros cuando se llama a la libreria
     *
     * @param parameterName
     * @return
     */
    public static String getParameter(String parameterName) {
        try {
            //Conectar con el ambiente
            AWSSimpleSystemsManagement ssmClient = AWSSimpleSystemsManagementClientBuilder
                    .defaultClient();

            //Traer valores que esten relacionados con el PATCH
            GetParameterRequest parameterRequest = new GetParameterRequest()
                    .withName(parameterName)
                    .withWithDecryption(true);

            GetParameterResult parameterResult = ssmClient.getParameter(parameterRequest);

            return parameterResult.getParameter().getValue();
        } catch (ParameterNotFoundException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Método getParameters trae los parámetros del parameterStore relacionados con el path
     * primero crea una conexion con el cliente AWSSimpleSystemsManagement
     * Luego define el path que se usara para enviar el mensaje
     * En el GetParametersByPathResult   se almacena el resultado de la consulta
     * y luego se mapea en una lista de parametros
     * Este metodo recibe los parametros cuando se llama a la libreria
     * @param path
     * @return
     */
    public static Map<String, String> getParameters(String path) {

        AWSSimpleSystemsManagement ssmClient = AWSSimpleSystemsManagementClientBuilder.defaultClient();


        GetParametersByPathRequest getParametersByPathRequest = new GetParametersByPathRequest();
        getParametersByPathRequest.setPath(path);
        getParametersByPathRequest.setRecursive(true);

        GetParametersByPathResult parameterResult = ssmClient
                .getParametersByPath(getParametersByPathRequest);

        List<Parameter> list = parameterResult.getParameters();

        Map<String, String> values = new HashMap<>();

        for (Parameter param : list) {
            values.put(param.getName(), param.getValue());
        }
        return values;
    }

}
