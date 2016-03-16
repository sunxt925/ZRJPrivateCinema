package cn.edu.cqu.csp.dao.lives;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Lives entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="lives"
    ,catalog="cqzrj"
)
public class Lives extends AbstractLives implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Lives() {
    }

    
    /** full constructor */
    public Lives(String livename, String liveaddress, String imageaddress) {
        super(livename, liveaddress,imageaddress);        
    }
   
}
