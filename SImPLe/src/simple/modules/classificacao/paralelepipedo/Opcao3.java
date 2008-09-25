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
public class Opcao3 extends javax.swing.JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numeroClasses;
	private int numeroCores;
	private RGBImage resultado;
	private boolean notClosed;
	private int corExcluida;
    
    /**
     * Creates new form opcao1 
     */
    public Opcao3() {
    	corExcluida = 0;
    }
    
    public void setNumeroClasses(int numeroClasses) {
    	this.numeroClasses = numeroClasses;
    }
    

    private void initComponents() {

        opcaoLabel = new javax.swing.JLabel();
        classificarButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        classeL = new javax.swing.JLabel();
        nivelMini = new javax.swing.JLabel();
        miniValorText1R = new javax.swing.JTextField();
        classeL1 = new javax.swing.JLabel();
        nivelMax = new javax.swing.JLabel();
        maxValorText1R = new javax.swing.JTextField();
        rotulo = new javax.swing.JLabel();
        cor = new javax.swing.JLabel();
        classeL11 = new javax.swing.JLabel();
        classeL111 = new javax.swing.JLabel();
        classeL2 = new javax.swing.JLabel();
        classeL22 = new javax.swing.JLabel();
        miniValorText1G = new javax.swing.JTextField();
        miniValorText1B = new javax.swing.JTextField();
        miniValorText2R = new javax.swing.JTextField();
        miniValorText2G = new javax.swing.JTextField();
        maxValorText1G = new javax.swing.JTextField();
        maxValorText1B = new javax.swing.JTextField();
        maxValorText2R = new javax.swing.JTextField();
        maxValorText2G = new javax.swing.JTextField();
        rotuloTextC1 = new javax.swing.JTextField();
        rotuloTextC2 = new javax.swing.JTextField();
        corL1 = new javax.swing.JLabel();
        corL2 = new javax.swing.JLabel();
        classeL222 = new javax.swing.JLabel();
        miniValorText2B = new javax.swing.JTextField();
        maxValorText2B = new javax.swing.JTextField();
        classeL3 = new javax.swing.JLabel();
        classeL33 = new javax.swing.JLabel();
        classeL333 = new javax.swing.JLabel();
        R = new javax.swing.JLabel();
        R1 = new javax.swing.JLabel();
        R2 = new javax.swing.JLabel();
        R3 = new javax.swing.JLabel();
        R4 = new javax.swing.JLabel();
        R5 = new javax.swing.JLabel();
        R6 = new javax.swing.JLabel();
        R7 = new javax.swing.JLabel();
        R8 = new javax.swing.JLabel();
        miniValorText3R = new javax.swing.JTextField();
        miniValorText3G = new javax.swing.JTextField();
        miniValorText3B = new javax.swing.JTextField();
        maxValorText3R = new javax.swing.JTextField();
        maxValorText3G = new javax.swing.JTextField();
        maxValorText3B = new javax.swing.JTextField();
        rotuloTextC3 = new javax.swing.JTextField();
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
        opcaoLabel.setBounds(20, 10, 410, 400);

        classificarButton.setText("Classificar Imagem");
        getContentPane().add(classificarButton);
        classificarButton.setBounds(150, 420, 150, 23);
        classificarButton.setEnabled(false);
        classificarButton.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e) {
				if (classificarButton.isEnabled()) {	
					try {
						if (numeroClasses >= 1) {
							int red = corL1.getBackground().getRed();
							int green = corL1.getBackground().getGreen();
							int blue = corL1.getBackground().getBlue();
							Cor color = new Cor(red, green, blue);
							ClasseParalelepipedo classe1 = new ClasseParalelepipedo(
								miniValorText1R.getText(), maxValorText1R.getText(),
								miniValorText1G.getText(), maxValorText1G.getText(),
								miniValorText1B.getText(),maxValorText1B.getText(),
								rotuloTextC1.getText(), color);
							GerenciadorClassesParalelepipedo.getInstance().addClasse(classe1);
						}
						if (numeroClasses >= 2) {
							int red = corL2.getBackground().getRed();
							int green = corL2.getBackground().getGreen();
							int blue = corL2.getBackground().getBlue();
							Cor color = new Cor(red, green, blue);
							ClasseParalelepipedo classe2 = new ClasseParalelepipedo(
								miniValorText2R.getText(), maxValorText2R.getText(),
								miniValorText2G.getText(), maxValorText2G.getText(),
								miniValorText2B.getText(),maxValorText2B.getText(),
								rotuloTextC2.getText(), color);		
							GerenciadorClassesParalelepipedo.getInstance().addClasse(classe2);
						}
						if (numeroClasses == 3) {
							int red = corL3.getBackground().getRed();
							int green = corL3.getBackground().getGreen();
							int blue = corL3.getBackground().getBlue();
							Cor color = new Cor(red, green, blue);
							ClasseParalelepipedo classe3 = new ClasseParalelepipedo(
								miniValorText3R.getText(), maxValorText3R.getText(),
								miniValorText3G.getText(), maxValorText3G.getText(),
								miniValorText3B.getText(),maxValorText3B.getText(),
								rotuloTextC3.getText(), color);	
							GerenciadorClassesParalelepipedo.getInstance().addClasse(classe3);
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
        

        jLabel3.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 180, 390, 100);

        jLabel2.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 70, 390, 100);

        jLabel1.setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 290, 390, 100);

        classeL.setBackground(new java.awt.Color(102, 102, 255));
        classeL.setFont(new java.awt.Font("Times New Roman", 0, 18));
        classeL.setForeground(new java.awt.Color(255, 255, 255));
        classeL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL.setText("Classe");
        classeL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        classeL.setOpaque(true);
        getContentPane().add(classeL);
        classeL.setBounds(80, 40, 60, 20);

        nivelMini.setBackground(new java.awt.Color(102, 102, 255));
        nivelMini.setFont(new java.awt.Font("Times New Roman", 0, 18));
        nivelMini.setForeground(new java.awt.Color(255, 255, 255));
        nivelMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nivelMini.setText("Min");
        nivelMini.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        nivelMini.setOpaque(true);
        getContentPane().add(nivelMini);
        nivelMini.setBounds(150, 40, 50, 20);

        miniValorText1R.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText1R.setBorder(null);
        getContentPane().add(miniValorText1R);
        miniValorText1R.setBounds(150, 80, 50, 20);
        miniValorText1R.addKeyListener(new KeyListener(){
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
        classeL1.setBounds(80, 80, 60, 20);

        nivelMax.setBackground(new java.awt.Color(102, 102, 255));
        nivelMax.setFont(new java.awt.Font("Times New Roman", 0, 18));
        nivelMax.setForeground(new java.awt.Color(255, 255, 255));
        nivelMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nivelMax.setText("Max");
        nivelMax.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        nivelMax.setOpaque(true);
        getContentPane().add(nivelMax);
        nivelMax.setBounds(210, 40, 50, 20);

        maxValorText1R.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText1R.setBorder(null);
        maxValorText1R.setName("max");
        getContentPane().add(maxValorText1R);
        maxValorText1R.setBounds(210, 80, 50, 20);
        maxValorText1R.addKeyListener(new KeyListener(){
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
        rotulo.setBounds(270, 40, 80, 20);

        cor.setBackground(new java.awt.Color(102, 102, 255));
        cor.setFont(new java.awt.Font("Times New Roman", 0, 18));
        cor.setForeground(new java.awt.Color(255, 255, 255));
        cor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cor.setText("Cor");
        cor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        cor.setOpaque(true);
        getContentPane().add(cor);
        cor.setBounds(360, 40, 50, 20);

        classeL11.setBackground(new java.awt.Color(255, 255, 255));
        classeL11.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL11.setText("1");
        classeL11.setOpaque(true);
        getContentPane().add(classeL11);
        classeL11.setBounds(80, 110, 60, 20);

        classeL111.setBackground(new java.awt.Color(255, 255, 255));
        classeL111.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL111.setText("1");
        classeL111.setOpaque(true);
        getContentPane().add(classeL111);
        classeL111.setBounds(80, 140, 60, 20);

        classeL2.setBackground(new java.awt.Color(255, 255, 255));
        classeL2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL2.setText("2");
        classeL2.setOpaque(true);
        getContentPane().add(classeL2);
        classeL2.setBounds(80, 190, 60, 20);

        classeL22.setBackground(new java.awt.Color(255, 255, 255));
        classeL22.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL22.setText("2");
        classeL22.setOpaque(true);
        getContentPane().add(classeL22);
        classeL22.setBounds(80, 220, 60, 20);

        miniValorText1G.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText1G.setBorder(null);
        getContentPane().add(miniValorText1G);
        miniValorText1G.setBounds(150, 110, 50, 20);
        miniValorText1G.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });           

        miniValorText1B.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText1B.setBorder(null);
        getContentPane().add(miniValorText1B);
        miniValorText1B.setBounds(150, 140, 50, 20);
        miniValorText1B.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        miniValorText2R.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText2R.setBorder(null);
        getContentPane().add(miniValorText2R);
        miniValorText2R.setBounds(150, 190, 50, 20);
        miniValorText2R.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        miniValorText2G.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText2G.setBorder(null);
        getContentPane().add(miniValorText2G);
        miniValorText2G.setBounds(150, 220, 50, 20);
        miniValorText2G.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText1G.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText1G.setBorder(null);
        maxValorText1G.setName("max");
        getContentPane().add(maxValorText1G);
        maxValorText1G.setBounds(210, 110, 50, 20);
        maxValorText1G.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });          

        maxValorText1B.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText1B.setBorder(null);
        maxValorText1B.setName("max");
        getContentPane().add(maxValorText1B);
        maxValorText1B.setBounds(210, 140, 50, 20);
        maxValorText1B.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText2R.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText2R.setBorder(null);
        maxValorText2R.setName("max");
        getContentPane().add(maxValorText2R);
        maxValorText2R.setBounds(210, 190, 50, 20);
        maxValorText2R.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText2G.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText2G.setBorder(null);
        maxValorText2G.setName("max");
        getContentPane().add(maxValorText2G);
        maxValorText2G.setBounds(210, 220, 50, 20);
        maxValorText2G.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        rotuloTextC1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        rotuloTextC1.setBorder(null);
        getContentPane().add(rotuloTextC1);
        rotuloTextC1.setBounds(270, 110, 80, 20);

        rotuloTextC2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        rotuloTextC2.setBorder(null);
        getContentPane().add(rotuloTextC2);
        rotuloTextC2.setBounds(270, 220, 80, 20);

        corL1.setBackground(new java.awt.Color(0, 0, 0));
        corL1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        corL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        corL1.setOpaque(true);
        getContentPane().add(corL1);
        corL1.setBounds(360, 110, 50, 20);

        corL2.setBackground(new java.awt.Color(0, 0, 0));
        corL2.setFont(new java.awt.Font("Times New Roman", 0, 14));
        corL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        corL2.setOpaque(true);
        getContentPane().add(corL2);
        corL2.setBounds(360, 220, 50, 20);

        classeL222.setBackground(new java.awt.Color(255, 255, 255));
        classeL222.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL222.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL222.setText("2");
        classeL222.setOpaque(true);
        getContentPane().add(classeL222);
        classeL222.setBounds(80, 250, 60, 20);

        miniValorText2B.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText2B.setBorder(null);
        getContentPane().add(miniValorText2B);
        miniValorText2B.setBounds(150, 250, 50, 20);
        miniValorText2B.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText2B.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText2B.setBorder(null);
        maxValorText2B.setName("max");
        getContentPane().add(maxValorText2B);
        maxValorText2B.setBounds(210, 250, 50, 20);
        maxValorText2B.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        classeL3.setBackground(new java.awt.Color(255, 255, 255));
        classeL3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL3.setText("3");
        classeL3.setOpaque(true);
        getContentPane().add(classeL3);
        classeL3.setBounds(80, 300, 60, 20);

        classeL33.setBackground(new java.awt.Color(255, 255, 255));
        classeL33.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL33.setText("3");
        classeL33.setOpaque(true);
        getContentPane().add(classeL33);
        classeL33.setBounds(80, 330, 60, 20);

        classeL333.setBackground(new java.awt.Color(255, 255, 255));
        classeL333.setFont(new java.awt.Font("Times New Roman", 0, 14));
        classeL333.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classeL333.setText("3");
        classeL333.setOpaque(true);
        getContentPane().add(classeL333);
        classeL333.setBounds(80, 360, 60, 20);

        R.setBackground(new java.awt.Color(255, 51, 51));
        R.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R.setForeground(new java.awt.Color(255, 255, 255));
        R.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R.setText("R");
        R.setOpaque(true);
        getContentPane().add(R);
        R.setBounds(50, 80, 20, 20);

        R1.setBackground(new java.awt.Color(255, 51, 51));
        R1.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R1.setForeground(new java.awt.Color(255, 255, 255));
        R1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R1.setText("R");
        R1.setOpaque(true);
        getContentPane().add(R1);
        R1.setBounds(50, 190, 20, 20);

        R2.setBackground(new java.awt.Color(255, 51, 51));
        R2.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R2.setForeground(new java.awt.Color(255, 255, 255));
        R2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R2.setText("R");
        R2.setOpaque(true);
        getContentPane().add(R2);
        R2.setBounds(50, 300, 20, 20);

        R3.setBackground(new java.awt.Color(0, 153, 0));
        R3.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R3.setForeground(new java.awt.Color(255, 255, 255));
        R3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R3.setText("G");
        R3.setOpaque(true);
        getContentPane().add(R3);
        R3.setBounds(50, 110, 20, 20);

        R4.setBackground(new java.awt.Color(0, 153, 0));
        R4.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R4.setForeground(new java.awt.Color(255, 255, 255));
        R4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R4.setText("G");
        R4.setOpaque(true);
        getContentPane().add(R4);
        R4.setBounds(50, 220, 20, 20);

        R5.setBackground(new java.awt.Color(0, 153, 0));
        R5.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R5.setForeground(new java.awt.Color(255, 255, 255));
        R5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R5.setText("G");
        R5.setOpaque(true);
        getContentPane().add(R5);
        R5.setBounds(50, 330, 20, 20);

        R6.setBackground(new java.awt.Color(51, 51, 255));
        R6.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R6.setForeground(new java.awt.Color(255, 255, 255));
        R6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R6.setText("B");
        R6.setOpaque(true);
        getContentPane().add(R6);
        R6.setBounds(50, 360, 20, 20);

        R7.setBackground(new java.awt.Color(51, 51, 255));
        R7.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R7.setForeground(new java.awt.Color(255, 255, 255));
        R7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R7.setText("B");
        R7.setOpaque(true);
        getContentPane().add(R7);
        R7.setBounds(50, 250, 20, 20);

        R8.setBackground(new java.awt.Color(51, 51, 255));
        R8.setFont(new java.awt.Font("Times New Roman", 1, 14));
        R8.setForeground(new java.awt.Color(255, 255, 255));
        R8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R8.setText("B");
        R8.setOpaque(true);
        getContentPane().add(R8);
        R8.setBounds(50, 140, 20, 20);

        miniValorText3R.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText3R.setBorder(null);
        getContentPane().add(miniValorText3R);
        miniValorText3R.setBounds(150, 300, 50, 20);
        miniValorText3R.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        miniValorText3G.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText3G.setBorder(null);
        getContentPane().add(miniValorText3G);
        miniValorText3G.setBounds(150, 330, 50, 20);
        miniValorText3G.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        miniValorText3B.setFont(new java.awt.Font("Times New Roman", 0, 14));
        miniValorText3B.setBorder(null);
        getContentPane().add(miniValorText3B);
        miniValorText3B.setBounds(150, 360, 50, 20);
        miniValorText3B.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText3R.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText3R.setName("max");
        maxValorText3R.setBorder(null);
        getContentPane().add(maxValorText3R);
        maxValorText3R.setBounds(210, 300, 50, 20);
        maxValorText3R.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText3G.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText3G.setBorder(null);
        maxValorText3G.setName("max");
        getContentPane().add(maxValorText3G);
        maxValorText3G.setBounds(210, 330, 50, 20);
        maxValorText3G.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        maxValorText3B.setFont(new java.awt.Font("Times New Roman", 0, 14));
        maxValorText3B.setBorder(null);
        maxValorText3B.setName("max");
        getContentPane().add(maxValorText3B);
        maxValorText3B.setBounds(210, 360, 50, 20);
        maxValorText3B.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				verificaValor((JTextField)e.getSource());				
			}
			public void keyTyped(KeyEvent e) {}
        	
        });         

        rotuloTextC3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        rotuloTextC3.setBorder(null);
        getContentPane().add(rotuloTextC3);
        rotuloTextC3.setBounds(270, 330, 80, 20);

        corL3.setBackground(new java.awt.Color(0, 0, 0));
        corL3.setFont(new java.awt.Font("Times New Roman", 0, 14));
        corL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        corL3.setOpaque(true);
        getContentPane().add(corL3);
        corL3.setBounds(360, 330, 50, 20);
        
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
        if (numeroClasses == 1) {
        	if (numeroCores == 3) {
	        	classeL2.setBackground(Color.lightGray);
	        	classeL22.setBackground(Color.lightGray);
	        	classeL222.setBackground(Color.lightGray);
	        	
	        	miniValorText2R.setEditable(false);
	        	miniValorText2R.setOpaque(true);
	        	miniValorText2R.setBackground(Color.lightGray);
	        	
	        	miniValorText2G.setEditable(false);
	        	miniValorText2G.setOpaque(true);
	        	miniValorText2G.setBackground(Color.lightGray);
	        	
	        	miniValorText2B.setEditable(false);
	        	miniValorText2B.setOpaque(true);
	        	miniValorText2B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText2R.setEditable(false);
	        	maxValorText2R.setOpaque(true);
	        	maxValorText2R.setBackground(Color.lightGray);
	        	
	        	maxValorText2G.setEditable(false);
	        	maxValorText2G.setOpaque(true);
	        	maxValorText2G.setBackground(Color.lightGray);
	        	
	        	maxValorText2B.setEditable(false);
	        	maxValorText2B.setOpaque(true);
	        	maxValorText2B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC2.setEditable(false);
	        	rotuloTextC2.setOpaque(true);
	        	rotuloTextC2.setBackground(Color.lightGray);
	        	corL2.setBackground(Color.lightGray);
	        	corL2.setEnabled(false); 
	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);        	
        	}
        	if (numeroCores == 2 && corExcluida == 1) {
        		classeL1.setBackground(Color.lightGray);
        		
	        	miniValorText1R.setEditable(false);
	        	miniValorText1R.setOpaque(true);
	        	miniValorText1R.setBackground(Color.lightGray);   
	        	
	        	maxValorText1R.setEditable(false);
	        	maxValorText1R.setOpaque(true);
	        	maxValorText1R.setBackground(Color.lightGray);	        	
        		
	        	classeL2.setBackground(Color.lightGray);
	        	classeL22.setBackground(Color.lightGray);
	        	classeL222.setBackground(Color.lightGray);
	        	
	        	miniValorText2R.setEditable(false);
	        	miniValorText2R.setOpaque(true);
	        	miniValorText2R.setBackground(Color.lightGray);
	        	
	        	miniValorText2G.setEditable(false);
	        	miniValorText2G.setOpaque(true);
	        	miniValorText2G.setBackground(Color.lightGray);
	        	
	        	miniValorText2B.setEditable(false);
	        	miniValorText2B.setOpaque(true);
	        	miniValorText2B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText2R.setEditable(false);
	        	maxValorText2R.setOpaque(true);
	        	maxValorText2R.setBackground(Color.lightGray);
	        	
	        	maxValorText2G.setEditable(false);
	        	maxValorText2G.setOpaque(true);
	        	maxValorText2G.setBackground(Color.lightGray);
	        	
	        	maxValorText2B.setEditable(false);
	        	maxValorText2B.setOpaque(true);
	        	maxValorText2B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC2.setEditable(false);
	        	rotuloTextC2.setOpaque(true);
	        	rotuloTextC2.setBackground(Color.lightGray);
	        	corL2.setBackground(Color.lightGray);
	        	corL2.setEnabled(false); 
	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);        	
        	}     
        	if (numeroCores == 2 && corExcluida == 2) {
        		classeL11.setBackground(Color.lightGray);
        		
	        	miniValorText1G.setEditable(false);
	        	miniValorText1G.setOpaque(true);
	        	miniValorText1G.setBackground(Color.lightGray);   
	        	
	        	maxValorText1G.setEditable(false);
	        	maxValorText1G.setOpaque(true);
	        	maxValorText1G.setBackground(Color.lightGray);	        	
        		
	        	classeL2.setBackground(Color.lightGray);
	        	classeL22.setBackground(Color.lightGray);
	        	classeL222.setBackground(Color.lightGray);
	        	
	        	miniValorText2R.setEditable(false);
	        	miniValorText2R.setOpaque(true);
	        	miniValorText2R.setBackground(Color.lightGray);
	        	
	        	miniValorText2G.setEditable(false);
	        	miniValorText2G.setOpaque(true);
	        	miniValorText2G.setBackground(Color.lightGray);
	        	
	        	miniValorText2B.setEditable(false);
	        	miniValorText2B.setOpaque(true);
	        	miniValorText2B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText2R.setEditable(false);
	        	maxValorText2R.setOpaque(true);
	        	maxValorText2R.setBackground(Color.lightGray);
	        	
	        	maxValorText2G.setEditable(false);
	        	maxValorText2G.setOpaque(true);
	        	maxValorText2G.setBackground(Color.lightGray);
	        	
	        	maxValorText2B.setEditable(false);
	        	maxValorText2B.setOpaque(true);
	        	maxValorText2B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC2.setEditable(false);
	        	rotuloTextC2.setOpaque(true);
	        	rotuloTextC2.setBackground(Color.lightGray);
	        	corL2.setBackground(Color.lightGray);
	        	corL2.setEnabled(false); 
	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);        	
        	}     
        	if (numeroCores == 2 && corExcluida == 3) {
        		classeL111.setBackground(Color.lightGray);
        		
	        	miniValorText1B.setEditable(false);
	        	miniValorText1B.setOpaque(true);
	        	miniValorText1B.setBackground(Color.lightGray);   
	        	
	        	maxValorText1B.setEditable(false);
	        	maxValorText1B.setOpaque(true);
	        	maxValorText1B.setBackground(Color.lightGray);	        	
        		
	        	classeL2.setBackground(Color.lightGray);
	        	classeL22.setBackground(Color.lightGray);
	        	classeL222.setBackground(Color.lightGray);
	        	
	        	miniValorText2R.setEditable(false);
	        	miniValorText2R.setOpaque(true);
	        	miniValorText2R.setBackground(Color.lightGray);
	        	
	        	miniValorText2G.setEditable(false);
	        	miniValorText2G.setOpaque(true);
	        	miniValorText2G.setBackground(Color.lightGray);
	        	
	        	miniValorText2B.setEditable(false);
	        	miniValorText2B.setOpaque(true);
	        	miniValorText2B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText2R.setEditable(false);
	        	maxValorText2R.setOpaque(true);
	        	maxValorText2R.setBackground(Color.lightGray);
	        	
	        	maxValorText2G.setEditable(false);
	        	maxValorText2G.setOpaque(true);
	        	maxValorText2G.setBackground(Color.lightGray);
	        	
	        	maxValorText2B.setEditable(false);
	        	maxValorText2B.setOpaque(true);
	        	maxValorText2B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC2.setEditable(false);
	        	rotuloTextC2.setOpaque(true);
	        	rotuloTextC2.setBackground(Color.lightGray);
	        	corL2.setBackground(Color.lightGray);
	        	corL2.setEnabled(false); 
	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);        	
        	}        	        	        	
        } else {
	        if (numeroClasses < 3 && numeroCores == 3) {
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);
	        }       
	        
	        
	        if (numeroClasses <= 3 && numeroCores == 2 && corExcluida == 1) {
	        	classeL1.setBackground(Color.lightGray);
	        	miniValorText1R.setEditable(false);
	        	miniValorText1R.setOpaque(true);
	        	miniValorText1R.setBackground(Color.lightGray);        	
	        	maxValorText1R.setEditable(false);
	        	maxValorText1R.setOpaque(true);
	        	maxValorText1R.setBackground(Color.lightGray);          	
	        	
	        	classeL2.setBackground(Color.lightGray);
	        	miniValorText2R.setEditable(false);
	        	miniValorText2R.setOpaque(true);
	        	miniValorText2R.setBackground(Color.lightGray);          	
	        	maxValorText2R.setEditable(false);   
	        	maxValorText2R.setOpaque(true);
	        	maxValorText2R.setBackground(Color.lightGray);          	
	        	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);
	        }
	        
	        if (numeroClasses <= 3 && numeroCores == 2 && corExcluida == 2) {
	        	classeL11.setBackground(Color.lightGray);
	        	miniValorText1G.setEditable(false);
	        	miniValorText1G.setOpaque(true);
	        	miniValorText1G.setBackground(Color.lightGray);           	
	        	maxValorText1G.setEditable(false);
	        	maxValorText1G.setOpaque(true);
	        	maxValorText1G.setBackground(Color.lightGray);          	
	        	
	        	classeL22.setBackground(Color.lightGray);
	        	miniValorText2G.setEditable(false);
	        	miniValorText2G.setOpaque(true);
	        	miniValorText2G.setBackground(Color.lightGray);            	
	        	maxValorText2G.setEditable(false);     
	        	maxValorText2G.setOpaque(true);
	        	maxValorText2G.setBackground(Color.lightGray);           	
	        	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);
	        }
	        
	        if (numeroClasses <= 3 && numeroCores == 2 && corExcluida == 3) {
	        	classeL111.setBackground(Color.lightGray);
	        	miniValorText1B.setEditable(false);
	        	miniValorText1B.setOpaque(true);
	        	miniValorText1B.setBackground(Color.lightGray);           	
	        	maxValorText1B.setEditable(false);
	        	maxValorText1B.setOpaque(true);
	        	maxValorText1B.setBackground(Color.lightGray);          	
	        	
	        	classeL222.setBackground(Color.lightGray);
	        	miniValorText2B.setEditable(false);
	        	miniValorText2B.setOpaque(true);
	        	miniValorText2B.setBackground(Color.lightGray);            	
	        	maxValorText2B.setEditable(false);     
	        	maxValorText2B.setOpaque(true);
	        	maxValorText2B.setBackground(Color.lightGray);           	
	        	
	        	classeL3.setBackground(Color.lightGray);
	        	classeL33.setBackground(Color.lightGray);
	        	classeL333.setBackground(Color.lightGray);
	        	
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);
	        	
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);
	        	
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	      	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);        	
	        	
	        	
	        	rotuloTextC3.setEditable(false);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.lightGray);
	        	corL3.setBackground(Color.lightGray);
	        	corL3.setEnabled(false);
	        } 
	        
	        if (numeroClasses == 3) {
	        	classeL3.setBackground(Color.white);
	        	classeL33.setBackground(Color.white);
	        	classeL333.setBackground(Color.white);
	        	
	        	miniValorText3R.setEditable(true);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.white);
	        	
	        	miniValorText3G.setEditable(true);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.white);
	        	
	        	miniValorText3B.setEditable(true);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.white);        	
	        	      	
	        	maxValorText3R.setEditable(true);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.white);
	        	
	        	maxValorText3G.setEditable(true);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.white);
	        	
	        	maxValorText3B.setEditable(true);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.white);        	
	        	
	        	
	        	rotuloTextC3.setEditable(true);
	        	rotuloTextC3.setOpaque(true);
	        	rotuloTextC3.setBackground(Color.white);
	        	corL3.setBackground(Color.black);
	        	corL3.setEnabled(true);        	
	        }
	        
	        if (numeroClasses == 3 && numeroCores == 2 && corExcluida == 1) {
	        	System.out.println("luciana");
	        	classeL3.setBackground(Color.lightGray);
	        	miniValorText3R.setEditable(false);
	        	miniValorText3R.setOpaque(true);
	        	miniValorText3R.setBackground(Color.lightGray);        	
	        	maxValorText3R.setEditable(false);
	        	maxValorText3R.setOpaque(true);
	        	maxValorText3R.setBackground(Color.lightGray);
	        } 
	
	        if (numeroClasses == 3 && numeroCores == 2 && corExcluida == 2) {
	        	classeL33.setBackground(Color.lightGray);
	        	miniValorText3G.setEditable(false);
	        	miniValorText3G.setOpaque(true);
	        	miniValorText3G.setBackground(Color.lightGray);        	
	        	maxValorText3G.setEditable(false);
	        	maxValorText3G.setOpaque(true);
	        	maxValorText3G.setBackground(Color.lightGray);
	        } 
	        
	        if (numeroClasses == 3 && numeroCores == 2 && corExcluida == 3) {
	        	classeL333.setBackground(Color.lightGray);
	        	miniValorText3B.setEditable(false);
	        	miniValorText3B.setOpaque(true);
	        	miniValorText3B.setBackground(Color.lightGray);        	
	        	maxValorText3B.setEditable(false);
	        	maxValorText3B.setOpaque(true);
	        	maxValorText3B.setBackground(Color.lightGray);
	        }     
        }


        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-459)/2, (screenSize.height-502)/2, 459, 502);
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
    	if (numeroClasses == 1 && numeroCores == 3 &&
    			!miniValorText1R.getText().equals("") &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&      		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")) {
        		return true;
        }
    	if (numeroCores == 2 && corExcluida == 1 &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&      		
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")) {
        		return true;
        }  
    	if (numeroCores == 2 && corExcluida == 2 &&
    			!miniValorText1R.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&      		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")) {
        		return true;
        }
    	if (numeroCores == 2 && corExcluida == 3 &&
    			!miniValorText1R.getText().equals("") &&
        		!miniValorText1G.getText().equals("") &&   		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1G.getText().equals("")) {
        		return true;
        }    	
    	if (numeroClasses == 2 && numeroCores == 2 && corExcluida == 1 &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&
        		!miniValorText2G.getText().equals("") &&
        		!miniValorText2B.getText().equals("") &&        		
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")  &&
        		!maxValorText2G.getText().equals("")  &&
        		!maxValorText2B.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 2 && numeroCores == 2 && corExcluida == 2 &&
        		!miniValorText1R.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&
        		!miniValorText2R.getText().equals("") &&
        		!miniValorText2B.getText().equals("") &&        		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")  &&
        		!maxValorText2R.getText().equals("")  &&
        		!maxValorText2B.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 2 && numeroCores == 2 && corExcluida == 3 &&
        		!miniValorText1R.getText().equals("") &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText2R.getText().equals("") &&
        		!miniValorText2G.getText().equals("") &&        		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText2R.getText().equals("")  &&
        		!maxValorText2G.getText().equals("")) {
        		return true;
        }   
    	if (numeroClasses == 2 && numeroCores == 3 &&
        		!miniValorText1R.getText().equals("") &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&
        		!miniValorText2R.getText().equals("") &&
        		!miniValorText2G.getText().equals("") &&
        		!miniValorText2B.getText().equals("") && 
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")  &&
        		!maxValorText2R.getText().equals("")  &&
        		!maxValorText2B.getText().equals("")  &&
        		!maxValorText2G.getText().equals("")) {
        		return true;
        }      	
    	if (numeroClasses == 3 && numeroCores == 2 && corExcluida == 1 &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&
        		!miniValorText2G.getText().equals("") &&
        		!miniValorText2B.getText().equals("") &&
        		!miniValorText3G.getText().equals("") &&
        		!miniValorText3B.getText().equals("") &&           		
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")  &&
        		!maxValorText2G.getText().equals("")  &&
        		!maxValorText2B.getText().equals("")  &&
        		!maxValorText3G.getText().equals("")  &&
        		!maxValorText3B.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 3 && numeroCores == 2 && corExcluida == 2 &&
        		!miniValorText1R.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&
        		!miniValorText2R.getText().equals("") &&
        		!miniValorText2B.getText().equals("") &&
        		!miniValorText3R.getText().equals("") &&
        		!miniValorText3B.getText().equals("") &&           		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")  &&
        		!maxValorText2R.getText().equals("")  &&
        		!maxValorText2B.getText().equals("")  &&
        		!maxValorText3R.getText().equals("")  &&
        		!maxValorText3B.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 3 && numeroCores == 2 && corExcluida == 3 &&
        		!miniValorText1R.getText().equals("") &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText2R.getText().equals("") &&
        		!miniValorText2G.getText().equals("") &&
        		!miniValorText3R.getText().equals("") &&
        		!miniValorText3G.getText().equals("") &&          		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText2R.getText().equals("")  &&
        		!maxValorText2G.getText().equals("")  &&
        		!maxValorText3R.getText().equals("")  &&
        		!maxValorText3G.getText().equals("")) {
        		return true;
        }
    	if (numeroClasses == 3 && numeroCores == 3 &&
        		!miniValorText1R.getText().equals("") &&
        		!miniValorText1G.getText().equals("") &&
        		!miniValorText1B.getText().equals("") &&
        		!miniValorText2R.getText().equals("") &&
        		!miniValorText2G.getText().equals("") &&
        		!miniValorText2B.getText().equals("") &&
        		!miniValorText3R.getText().equals("") &&
        		!miniValorText3G.getText().equals("") &&
        		!miniValorText3B.getText().equals("") &&           		
        		!maxValorText1R.getText().equals("")  &&
        		!maxValorText1G.getText().equals("")  &&
        		!maxValorText1B.getText().equals("")  &&
        		!maxValorText2R.getText().equals("")  &&
        		!maxValorText2G.getText().equals("")  &&
        		!maxValorText2B.getText().equals("")  &&
        		!maxValorText3R.getText().equals("")  &&
        		!maxValorText3G.getText().equals("")  &&
        		!maxValorText3B.getText().equals("")) {
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
    

    private javax.swing.JLabel R;
    private javax.swing.JLabel R1;
    private javax.swing.JLabel R2;
    private javax.swing.JLabel R3;
    private javax.swing.JLabel R4;
    private javax.swing.JLabel R5;
    private javax.swing.JLabel R6;
    private javax.swing.JLabel R7;
    private javax.swing.JLabel R8;
    private javax.swing.JLabel classeL;
    private javax.swing.JLabel classeL1;
    private javax.swing.JLabel classeL11;
    private javax.swing.JLabel classeL111;
    private javax.swing.JLabel classeL2;
    private javax.swing.JLabel classeL22;
    private javax.swing.JLabel classeL222;
    private javax.swing.JLabel classeL3;
    private javax.swing.JLabel classeL33;
    private javax.swing.JLabel classeL333;
    private javax.swing.JButton classificarButton;
    private javax.swing.JLabel cor;
    private javax.swing.JLabel corL1;
    private javax.swing.JLabel corL2;
    private javax.swing.JLabel corL3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField maxValorText1B;
    private javax.swing.JTextField maxValorText1G;
    private javax.swing.JTextField maxValorText1R;
    private javax.swing.JTextField maxValorText2B;
    private javax.swing.JTextField maxValorText2G;
    private javax.swing.JTextField maxValorText2R;
    private javax.swing.JTextField maxValorText3B;
    private javax.swing.JTextField maxValorText3G;
    private javax.swing.JTextField maxValorText3R;
    private javax.swing.JTextField miniValorText1B;
    private javax.swing.JTextField miniValorText1G;
    private javax.swing.JTextField miniValorText1R;
    private javax.swing.JTextField miniValorText2B;
    private javax.swing.JTextField miniValorText2G;
    private javax.swing.JTextField miniValorText2R;
    private javax.swing.JTextField miniValorText3B;
    private javax.swing.JTextField miniValorText3G;
    private javax.swing.JTextField miniValorText3R;
    private javax.swing.JLabel nivelMax;
    private javax.swing.JLabel nivelMini;
    private javax.swing.JLabel opcaoLabel;
    private javax.swing.JLabel rotulo;
    private javax.swing.JTextField rotuloTextC1;
    private javax.swing.JTextField rotuloTextC2;
    private javax.swing.JTextField rotuloTextC3;

	public int getNumeroCores() {
		return numeroCores;
	}

	public void setNumeroCores(int numeroCores) {
		this.numeroCores = numeroCores;
	}

	public int getCorExcluida() {
		return corExcluida;
	}

	public void setCorExcluida(int corExcluida) {
		this.corExcluida = corExcluida;
	}


}
