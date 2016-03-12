package cn.edu.cqu.csp.dao.messages;
// default package

import cn.edu.cqu.csp.dao.BaseHibernateDAO;
import cn.edu.cqu.csp.dao.tags.Tags;

import java.text.DecimalFormat;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Messages entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Messages
  * @author MyEclipse Persistence Tools 
 */
public class MessagesDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MessagesDAO.class);
		//property constants
	public static final String CONTENT = "content";
	public static final String SELECTED = "selected";


	private Transaction tx;
	private Session session;

    
    public void save(Messages transientInstance) {
        log.debug("saving Messages instance");
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
    
    public void update(Messages transientInstance) {
        log.debug("updating User instance");
        
        try {
        	transientInstance.setAuthor("zrj");
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
    
    
	public void delete(Messages persistentInstance) {
        log.debug("deleting Messages instance");
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
    
    public Messages findById( java.lang.Integer id) {
        log.debug("getting Messages instance with id: " + id);
        try {
            Messages instance = (Messages) getSession()
                    .get("Messages", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Messages> findByExample(Messages instance) {
        log.debug("finding Messages instance by example");
        try {
            List<Messages> results = (List<Messages>) getSession()
                    .createCriteria("Messages")
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
      log.debug("finding Messages instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Messages as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Messages> findByContent(Object content
	) {
		return findByProperty(CONTENT, content
		);
	}
	
	public List<Messages> findBySelected(Object selected
	) {
		return findByProperty(SELECTED, selected
		);
	}
	

	public List findAll() {
		log.debug("finding all Messages instances");
		try {
			Session session = getSession();
			String queryString = "from Messages m order by m.id desc";
			Transaction tx = session.beginTransaction();
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
            tx.commit();
            session.close();	 
			 return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Messages findShow() {
		log.debug("finding all Messages instances");
		try {
			Session session = getSession();
			String queryString = "from Messages m where m.selected > 0 order by m.id desc";
			Transaction tx = session.beginTransaction();
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
            tx.commit();
            session.close();	 
			 return (Messages) list.get(0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Messages merge(Messages detachedInstance) {
        log.debug("merging Messages instance");
        try {
            Messages result = (Messages) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Messages instance) {
        log.debug("attaching dirty Messages instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Messages instance) {
        log.debug("attaching clean Messages instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public void updateSender(int sendValue) {
		 try {
	        	session = getSession();
	        	tx = session.beginTransaction();
	        	String sql = "UPDATE CONFS SET ipaddress='"+sendValue+"' where id=2";               
	            session.createSQLQuery(sql).executeUpdate();  
	            tx.commit();
	            session.close();
	            log.debug("delete successful");
	        } catch (RuntimeException re) {
	            log.error("delete failed", re);
	            throw re;
	        }
		
	}

	public String showSender() {
		try {
        	session = getSession();
        	tx = session.beginTransaction();
        	String sql = "SELECT ipaddress from CONFS where id=2";               
            String rs=(String)session.createSQLQuery(sql).list().get(0);  
            tx.commit();
            session.close();
            log.debug("delete successful");
            return rs;
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }	
	}
	//返回当日播放序号（6+4），让序号加一
	public String getPlaySerial(String today) {
		String result="";
		try {
        	session = getSession();
        	tx = session.beginTransaction();
        	String sql = "SELECT ipaddress from CONFS where id=3";               
            String rs=(String)session.createSQLQuery(sql).list().get(0); 
            if(rs.startsWith(today)){  //还是当日影片 后四位序号加一
            	String serial=rs.substring(6);
            	DecimalFormat df = new DecimalFormat("0000"); 
    			serial = df.format(Integer.parseInt(serial)+1);
    			result=today+serial;
    			String sql2 = "UPDATE CONFS SET ipaddress='"+result+"' where id=3";               
	            session.createSQLQuery(sql2).executeUpdate();
            }else{// 没有当日影片数据，这个为第一次播放，后四位置为0001
            	String sql2 = "UPDATE CONFS SET ipaddress='"+today+"0001' where id=3";               
	            session.createSQLQuery(sql2).executeUpdate();  
	            result=today+"0001";
            }
            tx.commit();
            session.close();
            return result;
        } catch (RuntimeException re) {
            throw re;
        }	
	}
}