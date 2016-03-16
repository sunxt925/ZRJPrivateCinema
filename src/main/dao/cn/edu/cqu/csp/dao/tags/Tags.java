package cn.edu.cqu.csp.dao.tags;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Tags entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tags"
    ,catalog="cqzrj"
)
public class Tags extends AbstractTags implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Tags() {
    }

    
    /** full constructor */
    public Tags(String tagname, Integer selected, String imageaddress) {
        super(tagname, selected, imageaddress);        
    }
   
}
