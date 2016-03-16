package cn.edu.cqu.csp.src;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.movietag.MoviestagDAO;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import java.util.ArrayList;
import java.util.List;

public class MoviesRecord {

	private Integer id;
    private String moviename;
    private String filepath;
    private String photopath;
    private String description;
    private Integer doubanid;
    private Integer newestscore;
    private Integer hottestscore;
    private Integer classicscore;
    private String country;
    private Integer year;
    private String director;
    private String actor;
    private Integer count;
    
	//private Movies movie;
	private String movietag;
	
	private List<MoviesRecord> list;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getDoubanid() {
		return doubanid;
	}
	public void setDoubanid(Integer doubanid) {
		this.doubanid = doubanid;
	}
	public Integer getNewestscore() {
		return newestscore;
	}
	public void setNewestscore(Integer newestscore) {
		this.newestscore = newestscore;
	}
	public Integer getHottestscore() {
		return hottestscore;
	}
	public void setHottestscore(Integer hottestscore) {
		this.hottestscore = hottestscore;
	}
	public Integer getClassicscore() {
		return classicscore;
	}
	public void setClassicscore(Integer classicscore) {
		this.classicscore = classicscore;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	/*public Movies getMovie()
	{
		return this.movie;
	}*/
	public void setMovie(Movies movie) {
		this.id = movie.getId();
		this.moviename = movie.getMoviename();
        this.filepath = movie.getFilepath();
        this.photopath = movie.getPhotopath();
        this.description = movie.getDescription();
        this.doubanid = movie.getDoubanid();
        this.newestscore = movie.getNewestscore();
        this.hottestscore = movie.getHottestscore();
        this.classicscore = movie.getClassicscore();
        this.country = movie.getCountry();
        this.year = movie.getYear();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.count = movie.getCount();
		//this.movie = movie;
	}
	public String getMovietag() {
		return movietag;
	}
	public void setMovietag(String tags) {
		this.movietag = tags;
	}
	
	public List findAll()
	{
		list = new ArrayList();
		int i = 0, j = 0;
		int movieid;
		int tagid;
		List tagidList;
		List tagnameList=null;
		List newList=null;
		String tags="";
		MoviesDAO moviesDAO = new MoviesDAO();
		MoviesRecord moviesRecord = new MoviesRecord();
		TagsDAO tagsDAO = new TagsDAO();
		MoviestagDAO moviestagDAO = new MoviestagDAO();
		
		newList = (List) moviesDAO.findAll();
		for (i = 0; i < newList.size(); i++)
		{			
			movieid = ((Movies) newList.get(i)).getId();
			tagidList = moviestagDAO.findTagsidByMoviesid(movieid);
			for (j = 0; j < tagidList.size(); j++)
			{
				tagnameList = tagsDAO.findTagnameById((Integer)tagidList.get(j));
				tags = tags + (String)tagnameList.get(0)+"/";
			}
			System.out.println(tags);
			moviesRecord.setMovie((Movies) newList.get(i));
			moviesRecord.setMovietag(tags);
			list.add(moviesRecord);
		}
		return list;
	}
	
}
