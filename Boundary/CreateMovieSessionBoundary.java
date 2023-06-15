package Boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Controller.CreateMovieSessionController;
import Controller.GetMovieListController;
import Controller.GetRoomListController;

public class CreateMovieSessionBoundary {
	private int num;
	
	private final CreateMovieSessionController cmsc = new CreateMovieSessionController();
	private final GetRoomListController grlc = new GetRoomListController();
	private final GetMovieListController gmlc = new GetMovieListController();
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel mainPanel = new JPanel(new GridBagLayout());
		JPanel otherPanel = new JPanel(new GridBagLayout());
		JPanel scrollPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		String input = JOptionPane.showInputDialog(null, "Enter the number of movie session you want to enter");
		
		if(input == null)
			return null;
			
		while(!cmsc.validateInput(input)) {
			JOptionPane.showMessageDialog(null, "Invalid input", "Alert", JOptionPane.WARNING_MESSAGE);
			
			input = JOptionPane.showInputDialog(null, "Enter the number of movie session you want to enter");
			
			if(null == input)
				return null;
		}
		num = Integer.parseInt(input);
							
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
			
		JTextField movieIdField = new JTextField(8);
		movieIdField.setFont(new Font("Arial", Font.PLAIN, 25));
		movieIdField.setColumns(4);
			
		JTextField roomIdField = new JTextField(8);
		roomIdField.setFont(new Font("Arial", Font.PLAIN, 25));
		roomIdField.setColumns(4);
		
		SpinnerModel yearValue = new SpinnerNumberModel(Year.now().getValue(), Year.now().getValue(), 2150, 1);
		JSpinner yearSpinner = new JSpinner(yearValue);
		yearSpinner.setFont(new Font("Arial", Font.PLAIN, 25));
		
		NumberEditor yearEditor = new JSpinner.NumberEditor(yearSpinner, "#");
		yearEditor.getTextField().setEnabled(false);
		yearEditor.getTextField().setEditable(false);
		yearEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		
		yearSpinner.setEditor(yearEditor);
		
		SpinnerModel monthValue = new SpinnerNumberModel(Calendar.getInstance().get(Calendar.MONTH) + 1, 1, 12, 1);
		JSpinner monthSpinner = new JSpinner(monthValue);
		monthSpinner.setFont(new Font("Arial", Font.PLAIN, 25));
		
		DefaultEditor monthEditor = new JSpinner.DefaultEditor(monthSpinner);
		monthEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		monthEditor.getTextField().setColumns(4);
		
		monthSpinner.setEditor(monthEditor);
		
		SpinnerModel dayValue = new SpinnerNumberModel(LocalDateTime.now().getDayOfMonth(), 1, 31, 1);
		JSpinner daySpinner = new JSpinner(dayValue);
		daySpinner.setFont(new Font("Arial", Font.PLAIN, 25));
		
		DefaultEditor dayEditor = new JSpinner.DefaultEditor(daySpinner);
		dayEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		dayEditor.getTextField().setColumns(4);
		
		daySpinner.setEditor(dayEditor);
		
		SpinnerModel hourValue = new SpinnerNumberModel(0, 0, 23, 1);
		JSpinner hourSpinner = new JSpinner(hourValue);
		hourSpinner.setFont(new Font("Arial", Font.PLAIN, 25));
		
		DefaultEditor hourEditor = new JSpinner.DefaultEditor(hourSpinner);
		hourEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		hourEditor.getTextField().setColumns(4);
		
		hourSpinner.setEditor(hourEditor);
		
		SpinnerModel minuteValue = new SpinnerNumberModel(0, 0, 59, 1);
		JSpinner minuteSpinner = new JSpinner(minuteValue);
		minuteSpinner.setFont(new Font("Arial", Font.PLAIN, 25));
		
		DefaultEditor minuteEditor = new JSpinner.DefaultEditor(minuteSpinner);
		minuteEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		minuteEditor.getTextField().setColumns(4);
		
		minuteSpinner.setEditor(minuteEditor);
		
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
		       //all cells false
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

		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		enterButton.setPreferredSize(new Dimension(125, 50));
		enterButton.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent evt){
				String sessionTiming = "" + yearSpinner.getValue() + "-";
				
				int month = (int) (monthSpinner.getValue());
				if(month < 10)
					sessionTiming += "0" + month + "-";
				else
					sessionTiming += month + "-";
				
				int day = (int) (daySpinner.getValue());
				if(day < 10)
					sessionTiming += "0" + day + " ";
				else
					sessionTiming += day + " ";
				
				int hour = (int) (hourSpinner.getValue());
				if(hour < 10)
					sessionTiming += "0" + hour + ":";
				else
					sessionTiming += hour + ":";
				
				int minute = (int) (minuteSpinner.getValue());
				if(minute < 10)
					sessionTiming += "0" + minute + ":00";
				else
					sessionTiming += minute + ":00";
				
				if(cmsc.isDateValid(sessionTiming)) {
					try {
						
						if(!cmsc.checkMovieId(movieIdField.getText())) {					
							JOptionPane.showMessageDialog(dialog, "Movie ID does not exist", "Alert", JOptionPane.WARNING_MESSAGE);
						
						} else if(!cmsc.checkRoomId(roomIdField.getText())) {
							JOptionPane.showMessageDialog(dialog, "Room ID does not exist", "Alert", JOptionPane.WARNING_MESSAGE);
						
						} else if(!cmsc.isRoomValid(roomIdField.getText())) {
							JOptionPane.showMessageDialog(dialog, "Room is not available", "Alert", JOptionPane.WARNING_MESSAGE);
						
						} else {
							System.out.println("Here before execute task");
							String result = cmsc.executeTask(roomIdField.getText(), sessionTiming, movieIdField.getText());
							
							if(result.equals("Successful")) {
								JOptionPane.showMessageDialog(dialog, result);
								num--;
							} else {
								JOptionPane.showMessageDialog(dialog, result, "Alert", JOptionPane.WARNING_MESSAGE);
							}
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
					} catch (Exception e) {
						JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
					}
					
					roomIdField.setText("");
					movieIdField.setText("");
				} else { 
					JOptionPane.showMessageDialog(dialog, "Invalid date", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				
				if(num == 0) 
					dialog.setVisible(false);
			}
		});
						
		gbc.gridx = 0;
		gbc.gridy = 0;
		otherPanel.add(roomIdLabel, gbc);
						
		gbc.gridx++;
		otherPanel.add(roomIdField, gbc);
						
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(movieIdLabel, gbc);
						
		gbc.gridx++;
		otherPanel.add(movieIdField, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(yearLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(yearSpinner, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(monthLabel, gbc);
					
		gbc.gridx++;
		otherPanel.add(monthSpinner, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(dayLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(daySpinner, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(hourLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(hourSpinner, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		otherPanel.add(minuteLabel, gbc);
		
		gbc.gridx++;
		otherPanel.add(minuteSpinner, gbc);
		
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
		
		return mainPanel;
	}
}