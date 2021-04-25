package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ItemEvent;
/**
 * La classe venteform (formulaire de vente) permet � l'utilisateur (vendeur) d'entrer la commande du client et de cr�er un re�u de cette commande.
 * 
 */
public class venteform extends JFrame {
	private JTextField textFieldMontant;
	static Connection conn = null;
	static Statement st = null;
	/**
	 * Initialise le contenu de la classe venteform().
	 */
	public venteform() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 438, 347);
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setForeground(Color.WHITE);
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMarque.setBounds(83, 46, 66, 13);
		getContentPane().add(lblMarque);

		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setForeground(Color.WHITE);
		lblMontant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMontant.setBounds(83, 189, 66, 19);
		getContentPane().add(lblMontant);

		textFieldMontant = new JTextField();
		textFieldMontant.setBounds(159, 191, 96, 19);
		getContentPane().add(textFieldMontant);
		textFieldMontant.setColumns(10);
		JComboBox comboBoxMod�le = new JComboBox();

		JComboBox comboBoxMarque = new JComboBox();
		JComboBox comboBoxCouleur = new JComboBox();
		comboBoxMarque.addItemListener(new ItemListener() {
			/**
			 * 
			 * Permet de s�lectionner et d'afficher uniquement les mod�les et leurs couleurs de la marque de voiture s�lectionn�e auparavant.
			 * 
			 */
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st4 = conn.prepareStatement("select mod�le from voiture where marque=?");
					st4.setString(1, comboBoxMarque.getSelectedItem().toString());
					ArrayList<String> as1 = new ArrayList<>();
					ResultSet x4 = st4.executeQuery();
					while (x4.next())
						as1.add(x4.getString(1));
					as1 = venteform.removeDuplicates(as1);
					PreparedStatement st5 = conn
							.prepareStatement("select couleur from voiture where marque=? and mod�le=?");
					st5.setString(1, comboBoxMarque.getSelectedItem().toString());
					st5.setString(2, comboBoxMod�le.getSelectedItem().toString());
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBoxMod�le.setModel(new DefaultComboBoxModel(as1.toArray()));
					comboBoxCouleur.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ex) {
				}
			}
		});
		comboBoxMod�le.addItemListener(new ItemListener() {
			/**
			 * Permet de s�lectionner et d'afficher uniquement la couleur de la voiture de la marque et du mod�le s�lectionn� auparavant.
			 * 
			 */
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st5 = conn
							.prepareStatement("select couleur from voiture where marque=? and mod�le=?");
					st5.setString(1, comboBoxMarque.getSelectedItem().toString());
					st5.setString(2, comboBoxMod�le.getSelectedItem().toString());
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBoxCouleur.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ed) {
				}
			}
		});
		comboBoxMarque.setBounds(159, 43, 96, 21);
		getContentPane().add(comboBoxMarque);
		comboBoxCouleur.setBounds(159, 143, 96, 21);
		getContentPane().add(comboBoxCouleur);
		JButton btnConclure = new JButton("Conclure");
		btnConclure.addActionListener(new ActionListener() {
			/**
			 * 
			 * Permet d'enregistrer les informations saisies depuis le formulaire, dans la base de donn�es.
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConclure.setBounds(135, 242, 107, 21);
		getContentPane().add(btnConclure);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
			PreparedStatement st = conn.prepareStatement("select * from voiture");

			comboBoxMod�le.setBounds(159, 93, 96, 21);
			getContentPane().add(comboBoxMod�le);
			ResultSet x = st.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			while (x.next())
				as.add(x.getString(4));
			as = venteform.removeDuplicates(as);
			PreparedStatement st4 = conn.prepareStatement("select mod�le from voiture where marque=?");
			st4.setString(1, as.get(0));
			ArrayList<String> as1 = new ArrayList<>();
			ResultSet x4 = st4.executeQuery();
			while (x4.next())
				as1.add(x4.getString(1));
			as1 = venteform.removeDuplicates(as1);
			PreparedStatement st5 = conn.prepareStatement("select couleur from voiture where marque=? and mod�le=?");
			st5.setString(1, as.get(0));
			st5.setString(2, as1.get(0));
			ArrayList<String> as2 = new ArrayList<>();
			ResultSet x5 = st5.executeQuery();
			while (x5.next())
				as2.add(x5.getString(1));
			as2 = venteform.removeDuplicates(as2);
			comboBoxMarque.setModel(new DefaultComboBoxModel(as.toArray()));
			comboBoxMod�le.setModel(new DefaultComboBoxModel(as1.toArray()));
			comboBoxCouleur.setModel(new DefaultComboBoxModel(as2.toArray()));
			btnConclure.setBorderPainted(false);
			btnConclure.setBackground(new Color(246, 76, 114));

			JLabel lblMod�le = new JLabel("Mod\u00E8le");
			lblMod�le.setForeground(Color.WHITE);
			lblMod�le.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMod�le.setBounds(83, 96, 66, 13);
			getContentPane().add(lblMod�le);

			JLabel lblCouleur = new JLabel("Couleur");
			lblCouleur.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCouleur.setForeground(Color.WHITE);
			lblCouleur.setBounds(83, 146, 66, 18);
			getContentPane().add(lblCouleur);
			venteform vf = this;

			btnConclure.addActionListener(new ActionListener() {
				/**
				 * 
				 * Apr�s avoir appuy� sur le bouton "conclure", un fichier pdf sera g�n�r� avec les informations contenues dans le formulaire conclu,
				 * et le programme demandera � l'utilisateur de s�lectionner le dossier dans lequel il veut enregistrer le fichier.pdf.
				 * 
				 */
				public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						System.out.println("error");
					}
					try {
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
						PreparedStatement st = conn
								.prepareStatement("select id from voiture where marque=? and couleur=? and mod�le = ?");
						st.setString(1, comboBoxMarque.getSelectedItem().toString());
						st.setString(2, comboBoxCouleur.getSelectedItem().toString());
						st.setString(3, comboBoxMod�le.getSelectedItem().toString());
						ResultSet x = st.executeQuery();
						int id1 = 0;
						while (x.next()) {
							id1 = x.getInt(1);
						}
						PreparedStatement st3 = conn.prepareStatement(
								"insert into ventes(id_vendeur,confirmed,Montant,id_voiture,date) values(?,?,?,?,?)");
						PreparedStatement st4 = conn.prepareStatement("select id from vendeur where email=?");
						st4.setString(1, app.email);
						int id = 0;
						ResultSet s = st4.executeQuery();
						while (s.next()) {
							id = s.getInt(1);
						}
						st3.setInt(1, id);
						st3.setBoolean(2, false);
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						java.util.Date dt = new java.util.Date();
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String currentTime = sdf.format(dt);
						st3.setString(5, currentTime);
						st3.setInt(4, id1);
						st3.setString(3, textFieldMontant.getText());
						st3.execute();

						int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le re�u de vente ?",
								"Re�u de vente", JOptionPane.YES_NO_OPTION);

						if (option == 0) {//les differnet font utilis� de la biblioth�que itexpdf
							com.itextpdf.text.Font catFont = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
							com.itextpdf.text.Font redFont = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.NORMAL,
									BaseColor.RED);
							com.itextpdf.text.Font subFont = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
							com.itextpdf.text.Font smallBold = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

							Document document = new Document();
							JFileChooser fileChooser = new JFileChooser();
							if (fileChooser.showSaveDialog(vf) == JFileChooser.APPROVE_OPTION) {
								File file = fileChooser.getSelectedFile();
								try {
									PdfWriter.getInstance(document,
											new FileOutputStream(file.getAbsoluteFile() + ".pdf"));
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								document.open();
								try {

									Paragraph preface = new Paragraph();
									// Nous ajoutons une ligne vide
									addEmptyLine(preface, 1);
									// Permet d'�crire un gros en-t�te
									preface.add(new Paragraph("Re�u de vente", catFont));

									addEmptyLine(preface, 1);
									// Cr�era: Rapport g�n�r� par: _name, _date
									addEmptyLine(preface, 1);
									// Cr�era: Rapport g�n�r� par: _name, _date
									preface.add(new Paragraph(
											"G�ner� par  " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
											smallBold));
									addEmptyLine(preface, 3);
									preface.add(new Paragraph("Marque  : " + comboBoxMarque.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(new Paragraph("Mod�le  : " + comboBoxMod�le.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(new Paragraph("Couleur  : " + comboBoxCouleur.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(
											new Paragraph("Montant  : " + textFieldMontant.getText() + " Rs ", smallBold));
									addEmptyLine(preface, 8);

									document.add(preface);
									// Commencer une nouvelle page
								} catch (DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								document.close();
							}
						} else {
							System.out.print("no");
						}
					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}

				}
			});
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
	}
	/**
	 * Cr�e une liste de tableaux et supprime les listes dupliqu�es.
	 * 
	 */
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

		// cr�e une nouvelle liste de tableau.
		ArrayList<T> newList = new ArrayList<T>();

		// parcour la premi�re liste.
		for (T element : list) {

			//Si cet �l�ment n'est pas pr�sent dans newList
			// ajoutez-le
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		// retourne(affiche) la nouvelle liste.
		return newList;
	}
	
	/**
	 * 
	 * M�thode permettant l'ajout de lignes vides.
	 * 
	 */
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	/***
	 * 
	 * 
	 * Permet d'ex�cuter la classe venteform() quand celle-ci est appel�e.
	 * 
	 */
	public static void main(String[] args) {
		venteform vf = new venteform();
		vf.setVisible(true);
	}
}
