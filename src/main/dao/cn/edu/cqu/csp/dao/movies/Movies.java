package cn.edu.cqu.csp.dao.movies;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Movies entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="movies"
    ,catalog="cqzrj"
)
public class Movies extends AbstractMovies implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Movies() {
    }

    
    /** full constructor */
    public Movies(String moviename, String movietag, String filepath, String photopath, String description, String duration, Integer doubanid, String doubanscore, Integer newestscore, Integer hottestscore, Integer classicscore, String country, Integer year, String director, String actor, Integer count,String othername,String englishname) {
        super(moviename, movietag, filepath, photopath, description, duration, doubanid, doubanscore, newestscore, hottestscore, classicscore, country, year, director, actor, count,othername,englishname);        
    }
   
}
