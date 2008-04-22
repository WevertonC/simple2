/*
 * UNIVERSIDADE FEDERAL DE CAMPINA GRANDE
 * CENTRO DE ENGENHARIA ELÉTRICA E INFORMÁTICA
 * DEPARTAMENTO DE SISTEMAS E COMPUTAÇÃO
 * 
 * DISCIPLINA: PROCESSAMENTO DIGITAL DE IMAGENS
 * PROFESSOR: EUSTÁQUIO RANGEL
 * ALUNOS: LUCIANA CAVALCANTE DE MENEZES
 *         RICARDO MADEIRA FERNANDES
 * 
 * PROJETO: ILUSÕES DE ÓTICA.
 */
package ilusaoOticaNovo;

import facade.Facade;

import janelas.SImPLe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;





/**
 * Classe responsável pela exibição da ilusão de ótica 6.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Ilusao6 extends JInternalFrame implements InternalFrameListener, MouseListener {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;

	private PaletaDeCores paletaDeCores;

	private JLabel barra1;

	private JLabel barra10;

	private JLabel barra11;

	private JLabel barra12;

	private JLabel barra2;

	private JLabel barra3;

	private JLabel barra4;

	private JLabel barra5;

	private JLabel barra6;

	private JLabel barra7;

	private JLabel barra8;

	private JLabel barra9;

	private JLabel barraV1;

	private JLabel barraV10;

	private JLabel barraV2;

	private JLabel barraV3;

	private JLabel barraV4;

	private JLabel barraV5;

	private JLabel barraV6;

	private JLabel barraV7;

	private JLabel barraV8;

	private JLabel barraV9;

	private JLabel fundo;

	private JLabel titulo;
	
	private SImPLe fepdi;
	
	private Facade facade;
	
	private String nome = "Ilus\u00e3o de Ótica";

	public Ilusao6(SImPLe fepdi, Facade facade) {
		addMouseListener(this);
		addInternalFrameListener(this);
		this.setFrameIcon(new ImageIcon("Resource/Icones/fepdi.JPG"));
		this.setClosable(true);
		this.setMaximizable(false);
		this.setResizable(false);
		
		this.fepdi = fepdi;
		this.facade= facade;
		
		initComponents();
		titulo.setSize(290,36);
	}

	private void initComponents() {
		titulo = new JLabel();
		barra1 = new JLabel();
		barra2 = new JLabel();
		barra3 = new JLabel();
		barra5 = new JLabel();
		barra6 = new JLabel();
		barra7 = new JLabel();
		barra8 = new JLabel();
		barra9 = new JLabel();
		barra10 = new JLabel();
		barra11 = new JLabel();
		barra12 = new JLabel();
		barraV1 = new JLabel();
		barraV2 = new JLabel();
		barra4 = new JLabel();
		barraV3 = new JLabel();
		barraV4 = new JLabel();
		barraV5 = new JLabel();
		barraV6 = new JLabel();
		barraV7 = new JLabel();
		barraV8 = new JLabel();
		barraV9 = new JLabel();
		barraV10 = new JLabel();
		fundo = new JLabel();

		getContentPane().setLayout(null);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(nome);
		setBackground(new java.awt.Color(255, 255, 255));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setForeground(java.awt.Color.darkGray);
		setResizable(false);
		getAccessibleContext().setAccessibleParent(this);

		paletaDeCores = new PaletaDeCores(this);

		titulo.setFont(new java.awt.Font("Tahoma", 1, 36));
		titulo.setText(nome);
		getContentPane().add(titulo);
		titulo.setBounds(180, 20, 270, 44);

		barra1.setBackground(new java.awt.Color(0, 0, 0));
		barra1.setOpaque(true);
		getContentPane().add(barra1);
		barra1.setBounds(210, 100, 5, 40);

		barra2.setBackground(new java.awt.Color(0, 0, 0));
		barra2.setOpaque(true);
		getContentPane().add(barra2);
		barra2.setBounds(285, 100, 5, 40);

		barra3.setBackground(new java.awt.Color(0, 0, 0));
		barra3.setOpaque(true);
		getContentPane().add(barra3);
		barra3.setBounds(360, 100, 5, 40);

		barra5.setBackground(new java.awt.Color(0, 0, 0));
		barra5.setOpaque(true);
		getContentPane().add(barra5);
		barra5.setBounds(210, 160, 5, 40);

		barra6.setBackground(new java.awt.Color(0, 0, 0));
		barra6.setOpaque(true);
		getContentPane().add(barra6);
		barra6.setBounds(285, 160, 5, 40);

		barra7.setBackground(new java.awt.Color(0, 0, 0));
		barra7.setOpaque(true);
		getContentPane().add(barra7);
		barra7.setBounds(360, 160, 5, 40);

		barra8.setBackground(new java.awt.Color(0, 0, 0));
		barra8.setOpaque(true);
		getContentPane().add(barra8);
		barra8.setBounds(435, 160, 5, 40);

		barra9.setBackground(new java.awt.Color(0, 0, 0));
		barra9.setOpaque(true);
		getContentPane().add(barra9);
		barra9.setBounds(210, 220, 5, 40);

		barra10.setBackground(new java.awt.Color(0, 0, 0));
		barra10.setOpaque(true);
		getContentPane().add(barra10);
		barra10.setBounds(285, 220, 5, 40);

		barra11.setBackground(new java.awt.Color(0, 0, 0));
		barra11.setOpaque(true);
		getContentPane().add(barra11);
		barra11.setBounds(360, 220, 5, 40);

		barra12.setBackground(new java.awt.Color(0, 0, 0));
		barra12.setOpaque(true);
		getContentPane().add(barra12);
		barra12.setBounds(435, 220, 5, 40);

		barraV1.setBackground(new java.awt.Color(0, 0, 0));
		barraV1.setOpaque(true);
		getContentPane().add(barraV1);
		barraV1.setBounds(150, 147, 50, 5);

		barraV2.setBackground(new java.awt.Color(0, 0, 0));
		barraV2.setOpaque(true);
		getContentPane().add(barraV2);
		barraV2.setBounds(225, 147, 50, 5);

		barra4.setBackground(new java.awt.Color(0, 0, 0));
		barra4.setOpaque(true);
		getContentPane().add(barra4);
		barra4.setBounds(435, 100, 5, 40);

		barraV3.setBackground(new java.awt.Color(0, 0, 0));
		barraV3.setOpaque(true);
		getContentPane().add(barraV3);
		barraV3.setBounds(300, 147, 50, 5);

		barraV4.setBackground(new java.awt.Color(0, 0, 0));
		barraV4.setOpaque(true);
		getContentPane().add(barraV4);
		barraV4.setBounds(375, 147, 50, 5);

		barraV5.setBackground(new java.awt.Color(0, 0, 0));
		barraV5.setOpaque(true);
		getContentPane().add(barraV5);
		barraV5.setBounds(450, 147, 50, 5);

		barraV6.setBackground(new java.awt.Color(0, 0, 0));
		barraV6.setOpaque(true);
		getContentPane().add(barraV6);
		barraV6.setBounds(150, 207, 50, 5);

		barraV7.setBackground(new java.awt.Color(0, 0, 0));
		barraV7.setOpaque(true);
		getContentPane().add(barraV7);
		barraV7.setBounds(225, 207, 50, 5);

		barraV8.setBackground(new java.awt.Color(0, 0, 0));
		barraV8.setOpaque(true);
		getContentPane().add(barraV8);
		barraV8.setBounds(300, 207, 50, 5);

		barraV9.setBackground(new java.awt.Color(0, 0, 0));
		barraV9.setOpaque(true);
		getContentPane().add(barraV9);
		barraV9.setBounds(375, 207, 50, 5);

		barraV10.setBackground(new java.awt.Color(0, 0, 0));
		barraV10.setOpaque(true);
		getContentPane().add(barraV10);
		barraV10.setBounds(450, 207, 50, 5);

		fundo.setBackground(new java.awt.Color(255, 255, 255));
		fundo.setOpaque(true);
		fundo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fundoMouseClicked(evt);
			}
		});

		getContentPane().add(fundo);
		fundo.setBounds(120, 80, 410, 210);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 610) / 2, (screenSize.height - 421) / 2,
				610, 421);
	}

	private void fundoMouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			fundo.setBackground(paletaDeCores.getCorSelecionada());
			getContentPane().setComponentZOrder(fundo,
					getContentPane().getComponentCount() - 1);
			getContentPane().repaint();
		}
	}
	
	public static JInternalFrame create(SImPLe fepdi, Facade facade){
		JInternalFrame ilusao =  new Ilusao6(fepdi,facade);
		ilusao.setVisible(true);
		return ilusao;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Ilusao6(null,null).setVisible(true);
			}
		});
	}

	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		fepdi.verificaUltimo();
		facade.decrementaPosicao();
		SImPLe.ilusao6.setEnabled(true);
	}

	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
