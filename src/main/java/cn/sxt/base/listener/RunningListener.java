package cn.sxt.base.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.cfca_c.yb.util.Utils3DES;



public class RunningListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.err.println("项目终止");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.err.println("项目启动");
		String passString="";
		String timestamp="";
		System.out.println(getClass().getClassLoader().getResource(""));
		try {
			BufferedReader br=new BufferedReader(new FileReader(getClass().getClassLoader().getResource("/license/license.lic").getPath()));
			passString=br.readLine();
			System.out.println(passString);
			timestamp=Utils3DES.DecryptString(passString);
			System.out.println(timestamp);
			br.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean result=false;
		result=org.cfca_c.yb.util.MethodUtil.isInValidTime(Long.parseLong(timestamp));

		try {
			BufferedReader br2=new BufferedReader(new FileReader(getClass().getClassLoader().getResource("/license/licensemac.lic").getPath()));
			passString=br2.readLine();
			br2.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			result=result&&org.cfca_c.yb.util.MethodUtil.ecompareMD5(org.cfca_c.yb.util.LocalMac.getLocalMac(),passString.toUpperCase());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!result){
			System.err.println("权限验证失败");
			System.exit(1);
		}else {
			System.err.println("权限验证成功");
		}
		

	}

}
