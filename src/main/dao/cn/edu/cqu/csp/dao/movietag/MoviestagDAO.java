package cn.edu.cqu.csp.dao.movietag;
// default package

import cn.edu.cqu.csp.dao.BaseHibernateDAO;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Moviestag entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Moviestag
  * @author MyEclipse Persistence Tools 
 */
public class MoviestagDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MoviestagDAO.class);
		//property constants
	public static final String MOVIESID = "moviesid";
	public static final String TAGID = "tagid";



	private Transaction tx;
	private Session session;

	private List list;
	private Query queryObject;
    
    public void save(Moviestag transientInstance) {
        log.debug("saving Moviestag instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Moviestag persistentInstance) {
        log.debug("deleting Moviestag instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Moviestag findById( java.lang.Integer id) {
        log.debug("getting Moviestag instance with id: " + id);
        try {
            Moviestag instance = (Moviestag) getSession()
                    .get("Moviestag", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Moviestag> findByExample(Moviestag instance) {
        log.debug("finding Moviestag instance by example");
        try {
            List<Moviestag> results = (List<Moviestag>) getSession()
                    .createCriteria("Moviestag")
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
      log.debug("finding Moviestag instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Moviestag as model where model." 
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

	public List<Moviestag> findByMoviesid(Object moviesid
	) {
		return findByProperty(MOVIESID, moviesid
		);
	}
	
	public List<Integer> findTagsidByMoviesid (Integer moviesid)
	{
		String queryString = "select model.tagid from Moviestag as model where model.moviesid = ?";
		session = getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setString(0, moviesid.toString());
		list = queryObject.list();
		session.close();
		return list;
	}
	
	public List<Moviestag> findByTagid(Object tagid
	) {
		return findByProperty(TAGID, tagid
		);
	}
	

	public List findAll() {
		log.debug("finding all Moviestag instances");
		try {
			Session session = getSession();
			String queryString = "from Moviestag";
			Transaction tx = session.beginTransaction();
			Query queryObject = getSession().createQuery(queryString);
			list = queryObject.list();
            tx.commit();
            session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Moviestag merge(Moviestag detachedInstance) {
        log.debug("merging Moviestag instance");
        try {
            Moviestag result = (Moviestag) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Moviestag instance) {
        log.debug("attaching dirty Moviestag instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Moviestag instance) {
        log.debug("attaching clean Moviestag instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}