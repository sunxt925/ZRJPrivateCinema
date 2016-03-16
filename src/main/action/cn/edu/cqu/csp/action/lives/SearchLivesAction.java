package cn.edu.cqu.csp.action.lives;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cqu.csp.dao.lives.LivesDAO;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import net.sf.json.JSONObject;

public class SearchLivesAction {

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
	
	public String SearchLives(){
		LivesDAO livesDAO = new LivesDAO();
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
			//keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(keyword);
		list = (List) livesDAO.findByLivenameFuzzy(keyword);
		dataMap.clear();
		dataMap.put("success", 1);
		dataMap.put("rows", list);
		return "success";
	}
	
}
