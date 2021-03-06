package supercarjava;


import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

/********************************************************************
 * Application permettant la gestion des ventes de voiture, d'approvisionnement des entrep?ts, 
 * et le salaire des Ressources Humaines de l'entreprise supercar.
 *
 * Auteur:		Victor Huguet
 * Date:		05 octobre 2020
 ********************************************************************/
public class app {

	private JFrame frame;
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;
	static String email = "";
	private JPasswordField passwordField;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Lance l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app window = new app();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cr?e l'application
	 */
	public app() {
		initialize();
	}

	/**
	 * Initialise le contenu de la fen?tre.
	 */
	void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(200, 239, 249));
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(2, 36, 73));
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(340, 254, 80, 13);
		frame.getContentPane().add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(2, 36, 73));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(340, 301, 80, 13);
		frame.getContentPane().add(lblPassword);

		JButton btnlogin = new JButton("Login");
		btnlogin.setBackground(new Color(242, 125, 66));
		btnlogin.setBorderPainted(false);
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBounds(415, 374, 143, 29);
		frame.getContentPane().add(btnlogin);

		textField = new JTextField();
		textField.setBounds(444, 253, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(444, 300, 114, 19);
		frame.getContentPane().add(passwordField);
		admininterface ad = new admininterface();
		Vendeurinterface vd = new Vendeurinterface();
		Managerinterface mg = new Managerinterface();
		RHinterface rhi = new RHinterface();
		btnlogin.addActionListener(new ActionListener() {
			/***
			 * 
			 * Lors de l'ex?cution du bouton "login", l'application va comparer le mail et le mot de passe associ? saisi par l'utilisateur avec
			 * celui contenu dans la base de donn?es. Si le mot de passe et l'identifiant sont corrects l'application ouvrira l'interface
			 * correspondant au r?le de l'utilisateur qui s'est authentifi?.
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st = conn.prepareStatement("select * from users where email=? and password=? ");
					st.setString(1, textField.getText());
					String originalPassword = passwordField.getText();
					String encryptedPassword = ApiBlowfish.encryptInString(originalPassword,
							ApiBlowfish.loadkey("8BcmVz/DWm73fUim2OQ0XVo2PnldzDD044treMjnQwY"));
					//System.out.println(encryptedPassword);
					st.setString(2, encryptedPassword);
					ResultSet x = st.executeQuery();
					if (!x.next()) {
						System.out.println("no data");
					} else {
						do {
							int a = x.getInt("role");
							email = x.getString(2);
							if (a == 0)
								ad.setVisible(true);
							else if (a == 1)
								vd.setVisible(true);
							else if (a == 2)
								mg.setVisible(true);
							else if (a == 3)
								rhi.setVisible(true);
							frame.setVisible(false);
						} while (x.next());
					}
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});

	}

}
