package cn.edu.cqu.csp.action.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.movietag.MoviestagDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.MoviesRecord;

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
		//MoviesRecord moviesRecord = new MoviesRecord();
		//list = moviesRecord.findAll();
		MoviesDAO moviesDAO = new MoviesDAO();
		list = moviesDAO.findAll();
		dataMap.clear();
		dataMap.put("rows", list);
        return SUCCESS; 
	}
	

}
