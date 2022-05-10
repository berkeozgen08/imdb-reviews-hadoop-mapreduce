package com.mapreduce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JTextPane;

import com.mapreduce.util.ProcessHandler;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 898, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 308, 93);
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Box verticalBox = Box.createVerticalBox();
		panel.add(verticalBox);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		JLabel lblHadoop_2 = new JLabel("Hadoop: ");
		horizontalBox.add(lblHadoop_2);
		lblHadoop_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		final JTextPane txtpnAsd_1 = new JTextPane();
		horizontalBox.add(txtpnAsd_1);
		txtpnAsd_1.setText("off");
		txtpnAsd_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnAsd_1.setEditable(false);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("start-dfs.sh");
					txtpnAsd_1.setText("on");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd_1.setText(err.getMessage());
				}
			}
		});
		horizontalBox.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("stop-dfs.sh");
					txtpnAsd_1.setText("off");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd_1.setText(err.getMessage());
				}
			}
		});
		horizontalBox.add(btnStop);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JLabel lblHadoop = new JLabel("Yarn: ");
		horizontalBox_1.add(lblHadoop);
		lblHadoop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		final JTextPane txtpnAsd = new JTextPane();
		horizontalBox_1.add(txtpnAsd);
		txtpnAsd.setEditable(false);
		txtpnAsd.setText("off");
		txtpnAsd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnStart_1 = new JButton("Start");
		btnStart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("start-yarn.sh");
					txtpnAsd.setText("on");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd.setText(err.getMessage());
				}
			}
		});
		horizontalBox_1.add(btnStart_1);
		
		JButton btnStop_1 = new JButton("Stop");
		btnStop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("stop-yarn.sh");
					txtpnAsd.setText("off");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd.setText(err.getMessage());
				}
			}
		});
		horizontalBox_1.add(btnStop_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(788, 0, 95, 494);
		frame.getContentPane().add(panel_1);
		
		JButton btnRead = new JButton("Read");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadDialog.create();
			}
		});
		btnRead.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(btnRead);
		
		JButton btnNewButton = new JButton("Write");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteDialog.create();
			}
		});
		panel_1.add(btnNewButton);
	}
}
