package cn.edu.cqu.csp.dao.trailers;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Trailers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="trailers"
    ,catalog="cqzrj"
)
public class Trailers extends AbstractTrailers implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Trailers() {
    }

	/** minimal constructor */
    public Trailers(String moviename) {
        super(moviename);        
    }
    
    /** full constructor */
    public Trailers(String moviename, String movietag, String filepath, String photopath, String description, String duration, Integer doubanid, String doubanscore, String country, Integer year, String director, String actor, Integer count) {
        super(moviename, movietag, filepath, photopath, description, duration, doubanid, doubanscore, country, year, director, actor, count);        
    }
   
}
