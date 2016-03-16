package HttpClient;

import java.util.Map;
import java.util.StringTokenizer;

import cn.edu.cqu.csp.action.movies.GetMoviesInfoAction;
import cn.edu.cqu.csp.src.FileOperate;
import cn.edu.cqu.csp.src.MessageDigest5;
import cn.edu.cqu.csp.src.MovieInfo;
import cn.edu.cqu.csp.src.XmlOperate;

public class HttpClientTest {
	public static void main(String args[])
	{
		String oldname = "test.txt";
		String namecode = MessageDigest5.MD5("test");
		String extendname = oldname.substring(oldname.indexOf('.'));
		FileOperate.renameFile("d:/", oldname, namecode+extendname);
		
		FileOperate.addFile("d:/movieindex.txt", oldname+"\n"+namecode+extendname+"\n");
	}

}
