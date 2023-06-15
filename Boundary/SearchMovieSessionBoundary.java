package Boundary;

import Controller.GetRoomListController;
import Controller.SearchMovieSessionController;
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


public class SearchMovieSessionBoundary {
	private final SearchMovieSessionController smsc = new SearchMovieSessionController();
	private final GetRoomListController grlc = new GetRoomListController();
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel();
		
		String input = JOptionPane.showInputDialog(dialog, "Enter the movie name", "Input", JOptionPane.PLAIN_MESSAGE);
		
		if(input == null)
			return null;
		
		ArrayList<String[]> data = new ArrayList<>();
		ArrayList<String[]> roomData = new ArrayList<>();
		try {
			data = smsc.executeTask(input);
			roomData = grlc.executeTask();
			
			if(data.size() == 0) {
				JOptionPane.showMessageDialog(dialog, "Movie does not have a session", "Alert", JOptionPane.WARNING_MESSAGE);
				dialog.setVisible(false);
				
				return null;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		String roomColumn[] = new String[] {"ID", "Name"};
		
		String convertedRoomData[][] = new String[roomData.size()][2];
		
		for(int i = 0; i < roomData.size(); i++) {
			for(int j = 0; j < 2; j++) {
				convertedRoomData[i][j] = roomData.get(i)[j];
			}
		}
		
		DefaultTableModel tableModelRoom = new DefaultTableModel(convertedRoomData, roomColumn) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		JTable roomTable = new JTable(convertedRoomData, roomColumn);
		roomTable.setFont(new Font("Arial", Font.PLAIN, 25));
		roomTable.setRowHeight(35);
		roomTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		roomTable.setModel(tableModelRoom);
		
		TableColumnModel roomColumnModel = roomTable.getColumnModel();
		
		roomColumnModel.getColumn(0).setPreferredWidth(75);
		roomColumnModel.getColumn(1).setPreferredWidth(325);		
		
		JScrollPane roomScroll = new JScrollPane(roomTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		roomScroll.setPreferredSize(new Dimension(300, 350));
		
		String column[] = new String[] {"RoomId", "SessionTiming", "MovieId"};
		
		String convertedData[][] = new String[data.size()][3];
		
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < 3; j++) {
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
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(75);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		roomTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(450, 350));
		
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(roomScroll, BorderLayout.CENTER);
		
		return panel;
	}
}