
package com.coom.ath;

import com.coom.ath.repository.DynamoMapperRepository;
import com.coom.ath.util.BuildResponseUtil;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.coom.ath.model.EnrollmentRq;
import com.coom.ath.model.HeadersRq;
import com.coom.ath.model.ParameterStoreDto;
import com.coom.ath.model.Usuario;
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

    //Crea un objeto que retorna ParameterRepository que retorna un ParameterStoreDto
    ParameterStoreRepository parameterRepository = new ParameterStoreRepository();
    //Crea un objeto parameterStoreDto, al cual se le asigna el parameter Repository
    ParameterStoreDto parameterDto = parameterRepository.getParameter();
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
            //Mira que el body no se encuentre vacio
            if (input.getBody() != null) {
                //Recupera el headers que envio desde postan
                String servicio = input.getHeaders().get("servicio");
                //Muestra en consola el Header
                log.info("Tiene servicio: " + servicio);
                //Dependiendo del servicio entra al case
                switch (servicio) {
                    case "guardar":
                        //Entra al servicio de guardar
                        log.info("Entro a servicio guardar: ");
                        //Crea una instancia de Usuario y le asigna el objeto PArceandolo a usuario
                        Usuario usuarioGuardar = (Usuario) Util.string2object(input.getBody(), Usuario.class);
                        //Muestra el objeto que guardo en la bd
                        log.info("guardo: " + usuarioGuardar);

                        return dynamoService.saveUsuario(dynamoDbEnhancedClient, usuarioGuardar, parameterDto.getTabla());
                    case "consultar":
                        Usuario usuarioConsultar = (Usuario) Util.string2object(input.getBody(), Usuario.class);
                        return dynamoService.getUsuario(dynamoDbEnhancedClient, usuarioConsultar,parameterDto.getTabla());
                    case "consultarAPI":
                        EnrollmentRq enrollmentRq = (EnrollmentRq) Util.string2object(input.getBody(), EnrollmentRq.class);
                        HeadersRq headersRq = (HeadersRq) Util.string2object(Util.object2String(input.getHeaders()), HeadersRq.class);
                        return consumiService.enrollmentKeyService(enrollmentRq, headersRq, parameterDto.getUrlDynamo());

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
