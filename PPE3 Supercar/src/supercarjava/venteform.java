import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class venteform extends JFrame {
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;

	public venteform() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 300);
		JLabel lblNewLabel = new JLabel("Voiture");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(83, 89, 45, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Montant");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(83, 144, 66, 13);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(159, 141, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(159, 89, 96, 21);
		getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("Conclure");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(129, 215, 107, 21);
		getContentPane().add(btnNewButton);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
			PreparedStatement st = conn.prepareStatement("select * from voiture");
			// st.setString(1,textField.getText());
			// st.setString(2,textField_1.getText());
			ResultSet x = st.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			while (x.next())
				as.add(x.getString(2));
			comboBox.setModel(new DefaultComboBoxModel(as.toArray()));
			btnNewButton.setBorderPainted(false);
			btnNewButton.setBackground(new Color(246, 76, 114));
			/*
			 * if (!x.next() ) { System.out.println("no data"); } else { do { int
			 * a=x.getInt("role"); if(a==0) ad.setVisible(true); frame.setVisible(false); }
			 * while (x.next()); }
			 */
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						System.out.println("error");
					}
					try {
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
						PreparedStatement st = conn.prepareStatement("select id from voiture where modéle = ?");
						st.setString(1, comboBox.getSelectedItem().toString());
						ResultSet x = st.executeQuery();
						int id1 = 0;
						while (x.next()) {
							id1 = x.getInt(1);
						}
						PreparedStatement st3 = conn.prepareStatement(
								"insert into ventes(id_vendeur,confirmed,Montant,id_voiture,date) values(?,?,?,?,?)");
						PreparedStatement st4 = conn.prepareStatement("select id from vendeur where email=?");
						st4.setString(1, app.email);
						int id = 0;
						ResultSet s = st4.executeQuery();
						while (s.next()) {
							id = s.getInt(1);
						}
						st3.setInt(1, id);
						st3.setBoolean(2, false);
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						java.util.Date dt = new java.util.Date();
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String currentTime = sdf.format(dt);
						st3.setString(5, currentTime);
						st3.setInt(4, id1);
						st3.setString(3, textField.getText());
						st3.execute();
						/*
						 * if (!x.next() ) { System.out.println("no data"); } else { do { int
						 * a=x.getInt("role"); if(a==0) ad.setVisible(true); frame.setVisible(false); }
						 * while (x.next()); }
						 */
					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}

				}
			});
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
	}

	public static void main(String[] args) {
		venteform vf = new venteform();
		vf.setVisible(true);
	}
}
