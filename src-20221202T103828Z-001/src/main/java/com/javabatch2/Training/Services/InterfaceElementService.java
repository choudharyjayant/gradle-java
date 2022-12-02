package com.javabatch2.Training.Services;

import com.javabatch2.Training.Models.Element;
import com.javabatch2.Training.dto.ElementDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



public interface InterfaceElementService{

    Mono<Element> addElementByProjectAndSlateId(String projectId, String slateId, ElementDto elementDto);

    Flux<Element> getElementByProjectIdandSlateId(String projectId, String slateId);

    Mono<Element> updateElement(String projectId, String slateId, String elementId, ElementDto elementDto);

    Mono<Void> deleteElementById(String projectId, String slateId, String elementId);

    Mono<Element> getElementsByElementId(String projectId, String slateId, String elementId);

}
