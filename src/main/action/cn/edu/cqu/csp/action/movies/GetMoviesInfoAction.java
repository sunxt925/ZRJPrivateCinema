package cn.edu.cqu.csp.action.movies;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.src.MovieInfo;

import com.opensymphony.xwork2.ActionSupport;

public class GetMoviesInfoAction extends ActionSupport{

	private String idArray;
	
	private String doubanidArray;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	
	public String getDoubanidArray() {
		return doubanidArray;
	}

	public void setDoubanidArray(String doubanidArray) {
		this.doubanidArray = doubanidArray;
	}

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
	
	
	public String GetMoviesInfo(){
		MoviesDAO moviesDAO = new MoviesDAO();
		Movies movies = new Movies();
		MovieInfo mi = new MovieInfo();
		StringTokenizer st = new StringTokenizer(idArray);
		String error = "";
		while (st.hasMoreTokens()) {
			if(!mi.getMovies(st.nextToken()))
			{
				error="error get movies";
				continue;
			}
				
			//mi.getMovies(st.nextToken());
		/*	if(!mi.downloadPoster())
			{
				error="error downloadposter";
				continue;
			}*/
				
			
			if(!mi.getMovieInfo())
			{
				error="error getMovieInfo";
				continue;
			}
        }
		dataMap.put("error", error);
		dataMap.put("success", 1);
		return "success";
	}
	
}
