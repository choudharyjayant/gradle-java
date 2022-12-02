package com.javabatch2.Training.Router;

import com.javabatch2.Training.handler.ElementHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@EnableWebFlux
@Configuration
public class ElementRouter implements WebFluxConfigurer {
    @Autowired
    private ElementHandler elementHandler;

    @Bean
    public RouterFunction<ServerResponse> elementAPIs() {
        return route(POST("/projects/{projectId}/slates/{slateId}/elements"),
            elementHandler :: addElementByProjectAndSlateId)
            .andRoute(GET("/projects/{projectId}/slates/{slateId}/elements"),
                elementHandler::getElementByProjectIdandSlateId)
            .andRoute(PUT("/projects/{projectId}/slates/{slateId}/elements/{elementId}"),
                elementHandler::updateElement)
            .andRoute(DELETE("/projects/{projectId}/slates/{slateId}/elements/{elementId}"),
                elementHandler::deleteElementById)
            .andRoute(GET("/projects/{projectId}/slates/{slateId}/elements/{elementId}"),
                elementHandler::getElementsByElementId);

    }
}
