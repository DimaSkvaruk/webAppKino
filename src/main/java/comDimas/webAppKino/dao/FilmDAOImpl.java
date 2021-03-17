package comDimas.webAppKino.dao;

import comDimas.webAppKino.model.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FilmDAOImpl implements FilmDAO {

    private static final Logger LOGGER = LogManager.getLogger(FilmDAOImpl.class.getName());

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Film> allFilms(int page) {
        Session session = sessionFactory.getCurrentSession();
        List from_film = session.createQuery("from Film").setFirstResult(5 * (page - 1)).setMaxResults(5).list();
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " Returns List size:" + from_film.size());
        return from_film;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Film> bestFilms(int page) {
        Session session = sessionFactory.getCurrentSession();
        List from_film = session.createQuery("from Film order by rate desc ").setFirstResult(5 * (page - 1)).setMaxResults(5).list();
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " Returns " + from_film.size() + " films sorted by rate with reversOrder");
        return from_film;
    }

    @Override
    @Transactional
    public List newFilms(int page) {
        Session session = sessionFactory.getCurrentSession();
        List list = session.createQuery("from Film order by release desc ").setFirstResult(5 * (page - 1)).setMaxResults(5).list();
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " Returns " + list.size() + " films sorted by releaseDate with reverseOrder");
        return list;
    }

    @Override
    @Transactional
    public Film add(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);
        LOGGER.info("Added film " + film);
        return film;
    }

    @Override
    @Transactional
    public boolean delete(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);
        boolean deleted;
        deleted=!Optional.ofNullable(session.get(Film.class, film.getId())).isPresent();
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " Deleted film " + film);
        return deleted;
    }

    @Override
    @Transactional
    public boolean edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
        Film filmToCompare = session.find(Film.class, film.getId());
        boolean edited = false;
        if (film.equals(filmToCompare)) {
            edited = true;
        }
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " Film after editing " + filmToCompare);
        return edited;
    }

    @Override
    @Transactional
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Film film = session.get(Film.class, id);
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " Return film " + film);
        return film;
    }

    @Override
    @Transactional
    public int filmsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Film", Number.class).getSingleResult().intValue();
    }

    @Override
    @Transactional
    public boolean checkName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Film where name = :name");
        query.setParameter("name", name);
        return query.list().isEmpty();
    }
}
