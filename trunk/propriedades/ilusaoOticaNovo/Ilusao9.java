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

import janelas.SImPLe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;





import facade.Facade;

/**
 * Classe responsável pela exibição da ilusão de ótica 9.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Ilusao9 extends JInternalFrame implements InternalFrameListener, MouseListener{

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;

	private PaletaDeCores paletaDeCores;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel10;

	private javax.swing.JLabel jLabel11;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JLabel jLabel5;

	private javax.swing.JLabel jLabel6;

	private javax.swing.JLabel jLabel7;

	private javax.swing.JLabel jLabel8;

	private javax.swing.JLabel jLabel9;

	private javax.swing.JLabel titulo;
	
	private SImPLe fepdi;
	
	private Facade facade;
	
	private String nome = "Ilus\u00e3o de Ótica";

	public Ilusao9(SImPLe fepdi, Facade facade) {
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
		titulo = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();

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
		titulo.setBounds(160, 20, 270, 44);

		jLabel2.setBackground(new java.awt.Color(51, 51, 51));
		jLabel2.setOpaque(true);
		jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel2MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel2);
		jLabel2.setBounds(120, 130, 20, 60);

		jLabel3.setBackground(new java.awt.Color(51, 51, 51));
		jLabel3.setOpaque(true);
		jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel3MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel3);
		jLabel3.setBounds(150, 130, 20, 60);

		jLabel4.setBackground(new java.awt.Color(51, 51, 51));
		jLabel4.setOpaque(true);
		jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel4MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel4);
		jLabel4.setBounds(180, 130, 20, 60);

		jLabel5.setBackground(new java.awt.Color(51, 51, 51));
		jLabel5.setOpaque(true);
		jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel5MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel5);
		jLabel5.setBounds(210, 130, 20, 60);

		jLabel6.setBackground(new java.awt.Color(51, 51, 51));
		jLabel6.setOpaque(true);
		jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel6MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel6);
		jLabel6.setBounds(240, 130, 20, 60);

		jLabel7.setBackground(new java.awt.Color(51, 51, 51));
		jLabel7.setOpaque(true);
		jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel7MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel7);
		jLabel7.setBounds(270, 130, 20, 60);

		jLabel11.setBackground(new java.awt.Color(51, 51, 51));
		jLabel11.setOpaque(true);
		jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel11MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel11);
		jLabel11.setBounds(420, 130, 20, 60);

		jLabel8.setFont(new java.awt.Font("Arial Black", 0, 18));
		jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel8.setText("A");
		getContentPane().add(jLabel8);
		jLabel8.setBounds(120, 190, 20, 20);

		jLabel9.setFont(new java.awt.Font("Arial Black", 0, 18));
		jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel9.setText("B");
		getContentPane().add(jLabel9);
		jLabel9.setBounds(270, 110, 20, 20);

		jLabel10.setFont(new java.awt.Font("Arial Black", 0, 18));
		jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel10.setText("C");
		getContentPane().add(jLabel10);
		jLabel10.setBounds(420, 190, 20, 20);

		jLabel1.setBackground(new java.awt.Color(204, 255, 204));
		jLabel1.setOpaque(true);
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel1MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel1);
		jLabel1.setBounds(80, 90, 430, 150);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 610) / 2, (screenSize.height - 421) / 2,
				610, 421);
	}

	private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
		mudaCorLinha();
	}

	private void mudaCorLinha() {
		if (paletaDeCores.isPinturaAtivada()) {
			jLabel2.setBackground(paletaDeCores.getCorSelecionada());
			jLabel3.setBackground(paletaDeCores.getCorSelecionada());
			jLabel4.setBackground(paletaDeCores.getCorSelecionada());
			jLabel5.setBackground(paletaDeCores.getCorSelecionada());
			jLabel6.setBackground(paletaDeCores.getCorSelecionada());
			jLabel7.setBackground(paletaDeCores.getCorSelecionada());
			jLabel11.setBackground(paletaDeCores.getCorSelecionada());
		}
	}

	private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			jLabel1.setBackground(paletaDeCores.getCorSelecionada());
			getContentPane().setComponentZOrder(jLabel1,
					getContentPane().getComponentCount() - 1);
			getContentPane().repaint();
		}
	}
	
	public static JInternalFrame create(SImPLe fepdi, Facade facade){
		JInternalFrame ilusao =  new Ilusao9(fepdi,facade);
		ilusao.setVisible(true);
		return ilusao;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Ilusao9(null,null).setVisible(true);
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
		SImPLe.ilusao9.setEnabled(true);
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
