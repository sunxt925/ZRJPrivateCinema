package cn.edu.cqu.csp.src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.sites.Sites;
import cn.edu.cqu.csp.dao.sites.SitesDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class ImportMovies {

	private String sitename;
	
	private String moviespath;
	
	private List<String> list;
	
	private List<String> oldNamelist;
	
	private List<String> movienames;

	private TrailersDAO trailersDAO = new TrailersDAO();
	
	private Trailers trailers = new Trailers();
	
	private MoviesDAO moviesDAO = new MoviesDAO();
	
	private Movies movies = new Movies();
	
	private SitesDAO sitesDAO = new SitesDAO();
	
	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ImportMovies(String sitename) {
		this.sitename = sitename;
		list = new ArrayList();
		oldNamelist = new ArrayList();
		this.moviespath = sitesDAO.findBySitename(sitename).get(0).getSitepath();
	}
	
	public ImportMovies() {
		this.sitename = null;
		this.moviespath = null;
		list = new ArrayList();
	}
	
	
	public List getMoviesName(String reg)
	{
		list.clear();
		oldNamelist.clear();
		String res;
		File file = new File(moviespath);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				res = tempList[i].toString();
				Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(res);
                if (matcher.find()) {
                	list.add(matcher.group());
                	oldNamelist.add(res);
                }
				
			}
		}
		return list;
	}
	
	public boolean addNewMovie(String moviename, String oldname)
	{
		List result;
		String newmoviename = null;
		Pattern pattern = Pattern.compile("[\\S]+(?=\\.mkv|\\.mp4|\\.avi|\\.MKV|\\.MP4|\\.AVI|\\.RM|\\.RMVB|\\.rm|\\.rmvb|\\.WMV|\\.wmv)");
        Matcher matcher = pattern.matcher(moviename);
        if (matcher.find()) {
        	newmoviename = matcher.group();
        }
        else
        	return false;
		result = moviesDAO.findByMoviename(newmoviename);
		if(result.size() >= 1)
			return false;
		else
		{
			movies.setMoviename(newmoviename);
			oldname = oldname.substring(oldname.lastIndexOf("\\")+1);
			String namecode = MessageDigest5.MD5(newmoviename);
			String extendname = oldname.substring(oldname.indexOf('.'));
			if(FileOperate.renameFile(moviespath, oldname, namecode+extendname))
			{
				FileOperate.addFile(moviespath+"/movieindex.txt", oldname+"\n"+namecode+extendname+"\n");
				movies.setFilepath("http://192.168.0.3:8080/"+sitename+"/"+namecode+extendname);
				moviesDAO.save(movies);
				return true;
			}
			else
				return false;
			
		}
			
	}
	
	public boolean addNewTrailer(String moviename, String oldname)
	{
		List result;
		String newmoviename = null;
		Pattern pattern = Pattern.compile("[\\S]+(?=\\.mkv|\\.mp4|\\.avi|\\.MKV|\\.MP4|\\.AVI|\\.RM|\\.RMVB|\\.rm|\\.rmvb|\\.WMV|\\.wmv)");
        Matcher matcher = pattern.matcher(moviename);
        if (matcher.find()) {
        	newmoviename = matcher.group();
        }
        else
        	return false;
		result = trailersDAO.findByMoviename(newmoviename);
		if(result.size() >= 1)
			return false;
		else
		{
			trailers.setMoviename(newmoviename);
			oldname = oldname.substring(oldname.lastIndexOf("\\")+1);
			String namecode = MessageDigest5.MD5(newmoviename);
			String extendname = oldname.substring(oldname.indexOf('.'));
			if(FileOperate.renameFile(moviespath, oldname, namecode+extendname))
			{
				FileOperate.addFile(moviespath+"/movieindex.txt", oldname+"\n"+namecode+extendname+"\n");
				trailers.setFilepath("http://192.168.0.3:8080/"+sitename+"/"+namecode+extendname);
				trailersDAO.save(trailers);
				return true;
			}
			else
				return false;
			/*trailers.setMoviename(newmoviename);
			trailers.setFilepath("http://192.168.0.3:8080/"+sitename+"/"+moviename);
			trailersDAO.save(trailers);
			return true;*/
		}
			
	}
	
	
	public boolean importMovies()
	{
		FileOperate fo = new FileOperate();
		//fo.moveFile();
		getMoviesName("(?<=(\\d){4})[\\S]+");
		for(int i = 0; i < list.size(); i++)
		{
			addNewMovie(list.get(i),oldNamelist.get(i));
		}
		return true;
		
	}
	
	public boolean importTrailers()
	{
		FileOperate fo = new FileOperate();
		//fo.moveFile();
		getMoviesName("(?<=(\\d){4})[\\S]+");
		for(int i = 0; i < list.size(); i++)
		{
			addNewTrailer(list.get(i),oldNamelist.get(i));
		}
		return true;
		
	}
	
}
