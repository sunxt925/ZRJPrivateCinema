package MainTest;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.cqu.csp.src.FileOperate;

//电影字幕格式：.utf .idx .sub .srt .smi .rt .txt .ssa .aq .jss .js .ass
//电影格式：
public class GetMovies {

	public static void main(String args[]) {
		FileOperate fo = new FileOperate();
		fo.moveFile("D:\\MZH\\video\\2014one.mkv", "D:\\MZH\\2014one.mkv");
	}
		
}
