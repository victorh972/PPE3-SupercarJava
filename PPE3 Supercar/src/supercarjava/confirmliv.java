package supercarjava;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * La classe confirmliv (confirmer livraison) permet d'update le statut de la commande qui passera de false à true ce qui signifie que la commande
 * aura bien été livrée.
 *
 */
public class confirmliv extends JFrame {
	static Connection conn = null;
	static Statement st = null;
	ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
	/**
	 * Initialisation de la fenêtre confirmliv
	 *
	 */
	public confirmliv() {
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
				st4.setInt(1, s.getInt(4));
				as.add(String.valueOf(s.getInt(3)));
				ResultSet d = st4.executeQuery();
				while (d.next())
					as.add(d.getString(2));
				as.add(String.valueOf(s.getInt(7)));
				as.add("confirmer");
				array.add(as);
			}
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		String[][] data = new String[this.array.size()][];
		for (int i = 0; i < this.array.size(); i++) {
			ArrayList<String> row = this.array.get(i);
			data[i] = row.toArray(new String[row.size()]);
		}

		// nom des colonnes
		String[] columnNames = { "id", "voiture", "quantité", "" };

		// initialiser le JTable
		JTable AffichageTableauLivraisonEnCours = new JTable(data, columnNames);
		AffichageTableauLivraisonEnCours.setBounds(30, 40, 200, 300);

		// l'ajouter a JScrollPane
		JScrollPane ScrollbarrePanel = new JScrollPane(AffichageTableauLivraisonEnCours);
		getContentPane().add(ScrollbarrePanel);
		// taille de la frame
		this.setSize(500, 200);
		// Frame Visible = true
		this.setVisible(true);
		Action delete = new AbstractAction() {
			/**
			 * Lorsque l'utilisateur appuiera sur le bouton "confirmer", cela aura pour effet d'update le champs finished de la base de données
			 * qui passera de false à true. True étant la valeur pour dire que la commande a bien été livrée et false la valeur pour signifier
			 * que la livraison est en cours.
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				int id = Integer.parseInt(array.get(modelRow).get(0));
				System.out.println(id);
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st3 = conn.prepareStatement("update commande set finished=true where id=?");
					st3.setInt(1, id);
					st3.executeUpdate();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		};
		ButtonColumn buttonColumn = new ButtonColumn(AffichageTableauLivraisonEnCours, delete, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	/**
	 * Permet d'exécuter la classe confirmliv lorsque celle-ci est appelée. 
	 *
	 */
	public static void main(String[] args) {
		confirmliv rct = new confirmliv();
		rct.setVisible(true);
	}
}
