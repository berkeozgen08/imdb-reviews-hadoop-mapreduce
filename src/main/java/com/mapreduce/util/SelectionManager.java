package com.mapreduce.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class SelectionManager implements ActionListener {
	JComboBox<String> combo = null;
	List<String> selectedItems = new ArrayList<String>();
	List<String> nonSelectables = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		if (combo == null) {
			combo = (JComboBox<String>) e.getSource();
		}
		String item = (String) combo.getSelectedItem();
		// Toggle the selection state for item.
		if (selectedItems.contains(item)) {
			selectedItems.remove(item);
		} else if (!nonSelectables.contains(item)) {
			selectedItems.add(item);
		}
	}

	public void setNonSelectables(String... args) {
		for (int j = 0; j < args.length; j++) {
			nonSelectables.add(args[j]);
		}
	}

	public boolean isSelected(String item) {
		return selectedItems.contains(item);
	}
}
