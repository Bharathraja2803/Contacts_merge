package contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactData {
	
	private static final String phoneData = """
			Charlie Brown, 3334445555
			Maid Marion, 1234567890
			Mickey Mouse, 9998887777
			Mickey Mouse, 1247489758
			Minnie Mouse, 4567805666
			Robin Hood, 5647893000
			Robin Hood, 7899028222
			Lucy Van Pelt, 5642086852
			Mickey Mouse, 9998887777
			""";
	
	private static final String emailData = """
			Mickey Mouse, mckmouse@gmail.com
			Mickey Mouse, micky1@aws.com
			Minnie Mouse, minnie@verizon.net
			Robin Hood, rhood@gmail.com
			Linus Van Pelt, lvpelt2015@gmail.com
			Daffy Duck, daffy@google.com
			""";
	
	public static List<Contact> getData(String type){
		List<Contact> dataList = new ArrayList<>();
		String data = type.equals("phone") ? phoneData : type.equals("email") ? emailData : null;
		
		if(data == null) {
			return null;
		}
		
		Scanner scanner = new Scanner(data);
		while (scanner.hasNext()) {
			String [] line = scanner.nextLine().split(",");
			Arrays.asList(line).replaceAll(String::trim); // for trimming the extra space we are converting the array to list and using the method reference to call trim method in String class
			if(type.equals("phone")) {
				dataList.add(new Contact(line[0], Long.parseLong(line[1])));
			}else if(type.equals("email")) {
				dataList.add(new Contact(line[0], line[1]));
			}
		}
		return dataList; 
	}
}
