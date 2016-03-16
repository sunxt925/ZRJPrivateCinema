package cn.edu.cqu.csp.action.messages;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cqu.csp.dao.messages.Messages;
import cn.edu.cqu.csp.dao.messages.MessagesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class AddMessagesAction extends ActionSupport{

	private String messages_content;
	private String messages_selected;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	public String getMessages_content() {
		return messages_content;
	}
	public void setMessages_content(String messages_content) {
		this.messages_content = messages_content;
	}
	public String getMessages_selected() {
		return messages_selected;
	}
	public void setMessages_selected(String messages_selected) {
		this.messages_selected = messages_selected;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String AddMessages()
	{
		MessagesDAO messagesDAO = new MessagesDAO();
		Messages messages = new Messages();
		//System.out.println(tags_selected);
		//user.setId(userDAO.getMaxID()+1);
		messages.setContent(messages_content);
		if(messages_selected.equals("1"))
		{
			messages.setSelected(true);
		}
		else
		{
			messages.setSelected(false);
		}
		messagesDAO.save(messages);
		dataMap.put("success", 1);
		return "success";
	}
	
}
