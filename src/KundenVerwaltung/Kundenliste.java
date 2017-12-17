package KundenVerwaltung;

import java.util.ArrayList;

public class Kundenliste extends Thread {

	private ArrayList<Kunde> kunden = null;

	public Kundenliste() {
		// suche eine Kundenliste und hole sie in kunden andernfals erstelle eine leere
		// kundenliste
		// if() {
		//
		// }else {
		kunden = new ArrayList<Kunde>();
		kunden.add(new Kunde("Klaus", "Kellermann", "15.11.1998", "Ich heise nicht manni",Kunde.Age.ERWACHSEN));
		kunden.add(new Kunde("Hans", "Kellermann", "04.12.1979", "ich bin nicht kurt",Kunde.Age.RENTNER));
		kunden.add(new Kunde("Schorsch", "Kellermann", "29.05.1934", "ich mag dih nicht",Kunde.Age.KIND));
		kunden.add(new Kunde("Freddy", "Kellermann", "09.01.1986", "du bist dumm",Kunde.Age.ERWACHSEN));
		kunden.add(new Kunde("Manni", "Kellermann", "10.05.1967", "ich bins",Kunde.Age.KIND));
		kunden.add(new Kunde("Kurt", "Kellermann", "06.03.2008", "ich bin groﬂ",Kunde.Age.RENTNER));

		// }

		start();
	}

	public void run() {
		// sort the list

	}

	public String[] getKundenNamen() {
		String[] ret = new String[kunden.size()];
		int i = 0;
		for (Kunde k : kunden) {
			ret[i] = k.getFirstName() + " " + k.getLastName() + "      ";
			i++;
		}
		return ret;
	}

	public ArrayList<Kunde> getKunden() {
		return kunden;
	}

	public void setNewKunden(Kunde k) {
		kunden.add(k);
	}

	public void removeKunde(int index) {
		if (kunden.size() == 1)
			kunden.get(0).setBlank();
		else if (index < kunden.size() && index > 0)
			kunden.remove(index);
	}

	public void updateKunde(Kunde k, String firstName, String lastName, String gebDatum, String comment) {
		for (Kunde kunde : kunden) {
			if (kunde.equals(k)) {
				kunde.setFirstName(firstName);
				kunde.setLastName(lastName);
				kunde.setComment(comment);
				kunde.setGebDatum(gebDatum);
			}
		}

	}

}
