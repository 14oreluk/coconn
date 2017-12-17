package KundenVerwaltung;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;

public class Kunde {
	private String firstName = null;
	private String lastName = null;
	private String telNumber = null;
	private String comment = null;
	private Age age = null;
	private ArrayList<Termin> termine = new ArrayList<Termin>();

	public enum Age {
		KIND, ERWACHSEN, RENTNER
	}

	public Kunde(String firstName, String lastName, String geb, String comment, Age age ) {
		if (firstName != null && firstName.length() > 0) {
			this.setFirstName(firstName.trim());
		}
		if (lastName != null && lastName.length() > 0) {
			this.setLastName(lastName.trim());
		}
		if (geb != null && geb.length() > 0) {
			this.telNumber = geb;
		}
		if (comment != null && comment.length() > 0) {
			this.setComment(comment);
		}
		if (age==Age.KIND||age==Age.ERWACHSEN ||age==Age.RENTNER) {
			this.setAge(age);
		}
	}
	
	public void addTermin(Termin t) {
		termine.add(t);
		System.out.println("do sein  mir");
		for (Termin termin : termine) {
			System.out.println(termin);
		}
	}
	
	public String getFullName() {
		return firstName+" "+lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<Termin> getTermine() {
		return termine;
	}

	public void setTermine(ArrayList<Termin> termine) {
		this.termine = termine;
	}

	public void setGebDatum(String gebDatum) {
		this.telNumber = gebDatum;
	}

	public String getGebDatum() {
		return telNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setBlank() {
		this.setFirstName("");
		this.setLastName("");
		this.setGebDatum("");
		this.setComment("");
	}

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}
}
