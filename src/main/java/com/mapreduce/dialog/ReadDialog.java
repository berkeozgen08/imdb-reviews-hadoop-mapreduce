package com.mapreduce.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mapreduce.ReadWrite;
import com.mapreduce.Singletons;
import com.mapreduce.util.HadoopFileSystemView;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import java.util.Set;

public class ReadDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFileChooser fileChooser = new JFileChooser(new HadoopFileSystemView(Path.of("/")));
	private JLabel lblNewLabel_1;
	private final JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void create() {
		try {
			ReadDialog dialog = new ReadDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReadDialog() {
		setBounds(100, 100, 377, 118);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			lblNewLabel_1 = new JLabel("Input: ");
			contentPanel.add(lblNewLabel_1);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		{
			btnNewButton = new JButton("Choose");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switch (fileChooser.showOpenDialog(ReadDialog.this)) {
						case JFileChooser.APPROVE_OPTION:
							fileChooser.getSelectedFile();
							break;
						case JFileChooser.CANCEL_OPTION:
							break;					 
						case JFileChooser.ERROR_OPTION:
							btnNewButton.setText("Error");
							break;
					}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							ReadWrite.readFileFromHDFS(btnNewButton.getText());							
							// JOptionPane.showMessageDialog(okButton.getParent(), "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException err) {
							err.printStackTrace();
							// JOptionPane.showMessageDialog(okButton.getParent(), err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
