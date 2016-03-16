package cn.edu.cqu.csp.action.trailers;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class DeleteTrailersAction {

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
	
	public String DeleteTrailers(){
		TrailersDAO trailersDAO = new TrailersDAO();
		Trailers trailers = new Trailers();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			trailers.setId(Integer.parseInt(st.nextToken()));
			trailersDAO.delete(trailers);
        }
		dataMap.put("success", 1);
		return "success";
	}
}
