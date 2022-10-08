import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;

public class ChatServer {

	static JFrame frame;
	private JTextField textField;
	private JButton btnNewButton;
	private static JTextArea textArea;
	private static ServerSocket server;
	private static Socket conn;
	private JScrollPane scrollPane;
	private static JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServer window = new ChatServer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		server= new ServerSocket(8521);
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText("hors connexion");
		lblNewLabel_1.setForeground(Color.RED);
		conn = server.accept();
		lblNewLabel_1.setBounds(30, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		while (true) {
			if(conn.isClosed()){
				lblNewLabel_1.setForeground(Color.RED);
				lblNewLabel_1.setText("hors connexion");
			}else{
				lblNewLabel_1.setForeground(Color.GREEN);
				lblNewLabel_1.setText("En Ligne");
			}
			try {
				DataInputStream input = new DataInputStream(conn.getInputStream());
				String message = input.readUTF();
				textArea.setText(textArea.getText() + "\n" + "Oussama :" + message);
			} catch (Exception e) {
				textArea.setText(textArea.getText() + "\n" + "Problème de Connexion1 ");
				try {
					Thread.sleep(1000);
					System.exit(0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ChatServer() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setForeground(new Color(255, 128, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(10, 213, 322, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Envoyer");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
				DataOutputStream output;
				textArea.setText(textArea.getText()+"\n"+"Server:"+textField.getText());
				try {
					output = new DataOutputStream(conn.getOutputStream());
					output.writeUTF(textField.getText());
				} catch (IOException e1) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					System.exit(0);
				}
				textField.setText("");
			}
		});
		btnNewButton.setBounds(335, 220, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 414, 178);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Made With Gouaiche AND Jamal-Eddyn");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(135, 11, 205, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
