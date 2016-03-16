package cn.edu.cqu.csp.dao.movietag;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Moviestag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="moviestag"
    ,catalog="cqzrj"
)
public class Moviestag extends AbstractMoviestag implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Moviestag() {
    }

    
    /** full constructor */
    public Moviestag(Integer moviesid, Integer tagid) {
        super(moviesid, tagid);        
    }
   
}
