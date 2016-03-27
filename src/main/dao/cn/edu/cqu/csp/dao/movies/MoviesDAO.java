package cn.edu.cqu.csp.dao.movies;
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
 	* A data access object (DAO) providing persistence and search support for Movies entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Movies
  * @author MyEclipse Persistence Tools 
 */
public class MoviesDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MoviesDAO.class);
		//property constants
	public static final String MOVIENAME = "moviename";
	public static final String MOVIETAG = "movietag";
	public static final String FILEPATH = "filepath";
	public static final String PHOTOPATH = "photopath";
	public static final String DESCRIPTION = "description";
	public static final String DURATION = "duration";
	public static final String DOUBANID = "doubanid";
	public static final String DOUBANSCORE = "doubanscore";
	public static final String NEWESTSCORE = "newestscore";
	public static final String HOTTESTSCORE = "hottestscore";
	public static final String CLASSICSCORE = "classicscore";
	public static final String COUNTRY = "country";
	public static final String YEAR = "year";
	public static final String DIRECTOR = "director";
	public static final String ACTOR = "actor";
	public static final String COUNT = "count";


	private Transaction tx;
	private Session session;

	private List list;
	private Query queryObject;

    
    public void save(Movies transientInstance) {
        log.debug("saving Movies instance");
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
    
    public void update(Movies transientInstance) {
        log.debug("updating Movies instance");
        
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
    
	public void delete(Movies persistentInstance) {
        log.debug("deleting Movies instance");
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
    
    public Movies findById( java.lang.Integer id) {
        log.debug("getting Movies instance with id: " + id);
        try {
        	String queryString = "from Movies as model where model.id = ?";
        	session = getSession();
        	Query queryObject = session.createQuery(queryString);
        	queryObject.setParameter(0, id);
        	list = queryObject.list();
        	session.close();
            return (Movies)list.get(0);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
   
    
    public List<Movies> findByExample(Movies instance) {
        log.debug("finding Movies instance by example");
        try {
            List<Movies> results = (List<Movies>) getSession()
                    .createCriteria("Movies")
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
      log.debug("finding Movies instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Movies as model where model." 
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

	public List<Movies> findByMoviename(Object moviename
	) {
		return findByProperty(MOVIENAME, moviename
		);
	}
	
	public List<Movies> findByKeyword(String keyword) {
		session = getSession();
		tx=session.beginTransaction();  
		String hql="from Movies m where m.moviename like ? or m.country like ? or m.director like ? or m.actor like ? or m.movietag like ? or m.othername like ? or m.englishname like ? order by m.id desc";
		queryObject = session.createQuery(hql);
		
		for(int i = 0; i < 7; i++)
		{
			queryObject.setString(i,"%"+keyword+"%");
		}
		list=queryObject.list();  
		tx.commit(); 
		session.close();
		return list;

	}
	
	public List<Movies> findByMovietag(Object movietag
	) {
		return findByProperty(MOVIETAG, movietag
		);
	}
	
	public List<Movies> findByFilepath(Object filepath
	) {
		return findByProperty(FILEPATH, filepath
		);
	}
	
	public List<Movies> findByPhotopath(Object photopath
	) {
		return findByProperty(PHOTOPATH, photopath
		);
	}
	
	public List<Movies> findByDescription(Object description
	) {
		return findByProperty(DESCRIPTION, description
		);
	}
	
	public List<Movies> findByDuration(Object duration
	) {
		return findByProperty(DURATION, duration
		);
	}
	
	public List<Movies> findByDoubanid(Object doubanid
	) {
		return findByProperty(DOUBANID, doubanid
		);
	}
	
	public List<Movies> findByDoubanscore(Object doubanscore
	) {
		return findByProperty(DOUBANSCORE, doubanscore
		);
	}
	
	public List<Movies> findByNewestscore(Object newestscore
	) {
		return findByProperty(NEWESTSCORE, newestscore
		);
	}
	
	public List<Movies> findByHottestscore(Object hottestscore
	) {
		return findByProperty(HOTTESTSCORE, hottestscore
		);
	}
	
	public List<Movies> findByClassicscore(Object classicscore
	) {
		return findByProperty(CLASSICSCORE, classicscore
		);
	}
	
	public List<Movies> findByCountry(Object country
	) {
		return findByProperty(COUNTRY, country
		);
	}
	
	public List<Movies> findByYear(Object year
	) {
		return findByProperty(YEAR, year
		);
	}
	
	public List<Movies> findByDirector(Object director
	) {
		return findByProperty(DIRECTOR, director
		);
	}
	
	public List<Movies> findByActor(Object actor
	) {
		return findByProperty(ACTOR, actor
		);
	}
	
	public List<Movies> findByCount(Object count
	) {
		return findByProperty(COUNT, count
		);
	}
	

	public List findAll() {
		log.debug("finding all Movies instances");
		try {
			Session session = getSession();
			String queryString = "from Movies m order by m.id desc";
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
	
	public List findNewest() {
		log.debug("finding all Movies instances");
		try {
			Session session = getSession();
			String queryString = "from Movies m where m.newestscore>0 order by m.newestscore desc";
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
	
	public List findHottest() {
		log.debug("finding all Movies instances");
		try {
			Session session = getSession();
			String queryString = "from Movies m where m.hottestscore>0 order by m.hottestscore desc";
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
	
	public List findCount() {
		log.debug("finding all Movies instances");
		try {
			Session session = getSession();
			String queryString = "from Movies m where m.count>0 order by m.count desc";
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
	
	public List findClassic() {
		log.debug("finding all Movies instances");
		try {
			Session session = getSession();
			String queryString = "from Movies m where m.classicscore>0 order by m.classicscore desc";
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
	
    public Movies merge(Movies detachedInstance) {
        log.debug("merging Movies instance");
        try {
            Movies result = (Movies) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Movies instance) {
        log.debug("attaching dirty Movies instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Movies instance) {
        log.debug("attaching clean Movies instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}