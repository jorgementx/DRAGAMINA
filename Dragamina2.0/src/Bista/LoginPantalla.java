package Bista;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Eredua.Taula;

public class LoginPantalla extends JFrame implements Observer {
	
	private static final long serialVersionUID = 3805581801146219635L;
	private static LoginPantalla nLoginPantalla = null;
	
	private LoginPantalla() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
		Taula.getTaula().addObserver(this);
	}
	
	public static LoginPantalla getLoginPantalla() {
		if (nLoginPantalla==null) {
			nLoginPantalla = new LoginPantalla();
		}
		return nLoginPantalla;
	}
	
	public JPanel getContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
		
		JLabel userLabel = new JLabel("Erabiltzaile izena sartu: ");
		contentPane.add(userLabel);
		contentPane.add(Box.createRigidArea(new Dimension(50, 50)));
		
		JTextField userText = new JTextField();
		contentPane.add(userText);
		contentPane.add(Box.createRigidArea(new Dimension(50, 50)));
		
		JButton botoia = new JButton("OK");
		// botoia.addActionListener
		contentPane.add(botoia);
		
		return contentPane;
	}
	
	private void initialize() {
		this.setSize(300, 380);
		this.setResizable(false);
		this.setContentPane(getContentPane());
		this.setTitle("Login Pantalla");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//TODO
	}
	
	// Probak egiteko "main" hau sortu dut
	// Gero hau ezabatuko dut
	public static void main(String[] args) {
		LoginPantalla nLogin = LoginPantalla.getLoginPantalla();
	}
}