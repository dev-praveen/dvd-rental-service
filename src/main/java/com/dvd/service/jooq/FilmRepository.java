package com.dvd.service.jooq;

import com.dvd.service.model.Movie;
import com.dvd.service.model.MovieActor;
import com.dvd.service.model.MovieCast;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.dvd.service.Tables.*;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

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

  public List<MovieCast> fetchAllMoviesCast() {

    return database
        .select(
            FILM.TITLE,
            multiset(
                    select(FILM_ACTOR.actor().FIRST_NAME, FILM_ACTOR.actor().LAST_NAME)
                        .from(FILM_ACTOR)
                        .limit(5))
                .as("actors")
                .convertFrom(r -> r.map(mapping(MovieActor::new))),
            multiset(select(FILM_CATEGORY.category().NAME).from(FILM_CATEGORY).limit(5))
                .as("categories")
                .convertFrom(r -> r.map(Record1::value1)))
        .from(FILM)
        .orderBy(FILM.TITLE)
        .fetch(mapping(MovieCast::new));
  }
}
