package com.dvd.service.jooq;

import com.dvd.service.model.Movie;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.dvd.service.Tables.FILM;

@Repository
public class FilmRepository {

  @Autowired private DSLContext database;

  public List<Movie> findAllFilms(Integer noOfFilms) {
    return database
        .select(FILM.FILM_ID, FILM.TITLE, FILM.DESCRIPTION, FILM.RELEASE_YEAR)
        .from(FILM)
        .limit(noOfFilms)
        .fetchInto(Movie.class)
        .stream()
        .toList();
  }
}
