package com.mapreduce.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.table.DefaultTableModel;

public class TableDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public TableDialog(String[] headers, Object[][] rows) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 1099, 684);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(rows, headers));
			int width = table.getSize().width;
			table.getColumnModel().getColumn(0).setPreferredWidth(width);
			table.getColumnModel().getColumn(1).setPreferredWidth(width);
			table.getColumnModel().getColumn(2).setPreferredWidth(width);
		}
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			scrollPane = new JScrollPane(table);
			contentPanel.add(scrollPane);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
