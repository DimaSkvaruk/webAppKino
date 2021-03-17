package com.Dimas.webAppKino.dao;

import com.Dimas.webAppKino.model.Film;
import java.util.List;

public interface FilmDAO {
    List<Film> allFilms(int page);

    List<Film> bestFilms(int page);

    List<Film> newFilms(int page);

    Film add(Film film);

    boolean delete(Film film);

    boolean edit(Film film);

    Film getById(int id);

    int filmsCount();

    boolean checkName(String title);
}
