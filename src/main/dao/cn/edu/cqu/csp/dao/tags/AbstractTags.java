package cn.edu.cqu.csp.dao.tags;
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

import cn.edu.cqu.csp.dao.movies.Movies;


/**
 * AbstractTags entity provides the base persistence definition of the Tags entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractTags  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String tagname;
     private Integer selected;
     private String imageaddress;
     
     
    // Constructors

    


	/** default constructor */
    public AbstractTags() {
    }

    



	/** full constructor */
    public AbstractTags(String tagname, Integer selected, String imageaddress) {
        this.tagname = tagname;
        this.selected = selected;
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
    
    @Column(name="tagname", nullable=false)

    public String getTagname() {
        return this.tagname;
    }
    
    public void setTagname(String tagname) {
        this.tagname = tagname;
    }
    
    @Column(name="selected", nullable=false)

    public Integer getSelected() {
        return this.selected;
    }
    
    public void setSelected(Integer selected) {
        this.selected = selected;
    }
   
    @Column(name="imageaddress")

    public String getImageaddress() {
        return this.imageaddress;
    }
    
    public void setImageaddress(String imageaddress) {
        this.imageaddress = imageaddress;
    }








}