package cn.edu.cqu.csp.action.lives;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.cqu.csp.dao.lives.Lives;
import cn.edu.cqu.csp.dao.lives.LivesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

public class DeleteLivesAction extends ActionSupport{

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
	
	public String DeleteLives(){
		LivesDAO livesDAO = new LivesDAO();
		Lives lives = new Lives();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			lives.setId(Integer.parseInt(st.nextToken()));
			livesDAO.delete(lives);
        }
		dataMap.put("success", 1);
		return "success";
	}
	
}
