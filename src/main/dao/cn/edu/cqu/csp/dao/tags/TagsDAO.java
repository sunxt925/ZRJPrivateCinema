package cn.edu.cqu.csp.dao.tags;
// default package

import cn.edu.cqu.csp.dao.BaseHibernateDAO;
import cn.edu.cqu.csp.dao.User;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Tags entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Tags
  * @author MyEclipse Persistence Tools 
 */
public class TagsDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TagsDAO.class);
		//property constants
	public static final String TAGNAME = "tagname";
	public static final String SELECTED = "selected";
	public static final String IMAGEADDRESS = "imageaddress";

	private Transaction tx;
	private Session session;

	private List list;
	private Query queryObject;
    
    public void save(Tags transientInstance) {
        log.debug("saving Tags instance");
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
    
    public void update(Tags transientInstance) {
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
    
	public void delete(Tags persistentInstance) {
        log.debug("deleting Tags instance");
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
    
    public Tags findById( java.lang.Integer id) {
        log.debug("getting Tags instance with id: " + id);
        try {
            Tags instance = (Tags) getSession()
                    .get("Tags", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findTagnameById( java.lang.Integer id) {
        log.debug("getting Tags instance with id: " + id);
        try {
        	String queryString = "select model from Tags as model where model.id= ?";
    	  session = getSession();
    	  Query queryObject = session.createQuery(queryString);
    	  queryObject.setString(0, id.toString());
    	  list = queryObject.list();
    	  session.close();
    	  return list;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List<Tags> findByExample(Tags instance) {
        log.debug("finding Tags instance by example");
        try {
            List<Tags> results = (List<Tags>) getSession()
                    .createCriteria("Tags")
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
      log.debug("finding Tags instance with property: " + propertyName
            + ", value: " + value);
      try {
    	  String queryString = "from Tags as model where model." 
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

	public List<Tags> findByTagname(Object tagname
	) {
		return findByProperty(TAGNAME, tagname
		);
	}
	
	public List<Tags> findBySelected(Object selected
	) {
		return findByProperty(SELECTED, selected
		);
	}
	
	public List<Tags> findByImageaddress(Object imageaddress
	) {
		return findByProperty(IMAGEADDRESS, imageaddress
		);
	}
	
	public List<Tags> findByTagnameFuzzy(String keyword)
	{
		session = getSession();
		tx=session.beginTransaction();  
		String hql="from Tags t where t.tagname like ? order by t.id desc";
		queryObject = session.createQuery(hql);  
		queryObject.setString(0,"%"+keyword+"%");
		list=queryObject.list();  
		tx.commit(); 
		session.close();
		return list;
	}

	public List findAll() {
		log.debug("finding all Tags instances");
		try {
			Session session = getSession();
			String queryString = "from Tags u order by u.selected desc";
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
	
    public Tags merge(Tags detachedInstance) {
        log.debug("merging Tags instance");
        try {
            Tags result = (Tags) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tags instance) {
        log.debug("attaching dirty Tags instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tags instance) {
        log.debug("attaching clean Tags instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}