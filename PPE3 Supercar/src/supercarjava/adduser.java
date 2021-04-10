import javax.swing.JFrame;
import javax.swing.JLabel;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class adduser extends JFrame {
	private JPasswordField passwordField;
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;

	public adduser() {
		getContentPane().setBackground(new Color(243, 243, 243));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 500, 400);
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setForeground(new Color(147, 36, 50));
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(108, 122, 45, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(new Color(147, 36, 50));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(108, 177, 81, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("type");
		lblNewLabel_2.setForeground(new Color(147, 36, 50));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(108, 219, 58, 13);
		getContentPane().add(lblNewLabel_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(147, 36, 50));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "administrateur", "vendeur", "manager ", "RH" }));
		comboBox.setBounds(200, 215, 96, 21);
		getContentPane().add(comboBox);

		passwordField = new JPasswordField();
		passwordField.setBounds(199, 175, 96, 19);
		getContentPane().add(passwordField);

		textField = new JTextField();
		textField.setBounds(200, 120, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(60, 24, 116));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(170, 286, 96, 21);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st = conn
							.prepareStatement("insert into users (role,email,password) values (?,?,?)");
					st.setInt(1, comboBox.getSelectedIndex());
					st.setString(2, textField.getText());
					st.setString(3, AES256.encrypt(passwordField.getText()));
					st.execute();
					// adc.setVisible(true);
					// ac.dispose();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});

	}

	public static void main(String[] args) {
		adduser ads = new adduser();
		ads.setVisible(true);
	}

}
