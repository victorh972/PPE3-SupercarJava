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

public class addcar extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	static Connection conn = null;
	static Statement st = null;
	private JTextField textField_2;

	public addcar() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 300);
		JLabel lblNewLabel = new JLabel("Marque");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(73, 55, 61, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Couleur");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(73, 144, 61, 13);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(158, 53, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(158, 142, 96, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(246, 76, 114));
		addcommande adc = new addcommande();
		addcar ac = this;
		btnNewButton.addActionListener(new ActionListener() {
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
					st.setString(1, textField_1.getText());
					st.setString(3, textField.getText());
					st.setString(2, textField_2.getText());
					st.execute();
					adc.setVisible(true);
					ac.dispose();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnNewButton.setBounds(127, 205, 110, 21);
		getContentPane().add(btnNewButton);

		textField_2 = new JTextField();
		textField_2.setBounds(158, 101, 96, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mod\u00E9le");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(73, 104, 61, 13);
		getContentPane().add(lblNewLabel_2);
	}

	public static void main(String[] args) {
		addcar adc = new addcar();
		adc.setVisible(true);
	}
}
