package cn.edu.cqu.csp.dao.sites;
// default package

import cn.edu.cqu.csp.dao.BaseHibernateDAO;
import cn.edu.cqu.csp.dao.tags.Tags;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Sites entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Sites
  * @author MyEclipse Persistence Tools 
 */
public class SitesDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SitesDAO.class);
		//property constants
	public static final String SITENAME = "sitename";
	public static final String SITEPATH = "sitepath";


	private Transaction tx;
	private Session session;

	private List list;
	private Query queryObject;

    
    public void save(Sites transientInstance) {
        log.debug("saving Sites instance");
        try {
        	session = getSession();
        	tx = session.beginTransaction();
            session.save(transientInstance);
            tx.commit();
            session.close();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(Sites transientInstance) {
        log.debug("updating Tags instance");
        
        try {
        	session = getSession();
        	tx = session.beginTransaction();
            session.update(transientInstance);
            tx.commit();
            session.close();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
	public void delete(Sites persistentInstance) {
        log.debug("deleting Sites instance");
        try {
        	session = getSession();
        	tx = session.beginTransaction();
            session.delete(persistentInstance);
            tx.commit();
            session.close();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Sites findById( java.lang.Integer id) {
        log.debug("getting Sites instance with id: " + id);
        try {
        	String queryString = "select model from Sites as model where model.id= ?";
      	  	session = getSession();
      	  	Query queryObject = session.createQuery(queryString);
      	  	queryObject.setString(0, id.toString());
      	  	list = queryObject.list();
      	  	session.close();
            return (Sites) list.get(0);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Sites> findByExample(Sites instance) {
        log.debug("finding Sites instance by example");
        try {
            List<Sites> results = (List<Sites>) getSession()
                    .createCriteria("Sites")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Sites instance with property: " + propertyName
            + ", value: " + value);
      try {
    	  String queryString = "from Sites as model where model." 
					+ propertyName + "= ?";
    	  session = getSession();
    	  Query queryObject = session.createQuery(queryString);
    	  queryObject.setParameter(0, value);
    	  list = queryObject.list();
    	  session.close();
    	  return list;
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Sites> findBySitename(Object sitename
	) {
		return findByProperty(SITENAME, sitename
		);
	}
	
	public List<Sites> findBySitepath(Object sitepath
	) {
		return findByProperty(SITEPATH, sitepath
		);
	}
	

	
	public List<Sites> findBySitenameFuzzy(String keyword)
	{
		session = getSession();
		tx=session.beginTransaction();  
		String hql="from Sites t where t.sitename like ? order by t.id desc";
		queryObject = session.createQuery(hql);  
		queryObject.setString(0,"%"+keyword+"%");
		list=queryObject.list();  
		tx.commit(); 
		session.close();
		return list;
	}
	
	public List findAll() {
		log.debug("finding all Sites instances");
		try {
			Session session = getSession();
			String queryString = "from Sites u order by u.id desc";
			Transaction tx = session.beginTransaction();
			queryObject = getSession().createQuery(queryString);
			list = queryObject.list();
            tx.commit();
            session.close();	 
            return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Sites merge(Sites detachedInstance) {
        log.debug("merging Sites instance");
        try {
            Sites result = (Sites) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Sites instance) {
        log.debug("attaching dirty Sites instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Sites instance) {
        log.debug("attaching clean Sites instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}