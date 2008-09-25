/*
 * opcao1.java
 *
 * Created on 18 de Agosto de 2006, 19:22
 */

package simple.modules.classificacao.paralelepipedo;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import simple.manipulacoes.imagem.Cor;
import simple.manipulacoes.imagem.RGBImage;
import simple.modules.classificacao.paralelepipedo.ClasseParalelepipedo;
import simple.modules.classificacao.paralelepipedo.GerenciadorClassesParalelepipedo;






/**
 *
 * @author  LUCIANA
 */
public class Opcao2 extends javax.swing.JFrame {
    

	private static final long serialVersionUID = 1L;
	private int numeroClasses;
	private RGBImage resultado;
	private boolean notClosed;
	
    /**
     * Creates new form opcao1 
     */
    public Opcao2() {
        notClosed = true;
    }
    
    public void setNumeroClasses(int numeroClasses) {
    	this.numeroClasses = numeroClasses;
    }
    
    
    private void initComponents() {
        classeButtonGroup = new javax.swing.ButtonGroup();
        modeloCromaticoButtonGroup = new javax.swing.ButtonGroup();
        opcaoLabel = new javax.swing.JLabel();
        classificarButton = new javax.swing.JButton();
        classeL = new javax.swing.JLabel();
        nivelMini = new javax.swing.JLabel();
        miniValorText1 = new javax.swing.JTextField();
        classeL1 = new javax.swing.JLabel();
        nivelMax = new javax.swing.JLabel();
        maxValorText1 = new javax.swing.JTextField();
        rotulo = new javax.swing.JLabel();
        rotuloText1 = new javax.swing.JTextField();
        cor = new javax.swing.JLabel();
        corL1 = new javax.swing.JLabel();
        classeL2 = new javax.swing.JLabel();
        classeL3 = new javax.swing.JLabel();
        miniValorText2 = new javax.swing.JTextField();
        miniValorText3 = new javax.swing.JTextField();
        maxValorText2 = new javax.swing.JTextField();
        maxValorText3 = new javax.swing.JTextField();
        rotuloText2 = new javax.swing.JTextField();
        rotuloText3 = new javax.swing.JTextField();
        corL2 = new javax.swing.JLabel();
        corL3 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Classifica\u00e7\u00e3o - M\u00e9todo do paralelep\u00edpedo");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.darkGray);
        setResizable(false);
        getAccessibleContext().setAccessibleParent(this);
        opcaoLabel.setBorder(new javax.swing.border.TitledBorder("Defini\u00e7\u00e3o dos valores das classes"));
        getContentPane().add(opcaoLabel);
        opcaoLabel.setBounds(20, 10, 370, 160);

        classificarButton.setText("Classificar Imagem");
        getContentPane().add(classificarButton);
        classificarButton.setBounds(140, 180, 150, 23);
        classificarButton.setEnabled(false);
        classificarButton.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				if (classificarButton.isEnabled()) {	
					try {
						//pegar a figura
						//transformar cada opção numa classe
						if (numeroClasses >= 1 ) {
							int red = corL1.getBackground().getRed();
							int green = corL1.getBackground().getGreen();
							int blue = corL1.getBackground().getBlue();
							Cor color = new Cor(red, green, blue);
							ClasseParalelepipedo classe = new ClasseParalelepipedo(
									Integer.parseInt(miniValorText1.getText()),
									Integer.parseInt(maxValorText1.getText()),
									rotuloText1.getText(), color);
							GerenciadorClassesParalelepipedo.getInstance().addClasse(classe);
						}
						if (numeroClasses >= 2 ) {
							int red = corL2.getBackground().getRed();
							int green = corL2.getBackground().getGreen();
							int blue = corL2.getBackground().getBlue();
							Cor color = new Cor(red, green, blue);
							ClasseParalelepipedo classe = new ClasseParalelepipedo(
									Integer.parseInt(miniValorText2.getText()),
									Integer.parseInt(maxValorText2.getText()),
									rotuloText2.getText(), color);
							GerenciadorClassesParalelepipedo.getInstance().addClasse(classe);
						}
						if (numeroClasses >= 3 ) {
							int red = corL3.getBackground().getRed();
							int green = corL3.getBackground().getGreen();
							int blue = corL3.getBackground().getBlue();
							Cor color = new Cor(red, green, blue);
							ClasseParalelepipedo classe = new ClasseParalelepipedo(
									Integer.parseInt(miniValorText3.getText()),
									Integer.parseInt(maxValorText3.getText()),
									rotuloText3.getText(), color);
							GerenciadorClassesParalelepipedo.getInstance().addClasse(classe);
						}
	
						RGBImage imagem = GerenciadorClassesParalelepipedo.getInstance().classificaImagem();
						setResultado(imagem);
						fechar();
					} catch (Exception e1) {
						GerenciadorClassesParalelepipedo.getInstance().zeraClasses();
						alert("Faixas de valores incorretas!");
					}
			}
			}
        	
        });
        

        classeL.setBackground(new java.awt.Color(102, 102, 255));
        classeL.setFont(new java.awt.Font("Times New Roman", 0, 18));
        classeL.setForeground(new java.awt.Color(255, 255, 255));
        classeL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL.setText("Classe");
        classeL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        classeL.setOpaque(true);
        getContentPane().add(classeL);
        classeL.setBounds(40, 40, 60, 20);

        nivelMini.setBackground(new java.awt.Color(102, 102, 255));
        nivelMini.setFont(new java.awt.Font("Times New Roman", 0, 18));
        nivelMini.setForeground(new java.awt.Color(255, 255, 255));
        nivelMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nivelMini.setText("Min");
        nivelMini.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        nivelMini.setOpaque(true);
        getContentPane().add(nivelMini);
        nivelMini.setBounds(110, 40, 50, 20);

        miniValorText1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText1.setBorder(null);
        getContentPane().add(miniValorText1);
        miniValorText1.setBounds(110, 70, 50, 20);
        miniValorText1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });

        classeL1.setBackground(new java.awt.Color(255, 255, 255));
        classeL1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL1.setText("1");
        classeL1.setOpaque(true);
        getContentPane().add(classeL1);
        classeL1.setBounds(40, 70, 60, 20);

        nivelMax.setBackground(new java.awt.Color(102, 102, 255));
        nivelMax.setFont(new java.awt.Font("Times New Roman", 0, 18));
        nivelMax.setForeground(new java.awt.Color(255, 255, 255));
        nivelMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nivelMax.setText("Max");
        nivelMax.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        nivelMax.setOpaque(true);
        getContentPane().add(nivelMax);
        nivelMax.setBounds(170, 40, 50, 20);

        maxValorText1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText1.setBorder(null);
        maxValorText1.setName("max");
        getContentPane().add(maxValorText1);
        maxValorText1.setBounds(170, 70, 50, 20);
        maxValorText1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });        

        rotulo.setBackground(new java.awt.Color(102, 102, 255));
        rotulo.setFont(new java.awt.Font("Times New Roman", 0, 18));
        rotulo.setForeground(new java.awt.Color(255, 255, 255));
        rotulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rotulo.setText("R\u00f3tulo");
        rotulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        rotulo.setOpaque(true);
        getContentPane().add(rotulo);
        rotulo.setBounds(230, 40, 80, 20);

        rotuloText1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        rotuloText1.setBorder(null);
        getContentPane().add(rotuloText1);
        rotuloText1.setBounds(230, 70, 80, 20);

        cor.setBackground(new java.awt.Color(102, 102, 255));
        cor.setFont(new java.awt.Font("Times New Roman", 0, 18));
        cor.setForeground(new java.awt.Color(255, 255, 255));
        cor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cor.setText("Cor");
        cor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        cor.setOpaque(true);
        getContentPane().add(cor);
        cor.setBounds(320, 40, 50, 20);

        corL1.setBackground(new java.awt.Color(0, 0, 0));
        corL1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        corL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        corL1.setOpaque(true);
        getContentPane().add(corL1);
        corL1.setBounds(320, 70, 50, 20);
        corL1.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				escolheUmaCor((JLabel)e.getSource());
			}

			public void mouseEntered(MouseEvent arg0) {
				mudaParaCursorMao();
			}

			public void mouseExited(MouseEvent arg0) {
				mudaParaCursorDefault();
			}
        });        

        classeL2.setBackground(new java.awt.Color(255, 255, 255));
        classeL2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL2.setText("2");
        classeL2.setOpaque(true);
        getContentPane().add(classeL2);
        classeL2.setBounds(40, 100, 60, 20);

        classeL3.setBackground(new java.awt.Color(255, 255, 255));
        classeL3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL3.setText("3");
        classeL3.setOpaque(true);
        getContentPane().add(classeL3);
        classeL3.setBounds(40, 130, 60, 20);

        miniValorText2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText2.setBorder(null);
        getContentPane().add(miniValorText2);
        miniValorText2.setBounds(110, 100, 50, 20);
        miniValorText2.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });        

        miniValorText3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText3.setBorder(null);
        getContentPane().add(miniValorText3);
        miniValorText3.setBounds(110, 130, 50, 20);
        miniValorText3.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });        

        maxValorText2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText2.setBorder(null);
        maxValorText2.setName("max");
        getContentPane().add(maxValorText2);
        maxValorText2.setBounds(170, 100, 50, 20);
        maxValorText2.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });        

        maxValorText3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText3.setBorder(null);
        getContentPane().add(maxValorText3);
        maxValorText3.setName("max");
        maxValorText3.setBounds(170, 130, 50, 20);
        maxValorText3.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });        

        rotuloText2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        rotuloText2.setBorder(null);
        getContentPane().add(rotuloText2);
        rotuloText2.setBounds(230, 100, 80, 20);

        rotuloText3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        rotuloText3.setBorder(null);
        getContentPane().add(rotuloText3);
        rotuloText3.setBounds(230, 130, 80, 20);

        corL2.setBackground(new java.awt.Color(0, 0, 0));
        corL2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        corL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        corL2.setOpaque(true);
        getContentPane().add(corL2);
        corL2.setBounds(320, 100, 50, 20);

        corL3.setBackground(new java.awt.Color(0, 0, 0));
        corL3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        corL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        corL3.setOpaque(true);
        getContentPane().add(corL3);
        corL3.setBounds(320, 130, 50, 20);
        
        corL2.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				escolheUmaCor((JLabel)e.getSource());
			}

			public void mouseEntered(MouseEvent arg0) {
				mudaParaCursorMao();
			}

			public void mouseExited(MouseEvent arg0) {
				mudaParaCursorDefault();
			}
        });
        
        corL3.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				escolheUmaCor((JLabel)e.getSource());
			}

			public void mouseEntered(MouseEvent arg0) {
				mudaParaCursorMao();
			}

			public void mouseExited(MouseEvent arg0) {
				mudaParaCursorDefault();
			}
        });
        
        if (numeroClasses < 2) {
        	classeL2.setBackground(Color.lightGray);
        	miniValorText2.setEditable(false);
        	miniValorText2.setOpaque(true);
        	miniValorText2.setBackground(Color.lightGray);        	
        	maxValorText2.setEditable(false);
        	maxValorText2.setOpaque(true);
        	maxValorText2.setBackground(Color.lightGray);
        	rotuloText2.setEditable(false);
        	rotuloText2.setOpaque(true);
        	rotuloText2.setBackground(Color.lightGray);
        	corL2.setBackground(Color.lightGray);
        	corL2.setEnabled(false);
        }
        
        if (numeroClasses < 3) {
        	classeL3.setBackground(Color.lightGray);
        	miniValorText3.setEditable(false);
        	miniValorText3.setOpaque(true);
        	miniValorText3.setBackground(Color.lightGray);        	
        	maxValorText3.setEditable(false);
        	maxValorText3.setOpaque(true);
        	maxValorText3.setBackground(Color.lightGray);
        	rotuloText3.setEditable(false);
        	rotuloText3.setOpaque(true);
        	rotuloText3.setBackground(Color.lightGray);
        	corL3.setBackground(Color.lightGray);
        	corL3.setEnabled(false);
        }        

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-415)/2, (screenSize.height-255)/2, 415, 255);
    }
    
	public void setResultado(RGBImage resultado) {
		this.resultado = resultado;
	}

	public RGBImage getResultado() {
		return resultado;
	}
	
    
    protected void fechar() {
    	notClosed = false;
		this.setVisible(false);
		this.dispose();
	}
    
	@SuppressWarnings("static-access")
	protected void escolheUmaCor(JLabel label) {
    	JColorChooser colorChooser = new JColorChooser();
    	Color color =	colorChooser.showDialog(null, "Escolha uma cor", label.getBackground());
    	label.setBackground(color);
	}

	protected boolean habilitaBotao() {
    	if (numeroClasses == 1 && 
    		!miniValorText1.getText().equals("") &&
    		!maxValorText1.getText().equals("")) {
    		return true;
    	}
    	if (numeroClasses == 2 && 
        		!miniValorText1.getText().equals("") &&
        		!miniValorText2.getText().equals("") &&
        		!maxValorText1.getText().equals("")  &&
        		!maxValorText2.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 3 && 
        		!miniValorText1.getText().equals("") &&
        		!miniValorText2.getText().equals("") &&
        		!miniValorText3.getText().equals("") &&
        		!maxValorText1.getText().equals("")  &&
        		!maxValorText2.getText().equals("")  &&
        		!maxValorText3.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 4 && 
        		!miniValorText1.getText().equals("") &&
        		!miniValorText2.getText().equals("") &&
        		!miniValorText3.getText().equals("") &&
        		!maxValorText1.getText().equals("")  &&
        		!maxValorText2.getText().equals("")  &&
        		!maxValorText3.getText().equals("") ) {
        		return true;
        }    	
    	if (numeroClasses == 5 &&
    		!miniValorText1.getText().equals("") &&
    		!miniValorText2.getText().equals("") &&
    		!miniValorText3.getText().equals("") &&
    		!maxValorText1.getText().equals("") &&
    		!maxValorText2.getText().equals("") &&
    		!maxValorText3.getText().equals("")) {
    		return true;
    	}
    	return false;
    }
    
    protected void verificaValor(JTextField field) {
    	if (!field.getText().equals("")) {
			try {
				int valor = Integer.parseInt(field.getText());
	    		if (field.getName() != null && field.getName().equalsIgnoreCase("max")) {
	    			if (valor != 256) {
	    				if (valor < 0 || valor > 255) {
	    					alert("Digite apenas números inteiros entre 0 - 256.");
	    					field.setText("");
	    				}
	    			}
	    		} else if (valor < 0 || valor > 255) {
					alert("Digite apenas números inteiros entre 0 - 255.");
					field.setText("");
				}
			} catch (Exception e) {
				alert("Digite apenas números inteiros entre 0 - 255.");
				field.setText("");
			}
    	}
    	classificarButton.setEnabled(habilitaBotao());
	}



	private void alert(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
    private void mudaParaCursorDefault() {
        setCursor(Cursor.getDefaultCursor());
    }
    
    @SuppressWarnings("deprecation")
	private void mudaParaCursorMao() {
        setCursor(Cursor.HAND_CURSOR);
    }   
    
	public boolean notClosed() {
		return notClosed;
	}
	
	public void start() {
		initComponents();
	}

    

    @SuppressWarnings("unused")
	private javax.swing.ButtonGroup classeButtonGroup;
    private javax.swing.JLabel classeL;
    private javax.swing.JLabel classeL1;
    private javax.swing.JLabel classeL2;
    private javax.swing.JLabel classeL3;
    private javax.swing.JButton classificarButton;
    private javax.swing.JLabel cor;
    private javax.swing.JLabel corL1;
    private javax.swing.JLabel corL2;
    private javax.swing.JLabel corL3;
    private javax.swing.JTextField maxValorText1;
    private javax.swing.JTextField maxValorText2;
    private javax.swing.JTextField maxValorText3;
    private javax.swing.JTextField miniValorText1;
    private javax.swing.JTextField miniValorText2;
    private javax.swing.JTextField miniValorText3;
    @SuppressWarnings("unused")
	private javax.swing.ButtonGroup modeloCromaticoButtonGroup;
    private javax.swing.JLabel nivelMax;
    private javax.swing.JLabel nivelMini;
    private javax.swing.JLabel opcaoLabel;
    private javax.swing.JLabel rotulo;
    private javax.swing.JTextField rotuloText1;
    private javax.swing.JTextField rotuloText2;
    private javax.swing.JTextField rotuloText3;


}
