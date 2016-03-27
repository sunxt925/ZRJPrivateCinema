package cn.edu.cqu.csp.action.movies;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;

import net.sf.json.JSONObject;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class SearchMoviesAction extends ActionSupport{

	private String keyword;

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List list;
	
	private JSONObject resultObj;
	
	private String rows;//每页显示的记录数  

	private String page;//当前第几页

	public String getRows() {  
		return rows;  
	}  
	public void setRows(String rows) {  
		this.rows = rows;  
	}  
	public String getPage() {  
		return page;  
	}  
	public void setPage(String page) {  
		this.page = page;  
	}  

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
	
	
	public String SearchMovies(){
		
		 System.out.println("========");  
		
	        //当前页  
	        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
	        //每页显示条数  
	        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);  
	        //每页的开始记录  第一页为1  第二页为number +1   
	        int start = (intPage-1)*number; 
		MoviesDAO moviesDAO = new MoviesDAO();
		try {
			keyword = URLDecoder.decode(keyword, "UTF-8");
			//keyword = URLDecoder.decode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(keyword);
		List templist = (List) moviesDAO.findByKeyword(keyword);
		System.out.println("templist.size()======"+templist.size());  
		list=templist.subList(start, start+number);
		dataMap.clear();
		dataMap.put("rows", templist);
		//dataMap.put("total", templist.size());//total键 存放总记录数，必须的    
		

		dataMap.put("success", 1);
		return "success";
	}
	
}
