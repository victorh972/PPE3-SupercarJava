package supercarjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class recentcars extends JFrame {
	static Connection conn = null;
	static Statement st = null;
	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();

	public recentcars() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("error");
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
			PreparedStatement st = conn.prepareStatement("select * from commande where finished=false");
			ResultSet s = st.executeQuery();

			while (s.next()) {
				ArrayList<String> as = new ArrayList<String>();
				PreparedStatement st4 = conn.prepareStatement("select * from voiture where id=?");
				PreparedStatement st5 = conn.prepareStatement("select nom from fournisseur where id=?");
				st4.setInt(1, s.getInt(4));
				st5.setInt(1, s.getInt(5));
				ResultSet d = st4.executeQuery();
				ResultSet d2 = st5.executeQuery();
				while (d.next())
					as.add(d.getString(2));
				as.add(String.valueOf(s.getInt(6)));
				while (d2.next())
					as.add(d2.getString(1));
				as.add(String.valueOf(s.getInt(7)));
				array.add(as);
			}
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		/*
		 * mouchare pour voir si l'affichages des données dans le tableau ce font
		 * 
		 * String[][] data = { { "Mercedes benz class", "5","cars sh", "250000" }, {
		 * "BMW", "12","bmw cars", "600000" } };
		 */

		// nom des colonne
		String[][] data = new String[this.array.size()][];
		for (int i = 0; i < this.array.size(); i++) {
			ArrayList<String> row = this.array.get(i);
			data[i] = row.toArray(new String[row.size()]);
		}
		String[] columnNames = { "Voiture", "Prix", "Fournisseur", "Quantité" };

		// Initialiser le JTable
		JTable j = new JTable(data, columnNames);
		j.setBounds(30, 40, 200, 300);

		// ajouter le a JScrollPane
		JScrollPane sp = new JScrollPane(j);
		this.add(sp);
		// taille de la frame
		this.setSize(500, 200);
		// Frame Visible = true
		this.setVisible(true);
	}

	public static void main(String[] args) {
		recentcars tct = new recentcars();
		tct.setVisible(true);
	}

}
