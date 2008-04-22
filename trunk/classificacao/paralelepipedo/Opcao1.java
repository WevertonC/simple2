/*
 * opcao1.java
 *
 * Created on 18 de Agosto de 2006, 19:22
 */

package paralelepipedo;

import imagem.ModeloCromatico;
import imagem.Monocromatico;
import imagem.RGB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import paralelepipedo.GerenciadorClassesParalelepipedo;





/**
 *
 * @author  LUCIANA
 */
public class Opcao1 extends javax.swing.JFrame {
    
	private static final long serialVersionUID = 1L;
	private boolean notClosed;
	private Opcao2 opcao2;
	private Opcao3 opcao3;
	
	/**
     * Creates new form opcao1 
     */
    public Opcao1(Opcao2 opcao2, Opcao3 opcao3) {
    	this.opcao2 = opcao2;
    	this.opcao3 = opcao3;
    	notClosed = true;
        initComponents();
    }
    

    private void initComponents() {
        classeButtonGroup = new javax.swing.ButtonGroup();
        modeloCromaticoButtonGroup = new javax.swing.ButtonGroup();
        classesLabel = new javax.swing.JLabel();
        classesLabel1 = new javax.swing.JLabel();
        classe1 = new javax.swing.JRadioButton();
        classe2 = new javax.swing.JRadioButton();
        classe3 = new javax.swing.JRadioButton();
        monocromatico = new javax.swing.JRadioButton();
        rgb = new javax.swing.JRadioButton();
        rCheck = new javax.swing.JCheckBox();
        gCheck = new javax.swing.JCheckBox();
        bCheck = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Classifica\u00e7\u00e3o - M\u00e9todo do paralelep\u00edpedo");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.darkGray);
        setResizable(false);
        getAccessibleContext().setAccessibleParent(this);
        classesLabel.setBorder(new javax.swing.border.TitledBorder("Escolha o n\u00famero de classes"));
        getContentPane().add(classesLabel);
        classesLabel.setBounds(20, 10, 390, 50);

        classesLabel1.setBorder(new javax.swing.border.TitledBorder("Escolha o modelo crom\u00e1tico"));
        getContentPane().add(classesLabel1);
        classesLabel1.setBounds(20, 70, 390, 50);

        classeButtonGroup.add(classe1);
        classe1.setText("1");
        getContentPane().add(classe1);
        classe1.setBounds(40, 30, 40, 23);
        classe1.setSelected(true);

        classeButtonGroup.add(classe2);
        classe2.setText("2");
        getContentPane().add(classe2);
        classe2.setBounds(100, 30, 40, 23);

        classeButtonGroup.add(classe3);
        classe3.setText("3");
        getContentPane().add(classe3);
        classe3.setBounds(160, 30, 40, 23);

        modeloCromaticoButtonGroup.add(monocromatico);
        monocromatico.setText("Monocrom\u00e1tico");
        getContentPane().add(monocromatico);
        monocromatico.setBounds(40, 90, 130, 23);
        monocromatico.setSelected(true);
        monocromatico.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				habilitaDesabilitaChecbox(false);
			}
        	
        });

        modeloCromaticoButtonGroup.add(rgb);
        rgb.setText("RGB");
        getContentPane().add(rgb);
        rgb.setBounds(180, 90, 60, 23);
        rgb.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				habilitaDesabilitaChecbox(true);
			}
        	
        });

        rCheck.setText("R");
        getContentPane().add(rCheck);
        rCheck.setBounds(250, 90, 40, 23);
        rCheck.setEnabled(false);
        rCheck.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				okButton.setEnabled(verificaCheckBox());
			}        	
        });

        gCheck.setText("G");
        getContentPane().add(gCheck);
        gCheck.setBounds(300, 90, 40, 23);
        gCheck.setEnabled(false);
        gCheck.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				okButton.setEnabled(verificaCheckBox());
			}        	
        });        

        bCheck.setText("B");
        getContentPane().add(bCheck);
        bCheck.setBounds(350, 90, 40, 23);
        bCheck.setEnabled(false);
        bCheck.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				okButton.setEnabled(verificaCheckBox());
			}        	
        });          

        okButton.setText("OK");
        getContentPane().add(okButton);
        okButton.setBounds(190, 140, 70, 23);
        okButton.addMouseListener(new MouseAdapter(){

			public void mouseReleased(MouseEvent e) {
				if (okButton.isEnabled()) {
					ModeloCromatico modelo = recuperaModeloCromatico();
					GerenciadorClassesParalelepipedo.getInstance().setModeloCromatico(modelo);
					notClosed = false;
					getContentPane().setVisible(false);
					if (monocromatico.isSelected() || apenasUmSelecionado() ) {
						opcao2.setNumeroClasses(getNumeroClasse());
						opcao2.start();
						opcao2.setVisible(true);
					} else {
						opcao3.setNumeroClasses(getNumeroClasse());
						opcao3.setNumeroCores(getNumeroCores(opcao3));
						opcao3.start();
						opcao3.setVisible(true);					
					}
					disposeClass();
				}
			}

			private int getNumeroCores(Opcao3 opcao3) {
				if (apenasUmSelecionado())
					return 1;
				if (rCheck.isSelected() && bCheck.isSelected() && !gCheck.isSelected()) {
					opcao3.setCorExcluida(2);
					return 2;
				}
				if (!rCheck.isSelected() && bCheck.isSelected() && gCheck.isSelected()) {
					opcao3.setCorExcluida(1);
					return 2;
				}
				if (rCheck.isSelected() && !bCheck.isSelected() && gCheck.isSelected()) {
					opcao3.setCorExcluida(3);
					return 2;
				}
				return 3;
			}

			private boolean apenasUmSelecionado() {
				if (rCheck.isSelected() && !bCheck.isSelected() && !gCheck.isSelected())
					return true;
				if (!rCheck.isSelected() && bCheck.isSelected() && !gCheck.isSelected())
					return true;
				if (!rCheck.isSelected() && !bCheck.isSelected() && gCheck.isSelected())
					return true;
				return false;
			}
        });

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-438)/2, (screenSize.height-203)/2, 438, 203);
    }

    protected int getNumeroClasse() {
    	if (classe1.isSelected()) {
    		return 1;
    	}
    	if (classe2.isSelected()) {
    		return 2;
    	} else {
    		return 3;
    	}
	}


	protected void disposeClass() {
		dispose();
	}


	protected ModeloCromatico recuperaModeloCromatico() {
    	if (monocromatico.isSelected()) {
    		return new Monocromatico();
    	} else {
    		return new RGB(rCheck.isSelected(), gCheck.isSelected(), bCheck.isSelected());
    	}
	}


	protected void habilitaDesabilitaChecbox(boolean b) {
    	rCheck.setSelected(false);
    	gCheck.setSelected(false);
    	bCheck.setSelected(false);    	
    	rCheck.setEnabled(b);
    	gCheck.setEnabled(b);
    	bCheck.setEnabled(b);
    	okButton.setEnabled(!b);
	}


	protected boolean verificaCheckBox() {
		if (rCheck.isSelected() || gCheck.isSelected() || bCheck.isSelected()) {
			return true;
		}
		return false;
	}
	
	public boolean notClosed() {
		return notClosed;
	}

    
    private javax.swing.JCheckBox bCheck;
    private javax.swing.JRadioButton classe1;
    private javax.swing.JRadioButton classe2;
    private javax.swing.JRadioButton classe3;
    private javax.swing.ButtonGroup classeButtonGroup;
    private javax.swing.JLabel classesLabel;
    private javax.swing.JLabel classesLabel1;
    private javax.swing.JCheckBox gCheck;
    private javax.swing.ButtonGroup modeloCromaticoButtonGroup;
    private javax.swing.JRadioButton monocromatico;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox rCheck;
    private javax.swing.JRadioButton rgb;

}
