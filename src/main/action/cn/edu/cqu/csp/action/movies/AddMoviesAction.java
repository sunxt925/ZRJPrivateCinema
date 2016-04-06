package cn.edu.cqu.csp.action.movies;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cfca_c.yb.util.GetIDByName;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.MovieInfo;

import com.opensymphony.xwork2.ActionSupport;

public class AddMoviesAction extends ActionSupport{

	private String movies_moviename;
	private String movies_movietag;
	private String movies_filepath;
	private String movies_photopath;
	private String movies_description;
	private String movies_duration;
	private String movies_doubanid;
	private String movies_country;
	private String movies_year;
	private String movies_director;
	private String movies_actor;
	private String movies_count;
	private String movies_doubanscore;
	private String movies_newestscore;
	private String movies_hottestscore;
	private String movies_classicscore;

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	
	
	
	public String getMovies_doubanscore() {
		return movies_doubanscore;
	}

	public void setMovies_doubanscore(String movies_doubanscore) {
		this.movies_doubanscore = movies_doubanscore;
	}

	public String getMovies_movietag() {
		return movies_movietag;
	}

	public void setMovies_movietag(String movies_movietag) {
		this.movies_movietag = movies_movietag;
	}

	public String getMovies_duration() {
		return movies_duration;
	}

	public void setMovies_duration(String movies_duration) {
		this.movies_duration = movies_duration;
	}

	public String getMovies_moviename() {
		return movies_moviename;
	}

	public void setMovies_moviename(String movies_moviename) {
		this.movies_moviename = movies_moviename;
	}

	public String getMovies_filepath() {
		return movies_filepath;
	}

	public void setMovies_filepath(String movies_filepath) {
		this.movies_filepath = movies_filepath;
	}

	public String getMovies_photopath() {
		return movies_photopath;
	}

	public void setMovies_photopath(String movies_photopath) {
		this.movies_photopath = movies_photopath;
	}

	public String getMovies_description() {
		return movies_description;
	}

	public void setMovies_description(String movies_description) {
		this.movies_description = movies_description;
	}

	public String getMovies_doubanid() {
		return movies_doubanid;
	}

	public void setMovies_doubanid(String movies_doubanid) {
		this.movies_doubanid = movies_doubanid;
	}

	public String getMovies_country() {
		return movies_country;
	}

	public void setMovies_country(String movies_country) {
		this.movies_country = movies_country;
	}

	public String getMovies_year() {
		return movies_year;
	}

	public void setMovies_year(String movies_year) {
		this.movies_year = movies_year;
	}

	public String getMovies_director() {
		return movies_director;
	}

	public void setMovies_director(String movies_director) {
		this.movies_director = movies_director;
	}

	public String getMovies_actor() {
		return movies_actor;
	}

	public void setMovies_actor(String movies_actor) {
		this.movies_actor = movies_actor;
	}

	public String getMovies_count() {
		return movies_count;
	}

	public void setMovies_count(String movies_count) {
		this.movies_count = movies_count;
	}

	public String getMovies_newestscore() {
		return movies_newestscore;
	}

	public void setMovies_newestscore(String movies_newestscore) {
		this.movies_newestscore = movies_newestscore;
	}

	public String getMovies_hottestscore() {
		return movies_hottestscore;
	}

	public void setMovies_hottestscore(String movies_hottestscore) {
		this.movies_hottestscore = movies_hottestscore;
	}

	public String getMovies_classicscore() {
		return movies_classicscore;
	}

	public void setMovies_classicscore(String movies_classicscore) {
		this.movies_classicscore = movies_classicscore;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String AddMovies()
	{
		System.out.println("noauto");
		MoviesDAO moviesDAO = new MoviesDAO();
		Movies movies = new Movies();
		movies.setMoviename(movies_moviename);
		movies.setMovietag(movies_movietag);
		movies.setFilepath(movies_filepath);
		movies.setPhotopath(movies_photopath);
		movies.setDescription(movies_description);
		movies.setDuration(movies_duration);
		movies.setDoubanscore(movies_doubanscore);
		if(movies_doubanid.equals(""))
			movies.setDoubanid(0);
		else
			movies.setDoubanid(Integer.parseInt(movies_doubanid));
		movies.setCountry(movies_country);
		
		if(movies_year.equals(""))
			movies.setYear(0);
		else
			movies.setYear(Integer.parseInt(movies_year));
		
		movies.setDirector(movies_director);
		movies.setActor(movies_actor);
		if(movies_count.equals(""))
			movies.setCount(0);
		else
			movies.setCount(Integer.parseInt(movies_count));
		
		if(movies_newestscore.equals(""))
			movies.setNewestscore(0);
		else
			movies.setNewestscore(Integer.parseInt(movies_newestscore));
		
		if(movies_hottestscore.equals(""))
			movies.setHottestscore(0);
		else
			movies.setHottestscore(Integer.parseInt(movies_hottestscore));
		
		if(movies_classicscore.equals(""))
			movies.setClassicscore(0);
		else
			movies.setClassicscore(Integer.parseInt(movies_classicscore));
		
		/*if(tags_selected.equals("1"))
		{
			tags.setSelected(true);
		}
		else
		{
			tags.setSelected(false);
		}*/
		moviesDAO.save(movies);
		dataMap.put("success", 1);
		return "success";
	}
	
	public String AddMoviesAuto()
	{
		System.out.println("auto");
		//MoviesDAO moviesDAO = new MoviesDAO();
		//Movies movies = new Movies();
		try {
			System.out.println("name:"+movies_moviename);
			String doubanid=GetIDByName.getID(movies_moviename);
			String[] ids=doubanid.split(";");
			for(String i:ids){
				MovieInfo info=new MovieInfo();
				info.getMovieInfoAuto(movies_moviename, i);
			}
//			MovieInfo info=new MovieInfo();
//			info.getMovieInfoAuto(movies_moviename, doubanid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//moviesDAO.save(movies);
		dataMap.put("success", 1);
		return "success";
	}
}
