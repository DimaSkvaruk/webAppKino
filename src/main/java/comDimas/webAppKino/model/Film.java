package comDimas.webAppKino.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double rate;
    @Column(name = "releaseDate")
    private java.sql.Date release;
    @Column
    private String img;
    @Column
    private String video;

    public Film() {
    }

    public Film(int id, String name, String description, double rate, Date release, String img, String video) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.release = release;
        this.img = img;
        this.video = video;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public double getRate() {
        return rate;
    }


    public Date getRelease() {
        return release;
    }


    public String getImg() {
        return img;
    }


    public String getVideo() {
        return video;
    }


    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", release=" + release +
                ", img='" + img + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id &&
                Double.compare(film.rate, rate) == 0 &&
                Objects.equals(name, film.name) &&
                Objects.equals(description, film.description) &&
                Objects.equals(release, film.release) &&
                Objects.equals(img, film.img) &&
                Objects.equals(video, film.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, rate, release, img, video);
    }
}

