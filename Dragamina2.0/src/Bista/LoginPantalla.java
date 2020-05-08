package Bista;

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

public class LoginPantalla extends JFrame {
	
	private static final long serialVersionUID = 3805581801146219635L;
	private static LoginPantalla nLoginPantalla = null;
	private JPanel contentPane = new JPanel();
	private JLabel userLabel = new JLabel("Erabiltzaile izena sartu: ");
	private JTextField userText = new JTextField();
	private JButton botoia = new JButton("OK");
	private String jokIzena;
	
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
	
	public String getJokIzena() {
		return this.jokIzena;
	}
	
	public void setJokIzena(String izena) {
		this.jokIzena=izena;
	}
	
	public JPanel getContentPane() {
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		contentPane.add(userLabel);
		contentPane.add(userText);
		botoia.addMouseListener(new botoiMouseListener());
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
	
	private class botoiMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String izena = userText.getText();
			if (izena.isEmpty()) {
				LoginPantalla.getLoginPantalla();
			} else {
				LoginPantalla.getLoginPantalla().setJokIzena(izena);
				LoginPantalla.getLoginPantalla().dispose();
				ZailtasunPantaila.getZailtasunPantaila();
			}
		}
	}
}