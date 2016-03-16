package cn.edu.cqu.csp.action.sites;

import java.util.HashMap;
import java.util.Map;

import cn.edu.cqu.csp.dao.sites.Sites;
import cn.edu.cqu.csp.dao.sites.SitesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.XmlOperate;

import com.opensymphony.xwork2.ActionSupport;

public class EditSitesAction extends ActionSupport{

	private String sites_id;
	private String sites_sitename;
	private String sites_sitepath;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	public String getSites_id() {
		return sites_id;
	}
	public void setSites_id(String sites_id) {
		this.sites_id = sites_id;
	}
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
	
	public String EditSites()
	{
		SitesDAO sitesDAO = new SitesDAO();
		Sites sites = new Sites();
		String path =  sites.getClass().getResource("/").toString();
		path = path.replace("%20", " ");
		path = path.substring(5, path.indexOf("webapps"));
		System.out.println(path);
		XmlOperate xo= new XmlOperate(path+"conf/server.xml");
		if(xo.updateXmlNode("Host", "Context", sites_sitename, sites_sitepath))
		{
			sites.setId(Integer.parseInt(sites_id));
			sites.setSitename(sites_sitename);
			sites.setSitepath(sites_sitepath);
			sitesDAO.update(sites);
		}
		
		dataMap.put("success", 1);
		return "success";
	}
}
