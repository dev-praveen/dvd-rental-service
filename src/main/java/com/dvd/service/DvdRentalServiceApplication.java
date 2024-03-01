package com.dvd.service;

import com.dvd.service.tables.records.ActorRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.dvd.service.Tables.ACTOR;

@SpringBootApplication
public class DvdRentalServiceApplication {

  @Autowired DSLContext dslContext;

  public static void main(String[] args) {
    SpringApplication.run(DvdRentalServiceApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      final Result<ActorRecord> result = dslContext.selectFrom(ACTOR).fetch();
      System.out.println("actor table count " + (long) result.size());
    };
  }
}
