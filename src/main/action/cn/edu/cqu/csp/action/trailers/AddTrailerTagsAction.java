package cn.edu.cqu.csp.action.trailers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class AddTrailerTagsAction {

	private String tagname;
	private String id;

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

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
	
	public String AddTrailerTags()
	{
		List getTags = new ArrayList();
		System.out.println(tagname);
		TagsDAO tagsDAO = new TagsDAO();
		Tags tags = new Tags();
		TrailersDAO trailersDAO = new TrailersDAO();
		Trailers trailers = new Trailers();
		
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
			trailers = trailersDAO.findById(Integer.parseInt(st.nextToken()));
			//movies.setId(Integer.parseInt(st.nextToken()));
			System.out.println(trailers.getMovietag());
			trailers.setMovietag(trailers.getMovietag()+tagname+"/");
			trailersDAO.update(trailers);
        }
		
		
		dataMap.put("success", 1);
		return "success";
	}
}
