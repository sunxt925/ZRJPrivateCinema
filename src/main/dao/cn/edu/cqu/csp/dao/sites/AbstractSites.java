package cn.edu.cqu.csp.dao.sites;
// default package

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;


/**
 * AbstractSites entity provides the base persistence definition of the Sites entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractSites  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String sitename;
     private String sitepath;


    // Constructors

    /** default constructor */
    public AbstractSites() {
    }

    
    /** full constructor */
    public AbstractSites(String sitename, String sitepath) {
        this.sitename = sitename;
        this.sitepath = sitepath;
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
    
    @Column(name="sitename")

    public String getSitename() {
        return this.sitename;
    }
    
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }
    
    @Column(name="sitepath")

    public String getSitepath() {
        return this.sitepath;
    }
    
    public void setSitepath(String sitepath) {
        this.sitepath = sitepath;
    }
   








}