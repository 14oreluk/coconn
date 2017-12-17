package KundenVerwaltung;

import java.util.Date;

public class Termin {
	private String terminStart = null;
	private Date terminDate = null;
	private double time = 0;

	public Termin(Date d) throws KundenVerwaltungException {
		if (d != null && d.after(new Date())) {
			this.setTerminDate(d);
		} else {
			throw new KundenVerwaltungException(
					"Das Eingegebene Datum ist bereits vergangen. Bitte wählen Sie ein gültiges Datum.");
		}
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}



	public Date getTerminDate() {
		return terminDate;
	}



	public void setTerminDate(Date terminDate) {
		this.terminDate = terminDate;
	}



	



	public void setTerminStart(String terminStart) {
		this.terminStart = terminStart;
	}

	

}
