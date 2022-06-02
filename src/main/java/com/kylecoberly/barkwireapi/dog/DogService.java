package com.kylecoberly.barkwireapi.dog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {
  @Autowired
  private DogRepository dogRepository;

  public Iterable<Dog> list() {
    return dogRepository.findAll();
  }
}
