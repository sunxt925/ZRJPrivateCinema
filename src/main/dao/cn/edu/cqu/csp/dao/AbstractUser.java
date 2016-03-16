package cn.edu.cqu.csp.dao;
// default package

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * AbstractUser entity provides the base persistence definition of the User entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass

public abstract class AbstractUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String username;
     private String password;


    // Constructors

    /** default constructor */
    public AbstractUser() {
    }

	/** minimal constructor */
    public AbstractUser(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
    
    /** full constructor */
    public AbstractUser(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="username", nullable=false)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="password")

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
   








}