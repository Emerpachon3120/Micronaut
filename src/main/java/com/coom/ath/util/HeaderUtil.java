package com.coom.ath.util;

import com.coom.ath.model.HeadersRq;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpPost;
import static com.coom.ath.constants.HeadersEnum.*;

/**
 * HeaderUtil
 * <p>
 * Desarrollo ATH - SPBVI
 * <p>
 * Creado el: 29 de octubre de 2024
 *
 * @author Luis F. Herreño Mateus
 * @version 1.0
 * @since 1.0
 * <p>
 * Requerimiento: SPBVI - Sistema de pagos de bajo valor inmediatos
 * <p>
 * Copyright © A Toda Hora S.A. Todos los derechos reservados
 * <p>
 * Este software es confidencial y es propiedad de ATH, queda prohibido
 * su uso, reproducción y copia de manera parcial o permanente salvo autorización
 * expresa de A Toda Hora S.A o de quién represente sus derechos.
 * <p>
 * <p>
 * Clase HeaderUtil encargada de añadir encabezados para realiza el consumo de los
 * servicios
 */
@NoArgsConstructor
@Slf4j
public class HeaderUtil {

    public void addEnrollmentHeaders(HttpPost httpPost,
                                     HeadersRq headers) {

        httpPost.setHeader(CONTENT_TYPE.getValue(), headers.getContentType());
      //  httpPost.setHeader(CONTENT_LENGTH.getValue(), headers.getContentLength());
    }


}
