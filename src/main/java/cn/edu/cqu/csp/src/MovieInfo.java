package cn.edu.cqu.csp.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.cfca_c.yb.util.GetIDByName;

import cn.edu.cqu.csp.dao.movies.Movies;
import cn.edu.cqu.csp.dao.movies.MoviesDAO;
import cn.edu.cqu.csp.dao.sites.Sites;
import cn.edu.cqu.csp.dao.sites.SitesDAO;
import cn.edu.cqu.csp.dao.trailers.Trailers;
import cn.edu.cqu.csp.dao.trailers.TrailersDAO;

public class MovieInfo {

	public static final String DIRECTOR = "(?<=\"v:directedBy\">)[ \\S]+(?=</a></span></span><br/>)";
	public static final String DESCRIPTION = "(?<=<span property=\"v:summary\"( class=\"\")?>[\\s]{0,1000})[ \\S]+(\\s+<br />\\s+[ \\S]+)*";
	public static final String ACTOR = "(?<=rel=\"v:starring\">)[\u4e00-\u9fa5·‧]+";
	public static final String COUNTRY = "(?<=制片国家/地区:</span> )[\u4e00-\u9fa5\\s/]+";
	public static final String YEAR = "(?<=<span property=\"v:initialReleaseDate\" content=\")[\\d]+(?=(-\\d\\d){0,2})";
	public static final String DURATION = "(?<=<span property=\"v:runtime\" content=\"\\d{1,3}\">)[\\d\u4e00-\u9fa5]+";
	public static final String DOUBANSCORE = "(?<=<strong class=\"ll rating_num\" property=\"v:average\">)[ \\d.]+";
	public static final String TAG = "(?<=<span property=\"v:genre\">)[\u4e00-\u9fa5\\s/]+";
	public static final String PHOTO = "(?<= href=\")[\\S]+(?=\" title=\"点击看更多海报\">)";
	public static final String PHOTO2 = "(?<=<img src=\")[\\S]+(?=\" />)";
	public static final String OTHERNAME = "(?<=又名:</span> )[\u4e00-\u9fa5\\s\\w/()]+";
	public static final String ENGLISHNAME = "(?<=\"v:itemreviewed\">)[\u4e00-\u9fa5\\s\\w]++";
	
	private String Str = new String("");
	private String movieHtml;
	private String doubanid;
	private String moviename;
	//private Pattern pattern;
    //private Matcher matcher;
	private String tempStr = new String("");
	
    private MoviesDAO moviesDAO;
    private Movies movies;
    
    private TrailersDAO trailersDAO;
    private Trailers trailers;
    
    //private SitesDAO sitesDAO;
    //private Sites site;
    
    private String sitename;
    private String sitepath;
    public MovieInfo()
    {
    	SitesDAO sitesDAO = new SitesDAO();
    	Sites site = new Sites();
    	site = (sitesDAO.findBySitename("images")).get(0);
    	sitename = site.getSitename();
    	sitepath = site.getSitepath();
    	movieHtml = new String();
    	moviesDAO = new MoviesDAO();
    	movies = new Movies();
    	
    	trailersDAO = new TrailersDAO();
    	trailers = new Trailers();
    	
    }
    /*
     * 获取豆瓣id By name, sxt
     */
    public boolean getDoubanId(String movieid)
    {
    	movies = moviesDAO.findById(Integer.parseInt(movieid));
    	if(movies.getDoubanid()==null||movies.getDoubanid()==0){
    		try
    		{
    			doubanid = GetIDByName.getID(movies.getMoviename());
    			System.out.println("获取豆瓣ID："+doubanid);
    		}
    		catch(Exception e)
    		{
    			return false;
    		}

    		if(doubanid == null||doubanid.length()==0)
    		{
    			return false;
    		}
    		else
    		{
    			System.out.println("获取豆瓣ID成功："+doubanid);
    			return true;
    		}
    	}else{
    		doubanid = movies.getDoubanid().toString();
    	}
		return true;
    	
    }
    
    public boolean getMovies(String movieid)
    {
    	//System.out.println("getting movies1");
    	movies = moviesDAO.findById(Integer.parseInt(movieid));
    	//System.out.println("getting movies2");
    	try
    	{
    		//System.out.println("getting movies3");
    		//doubanid = movies.getDoubanid().toString();
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    	
    	//System.out.println("hghgh");
    	moviename = movies.getMoviename();
    	if(doubanid == null||doubanid.length()==0)
    	{
    		return false;
    	}
    	else
    	{
    		
        	return getMovieHtml();
    	}
    	
    }
    
    public boolean getTrailers(String movieid)
    {
    	trailers = trailersDAO.findById(Integer.parseInt(movieid));
    	try
    	{
    		doubanid = trailers.getDoubanid().toString();
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    	
    	//System.out.println("hghgh");
    	moviename = trailers.getMoviename();
    	if(doubanid == null||doubanid.length()==0)
    	{
    		return false;
    	}
    	else
    	{
    		return getMovieHtml();
        	 
    	}
    	
    }
    
	public String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,Charset.forName("utf-8")));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {
            	//getEncoding(line);
                sb.append(line + "\n");  
                //System.out.println(new String(line.getBytes("utf-8"),"utf-8"));
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
	
	public boolean getMovieHtml()
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet("http://movie.douban.com/subject/"+doubanid+"/");    
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);
            if(response.getStatusLine().equals("403"))
            	return false;
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                //System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                if (entity != null) { 
                	InputStream instreams = entity.getContent();
                	movieHtml = convertStreamToString(instreams);
                } 
                else
                	return false;
                System.out.println("------------------------------------");  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
            return false;
        } catch (ParseException e) {  
            e.printStackTrace();  
            return false;
        } catch (IOException e) {  
            e.printStackTrace(); 
            return false;
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return true;
	}
	public boolean getMovieHtmlAuto(String doubanid)
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet("http://movie.douban.com/subject/"+doubanid+"/");    
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);
            if(response.getStatusLine().equals("403"))
            	return false;
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                //System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                if (entity != null) { 
                	InputStream instreams = entity.getContent();
                	movieHtml = convertStreamToString(instreams);
                } 
                else
                	return false;
                System.out.println("------------------------------------");  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
            return false;
        } catch (ParseException e) {  
            e.printStackTrace();  
            return false;
        } catch (IOException e) {  
            e.printStackTrace(); 
            return false;
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return true;
	}
	
	public boolean downloadPoster()
	{
		String photopath1=new String("");
		String photopath2=new String("");
		String photoHtml;
		String photopath = matchInfo(PHOTO);
		if(photopath==null)
			return false;
		photopath = photopath.substring(0,photopath.length()-1);
		//System.out.println(photopath);
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(photopath);    
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);
            //response.getStatusLine();
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                //System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                if (entity != null) { 
                	InputStream instreams = entity.getContent();
                	photoHtml = convertStreamToString(instreams);
                	Str="";
                	Pattern pattern = Pattern.compile(PHOTO2);
            		Matcher matcher = pattern.matcher(photoHtml);
                    while (matcher.find()) {
                    	if(photopath1.equals(""))
                    		photopath1 = matcher.group();
                    	else
                    	{
                    		photopath2 = matcher.group();
                    		break;
                    	}
                    }
                    if(photopath2.equals(""))
                    	Str=photopath1;
                    else
                    	Str=photopath2;
                    
                    Str = Str.replace("thumb", "photo");
                    System.out.println(Str);
                    UrlResource.saveUrlFile(Str,sitepath+"/"+MessageDigest5.MD5(moviename)+".jpg");
                    movies.setPhotopath("http://192.168.0.3:8080/"+sitename+"/"+MessageDigest5.MD5(moviename)+".jpg");
                } 
                else
                	return false;
                //System.out.println("------------------------------------");  
            } catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
            	return false;
			} finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
        	return false;
        } catch (ParseException e) {  
        	return false;  
        } catch (IOException e) {  
        	return false; 
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return true;
	}
	
	public boolean downloadPoster1()
	{
		String photopath1=new String("");
		String photopath2=new String("");
		String photoHtml;
		String photopath = matchInfo(PHOTO);
		if(photopath==null)
			return false;
		photopath = photopath.substring(0,photopath.length()-1);
		//System.out.println(photopath);
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet(photopath);    
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);
            //response.getStatusLine();
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                //System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                if (entity != null) { 
                	InputStream instreams = entity.getContent();
                	photoHtml = convertStreamToString(instreams);
                	Str="";
                	Pattern pattern = Pattern.compile(PHOTO2);
            		Matcher matcher = pattern.matcher(photoHtml);
                    while (matcher.find()) {
                    	if(photopath1.equals(""))
                    		photopath1 = matcher.group();
                    	else
                    	{
                    		photopath2 = matcher.group();
                    		break;
                    	}
                    }
                    if(photopath2.equals(""))
                    	Str=photopath1;
                    else
                    	Str=photopath2;
                    
                    Str = Str.replace("thumb", "photo");
                    System.out.println(Str);
                    UrlResource.saveUrlFile(Str,sitepath+"/"+MessageDigest5.MD5(moviename)+".jpg");
                    trailers.setPhotopath("http://192.168.0.3:8080/"+sitename+"/"+MessageDigest5.MD5(moviename)+".jpg");
                } 
                else
                	return false;
                //System.out.println("------------------------------------");  
            } catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
            	return false;
			} finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
        	return false;
        } catch (ParseException e) {  
        	return false;  
        } catch (IOException e) {  
        	return false; 
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return true;
	}
	
	public String matchInfo(String reg)
	{
		tempStr = "";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(movieHtml);
        while (matcher.find()) {
        	//String str1 = matcher.group();
        	//Str = new String(matcher.group().getBytes("GBK"), "unicode");
			//System.out.println(matcher.group());
        	if(tempStr.equals(matcher.group()+"/"))
        		break;
        	tempStr = tempStr + matcher.group()+"/";
        	//System.out.println(tempStr);
        	//return matcher.group();
			
        }
        if(tempStr.equals(""))
        	return null;
        else
        	return tempStr;
		
	}
	/*
	 * 只增加英文名和别名： sxt
	 */
	public boolean getMovieEOInfo()
	{
		System.out.println("getInfo");
			System.out.println(matchInfo(ENGLISHNAME));
		movies.setOthername(matchInfo(OTHERNAME));
		movies.setEnglishname(matchInfo(ENGLISHNAME));
		moviesDAO.update(movies);
		return true;
	}
	public boolean getMoviesEO(String movieid)
    {
    	//System.out.println("getting movies1");
    	movies = moviesDAO.findById(Integer.parseInt(movieid));
    	//System.out.println("getting movies2");
    	try
    	{
    		//System.out.println("getting movies3");
    		doubanid = movies.getDoubanid().toString();
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    	
    	//System.out.println("hghgh");
    	moviename = movies.getMoviename();
    	if(doubanid == null||doubanid.length()==0)
    	{
    		return false;
    	}
    	else
    	{
    		
        	return getMovieHtml();
    	}
    	
    }
	
	public boolean getMovieInfo()
	{
		System.out.println("getInfo");
		//
		movies.setDirector(matchInfo(DIRECTOR));
		String actor = matchInfo(ACTOR);
		System.out.println(actor);
		movies.setActor(actor);
		System.out.println(movies.getActor());
		movies.setDescription(matchInfo(DESCRIPTION));
		movies.setCountry(matchInfo(COUNTRY));
		try{
			movies.setYear(Integer.parseInt(matchInfo(YEAR).substring(0, 4)));
		}
		catch(Exception ex)
		{
			return false;
		}
		
		movies.setDoubanid(Integer.parseInt(doubanid));
		movies.setDuration(matchInfo(DURATION));
		String dbscore=matchInfo(DOUBANSCORE);
		movies.setDoubanscore(dbscore);
		if(dbscore.startsWith("8")||dbscore.startsWith("9"))
			movies.setMovietag(matchInfo(TAG)+"豆瓣高分");
		else {
			movies.setMovietag(matchInfo(TAG));
		}	
		//movies.setMovietag(matchInfo(TAG));
		System.out.println(matchInfo(ENGLISHNAME));
		movies.setOthername(matchInfo(OTHERNAME));
		movies.setEnglishname(matchInfo(ENGLISHNAME));
		moviesDAO.update(movies);
		return true;
	}
	
	public boolean getMovieInfoAuto(String name,String doubanid)
	{
		System.out.println(name+"++"+doubanid);
		Movies movies=new Movies();
		movies.setMoviename(name);
		movies.setDoubanid(Integer.parseInt(doubanid));
		System.out.println("getInfoAuto");
		getMovieHtmlAuto(doubanid);
		
		movies.setDirector(matchInfo(DIRECTOR));
		
		String actor = matchInfo(ACTOR);
		System.out.println(actor);
		movies.setActor(actor);
		System.out.println(movies.getActor());
		movies.setDescription(matchInfo(DESCRIPTION));
		movies.setCountry(matchInfo(COUNTRY));
		try{
			movies.setYear(Integer.parseInt(matchInfo(YEAR).substring(0, 4)));
		}
		catch(Exception ex)
		{
			return false;
		}
		
		movies.setCount(0);
		movies.setNewestscore(0);
		movies.setHottestscore(0);
		movies.setClassicscore(0);
		movies.setDuration(matchInfo(DURATION));
		String dbscore=matchInfo(DOUBANSCORE);
		System.out.println("dbscore "+dbscore);
		movies.setDoubanscore(dbscore);
		if(dbscore.startsWith("8")||dbscore.startsWith("9"))
			movies.setMovietag(matchInfo(TAG)+"豆瓣高分");
		else {
			movies.setMovietag(matchInfo(TAG));
		}		
		System.out.println(matchInfo(ENGLISHNAME));
		movies.setOthername(matchInfo(OTHERNAME));
		movies.setEnglishname(matchInfo(ENGLISHNAME));
		moviesDAO.save(movies);
		return true;
	}
	
	public boolean getTrailerInfo()
	{
		//
		trailers.setDirector(matchInfo(DIRECTOR));
		String actor = matchInfo(ACTOR);
		System.out.println(actor);
		trailers.setActor(actor);
		System.out.println(movies.getActor());
		trailers.setDescription(matchInfo(DESCRIPTION));
		trailers.setCountry(matchInfo(COUNTRY));
		try{
			trailers.setYear(Integer.parseInt(matchInfo(YEAR).substring(0, 4)));
		}
		catch(Exception ex)
		{
			return false;
		}
		
		trailers.setDuration(matchInfo(DURATION));
		trailers.setDoubanscore(matchInfo(DOUBANSCORE));
		trailers.setMovietag(matchInfo(TAG));
		trailersDAO.update(trailers);
		return true;
	}
}
