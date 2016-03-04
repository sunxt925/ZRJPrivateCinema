package org.cfca_c.yb.util;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.cfca_c.yb.WebService.WebServiceLocator;
import org.cfca_c.yb.WebService.WebServiceSoap;

public class Sender {
	//上报xml拼接
	public static String makeXML(String nowTime, String moviename,
			String movieStartTime, String movieEndTime) {
		String userCode="";
		String password="";
		String onlyCode="";
		String movieSerial=getTimeText().split(" ")[0].replace("-", "").substring(2);//再加上当日播放号
		String xml="<?xml version='1.0' encoding='utf-8' ?><MoviePlayDetail><UserInfo><UserName name='用户代码'>";
		xml+=userCode;
		xml+="</UserName><UserPassword name='密码'>";
		xml+=password;
		xml+="</UserPassword><UserPIN name='唯一识别码'>";
		xml+=onlyCode;
		xml+="</UserPIN></UserInfo>";
		
		xml+="<MovieInfo><NO name='序号：当日日期（六位）+当日播放顺序号（四位）'>";
		xml+=movieSerial;
		xml+="</NO><MovieName name='影片名称'>";
		xml+=moviename;
		xml+="</MovieName><MoviePlayStartTime name='播放开始时间'>";
		xml+=movieStartTime;
		xml+="</MoviePlayStartTime><MoviePlayEndTime name='播放结束时间'>";
		xml+=movieEndTime;
		xml+="</MoviePlayEndTime></MovieInfo></MoviePlayDetail>";
		System.out.println("xml--"+xml);
		return xml;
	}
	//上报接口
	public static String sendPlayInfo(String MovieInfoXmlWith3DES){
		String result="";
		String inputString=Utils3DES.EncryptString (MovieInfoXmlWith3DES);

		WebServiceLocator service=new WebServiceLocator();
		try {
			WebServiceSoap soap=service.getWebServiceSoap();
			try {
				result=soap.movieBarImportWithWebservice(inputString);
				//System.out.println(result);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		String MovieInfoXmlWith3DES="<?xml version='1.0' encoding='utf-8' ?><MoviePlayDetail><UserInfo><UserName name='用户代码'>31011510000001</UserName><UserPassword name='密码'>Password</UserPassword><UserPIN name='唯一识别码'>BC591D2E-E416-4F55-9FC6-F120CB39D79A</UserPIN></UserInfo><MovieInfo><NO name='序号：当日日期（六位）+当日播放顺序号（四位）'>1504221221</NO><MovieName name='影片名称'>乙方丙方</MovieName><MoviePlayStartTime name='播放开始时间'>2015-04-22 11:01:45</MoviePlayStartTime><MoviePlayEndTime name='播放结束时间'>2015-04-22 13:02:45</MoviePlayEndTime></MovieInfo></MoviePlayDetail>";
		String inputString=Utils3DES.EncryptString (MovieInfoXmlWith3DES);
		
		WebServiceLocator service=new WebServiceLocator();
		try {
			WebServiceSoap soap=service.getWebServiceSoap();
			try {
				String resultString=soap.movieBarImportWithWebservice(inputString);
				System.out.println(resultString);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获取当前时间字符串
	public static String getTimeText() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String result=df.format(new Date());
		System.out.println(result);	
		return result;
	}

	// 获取两个时间点间隔时长
	public static long getTimeSpan(String thisTime, String lastTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long span=df.parse(thisTime).getTime()-df.parse(lastTime).getTime();
		long minSpan=span/6000; //相隔多少分钟
		return minSpan;
	}

	public static String getFullEndTime(String movieStartTime, String movieTime) {
		// 获取正常看完影片的结束时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long endTime = 0;
		try {
			endTime = df.parse(movieStartTime).getTime()+Long.parseLong(movieTime)*60000;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result=df.format(new Date(endTime));
		return result;
	}

	
}
