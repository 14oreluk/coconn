package KundenVerwaltung;

import javax.swing.JOptionPane;

import KundenVerwaltungGUI.KundenVerwaltungGUI;

public class KundenVerwaltungException extends Exception {
	public KundenVerwaltungException(String message) {
		JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}

}
