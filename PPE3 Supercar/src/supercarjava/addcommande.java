import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class addcommande extends JFrame {
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;
	private JTextField textField_1;

	public addcommande() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setSize(500, 400);
		JLabel lblNewLabel = new JLabel("Prix");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(106, 82, 45, 13);
		getContentPane().add(lblNewLabel);
		addcommande ad = this;
		JLabel lblNewLabel_1 = new JLabel("Voiture");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(106, 159, 45, 16);
		getContentPane().add(lblNewLabel_1);
		textField = new JTextField();
		textField.setBounds(194, 79, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(194, 204, 96, 21);
		getContentPane().add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("Fournisseur");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(106, 208, 78, 13);
		getContentPane().add(lblNewLabel_2);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(194, 157, 96, 21);
		getContentPane().add(comboBox_1);

		JButton btnNewButton = new JButton("Ajouter commande");
		btnNewButton.setBackground(new Color(246, 76, 114));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(178, 268, 139, 21);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.setBackground(new Color(153, 115, 142));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcar adc = new addcar();
				adc.setVisible(true);
				ad.dispose();
			}
		});
		btnNewButton_1.setBounds(309, 157, 45, 21);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("+");
		btnNewButton_2.setBackground(new Color(153, 115, 142));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addfournisseur adf = new addfournisseur();
				adf.setVisible(true);
				ad.dispose();
			}
		});
		btnNewButton_2.setBounds(309, 204, 45, 21);
		getContentPane().add(btnNewButton_2);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
			PreparedStatement st = conn.prepareStatement("select * from voiture");
			PreparedStatement st2 = conn.prepareStatement("select * from fournisseur");
			// st.setString(1,textField.getText());
			// st.setString(2,textField_1.getText());
			ResultSet x = st.executeQuery();
			ResultSet x2 = st2.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			ArrayList<String> as1 = new ArrayList<>();
			while (x.next())
				as.add(x.getString(2));
			while (x2.next())
				as1.add(x2.getString(3));
			comboBox_1.setModel(new DefaultComboBoxModel(as.toArray()));
			comboBox.setModel(new DefaultComboBoxModel(as1.toArray()));

			JLabel lblQuantit = new JLabel("Quantit\u00E9");
			lblQuantit.setForeground(Color.WHITE);
			lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblQuantit.setBounds(106, 118, 61, 13);
			getContentPane().add(lblQuantit);

			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(194, 115, 96, 19);
			getContentPane().add(textField_1);
			/*
			 * if (!x.next() ) { System.out.println("no data"); } else { do { int
			 * a=x.getInt("role"); if(a==0) ad.setVisible(true); frame.setVisible(false); }
			 * while (x.next()); }
			 */
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
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercar", "root", "");
					PreparedStatement st = conn.prepareStatement("select id from voiture where modéle = ?");
					PreparedStatement st2 = conn.prepareStatement("select id from fournisseur where nom = ?");
					st2.setString(1, comboBox.getSelectedItem().toString());
					st.setString(1, comboBox_1.getSelectedItem().toString());
					ResultSet x = st.executeQuery();
					ResultSet x2 = st2.executeQuery();
					int id1 = 0;
					int id2 = 0;
					while (x.next()) {
						id1 = x.getInt(1);
					}
					while (x2.next()) {
						id2 = x2.getInt(1);
					}
					System.out.println("id1 : " + id1 + " id 2 : " + id2);
					PreparedStatement st3 = conn.prepareStatement(
							"insert into commande(finished,date,id_voiture,id_fournisseur,Prix,quantite) values(?,?,?,?,?,?)");
					st3.setBoolean(1, false);
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
					java.util.Date dt = new java.util.Date();

					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					String currentTime = sdf.format(dt);
					st3.setString(2, currentTime);
					st3.setInt(3, id1);
					st3.setInt(4, id2);
					st3.setString(5, textField.getText());
					st3.setInt(6, Integer.parseInt(textField_1.getText()));
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
	}

	public static void main(String[] args) {
		addcommande adc = new addcommande();
		adc.setVisible(true);
	}
}
