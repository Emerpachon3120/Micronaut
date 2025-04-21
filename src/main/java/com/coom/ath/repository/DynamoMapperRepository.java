package com.coom.ath.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coom.ath.model.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.ContainerCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@AllArgsConstructor
public class DynamoMapperRepository {
    /**
     * Region de AWS en donde se alojan los servicios. */ private String dynamoRegion;


    /**
     * Variable encargada de manejar mensajes de error para los catch */ static StringWriter errors;

    /**
     * Variable constante de ERROR */ static final String ERROR = "Error: ";

    public DynamoDbEnhancedClient getClient() {

        DynamoDbEnhancedClient enhancedClient;
        try {
            log.info("Entrando getClient");
            var dynamoDbClient = DynamoDbClient.builder().region(Region.of(dynamoRegion))
                    .credentialsProvider(
                            SdkSystemSetting.AWS_CONTAINER_CREDENTIALS_FULL_URI.getStringValue().isPresent()
                                    ? ContainerCredentialsProvider.builder().build()
                                    : EnvironmentVariableCredentialsProvider.create())
                    .build();

            enhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();

            log.info("Saliendo getClient");
            return enhancedClient;
        } catch (Exception e) {
            errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            log.error(ERROR + errors);
            log.info("Entra a default");
            return null;
        }
    }
    }