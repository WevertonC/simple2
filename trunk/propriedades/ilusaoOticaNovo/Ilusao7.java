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
 * Classe responsável pela exibição da ilusão de ótica 7.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Ilusao7 extends JInternalFrame implements InternalFrameListener, MouseListener{

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;

	private PaletaDeCores paletaDeCores;

	private JLabel jLabel17;

	private JLabel jLabel18;

	private JLabel jLabel19;

	private JLabel jLabel20;

	private JLabel jLabel21;

	private JLabel jLabel22;

	private JLabel jLabel23;

	private JLabel jLabel24;

	private JLabel jLabel25;

	private JLabel jLabel26;

	private JLabel jLabel27;

	private JLabel jLabel28;

	private JLabel jLabel29;

	private JLabel jLabel30;

	private JLabel jLabel31;

	private JLabel jLabel32;

	private JLabel jLabel35;

	private JLabel jLabel39;

	private JLabel jLabel41;

	private JLabel jLabel42;

	private JLabel jLabel48;

	private JLabel jLabel49;

	private JLabel jLabel50;

	private JLabel jLabel51;

	private JLabel jLabel52;

	private JLabel jLabel53;

	private JLabel jLabel54;

	private JLabel jLabel55;

	private JLabel jLabel56;

	private JLabel jLabel59;

	private JLabel jLabel60;

	private JLabel jLabel64;

	private JLabel jLabel65;

	private JLabel jLabel66;

	private JLabel jLabel67;

	private JLabel jLabel68;

	private JLabel jLabel69;

	private JLabel jLabel70;

	private JLabel jLabel71;

	private JLabel jLabel72;

	private JLabel titulo;
	
	private SImPLe fepdi;
	
	private Facade facade;
	
	private String nome = "Ilus\u00e3o de Ótica";

	/** Creates new form ilusao1 */
	public Ilusao7(SImPLe fepdi, Facade facade) {
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
		jLabel17 = new JLabel();
		jLabel18 = new JLabel();
		jLabel19 = new JLabel();
		jLabel20 = new JLabel();
		jLabel21 = new JLabel();
		jLabel22 = new JLabel();
		jLabel23 = new JLabel();
		jLabel24 = new JLabel();
		jLabel25 = new JLabel();
		jLabel26 = new JLabel();
		jLabel27 = new JLabel();
		jLabel28 = new JLabel();
		jLabel29 = new JLabel();
		jLabel30 = new JLabel();
		jLabel31 = new JLabel();
		jLabel32 = new JLabel();
		jLabel35 = new JLabel();
		jLabel39 = new JLabel();
		jLabel41 = new JLabel();
		jLabel42 = new JLabel();
		jLabel48 = new JLabel();
		jLabel59 = new JLabel();
		jLabel60 = new JLabel();
		jLabel64 = new JLabel();
		jLabel49 = new JLabel();
		jLabel50 = new JLabel();
		jLabel51 = new JLabel();
		jLabel52 = new JLabel();
		jLabel53 = new JLabel();
		jLabel54 = new JLabel();
		jLabel55 = new JLabel();
		jLabel56 = new JLabel();
		jLabel65 = new JLabel();
		jLabel66 = new JLabel();
		jLabel67 = new JLabel();
		jLabel68 = new JLabel();
		jLabel69 = new JLabel();
		jLabel70 = new JLabel();
		jLabel71 = new JLabel();
		jLabel72 = new JLabel();

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
		titulo.setBounds(150, 20, 270, 44);

		jLabel17.setBackground(new java.awt.Color(51, 51, 51));
		jLabel17.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel17.setOpaque(true);
		jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel17MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel17);
		jLabel17.setBounds(170, 220, 30, 30);

		jLabel18.setBackground(new java.awt.Color(255, 255, 255));
		jLabel18.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel18.setOpaque(true);
		jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel18MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel18);
		jLabel18.setBounds(260, 220, 34, 30);

		jLabel19.setBackground(new java.awt.Color(51, 51, 51));
		jLabel19.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel19.setOpaque(true);
		jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel19MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel19);
		jLabel19.setBounds(200, 160, 34, 30);

		jLabel20.setBackground(new java.awt.Color(255, 255, 255));
		jLabel20.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel20.setOpaque(true);
		jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel20MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel20);
		jLabel20.setBounds(234, 160, 30, 30);

		jLabel21.setBackground(new java.awt.Color(51, 51, 51));
		jLabel21.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel21.setOpaque(true);
		jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel21MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel21);
		jLabel21.setBounds(264, 160, 30, 30);

		jLabel22.setBackground(new java.awt.Color(255, 255, 255));
		jLabel22.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel22.setOpaque(true);
		jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel22MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel22);
		jLabel22.setBounds(294, 160, 30, 30);

		jLabel23.setBackground(new java.awt.Color(51, 51, 51));
		jLabel23.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel23.setOpaque(true);
		jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel23MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel23);
		jLabel23.setBounds(324, 160, 30, 30);

		jLabel24.setBackground(new java.awt.Color(255, 255, 255));
		jLabel24.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel24.setOpaque(true);
		jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel24MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel24);
		jLabel24.setBounds(352, 160, 28, 30);

		jLabel25.setBackground(new java.awt.Color(51, 51, 51));
		jLabel25.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel25.setOpaque(true);
		jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel25MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel25);
		jLabel25.setBounds(370, 190, 30, 30);

		jLabel26.setBackground(new java.awt.Color(255, 255, 255));
		jLabel26.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel26.setOpaque(true);
		jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel26MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel26);
		jLabel26.setBounds(170, 190, 20, 30);

		jLabel27.setBackground(new java.awt.Color(51, 51, 51));
		jLabel27.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel27.setOpaque(true);
		jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel27MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel27);
		jLabel27.setBounds(190, 190, 34, 30);

		jLabel28.setBackground(new java.awt.Color(255, 255, 255));
		jLabel28.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel28.setOpaque(true);
		jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel28MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel28);
		jLabel28.setBounds(224, 190, 30, 30);

		jLabel29.setBackground(new java.awt.Color(51, 51, 51));
		jLabel29.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel29.setOpaque(true);
		jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel29MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel29);
		jLabel29.setBounds(254, 190, 30, 30);

		jLabel30.setBackground(new java.awt.Color(255, 255, 255));
		jLabel30.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel30.setOpaque(true);
		jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel30MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel30);
		jLabel30.setBounds(284, 190, 30, 30);

		jLabel31.setBackground(new java.awt.Color(51, 51, 51));
		jLabel31.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel31.setOpaque(true);
		jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel31MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel31);
		jLabel31.setBounds(314, 190, 30, 30);

		jLabel32.setBackground(new java.awt.Color(255, 255, 255));
		jLabel32.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel32.setOpaque(true);
		jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel32MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel32);
		jLabel32.setBounds(342, 190, 28, 30);

		jLabel35.setBackground(new java.awt.Color(51, 51, 51));
		jLabel35.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel35.setOpaque(true);
		jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel35MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel35);
		jLabel35.setBounds(230, 220, 30, 30);

		jLabel39.setBackground(new java.awt.Color(51, 51, 51));
		jLabel39.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel39.setOpaque(true);
		jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel39MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel39);
		jLabel39.setBounds(350, 220, 30, 30);

		jLabel41.setBackground(new java.awt.Color(51, 51, 51));
		jLabel41.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel41.setOpaque(true);
		jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel41MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel41);
		jLabel41.setBounds(380, 160, 20, 30);

		jLabel42.setBackground(new java.awt.Color(255, 255, 255));
		jLabel42.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel42.setOpaque(true);
		jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel42MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel42);
		jLabel42.setBounds(200, 220, 30, 30);

		jLabel48.setBackground(new java.awt.Color(255, 255, 255));
		jLabel48.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel48.setOpaque(true);
		jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel48MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel48);
		jLabel48.setBounds(170, 160, 30, 30);

		jLabel59.setBackground(new java.awt.Color(51, 51, 51));
		jLabel59.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel59.setOpaque(true);
		jLabel59.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel59MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel59);
		jLabel59.setBounds(294, 220, 30, 30);

		jLabel60.setBackground(new java.awt.Color(255, 255, 255));
		jLabel60.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel60.setOpaque(true);
		jLabel60.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel60MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel60);
		jLabel60.setBounds(324, 220, 26, 30);

		jLabel64.setBackground(new java.awt.Color(255, 255, 255));
		jLabel64.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel64.setOpaque(true);
		jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel64MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel64);
		jLabel64.setBounds(380, 220, 20, 30);

		jLabel49.setBackground(new java.awt.Color(51, 51, 51));
		jLabel49.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel49.setOpaque(true);
		jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel49MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel49);
		jLabel49.setBounds(170, 100, 30, 30);

		jLabel50.setBackground(new java.awt.Color(51, 51, 51));
		jLabel50.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel50.setOpaque(true);
		jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel50MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel50);
		jLabel50.setBounds(250, 130, 30, 30);

		jLabel51.setBackground(new java.awt.Color(51, 51, 51));
		jLabel51.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel51.setOpaque(true);
		jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel51MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel51);
		jLabel51.setBounds(190, 130, 30, 30);

		jLabel52.setBackground(new java.awt.Color(255, 255, 255));
		jLabel52.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel52.setOpaque(true);
		jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel52MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel52);
		jLabel52.setBounds(200, 100, 34, 30);

		jLabel53.setBackground(new java.awt.Color(255, 255, 255));
		jLabel53.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel53.setOpaque(true);
		jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel53MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel53);
		jLabel53.setBounds(170, 130, 20, 30);

		jLabel54.setBackground(new java.awt.Color(51, 51, 51));
		jLabel54.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel54.setOpaque(true);
		jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel54MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel54);
		jLabel54.setBounds(234, 100, 30, 30);

		jLabel55.setBackground(new java.awt.Color(255, 255, 255));
		jLabel55.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel55.setOpaque(true);
		jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel55MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel55);
		jLabel55.setBounds(264, 100, 30, 30);

		jLabel56.setBackground(new java.awt.Color(255, 255, 255));
		jLabel56.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel56.setOpaque(true);
		jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel56MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel56);
		jLabel56.setBounds(220, 130, 30, 30);

		jLabel65.setBackground(new java.awt.Color(51, 51, 51));
		jLabel65.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel65.setOpaque(true);
		jLabel65.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel65MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel65);
		jLabel65.setBounds(310, 130, 34, 30);

		jLabel66.setBackground(new java.awt.Color(51, 51, 51));
		jLabel66.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel66.setOpaque(true);
		jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel66MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel66);
		jLabel66.setBounds(294, 100, 30, 30);

		jLabel67.setBackground(new java.awt.Color(255, 255, 255));
		jLabel67.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel67.setOpaque(true);
		jLabel67.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel67MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel67);
		jLabel67.setBounds(324, 100, 30, 30);

		jLabel68.setBackground(new java.awt.Color(255, 255, 255));
		jLabel68.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel68.setOpaque(true);
		jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel68MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel68);
		jLabel68.setBounds(280, 130, 30, 30);

		jLabel69.setBackground(new java.awt.Color(51, 51, 51));
		jLabel69.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel69.setOpaque(true);
		jLabel69.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel69MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel69);
		jLabel69.setBounds(374, 130, 26, 30);

		jLabel70.setBackground(new java.awt.Color(51, 51, 51));
		jLabel70.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel70.setOpaque(true);
		jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel70MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel70);
		jLabel70.setBounds(354, 100, 30, 30);

		jLabel71.setBackground(new java.awt.Color(255, 255, 255));
		jLabel71.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel71.setOpaque(true);
		jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel71MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel71);
		jLabel71.setBounds(382, 100, 18, 30);

		jLabel72.setBackground(new java.awt.Color(255, 255, 255));
		jLabel72.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(153, 153, 153)));
		jLabel72.setOpaque(true);
		jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel72MouseClicked(evt);
			}
		});

		getContentPane().add(jLabel72);
		jLabel72.setBounds(342, 130, 32, 30);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 610) / 2, (screenSize.height - 421) / 2,
				610, 421);
	}

	private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel60MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel67MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}

	}

	private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorBrancos();
		}
	}

	private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel59MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel69MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel65MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}

	}

	private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {
		if (paletaDeCores.isPinturaAtivada()) {
			mudaCorPretos();
		}
	}

	private void mudaCorPretos() {
		jLabel49.setBackground(paletaDeCores.getCorSelecionada());
		jLabel54.setBackground(paletaDeCores.getCorSelecionada());
		jLabel66.setBackground(paletaDeCores.getCorSelecionada());
		jLabel70.setBackground(paletaDeCores.getCorSelecionada());
		jLabel51.setBackground(paletaDeCores.getCorSelecionada());
		jLabel50.setBackground(paletaDeCores.getCorSelecionada());
		jLabel65.setBackground(paletaDeCores.getCorSelecionada());
		jLabel69.setBackground(paletaDeCores.getCorSelecionada());
		jLabel41.setBackground(paletaDeCores.getCorSelecionada());
		jLabel23.setBackground(paletaDeCores.getCorSelecionada());
		jLabel21.setBackground(paletaDeCores.getCorSelecionada());
		jLabel19.setBackground(paletaDeCores.getCorSelecionada());
		jLabel27.setBackground(paletaDeCores.getCorSelecionada());
		jLabel29.setBackground(paletaDeCores.getCorSelecionada());
		jLabel31.setBackground(paletaDeCores.getCorSelecionada());
		jLabel25.setBackground(paletaDeCores.getCorSelecionada());
		jLabel17.setBackground(paletaDeCores.getCorSelecionada());
		jLabel35.setBackground(paletaDeCores.getCorSelecionada());
		jLabel59.setBackground(paletaDeCores.getCorSelecionada());
		jLabel39.setBackground(paletaDeCores.getCorSelecionada());
	}

	private void mudaCorBrancos() {
		jLabel52.setBackground(paletaDeCores.getCorSelecionada());
		jLabel55.setBackground(paletaDeCores.getCorSelecionada());
		jLabel67.setBackground(paletaDeCores.getCorSelecionada());
		jLabel71.setBackground(paletaDeCores.getCorSelecionada());
		jLabel72.setBackground(paletaDeCores.getCorSelecionada());
		jLabel68.setBackground(paletaDeCores.getCorSelecionada());
		jLabel56.setBackground(paletaDeCores.getCorSelecionada());
		jLabel53.setBackground(paletaDeCores.getCorSelecionada());
		jLabel48.setBackground(paletaDeCores.getCorSelecionada());
		jLabel20.setBackground(paletaDeCores.getCorSelecionada());
		jLabel22.setBackground(paletaDeCores.getCorSelecionada());
		jLabel24.setBackground(paletaDeCores.getCorSelecionada());
		jLabel26.setBackground(paletaDeCores.getCorSelecionada());
		jLabel28.setBackground(paletaDeCores.getCorSelecionada());
		jLabel30.setBackground(paletaDeCores.getCorSelecionada());
		jLabel32.setBackground(paletaDeCores.getCorSelecionada());
		jLabel42.setBackground(paletaDeCores.getCorSelecionada());
		jLabel18.setBackground(paletaDeCores.getCorSelecionada());
		jLabel60.setBackground(paletaDeCores.getCorSelecionada());
		jLabel64.setBackground(paletaDeCores.getCorSelecionada());
	}
	
	public static JInternalFrame create(SImPLe fepdi, Facade facade){
		JInternalFrame ilusao =  new Ilusao7(fepdi,facade);
		ilusao.setVisible(true);
		return ilusao;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Ilusao7(null,null).setVisible(true);
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
		SImPLe.ilusao7.setEnabled(true);
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
