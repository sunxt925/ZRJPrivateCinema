package cn.edu.cqu.csp.action.trailers;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class AddTrailersAction {

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
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public String getMovies_moviename() {
		return movies_moviename;
	}

	public void setMovies_moviename(String movies_moviename) {
		this.movies_moviename = movies_moviename;
	}

	public String getMovies_movietag() {
		return movies_movietag;
	}

	public void setMovies_movietag(String movies_movietag) {
		this.movies_movietag = movies_movietag;
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

	public String getMovies_duration() {
		return movies_duration;
	}

	public void setMovies_duration(String movies_duration) {
		this.movies_duration = movies_duration;
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

	public String getMovies_doubanscore() {
		return movies_doubanscore;
	}

	public void setMovies_doubanscore(String movies_doubanscore) {
		this.movies_doubanscore = movies_doubanscore;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String AddTrailers()
	{
		TrailersDAO trailersDAO = new TrailersDAO();
		Trailers trailers = new Trailers();
		trailers.setMoviename(movies_moviename);
		trailers.setMovietag(movies_movietag);
		trailers.setFilepath(movies_filepath);
		trailers.setPhotopath(movies_photopath);
		trailers.setDescription(movies_description);
		trailers.setDuration(movies_duration);
		trailers.setDoubanscore(movies_doubanscore);
		if(movies_doubanid.equals(""))
			trailers.setDoubanid(0);
		else
			trailers.setDoubanid(Integer.parseInt(movies_doubanid));
		trailers.setCountry(movies_country);
		
		if(movies_year.equals(""))
			trailers.setYear(0);
		else
			trailers.setYear(Integer.parseInt(movies_year));
		
		trailers.setDirector(movies_director);
		trailers.setActor(movies_actor);
		if(movies_count.equals(""))
			trailers.setCount(0);
		else
			trailers.setCount(Integer.parseInt(movies_count));
		
		
		trailersDAO.save(trailers);
		dataMap.put("success", 1);
		return "success";
	}
}
