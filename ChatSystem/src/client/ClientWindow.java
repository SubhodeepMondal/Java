package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientWindow {

	private JFrame frmChatSystem;
	private JTextField messageField;
	private static JTextArea textArea = new JTextArea();
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					window.frmChatSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
		String name=JOptionPane.showInputDialog("Enter Name:");
		client=new Client(name,"localhost",52864);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatSystem = new JFrame();
		frmChatSystem.setResizable(false);
		frmChatSystem.setTitle("Chat System");
		frmChatSystem.setBounds(100, 100, 600, 400);
		frmChatSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		frmChatSystem.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frmChatSystem.getContentPane().add(panel, BorderLayout.SOUTH);
		
		messageField = new JTextField();
		panel.add(messageField);
		messageField.setColumns(40);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(e->{
			if(!messageField.getText().equals("")) {
				client.send(messageField.getText());
				messageField.setText("");
			}
			
			
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnNewButton);
		
		frmChatSystem.setLocationRelativeTo(null);
		
	}
	
	public static void printToConsole(String message) {
		textArea.setText(textArea.getText()+message+"\n");
	}

}
