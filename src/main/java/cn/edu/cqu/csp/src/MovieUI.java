package cn.edu.cqu.csp.src;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cqu.csp.dao.lives.Lives;
import cn.edu.cqu.csp.dao.lives.LivesDAO;
import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class MovieUI {

	private Integer id;
    private String moviename;
    private String movietag;
    private String filepath;
    private String photopath;
    private String description;
    private String duration;
    private Integer doubanid;
    private String doubanscore;
    private Integer newestscore;
    private Integer hottestscore;
    private Integer classicscore;
    private String country;
    private Integer year;
    private String director;
    private String actor;
    private Integer count;
    
    private int intPageCount;
    private String nextPage;
    private MoviesDAO moviesDAO;
    private Movies movies;
    private List list = new ArrayList();
    public MovieUI()
    {
    	moviesDAO = new MoviesDAO();
    	movies = new Movies();
    }
    
    
	public String getNextPage() {
		return nextPage;
	}


	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}


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
	public String getMovietag() {
		return movietag;
	}
	public void setMovietag(String movietag) {
		this.movietag = movietag;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getDoubanid() {
		return doubanid;
	}
	public void setDoubanid(Integer doubanid) {
		this.doubanid = doubanid;
	}
	public String getDoubanscore() {
		return doubanscore;
	}
	public void setDoubanscore(String doubanscore) {
		this.doubanscore = doubanscore;
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
    
	public Movies transformMovie(Movies movie)
	{
		this.id = movie.getId();
		this.moviename = movie.getMoviename();
		this.movietag = movie.getMovietag();
		this.filepath = movie.getFilepath();
		this.photopath = movie.getPhotopath();
		this.description = movie.getDescription();
		if(movie.getDuration()==null||movie.getDuration().length()<1)
			this.duration = "";
		else
			this.duration = movie.getDuration().substring(0,movie.getDuration().length()-1);
		//System.out.println((movie.getDuration()).substring(0,movie.getDuration().length()-1));
		//this.duration = movie.getDuration();
		this.doubanid = movie.getDoubanid();
		if(movie.getDoubanscore()==null||movie.getDoubanscore().length()<1)
			this.doubanscore = "";
		else
			this.doubanscore = movie.getDoubanscore().substring(0,movie.getDoubanscore().length()-1);
		//this.doubanscore = movie.getDoubanscore().substring(0,movie.getDoubanscore().length()-1);
		
		if(movie.getCountry()==null||movie.getCountry().length()<1)
			this.country = "";
		else
			this.country = movie.getCountry().substring(0,movie.getCountry().length()-1);
		
		this.year = movie.getYear();
		
		if(movie.getDirector()==null||movie.getDirector().length()<1)
			this.director = "";
		else
			this.director = movie.getDirector().substring(0,movie.getDirector().length()-1);
		
		//this.actor  = movie.getActor();
		if(movie.getActor()==null||movie.getActor().length()<1)
			this.actor = "";
		else
			this.actor  = movie.getActor().substring(0,movie.getActor().length()-1);
		this.count = movie.getCount();
		
		Movies temp = new Movies();
		
		temp.setId(id);
		temp.setMoviename(moviename);
		temp.setMovietag(movietag);
		temp.setFilepath(filepath);
		temp.setPhotopath(photopath);
		temp.setDescription(description);
		temp.setDuration(duration);
		temp.setDoubanid(doubanid);
		temp.setDoubanscore(doubanscore);
		temp.setCountry(country);
		temp.setYear(year);
		temp.setDirector(director);
		temp.setActor(actor);
		temp.setCount(count);
		
		
		return temp;
	}
	
	public Trailers transformTrailer(Trailers movie)
	{
		this.id = movie.getId();
		this.moviename = movie.getMoviename();
		this.movietag = movie.getMovietag();
		this.filepath = movie.getFilepath();
		this.photopath = movie.getPhotopath();
		this.description = movie.getDescription();
		if(movie.getDuration()==null||movie.getDuration().length()<1)
			this.duration = "";
		else
			this.duration = movie.getDuration().substring(0,movie.getDuration().length()-1);
		//System.out.println((movie.getDuration()).substring(0,movie.getDuration().length()-1));
		//this.duration = movie.getDuration();
		this.doubanid = movie.getDoubanid();
		if(movie.getDoubanscore()==null||movie.getDoubanscore().length()<1)
			this.doubanscore = "";
		else
			this.doubanscore = movie.getDoubanscore().substring(0,movie.getDoubanscore().length()-1);
		//this.doubanscore = movie.getDoubanscore().substring(0,movie.getDoubanscore().length()-1);
		
		if(movie.getCountry()==null||movie.getCountry().length()<1)
			this.country = "";
		else
			this.country = movie.getCountry().substring(0,movie.getCountry().length()-1);
		
		this.year = movie.getYear();
		
		if(movie.getDirector()==null||movie.getDirector().length()<1)
			this.director = "";
		else
			this.director = movie.getDirector().substring(0,movie.getDirector().length()-1);
		
		//this.actor  = movie.getActor();
		if(movie.getActor()==null||movie.getActor().length()<1)
			this.actor = "";
		else
			this.actor  = movie.getActor().substring(0,movie.getActor().length()-1);
		this.count = movie.getCount();
		
		Trailers temp = new Trailers();
		
		temp.setId(id);
		temp.setMoviename(moviename);
		temp.setMovietag(movietag);
		temp.setFilepath(filepath);
		temp.setPhotopath(photopath);
		temp.setDescription(description);
		temp.setDuration(duration);
		temp.setDoubanid(doubanid);
		temp.setDoubanscore(doubanscore);
		temp.setCountry(country);
		temp.setYear(year);
		temp.setDirector(director);
		temp.setActor(actor);
		temp.setCount(count);
		
		
		return temp;
	}
	
    public List getMoviesList(String currentPage, String pageSize, int type )
    {
    	
    	int intPage = 1;
    	int intPageSize = 1;
    	List tempList = new ArrayList();
    	
    	if(currentPage == null)
    		intPage = 1;
    	else
    		
    		intPage = Integer.parseInt(currentPage);
    	
    	if(pageSize == null)
    		intPageSize = 1;
    	else
    		
    		intPageSize = Integer.parseInt(pageSize);
    	
    	switch(type)
    	{
    	case 0:
    		tempList = moviesDAO.findNewest();
    		break;
    	case 1:
    		tempList = moviesDAO.findCount();
    		break;
    	case 2:
    		tempList = moviesDAO.findClassic();
    		break;
    	case 3:
    		tempList = moviesDAO.findAll();
    		break;
    	
    	}
    	
    	if(tempList.size()%intPageSize == 0)
    		intPageCount = tempList.size()/intPageSize;
    	else
    		intPageCount = tempList.size()/intPageSize+1;
    	
    	if(intPage >= intPageCount)
    		intPage = intPageCount;
    	
    	if(intPage <= 1)
    		intPage = 1;
    	
    	list.clear();
    	for(int i = intPageSize*(intPage - 1); i < intPageSize*(intPage - 1)+intPageSize && i<tempList.size(); i++ )
    	{
    		list.add((Movies)transformMovie((Movies) tempList.get(i)));
    	}
    	
    	while(list.size()<intPageSize)
    	{
    		list.add(new Movies());    		
    	}
    		
    	nextPage = intPage + "/" +intPageCount;
    	
    	return list;
    }
    
    public List getTagsList(String currentPage, String pageSize)
    {
    	TagsDAO tagsDAO = new TagsDAO();
    	int intPage = 1;
    	int intPageSize = 1;
    	List tempList = new ArrayList();
    	
    	if(currentPage == null)
    		intPage = 1;
    	else
    		
    		intPage = Integer.parseInt(currentPage);
    	
    	if(pageSize == null)
    		intPageSize = 1;
    	else
    		
    		intPageSize = Integer.parseInt(pageSize);

    	tempList = tagsDAO.findAll();
    	
    	if(tempList.size()%intPageSize == 0)
    		intPageCount = tempList.size()/intPageSize;
    	else
    		intPageCount = tempList.size()/intPageSize+1;
    	
    	if(intPage >= intPageCount)
    		intPage = intPageCount;
    	
    	if(intPage <= 1)
    		intPage = 1;
    	
    	list.clear();
    	for(int i = intPageSize*(intPage - 1); i < intPageSize*(intPage - 1)+intPageSize && i<tempList.size(); i++ )
    	{
    		list.add(tempList.get(i));
    	}
    	
    	while(list.size()<intPageSize)
    	{
    		list.add(new Tags());    		
    	}
    		
    	nextPage = intPage + "/" +intPageCount;
    	
    	return list;
    }
    
    public List getLivesList(String currentPage, String pageSize)
    {
    	LivesDAO livesDAO = new LivesDAO();
    	int intPage = 1;
    	int intPageSize = 1;
    	List tempList = new ArrayList();
    	
    	if(currentPage == null)
    		intPage = 1;
    	else
    		
    		intPage = Integer.parseInt(currentPage);
    	
    	if(pageSize == null)
    		intPageSize = 1;
    	else
    		
    		intPageSize = Integer.parseInt(pageSize);

    	tempList = livesDAO.findAll();
    	
    	if(tempList.size()%intPageSize == 0)
    		intPageCount = tempList.size()/intPageSize;
    	else
    		intPageCount = tempList.size()/intPageSize+1;
    	
    	if(intPage >= intPageCount)
    		intPage = intPageCount;
    	
    	if(intPage <= 1)
    		intPage = 1;
    	
    	list.clear();
    	for(int i = intPageSize*(intPage - 1); i < intPageSize*(intPage - 1)+intPageSize && i<tempList.size(); i++ )
    	{
    		list.add(tempList.get(i));
    	}
    	
    	while(list.size()<intPageSize)
    	{
    		list.add(new Lives());    		
    	}
    		
    	nextPage = intPage + "/" +intPageCount;
    	
    	return list;
    }
    
    public List getTrailersList(String currentPage, String pageSize)
    {
    	TrailersDAO trailersDAO = new TrailersDAO();
    	int intPage = 1;
    	int intPageSize = 1;
    	List tempList = new ArrayList();
    	
    	if(currentPage == null)
    		intPage = 1;
    	else
    		
    		intPage = Integer.parseInt(currentPage);
    	
    	if(pageSize == null)
    		intPageSize = 1;
    	else
    		
    		intPageSize = Integer.parseInt(pageSize);

    	tempList = trailersDAO.findAll();
    	
    	if(tempList.size()%intPageSize == 0)
    		intPageCount = tempList.size()/intPageSize;
    	else
    		intPageCount = tempList.size()/intPageSize+1;
    	
    	if(intPage >= intPageCount)
    		intPage = intPageCount;
    	
    	if(intPage <= 1)
    		intPage = 1;
    	
    	list.clear();
    	for(int i = intPageSize*(intPage - 1); i < intPageSize*(intPage - 1)+intPageSize && i<tempList.size(); i++ )
    	{
    		list.add((Trailers)transformTrailer((Trailers) tempList.get(i)));
    	}
    	
    	while(list.size()<intPageSize)
    	{
    		list.add(new Trailers());    		
    	}
    		
    	nextPage = intPage + "/" +intPageCount;
    	
    	return list;
    }
    
    public List getSearchMovies(String currentPage, String pageSize, String keyword )
    {
    	
    	int intPage = 1;
    	int intPageSize = 1;
    	List tempList = new ArrayList();
    	
    	if(currentPage == null)
    		intPage = 1;
    	else
    		
    		intPage = Integer.parseInt(currentPage);
    	
    	if(pageSize == null)
    		intPageSize = 1;
    	else
    		
    		intPageSize = Integer.parseInt(pageSize);
    	
    	tempList = moviesDAO.findByKeyword(keyword);
    	
    	
    	if(tempList.size()%intPageSize == 0)
    		intPageCount = tempList.size()/intPageSize;
    	else
    		intPageCount = tempList.size()/intPageSize+1;
    	
    	if(intPage >= intPageCount)
    		intPage = intPageCount;
    	
    	if(intPage <= 1)
    		intPage = 1;
    	
    	list.clear();
    	for(int i = intPageSize*(intPage - 1); i < intPageSize*(intPage - 1)+intPageSize && i<tempList.size(); i++ )
    	{
    		list.add((Movies)transformMovie((Movies) tempList.get(i)));
    	}
    	
    	while(list.size()<intPageSize)
    	{
    		list.add(new Movies());    		
    	}
    		
    	nextPage = intPage + "/" +intPageCount;
    	
    	return list;
    }
}
