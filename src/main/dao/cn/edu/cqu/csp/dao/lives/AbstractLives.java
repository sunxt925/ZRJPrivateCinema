package cn.edu.cqu.csp.dao.lives;
// default package

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;


/**
 * AbstractLives entity provides the base persistence definition of the Lives entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractLives  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String livename;
     private String liveaddress;
     private String imageaddress;


    // Constructors

    /** default constructor */
    public AbstractLives() {
    }

    
    /** full constructor */
    public AbstractLives(String livename, String liveaddress, String imageaddress) {
        this.livename = livename;
        this.liveaddress = liveaddress;
        this.imageaddress = imageaddress;
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
    
    @Column(name="livename")

    public String getLivename() {
        return this.livename;
    }
    
    public void setLivename(String livename) {
        this.livename = livename;
    }
    
    @Column(name="liveaddress")

    public String getLiveaddress() {
        return this.liveaddress;
    }
    
    public void setLiveaddress(String liveaddress) {
        this.liveaddress = liveaddress;
    }
   
    @Column(name="imageaddress")

    public String getImageaddress() {
        return this.imageaddress;
    }
    
    public void setImageaddress(String imageaddress) {
        this.imageaddress = imageaddress;
    }








}