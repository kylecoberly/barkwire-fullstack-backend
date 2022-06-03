package com.kylecoberly.barkwireapi.dog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {
  @Autowired
  private DogRepository dogRepository;

  public Map<String, Iterable<Dog>> list() {
    Iterable<Dog> dogs = dogRepository.findAll();
    return createHashPlural(dogs);
  }

  public Map<String, Iterable<Dog>> search(String searchTerm) {
    Iterable<Dog> dogs = dogRepository.findAll();
    List<Dog> dogsList = new ArrayList<Dog>();
    dogs.forEach(dogsList::add);

    List<Dog> filteredDogs = dogsList.stream().filter(dog -> {
      String dogName = dog.getName().toLowerCase();
      String search = searchTerm.toLowerCase();
      return dogName.matches("(.*)" + search + "(.*)");
    }).collect(Collectors.toList());
    
    return createHashPlural(filteredDogs);
  }

  public Map<String, Dog> create(Dog dog) {
    Dog savedDog = dogRepository.save(dog);
    return createHashSingular(savedDog);
  }


  private Map<String, Dog> createHashSingular(Dog dog){
    Map<String, Dog> response = new HashMap<String, Dog>();
    response.put("dog", dog);

    return response;
  }

  private Map<String, Iterable<Dog>> createHashPlural(Iterable<Dog> dogs){
    Map<String, Iterable<Dog>> response = new HashMap<String, Iterable<Dog>>();
    response.put("dogs", dogs);

    return response;
  }
}
