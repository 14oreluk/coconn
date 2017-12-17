package KundenVerwaltungGUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JDialog;
import com.toedter.calendar.JDateChooser;

import KundenVerwaltung.Kunde;
import KundenVerwaltung.KundenVerwaltungException;
import KundenVerwaltung.Termin;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TerminPlanerGUI extends JDialog {

	Dimension d = new Dimension(300, 200);
	JDateChooser dateChooser;
	JTextArea textArea;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnNewButton = new JButton("Berst\u00E4tigen");

	public TerminPlanerGUI(Kunde k) {
		this.setTitle("Termin");
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(315, 235));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					k.addTermin(new Termin(getDate()));
					dispose();
				} catch (KundenVerwaltungException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		

		getContentPane().setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(92, 7, 180, 20);
		getContentPane().add(dateChooser);

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setBounds(7, 7, 75, 14);
		getContentPane().add(lblDatum);

		JLabel lblUhrzeit = new JLabel("Uhrzeit");
		lblUhrzeit.setBounds(7, 38, 75, 14);
		getContentPane().add(lblUhrzeit);

		textField = new JTextField();
		textField.setBounds(92, 35, 46, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel(":");
		label.setBounds(144, 38, 19, 14);
		getContentPane().add(label);

		textField_1 = new JTextField();
		textField_1.setBounds(154, 35, 46, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Kommentar");
		lblNewLabel.setBounds(7, 63, 75, 14);
		getContentPane().add(lblNewLabel);

		textArea = new JTextArea();
		textArea.setBounds(92, 59, 207, 107);
		getContentPane().add(textArea);

		btnNewButton.setBounds(199, 172, 100, 23);
		getContentPane().add(btnNewButton);
	}

	public Date getDate() {
		Date d = dateChooser.getDate();
		int hour=0;
		int min=0;
		if(isNumberic(textField.getText()))
			hour=Integer.parseInt(textField.getText());
		if(isNumberic(textField_1.getText()))
			min=Integer.parseInt(textField_1.getText());
		Date ret = d;
		ret.setHours(hour);
		ret.setMinutes(min);
		return ret;
	}

	private boolean isNumberic(String s) {
		boolean ret = true;
		for (int i = 0; i < s.length(); i++) {
			if ((int) s.charAt(i) < 49 || (int) s.charAt(i) > 57) {
				ret = false;
				return ret;
			}
		}
		return ret;
	}

	public String getComment() {
		return textArea.getText();
	}
}
