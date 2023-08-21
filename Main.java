package contacts;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
	public static void main(String [] args) {
		List<Contact> emails = ContactData.getData("email");
		List<Contact> phones = ContactData.getData("phone");
		printData("Phone List", phones);
		printData("Email List", emails);
		
		Set<Contact> emailSet = new HashSet<>(emails);
		Set<Contact> phoneSet = new HashSet<>(phones);
		printData("Eamil Set", emailSet);
		printData("Phone Set", phoneSet);
		
		System.out.println("-".repeat(30));
		System.out.println("Adding new mail id");
		System.out.println("-".repeat(30));
		int indexOfContact = emails.indexOf(new Contact("Minnie Mouse"));
		Contact minnieMouse = emails.get(indexOfContact);
		minnieMouse.addEmail("Infosys");
		System.out.println(minnieMouse);
		
		
		Set<Contact> unionAB = new HashSet<>(emailSet);
		unionAB.addAll(phoneSet);
		printData("(A \u222A B) Email (A) union Phone (B)", unionAB);
		
		Set<Contact> IntersectAB = new HashSet<>(emailSet);
		IntersectAB.retainAll(phoneSet);
		printData("(A \u2229 B) Email (A) Intersect Phone (B)", IntersectAB);
		
		Set<Contact> IntersectBA = new HashSet<>(phoneSet);
		IntersectBA.retainAll(emailSet);
		printData("(B \\u2229 A) Phone (B) Intersect Email (A)", IntersectBA);
		
		Set<Contact> setDifferenceAB = new HashSet<>(emailSet);
		setDifferenceAB.removeAll(phoneSet);
		printData("(A - B) Email (A) set difference Phone (B)", setDifferenceAB);
		
		Set<Contact> setDifferenceBA = new HashSet<>(phoneSet);
		setDifferenceBA.removeAll(emailSet);
		printData("(B - A) Phone (B) set difference Email (A)", setDifferenceBA);
		
		
	}
	
	public static void printData(String header, Collection<Contact> contacts) {
		System.out.println("-".repeat(30));
		System.out.println(header);
		System.out.println("-".repeat(30));
		contacts.forEach(System.out::println);
	}
}
