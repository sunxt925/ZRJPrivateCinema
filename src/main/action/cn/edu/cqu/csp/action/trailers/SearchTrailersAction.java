package cn.edu.cqu.csp.action.trailers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

import net.sf.json.JSONObject;

public class SearchTrailersAction {

	private String keyword;

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List list;
	
	private JSONObject resultObj;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public JSONObject getResultObj() {
		return resultObj;
	}

	public void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}
	
	public String SearchTrailers(){
		TrailersDAO trailersDAO = new TrailersDAO();
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
			//keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(keyword);
		list = (List) trailersDAO.findByKeyword(keyword);
		dataMap.clear();
		dataMap.put("rows", list);
		dataMap.put("success", 1);
		return "success";
	}
}
