package cn.edu.cqu.csp.action.sites;

import java.util.List;

import cn.edu.cqu.csp.dao.sites.SitesDAO;
import cn.edu.cqu.csp.dao.tags.TagsDAO;

import com.opensymphony.xwork2.ActionSupport;

public class LoadMovieSitesAction extends ActionSupport{

	private List list;
	
	
	
	public List getList() {
		return list;
	}



	public void setList(List list) {
		this.list = list;
	}



	public String LoadMovieSites()
	{
		SitesDAO sitesDAO = new SitesDAO();
		//List temp = new ArrayList();
		//list = new ArrayList();
		list = (List) sitesDAO.findAll();
		/*for(int i = 0; i<temp.size(); i++)
		{
			record.setId(((Tags)temp.get(i)).getId());
			record.setTagname(((Tags)temp.get(i)).getTagname());
			list.add(record);
			
		}*/
		//dataMap.clear();
		//dataMap.put("rows", list);  
        return SUCCESS;   
	}
}
