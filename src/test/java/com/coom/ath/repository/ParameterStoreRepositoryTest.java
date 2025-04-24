package com.coom.ath.repository;

import com.coom.ath.model.ParameterStoreDto;
import com.coom.ath.util.ParameterStoreUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.coom.ath.repository.ParameterStoreRepository;
import com.coom.ath.util.ParameterStoreUtil;
import com.coom.ath.model.ParameterStoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.Map;

public class ParameterStoreRepositoryTest {
//
    private ParameterStoreRepository parameterStoreRepository;

    @BeforeEach
    void setUp() {
        // Crear una instancia del repositorio
        parameterStoreRepository = new ParameterStoreRepository();
    }

    @Test
    void testGetParameter() {
        // Mockear el comportamiento de getParameters
        Map<String, String> mockParameters = Map.of(
                "/Semillero/capacitacion-semillero/nombreTabla", "TablaTest",
                "/Semillero/capacitacion-semillero/region", "us-west-1",
                "/Semillero/capacitacion-semillero/dynamoEndpoint", "http://localhost:8000",
                "/Semillero/capacitacion-semillero/arnSecret", "arn:aws:secretsmanager:region:account-id:secret:secret-id"
        );

        // Simular que ParameterStoreUtil devuelve los parámetros predefinidos
        Mockito.mockStatic(ParameterStoreUtil.class);
        when(ParameterStoreUtil.getParameters(anyString())).thenReturn(mockParameters);

        // Ejecutar el método a probar
        ParameterStoreDto result = parameterStoreRepository.getParameter();

        // Verificar que los valores retornados sean los esperados
        assertNotNull(result);
        assertEquals("TablaTest", result.getTabla());
        assertEquals("us-west-1", result.getRegion());
        assertEquals("http://localhost:8000", result.getUrlDynamo());
        assertEquals("arn:aws:secretsmanager:region:account-id:secret:secret-id", result.getArnSecret());
    }
}
