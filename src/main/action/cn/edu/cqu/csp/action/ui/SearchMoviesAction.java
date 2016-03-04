package cn.edu.cqu.csp.action.ui;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cqu.csp.src.MovieUI;

public class SearchMoviesAction {

	private String keyword;
	private String secondkey;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	
	
	public String getSecondkey() {
		return secondkey;
	}

	public void setSecondkey(String secondkey) {
		this.secondkey = secondkey;
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
	
	public String SearchMovies()
	{
		
		MovieUI movieUI = new MovieUI();
		List movies = new ArrayList();
		/*
		String x="";
		try {
			x = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("keyword:"+x);
		*/
		movies = movieUI.getSearchMovies("1","9",keyword);
		dataMap.clear();
		
		dataMap.put("flag", "movies");
		dataMap.put("movies", movies);
		dataMap.put("nextpage", movieUI.getNextPage());
		dataMap.put("success", 1);
		return "success";
	}
}
