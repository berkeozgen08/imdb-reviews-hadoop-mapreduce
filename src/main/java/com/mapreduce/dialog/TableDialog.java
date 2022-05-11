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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.event.ActionEvent;

public class TableDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Create the dialog.
	 */
	public TableDialog(String[] headers, Object[][] rows) {
		setBounds(100, 100, 1099, 684);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			table = new JTable();
			table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 18));
			table.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String str = "";
						for (int i = 0; i < rows.length; i++) {
							int j;
							for (j = 0; j < rows[i].length - 1; j++) {
								str += rows[i][j] + "\t";
							}
							str += rows[i][j] + "\n";
						}
						try {
							System.out.println("\n\n");
							System.out.println("Wrote to " + Files.writeString(
								Path.of("output.txt"),
								str,
								StandardCharsets.UTF_8
							).toAbsolutePath().toString());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
