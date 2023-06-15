package Boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.UpdateMovieSessionController;
import Controller.GetMovieListController;
import Controller.GetRoomListController;
import Controller.RetrieveMovieSessionController;

public class UpdateMovieSessionBoundary {
	private final UpdateMovieSessionController umsc = new UpdateMovieSessionController();
	private final RetrieveMovieSessionController rmsc = new RetrieveMovieSessionController();
	private final GetRoomListController grlc = new GetRoomListController();
	private final GetMovieListController gmlc = new GetMovieListController();
	
	public JPanel constructBoundary(JDialog dialog, String roomId, String sessionTiming, String movieId){
		JPanel mainPanel = new JPanel(new GridBagLayout());
		JPanel otherPanel = new JPanel(new GridBagLayout());
		JPanel scrollPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
							
		JLabel movieIdLabel = new JLabel("Movie ID:");
		movieIdLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		movieIdLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel roomIdLabel = new JLabel("Room ID:");
		roomIdLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		roomIdLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel yearLabel = new JLabel("Year:");
		yearLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		yearLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel monthLabel = new JLabel("Month:");
		monthLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		monthLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		dayLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel hourLabel = new JLabel("Hour:");
		hourLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		hourLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel minuteLabel = new JLabel("Minute:");
		minuteLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		minuteLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[][] data = new String[][] {};
		try {
			data = rmsc.executeTask(roomId, sessionTiming, movieId);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		String oldMovieID = data[0][2];
		
		JTextField movieIdField = new JTextField(8);
		movieIdField.setText(oldMovieID);
		movieIdField.setFont(new Font("Arial", Font.PLAIN, 25));
		movieIdField.setColumns(4);
		
		JLabel roomIdResultLabel = new JLabel(data[0][0]);
		roomIdResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		roomIdResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel yearResultLabel = new JLabel(data[0][1].substring(0, 4));
		yearResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		yearResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel monthResultLabel = new JLabel(data[0][1].substring(5, 7));
		monthResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		monthResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel dayResultLabel = new JLabel(data[0][1].substring(8, 10));
		dayResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		dayResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel hourResultLabel = new JLabel(data[0][1].substring(11, 13));
		hourResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		hourResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel minuteResultLabel = new JLabel(data[0][1].substring(14, 16));
		minuteResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		minuteResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		ArrayList<String[]> movieData = new ArrayList<>();
		ArrayList<String[]> roomData = new ArrayList<>();
		
		try {
			movieData = gmlc.executeTask();
			roomData = grlc.executeTask();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		String column[] = new String[] {"ID", "Name"};
		
		String convertedMovieData[][] = new String[movieData.size()][2];
		String convertedRoomData[][] = new String[roomData.size()][2];
		
		for(int i = 0; i < movieData.size(); i++) {
			for(int j = 0; j < 2; j++) {
				convertedMovieData[i][j] = movieData.get(i)[j];
			}
		}
		
		for(int i = 0; i < roomData.size(); i++) {
			for(int j = 0; j < 2; j++) {
				convertedRoomData[i][j] = roomData.get(i)[j];
			}
		}
		
		DefaultTableModel tableModelMovie = new DefaultTableModel(convertedMovieData, column) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};

		JTable movieTable = new JTable(convertedMovieData, column);
		movieTable.setFont(new Font("Arial", Font.PLAIN, 25));
		movieTable.setRowHeight(35);
		movieTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		movieTable.setModel(tableModelMovie);
		
		TableColumnModel movieColumnModel = movieTable.getColumnModel();
		
		movieColumnModel.getColumn(0).setPreferredWidth(75);
		movieColumnModel.getColumn(1).setPreferredWidth(325);
		
		DefaultTableModel tableModelRoom = new DefaultTableModel(convertedRoomData, column) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		JTable roomTable = new JTable(convertedRoomData, column);
		roomTable.setFont(new Font("Arial", Font.PLAIN, 25));
		roomTable.setRowHeight(35);
		roomTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		roomTable.setModel(tableModelRoom);
		
		TableColumnModel roomColumnModel = roomTable.getColumnModel();
		
		roomColumnModel.getColumn(0).setPreferredWidth(75);
		roomColumnModel.getColumn(1).setPreferredWidth(325);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		movieTable.setDefaultRenderer(Object.class, centerRenderer);
		roomTable.setDefaultRenderer(Object.class, centerRenderer);
		
		JScrollPane movieScroll = new JScrollPane(movieTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		movieScroll.setPreferredSize(new Dimension(300, 200));
		
		JScrollPane roomScroll = new JScrollPane(roomTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		roomScroll.setPreferredSize(new Dimension(300, 200));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 25));
		cancelButton.setPreferredSize(new Dimension(125, 50));
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				dialog.setVisible(false);
			}
		});
		
		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		enterButton.setPreferredSize(new Dimension(125, 50));
		enterButton.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent evt){	
				try {
						
					if(!umsc.checkMovieId(movieIdField.getText())) {					
						JOptionPane.showMessageDialog(dialog, "Movie ID does not exist", "Alert", JOptionPane.WARNING_MESSAGE);
						
					} else {
						String result = umsc.executeTask(roomIdResultLabel.getText(), sessionTiming, movieIdField.getText(), oldMovieID);
						
						if(result.equals("Successful")) {
							JOptionPane.showMessageDialog(dialog, result);
							dialog.setVisible(false);
							
						} else {
							JOptionPane.showMessageDialog(dialog, result, "Alert", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
						
		gbc.gridx = 0;
		gbc.gridy = 0;
		otherPanel.add(roomIdLabel, gbc);
						
		gbc.gridx++;
		otherPanel.add(roomIdResultLabel, gbc);
						
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(movieIdLabel, gbc);
						
		gbc.gridx++;
		otherPanel.add(movieIdField, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(yearLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(yearResultLabel, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(monthLabel, gbc);
					
		gbc.gridx++;
		otherPanel.add(monthResultLabel, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(dayLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(dayResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(hourLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(hourResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(minuteLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(minuteResultLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		scrollPanel.add(roomScroll, gbc);
		
		gbc.gridy++;
		scrollPanel.add(movieScroll, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(otherPanel, gbc);
		
		gbc.gridx++;
		mainPanel.add(scrollPanel, gbc);
		
		gbc.gridy++;
		mainPanel.add(enterButton, gbc);
		
		gbc.gridx--;
		mainPanel.add(cancelButton, gbc);
		
		return mainPanel;
	}
}