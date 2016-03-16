package cn.edu.cqu.csp.dao;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="cqzrj"
)
public class User extends AbstractUser implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Integer id, String username) {
        super(id, username);        
    }
    
    /** full constructor */
    public User(Integer id, String username, String password) {
        super(id, username, password);        
    }
   
}
