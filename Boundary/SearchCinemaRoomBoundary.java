package Boundary;

import Controller.SearchCinemaRoomController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class SearchCinemaRoomBoundary {
	private final SearchCinemaRoomController scrc = new SearchCinemaRoomController();
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel();
		
		String[] options = new String[] {"Available", "Not Available (Maintenance)", 
										 "Not Available (Renovation)", "Not Available (Other)"};
		String input = (String) (JOptionPane.showInputDialog(dialog, "Choose the cinema room state", 
								 "Input", JOptionPane.PLAIN_MESSAGE, null, options, options[0]));
		
		if(input == null)
			return null;
		
		ArrayList<String[]> data = new ArrayList<>();
		try {
			data = scrc.executeTask(input);
			
			if(data.size() == 0) {
				JOptionPane.showMessageDialog(dialog, "Cinema room with the state does not exist", "Alert", JOptionPane.WARNING_MESSAGE);
				dialog.setVisible(false);
				
				return null;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		String column[] = new String[] {"ID", "Name", "Capacity", "State"};
		
		String convertedData[][] = new String[data.size()][4];
		
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < 4; j++) {
				convertedData[i][j] = data.get(i)[j];
			}
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(convertedData, column) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		JTable table = new JTable(convertedData, column);
		table.setFont(new Font("Arial", Font.PLAIN, 25));
		table.setRowHeight(35);
		table.setModel(tableModel);
		
		TableColumnModel columnModel = table.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(75);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(325);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(700, 400));
		
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
}
