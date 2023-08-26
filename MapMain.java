package contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthOptionPaneUI;

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
		
		for(String names : new String[] {"Minnie Mouse","Robin Hood","Jackson Durai"}) {
			contacts.computeIfAbsent(names, k-> new Contact(names));
		}
		
		System.out.println("Map Contacts -> computeIfAbsent method\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		for(String names : new String[] {"Minnie Mouse","Robin Hood","Jackson Durai"}) {
			contacts.computeIfPresent(names, (k,v)-> {v.addEmail("RKB Softwares"); return v;});
		}
		
		System.out.println("Map Contacts -> computeIfPresent method\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		contacts.replaceAll((k,v) -> {
			String newMail = k.replaceAll(" ", "").toLowerCase() + "@rkbsoftwares.com";
			v.replaceEmail("daffy@google.com", newMail);
			return v;
		});
		
		System.out.println("Map Contacts -> replaceAll method\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		Contact newDaffy = new Contact("Daffy Jane Duck","daffy.j@duck.com");
		Contact replacedContact = contacts.replace("Daffy Duck", newDaffy);
		System.out.println("new Daffy: "+newDaffy);
		System.out.println("replaced Conatct: "+replacedContact);
		
		System.out.println("Map Contacts -> replace method\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		Contact updatedDaffy = replacedContact.mergeContactData(newDaffy);
		System.out.println("updated daffy: "+ updatedDaffy);
		boolean isSuccess = contacts.replace("Daffy Duck", newDaffy, updatedDaffy);
		if(isSuccess) {
			System.out.println("Replace sucessfull");
		}else {
            System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", replacedContact));
		}
		
		System.out.println("Map Contacts -> replace(boolean return) method\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
		
		isSuccess = contacts.remove("Daffy Duck", newDaffy);
		if(isSuccess) {
			System.out.println("Replace sucessfull");
		}else {
            System.out.printf("Did not match on both key: %s and value: %s %n".formatted("Daisy Duck", newDaffy));
		}
		
		System.out.println("Map Contacts -> remove(boolean return) method\n"+UnderLine);
		contacts.forEach((k,v) -> System.out.println("Key = "+k+", Value = "+v));
		System.out.println(LineSeparator);
	}

}
