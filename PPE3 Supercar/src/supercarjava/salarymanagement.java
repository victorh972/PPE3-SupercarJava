
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class salarymanagement extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	static Connection conn = null;
	static Statement st = null;

	public salarymanagement() {
		getContentPane().setBackground(new Color(243, 243, 243));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setForeground(new Color(147, 36, 50));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(103, 85, 45, 13);
		getContentPane().add(lblNewLabel);
		this.setBounds(100, 100, 400, 400);
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setForeground(new Color(147, 36, 50));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(103, 135, 58, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Fonction");
		lblNewLabel_2.setForeground(new Color(147, 36, 50));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(103, 181, 58, 13);
		getContentPane().add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(182, 82, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(182, 132, 96, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(147, 36, 50));
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "administrateur", "vendeur", "manager", "RH" }));
		comboBox.setBounds(182, 177, 96, 21);
		getContentPane().add(comboBox);

		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setForeground(new Color(147, 36, 50));
		lblMontant.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMontant.setBounds(103, 229, 58, 13);
		getContentPane().add(lblMontant);

		textField_2 = new JTextField();
		textField_2.setBounds(182, 227, 96, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("Confirmer");
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
							.prepareStatement("select * from employee where nom=? and prenom=? and fonction=?");
					st.setString(2, textField_1.getText());
					st.setString(1, textField.getText());
					st.setString(3, comboBox.getSelectedItem().toString());
					java.sql.ResultSet s = st.executeQuery();
					while (s.next()) {
						int id = s.getInt(1);
						PreparedStatement st2 = conn
								.prepareStatement("insert into transaction (date,montant,id_employee) values(?,?,?)");
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						java.util.Date dt = new java.util.Date();

						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						String currentTime = sdf.format(dt);
						st2.setString(1, currentTime);
						st2.setInt(2, Integer.parseInt(textField_2.getText()));
						st2.setInt(3, id);
						st2.execute();
						break;
					}
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(60, 24, 116));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(160, 302, 105, 21);
		getContentPane().add(btnNewButton);
	}

	public static void main(String[] args) {
		salarymanagement sm = new salarymanagement();
		sm.setVisible(true);
	}
}
