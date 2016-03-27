package cn.edu.cqu.csp.action.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.movietag.MoviestagDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.MoviesRecord;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoadMoviesAction extends ActionSupport{
	
	private boolean checkbox;
	private int id;
	private String movietag;
	private String moviename;
	private String filepath;
	private String photopath;
	private String description;
	private String duration;
	private int doubanid;
	private String country;
	private int year;
	private String director;
	private String actor;
	private int count;
	private int doubanscore;
	private int newestscore;
	private int hottestscore;
	private int classicscore;
	
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
	public int getDoubanscore() {
		return doubanscore;
	}
	public void setDoubanscore(int doubanscore) {
		this.doubanscore = doubanscore;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDoubanid() {
		return doubanid;
	}
	public void setDoubanid(int doubanid) {
		this.doubanid = doubanid;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNewestscore() {
		return newestscore;
	}
	public void setNewestscore(int newestscore) {
		this.newestscore = newestscore;
	}
	public int getHottestscore() {
		return hottestscore;
	}
	public void setHottestscore(int hottestscore) {
		this.hottestscore = hottestscore;
	}
	public int getClassicscore() {
		return classicscore;
	}
	public void setClassicscore(int classicscore) {
		this.classicscore = classicscore;
	}
	public String getMovietag() {
		return movietag;
	}
	public void setMovietag(String moivetag) {
		this.movietag = moivetag;
	}
	
	public String LoadMovies()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		 System.out.println("---------------");  
	        //当前页  
	        int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);  
	        //每页显示条数  
	        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);  
	        //每页的开始记录  第一页为1  第二页为number +1   
	        int start = (intPage-1)*number; 
		//MoviesRecord moviesRecord = new MoviesRecord();
		//list = moviesRecord.findAll();
		MoviesDAO moviesDAO = new MoviesDAO();
		List templist = moviesDAO.findAll();
		
		list=templist.subList(start, start+number);
		//list = moviesDAO.findByPage(start, number);
		
		dataMap.clear();
		dataMap.put("total", templist.size());//total键 存放总记录数，必须的    
		dataMap.put("rows", list);
		request.setAttribute("Search", 0);
		//resultObj = JSONObject.fromObject(dataMap);//格式化result   一定要是JSONObject  
        return SUCCESS; 
	}
	

}
