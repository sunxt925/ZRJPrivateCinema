package cn.edu.cqu.csp.action.lives;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.edu.cqu.csp.dao.lives.LivesDAO;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoadLivesAction extends ActionSupport{

	private boolean checkbox;
	private int id;
	private String livename;
	private String liveaddress;
	private String imageaddress;
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List list;
	
	private JSONObject resultObj;

	
	public String getImageaddress() {
		return imageaddress;
	}

	public void setImageaddress(String imageaddress) {
		this.imageaddress = imageaddress;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLivename() {
		return livename;
	}

	public void setLivename(String livename) {
		this.livename = livename;
	}

	public String getLiveaddress() {
		return liveaddress;
	}

	public void setLiveaddress(String liveaddress) {
		this.liveaddress = liveaddress;
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
	
	public String LoadLives()
	{
		
		//TagsRecord record = new TagsRecord();
		LivesDAO livesDAO = new LivesDAO();
		//List temp = new ArrayList();
		//list = new ArrayList();
		list = (List) livesDAO.findAll();
		/*for(int i = 0; i<temp.size(); i++)
		{
			record.setId(((Tags)temp.get(i)).getId());
			record.setTagname(((Tags)temp.get(i)).getTagname());
			list.add(record);
			
		}*/
		dataMap.clear();
		dataMap.put("rows", list);
        return SUCCESS;   
	}
	
	
}
