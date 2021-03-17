package com.Dimas.webAppKino.services;

import com.Dimas.webAppKino.dao.FilmDAO;
import com.Dimas.webAppKino.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FilmServiceImp implements FilmService {

    private FilmDAO filmDAO;

    @Autowired
   public FilmServiceImp(FilmDAO filmDAO){
        this.filmDAO=filmDAO;
    }

    @Override
    @Transactional
    public List<Film> allFilms(int page) {
        return filmDAO.allFilms(page);
    }

    @Override
    @Transactional
    public List<Film> bestFilms(int page) {
        return filmDAO.bestFilms(page);
    }

    @Override
    @Transactional
    public List<Film> newFilms(int page) {
        return filmDAO.newFilms(page);
    }

    @Override
    @Transactional
    public Film add(Film film) {
       return filmDAO.add(film);
    }

    @Override
    @Transactional
    public boolean delete(Film film) {
        return filmDAO.delete(film);
    }

    @Override
    @Transactional
    public boolean edit(Film film) {
        return filmDAO.edit(film);
    }

    @Override
    @Transactional
    public Film getById(int id) {
      return filmDAO.getById(id);
    }

    @Override
    @Transactional
    public int filmsCount() {
       return filmDAO.filmsCount();
    }

    @Override
    @Transactional
    public boolean checkName(String title) {
        return filmDAO.checkName(title);
    }
}
