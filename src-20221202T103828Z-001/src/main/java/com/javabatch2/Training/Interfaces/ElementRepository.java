package com.javabatch2.Training.Interfaces;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.javabatch2.Training.Models.Element;

@Repository
public interface ElementRepository extends ReactiveMongoRepository<Element,String> {
}
