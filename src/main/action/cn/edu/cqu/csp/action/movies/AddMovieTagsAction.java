package cn.edu.cqu.csp.action.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class AddMovieTagsAction extends ActionSupport{

	private String tagname;
	private String id;

	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	
	public String AddMovieTags()
	{
		List getTags = new ArrayList();
		System.out.println(tagname);
		TagsDAO tagsDAO = new TagsDAO();
		Tags tags = new Tags();
		MoviesDAO moviesDAO = new MoviesDAO();
		Movies movies = new Movies();
		
		getTags = tagsDAO.findByTagname(tagname);
		if(getTags.size()==0)
		{
			System.out.println(tagname);
			tags.setTagname(tagname);
			tags.setSelected(0);
			tagsDAO.save(tags);	
		}
		System.out.println(id);
		StringTokenizer st = new StringTokenizer(id);
		while (st.hasMoreTokens()) {
			movies = moviesDAO.findById(Integer.parseInt(st.nextToken()));
			//movies.setId(Integer.parseInt(st.nextToken()));
			//System.out.println(movies.getMovietag());
			if(!movies.getMovietag().contains(tagname))
				movies.setMovietag(movies.getMovietag()+tagname+"/");
			moviesDAO.update(movies);
        }
		
		
		dataMap.put("success", 1);
		return "success";
	}
}
