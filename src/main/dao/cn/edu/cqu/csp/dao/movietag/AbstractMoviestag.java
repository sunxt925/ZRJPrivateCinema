package cn.edu.cqu.csp.dao.movietag;
// default package

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;


/**
 * AbstractMoviestag entity provides the base persistence definition of the Moviestag entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractMoviestag  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer moviesid;
     private Integer tagid;


    // Constructors

    /** default constructor */
    public AbstractMoviestag() {
    }

    
    /** full constructor */
    public AbstractMoviestag(Integer moviesid, Integer tagid) {
        this.moviesid = moviesid;
        this.tagid = tagid;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="moviesid", nullable=false)

    public Integer getMoviesid() {
        return this.moviesid;
    }
    
    public void setMoviesid(Integer moviesid) {
        this.moviesid = moviesid;
    }
    
    @Column(name="tagid", nullable=false)

    public Integer getTagid() {
        return this.tagid;
    }
    
    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }
   








}