package cn.edu.cqu.csp.action.lives;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cqu.csp.dao.lives.Lives;
import cn.edu.cqu.csp.dao.lives.LivesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class AddLivesAction extends ActionSupport{

	private String lives_livename;
	private String lives_liveaddress;
	private String lives_imageaddress;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	public String getLives_imageaddress() {
		return lives_imageaddress;
	}
	public void setLives_imageaddress(String lives_imageaddress) {
		this.lives_imageaddress = lives_imageaddress;
	}
	public String getLives_livename() {
		return lives_livename;
	}
	public void setLives_livename(String lives_livename) {
		this.lives_livename = lives_livename;
	}
	public String getLives_liveaddress() {
		return lives_liveaddress;
	}
	public void setLives_liveaddress(String lives_liveaddress) {
		this.lives_liveaddress = lives_liveaddress;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String AddLives()
	{
		LivesDAO livesDAO = new LivesDAO();
		Lives lives = new Lives();
		//System.out.println(tags_selected);
		//user.setId(userDAO.getMaxID()+1);
		lives.setLivename(lives_livename);
		lives.setLiveaddress(lives_liveaddress);
		lives.setImageaddress(lives_imageaddress);
		livesDAO.save(lives);
		dataMap.put("success", 1);
		return "success";
	}
	
}
