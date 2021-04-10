package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class stockmangement extends JFrame {
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;

	public stockmangement() {
		getContentPane().setBackground(new Color(26, 26, 29));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 400);
		JLabel lblNewLabel = new JLabel("Voiture");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(101, 95, 45, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Entrepot");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(101, 154, 67, 13);
		getContentPane().add(lblNewLabel_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(176, 92, 85, 21);
		getContentPane().add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(
				new DefaultComboBoxModel(new String[] { "Port Louis", "Baie du Tombeau", "Phoenix", "Plaisance" }));
		comboBox_1.setBounds(176, 151, 85, 21);
		getContentPane().add(comboBox_1);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(111, 34, 50));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(69, 261, 105, 21);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setBackground(new Color(195, 7, 63));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st = conn.prepareStatement(
							"update stock set quantité=quantité-? where id_entrepot=? and id_voiture=?");
					st.setInt(1, Integer.parseInt(textField.getText()));
					st.setInt(2, comboBox_1.getSelectedIndex() + 1);
					PreparedStatement st2 = conn.prepareStatement("select id from voiture where modéle=?");
					st2.setString(1, comboBox.getSelectedItem().toString());
					ResultSet x2 = st2.executeQuery();
					int id = 0;
					while (x2.next()) {
						id = x2.getInt(1);
						break;
					}
					st.setInt(3, id);
					st.execute();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnNewButton_1.setBounds(252, 261, 105, 21);
		getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Quantité ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(101, 201, 67, 13);
		getContentPane().add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(176, 199, 78, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
			PreparedStatement st = conn.prepareStatement("select * from voiture");
			ResultSet x = st.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			while (x.next())
				as.add(x.getString(2));
			comboBox.setModel(new DefaultComboBoxModel(as.toArray()));
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st = conn.prepareStatement(
							"update stock set quantité=quantité+? where id_entrepot=? and id_voiture=?");
					st.setInt(1, Integer.parseInt(textField.getText()));
					st.setInt(2, comboBox_1.getSelectedIndex() + 1);
					PreparedStatement st2 = conn.prepareStatement("select id from voiture where modéle=?");
					st2.setString(1, comboBox.getSelectedItem().toString());
					ResultSet x2 = st2.executeQuery();
					int id = 0;
					while (x2.next()) {
						id = x2.getInt(1);
						break;
					}
					st.setInt(3, id);
					st.execute();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
	}

	public static void main(String[] args) {
		stockmangement stm = new stockmangement();
		stm.setVisible(true);
	}
}
