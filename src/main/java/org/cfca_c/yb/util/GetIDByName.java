package org.cfca_c.yb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class GetIDByName {
	
	public static void main(String[] args) throws IOException{
//		String url="http://i.cqu.edu.cn/iframe/searchStaffInfo.do?zjhm=411302199103292331";
//		String username=getUsername(url);
//		System.out.println("username:::"+username);
		String name="��ҹ��";
		System.out.println(name+"��ID: "+getID(name));
	}
	
	public static String getID(String name) throws IOException{
		String money="";
		String url="https://movie.douban.com/subject_search?search_text="+name+"&cat=1002";
		System.out.println("url:"+url);
		String movieId=getIDsByURL(url,name);
		//String loginurl="http://ids.cqu.edu.cn/amserver/UI/Login?goto=http://i.cqu.edu.cn/welcome/getUserInfo.do&goto=http://i.cqu.edu.cn&IDToken1="+username+"&IDToken2="+password;
		//System.out.println("loginurl:::::::::"+loginurl);
	
		/*
		Random rand = new Random();
		String filename="c:/"+rand.nextInt()+".html";
		URL aurl = new URL(loginurl);
		BufferedReader br = new BufferedReader(new InputStreamReader(aurl.openStream()));
		FileWriter fw = new FileWriter(filename);
		String line = "";
		while (true)
		{
			line = br.readLine();
			if(line==null)break;
			fw.write(line);
		}
		fw.flush();
		fw.close();
		
		File html=new File(filename);
        try {  
            org.jsoup.nodes.Document doc = Jsoup.parse(html, "utf-8");  
//            Elements links = doc.select("td[class=gradule-args]");   
//            System.out.println("size::::"+links.size());
//            money=links.get(0).text();

        } catch (IOException e) {  
            e.printStackTrace();  
        } finally{
        	//html.delete();
        }
		*/
		return movieId;
	}
	private static String getIDsByURL(String url,String name) throws IOException {  
        String id="";
        try {  
            org.jsoup.nodes.Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();  
            Elements links = doc.select("p[class=pl]");   
            Element link=links.first();
            String text=link.text();
            System.out.println("��飺"+text);
            Elements idlinks = doc.select("div[class=pl2]>a");
            for(Element lk:idlinks){
            	if(lk.text().split("/")[0].startsWith(name)){
            		id=id+getIDFromElement(lk)+";";            		
            	}
            }
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        System.out.println("ids:"+id);
        return id;  
    }
	
	private static String getIDByURL(String url,String name) throws IOException {  
        String id="";
        try {  
            org.jsoup.nodes.Document doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();  
            Elements links = doc.select("p[class=pl]");   
            Element link=links.first();
            String text=link.text();
            System.out.println("��飺"+text);
            Elements idlinks = doc.select("div[class=pl2]>a");
            for(Element lk:idlinks){
            	if(lk.text().split("/")[0].startsWith(name)){
            		return getIDFromElement(lk);            		
            	}
            }

        } catch (IOException e) {  
            e.printStackTrace();  
        }
  
        return id;  
    }

	private static String getIDFromElement(Element lk) {
		String id="";
		String allnames=lk.text();
        //System.out.println("�������ƣ�"+allnames);
        String text2=lk.attr("href");
        int index=text2.substring(0, text2.length()-1).lastIndexOf("/");
        id=text2.substring(index+1,text2.length()-1);
        //System.out.println("id��"+id);
		return id;
	}  
}
