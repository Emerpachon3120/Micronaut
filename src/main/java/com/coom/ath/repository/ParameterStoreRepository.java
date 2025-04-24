package com.coom.ath.repository;


import com.coom.ath.model.ParameterStoreDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Slf4j
public class ParameterStoreRepository {
    public ParameterStoreDto getParameter(){
        ParameterStoreDto parameter = new ParameterStoreDto();

        //Representa dicionario o tabla clave valor
        Map<String, String> parameterSemillero = com.coom.ath.util.ParameterStoreUtil.getParameters("/Semillero/capacitacion-semillero/");
        log.info("Recibí los parámetros del Parameter Store: " + parameterSemillero);

        //Asigna al objeto, los parametros que encuentra en cada uno de la ruta
        parameter.setTabla(parameterSemillero.get("/Semillero/capacitacion-semillero/nombreTabla"));
        parameter.setRegion(parameterSemillero.get("/Semillero/capacitacion-semillero/region"));
        parameter.setUrlDynamo(parameterSemillero.get("/Semillero/capacitacion-semillero/dynamoEndpoint"));
        parameter.setArnSecret(parameterSemillero.get("/Semillero/capacitacion-semillero/arnSecret"));

        //Muesta el Url Dynamo
        log.info("Parametros correctos" + parameter.getUrlDynamo());

        //Retorna un parameter
        return parameter;

    }
}
