package com.dvd.service.resource;

import com.dvd.service.jooq.FilmRepository;
import com.dvd.service.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FilmController {

  @Autowired private FilmRepository filmRepository;

  @GetMapping("/films")
  public ResponseEntity<List<Movie>> getFilms(@RequestParam Integer noOfFilms) {

    return ResponseEntity.ok(filmRepository.findAllFilms(noOfFilms));
  }
}