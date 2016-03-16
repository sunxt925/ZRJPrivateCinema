package cn.edu.cqu.csp.dao.messages;
// default package

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;


/**
 * AbstractMessages entity provides the base persistence definition of the Messages entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractMessages  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String content;
     private Boolean selected;
     private String author;


    // Constructors

    /** default constructor */
    public AbstractMessages() {
    }

	/** minimal constructor */
    public AbstractMessages(Boolean selected) {
        this.selected = selected;
    }
    
    /** full constructor */
    public AbstractMessages(String content, Boolean selected) {
        this.content = content;
        this.selected = selected;
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
    
    @Column(name="content", length=65535)

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    @Column(name="selected", nullable=false)

    public Boolean getSelected() {
        return this.selected;
    }
    
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Column(name="author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
   
    








}