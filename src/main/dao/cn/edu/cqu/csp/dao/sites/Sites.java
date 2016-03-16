package cn.edu.cqu.csp.dao.sites;
// default package

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Sites entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sites"
    ,catalog="cqzrj"
)
public class Sites extends AbstractSites implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Sites() {
    }

    
    /** full constructor */
    public Sites(String sitename, String sitepath) {
        super(sitename, sitepath);        
    }
   
}
