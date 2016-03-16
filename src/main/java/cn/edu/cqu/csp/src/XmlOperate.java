package cn.edu.cqu.csp.src;

import java.io.File;  

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.w3c.dom.*;  
 

public class XmlOperate {

	private String filename;
	private Document document;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	
	/**
	 * 无参数构造函数
	 */
	public XmlOperate()
	{
		
	}
	
	/**
	 * 带参数的构造函数
	 * @param filename
	 */
	public XmlOperate(String filename)
	{
		this.filename = filename;
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			document=builder.parse(new File(filename));
			document.normalize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * 将xml保存到指定文件
	 * @param filepath
	 * @return 保存成功返回true 
	 * 		         保存失败返回false
	 */
	public boolean docToXml(String filepath)
	{
		try   
	       {   
	             /** 将document中的内容写入文件中   */   
	             TransformerFactory tFactory = TransformerFactory.newInstance();      
	             Transformer transformer = tFactory.newTransformer();    
	             /** 编码 */   
	             //transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");   
	             DOMSource source = new DOMSource(document);    
	             StreamResult result = new StreamResult(new File(filepath));      
	             transformer.transform(source, result);    
	         }catch(Exception ex)   
	         {   
	             return false;   
	             //ex.printStackTrace();   
	         }
		return true;
	}
	
	
	public boolean addXmlNode(String parentNode, String childNode, String sitename, String sitepath)
	{
		NodeList links =document.getElementsByTagName(parentNode);  
	    if(links.getLength()>0){
	    	
	    	Element parent = (Element) document.getElementsByTagName(parentNode).item(0);
	    	Element context = document.createElement(childNode);
	    	context.setAttribute("path", "/"+sitename);
	    	context.setAttribute("docBase", sitepath);
	    	parent.appendChild(context);
	    	docToXml(filename);
	    	System.out.println("add success");
	    	return true;
	    }
	    else
	    	return false;
		//return true;
	}
	
	public boolean updateXmlNode(String parentNode, String childNode, String sitename, String sitepath)
	{
		if(deleteXmlNode(parentNode,childNode,sitename))
		{
			if(addXmlNode(parentNode,childNode,sitename,sitepath))
			{
				docToXml(filename);
				return true;
			}
				
			else
				return false;
		}
		else
			return false;
		
		//return true;
	}
	public boolean deleteXmlNode(String parentNode, String childNode, String sitename)
	{
		NodeList links =(NodeList) document.getElementsByTagName("Context");  
	    if(links.getLength()>0){
	    	for (int i=0;i<links.getLength();i++){ 
	    		Element context = (Element) links.item(i);
                if(context.getAttribute("path").equals("/"+sitename))
                {
                	System.out.println("query success");
                	Element parent = (Element) document.getElementsByTagName(parentNode).item(0);
                	parent.removeChild(context);
                }
            }
	    	System.out.println("delete success");
	    	docToXml(filename);
	    	return true;
	    }
	    else
	    	return false;
	}
}
