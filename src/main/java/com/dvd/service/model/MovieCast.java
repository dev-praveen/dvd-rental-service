package com.dvd.service.model;

import java.util.List;

public record MovieCast(String title, List<MovieActor> actors, List<String> categories) {}