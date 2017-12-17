package KundenVerwaltungGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import KundenVerwaltung.Kunde;
import KundenVerwaltung.KundenVerwaltungException;
import KundenVerwaltung.Kundenliste;
import KundenVerwaltung.Termin;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JEditorPane;

public class KundenVerwaltungGUI extends JFrame {

	private Dimension d = new Dimension(400, 400);
	private Kundenliste kl = null;

	private JTable table;
	private JTextField textVorname;
	private JTextField textNachname;
	private JTextField textTel;
	private JTextArea textKommentar;
	private JTextField textSuche;
	private JList<Termin> list;
	private JList kundenList = new JList();

	public static void main(String[] args) {
		new KundenVerwaltungGUI().setVisible(true);
	}

	public KundenVerwaltungGUI() {
		super("Kunden Verwaltung");
		this.setSize(d);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kl = new Kundenliste();
		kundenList.setCellRenderer(new cellRendererKundenListe());
		updateList();

		kundenList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateKundenData();
			}
		});

		JButton btnHinzufuegen = new JButton("Neuer Kunde");
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textVorname != null || textNachname != null && textVorname.getText().length() > 0
						|| textNachname.getText().length() > 0)
					kl.setNewKunden(new Kunde("", "", "", "", null));
				else
					try {
						throw new KundenVerwaltungException("Geben Sie dem Kunden einen Nahmen.");
					} catch (KundenVerwaltungException e) {
					}

				updateList();
			}
		});
		getContentPane().setLayout(new MigLayout("",
				"[123px][20px][123px][19px][124px][616px][80px][19px][122px][20px][82px]", "[34px][617px]"));
		getContentPane().add(btnHinzufuegen, "cell 0 0,grow");

		JButton btnLoeschen = new JButton("Kunde Entfernen");
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kl.removeKunde(kundenList.getSelectedIndex());
				updateList();
			}
		});
		getContentPane().add(btnLoeschen, "cell 2 0,grow");

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveKundenData();
				updateList();

			}
		});
		getContentPane().add(btnSpeichern, "cell 4 0,grow");

		JLabel lblNewLabel = new JLabel("Kunde Suchen:");
		getContentPane().add(lblNewLabel, "cell 5 0,alignx right,aligny center");

		JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, "cell 0 1 11 1,grow");

		JPanel panelKunde = new JPanel();
		splitPane.setRightComponent(panelKunde);
		panelKunde.setLayout(new MigLayout("", "[1131px]", "[182px][400px]"));

		JPanel panelTLoeschen = new JPanel();
		panelTLoeschen.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelKunde.add(panelTLoeschen, "cell 0 1,grow");
		panelTLoeschen.setLayout(new MigLayout("", "[89px][10px][89px][10px][913px]", "[20px][23px][319px]"));

		JLabel lblNewLabel_1 = new JLabel("Termine");
		panelTLoeschen.add(lblNewLabel_1, "cell 0 0 3 1,alignx left,growy");

		JButton btnTHinzufgen = new JButton("hinzuf\u00FCgen");
		btnTHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (kundenList.getSelectedIndex() >= 0) {
					Kunde k = kl.getKunden().get(kundenList.getSelectedIndex());
					TerminPlanerGUI tp = new TerminPlanerGUI(k);
					tp.setVisible(true);
					updateTerminList();

				} else
					try {
						throw new KundenVerwaltungException("Bitte Wählen Sie vorher einen Kunden aus.");
					} catch (KundenVerwaltungException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});
		panelTLoeschen.add(btnTHinzufgen, "cell 0 1,grow");

		JButton btnTerminLschen = new JButton("l\u00F6schen");
		btnTerminLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kunde k = kl.getKunden().get(kundenList.getSelectedIndex());
				if(list.getSelectedIndex()>-1&&k.getTermine().size()>0) 
					k.getTermine().remove(list.getSelectedIndex());
				else
					try {
						throw new KundenVerwaltungException("Wählen Sie zuerst ein Element aus.");
					} catch (KundenVerwaltungException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 updateTerminList();
			}
		});
		panelTLoeschen.add(btnTerminLschen, "cell 2 1,grow");

		JPanel panelTermine_1 = new JPanel();
		panelTermine_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTLoeschen.add(panelTermine_1, "cell 0 2 5 1,grow");
		panelTermine_1.setLayout(new MigLayout("", "[1097px,grow]", "[300px,grow]"));

		list = new JList();
		list.setCellRenderer(new cellRendererTerminListe());
		panelTermine_1.add(list, "cell 0 0,grow");

		JPanel panelPDaten = new JPanel();
		panelPDaten.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelKunde.add(panelPDaten, "cell 0 0,grow");
		panelPDaten.setLayout(new MigLayout("", "[106px][26px][111px][833px]", "[14px][135px]"));

		JLabel lblPersonendaten = new JLabel("Personendaten");
		panelPDaten.add(lblPersonendaten, "cell 0 0,growx,aligny top");

		JPanel panelPDaten_1 = new JPanel();
		panelPDaten_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPDaten.add(panelPDaten_1, "cell 0 1,alignx left,growy");
		panelPDaten_1.setLayout(new MigLayout("", "[95px]", "[20px][20px][][20px][][20px][]"));

		JLabel lblVorname = new JLabel("Vorname");
		panelPDaten_1.add(lblVorname, "cell 0 0,grow");

		JLabel lblNachname = new JLabel("Nachname");
		panelPDaten_1.add(lblNachname, "cell 0 2,grow");

		JLabel lblTel = new JLabel("Telefon:");
		panelPDaten_1.add(lblTel, "cell 0 4,grow");

		JLabel lblAltersgruppe = new JLabel("Altersgruppe:");
		panelPDaten_1.add(lblAltersgruppe, "cell 0 6,grow");

		ButtonGroup buttonGroup = new ButtonGroup();

		JPanel panelPDaten_2 = new JPanel();
		panelPDaten_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPDaten.add(panelPDaten_2, "cell 2 1 2 1,grow");
		panelPDaten_2.setLayout(new MigLayout("", "[70px][2px][70px][2px][106px][70px][443px,grow]",
				"[20px,grow][11px][20px][11px][20px][7px][26px]"));

		textVorname = new JTextField();
		panelPDaten_2.add(textVorname, "cell 0 0 5 1,growx,aligny top");
		textVorname.setColumns(10);

		textKommentar = new JTextArea();
		panelPDaten_2.add(textKommentar, "cell 6 0 1 7,grow");

		textNachname = new JTextField();
		textNachname.setColumns(10);
		panelPDaten_2.add(textNachname, "cell 0 2 5 1,growx,aligny top");

		textTel = new JTextField();
		textTel.setColumns(10);
		panelPDaten_2.add(textTel, "cell 0 4 5 1,growx,aligny top");

		JRadioButton rdbtnKind = new JRadioButton("Kind");
		panelPDaten_2.add(rdbtnKind, "cell 0 6,growx,aligny top");

		JRadioButton rdbtnErwachsen = new JRadioButton("Erwachsen");
		panelPDaten_2.add(rdbtnErwachsen, "cell 2 6,growx,aligny top");

		JRadioButton rdbtnRentner = new JRadioButton("Rentner");
		panelPDaten_2.add(rdbtnRentner, "cell 4 6,growx,aligny top");

		buttonGroup.add(rdbtnKind);
		buttonGroup.add(rdbtnErwachsen);
		buttonGroup.add(rdbtnRentner);

		JLabel lblKommentar = new JLabel("Kommentar");
		panelPDaten_2.add(lblKommentar, "cell 5 0,grow");

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		updateList();
		scrollPane.setViewportView(kundenList);

		textSuche = new JTextField();
		getContentPane().add(textSuche, "cell 6 0 4 1,growx,aligny center");
		textSuche.setColumns(10);

		JButton btnSuche = new JButton("Suchen");
		btnSuche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kundenList.setSelectedIndex(sucheKunde(textSuche.getText()));
				updateList();
				updateKundenData();
			}

			private int sucheKunde(String fullname) {
				System.out.println("suche");
				for (int i = 0; i < kl.getKunden().size(); i++) {
					Kunde k = kl.getKunden().get(i);
					System.out.println(k.getFullName().toLowerCase() + " ->" + fullname);
					if (k.getFullName().toLowerCase().startsWith(fullname.toLowerCase())) {
						return kl.getKunden().indexOf(k);
					}
				}
				return -1;
			}
		});
		getContentPane().add(btnSuche, "cell 10 0,growx,aligny center");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Optionen");
		menuBar.add(mnFile);

		JMenuItem mntmLayoutAnpassen = new JMenuItem("Layout anpassen");
		mnFile.add(mntmLayoutAnpassen);

		JMenuItem mntmListe = new JMenuItem("Liste importieren");
		mnFile.add(mntmListe);

		JMenuItem mntmBackup = new JMenuItem("Backup");
		mnFile.add(mntmBackup);

		JMenuItem mntmDrucken = new JMenuItem("Drucken");
		mnFile.add(mntmDrucken);

		JMenuItem mntmAllesZurcksetzen = new JMenuItem("Alles Zur\u00FCcksetzen");
		mnFile.add(mntmAllesZurcksetzen);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnFile.add(mntmBeenden);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmPatchnots = new JMenuItem("Patchnotes");
		mnHelp.add(mntmPatchnots);

		JMenuItem mntmInfos = new JMenuItem("Infos");
		mnHelp.add(mntmInfos);

		kl = new Kundenliste();
		repaint();
	}

	private void saveKundenData() {
		if (kundenList.getSelectedIndex() >= 0) {
			if (kl.getKunden().size() > 0) {
				Kunde k = kl.getKunden().get(kundenList.getSelectedIndex());
				k.setFirstName(textVorname.getText());
				k.setLastName(textNachname.getText());
				k.setGebDatum(textTel.getText());
				k.setComment(textKommentar.getText());
			}
		}
	}

	private void updateKundenData() {
		if (kundenList.getSelectedIndex() >= 0) {
			clearAll();
			if (kl.getKunden().size() > 0) {
				Kunde k = kl.getKunden().get(kundenList.getSelectedIndex());
				if (k.getFirstName() != null)
					textVorname.setText(k.getFirstName());
				if (k.getLastName() != null)
					textNachname.setText(k.getLastName());
				if (k.getGebDatum() != null)
					textTel.setText(k.getGebDatum().toString());
				if (k.getComment() != null)
					textKommentar.setText(k.getComment());
				 updateTerminList();
			}
		}
	}

	private void updateTerminList() {
		Kunde k = kl.getKunden().get(kundenList.getSelectedIndex());
		if (k.getTermine().size() > 0) {
			DefaultListModel<Termin> replacement = new DefaultListModel<Termin>();
			for (Termin s : k.getTermine()) {
				replacement.addElement(s);
			}
			list.setModel(replacement);
		}
		list.updateUI();
	}

	private void updateList() {
		if (kl.getKunden().size() > 0) {

			DefaultListModel<Kunde> replacement = new DefaultListModel<Kunde>();
			for (Kunde s : kl.getKunden()) {
				replacement.addElement(s);
			}
			kundenList.setModel(replacement);

		}
	}

	public class cellRendererKundenListe extends JLabel implements ListCellRenderer<Kunde> {
		@Override
		public Component getListCellRendererComponent(JList<? extends Kunde> list, Kunde kunde, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(kunde.getFirstName() + " " + kunde.getLastName());

			return this;
		}

	}

	public class cellRendererTerminListe extends JLabel implements ListCellRenderer<Termin> {
		@Override
		public Component getListCellRendererComponent(JList<? extends Termin> list, Termin termin, int index,
				boolean isSelected, boolean cellHasFocus) {

			setText(termin.getTerminDate().toLocaleString() + " Uhr");

			return this;
		}

	}

	private void clearAll() {
		textVorname.setText("");
		textNachname.setText("");
		textTel.setText("");
		textKommentar.setText("");
		textSuche.setText("");
		list.removeAll();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
