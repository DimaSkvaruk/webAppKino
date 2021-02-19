package comDimas.webAppKino.services;

import comDimas.webAppKino.dao.FilmDAO;
import comDimas.webAppKino.model.Film;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FilmServiceImpTest {

    @Mock
    FilmDAO filmDAO;

    FilmServiceImp filmServiceImp;

    public FilmServiceImpTest() {
        MockitoAnnotations.initMocks(this);
        this.filmServiceImp = new FilmServiceImp(filmDAO);

    }

    List<Film> filmList = new ArrayList<>();

    @BeforeAll
    void initListOfFilms() {

        filmList.add(new Film(11, "Titanic", "description", 7.3, new Date(1999 - 10 - 10), "img", "video"));
        filmList.add(new Film(11, "Terminator", "description", 8.3, new Date(2010 - 10 - 10), "img", "video"));
        filmList.add(new Film(11, "Matrix", "description", 9.3, new Date(2002 - 10 - 10), "img", "video"));
        filmList.add(new Film(11, "The Thing", "description", 10, new Date(2019 - 10 - 10), "img", "video"));
        filmList.add(new Film(11, "World", "description", 4.3, new Date(2020 - 10 - 10), "img", "video"));

    }

    @Test
    void allFilmsTest() {
        when(filmDAO.allFilms(1)).thenReturn((filmList));
        List<Film> films = filmServiceImp.allFilms(1);
        films.forEach(e -> System.out.println(e));
        assertArrayEquals(filmList.toArray(), films.toArray());
    }


    @Test
    void bestFilmsTest() {
        when(filmDAO.bestFilms(1)).thenReturn(List.of(new Film(11, "Titanic", "description", 8.3, new Date(2000 - 10 - 10), "img", "video"),
                new Film(2, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video"),
                new Film(2, "Terminator", "description", 9.3, new Date(2000 - 10 - 10), "img", "video")).stream().filter(r -> r.getRate() >= 8).collect(Collectors.toList()));
        List<Film> films = filmServiceImp.bestFilms(1);
        assertArrayEquals(new Film[]{new Film(11, "Titanic", "description", 8.3, new Date(2000 - 10 - 10), "img", "video"),
                new Film(2, "Terminator", "description", 9.3, new Date(2000 - 10 - 10), "img", "video")}, films.toArray());
    }

    @Test
    void newFilms() {
        when(filmDAO.newFilms(1)).thenReturn(filmList.stream().sorted((o1, o2) -> o1.getRelease().compareTo(o2.getRelease())).collect(Collectors.toList()));
        List<Film> films = filmServiceImp.newFilms(1);
        films.forEach(e-> System.out.println(e));
    }

    @Test
    void add() {
        when(filmDAO.add(new Film(1, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video"))).
                thenReturn(new Film(1, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video"));
        Film film = filmServiceImp.add(new Film(1, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video"));
        assertEquals("Speed", film.getName());
    }

    @Test
    void deleteTest() {
        when(filmDAO.delete(new Film (1, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video"))).thenReturn(true);
        boolean delete = filmServiceImp.delete(new Film(1, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video"));
        assertTrue(delete);
    }

    @Test
    void edit() {
        Film film = new Film(1, "Speed", "description", 6.3, new Date(2000 - 10 - 10), "img", "video");

        when(filmDAO.edit(film)).thenReturn(true);
        boolean edited = filmServiceImp.edit(film);
        assertTrue(edited);


    }

    @Test
    void getById() {
    }

    @Test
    void filmsCount() {
    }

    @Test
    @Disabled("not implemented yet")
    void checkName() {
    }
}