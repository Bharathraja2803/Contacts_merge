package contacts;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Contact {
	private String name;
	private Set<String> emails = new HashSet<>();
	private Set<String> phones = new HashSet<>();
	
	public Contact(String name) {
		this(name, null, 0);
	}
	
	public Contact(String name, String email) {
		this(name, email,0);
	}
	
	public Contact(String name, long phone) {
		this(name, null, phone);
	}
	
	public Contact(String name, String email, long phone) {
		this.name = name;
		if(email != null) {
			this.emails.add(email);
		}
		if(phone !=0) {
			String phoneString = Long.toString(phone);
			this.phones.add("(%s) %s-%s".formatted(phoneString.substring(0, 3), phoneString.substring(3,6),phoneString.substring(6)));			
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLastNameFirstName() {
		return this.name.substring(name.indexOf(" ")+1)+", "+name.substring(0,name.indexOf(" "));
	}
	
	@Override
	public String toString() {
		return "%s: %s %s".formatted(this.name, this.emails, this.phones);
	}
	
	public Contact mergeContactData(Contact contact) {
		Contact newContact = new Contact(this.name);
		newContact.emails = new HashSet<>(this.emails);
		newContact.phones = new HashSet<>(this.phones);
		newContact.emails.addAll(contact.emails);
		newContact.phones.addAll(contact.phones);
		
		return newContact;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(name, other.name);
	}
	
	public void addEmail (String companyName) {
		String email = "%s%s@%s.com".formatted(name.split(" ")[0], name.split(" ")[1].charAt(0), companyName.replaceAll(" ", "").toLowerCase());
		if(!emails.add(email)) {
			System.out.println(name +" already has this email "+ email);
		}else {
			System.out.println(name + " got added a new mail " + email);
		}		
	}
	
	public void replaceEmail(String oldMail, String newMail) {
		if(emails.contains(oldMail)) {
			emails.remove(oldMail);
			emails.add(newMail);
		}else {
			System.out.println("no mails found as "+ oldMail);
		}
	}
}
