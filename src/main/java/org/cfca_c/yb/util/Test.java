package org.cfca_c.yb.util;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.cfca_c.yb.WebService.WebServiceLocator;
import org.cfca_c.yb.WebService.WebServiceSoap;

public class Test {

	public static void main(String[] args) {
		String MovieInfoXmlWith3DES="<?xml version='1.0' encoding='utf-8' ?><MoviePlayDetail><UserInfo><UserName name='�û�����'>31011510000001</UserName><UserPassword name='����'>Password</UserPassword><UserPIN name='Ψһʶ����'>BC591D2E-E416-4F55-9FC6-F120CB39D79A</UserPIN></UserInfo><MovieInfo><NO name='��ţ��������ڣ���λ��+���ղ���˳��ţ���λ��'>1504221221</NO><MovieName name='ӰƬ����'>�ҷ�����</MovieName><MoviePlayStartTime name='���ſ�ʼʱ��'>2015-04-22 11:01:45</MoviePlayStartTime><MoviePlayEndTime name='���Ž���ʱ��'>2015-04-22 13:02:45</MoviePlayEndTime></MovieInfo></MoviePlayDetail>";
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

}
