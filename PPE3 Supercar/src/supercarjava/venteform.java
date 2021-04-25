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
 * La classe venteform (formulaire de vente) permet à l'utilisateur (vendeur) d'entrer la commande du client et de créer un reçu de cette commande.
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
		JComboBox comboBoxModèle = new JComboBox();

		JComboBox comboBoxMarque = new JComboBox();
		JComboBox comboBoxCouleur = new JComboBox();
		comboBoxMarque.addItemListener(new ItemListener() {
			/**
			 * 
			 * Permet de sélectionner et d'afficher uniquement les modèles et leurs couleurs de la marque de voiture sélectionnée auparavant.
			 * 
			 */
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st4 = conn.prepareStatement("select modèle from voiture where marque=?");
					st4.setString(1, comboBoxMarque.getSelectedItem().toString());
					ArrayList<String> as1 = new ArrayList<>();
					ResultSet x4 = st4.executeQuery();
					while (x4.next())
						as1.add(x4.getString(1));
					as1 = venteform.removeDuplicates(as1);
					PreparedStatement st5 = conn
							.prepareStatement("select couleur from voiture where marque=? and modèle=?");
					st5.setString(1, comboBoxMarque.getSelectedItem().toString());
					st5.setString(2, comboBoxModèle.getSelectedItem().toString());
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBoxModèle.setModel(new DefaultComboBoxModel(as1.toArray()));
					comboBoxCouleur.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ex) {
				}
			}
		});
		comboBoxModèle.addItemListener(new ItemListener() {
			/**
			 * Permet de sélectionner et d'afficher uniquement la couleur de la voiture de la marque et du modèle sélectionné auparavant.
			 * 
			 */
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st5 = conn
							.prepareStatement("select couleur from voiture where marque=? and modèle=?");
					st5.setString(1, comboBoxMarque.getSelectedItem().toString());
					st5.setString(2, comboBoxModèle.getSelectedItem().toString());
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
			 * Permet d'enregistrer les informations saisies depuis le formulaire, dans la base de données.
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

			comboBoxModèle.setBounds(159, 93, 96, 21);
			getContentPane().add(comboBoxModèle);
			ResultSet x = st.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			while (x.next())
				as.add(x.getString(4));
			as = venteform.removeDuplicates(as);
			PreparedStatement st4 = conn.prepareStatement("select modèle from voiture where marque=?");
			st4.setString(1, as.get(0));
			ArrayList<String> as1 = new ArrayList<>();
			ResultSet x4 = st4.executeQuery();
			while (x4.next())
				as1.add(x4.getString(1));
			as1 = venteform.removeDuplicates(as1);
			PreparedStatement st5 = conn.prepareStatement("select couleur from voiture where marque=? and modèle=?");
			st5.setString(1, as.get(0));
			st5.setString(2, as1.get(0));
			ArrayList<String> as2 = new ArrayList<>();
			ResultSet x5 = st5.executeQuery();
			while (x5.next())
				as2.add(x5.getString(1));
			as2 = venteform.removeDuplicates(as2);
			comboBoxMarque.setModel(new DefaultComboBoxModel(as.toArray()));
			comboBoxModèle.setModel(new DefaultComboBoxModel(as1.toArray()));
			comboBoxCouleur.setModel(new DefaultComboBoxModel(as2.toArray()));
			btnConclure.setBorderPainted(false);
			btnConclure.setBackground(new Color(246, 76, 114));

			JLabel lblModèle = new JLabel("Mod\u00E8le");
			lblModèle.setForeground(Color.WHITE);
			lblModèle.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblModèle.setBounds(83, 96, 66, 13);
			getContentPane().add(lblModèle);

			JLabel lblCouleur = new JLabel("Couleur");
			lblCouleur.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCouleur.setForeground(Color.WHITE);
			lblCouleur.setBounds(83, 146, 66, 18);
			getContentPane().add(lblCouleur);
			venteform vf = this;

			btnConclure.addActionListener(new ActionListener() {
				/**
				 * 
				 * Après avoir appuyé sur le bouton "conclure", un fichier pdf sera généré avec les informations contenues dans le formulaire conclu,
				 * et le programme demandera à l'utilisateur de sélectionner le dossier dans lequel il veut enregistrer le fichier.pdf.
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
								.prepareStatement("select id from voiture where marque=? and couleur=? and modèle = ?");
						st.setString(1, comboBoxMarque.getSelectedItem().toString());
						st.setString(2, comboBoxCouleur.getSelectedItem().toString());
						st.setString(3, comboBoxModèle.getSelectedItem().toString());
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

						int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le reçu de vente ?",
								"Reçu de vente", JOptionPane.YES_NO_OPTION);

						if (option == 0) {//les differnet font utilisé de la bibliothèque itexpdf
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
									// Permet d'écrire un gros en-tête
									preface.add(new Paragraph("Reçu de vente", catFont));

									addEmptyLine(preface, 1);
									// Créera: Rapport généré par: _name, _date
									addEmptyLine(preface, 1);
									// Créera: Rapport généré par: _name, _date
									preface.add(new Paragraph(
											"Géneré par  " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
											smallBold));
									addEmptyLine(preface, 3);
									preface.add(new Paragraph("Marque  : " + comboBoxMarque.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(new Paragraph("Modèle  : " + comboBoxModèle.getSelectedItem().toString(),
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
	 * Crée une liste de tableaux et supprime les listes dupliquées.
	 * 
	 */
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

		// crée une nouvelle liste de tableau.
		ArrayList<T> newList = new ArrayList<T>();

		// parcour la première liste.
		for (T element : list) {

			//Si cet élément n'est pas présent dans newList
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
	 * Méthode permettant l'ajout de lignes vides.
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
	 * Permet d'exécuter la classe venteform() quand celle-ci est appelée.
	 * 
	 */
	public static void main(String[] args) {
		venteform vf = new venteform();
		vf.setVisible(true);
	}
}
