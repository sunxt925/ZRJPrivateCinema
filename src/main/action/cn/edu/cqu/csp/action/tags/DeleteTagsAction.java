package cn.edu.cqu.csp.action.tags;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.User;
import cn.edu.cqu.csp.dao.UserDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteTagsAction extends ActionSupport{


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
	
	public String DeleteTags(){
		TagsDAO tagsDAO = new TagsDAO();
		Tags tags = new Tags();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			tags.setId(Integer.parseInt(st.nextToken()));
			tagsDAO.delete(tags);
        }
		dataMap.put("success", 1);
		return "success";
	}
}
