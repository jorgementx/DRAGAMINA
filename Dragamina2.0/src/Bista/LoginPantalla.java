package Bista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Eredua.Taula;

public class LoginPantalla extends JFrame {
	
	private static final long serialVersionUID = 3805581801146219635L;
	private static LoginPantalla nLoginPantalla = null;
	
	private LoginPantalla() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initialize();
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
		//contentPane.setLayout(new GridLayout(3,0));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		JLabel userLabel = new JLabel("Erabiltzaile izena sartu: ");
		contentPane.add(userLabel);
		
		JTextField userText = new JTextField();
		contentPane.add(userText);
		
		JButton botoia = new JButton("OK");
		botoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izena = userText.getText();
				if (izena.isEmpty()) {
					LoginPantalla.getLoginPantalla();
				} else {
					// Orain, jasotako erabiltzaile izena Taula 
					// klasean gorde Ranking-a kudeatzeko
					LoginPantalla.getLoginPantalla().dispose();
					ZailtasunPantaila.getZailtasunPantaila();
				}
			}
		});
		
		contentPane.add(botoia);
		return contentPane;
	}
	
	private void initialize() {
		this.setSize(300, 180);
		this.setResizable(false);
		this.setContentPane(getContentPane());
		this.setTitle("Dragamina");
		this.setLocationRelativeTo(null);	
		this.setContentPane(getContentPane());
		this.setIconImage(new ImageIcon(this.getClass().getResource("icono.png")).getImage());
		this.setVisible(true);
	}
	
	private class okMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			ZailtasunPantaila.getZailtasunPantaila().setVisible(false);
			Dragamina.getDragamina();
		}
	}
}