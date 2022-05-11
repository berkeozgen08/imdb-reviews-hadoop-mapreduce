package com.mapreduce.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mapreduce.ReadWrite;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class WriteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void create() {
		try {
			WriteDialog dialog = new WriteDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WriteDialog() {
		setBounds(100, 100, 317, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{17, 215, 0};
		gbl_contentPanel.rowHeights = new int[]{39, 39, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblNewLabel_1 = new JLabel("Input: ");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		{
			textField_1 = new JTextField();
			lblNewLabel_1.setLabelFor(textField_1);
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 0;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField_1.setColumns(10);
		}
		{
			lblNewLabel = new JLabel("Output: ");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		{
			textField = new JTextField();
			lblNewLabel.setLabelFor(textField);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField.setColumns(10);
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
							ReadWrite.writeFile(textField.getText(), textField_1.getText());							
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
