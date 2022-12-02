package com.javabatch2.Training.handler;

import com.javabatch2.Training.Services.InterfaceElementService;
import com.javabatch2.Training.Models.Element;
import com.javabatch2.Training.dto.ElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ElementHandler {
   @Autowired
    private InterfaceElementService elementService;

    public Mono<ServerResponse> addElementByProjectAndSlateId (ServerRequest serverRequest) {
      String projectId = serverRequest.pathVariable("projectId");
      String slateId = serverRequest.pathVariable("slateId");

      Mono<ElementDto> elementDtoMono = serverRequest.bodyToMono(ElementDto.class);

        return elementDtoMono.flatMap(elementDto -> {
            return elementService.addElementByProjectAndSlateId(projectId, slateId, elementDto);
        }).flatMap(element -> {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(element), Element.class);
        });

    }
    public Mono<ServerResponse> getElementByProjectIdandSlateId(ServerRequest serverRequest) {
      String projectId = serverRequest.pathVariable("projectId");
      String slateId = serverRequest.pathVariable("slateId");

      Flux<Element> elementFlux = elementService.getElementByProjectIdandSlateId(projectId, slateId);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(elementFlux, Element.class);
    }

    public Mono<ServerResponse> updateElement(ServerRequest serverRequest) {
      String projectId = serverRequest.pathVariable("projectId");
      String slateId = serverRequest.pathVariable("slateId");
      String elementId = serverRequest.pathVariable("elementId");

      Mono<ElementDto> elementDtoMono = serverRequest.bodyToMono(ElementDto.class);

        return elementDtoMono.flatMap(elementDto -> {
            return elementService.updateElement(projectId, slateId, elementId, elementDto);
        }).flatMap(element -> {
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(element), Element.class);
        });

    }

    public Mono<ServerResponse> deleteElementById(ServerRequest serverRequest) {
        String projectId = serverRequest.pathVariable("projectId");
        String slateId = serverRequest.pathVariable("slateId");
        String elementId = serverRequest.pathVariable("elementId");

        Mono<Void> elements = elementService.deleteElementById(projectId, slateId, elementId);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(elements, Element.class);

    }

    public Mono<ServerResponse> getElementsByElementId(ServerRequest serverRequest) {
      String projectId = serverRequest.pathVariable("projectId");
      String slateId = serverRequest.pathVariable("slateId");
      String elementId = serverRequest.pathVariable("elementId");

      Mono<Element> elementMono = elementService.getElementsByElementId(projectId, slateId, elementId);

      return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(elementMono, Element.class);
    }

}
