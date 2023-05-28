package libraries;
import java.util.*;

public class console{
	static Scanner input = new Scanner(System.in);
	static String request_string(String str)
	{
		System.out.println(str);
		return input.nextLine();
	}
}
