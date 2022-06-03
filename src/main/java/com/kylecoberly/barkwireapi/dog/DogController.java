package com.kylecoberly.barkwireapi.dog;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@RestController
@RequestMapping("dogs")
public class DogController {
  @Autowired
  private DogService dogService;

  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Dog>> search(@RequestParam String search) {
    return dogService.search(search);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Dog>> list() {
    return dogService.list();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Dog> create(@Validated @RequestBody Dog dog) {
    return dogService.create(dog);
  }
}
