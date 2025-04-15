
package com.coom.ath;
import co.com.semillero.util.BuildResponseUtil;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    ParameterStoreRepository parameterRepository = new ParameterStoreRepository();
    ParameterStoreDto parameterDto = parameterRepository.getParameter();
    DynamoRepository dynamoRepository = new DynamoRepository();
    DynamoDBMapper dynamoDBMapper = dynamoRepository.build(parameterDto);
    DynamoService dynamoService = new DynamoService();
    ConsumiService consumiService = new ConsumiService();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
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
                        Usuario usuarioGuardar = Util.string2object(input.getBody(), Usuario.class);
                        log.info("guardo: " + usuarioGuardar);
                        return dynamoService.saveUsuario(dynamoDBMapper, usuarioGuardar);
                    case "consultar":
                        Usuario usuarioConsultar = (Usuario) Util.string2object(input.getBody(), Usuario.class);
                        return dynamoService.getUsuario(dynamoDBMapper, usuarioConsultar);
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
