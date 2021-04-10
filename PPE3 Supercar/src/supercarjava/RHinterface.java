package supercarjava;


import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RHinterface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RHinterface() throws HeadlessException {
		this.getContentPane().setBackground(new Color(85, 122, 149));
		getContentPane().setLayout(null);
		salarymanagement smg=new salarymanagement();
		JButton btnNewButton = new JButton("Gestion des salaires");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smg.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(177, 162, 150));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(325, 236, 360, 158);
		getContentPane().add(btnNewButton);
		this.setBounds(100, 100, 1000,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
