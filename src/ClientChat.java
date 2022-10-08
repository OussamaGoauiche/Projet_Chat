import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class ClientChat {

	private static JFrame frame;
	private JTextField textField;
	private JButton btnNewButton;
	private static JTextArea textArea;
	private static Socket conn;
	private JScrollPane scrollPane;
	private static JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChat window = new ClientChat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		lblNewLabel_1 = new JLabel();
		conn = new Socket("localhost",8521);
		lblNewLabel_1.setBounds(30, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		while (true) {
			try {
				if(conn.isClosed()){
					lblNewLabel_1.setForeground(Color.RED);
					lblNewLabel_1.setText("hors connexion");
				}else{
					lblNewLabel_1.setForeground(Color.GREEN);
					lblNewLabel_1.setText("En Ligne");
				}
				DataInputStream input = new DataInputStream(conn.getInputStream());
				String message = input.readUTF();
				textArea.setText(textArea.getText() + "\n" + "Server :" + message);
			} catch (Exception e) {
				textArea.setText(textArea.getText() + "\n" + "Problème de Connexion ");
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
	 */
	public ClientChat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
				textArea.setText(textArea.getText()+"\n"+"Oussama:"+textField.getText());
				DataOutputStream output;
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
