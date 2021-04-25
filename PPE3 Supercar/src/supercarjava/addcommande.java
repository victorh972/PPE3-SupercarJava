package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
/**
 * Permet à l'Administration d'ajouter une commande de voitures pour les entrepôts.
 *
 */
public class addcommande extends JFrame {
	private JTextField textFieldPrix;
	static Connection conn = null;
	static Statement st = null;
	private JTextField textFieldQuantité;
	/**
	 * Initialisation du contenu de la fenêtre addcommande.
	 *
	 */
	public addcommande() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setSize(500, 400);
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setForeground(Color.WHITE);
		lblPrix.setBackground(Color.WHITE);
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrix.setBounds(106, 82, 45, 13);
		getContentPane().add(lblPrix);
		addcommande ad = this;
		JLabel lblVoiture = new JLabel("Voiture");
		lblVoiture.setForeground(Color.WHITE);
		lblVoiture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVoiture.setBounds(106, 159, 45, 16);
		getContentPane().add(lblVoiture);
		textFieldPrix = new JTextField();
		textFieldPrix.setBounds(194, 79, 96, 19);
		getContentPane().add(textFieldPrix);
		textFieldPrix.setColumns(10);
		JComboBox comboBoxFournissseur = new JComboBox();
		comboBoxFournissseur.setBounds(194, 204, 96, 21);
		getContentPane().add(comboBoxFournissseur);

		JLabel lblFournisseur = new JLabel("Fournisseur");
		lblFournisseur.setForeground(Color.WHITE);
		lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFournisseur.setBounds(106, 208, 78, 13);
		getContentPane().add(lblFournisseur);

		JComboBox comboBoxVoiture = new JComboBox();
		comboBoxVoiture.setBounds(194, 157, 96, 21);
		getContentPane().add(comboBoxVoiture);

		JButton btnAjouterCommande = new JButton("Ajouter commande");
		btnAjouterCommande.setBackground(new Color(246, 76, 114));
		btnAjouterCommande.setBorderPainted(false);
		btnAjouterCommande.setBounds(178, 268, 139, 21);
		getContentPane().add(btnAjouterCommande);
		
		JButton btnNouvelleVoiture = new JButton("+");//permet d'ajouter une nouvelle voiture dans la base de donnée
		btnNouvelleVoiture.setBackground(new Color(153, 115, 142));
		btnNouvelleVoiture.setBorderPainted(false);
		btnNouvelleVoiture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcar adc = new addcar();
				adc.setVisible(true);
				ad.dispose();
			}
		});
		btnNouvelleVoiture.setBounds(309, 157, 45, 21);
		getContentPane().add(btnNouvelleVoiture);

		JButton btnNouveauFournisseur = new JButton("+");// permet d'ajouter un nouveau fournisseur dans la base de donnée
		btnNouveauFournisseur.setBackground(new Color(153, 115, 142));
		btnNouveauFournisseur.setBorderPainted(false);
		btnNouveauFournisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfournisseur adf = new addfournisseur();
				adf.setVisible(true);
				ad.dispose();
			}
		});
		btnNouveauFournisseur.setBounds(309, 204, 45, 21);
		getContentPane().add(btnNouveauFournisseur);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//permet de vérifier si le programme arrive a se connecter a jdbcDriver. retourne une erreur si c'est pas le cas
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", ""); //permet de se connecter a ma base de donnée mysql.
			PreparedStatement st = conn.prepareStatement("select * from voiture");
			PreparedStatement st2 = conn.prepareStatement("select * from fournisseur");
			// st.setString(1,textField.getText());
			// st.setString(2,textField_1.getText());
			ResultSet x = st.executeQuery();
			ResultSet x2 = st2.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			ArrayList<String> as1 = new ArrayList<>();
			while (x.next())
				as.add(x.getString(2));
			while (x2.next())
				as1.add(x2.getString(3));
			comboBoxVoiture.setModel(new DefaultComboBoxModel(as.toArray()));
			comboBoxFournissseur.setModel(new DefaultComboBoxModel(as1.toArray()));

			JLabel lblQuantité = new JLabel("Quantité");
			lblQuantité.setForeground(Color.WHITE);
			lblQuantité.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblQuantité.setBounds(106, 118, 61, 13);
			getContentPane().add(lblQuantité);

			textFieldQuantité = new JTextField();
			textFieldQuantité.setColumns(10);
			textFieldQuantité.setBounds(194, 115, 96, 19);
			getContentPane().add(textFieldQuantité);
			
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		/**
		 * Permet d'enregistrer dans la base de données les informations de la commande saisie.
		 *
		 */
		btnAjouterCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st = conn.prepareStatement("select id from voiture where modéle = ?");
					PreparedStatement st2 = conn.prepareStatement("select id from fournisseur where nom = ?");
					st2.setString(1, comboBoxFournissseur.getSelectedItem().toString());
					st.setString(1, comboBoxVoiture.getSelectedItem().toString());
					ResultSet x = st.executeQuery();
					ResultSet x2 = st2.executeQuery();
					int id1 = 0;
					int id2 = 0;
					while (x.next()) {
						id1 = x.getInt(1);
					}
					while (x2.next()) {
						id2 = x2.getInt(1);
					}
					System.out.println("id1 : " + id1 + " id 2 : " + id2);
					PreparedStatement st3 = conn.prepareStatement(
							"insert into commande(finished,date,id_voiture,id_fournisseur,Prix,quantite) values(?,?,?,?,?,?)");
					st3.setBoolean(1, false);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					java.util.Date dt = new java.util.Date();

					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					String currentTime = sdf.format(dt);
					st3.setString(2, currentTime);
					st3.setInt(3, id1);
					st3.setInt(4, id2);
					st3.setString(5, textFieldPrix.getText());
					st3.setInt(6, Integer.parseInt(textFieldQuantité.getText()));
					st3.execute();
					
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
	}
	
	/**
	 * Permet d'exécuter la classe addcommande lorsque celle-ci est appelée.
	 *
	 */
	public static void main(String[] args) {
		addcommande adc = new addcommande();
		adc.setVisible(true);
	}
}
