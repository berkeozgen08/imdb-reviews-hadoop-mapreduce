package com.mapreduce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextPane;

import com.mapreduce.util.PrintStreamCapturer;
import com.mapreduce.util.ProcessHandler;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.mapreduce.dialog.TotalReviewsDialog;
import com.mapreduce.dialog.ReadDialog;
import com.mapreduce.dialog.WriteDialog;

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
		
		JPanel panel = new JPanel();
		panel.setBounds(7, 10, 870, 62);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 4, 0, 0));
		
		JLabel lblHadoop_2 = new JLabel("Hadoop: ");
		lblHadoop_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblHadoop_2);
		lblHadoop_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		final JTextPane txtpnAsd_1 = new JTextPane();
		panel_1.add(txtpnAsd_1);
		txtpnAsd_1.setText("off");
		txtpnAsd_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnAsd_1.setEditable(false);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(btnStop);
		
		JLabel lblHadoop = new JLabel("Yarn: ");
		lblHadoop.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblHadoop);
		lblHadoop.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		final JTextPane txtpnAsd = new JTextPane();
		panel_1.add(txtpnAsd);
		txtpnAsd.setEditable(false);
		txtpnAsd.setText("off");
		txtpnAsd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnStart_1 = new JButton("Start");
		btnStart_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(btnStart_1);
		
		JButton btnStop_1 = new JButton("Stop");
		btnStop_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(btnStop_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnRead = new JButton("Read");
		panel_2.add(btnRead);
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadDialog.create();
			}
		});
		btnRead.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnNewButton = new JButton("Write");
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WriteDialog.create();
			}
		});
		btnStop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("stop-yarn.sh");
					txtpnAsd.setText("off");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd.setText("error");
				}
			}
		});
		btnStart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("start-yarn.sh");
					txtpnAsd.setText("on");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd.setText("error");
				}
			}
		});
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("stop-dfs.sh");
					txtpnAsd_1.setText("off");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd_1.setText("error");
				}
			}
		});
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProcessHandler.run("start-dfs.sh");
					txtpnAsd_1.setText("on");
				} catch (IOException err) {
					err.printStackTrace();
					txtpnAsd_1.setText("error");
				}
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(7, 82, 870, 412);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JComboBox<Job> comboBox = new JComboBox<Job>();
		panel_4.add(comboBox);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel<Job>(Job.values()));
		comboBox.setSelectedIndex(0);

		JButton btnNewButton_1 = new JButton("Run");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Job job = (Job) comboBox.getSelectedItem();
				switch (job) {
					case TotalReviews:
						TotalReviewsDialog.create();
					default:
						break;
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_4.add(btnNewButton_1);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(1, 1, 0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		// panel_3.add(textArea);
		System.setOut(new PrintStreamCapturer(textArea, System.out));
		System.setErr(new PrintStreamCapturer(textArea, System.err, "[ERROR] "));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel_3.add(scrollPane);
	}

	public enum Job {
		TotalReviews,
		MovieAverage,
		MinMaxReview,
		DateAverage;
	}
}
