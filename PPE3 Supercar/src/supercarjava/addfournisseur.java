package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
/**
 * La classe addfournisseur permet d'ajouter de nouveaux fournisseurs de voitures dans la base de données
 * pour pouvoir ensuite les selectionner lors de la commande de voitures pour les entrepôts.
 *
 */
public class addfournisseur extends JFrame {
	private JTextField textFieldNomFournisseur;
	private JTextField textFieldAddresseFournisseur;
	static Connection conn = null;
	static Statement st = null;
	/**
	 * Initialisation de la fenêtre addfournisseur.
	 *
	 */
	public addfournisseur() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 300);
		JLabel lblNomFournisseur = new JLabel("Nom");
		lblNomFournisseur.setForeground(Color.WHITE);
		lblNomFournisseur.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNomFournisseur.setBounds(96, 60, 60, 22);
		getContentPane().add(lblNomFournisseur);

		textFieldNomFournisseur = new JTextField();
		textFieldNomFournisseur.setBounds(177, 62, 96, 19);
		getContentPane().add(textFieldNomFournisseur);
		textFieldNomFournisseur.setColumns(10);

		JLabel lblAddresseFournisseur = new JLabel("Adresse");
		lblAddresseFournisseur.setForeground(Color.WHITE);
		lblAddresseFournisseur.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddresseFournisseur.setBounds(96, 120, 60, 13);
		getContentPane().add(lblAddresseFournisseur);

		textFieldAddresseFournisseur = new JTextField();
		textFieldAddresseFournisseur.setBounds(177, 117, 96, 19);
		getContentPane().add(textFieldAddresseFournisseur);
		textFieldAddresseFournisseur.setColumns(10);
		addcommande adc = new addcommande();
		addfournisseur adf = this;
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(246, 76, 114));
		btnAjouter.setBorderPainted(false);
		btnAjouter.addActionListener(new ActionListener() {
			/**
			 * Permet d'ajouter les données saisies à partir de l'application, dans la base de données,
			 * après que l'utilisateur ait appuyé sur le bouton "Ajouter".
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
					PreparedStatement st = conn.prepareStatement("insert into fournisseur (adresse,nom) values (?,?)");
					st.setString(1, textFieldAddresseFournisseur.getText());
					st.setString(2, textFieldNomFournisseur.getText());
					st.execute();
					adc.setVisible(true);
					adf.dispose();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnAjouter.setBounds(153, 191, 85, 21);
		getContentPane().add(btnAjouter);
	}
	/**
	 * Permet d'exécuter la classe addfournisseur lorsque celle-ci est appelée.
	 *
	 */
	public static void main(String[] args) {
		addfournisseur adf = new addfournisseur();
		adf.setVisible(true);
	}
}
