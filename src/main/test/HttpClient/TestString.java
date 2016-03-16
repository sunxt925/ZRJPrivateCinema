package HttpClient;

public class TestString {
	public static void main(String args[])
	{
		String testString = new String("http://img5.douban.com/view/photo/thumb/public/p2222384116");
		String newString = testString.replace("thumb", "photo");
		System.out.println(newString);
	}
	
}
