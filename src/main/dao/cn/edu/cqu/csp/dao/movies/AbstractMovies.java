package cn.edu.cqu.csp.dao.movies;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import cn.edu.cqu.csp.dao.tags.Tags;


/**
 * AbstractMovies entity provides the base persistence definition of the Movies entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractMovies  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String moviename;
     private String movietag;
     private String filepath;
     private String photopath;
     private String description;
     private String duration;
     private Integer doubanid;
     private String doubanscore;
     private Integer newestscore;
     private Integer hottestscore;
     private Integer classicscore;
     private String country;
     private Integer year;
     private String director;
     private String actor;
     private Integer count;


     
     
     
    // Constructors

    





	/** default constructor */
    public AbstractMovies() {
    }

    
    /** full constructor */
    public AbstractMovies(String moviename, String movietag, String filepath, String photopath, String description, String duration, Integer doubanid, String doubanscore, Integer newestscore, Integer hottestscore, Integer classicscore, String country, Integer year, String director, String actor, Integer count) {
        this.moviename = moviename;
        this.movietag = movietag;
        this.filepath = filepath;
        this.photopath = photopath;
        this.description = description;
        this.duration = duration;
        this.doubanid = doubanid;
        this.doubanscore = doubanscore;
        this.newestscore = newestscore;
        this.hottestscore = hottestscore;
        this.classicscore = classicscore;
        this.country = country;
        this.year = year;
        this.director = director;
        this.actor = actor;
        this.count = count;
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
    
    @Column(name="moviename")

    public String getMoviename() {
        return this.moviename;
    }
    
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }
    
    @Column(name="movietag")

    public String getMovietag() {
        return this.movietag;
    }
    
    public void setMovietag(String movietag) {
        this.movietag = movietag;
    }
    
    @Column(name="filepath")

    public String getFilepath() {
        return this.filepath;
    }
    
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    @Column(name="photopath")

    public String getPhotopath() {
        return this.photopath;
    }
    
    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
    
    @Column(name="description", length=65535)

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="duration")

    public String getDuration() {
        return this.duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    @Column(name="doubanid")

    public Integer getDoubanid() {
        return this.doubanid;
    }
    
    public void setDoubanid(Integer doubanid) {
        this.doubanid = doubanid;
    }
    
    @Column(name="doubanscore")

    public String getDoubanscore() {
        return this.doubanscore;
    }
    
    public void setDoubanscore(String doubanscore) {
        this.doubanscore = doubanscore;
    }
    
    @Column(name="newestscore")

    public Integer getNewestscore() {
        return this.newestscore;
    }
    
    public void setNewestscore(Integer newestscore) {
        this.newestscore = newestscore;
    }
    
    @Column(name="hottestscore")

    public Integer getHottestscore() {
        return this.hottestscore;
    }
    
    public void setHottestscore(Integer hottestscore) {
        this.hottestscore = hottestscore;
    }
    
    @Column(name="classicscore")

    public Integer getClassicscore() {
        return this.classicscore;
    }
    
    public void setClassicscore(Integer classicscore) {
        this.classicscore = classicscore;
    }
    
    @Column(name="country")

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Column(name="year")

    public Integer getYear() {
        return this.year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    @Column(name="director")

    public String getDirector() {
        return this.director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    @Column(name="actor")

    public String getActor() {
        return this.actor;
    }
    
    public void setActor(String actor) {
        this.actor = actor;
    }
    
    @Column(name="count")

    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
   








}