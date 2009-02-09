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
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 * Classe responsável pela exibição da paleta de cores.
 * @author Luciana Cavalcante de Menezes.
 * @author Ricardo Madeira Fernandes.
 */
public class PaletaDeCores {
	
	private Image cursorImage;
	private Image cursorImageGrande;
	private Image escolhaDeCor;
	private boolean pinturaAtivada;
	private JButton botaoCor;
	private JButton botaoEscolherCor;
	private JLabel cor1;
	private JLabel cor10;
	private JLabel cor11;
	private JLabel cor12;
	private JLabel cor13;
	private JLabel cor14;
	private JLabel cor15;
	private JLabel cor16;
	private JLabel cor17;
	private JLabel cor18;
	private JLabel cor2;
	private JLabel cor3;
	private JLabel cor4;
	private JLabel cor5;
	private JLabel cor6;
	private JLabel cor7;
	private JLabel cor8;
	private JLabel cor9;
	private JLabel corSelecionada;
	private JInternalFrame frame;
	private JLabel opcoes;
	
	public PaletaDeCores(JInternalFrame _frame) {
		frame = _frame;
		pinturaAtivada = false;
		cursorImage = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("cursor.gif"));
		cursorImageGrande = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("cursorGrande.gif"));
		escolhaDeCor = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("cores.gif"));
		initComponents();
		botaoCor.setIcon(new ImageIcon(cursorImage));
		//botaoEscolherCor.setIcon(new ImageIcon(escolhaDeCor));		
	}
	
	/**
	 * Inicializa os componentes da paleta de cores.
	 */
	private void initComponents() {
		opcoes = new JLabel();
		botaoCor = new JButton();
		cor2 = new JLabel();
		cor1 = new JLabel();
		cor3 = new JLabel();
		cor4 = new JLabel();
		cor6 = new JLabel();
		cor5 = new JLabel();
		cor7 = new JLabel();
		cor8 = new JLabel();
		cor9 = new JLabel();
		cor10 = new JLabel();
		cor11 = new JLabel();
		cor12 = new JLabel();
		cor13 = new JLabel();
		cor14 = new JLabel();
		cor15 = new JLabel();
		cor16 = new JLabel();
		cor17 = new JLabel();
		corSelecionada = new JLabel();
		cor18 = new JLabel();
		botaoEscolherCor = new JButton();
		
		botaoCor.setBackground(new java.awt.Color(204, 204, 204));
		botaoCor.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		botaoCor.setOpaque(false);
		botaoCor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				botaoCorMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botaoCorMouseEntered(evt);
			}
		});
		
		frame.getContentPane().add(botaoCor);
		botaoCor.setBounds(50, 320, 30, 30);
		
		//eventos da cor 2
		cor2.setBackground(new java.awt.Color(0, 0, 0));
		cor2.setOpaque(true);
		cor2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor2.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor2);
		cor2.setBounds(120, 320, 15, 15);
		
		//eventos da cor 1
		cor1.setBackground(new java.awt.Color(255, 255, 255));
		cor1.setOpaque(true);
		cor1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor1.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor1);
		cor1.setBounds(120, 340, 15, 15);

		//eventos da cor 3
		cor3.setBackground(new java.awt.Color(204, 204, 204));
		cor3.setOpaque(true);
		cor3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor3.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor3);
		cor3.setBounds(140, 340, 15, 15);

		//eventos da cor 4
		cor4.setBackground(new java.awt.Color(153, 153, 153));
		cor4.setOpaque(true);
		cor4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor4.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor4);
		cor4.setBounds(140, 320, 15, 15);

		//eventos da cor 6
		cor6.setBackground(new java.awt.Color(153, 51, 0));
		cor6.setOpaque(true);
		cor6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor6.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor6);
		cor6.setBounds(160, 320, 15, 15);

		//eventos da cor 5
		cor5.setBackground(new java.awt.Color(255, 0, 0));
		cor5.setOpaque(true);
		cor5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor5.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor5);
		cor5.setBounds(160, 340, 15, 15);

		//eventos da cor 7
		cor7.setBackground(new java.awt.Color(255, 255, 0));
		cor7.setOpaque(true);
		cor7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor7.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor7);
		cor7.setBounds(180, 340, 15, 15);

		//eventos da cor 8
		cor8.setBackground(new java.awt.Color(102, 102, 0));
		cor8.setOpaque(true);
		cor8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor8.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor8);
		cor8.setBounds(180, 320, 15, 15);

		//eventos da cor 9
		cor9.setBackground(new java.awt.Color(0, 255, 0));
		cor9.setOpaque(true);
		cor9.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor9.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor9);
		cor9.setBounds(200, 340, 15, 15);

		//eventos da cor 10
		cor10.setBackground(new java.awt.Color(0, 153, 0));
		cor10.setOpaque(true);
		cor10.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor10.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor10);
		cor10.setBounds(200, 320, 15, 15);

		//eventos da cor 11
		cor11.setBackground(new java.awt.Color(0, 255, 255));
		cor11.setOpaque(true);
		cor11.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor11.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor11);
		cor11.setBounds(220, 340, 15, 15);

		//eventos da cor 12
		cor12.setBackground(new java.awt.Color(0, 153, 153));
		cor12.setOpaque(true);
		cor12.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor12.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor12);
		cor12.setBounds(220, 320, 15, 15);

		//eventos da cor 13
		cor13.setBackground(new java.awt.Color(51, 51, 255));
		cor13.setOpaque(true);
		cor13.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor13.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor13);
		cor13.setBounds(240, 340, 15, 15);

		//eventos da cor 14
		cor14.setBackground(new java.awt.Color(51, 0, 153));
		cor14.setOpaque(true);
		cor14.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor14.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor14);
		cor14.setBounds(240, 320, 15, 15);

		//eventos da cor 15
		cor15.setBackground(new java.awt.Color(255, 51, 255));
		cor15.setOpaque(true);
		cor15.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor15.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor15);
		cor15.setBounds(260, 340, 15, 15);

		//eventos da cor 16
		cor16.setBackground(new java.awt.Color(153, 0, 153));
		cor16.setOpaque(true);
		cor16.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor16.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor16);
		cor16.setBounds(260, 320, 15, 15);

		//eventos da cor 17
		cor17.setBackground(new java.awt.Color(153, 102, 0));
		cor17.setOpaque(true);
		cor17.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor17.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor17);
		cor17.setBounds(280, 340, 15, 15);

		corSelecionada.setBackground(new java.awt.Color(0, 0, 0));
		corSelecionada.setOpaque(true);
		corSelecionada.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				corSelecionadaMouseEntered(evt);
			}
		});

		frame.getContentPane().add(corSelecionada);
		corSelecionada.setBounds(90, 320, 15, 35);

		//eventos da cor 18
		cor18.setBackground(new java.awt.Color(255, 102, 51));
		cor18.setOpaque(true);
		cor18.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				corSelecionada.setBackground(cor18.getBackground());
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				mudaParaCursorDefault();
			}
		});

		frame.getContentPane().add(cor18);
		cor18.setBounds(280, 320, 15, 15);

		botaoEscolherCor.setBackground(new java.awt.Color(204, 204, 204));
		botaoEscolherCor.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		botaoEscolherCor.setOpaque(false);
		botaoEscolherCor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				botaoEscolherCorMouseClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				botaoEscolherCorMouseEntered(evt);
			}
		});

		frame.getContentPane().add(botaoEscolherCor);
		botaoEscolherCor.setBounds(300, 320, 30, 35);
		
		opcoes.setBackground(new java.awt.Color(255, 0, 51));
		opcoes.setBorder(new javax.swing.border.TitledBorder(
				new javax.swing.border.TitledBorder("Op\u00e7\u00f5es")));
		opcoes.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				opcoesMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				opcoesMouseExited(evt);
			}
		});

		frame.getContentPane().add(opcoes);
		opcoes.setBounds(10, 300, 580, 70);
		
		
	}
	
	private void opcoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcoesMouseEntered
		mudaParaCursorDefault();
	}
	
	private void opcoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcoesMouseExited
		if (pinturaAtivada) {
			mudaParaCursorColor();
		}
	}	
	
	private void botaoEscolherCorMouseEntered(java.awt.event.MouseEvent evt) {
		mudaParaCursorDefault();
	}

	private void botaoEscolherCorMouseClicked(java.awt.event.MouseEvent evt) {
		Color cor = corSelecionada.getBackground();
		cor = JColorChooser.showDialog(frame, "Escolha uma cor", cor);
		if (cor != null) {
			corSelecionada.setBackground(cor);
		}
	}

	private void botaoCorMouseEntered(java.awt.event.MouseEvent evt) {
		mudaParaCursorDefault();
	}

	private void corSelecionadaMouseEntered(java.awt.event.MouseEvent evt) {
		mudaParaCursorDefault();
	}

	private void mudaParaCursorDefault() {
		frame.setCursor(Cursor.getDefaultCursor());
	}
	

	public boolean isPinturaAtivada() {
		return pinturaAtivada;
	}
	
	private void botaoCorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoCorMouseClicked
		if (pinturaAtivada) {
			pinturaAtivada = false;
			botaoCorDesativado();
			mudaParaCursorDefault();
		} else {
			pinturaAtivada = true;
			botaoCorAtivado();
		}
	}
	
	private void botaoCorAtivado() {
		botaoCor.setOpaque(true);
		botaoCor.setBackground(new java.awt.Color(255, 255, 255));
		botaoCor.setBorder(new javax.swing.border.BevelBorder(
				javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102,
						204, 255), new java.awt.Color(153, 153, 153),
				new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153,
						255)));
	}

	private void botaoCorDesativado() {
		botaoCor.setOpaque(false);
		botaoCor.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.RAISED));
	}
	
	private void mudaParaCursorColor() {
		Cursor meuCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImageGrande, new Point(24, 24), "CusorColor");
		frame.setCursor(meuCursor);
	}
	
	public Color getCorSelecionada() {
		return corSelecionada.getBackground();
	}
	
}
