package supercarjava;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*************************************************************************************
 * 
 * Affichage de l'interface de l'Administration.
 * 
 ************************************************************************************/
public class admininterface extends JFrame {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Initialise le contenu de l'interface de l'Administration.
	 */
	public admininterface() {
		this.getContentPane().setBackground(new Color(200, 239, 249));
		getContentPane().setLayout(null);
		JButton btnNouvelleCommande = new JButton("Passer une nouvelle commande");
		admininterface adm=this;
		btnNouvelleCommande.addActionListener(new ActionListener() {
			/**
			 * Permet d'appeler la classe addcommande lors de l'exécution du bouton "Passer une nouvelle commande".
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				///adm.dispose();
				addcommande adm=new addcommande();
				adm.setVisible(true);
			}
		});
		btnNouvelleCommande.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNouvelleCommande.setBackground(new Color(242, 125, 66));
		btnNouvelleCommande.setBorderPainted(false);
		btnNouvelleCommande.setBounds(56, 250, 278, 144);
		getContentPane().add(btnNouvelleCommande);
		
		JButton btnSuiviDesAchats = new JButton("Suivi des achats ");
		btnSuiviDesAchats.addActionListener(new ActionListener() {
			/**
			 * Permet d'appeler la classe recentcars() lors de l'exécution du bouton "Suivi des achats"
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				//adm.dispose();
				recentcars rct=new recentcars();
				rct.setVisible(true);
			}
		});
		btnSuiviDesAchats.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSuiviDesAchats.setBorderPainted(false);
		btnSuiviDesAchats.setBackground(new Color(242, 125, 66));
		btnSuiviDesAchats.setBounds(387, 250, 278, 144);
		getContentPane().add(btnSuiviDesAchats);
		
		JButton btnSuiviDesVentes = new JButton("Suivi des ventes");
		btnSuiviDesVentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Permet d'appeler la classe recentsales() lors de l'exécution du bouton "Suivi des ventes"
				 *
				 */
				//adm.dispose();
				recentsales rcs=new recentsales();
				rcs.setVisible(true);
			}
		});
		btnSuiviDesVentes.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSuiviDesVentes.setBorderPainted(false);
		btnSuiviDesVentes.setBackground(new Color(242, 125, 66));
		btnSuiviDesVentes.setBounds(689, 250, 275, 144);
		getContentPane().add(btnSuiviDesVentes);
		adduser ads=new adduser();
		JButton btnAjoutUtilisateur = new JButton("Ajouter un utilisateur");
		btnAjoutUtilisateur.addActionListener(new ActionListener() {
			/**
			 * Permet d'appeler la classe adduser() lors de l'exécution du bouton "Ajouter un utilisateur"
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				ads.setVisible(true);
			}
		});
		btnAjoutUtilisateur.setBackground(new Color(242, 125, 66));
		btnAjoutUtilisateur.setBorderPainted(false);
		btnAjoutUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjoutUtilisateur.setBounds(387, 478, 278, 131);
		getContentPane().add(btnAjoutUtilisateur);
		this.setBounds(100, 100, 1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Permet d'exécuter la classe admininterface lorsque celle-ci est appelée.
	 *
	 */
	public static void main(String[] args)
	{
		admininterface adm=new admininterface();
		adm.setVisible(true);
	}
}
