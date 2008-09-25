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

import simple.facade.Facade;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import simple.ui.janelas.SImPLe;





/**
 * Classe responsável pela exibição da ilusão de ótica 10.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class Ilusao10 extends JInternalFrame implements InternalFrameListener, MouseListener {
	
	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = 1L;	
	
	private PaletaDeCores paletaDeCores;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel18;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel24;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JLabel titulo;	
    
    private SImPLe fepdi;
	
	private Facade facade;
    
    private String nome = "Ilus\u00e3o de Ótica";
    
    /** Creates new form ilusao1 */
    public Ilusao10(SImPLe fepdi, Facade facade) {
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
        jLabel20 = new JLabel();
        jLabel19 = new JLabel();
        jLabel21 = new JLabel();
        jLabel24 = new JLabel();
        jLabel23 = new JLabel();
        jLabel22 = new JLabel();
        jLabel18 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jLabel17 = new JLabel();

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
        titulo.setBounds(160, 20, 270, 44);

        jLabel20.setBackground(new java.awt.Color(255, 153, 0));
        jLabel20.setOpaque(true);
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel20);
        jLabel20.setBounds(250, 210, 10, 40);

        jLabel19.setBackground(new java.awt.Color(255, 153, 0));
        jLabel19.setOpaque(true);
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel19);
        jLabel19.setBounds(230, 210, 10, 40);

        jLabel21.setBackground(new java.awt.Color(255, 153, 0));
        jLabel21.setOpaque(true);
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel21);
        jLabel21.setBounds(270, 210, 10, 40);

        jLabel24.setBackground(new java.awt.Color(255, 153, 0));
        jLabel24.setOpaque(true);
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel24);
        jLabel24.setBounds(330, 210, 10, 40);

        jLabel23.setBackground(new java.awt.Color(255, 153, 0));
        jLabel23.setOpaque(true);
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel23);
        jLabel23.setBounds(310, 210, 10, 40);

        jLabel22.setBackground(new java.awt.Color(255, 153, 0));
        jLabel22.setOpaque(true);
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel22);
        jLabel22.setBounds(290, 210, 10, 40);

        jLabel18.setBackground(new java.awt.Color(255, 153, 0));
        jLabel18.setOpaque(true);
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel18);
        jLabel18.setBounds(310, 120, 10, 40);

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 100, 10, 80);

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel3);
        jLabel3.setBounds(240, 100, 10, 80);

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel4);
        jLabel4.setBounds(260, 100, 10, 80);

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel5);
        jLabel5.setBounds(280, 100, 10, 80);

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel6);
        jLabel6.setBounds(300, 100, 10, 80);

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel7);
        jLabel7.setBounds(320, 100, 10, 80);

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel8);
        jLabel8.setBounds(230, 190, 10, 80);

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel9);
        jLabel9.setBounds(250, 190, 10, 80);

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel10);
        jLabel10.setBounds(270, 190, 10, 80);

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setOpaque(true);
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel11);
        jLabel11.setBounds(290, 190, 10, 80);

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setOpaque(true);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel12);
        jLabel12.setBounds(310, 190, 10, 80);

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel13);
        jLabel13.setBounds(330, 190, 10, 80);

        jLabel14.setBackground(new java.awt.Color(255, 153, 0));
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel14);
        jLabel14.setBounds(230, 120, 10, 40);

        jLabel15.setBackground(new java.awt.Color(255, 153, 0));
        jLabel15.setOpaque(true);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel15);
        jLabel15.setBounds(250, 120, 10, 40);

        jLabel16.setBackground(new java.awt.Color(255, 153, 0));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel16);
        jLabel16.setBounds(270, 120, 10, 40);

        jLabel17.setBackground(new java.awt.Color(255, 153, 0));
        jLabel17.setOpaque(true);
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        getContentPane().add(jLabel17);
        jLabel17.setBounds(290, 120, 10, 40);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-610)/2, (screenSize.height-421)/2, 610, 421);
    }
    

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinhaLaranja();
    }

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinha();
    }

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinha();
    }

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinha();
    }

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinha();
    }

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {
        mudaCorLinha();
    }

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {
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
            jLabel8.setBackground(paletaDeCores.getCorSelecionada());
            jLabel9.setBackground(paletaDeCores.getCorSelecionada());
            jLabel10.setBackground(paletaDeCores.getCorSelecionada());
            jLabel11.setBackground(paletaDeCores.getCorSelecionada());
            jLabel12.setBackground(paletaDeCores.getCorSelecionada());
            jLabel13.setBackground(paletaDeCores.getCorSelecionada());
            getContentPane().setComponentZOrder(jLabel8, getContentPane().getComponentCount() - 8);
            getContentPane().setComponentZOrder(jLabel9, getContentPane().getComponentCount() - 7);
            getContentPane().setComponentZOrder(jLabel10, getContentPane().getComponentCount() - 6);
            getContentPane().setComponentZOrder(jLabel11, getContentPane().getComponentCount() - 5);
            getContentPane().setComponentZOrder(jLabel12, getContentPane().getComponentCount() - 4);
            getContentPane().setComponentZOrder(jLabel13, getContentPane().getComponentCount() - 3);
            getContentPane().repaint();
        }
    }
    
        private void mudaCorLinhaLaranja() {
         if (paletaDeCores.isPinturaAtivada()) {
            jLabel14.setBackground(paletaDeCores.getCorSelecionada());
            jLabel15.setBackground(paletaDeCores.getCorSelecionada());
            jLabel16.setBackground(paletaDeCores.getCorSelecionada());
            jLabel17.setBackground(paletaDeCores.getCorSelecionada());
            jLabel18.setBackground(paletaDeCores.getCorSelecionada());
            jLabel19.setBackground(paletaDeCores.getCorSelecionada());
            jLabel20.setBackground(paletaDeCores.getCorSelecionada());
            jLabel21.setBackground(paletaDeCores.getCorSelecionada());
            jLabel22.setBackground(paletaDeCores.getCorSelecionada());
            jLabel23.setBackground(paletaDeCores.getCorSelecionada());
            jLabel24.setBackground(paletaDeCores.getCorSelecionada());
        }
    }

        
    public static JInternalFrame create(SImPLe fepdi, Facade facade){
    	JInternalFrame ilusao =  new Ilusao10(fepdi,facade);
    	ilusao.setVisible(true);
    	
    	return ilusao;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ilusao10(null,null).setVisible(true);
            }
        });
    }


	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		fepdi.verificaUltimo();
		facade.decrementaPosicao();
		fepdi.closeIlusao(10);
	}


	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
