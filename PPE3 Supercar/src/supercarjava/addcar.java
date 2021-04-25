package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * La classe addcar permet d'ajouter les voitures d'une marque et/ou d'un mod�le 
 * qui ne sont pas enregistr�es dans la base de donn�es lors d'une commande de voiture,
 * pour renouveler le stock des entrep�ts.
 *
 */
public class addcar extends JFrame {
	private JTextField textFieldMarque;
	private JTextField textFieldCouleur;
	static Connection conn = null;
	static Statement st = null;
	private JTextField textFieldMod�le;

	
	/**
	 * Initialisation de la fen�tre d'ajout de voitures.
	 *
	 */
	public addcar() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 300);
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setForeground(Color.WHITE);
		lblMarque.setBackground(Color.WHITE);
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMarque.setBounds(73, 55, 61, 13);
		getContentPane().add(lblMarque);

		JLabel lblCouleur = new JLabel("Couleur");
		lblCouleur.setForeground(Color.WHITE);
		lblCouleur.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCouleur.setBounds(73, 144, 61, 13);
		getContentPane().add(lblCouleur);

		textFieldMarque = new JTextField();
		textFieldMarque.setBounds(158, 53, 96, 19);
		getContentPane().add(textFieldMarque);
		textFieldMarque.setColumns(10);

		textFieldCouleur = new JTextField();
		textFieldCouleur.setBounds(158, 142, 96, 19);
		getContentPane().add(textFieldCouleur);
		textFieldCouleur.setColumns(10);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBorderPainted(false);
		btnAjouter.setBackground(new Color(246, 76, 114));
		addcommande adc = new addcommande();
		addcar ac = this;
		
		/**
		 * Lors de l'ex�cution du bouton "Ajouter" les donn�es entr�es seront inscrites dans la base de donn�e.
		 *
		 */
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercar", "root", "");
					PreparedStatement st = conn
							.prepareStatement("insert into voiture (couleur,modele,marque) values (?,?,?)");
					st.setString(1, textFieldCouleur.getText());
					st.setString(3, textFieldMarque.getText());
					st.setString(2, textFieldMod�le.getText());
					st.execute();
					adc.setVisible(true);
					ac.dispose();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnAjouter.setBounds(127, 205, 110, 21);
		getContentPane().add(btnAjouter);

		textFieldMod�le = new JTextField();
		textFieldMod�le.setBounds(158, 101, 96, 19);
		getContentPane().add(textFieldMod�le);
		textFieldMod�le.setColumns(10);

		JLabel lblMod�le = new JLabel("Mod�le");
		lblMod�le.setForeground(Color.WHITE);
		lblMod�le.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMod�le.setBounds(73, 104, 61, 13);
		getContentPane().add(lblMod�le);
	}
	
	/**
	 * Permet d'ex�cuter la classe addcar lorsque celle-ci est appel�e.
	 *
	 */
	public static void main(String[] args) {
		addcar adc = new addcar();
		adc.setVisible(true);
	}
}
