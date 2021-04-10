package supercarjava;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admininterface extends JFrame {
	/**
	 * Launch the application.
	 */
	private static final long serialVersionUID = 1L;

	public admininterface() {
		this.getContentPane().setBackground(new Color(200, 239, 249));
		getContentPane().setLayout(null);
		JButton btnNewButton = new JButton("Passer une nouvelle commande");
		admininterface adm=this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				///adm.dispose();
				addcommande adm=new addcommande();
				adm.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(242, 125, 66));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBounds(56, 250, 278, 144);
		getContentPane().add(btnNewButton);
		
		JButton btnSuiviDesAchats = new JButton("Suivi des achats ");
		btnSuiviDesAchats.addActionListener(new ActionListener() {
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
		JButton btnNewButton_1 = new JButton("Ajouter un utilisateur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ads.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(242, 125, 66));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBounds(387, 478, 278, 131);
		getContentPane().add(btnNewButton_1);
		this.setBounds(100, 100, 1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
