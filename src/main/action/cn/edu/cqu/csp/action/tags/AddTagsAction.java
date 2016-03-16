package cn.edu.cqu.csp.action.tags;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cqu.csp.dao.User;
import cn.edu.cqu.csp.dao.UserDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class AddTagsAction extends ActionSupport{

	private String tags_tagname;
	private String tags_selected;
	private String tags_imageaddress;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	public String getTags_tagname() {
		return tags_tagname;
	}
	public void setTags_tagname(String tags_tagname) {
		this.tags_tagname = tags_tagname;
	}
	
	public String getTags_selected() {
		return tags_selected;
	}
	public void setTags_selected(String tags_selected) {
		this.tags_selected = tags_selected;
	}
	
	public String getTags_imageaddress() {
		return tags_imageaddress;
	}
	public void setTags_imageaddress(String tags_imageaddress) {
		this.tags_imageaddress = tags_imageaddress;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String AddTags()
	{
		TagsDAO tagsDAO = new TagsDAO();
		Tags tags = new Tags();
		//System.out.println(tags_selected);
		//user.setId(userDAO.getMaxID()+1);
		tags.setTagname(tags_tagname);
		tags.setImageaddress(tags_imageaddress);
		if(tags_selected.equals("")||tags_selected==null)
		{
			tags.setSelected(0);
		}
		else
		{
			tags.setSelected(Integer.parseInt(tags_selected));
		}
		tagsDAO.save(tags);
		dataMap.put("success", 1);
		return "success";
	}
	
}
