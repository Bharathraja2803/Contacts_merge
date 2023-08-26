package contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MapViewMain {
	
	public static void main(String[] args) {
		Map<String, Contact> contacts = new HashMap<>(); 
		ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
		ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
		
		printHeadAndDesc("Contacts HashMap", "Normal contacts hash map");
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		Set<String> keyView = contacts.keySet();
		printHeadAndDesc("Key view", "Direct keyview is refering to the key in the contacts HashMap.\nKey view is backed by contacts HashMap.\nIf we modify the keyview set which will also modify data in the contacts HashMap.\nThe same if we modify some thing in the contacts hash map which well be reflected in the keyview as well.");
		System.out.println("Key view : "+keyView);
		
		Set<String> copyKeyView = new TreeSet<>(contacts.keySet());
		printHeadAndDesc("Copy of Key value", "Copy of keyview is not directly pointing to the contacts HashMap.\nIf we change something in the copy keyview which is not reflected in the contacts HashMap and viceversa");
		System.out.println("Copy Key value"+copyKeyView);
		
		keyView.remove("Minnie Mouse");
		printHeadAndDesc("After removing \"Minnie Mouse\" in key view ", "It will remove \"Minnie Mouse\" in both key view and contacts HashMap");
		System.out.println("Key view : "+keyView);
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		copyKeyView.remove("Maid Marion");
		printHeadAndDesc("After removing \"Maid Marion\" in copy key view ", "It will remove \"Maid Marion\" in only copy key view and contacts HashMap remains unchanged");
		System.out.println("Copy Key view : "+copyKeyView);
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		keyView.retainAll(List.of("Robin Hood","Daffy Duck","Charlie Brown","Linus Van Pelt"));
		printHeadAndDesc("After retaining only few values", "This will retain only the \n\"Robin Hood\", \"Daffy Duck\", \"Charlie Brown\",\"Linus Van Pelt\" \n the above values in both key value and contacts HashMap \nRest all get removed in both");
		System.out.println("Key view : "+keyView);
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		keyView.clear();
		printHeadAndDesc("After clearing key view", "Both the data in the key view and contacts HashMap got cleared");
		System.out.println("Key view : "+keyView);
		System.out.println("Contacts : "+contacts);
		
		ContactData.getData("phone").forEach(c -> contacts.put(c.getName(), c));
		ContactData.getData("email").forEach(c -> contacts.put(c.getName(), c));
		Collection<Contact> values = contacts.values();
		printHeadAndDesc("Values collection", "It acts as the values view for the contacts HashMap");
		values.forEach(System.out::println);
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		values.retainAll(ContactData.getData("email"));
		printHeadAndDesc("After using retain all on values", "It will retain only the contacts in the ContactData email in both values and contacts HashMap");
		values.forEach(System.out::println);
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		printHeadAndDesc("Limitations in both keySet() and values()", """
				We unable to use add and addAll methods in both of the outputs returned by the
					keySet() and values() methods in HashMap
				""");
		
		List<Contact> list = new ArrayList<>(values);
		list.sort(Comparator.comparing(Contact::getLastNameFirstName));
		printHeadAndDesc("After sorting it with its lastName", "adding one new mothod in contact that returns lastName, firstName of contacts\nSo sorting according to that and displaying the same");
		list.forEach(s -> System.out.println(s.getLastNameFirstName()+" -> "+s));
		
		Contact first = list.get(0);
		contacts.put(first.getLastNameFirstName(), first);
		printHeadAndDesc("After adding same value with diffrent key", "By giving getLastNameFirstName return as a key and the same values is added into the contacts HashMap");
		values.forEach(System.out::println);
		contacts.forEach((k,v) -> System.out.println("key: "+k+", value: "+v));
		
		Set<Contact> valueSet = new HashSet<>(values);
		if(valueSet.size() < contacts.size()) {
			System.out.println("There is a duplicate value found in contact");
		}else {
			System.out.println("There is no duplicate values in contacts");
		}
		
		var nodes = contacts.entrySet();
		for (var node: nodes) {
			if(!node.getKey().equals(node.getValue().getName())) {
				System.out.println("Key doesn't match with contact name -> "+node.getKey()+":"+node.getValue().getName());
			}
		}
	}
	
	public static void printHeadAndDesc(String header, String desc) {
		String lineSeparator = "-".repeat(90);
		String underLine = "=".repeat(header.length());
		System.out.println("%1$s%n%2$s%n%3$s%nDesc:%4$s%n%1$s".formatted(lineSeparator, header, underLine, desc));
	}
	
}
