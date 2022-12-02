package com.javabatch2.Training.Services;

import com.javabatch2.Training.Interfaces.ElementRepository;
import com.javabatch2.Training.Models.Element;
import com.javabatch2.Training.Services.InterfaceElementService;
import com.javabatch2.Training.dto.ElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ElementService implements InterfaceElementService {

  @Autowired
  private ElementRepository elementRepository;

  private String uuid() {
    return UUID.randomUUID().toString();
  }

  private String date() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
  }

  public Mono<Element> addElementByProjectandSlateId(String projectId, String slateId, ElementDto elementDto) {

    Element element = new Element();
    element.setWorkUrn(uuid());
    element.setEntityUrn(uuid());
    element.setText(elementDto.getText());
    element.setSlateManifestUrn(uuid());
    element.setType(elementDto.getType());
    element.setCreatedBy(elementDto.getCreatedBy());
    element.setUpdatedBy(elementDto.getUpdatedBy());
    element.setCreatedTimestamp(date());
    element.setUpdatedTimestamp(date());
    return elementRepository.save(element);
  }

  @Override
  public Mono<Element> addElementByProjectAndSlateId(String projectId, String slateId, ElementDto elementDto) {
    return null;
  }

  public Flux<Element> getElementByProjectIdandSlateId(String projectId, String slateId) {
    return elementRepository.findAll().filter(element -> !Objects.isNull(element.getSlateManifestUrn())
        && element.getSlateManifestUrn().equalsIgnoreCase(slateId));
  }

  // PUT /projects/{projectId}/slates/{slateId}/elements/{elementId}

  public Mono<Element> updateElement(String projectId, String slateId, String elementId, ElementDto elementDto) {
    Mono<Element> existingElement = elementRepository.findById(elementId);
    return existingElement.flatMap(element -> {
      element.setWorkUrn(elementId);
      element.setEntityUrn(uuid());
      element.setText(elementDto.getText());
      element.setSlateManifestUrn(slateId);
      element.setType(elementDto.getType());
      element.setCreatedBy(elementDto.getCreatedBy());
      element.setUpdatedBy(elementDto.getUpdatedBy());
      element.setCreatedTimestamp(date());
      element.setUpdatedTimestamp(date());
      return elementRepository.save(element);
    });
  }

  // DELETE /projects/{projectId}/slates/{slateId}/elements/{elementId}

  public Mono<Void> deleteElementById(String projectId, String slateId, String elementId) {
    return elementRepository.deleteById(elementId);



  }

  // GET /projects/{projectId}/slates/{slateId}/elements/{elementId}

  @Override
  public Mono<Element> getElementsByElementId(String projectId, String slateId, String elementId) {
    return elementRepository.findById(elementId).filter(element -> element.getSlateManifestUrn().equals(slateId));
  }
  private Pagination<Element> getPage(List<Element> elements, int pageNumber) {
    int skipCount = (pageNumber - 1) * 20;
    List<Element> elementPage = elements
        .stream()
        .skip(skipCount)
        .limit(20)
        .collect(Collectors.toList());

    Pagination<Element> page = new Pagination(pageNumber, elements.size(), elementPage);

    return page;
  }

}
