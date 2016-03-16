package HttpClient;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



public class CreateXml { 
	  private Document document;
	  private String filename;
	  DocumentBuilderFactory factory;
	  DocumentBuilder builder;  
	  
	  public CreateXml(String name){
	      filename=name;
	      factory=DocumentBuilderFactory.newInstance();
	     try {
	        builder = factory.newDocumentBuilder();
	        document=builder.newDocument();
	      } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	       }     
	   }
	//写节点的方法 
	 /*public void toWrite(SystemInfo sBean){
	        String sid=String.valueOf(sBean.getId());
	        Document doc=null;
	        try {
	            doc =  builder.parse(new File(filename));
	        } catch (SAXException e) {
	          e.printStackTrace();
	        } catch (IOException e) {
	          e.printStackTrace();
	        } 
	  //判断是否有该节点，如果有，则删除
	      NodeList links =doc.getElementsByTagName("System"+sid);  
	      if(links.getLength()>0){              //节点已存在
	           for (int i=0;i<links.getLength();i++){ 
	                 Node nd=links.item(i); 
	                 Node catParent = nd.getParentNode();            //得到nd父节点 
	                 catParent.removeChild(nd);             //删除nd节点 
	            }
	      }
	 //写节点   
	          Element system=doc.createElement("System_"+sid);
	          Element erefreshCycle=doc.createElement("refreshCycle_"+sid);
	          Element esaveInterval=doc.createElement("saveInterval_"+sid);
	          Element edataReadCycle=doc.createElement("dataReadCycle_"+sid);
	          Element esaveData=doc.createElement("saveData_"+sid);
	          Element esoundAlarm=doc.createElement("soundAlarm_"+sid);
	  
	        Text trefreshCycle=doc.createTextNode(sBean.getRefreshCycle()); 
	        Text tsaveInterval=doc.createTextNode(sBean.getSaveInterval()); 
	        Text tdataReadCycle=doc.createTextNode(sBean.getDataReadCycle()); 
	        Text tsaveData =doc.createTextNode(sBean.getSaveData());
	        Text tsoundAlarm=doc.createTextNode(sBean.getSoundAlarm());
	        
	        Node nrefreshCycle =system.appendChild(erefreshCycle).appendChild(trefreshCycle); 
	        Node nsaveInterva = system.appendChild(esaveInterval).appendChild(tsaveInterval); 
	        Node ndataReadCycle = system.appendChild(edataReadCycle).appendChild(tdataReadCycle); 
	        Node nsaveData = system.appendChild(esaveData).appendChild(tsaveData); 
	        Node nsoundAlarm = system.appendChild(esoundAlarm).appendChild(tsoundAlarm); 
	        Node nsystem =   doc.getDocumentElement().appendChild(system);

	        TransformerFactory   tff   =   TransformerFactory.newInstance(); 
	        Transformer tf=null;
	         try {
	              tf = tff.newTransformer();
	              tf.setOutputProperty(OutputKeys.ENCODING,"GB2312");
	              tf.setOutputProperty(OutputKeys.INDENT,"yes");
	              DOMSource source =new DOMSource(doc); 
	              StreamResult rs = new StreamResult(new File(filename)); 
	              tf.transform(source,rs); 
	         } catch (Exception e) {
	             e.printStackTrace();
	         } 
	     }*/
	

	 

	 

	//读xml节点值的方法
	public String getPara(String path,String nodeName){
	        String node="";
	        try
	        {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(new File(path));
	            Element rootElement = document.getDocumentElement();
	            
	            NodeList list = rootElement.getElementsByTagName(nodeName);
	            if(list.getLength()>0){ 
	             Element element = (Element) list.item(0);
	             node=element.getChildNodes().item(0).getNodeValue();
	            }else{    //没有配置时，取默认的System_0的内容
	             nodeName=nodeName.substring(0,nodeName.indexOf("_"));
	             list = rootElement.getElementsByTagName(nodeName+"_0"); 
	             Element element = (Element) list.item(0);
	             node=element.getChildNodes().item(0).getNodeValue();
	            }          
	        }
	        catch (Exception e)
	        {
	            System.out.println("exception:" + e.getMessage());
	        }
	        return node;
	    }
}
