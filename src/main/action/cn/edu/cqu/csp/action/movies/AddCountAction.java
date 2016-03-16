package cn.edu.cqu.csp.action.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
			Movies movies = new Movies();
			movies = (Movies)((moviesDAO.findByFilepath(videoaddress)).get(0));
			movies.setCount(movies.getCount()+1);
			moviesDAO.update(movies);
		}	
		System.out.println(flag);
		dataMap.put("success", 1);
		return "success";
	}
}
