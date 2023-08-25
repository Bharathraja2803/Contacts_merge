package contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {
	private static final String LineSeparator = "-".repeat(90);
	private static final String UnderLine = "=".repeat(50);
	public static void main(String[] args) {
		List<Contact> phone = ContactData.getData("phone");
		List<Contact> email = ContactData.getData("email");
		List<Contact> fullList = new ArrayList<>(phone);
		fullList.addAll(email);
		
		System.out.println("Full List --> ArrayList\n"+UnderLine);
		fullList.forEach(System.out::println);
		System.out.println(LineSeparator);
		
		Map<String, Contact> contacts = new HashMap<>();
		for (Contact contact : fullList) {
			contacts.put(contact.getName(), contact);
		}
		
		System.out.println("Map Contacts --> HashMap (Using normal put method)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.clear();
		for (Contact contact : fullList) {
			contacts.putIfAbsent(contact.getName(), contact);
		}
		
		System.out.println("Map Contacts --> HashMap (Using normal putIfAbsent method)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.clear();
		for (Contact contact : fullList) {
			Contact duplicate = contacts.put(contact.getName(), contact);
			if(duplicate != null) {
				contacts.put(contact.getName(), contact.mergeContactData(duplicate));
			}
		}
		
		System.out.println("Map Contacts --> HashMap (Merging the contacts using put method in Map and mergeContactData<User method in Contact class>)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.clear();
		fullList.forEach(contact -> {
			Contact duplicate = contacts.put(contact.getName(), contact);
			if(duplicate !=null) {
				contacts.put(contact.getName(), contact.mergeContactData(duplicate));
			}
		}
		);
		
		System.out.println("Map Contacts --> HashMap (lambda expression and mergeContactData<User method in Contact class>)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.clear();
		fullList.forEach(contact -> contacts.merge(contact.getName(), contact, 
				(previous, current) -> {
					System.out.println("prev: "+ previous+", curr: "+current);
					Contact merge = previous.mergeContactData(current);
					System.out.println("merge: "+ merge);
					return merge;
				}));
		
		System.out.println("Map Contacts --> HashMap (lambda expression and merge method on maps and mergeContactData<User method in Contact class>)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.clear();
		fullList.forEach(contact -> contacts.merge(contact.getName(), contact, 
				(previous, current) -> previous.mergeContactData(current)));
		
		System.out.println("Map Contacts --> HashMap (lambda expression(Single line) and merge method on maps and mergeContactData<User method in Contact class>)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.clear();
		fullList.forEach(contact -> contacts.merge(contact.getName(), contact, Contact::mergeContactData));
		
		System.out.println("Map Contacts --> HashMap (method reference of mergeContactData<User method in Contact class>)\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		
	}

}
