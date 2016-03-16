package cn.edu.cqu.csp.action.messages;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.messages.Messages;
import cn.edu.cqu.csp.dao.messages.MessagesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteMessagesAction extends ActionSupport{


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
	
	public String DeleteMessages(){
		MessagesDAO messagesDAO = new MessagesDAO();
		Messages messages = new Messages();
		//System.out.println(idArray);
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			messages.setId(Integer.parseInt(st.nextToken()));
			messagesDAO.delete(messages);
        }
		dataMap.put("success", 1);
		return "success";
	}
	
}
