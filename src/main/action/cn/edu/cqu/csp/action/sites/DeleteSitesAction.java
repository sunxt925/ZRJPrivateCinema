package cn.edu.cqu.csp.action.sites;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.dao.sites.Sites;
import cn.edu.cqu.csp.dao.sites.SitesDAO;
import cn.edu.cqu.csp.dao.tags.Tags;
import cn.edu.cqu.csp.dao.tags.TagsDAO;
import cn.edu.cqu.csp.src.XmlOperate;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteSitesAction extends ActionSupport{

private String idArray;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public String getIdArray() {
		return idArray;
	}

	public void setIdArray(String idArray) {
		this.idArray = idArray;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public String DeleteSites(){
		SitesDAO sitesDAO = new SitesDAO();
		Sites sites = new Sites();
		String sites_sitename = new String("");
		
		String path =  sites.getClass().getResource("/").toString();
		path = path.replace("%20", " ");
		path = path.substring(5, path.indexOf("webapps"));
		System.out.println(path);
		XmlOperate xo= new XmlOperate(path+"conf/server.xml");
		StringTokenizer st = new StringTokenizer(idArray);
		while (st.hasMoreTokens()) {
			sites = sitesDAO.findById(Integer.parseInt(st.nextToken()));
			if(xo.deleteXmlNode("Host", "Context", sites.getSitename()))
			{
				//xo.docToXml(filepath)
				//sites.setId(Integer.parseInt(st.nextToken()));
				sitesDAO.delete(sites);
			}
			
        }
		dataMap.put("success", 1);
		return "success";
	}
}
