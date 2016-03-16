package cn.edu.cqu.csp.dao.lives;
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
 	* A data access object (DAO) providing persistence and search support for Lives entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Lives
  * @author MyEclipse Persistence Tools 
 */
public class LivesDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(LivesDAO.class);
		//property constants
	public static final String LIVENAME = "livename";
	public static final String LIVEADDRESS = "liveaddress";
	public static final String IMAGEADDRESS = "imageaddress";

	private Transaction tx;
	private Session session;

	private List list;
	private Query queryObject;

    
    public void save(Lives transientInstance) {
        log.debug("saving Lives instance");
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
    
    public void update(Lives transientInstance) {
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
    
	public void delete(Lives persistentInstance) {
        log.debug("deleting Lives instance");
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
    
    public Lives findById( java.lang.Integer id) {
        log.debug("getting Lives instance with id: " + id);
        try {
            Lives instance = (Lives) getSession()
                    .get("Lives", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Lives> findByExample(Lives instance) {
        log.debug("finding Lives instance by example");
        try {
            List<Lives> results = (List<Lives>) getSession()
                    .createCriteria("Lives")
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
      log.debug("finding Lives instance with property: " + propertyName
            + ", value: " + value);
      try {
    	  String queryString = "from Lives as model where model." 
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

	public List<Lives> findByLivename(Object livename
	) {
		return findByProperty(LIVENAME, livename
		);
	}
	
	public List<Lives> findByLiveaddress(Object liveaddress
	) {
		return findByProperty(LIVEADDRESS, liveaddress
		);
	}
	
	public List<Lives> findByImageaddress(Object imageaddress
	) {
		return findByProperty(IMAGEADDRESS, imageaddress
		);
	}
	

	public List<Lives> findByLivenameFuzzy(String keyword)
	{
		session = getSession();
		tx=session.beginTransaction();  
		String hql="from Lives t where t.livename like ? order by t.id desc";
		queryObject = session.createQuery(hql);  
		queryObject.setString(0,"%"+keyword+"%");
		list=queryObject.list();  
		tx.commit(); 
		session.close();
		return list;
	}
	
	public List findAll() {
		log.debug("finding all Lives instances");
		try {
			Session session = getSession();
			String queryString = "from Lives u order by u.id desc";
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
	
    public Lives merge(Lives detachedInstance) {
        log.debug("merging Lives instance");
        try {
            Lives result = (Lives) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Lives instance) {
        log.debug("attaching dirty Lives instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Lives instance) {
        log.debug("attaching clean Lives instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}