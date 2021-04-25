package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;

/***********************************************************************************
 * La classe adduser permet d'ajouter de nouveaux utilisateurs dans la base de
 * données, ce qui leur permettra d'utiliser l'application par la suite.
 *
 **********************************************************************************/
public class adduser extends JFrame {
	private JPasswordField passwordField;
	private JTextField textFieldEmail;
	static Connection conn = null;
	static Statement st = null;
	/*****
	 * 
	 * 
	 * Initialise le contenu de la fenêtre d'ajout d'utilisateur.
	 * 
	 */
	public adduser() {
		getContentPane().setBackground(new Color(243, 243, 243));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 500, 400);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(147, 36, 50));
		lblEmail.setBackground(Color.YELLOW);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(108, 122, 45, 13);
		getContentPane().add(lblEmail);

		JLabel lblPasswoed = new JLabel("Password");
		lblPasswoed.setForeground(new Color(147, 36, 50));
		lblPasswoed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPasswoed.setBounds(108, 177, 81, 13);
		getContentPane().add(lblPasswoed);

		JLabel lblrole = new JLabel("Role");
		lblrole.setForeground(new Color(147, 36, 50));
		lblrole.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblrole.setBounds(108, 219, 58, 13);
		getContentPane().add(lblrole);

		JComboBox comboBoxRole = new JComboBox();
		comboBoxRole.setForeground(new Color(147, 36, 50));
		comboBoxRole.setModel(new DefaultComboBoxModel(new String[] { "administrateur", "vendeur", "manager ", "RH" }));
		comboBoxRole.setBounds(200, 215, 96, 21);
		getContentPane().add(comboBoxRole);

		passwordField = new JPasswordField();
		passwordField.setBounds(199, 175, 96, 19);
		getContentPane().add(passwordField);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(200, 120, 96, 19);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setBackground(new Color(60, 24, 116));
		btnAjouter.setBorderPainted(false);
		btnAjouter.setBounds(200, 286, 96, 21);
		getContentPane().add(btnAjouter);
		adduser ads = this;
		btnAjouter.addActionListener(new ActionListener() {
			/***
			 *
			 *Lors de l'exécution du bouton "Ajouter" le programme vérifie
			 *que toutes les données saisies sont dans le bon format.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
				Matcher mat = pattern.matcher(textFieldEmail.getText());
				if (textFieldEmail.getText().isEmpty() || passwordField.getText().isEmpty())
					JOptionPane.showMessageDialog(ads, "Il faut remplir tous les champs");
				else if (!mat.matches()) {
					JOptionPane.showMessageDialog(ads, "Invalide adresse email");
				} else if (!containsNumber(passwordField.getText()) || !containsSpecialchar(passwordField.getText())) {
					JOptionPane.showMessageDialog(ads,
							"le mot de passe doit contenir des nombres et des caractères spéciaux");
				} else if (!containsLowerCase(passwordField.getText()) || !containsUpperCase(passwordField.getText())) {
					JOptionPane.showMessageDialog(ads,
							"le mot de passe doit contenir des lettres en majuscules et minuscules");
				} else if (passwordField.getText().length() < 15)
					JOptionPane.showMessageDialog(ads, "le mot de passe doit depasser la longeur 15");
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						System.out.println("error");
					}
					try {
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
						PreparedStatement st2 = conn.prepareStatement("select * from users where email=?");
						st2.setString(1, textFieldEmail.getText());
						ResultSet d = st2.executeQuery();
						if (d.next()) {
							JOptionPane.showMessageDialog(ads, "l'email existe déjà ");
						} else {
							PreparedStatement st = conn
									.prepareStatement("insert into users (role,email,password) values (?,?,?)");
							st.setInt(1, comboBoxRole.getSelectedIndex());
							st.setString(2, textFieldEmail.getText());
							try {
								st.setString(3, Hash.hashPassword(passwordField.getText()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							st.execute();
							JOptionPane.showMessageDialog(ads, "Opération réussie");
							// ac.dispose();
						}
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(ads, "Problème de connexion à la base de données");
					}
				}
			}
		});

	}

	/**
	 * 
	 * Cette méthode permet de vérifier si la chaine de caratères (ici, le mot de passe) contient
	 * des lettres en minuscule ou non.
	 * 
	 * */
	private boolean containsLowerCase(String value) {
		return contains(value, i -> Character.isLetter(i) && Character.isLowerCase(i));
	}

	/**
	 * 
	 * Cette méthode permet de vérifier si la chaine de caratères (ici, le mot de passe) contient
	 * des lettres en majuscule ou non
	 * 
	 * */
	private boolean containsUpperCase(String value) {
		return contains(value, i -> Character.isLetter(i) && Character.isUpperCase(i));
	}
	/**
	 * 
	 * Cette méthode permet de vérifier si la chaine de caratères (ici, le mot de passe) contient
	 * des chiffres ou non
	 * 
	 * */
	private boolean containsNumber(String value) {
		return contains(value, Character::isDigit);
	}
/**
 * 
 * Cette méthode permet de vérifier si la chaine de caratères (ici, le mot de passe) contient
 * des caractères spéciaux ou non.
 * 
 * */
	private boolean containsSpecialchar(String str) {
		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (!Character.isDigit(ch) && !Character.isLetter(ch) && !Character.isSpace(ch))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * Cette méthode permet de lire une chaine de caractères (ici, le mot de passe)
	 * et de vérifier si tous les prérequis relatifs au mot de passe sont valides.
	 * 
	 * */
	private boolean contains(String value, IntPredicate predicate) {
		return value.chars().anyMatch(predicate);
	}

	public static void main(String[] args) {
		adduser ads = new adduser();
		ads.setVisible(true);
	}

}
