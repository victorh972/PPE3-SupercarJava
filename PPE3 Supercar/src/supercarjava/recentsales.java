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
import javax.swing.table.DefaultTableModel;

public class recentsales extends JFrame {
	static Connection conn=null;
	static Statement st=null;
	   ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
	public recentsales() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        }
		catch(ClassNotFoundException e1) 
		{System.out.println("error");}
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava", "root", "");					
		PreparedStatement st=conn.prepareStatement("select * from ventes where confirmed=false");
	   ResultSet s= st.executeQuery();
	
	 while(s.next())
	 {
		 ArrayList<String> as=new ArrayList<String>();
		 PreparedStatement st4=conn.prepareStatement("select * from voiture where id=?");
		 st4.setInt(1, s.getInt(5));
		 as.add(String.valueOf(s.getInt(1)));
		 ResultSet d=st4.executeQuery();
		 while(d.next())
			 as.add(d.getString(2));
		 as.add(String.valueOf(s.getInt(4)));
		 as.add("confirmed");
		 array.add(as);
	    }
		}
	catch(SQLException e2)
	{
		System.out.println(e2.getMessage());	
	}
		String[][] data = new String[this.array.size()][];
		for (int i = 0; i < this.array.size(); i++) {
		    ArrayList<String> row = this.array.get(i);
		    data[i] = row.toArray(new String[row.size()]);
		}
	  
	        // nom des colonnes
	        String[] columnNames = { "id","voiture", "Montant", "" };
	  
	        // Initialiser le JTable
	        JTable j = new JTable(data, columnNames);
	        j.setBounds(30, 40, 200, 300);
	  
	        // l'ajouté à JScrollPane
	        JScrollPane sp = new JScrollPane(j);
	        this.add(sp);
	        // taille de la frame
	        this.setSize(500, 200);
	        // Frame Visible = true
	        this.setVisible(true);
	        Action delete = new AbstractAction()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	                JTable table = (JTable)e.getSource();
	                int modelRow = Integer.valueOf( e.getActionCommand() );
	                int id=Integer.parseInt(array.get(modelRow).get(0));
	                System.out.println(id);
	                try {
	        			Class.forName("com.mysql.jdbc.Driver");
	        	        }
	        		catch(ClassNotFoundException e1) 
	        		{System.out.println("error");}
	        		try {
	        			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/supercar", "root", "");					
	        		PreparedStatement st=conn.prepareStatement("select id_vendeur from ventes where id=?");
	        		PreparedStatement st3=conn.prepareStatement("update ventes set confirmed=true where id=?");
	        		st.setInt(1, id);
	        		st3.setInt(1, id);
	        		st3.executeUpdate();
	        	   ResultSet s= st.executeQuery();
	        	 while(s.next())
	        	 {
	        		 int id_ven=s.getInt(1);
	        		 PreparedStatement st2=conn.prepareStatement("update vendeur set commision=commision+3500 where id=?");
	        		 st2.setInt(1, id_ven);
	        		 st2.execute();
	        	    }
	        		}
	        	catch(SQLException e2)
	        	{
	        		System.out.println(e2.getMessage());	
	        	}
	              }
	        };
	        ButtonColumn buttonColumn = new ButtonColumn(j, delete, 3);
	        buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
public static void main(String[] args)
{
	recentsales rct=new recentsales();
	rct.setVisible(true);
}
}
