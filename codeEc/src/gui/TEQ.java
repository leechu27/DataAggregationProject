package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import database.DatabaseQuery;
import databaseSelector.DatabaseSelector;
import reports.PieGraphReport;
import reports.Report;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TEQ extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JLabel lblTeq;
	private JButton btnNewButton;
	private Report report;
	private DatabaseQuery database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEQ frame = new TEQ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TEQ() {
		setTitle("TEQ\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(43, 91, 66, 28);
		contentPane.add(lblTable);
		
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(43, 133, 66, 14);
		contentPane.add(lblCondition);
		
		JLabel lblNewLabel = new JLabel("Label\r\n");
		lblNewLabel.setBounds(43, 173, 66, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(119, 130, 238, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 95, 238, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 170, 238, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Create button clicked");
				report.writeToFile();
			}
		});
		btnCreate.setBounds(245, 365, 89, 23);
		contentPane.add(btnCreate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 227, 226, 127);
		contentPane.add(scrollPane);
		
		table = new JTable(new DefaultTableModel());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Label", "Condition", "Count"
			}
		));
		scrollPane.setViewportView(table);
		
		lblTeq = new JLabel("TEQ");
		lblTeq.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTeq.setBounds(245, 37, 107, 40);
		contentPane.add(lblTeq);
		
		btnNewButton = new JButton("Filter\r\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Filter button clicked");
				String tableInput = textField.getText();
				String condition = textField_1.getText();
				String label = textField_2.getText();
				System.out.println("Table: " + tableInput);
				System.out.println("Condition: " + condition);
				System.out.println("Label: " + label);
				if (!tableInput.equals("") && !condition.equals("") && !label.equals("")) {
				    DefaultTableModel model = (DefaultTableModel) table.getModel();
				    
				    int count;
                    try {
                      count = DatabaseSelector.countRows(database, tableInput, condition);
                      model.addRow(new Object[]{label, condition, count});
                    } catch (SQLException e) {
                      e.printStackTrace();
                    }

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(382, 94, 131, 93);
		contentPane.add(btnNewButton);
		
		database = new DatabaseQuery("test.db");
		
		String choosertitle = null;
        
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
   
        chooser.setAcceptAllFileFilterUsed(false);
        
        try {
          if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
            System.out.println("getCurrentDirectory(): "+  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
            if (chooser.getSelectedFile().toString().endsWith(".png")) {
              report = new PieGraphReport(chooser.getSelectedFile().toString(), "Report");
            } else {
              report = new PieGraphReport("report.png", "Report");
            }
          }
          else {
            System.out.println("No Selection ");
             report = new PieGraphReport("report.png", "Report");
            
          }
        } catch (InvalidFileException e1) {
          e1.printStackTrace();
        }
	}
}
