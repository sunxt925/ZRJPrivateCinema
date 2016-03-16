package cn.edu.cqu.csp.action.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cqu.csp.src.MovieUI;

public class PageAction {

	private String page;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String newPage()
	{
		
		MovieUI movieUI = new MovieUI();
		List movies = new ArrayList();
		
		//List tags = new ArrayList();
		System.out.println("pageTitle:"+page);
		
		dataMap.clear();
		
		if(page.equals("新片速递"))
		{
			movies = movieUI.getMoviesList("1","9",0);
			dataMap.put("flag", "movies");
			dataMap.put("movies", movies);
		}
		
		if(page.equals("本周热门"))
		{
			movies = movieUI.getMoviesList("1","9",1);
			dataMap.put("flag", "movies");
			dataMap.put("movies", movies);
		}
		
		if(page.equals("本院推荐"))
		{
			movies = movieUI.getMoviesList("1","9",2);
			dataMap.put("flag", "movies");
			dataMap.put("movies", movies);
		}
		
		if(page.equals("精彩预告"))
		{
			movies = movieUI.getTrailersList("1","9");
			dataMap.put("flag", "trailers");
			dataMap.put("movies", movies);
		}
		
		if(page.equals("L i v e"))
		{
			movies = movieUI.getLivesList("1","11");
			dataMap.put("flag", "lives");
			dataMap.put("lives", movies);
		}
		
		if(page.equals("全部标签"))
		{
			movies = movieUI.getTagsList("1","11");
			dataMap.put("flag", "tags");
			dataMap.put("tags", movies);
		}
		
		if(page.equals("全部电影"))
		{
			movies = movieUI.getMoviesList("1","9",3);
			dataMap.put("flag", "movies");
			dataMap.put("movies", movies);
		}
		
		//dataMap.put("pagename", pagename);
		
		dataMap.put("nextpage", movieUI.getNextPage());
		
		dataMap.put("success", 1);
		return "success";
	}
}
