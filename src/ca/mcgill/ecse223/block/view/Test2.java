package ca.mcgill.ecse223.block.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test2 extends JFrame {
	public Test2() {
		getContentPane().setLayout(null);
		
		JButton btnClickMe = new JButton("Click me");
		btnClickMe.setBounds(54, 222, 117, 29);
		getContentPane().add(btnClickMe);
	}
}
