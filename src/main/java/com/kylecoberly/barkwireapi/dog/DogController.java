package com.kylecoberly.barkwireapi.dog;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@RestController
@RequestMapping("dogs")
public class DogController {
  @Autowired
  private DogService dogService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Dog>> list() {
    Iterable<Dog> dogs = dogService.list();
    return createHashPlural(dogs);
  }

  private Map<String, Iterable<Dog>> createHashPlural(Iterable<Dog> dogs){
    Map<String, Iterable<Dog>> response = new HashMap<String, Iterable<Dog>>();
    response.put("dogs", dogs);

    return response;
  }
}
