package supercarjava;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/***********************************************************************************************************
 *
 * Affichage de l'interface Vendeur.
 * 
 * 
 **********************************************************************************************************/
public class Vendeurinterface extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/**
	 * Lance l'application.
	 */
	public Vendeurinterface() {
		this.getContentPane().setBackground(new Color(200, 239, 249));
		getContentPane().setLayout(null);

		JButton btnConclure = new JButton("Conclure une vente");
		Vendeurinterface vfd = this;
		btnConclure.addActionListener(new ActionListener() {
			/**
			 * Lorsque l'utilisateur clique sur le bouton "Conclure une vente",
			 * la classe venteform() sera executée et l'interface correspondante s'affichera.
			 */
			public void actionPerformed(ActionEvent e) {
				vfd.dispose();
				venteform vf = new venteform();
				vf.setVisible(true);
			}
		});
		btnConclure.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConclure.setBorderPainted(false);
		btnConclure.setBackground(new Color(242, 125, 66));
		btnConclure.setBounds(53, 235, 278, 144);
		getContentPane().add(btnConclure);

		JButton btnCommandesEnCours = new JButton("Vente en cours");
		btnCommandesEnCours.addActionListener(new ActionListener() {
			/**
			 * 
			 * Lorsque l'utilisateur appuiera sur le bouton vente en cours, la classe Ventesnonl() s'exécutera,
			 * ce qui permettra à l'utilisateur de consulter les ventes qu'il a effectué et qui sont en cours de livraison.
			 * 
			 */
			public void actionPerformed(ActionEvent e) {
				vfd.dispose();
				Ventesnonl vnl = new Ventesnonl();
				vnl.setVisible(true);
			}
		});
		btnCommandesEnCours.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCommandesEnCours.setBorderPainted(false);
		btnCommandesEnCours.setBackground(new Color(242, 125, 66));
		btnCommandesEnCours.setBounds(368, 235, 278, 144);
		getContentPane().add(btnCommandesEnCours);

		JButton btnCommandeEnVours = new JButton("Commande en cours");
		btnCommandeEnVours.addActionListener(new ActionListener() {
			/**
			 * Lorsque l'utilisateur cliquera sur le bouton "Commande en cours", la classe "cmdencours()" s'exécutera,
			 * ce qui permettra à l'utilisateur de voir les commandes de voiture pour les entrepôts en cours de livraison.
			 */
			public void actionPerformed(ActionEvent e) {
				vfd.dispose();
				cmdencours cd = new cmdencours();
				cd.setVisible(true);
			}
		});
		btnCommandeEnVours.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCommandeEnVours.setBorderPainted(false);
		btnCommandeEnVours.setBackground(new Color(242, 125, 66));
		btnCommandeEnVours.setBounds(676, 235, 278, 144);
		getContentPane().add(btnCommandeEnVours);
		this.setBounds(100, 100, 1000, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	/**
	 * Permet d'exécuter la classe Vendeurinterface() lorsque que celle-ci est appelée.
	 */
	public static void main(String[] args) {
		Vendeurinterface vd = new Vendeurinterface();
		vd.setVisible(true);
	}
}
