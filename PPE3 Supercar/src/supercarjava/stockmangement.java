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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class stockmangement extends JFrame {
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;

	public stockmangement() {
		getContentPane().setBackground(new Color(26, 26, 29));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 400);
		JLabel lblNewLabel = new JLabel("Marque");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(101, 95, 45, 13);
		getContentPane().add(lblNewLabel);
		JComboBox comboBox = new JComboBox();
		JLabel lblNewLabel_1 = new JLabel("Entrepot");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(101, 50, 67, 13);
		getContentPane().add(lblNewLabel_1);
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(176, 169, 85, 21);
		getContentPane().add(comboBox_3);
		JComboBox comboBox_2 = new JComboBox();
		JComboBox comboBox_4 = new JComboBox();
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st5 = conn.prepareStatement(
							"select couleur from voiture where marque=? and modéle=? and id in (select id_voiture from stock where id_entrepot=?");
					st5.setString(1, comboBox.getSelectedItem().toString());
					st5.setString(2, comboBox_2.getSelectedItem().toString());
					st5.setInt(3, comboBox_4.getSelectedIndex() + 1);
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBox_3.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ed) {
				}
			}
		});
		comboBox_2.setBounds(176, 128, 85, 21);
		getContentPane().add(comboBox_2);

		JLabel lblNewLabel_4 = new JLabel("Couleur");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(101, 172, 45, 13);
		getContentPane().add(lblNewLabel_4);

		comboBox.setBounds(176, 87, 85, 21);
		getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(111, 34, 50));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(67, 289, 105, 21);
		getContentPane().add(btnNewButton);

		comboBox_4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st = conn.prepareStatement(
							"select marque from voiture where id in (select id_voiture from stock where id_entrepot=?)");
					st.setInt(1, comboBox_4.getSelectedIndex() + 1);
					ResultSet x = st.executeQuery();
					ArrayList<String> as = new ArrayList<>();
					while (x.next())
						as.add(x.getString(1));
					as = venteform.removeDuplicates(as);
					PreparedStatement st4 = conn.prepareStatement(
							"select modéle from voiture where marque=? and id in (select id_voiture from stock where id_entrepot=?)");
					st4.setString(1, as.get(0));
					st4.setInt(2, comboBox_4.getSelectedIndex() + 1);
					ArrayList<String> as1 = new ArrayList<>();
					ResultSet x4 = st4.executeQuery();
					while (x4.next())
						as1.add(x4.getString(1));
					as1 = venteform.removeDuplicates(as1);
					PreparedStatement st5 = conn.prepareStatement(
							"select couleur from voiture where marque=? and modéle=?  and id in (select id_voiture from stock where id_entrepot=?)");
					st5.setString(1, as.get(0));
					st5.setString(2, as1.get(0));
					st5.setInt(3, comboBox_4.getSelectedIndex() + 1);
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBox.setModel(new DefaultComboBoxModel(as.toArray()));
					comboBox_2.setModel(new DefaultComboBoxModel(as1.toArray()));
					comboBox_3.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ec) {
				}
			}
		});

		comboBox_4.setModel(
				new DefaultComboBoxModel(new String[] { "Port Louis", "Baie du Tombeau", "Phoenix", "Plaisance" }));
		comboBox_4.setBounds(178, 47, 85, 21);
		getContentPane().add(comboBox_4);
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setForeground(Color.WHITE);
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
					st.setInt(2, comboBox_4.getSelectedIndex() + 1);
					PreparedStatement st2 = conn
							.prepareStatement("select id from voiture where modéle=? and marque=? and couleur=?");
					st2.setString(1, comboBox_2.getSelectedItem().toString());
					st2.setString(2, comboBox.getSelectedItem().toString());
					st2.setString(3, comboBox_3.getSelectedItem().toString());
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
		btnNewButton_1.setBounds(250, 289, 105, 21);
		getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Qauntit\u00E9 ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(101, 217, 67, 13);
		getContentPane().add(lblNewLabel_2);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st4 = conn.prepareStatement(
							"select modéle from voiture where marque=? and id in (select id_voiture from stock where id_entrepot=?)");
					st4.setString(1, comboBox.getSelectedItem().toString());
					st4.setInt(2, comboBox_4.getSelectedIndex() + 1);
					ArrayList<String> as1 = new ArrayList<>();
					ResultSet x4 = st4.executeQuery();
					while (x4.next())
						as1.add(x4.getString(1));
					as1 = venteform.removeDuplicates(as1);
					PreparedStatement st5 = conn.prepareStatement(
							"select couleur from voiture where marque=? and modéle=? and id in (select id_voiture from stock where id_entrepot=?)");
					st5.setString(1, comboBox.getSelectedItem().toString());
					st5.setInt(3, comboBox_4.getSelectedIndex() + 1);
					st5.setString(2, comboBox_2.getSelectedItem().toString());
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBox_2.setModel(new DefaultComboBoxModel(as1.toArray()));
					comboBox_3.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ex) {
				}
			}
		});
		textField = new JTextField();
		textField.setBounds(176, 215, 85, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
			PreparedStatement st = conn.prepareStatement(
					"select marque from voiture where id in (select id_voiture from stock where id_entrepot=1)\r\n"
							+ "");
			ResultSet x = st.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			while (x.next())
				as.add(x.getString(1));
			as = venteform.removeDuplicates(as);
			PreparedStatement st4 = conn.prepareStatement(
					"select modéle from voiture where marque=? and id in (select id_voiture from stock where id_entrepot=1)");
			st4.setString(1, as.get(0));
			ArrayList<String> as1 = new ArrayList<>();
			ResultSet x4 = st4.executeQuery();
			while (x4.next())
				as1.add(x4.getString(1));
			as1 = venteform.removeDuplicates(as1);
			PreparedStatement st5 = conn.prepareStatement(
					"select couleur from voiture where marque=? and modéle=? and id in (select id_voiture from stock where id_entrepot=1)");
			st5.setString(1, as.get(0));
			st5.setString(2, as1.get(0));
			ArrayList<String> as2 = new ArrayList<>();
			ResultSet x5 = st5.executeQuery();
			while (x5.next())
				as2.add(x5.getString(1));
			as2 = venteform.removeDuplicates(as2);
			comboBox.setModel(new DefaultComboBoxModel(as.toArray()));
			comboBox_2.setModel(new DefaultComboBoxModel(as1.toArray()));
			comboBox_3.setModel(new DefaultComboBoxModel(as2.toArray()));

			JLabel lblNewLabel_3 = new JLabel("Mod\u00E9le");
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel_3.setBounds(105, 131, 67, 13);
			getContentPane().add(lblNewLabel_3);
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
					st.setInt(2, comboBox_4.getSelectedIndex() + 1);
					PreparedStatement st2 = conn
							.prepareStatement("select id from voiture where modéle=? and marque=? and couleur=?");
					st2.setString(1, comboBox_2.getSelectedItem().toString());
					st2.setString(2, comboBox.getSelectedItem().toString());
					st2.setString(3, comboBox_3.getSelectedItem().toString());
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
