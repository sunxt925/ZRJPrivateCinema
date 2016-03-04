package cn.edu.cqu.csp.action.movies;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.ha.backend.Sender;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import cn.edu.cqu.csp.dao.messages.MessagesDAO;
import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class AddCountAction {
	private String flag;
	private String videoaddress;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	public String getVideoaddress() {
		return videoaddress;
	}

	public void setVideoaddress(String videoaddress) {
		this.videoaddress = videoaddress;
	}

	public String AddCount()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String clientIP=request.getRemoteAddr();
		System.out.println("客户端ip为:"+clientIP);
		
		
		if(flag == "trailers")
		{
			TrailersDAO moviesDAO = new TrailersDAO();
			Trailers movies = new Trailers();
			movies = (Trailers)((moviesDAO.findByFilepath(videoaddress)).get(0));
			movies.setCount(movies.getCount()+1);
			moviesDAO.update(movies);
		}
		else
		{
			MoviesDAO moviesDAO = new MoviesDAO();
			MessagesDAO messagesDAO = new MessagesDAO();
			Movies movies = new Movies();
			movies = (Movies)((moviesDAO.findByFilepath(videoaddress)).get(0));
			String movieTime=movies.getDuration().split("/")[0];
			movieTime=movieTime.split("分")[0];
			System.out.println("本电影时长:"+movieTime);
			if(request.getSession().getAttribute(clientIP)==null){
				System.out.println("没有上次数据");
				request.getSession().setAttribute(clientIP, org.cfca_c.yb.util.Sender.getTimeText());
			}else {
				System.out.println("有上次数据");
				try {
					String nowTime=org.cfca_c.yb.util.Sender.getTimeText();
					long timeSpan=org.cfca_c.yb.util.Sender.getTimeSpan(nowTime,(String)request.getSession().getAttribute(clientIP));
					System.out.println("timeSpan  "+timeSpan);
					if(timeSpan<0.1){
						System.out.println("小于20分钟");
						request.getSession().setAttribute(clientIP, nowTime);
					}else if(timeSpan<Long.parseLong(movieTime)){  //还应去除分钟
						movies.setCount(movies.getCount()+1);
						if((movies.getCount()+1)%Integer.parseInt(messagesDAO.showSender())==0){ //此时应上报数据
							 String movieStartTime=(String)request.getSession().getAttribute(clientIP);
							 String movieEndTime=nowTime;
							 System.out.println("startTime:"+movieStartTime+"  endTime:"+movieEndTime);
							//上报
							 String MovieInfoXmlWith3DES=org.cfca_c.yb.util.Sender.makeXML(nowTime,movies.getMoviename(),movieStartTime,movieEndTime);
							 System.out.println(org.cfca_c.yb.util.Sender.sendPlayInfo(MovieInfoXmlWith3DES));
						}
						request.getSession().setAttribute(clientIP, nowTime);
					}else {
						movies.setCount(movies.getCount()+1);
						if((movies.getCount()+1)%Integer.parseInt(messagesDAO.showSender())==0){ //此时应上报数据
							 String movieStartTime=(String)request.getSession().getAttribute(clientIP);
							 String movieEndTime=org.cfca_c.yb.util.Sender.getFullEndTime(movieStartTime,movieTime);
							 System.out.println("startTime:"+movieStartTime+"  endTime:"+movieEndTime);
							 //上报
							 String MovieInfoXmlWith3DES=org.cfca_c.yb.util.Sender.makeXML(nowTime,movies.getMoviename(),movieStartTime,movieEndTime);
							 System.out.println(org.cfca_c.yb.util.Sender.sendPlayInfo(MovieInfoXmlWith3DES));
						}
						request.getSession().setAttribute(clientIP, nowTime);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			moviesDAO.update(movies);
		}	
		System.out.println(flag);
		dataMap.put("success", 1);
		return "success";
	}
}
