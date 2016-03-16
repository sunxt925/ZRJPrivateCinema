package cn.edu.cqu.csp.action.movies;

import cn.edu.cqu.csp.src.ImportMovies;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class ImportMoviesAction extends ActionSupport{

	public String sitename;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String ImportMovies()
	{
		ImportMovies importMovies = new ImportMovies(sitename);
		importMovies.importMovies();
		dataMap.put("success", 1);
		return "success";
	}
}
