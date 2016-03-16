package cn.edu.cqu.csp.action.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.edu.cqu.csp.dao.UserDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.TagsRecord;

import com.opensymphony.xwork2.ActionSupport;

public class LoadTagsAction extends ActionSupport{

	private boolean checkbox;
	private int id;
	private String tagname;
	private Integer selected;
	private String imageaddress;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List list;
	
	private JSONObject resultObj;

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	
	public String getImageaddress() {
		return imageaddress;
	}

	public void setImageaddress(String imageaddress) {
		this.imageaddress = imageaddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
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
	
	public String LoadTags()
	{
		
		//TagsRecord record = new TagsRecord();
		TagsDAO tagsDAO = new TagsDAO();
		//List temp = new ArrayList();
		//list = new ArrayList();
		list = (List) tagsDAO.findAll();
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
	
	public String LoadMoviesTags()
	{
		class  temp
		{
			public String id;
			public String tagname;
			
			public temp()
			{
				this.id = "1";
				this.tagname = "Hello World";
			}
		}
		temp temp1 = new temp();
		temp1.id = "1";
		temp1.tagname = "Hello World";
		list = new ArrayList();
		list.add(temp1);
		System.out.println(((temp)(list.get(0))).id);
		System.out.println(((temp)(list.get(0))).tagname);
		//list = (List) tagsDAO.findAll();
		dataMap.clear();
		dataMap.put("rows", list);
        return SUCCESS;   
	}
}
