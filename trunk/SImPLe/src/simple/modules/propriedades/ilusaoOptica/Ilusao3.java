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

package simple.modules.propriedades.ilusaoOptica;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import simple.ui.janelas.SImPLe;
import simple.facade.Facade;

/**
 * Classe responsável pela exibição da ilusão de ótica 3.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Ilusao3 extends JInternalFrame implements InternalFrameListener, MouseListener{

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;

	private PaletaDeCores paletaDeCores;

	private javax.swing.JLabel fundo;

	private javax.swing.JLabel fundo1;

	private javax.swing.JLabel titulo;

	private BotaoRedondo circulo1;

	private BotaoRedondo circulo2;

	private BotaoRedondo circulo3;

	private BotaoRedondo circulo4;
	
	private SImPLe fepdi;
	
	private Facade facade;
	
	private String nome = "Ilus\u00e3o de Ótica";

	/** Creates new form ilusao1 */
	public Ilusao3(SImPLe fepdi, Facade facade) {
		addMouseListener(this);
		addInternalFrameListener(this);
		this.setFrameIcon(new ImageIcon("Resource/Icones/fepdi.JPG"));
		this.setClosable(true);
		this.setMaximizable(false);
		this.setResizable(false);
		
		this.fepdi = fepdi;
		this.facade= facade;
		
		desenhaCirculo();
		initComponents();
		titulo.setSize(290,36);
	}

	private void desenhaCirculo() {
		circulo1 = new BotaoRedondo("");
		getContentPane().add(circulo1);
		circulo1.setBackground(Color.red);
		circulo1.setBounds(200, 80, 60, 60);
		circulo1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				mudaCorCirculo(circulo1);
			}
		});

		circulo2 = new BotaoRedondo("");
		getContentPane().add(circulo2);
		circulo2.setBackground(Color.red);
		circulo2.setBounds(340, 80, 60, 60);
		circulo2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				mudaCorCirculo(circulo2);
			}
		});

		circulo3 = new BotaoRedondo("");
		getContentPane().add(circulo3);
		circulo3.setBackground(Color.red);
		circulo3.setBounds(340, 222, 60, 60);
		circulo3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				mudaCorCirculo(circulo3);
			}
		});

		circulo4 = new BotaoRedondo("");
		getContentPane().add(circulo4);
		circulo4.setBackground(Color.red);
		circulo4.setBounds(200, 222, 60, 60);
		circulo4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				mudaCorCirculo(circulo4);
			}
		});
	}

	private void mudaCorCirculo(BotaoRedondo circulo) {
		if (paletaDeCores.isPinturaAtivada())
			circulo.setBackground(paletaDeCores.getCorSelecionada());
	}

	private void initComponents() {

		titulo = new javax.swing.JLabel();
		fundo1 = new javax.swing.JLabel();
		fundo = new javax.swing.JLabel();

		getContentPane().setLayout(null);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
		titulo.setBounds(170, 10, 270, 44);

		fundo1.setBackground(new java.awt.Color(255, 255, 204));
		fundo1.setOpaque(true);
		getContentPane().add(fundo1);
		fundo1.setBounds(230, 110, 140, 140);

		fundo.setBackground(new java.awt.Color(255, 255, 204));
		fundo.setOpaque(true);
		getContentPane().add(fundo);
		fundo.setBounds(190, 70, 220, 220);
		fundo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fundoMouseClicked(evt);
			}
		});

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 610) / 2, (screenSize.height - 421) / 2,
				610, 421);

		getContentPane().setComponentZOrder(fundo,
				getContentPane().getComponentCount() - 1);
		getContentPane().setComponentZOrder(fundo1, 0);
		getContentPane().repaint();
	}

	private void fundoMouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			fundo.setBackground(paletaDeCores.getCorSelecionada());
			fundo1.setBackground(paletaDeCores.getCorSelecionada());
			getContentPane().setComponentZOrder(fundo,
					getContentPane().getComponentCount() - 1);
			getContentPane().setComponentZOrder(fundo1, 0);
			getContentPane().repaint();
		}
	}
	
	public static JInternalFrame create(SImPLe fepdi, Facade facade){
		JInternalFrame ilusao =  new Ilusao3(fepdi,facade);
		ilusao.setVisible(true);
		return ilusao;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Ilusao3(null,null).setVisible(true);
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
		fepdi.closeIlusao(3);
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
