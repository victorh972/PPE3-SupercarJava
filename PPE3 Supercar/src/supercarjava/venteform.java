package supercarjava;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ItemEvent;

public class venteform extends JFrame {
	private JTextField textField;
	static Connection conn = null;
	static Statement st = null;

	public venteform() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 438, 347);
		JLabel lblNewLabel = new JLabel("Marque");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(83, 46, 66, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Montant");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(83, 189, 66, 19);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(159, 191, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		JComboBox comboBox_1 = new JComboBox();

		JComboBox comboBox = new JComboBox();
		JComboBox comboBox_2 = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st4 = conn.prepareStatement("select modéle from voiture where marque=?");
					st4.setString(1, comboBox.getSelectedItem().toString());
					ArrayList<String> as1 = new ArrayList<>();
					ResultSet x4 = st4.executeQuery();
					while (x4.next())
						as1.add(x4.getString(1));
					as1 = venteform.removeDuplicates(as1);
					PreparedStatement st5 = conn
							.prepareStatement("select couleur from voiture where marque=? and modéle=?");
					st5.setString(1, comboBox.getSelectedItem().toString());
					st5.setString(2, comboBox_1.getSelectedItem().toString());
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBox_1.setModel(new DefaultComboBoxModel(as1.toArray()));
					comboBox_2.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ex) {
				}
			}
		});
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					PreparedStatement st5 = conn
							.prepareStatement("select couleur from voiture where marque=? and modéle=?");
					st5.setString(1, comboBox.getSelectedItem().toString());
					st5.setString(2, comboBox_1.getSelectedItem().toString());
					ArrayList<String> as2 = new ArrayList<>();
					ResultSet x5 = st5.executeQuery();
					while (x5.next())
						as2.add(x5.getString(1));
					as2 = venteform.removeDuplicates(as2);
					comboBox_2.setModel(new DefaultComboBoxModel(as2.toArray()));
				} catch (Exception ed) {
				}
			}
		});
		comboBox.setBounds(159, 43, 96, 21);
		getContentPane().add(comboBox);
		comboBox_2.setBounds(159, 143, 96, 21);
		getContentPane().add(comboBox_2);
		JButton btnNewButton = new JButton("Conclure");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(135, 242, 107, 21);
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

			comboBox_1.setBounds(159, 93, 96, 21);
			getContentPane().add(comboBox_1);
			ResultSet x = st.executeQuery();
			ArrayList<String> as = new ArrayList<>();
			while (x.next())
				as.add(x.getString(4));
			as = venteform.removeDuplicates(as);
			PreparedStatement st4 = conn.prepareStatement("select modéle from voiture where marque=?");
			st4.setString(1, as.get(0));
			ArrayList<String> as1 = new ArrayList<>();
			ResultSet x4 = st4.executeQuery();
			while (x4.next())
				as1.add(x4.getString(1));
			as1 = venteform.removeDuplicates(as1);
			PreparedStatement st5 = conn.prepareStatement("select couleur from voiture where marque=? and modéle=?");
			st5.setString(1, as.get(0));
			st5.setString(2, as1.get(0));
			ArrayList<String> as2 = new ArrayList<>();
			ResultSet x5 = st5.executeQuery();
			while (x5.next())
				as2.add(x5.getString(1));
			as2 = venteform.removeDuplicates(as2);
			comboBox.setModel(new DefaultComboBoxModel(as.toArray()));
			comboBox_1.setModel(new DefaultComboBoxModel(as1.toArray()));
			comboBox_2.setModel(new DefaultComboBoxModel(as2.toArray()));
			btnNewButton.setBorderPainted(false);
			btnNewButton.setBackground(new Color(246, 76, 114));

			JLabel lblNewLabel_2 = new JLabel("Mod\u00E9le");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_2.setBounds(83, 96, 66, 13);
			getContentPane().add(lblNewLabel_2);

			JLabel lblNewLabel_3 = new JLabel("Couleur");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setBounds(83, 146, 66, 18);
			getContentPane().add(lblNewLabel_3);
			venteform vf = this;

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
								.prepareStatement("select id from voiture where marque=? and couleur=? and modéle = ?");
						st.setString(1, comboBox.getSelectedItem().toString());
						st.setString(2, comboBox_2.getSelectedItem().toString());
						st.setString(3, comboBox_1.getSelectedItem().toString());
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

						int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le reçu de vente ?",
								"Reçu de vente", JOptionPane.YES_NO_OPTION);

						if (option == 0) { // The ISSUE is here
							com.itextpdf.text.Font catFont = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
							com.itextpdf.text.Font redFont = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.NORMAL,
									BaseColor.RED);
							com.itextpdf.text.Font subFont = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
							com.itextpdf.text.Font smallBold = new com.itextpdf.text.Font(
									com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

							Document document = new Document();
							JFileChooser fileChooser = new JFileChooser();
							if (fileChooser.showSaveDialog(vf) == JFileChooser.APPROVE_OPTION) {
								File file = fileChooser.getSelectedFile();
								try {
									PdfWriter.getInstance(document,
											new FileOutputStream(file.getAbsoluteFile() + ".pdf"));
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								document.open();
								try {

									Paragraph preface = new Paragraph();
									// We add one empty line
									addEmptyLine(preface, 1);
									// Lets write a big header
									preface.add(new Paragraph("Reçu de vente", catFont));

									addEmptyLine(preface, 1);
									// Will create: Report generated by: _name, _date
									addEmptyLine(preface, 1);
									// Will create: Report generated by: _name, _date
									preface.add(new Paragraph(
											"Géneré par  " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
											smallBold));
									addEmptyLine(preface, 3);
									preface.add(new Paragraph("Marque  : " + comboBox.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(new Paragraph("Modéle  : " + comboBox_1.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(new Paragraph("Couleur  : " + comboBox_2.getSelectedItem().toString(),
											smallBold));
									addEmptyLine(preface, 2);
									preface.add(
											new Paragraph("Montant  : " + textField.getText() + " euro ", smallBold));
									addEmptyLine(preface, 8);

									document.add(preface);
									// Start a new page
								} catch (DocumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								document.close();
							}
						} else {
							System.out.print("no");
						}
					} catch (SQLException e2) {
						System.out.println(e2.getMessage());
					}

				}
			});
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

		// Create a new ArrayList
		ArrayList<T> newList = new ArrayList<T>();

		// Traverse through the first list
		for (T element : list) {

			// If this element is not present in newList
			// then add it
			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		// return the new list
		return newList;
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public static void main(String[] args) {
		venteform vf = new venteform();
		vf.setVisible(true);
	}
}
