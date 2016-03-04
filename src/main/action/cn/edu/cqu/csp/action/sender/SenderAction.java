package cn.edu.cqu.csp.action.sender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import net.sf.json.JSONObject;
import cn.edu.cqu.csp.dao.messages.MessagesDAO;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class SenderAction extends ActionSupport{

	private int sendValue;
	private String value;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List list;
	
	private JSONObject resultObj;
	
	private Transaction tx;
	private Session session;
	
	
	
	public int getSendValue() {
		return sendValue;
	}
	public void setSendValue(int sendValue) {
		this.sendValue = sendValue;
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
	public String show()
	{	
		HttpServletRequest request = ServletActionContext.getRequest();
		MessagesDAO messagesDAO = new MessagesDAO();
		value=messagesDAO.showSender();
		request.setAttribute("value", value);
		System.out.println(value);
		//dataMap.clear();
        return "show";   
	}
	
	public String edit()
	{	
		MessagesDAO messagesDAO = new MessagesDAO();
		messagesDAO.updateSender(sendValue);
		//dataMap.clear();
        return SUCCESS;   
	}
	
	
}
