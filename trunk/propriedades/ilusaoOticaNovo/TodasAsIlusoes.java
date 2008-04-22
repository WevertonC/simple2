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

import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 * Classe responsável pela exibição do frame de opções de ilusões de ótica.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class TodasAsIlusoes extends javax.swing.JFrame {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;

	private ButtonGroup buttonGroup1;

	private JRadioButton ilusao1;

	private JRadioButton ilusao10;

	private JRadioButton ilusao2;

	private JRadioButton ilusao3;

	private JRadioButton ilusao4;

	private JRadioButton ilusao5;

	private JRadioButton ilusao6;

	private JRadioButton ilusao7;

	private JRadioButton ilusao8;

	private JRadioButton ilusao9;

	private JLabel jLabel1;

	private JButton okButton;

	private JLabel titulo;

	/**
	 * Cria um novo frame de opções de ilusões de ótica.
	 */
	public TodasAsIlusoes() {
		initComponents();
	}

	/**
	 * Inicializa o frame com as opções de ilusão de ótica.
	 */
	private void initComponents() {
		buttonGroup1 = new ButtonGroup();
		ilusao1 = new JRadioButton();
		ilusao2 = new JRadioButton();
		ilusao3 = new JRadioButton();
		ilusao4 = new JRadioButton();
		ilusao5 = new JRadioButton();
		ilusao6 = new JRadioButton();
		ilusao7 = new JRadioButton();
		ilusao8 = new JRadioButton();
		ilusao9 = new JRadioButton();
		ilusao10 = new JRadioButton();
		jLabel1 = new JLabel();
		okButton = new JButton();
		titulo = new JLabel();

		getContentPane().setLayout(null);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Ilus\u00e3o de \u00f3tica");
		setBackground(new java.awt.Color(231, 231, 250));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setForeground(java.awt.Color.darkGray);
		setResizable(false);
		getAccessibleContext().setAccessibleParent(this);

		//ilusão 1
		buttonGroup1.add(ilusao1);
		ilusao1.setText("Ilus\u00e3o 1");
		getContentPane().add(ilusao1);
		ilusao1.setBounds(140, 150, 100, 23);

		//ilusão 2
		buttonGroup1.add(ilusao2);
		ilusao2.setText("Ilus\u00e3o 2");
		getContentPane().add(ilusao2);
		ilusao2.setBounds(140, 180, 110, 23);

		//ilusão 3
		buttonGroup1.add(ilusao3);
		ilusao3.setText("Ilus\u00e3o 3");
		getContentPane().add(ilusao3);
		ilusao3.setBounds(140, 210, 110, 23);

		//ilusão 4         
		buttonGroup1.add(ilusao4);
		ilusao4.setText("Ilus\u00e3o 6");
		getContentPane().add(ilusao4);
		ilusao4.setBounds(390, 150, 100, 23);

		//ilusão 5        
		buttonGroup1.add(ilusao5);
		ilusao5.setText("Ilus\u00e3o 5");
		getContentPane().add(ilusao5);
		ilusao5.setBounds(140, 270, 110, 23);

		//ilusão 6        
		buttonGroup1.add(ilusao6);
		ilusao6.setText("Ilus\u00e3o 4");
		getContentPane().add(ilusao6);
		ilusao6.setBounds(140, 240, 110, 23);

		//ilusão 7        
		buttonGroup1.add(ilusao7);
		ilusao7.setText("Ilus\u00e3o 7");
		getContentPane().add(ilusao7);
		ilusao7.setBounds(390, 180, 110, 23);

		//ilusão 8        
		buttonGroup1.add(ilusao8);
		ilusao8.setText("Ilus\u00e3o 8");
		getContentPane().add(ilusao8);
		ilusao8.setBounds(390, 210, 110, 23);

		//ilusão 9        
		buttonGroup1.add(ilusao9);
		ilusao9.setText("Ilus\u00e3o 9");
		getContentPane().add(ilusao9);
		ilusao9.setBounds(390, 240, 100, 23);

		//ilusão 10        
		buttonGroup1.add(ilusao10);
		ilusao10.setText("Ilus\u00e3o 10");
		getContentPane().add(ilusao10);
		ilusao10.setBounds(390, 270, 120, 23);

		//label        
		jLabel1.setBackground(new java.awt.Color(232, 232, 252));
		jLabel1
				.setBorder(new javax.swing.border.TitledBorder(null,
						"Escolha uma ilus\u00e3o de \u00f3tica",
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
						javax.swing.border.TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(
								0, 0, 0)));
		getContentPane().add(jLabel1);
		jLabel1.setBounds(40, 100, 530, 210);

		okButton.setText("OK");
		okButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				okButtonMouseClicked(evt);
			}
		});

		getContentPane().add(okButton);
		okButton.setBounds(280, 340, 73, 23);

		titulo.setFont(new java.awt.Font("Tahoma", 1, 36));
		titulo.setText("Ilus\u00f5es de \u00f3tica");
		getContentPane().add(titulo);
		titulo.setBounds(160, 20, 310, 44);

		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - 610) / 2, (screenSize.height - 421) / 2,
				610, 421);
	}

	/**
	 * Evento do click no botão ok - instanciar uma ilusão de ótica.
	 * @param evt O evento do click.
	 */
	@SuppressWarnings("unchecked")
	private void okButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMouseClicked
		Enumeration enumeration = buttonGroup1.getElements();
		while (enumeration.hasMoreElements()) {
			JRadioButton radioButton = (JRadioButton) enumeration.nextElement();
			if (radioButton.isSelected()) {
				if (radioButton.getText().equals("Ilusão 1")) {
					Ilusao1.main(null);
				} else if (radioButton.getText().equals("Ilusão 2")) {
					Ilusao2.main(null);
				} else if (radioButton.getText().equals("Ilusão 3")) {
					Ilusao3.main(null);
				} else if (radioButton.getText().equals("Ilusão 4")) {
					Ilusao4.main(null);
				} else if (radioButton.getText().equals("Ilusão 5")) {
					Ilusao5.main(null);
				} else if (radioButton.getText().equals("Ilusão 6")) {
					Ilusao6.main(null);
				} else if (radioButton.getText().equals("Ilusão 7")) {
					Ilusao7.main(null);
				} else if (radioButton.getText().equals("Ilusão 8")) {
					Ilusao8.main(null);
				} else if (radioButton.getText().equals("Ilusão 9")) {
					Ilusao9.main(null);
				} else if (radioButton.getText().equals("Ilusão 10")) {
					Ilusao10.main(null);
				}
			}
		}
	}

	/**
	 * Main.
	 * @param args argumentos da linha de comando.
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TodasAsIlusoes().setVisible(true);
			}
		});
	}

}
