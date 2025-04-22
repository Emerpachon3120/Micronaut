
package com.coom.ath;
import com.coom.ath.repository.DynamoMapperRepository;
import com.coom.ath.util.BuildResponseUtil;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.coom.ath.model.EnrollmentRq;
import com.coom.ath.model.HeadersRq;
import com.coom.ath.model.ParameterStoreDto;
import com.coom.ath.model.Usuario;
import com.coom.ath.repository.DynamoRepository;
import com.coom.ath.repository.ParameterStoreRepository;
import com.coom.ath.service.ConsumiService;
import com.coom.ath.service.DynamoService;
import com.coom.ath.util.Util;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.function.aws.MicronautRequestHandler;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Slf4j
@Introspected
public class Handler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    ParameterStoreRepository parameterRepository = new ParameterStoreRepository();
    ParameterStoreDto parameterDto = parameterRepository.getParameter();
    DynamoRepository dynamoRepository = new DynamoRepository();
    //DynamoDBMapper dynamoDBMapper = dynamoRepository.build(parameterDto);
    DynamoService dynamoService = new DynamoService();
    ConsumiService consumiService = new ConsumiService();
    DynamoMapperRepository dynamoMapperRepository = new DynamoMapperRepository(parameterDto.getRegion());
    DynamoDbEnhancedClient dynamoDbEnhancedClient = dynamoMapperRepository.getClient();

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {
        log.info("ParameterStore: " + Util.object2String(parameterDto));
        return BuildResponseUtil.buildSuccess(redirect(input));
    }

    // Validación y llamada a los diferentes tipos de servicios
    public Object redirect(APIGatewayProxyRequestEvent input) {
        try {
            if (input.getBody() != null) {
                String servicio = input.getHeaders().get("servicio");
                log.info("Tiene servicio: " + servicio);

                switch (servicio) {
                    case "guardar":
                        log.info("Entro a servicio guardar: ");
                        Usuario usuarioGuardar = (Usuario) Util.string2object(input.getBody(), Usuario.class);
                        log.info("guardo: " + usuarioGuardar);
                        return dynamoService.saveUsuario(dynamoDbEnhancedClient, usuarioGuardar, parameterDto.getTabla());
                    case "consultar":
                        Usuario usuarioConsultar = (Usuario) Util.string2object(input.getBody(), Usuario.class);
                        return dynamoService.getUsuario(dynamoDbEnhancedClient, usuarioConsultar,parameterDto.getTabla());
                    case "consultarAPI":
                        EnrollmentRq enrollmentRq = (EnrollmentRq) Util.string2object(input.getBody(), EnrollmentRq.class);
                        HeadersRq headersRq = (HeadersRq) Util.string2object(Util.object2String(input.getHeaders()), HeadersRq.class);
                        return consumiService.enrollmentKeyService(enrollmentRq, headersRq, parameterDto.getUrlDynamo());
                    case "mock":
                        return consumiService.mockRedebanResponse();
                    default:
                        return "Servicio no disponible";
                }
            }
            return "No tiene información";
        } catch (Exception e) {
            log.error("Error en redirect: ", e);
            return e.getMessage();
        }
    }


}
