package cn.edu.cqu.csp.action.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.StringTokenizer;


import cn.edu.cqu.csp.src.MovieUI;

public class MoviePageAction {
	private String pagenum;
	private String keyword;
	private String pagename;
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

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getPagenum() {
		return pagenum;
	}

	public void setPagenum(String pagenum) {
		this.pagenum = pagenum;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String NextPage()
	{
		String nextPage="";
		MovieUI movieUI = new MovieUI();
		StringTokenizer st = new StringTokenizer(pagenum,"/");
		while (st.hasMoreTokens()) {
			nextPage = st.nextToken();
			break;
        }
		nextPage = String.valueOf(Integer.parseInt(nextPage)+1);
		List movies = new ArrayList();
		
		dataMap.clear();
		if (secondkey.equals("tagsearch")) {
			movies = movieUI.getSearchMovies(nextPage, "9", pagename);
			dataMap.put("flag", "movies");
			dataMap.put("movies", movies);
		} else {
			if (pagename.equals("新片速递")) {
				movies = movieUI.getMoviesList(nextPage, "9", 0);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);

			}

			if (pagename.equals("本周热门")) {
				movies = movieUI.getMoviesList(nextPage, "9", 1);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("本院推荐")) {
				movies = movieUI.getMoviesList(nextPage, "9", 2);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("精彩预告")) {
				movies = movieUI.getTrailersList(nextPage, "9");
				dataMap.put("flag", "trailers");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("L i v e")) {
				movies = movieUI.getLivesList(nextPage, "11");
				dataMap.put("flag", "lives");
				dataMap.put("lives", movies);
			}

			if (pagename.equals("全部标签")) {
				movies = movieUI.getTagsList(nextPage, "11");
				dataMap.put("flag", "tags");
				dataMap.put("tags", movies);
			}

			if (pagename.equals("全部电影")) {
				movies = movieUI.getMoviesList(nextPage, "9", 3);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("搜索")) {
				movies = movieUI.getSearchMovies(nextPage, "9", secondkey);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}
		}
		dataMap.put("nextpage", movieUI.getNextPage());
		
		dataMap.put("success", 1);
		
		return "success";
	}
	
	public String PreviousPage()
	{
		String previousPage="";
		MovieUI movieUI = new MovieUI();
		StringTokenizer st = new StringTokenizer(pagenum,"/");
		while (st.hasMoreTokens()) {
			previousPage = st.nextToken();
			break;
        }
		previousPage = String.valueOf(Integer.parseInt(previousPage)-1);
		List movies = new ArrayList();
		
		dataMap.clear();
		
		if (secondkey.equals("tagsearch")) {
			movies = movieUI.getSearchMovies(previousPage, "9", pagename);
			dataMap.put("flag", "movies");
			dataMap.put("movies", movies);
		} else {
			if (pagename.equals("新片速递")) {
				movies = movieUI.getMoviesList(previousPage, "9", 0);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);

			}

			if (pagename.equals("本周热门")) {
				movies = movieUI.getMoviesList(previousPage, "9", 1);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("本院推荐")) {
				movies = movieUI.getMoviesList(previousPage, "9", 2);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("精彩预告")) {
				movies = movieUI.getTrailersList(previousPage, "9");
				dataMap.put("flag", "trailers");
				dataMap.put("movies", movies);
			}

			if (pagename.equals("L i v e")) {
				movies = movieUI.getLivesList(previousPage, "11");
				dataMap.put("flag", "lives");
				dataMap.put("lives", movies);
			}

			if (pagename.equals("全部标签")) {
				movies = movieUI.getTagsList(previousPage, "9");
				dataMap.put("flag", "tags");
				dataMap.put("tags", movies);
			}

			if (pagename.equals("全部电影")) {
				movies = movieUI.getMoviesList(previousPage, "9", 3);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}
			if (pagename.equals("搜索")) {
				movies = movieUI.getSearchMovies(previousPage, "9", secondkey);
				dataMap.put("flag", "movies");
				dataMap.put("movies", movies);
			}
		}
		dataMap.put("nextpage", movieUI.getNextPage());
		
		dataMap.put("success", 1);
		
		return "success";
	}
}
