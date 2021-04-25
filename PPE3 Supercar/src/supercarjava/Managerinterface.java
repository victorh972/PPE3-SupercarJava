package supercarjava;

import java.awt.Color;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*****************************************************************************************************************************
 * 
 *Affichage de l'interface du/des Manager(s)
 *
 ****************************************************************************************************************************/
public class Managerinterface extends JFrame{

	
	private static final long serialVersionUID = 1L;
	/**
	 *Initialise le contenu de l'interface Manager. 
	 */
	public Managerinterface()  {
		this.getContentPane().setBackground(new Color(85, 122, 149));
		getContentPane().setLayout(null);
		
		JButton btnGestionStock = new JButton("Gestion stock");
		Managerinterface mg=this;
		btnGestionStock.addActionListener(new ActionListener() {
			/**
			 * Permet d'appeler la classe stockmangement lorsque l'on clique sur le bouton "Gestion Stock".
			 */
			public void actionPerformed(ActionEvent e) {
				mg.dispose();
				stockmangement stm=new stockmangement();
				stm.setVisible(true);
			}
		});
		btnGestionStock.setBackground(new Color(177, 162, 150));
		btnGestionStock.setBorderPainted(false);
		btnGestionStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGestionStock.setBounds(77, 276, 291, 159);
		getContentPane().add(btnGestionStock);
		
		JButton btnVoitureCommandées = new JButton("Voitures Commandées");
		btnVoitureCommandées.addActionListener(new ActionListener() {
			/**
			 * Permet d'appeler la classe confirmliv() lorsque l'on clique sur le bouton "Voitures Commandées".
			 */
			public void actionPerformed(ActionEvent e) {
				mg.dispose();
				confirmliv cfl=new confirmliv();
				cfl.setVisible(true);
			}
		});
		btnVoitureCommandées.setBackground(new Color(177, 162, 150));
		btnVoitureCommandées.setBorderPainted(false);
		btnVoitureCommandées.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoitureCommandées.setBounds(556, 276, 284, 159);
		getContentPane().add(btnVoitureCommandées);
		this.setBounds(100, 100, 1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Permet d'exécuter la classe Managerinterface lorsque celle-ci est appelée.
	 */
	public static void main(String[] args)
	{
		Managerinterface mng=new Managerinterface();
		mng.setVisible(true);
	}
}
