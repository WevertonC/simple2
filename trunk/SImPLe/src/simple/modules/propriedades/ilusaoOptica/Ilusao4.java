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
import javax.swing.JLabel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import simple.ui.janelas.SImPLe;
import simple.facade.Facade;

/**
 * Classe responsável pela exibição da ilusão de ótica 4.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Ilusao4 extends JInternalFrame implements InternalFrameListener, MouseListener{
	
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;
	private PaletaDeCores paletaDeCores;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel fundo1;
    private javax.swing.JLabel fundo2;
    private javax.swing.JLabel titulo;

    private BotaoRedondo circulo1;
    private BotaoRedondo circulo2;
    private BotaoRedondo circulo3;
    private BotaoRedondo circulo4;
    private BotaoRedondo circulo5;
    private BotaoRedondo circulo6;	

    private SImPLe fepdi;
	
	private Facade facade;
    
    private String nome = "Ilus\u00e3o de Ótica";
    
    /** Creates new form ilusao1 */
    public Ilusao4(SImPLe fepdi, Facade facade){
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
        
        circulo2 = new BotaoRedondo("");
        getContentPane().add(circulo2);
        circulo2.setBackground(Color.red);
        circulo2.setBounds(75, 135, 70, 70);
        circulo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mudaCorCirculo(circulo2, fundo1);
            }
        });
        
        circulo1 = new BotaoRedondo("");
        getContentPane().add(circulo1);
        circulo1.setBackground(Color.yellow);
        circulo1.setBounds(50, 110, 120, 120);
        circulo1.setForeground(Color.black);
        circulo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mudaCorCirculoMaior(circulo1,circulo2);
            }
        });
        
        circulo4 = new BotaoRedondo("");
        getContentPane().add(circulo4);
        circulo4.setBackground(Color.blue);
        circulo4.setBounds(265, 135, 70, 70);
        circulo4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mudaCorCirculo(circulo4, fundo);
            }
        });        
        
        circulo3 = new BotaoRedondo("");
        getContentPane().add(circulo3);
        circulo3.setBackground(Color.yellow);
        circulo3.setBounds(240, 110, 120, 120);
        circulo3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               mudaCorCirculoMaior(circulo3, circulo4);
            }
        });               
        
        circulo6 = new BotaoRedondo("");
        getContentPane().add(circulo6);
        circulo6.setBackground(Color.green);
        circulo6.setBounds(455, 135, 70, 70);
        circulo6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mudaCorCirculo(circulo6, fundo2);
            }
        });
        
        circulo5 = new BotaoRedondo("");
        getContentPane().add(circulo5);
        circulo5.setBackground(Color.yellow);
        circulo5.setBounds(430, 110, 120, 120);
        circulo5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               mudaCorCirculoMaior(circulo5,circulo6);
            }
        });        

    }
    
    private void mudaCorCirculo(BotaoRedondo circulo, JLabel quadrado) {
        if (paletaDeCores.isPinturaAtivada()) {
            circulo.setBackground(paletaDeCores.getCorSelecionada());
            quadrado.setBackground(paletaDeCores.getCorSelecionada());
            getContentPane().setComponentZOrder(quadrado, getContentPane().getComponentCount() -1);            
            getContentPane().repaint();            
        }
    }
    
    private void mudaCorCirculoMaior(BotaoRedondo circuloMaior, BotaoRedondo circuloMenor) {
        if (paletaDeCores.isPinturaAtivada()) {
            circuloMaior.setBackground(paletaDeCores.getCorSelecionada());            
            getContentPane().setComponentZOrder(circuloMenor, 0);            
            getContentPane().repaint();            
        }
    }    
    

    private void initComponents() {
        titulo = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();
        fundo1 = new javax.swing.JLabel();
        fundo2 = new javax.swing.JLabel();

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

        fundo.setBackground(new java.awt.Color(0, 0, 255));
        fundo.setOpaque(true);
        fundo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fundoMouseClicked(evt);
            }
        });

        getContentPane().add(fundo);
        fundo.setBounds(210, 80, 180, 180);

        fundo1.setBackground(new java.awt.Color(255, 0, 51));
        fundo1.setOpaque(true);
        fundo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fundo1MouseClicked(evt);
            }
        });

        getContentPane().add(fundo1);
        fundo1.setBounds(20, 80, 180, 180);

        fundo2.setBackground(java.awt.Color.green);
        fundo2.setOpaque(true);
        fundo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fundo2MouseClicked(evt);
            }
        });

        getContentPane().add(fundo2);
        fundo2.setBounds(400, 80, 180, 180);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-610)/2, (screenSize.height-421)/2, 610, 421);
    }
   
     
    private void fundo2MouseClicked(java.awt.event.MouseEvent evt) {
        if (paletaDeCores.isPinturaAtivada()) {
            fundo2.setBackground(paletaDeCores.getCorSelecionada());
            circulo6.setBackground(paletaDeCores.getCorSelecionada());
            getContentPane().setComponentZOrder(fundo, getContentPane().getComponentCount() -1);            
            getContentPane().repaint();
        }
    }

    private void fundo1MouseClicked(java.awt.event.MouseEvent evt) {
        if (paletaDeCores.isPinturaAtivada()) {
            fundo1.setBackground(paletaDeCores.getCorSelecionada());
            circulo2.setBackground(paletaDeCores.getCorSelecionada());
            getContentPane().setComponentZOrder(fundo, getContentPane().getComponentCount() -1);            
            getContentPane().repaint();
        }
    }

    private void fundoMouseClicked(java.awt.event.MouseEvent evt) {
        if (paletaDeCores.isPinturaAtivada()) {
            fundo.setBackground(paletaDeCores.getCorSelecionada());
            circulo4.setBackground(paletaDeCores.getCorSelecionada());
            getContentPane().setComponentZOrder(fundo, getContentPane().getComponentCount() -1);            
            getContentPane().repaint();
        }
    }
    
    public static JInternalFrame create(SImPLe fepdi, Facade facade){
    	JInternalFrame ilusao =  new Ilusao4(fepdi,facade);
    	ilusao.setVisible(true);
    	return ilusao;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ilusao4(null,null).setVisible(true);
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
		fepdi.closeIlusao(4);
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
