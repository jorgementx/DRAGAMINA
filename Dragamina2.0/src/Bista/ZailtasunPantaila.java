package Bista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;

import Bista.Dragamina;

import javax.swing.event.ChangeEvent;

public class ZailtasunPantaila extends JFrame {


	private static ZailtasunPantaila nZailtasunPantaila = null;
	private JPanel contentPane;
	private JPanel panelTestua;
	private JPanel panelBotoiak;
	private JPanel panelZailtasunBotoiak;
	private JPanel panelAdosBotoia;
	private JLabel lblAukeratuZailtasunmaila;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JRadioButton radioButton_3;
	private JButton btnAdos;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int zailtasuna;

	/**
	 * Create the frame.
	 */
	private ZailtasunPantaila() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialize();
	}
	
	public static ZailtasunPantaila getZailtasunPantaila() {
		if (nZailtasunPantaila == null) {
			nZailtasunPantaila = new ZailtasunPantaila();
		}
		return nZailtasunPantaila;
	}
	public void setZailtasuna(int pZailtasuna) {
		this.zailtasuna=pZailtasuna;
	}
	public int getZailtasuna() {
		return this.zailtasuna;
	}
	private void initialize() {
		this.setSize(300, 180); //Leihoaren tamaina
		this.setResizable(false); //Leihoaren tamaina aldatu ezin izateko
		this.setTitle("Dragamina"); //Leihoaren izena finkatu
		this.setLocationRelativeTo(null); //Leihoa monitorearen erdian pantailaratzeko
		this.setContentPane(getContentPane());
		this.setIconImage(new ImageIcon(this.getClass().getResource("icono.png")).getImage());
		this.setVisible(true);
	}
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout()); //contentPane-aren diseinua (hgap eta vgap gehitu behar izanez gero)
			contentPane.add(getPanelTestua(), BorderLayout.CENTER);
			contentPane.add(getPanelBotoiak(), BorderLayout.SOUTH);
		}
		return contentPane;
	}
	private JPanel getPanelTestua() {
		if (panelTestua == null) {
			panelTestua = new JPanel();
			panelTestua.setLayout(new BorderLayout());
			panelTestua.add(getLblAukeratuZailtasunmaila(), BorderLayout.CENTER);
		}
		return panelTestua;
	}
	private JPanel getPanelBotoiak() {
		if (panelBotoiak == null) {
			panelBotoiak = new JPanel();
			panelBotoiak.setLayout(new BorderLayout());
			panelBotoiak.add(getPanelZailtasunBotoiak());
			panelBotoiak.add(getPanelAdosBotoia(), BorderLayout.SOUTH);
		}
		return panelBotoiak;
	}
	private JPanel getPanelZailtasunBotoiak() {
		if (panelZailtasunBotoiak == null) {
			panelZailtasunBotoiak = new JPanel();
			panelZailtasunBotoiak.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
			panelZailtasunBotoiak.add(getRadioButton_1());
			panelZailtasunBotoiak.add(getRadioButton_2());
			panelZailtasunBotoiak.add(getRadioButton_3());
		}
		return panelZailtasunBotoiak;
	}
	private JPanel getPanelAdosBotoia() {
		if (panelAdosBotoia == null) {
			panelAdosBotoia = new JPanel();
			panelAdosBotoia.add(getBtnAdos());
		}
		return panelAdosBotoia;
	}
	private JLabel getLblAukeratuZailtasunmaila() {
		if (lblAukeratuZailtasunmaila == null) {
			lblAukeratuZailtasunmaila = new JLabel("Aukeratu zailtasun-maila:");
			lblAukeratuZailtasunmaila.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAukeratuZailtasunmaila;
	}
	private JRadioButton getRadioButton_1() {
		if (radioButton_1 == null) {
			radioButton_1 = new JRadioButton("1");
			radioButton_1.addMouseListener(new RadioButtonMouseListener());
			buttonGroup.add(radioButton_1);
		}
		return radioButton_1;
	}
	private JRadioButton getRadioButton_2() {
		if (radioButton_2 == null) {
			radioButton_2 = new JRadioButton("2");
			radioButton_2.addMouseListener(new RadioButtonMouseListener());
			buttonGroup.add(radioButton_2);
		}
		return radioButton_2;
	}
	private JRadioButton getRadioButton_3() {
		if (radioButton_3 == null) {
			radioButton_3 = new JRadioButton("3");
			radioButton_3.addMouseListener(new RadioButtonMouseListener());
			buttonGroup.add(radioButton_3);
		}
		return radioButton_3;
	}
	private JButton getBtnAdos() {
		if (btnAdos == null) {
			btnAdos = new JButton("Ados");
			btnAdos.setEnabled(false);
		}
		return btnAdos;
	}
	private class RadioButtonMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!(btnAdos.isEnabled())) { //Behin bakarrik egiteko
				btnAdos.setEnabled(true);
				btnAdos.addMouseListener(new BtnAdosMouseListener());
			}
			if (e.getComponent().equals(radioButton_1)) {
				ZailtasunPantaila.getZailtasunPantaila().setZailtasuna(1);
			}
			else if (e.getComponent().equals(radioButton_2)) {
				ZailtasunPantaila.getZailtasunPantaila().setZailtasuna(2);
			}
			else if (e.getComponent().equals(radioButton_3)) {
				ZailtasunPantaila.getZailtasunPantaila().setZailtasuna(3);
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (!(btnAdos.isEnabled())) { //Behin bakarrik egiteko
				btnAdos.setEnabled(true);
				btnAdos.addMouseListener(new BtnAdosMouseListener());
			}
			if (e.getComponent().equals(radioButton_1)) {
				ZailtasunPantaila.getZailtasunPantaila().setZailtasuna(1);
			}
			else if (e.getComponent().equals(radioButton_2)) {
				ZailtasunPantaila.getZailtasunPantaila().setZailtasuna(2);
			}
			else if (e.getComponent().equals(radioButton_3)) {
				ZailtasunPantaila.getZailtasunPantaila().setZailtasuna(3);
			}
		}
	}
	private class BtnAdosMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			ZailtasunPantaila.getZailtasunPantaila().setVisible(false); //Lehioa ezkutatzekoa
			Dragamina.getDragamina();
		}
	}
}
