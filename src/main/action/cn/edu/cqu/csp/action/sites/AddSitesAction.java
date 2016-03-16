package cn.edu.cqu.csp.action.sites;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import cn.edu.cqu.csp.dao.sites.Sites;
import cn.edu.cqu.csp.dao.sites.SitesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.XmlOperate;

import com.opensymphony.xwork2.ActionSupport;

public class AddSitesAction extends ActionSupport{

	private String sites_sitename;
	private String sites_sitepath;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	
	
	public String getSites_sitename() {
		return sites_sitename;
	}
	public void setSites_sitename(String sites_sitename) {
		this.sites_sitename = sites_sitename;
	}
	public String getSites_sitepath() {
		return sites_sitepath;
	}
	public void setSites_sitepath(String sites_sitepath) {
		this.sites_sitepath = sites_sitepath;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String AddSites()
	{
		//ServletContext sc;
		SitesDAO sitesDAO = new SitesDAO();
		Sites sites = new Sites();
		
		String path =  sites.getClass().getResource("/").toString();
		path = path.replace("%20", " ");
		path = path.substring(5, path.indexOf("webapps"));
		System.out.println(path);
		XmlOperate xo= new XmlOperate(path+"conf/server.xml");
		//String path = this.ServletContext;
		
		
		if(xo.addXmlNode("Host", "Context", sites_sitename, sites_sitepath))
		{
			//xo.docToXml(filepath)
			sites.setSitename(sites_sitename);
			sites.setSitepath(sites_sitepath);
			sitesDAO.save(sites);
		}
		
		dataMap.put("success", 1);
		return "success";
	}
}
