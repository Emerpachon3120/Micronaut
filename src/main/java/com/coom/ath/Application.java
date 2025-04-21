package com.coom.ath;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime;
import io.micronaut.runtime.Micronaut;
import lombok.extern.slf4j.Slf4j;
import java.net.MalformedURLException;

@Slf4j
public class Application extends AbstractMicronautLambdaRuntime<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent,
        APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public static void main(String[] args) throws MalformedURLException {
        log.info("Entro a Lambda handlerRuntime main()");
        new Application().run(args);
    }

    @Override
    protected RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>
    createRequestHandler(String... args){
        return new Handler();
    }
}