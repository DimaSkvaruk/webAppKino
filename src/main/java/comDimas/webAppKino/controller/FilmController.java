package comDimas.webAppKino.controller;

import comDimas.webAppKino.dao.FilmDAO;
import comDimas.webAppKino.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FilmController {
    private int page;
    private FilmDAO filmDAO;

    @Autowired
    public void setFilmService(FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
        List<Film> films = filmDAO.allFilms(page);
        int filmsCount = filmDAO.filmsCount();
        int pagesCount = (filmsCount + 4)/5;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }
    @RequestMapping(value = "/bestFilms",method = RequestMethod.GET)
    public ModelAndView bestFilms(@RequestParam(defaultValue = "1") int page){
        List<Film>bestFilms=filmDAO.bestFilms(page);
        int filmsCount = filmDAO.filmsCount();
        int pagesCount = (filmsCount + 4)/5;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("bestFilms");
        modelAndView.addObject("bestFilms",bestFilms);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }
    @RequestMapping(value = "/newFilms",method = RequestMethod.GET)
    public ModelAndView newFilms(@RequestParam(defaultValue = "1") int page){
        List<Film>newFilms=filmDAO.newFilms(page);
        int filmsCount = filmDAO.filmsCount();
        int pagesCount = (filmsCount + 4)/5;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("newFilms");
        modelAndView.addObject("newFilms",newFilms);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/about")
    public String message(){
        return "message";
    }



    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmDAO.checkName(film.getName())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmDAO.add(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getName() + "\" already exists");
            modelAndView.setViewName("redirect:/add");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id,
                                 @ModelAttribute("message") String message) {
        Film film = filmDAO.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        if (filmDAO.checkName(film.getName()) || filmDAO.getById(film.getId()).getName().equals(film.getName())) {
            modelAndView.setViewName("redirect:/");
            modelAndView.addObject("page", page);
            filmDAO.edit(film);
        } else {
            modelAndView.addObject("message","part with title \"" + film.getName() + "\" already exists");
            modelAndView.setViewName("redirect:/edit/" +  + film.getId());
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        int filmsCount = filmDAO.filmsCount();
        int page = ((filmsCount - 1) % 10 == 0 && filmsCount > 10 && this.page == (filmsCount + 9)/10) ?
                this.page - 1 : this.page;
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("page", page);
        Film film = filmDAO.getById(id);
        filmDAO.delete(film);
        return modelAndView;
    }
}
