import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class addfournisseur extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	static Connection conn = null;
	static Statement st = null;

	public addfournisseur() {
		getContentPane().setBackground(new Color(36, 37, 130));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 400, 300);
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(96, 60, 60, 22);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(177, 62, 96, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Adresse");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(96, 120, 60, 13);
		getContentPane().add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(177, 117, 96, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		addcommande adc = new addcommande();
		addfournisseur adf = this;
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(246, 76, 114));
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					System.out.println("error");
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");
					PreparedStatement st = conn.prepareStatement("insert into fournisseur (adresse,nom) values (?,?)");
					st.setString(1, textField_1.getText());
					st.setString(2, textField.getText());
					st.execute();
					adc.setVisible(true);
					adf.dispose();
				} catch (SQLException e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnNewButton.setBounds(153, 191, 85, 21);
		getContentPane().add(btnNewButton);
	}

	public static void main(String[] args) {
		addfournisseur adf = new addfournisseur();
		adf.setVisible(true);
	}
}
