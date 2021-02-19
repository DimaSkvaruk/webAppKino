package comDimas.webAppKino.dao;

import comDimas.webAppKino.model.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmDAOImpl implements FilmDAO {
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
        return session.createQuery("from Film").setFirstResult(5 * (page - 1)).setMaxResults(5).list();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Film> bestFilms(int page) {
        Session session=sessionFactory.getCurrentSession();

       return session.createQuery("from Film where rate>=8.0 order by rate DESC ").setFirstResult(5*(page-1)).setMaxResults(5).list();
    }

    @Override
    @Transactional
    public List newFilms(int page) {
        Session session=sessionFactory.getCurrentSession();
        return session.createQuery("from Film order by release desc ").setFirstResult(5*(page-1)).setMaxResults(5).list() ;
    }

    @Override
    @Transactional
    public Film add(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);
        return film;
    }

    @Override
    @Transactional
    public boolean delete(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);
        boolean deleted=false;
        Film filmAfterDelete = session.get(Film.class, film.getId());
        if (filmAfterDelete==null){
            deleted=true;
        }
        return deleted;
    }

    @Override
    @Transactional
    public boolean edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
        Film filmToCompare = session.find(Film.class, film.getId());
        boolean edited=false;
        if (film.equals(filmToCompare)){
             edited=true;
        }
        return edited;
    }

    @Override
    @Transactional
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
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
