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

public class Managerinterface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Managerinterface()  {
		this.getContentPane().setBackground(new Color(85, 122, 149));
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Gestion stock");
		Managerinterface mg=this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mg.dispose();
				stockmangement stm=new stockmangement();
				stm.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(177, 162, 150));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(77, 276, 291, 159);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voitures Command\u00E9es");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mg.dispose();
				confirmliv cfl=new confirmliv();
				cfl.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(new Color(177, 162, 150));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(556, 276, 284, 159);
		getContentPane().add(btnNewButton_1);
		this.setBounds(100, 100, 1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		Managerinterface mng=new Managerinterface();
		mng.setVisible(true);
	}
}
