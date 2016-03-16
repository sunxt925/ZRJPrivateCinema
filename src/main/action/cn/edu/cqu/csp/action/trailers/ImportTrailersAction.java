package cn.edu.cqu.csp.action.trailers;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cqu.csp.src.ImportMovies;

public class ImportTrailersAction {

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
	
	public String ImportTrailers()
	{
		ImportMovies importMovies = new ImportMovies(sitename);
		importMovies.importTrailers();
		dataMap.put("success", 1);
		return "success";
	}
	
}
