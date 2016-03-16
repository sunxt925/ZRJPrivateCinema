package cn.edu.cqu.csp.action.movies;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteMoviesAction extends ActionSupport{

	private String idArray;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public String getIdArray() {
		return idArray;
	}

	public void setIdArray(String idArray) {
		this.idArray = idArray;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String DeleteMovies(){
		MoviesDAO moviesDAO = new MoviesDAO();
		Movies movies = new Movies();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			movies.setId(Integer.parseInt(st.nextToken()));
			moviesDAO.delete(movies);
        }
		dataMap.put("success", 1);
		return "success";
	}
	
	public String DeleteCount(){
		MoviesDAO moviesDAO = new MoviesDAO();
		Movies movies = new Movies();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			movies = moviesDAO.findById(Integer.parseInt(st.nextToken()));
			movies.setCount(0);
			moviesDAO.update(movies);
			//movies.setId(Integer.parseInt(st.nextToken()));
			//moviesDAO.delete(movies);
        }
		dataMap.put("success", 1);
		return "success";
	}
}
