package cn.edu.cqu.csp.dao.messages;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Messages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="messages"
    ,catalog="cqzrj"
)
public class Messages extends AbstractMessages implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Messages() {
    }

	/** minimal constructor */
    public Messages(Boolean selected) {
        super(selected);        
    }
    
    /** full constructor */
    public Messages(String content, Boolean selected) {
        super(content, selected);        
    }
   
}
