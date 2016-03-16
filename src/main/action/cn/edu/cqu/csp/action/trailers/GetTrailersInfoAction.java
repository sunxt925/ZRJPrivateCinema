package cn.edu.cqu.csp.action.trailers;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;
import cn.edu.cqu.csp.src.MovieInfo;

public class GetTrailersInfoAction {

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
	
	
	public String GetTrailersInfo(){
		TrailersDAO trailersDAO = new TrailersDAO();
		Trailers trailers = new Trailers();
		MovieInfo mi = new MovieInfo();
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			if(!mi.getTrailers(st.nextToken()))
				continue;
			//mi.getMovies(st.nextToken());
			if(!mi.downloadPoster1())
				continue;
			
			if(!mi.getTrailerInfo())
				continue;
        }
		dataMap.put("success", 1);
		return "success";
	}
	
}
