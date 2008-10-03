package simple.ajuda;
/*
 * Janela de Ajuda
 * 
 * @version 1.0
 * 
 * Date: 23/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JTextPane;

/**
 * Janela que contem um texto explicativo referente a ajuda.
 * @version 1.0 23/11/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Ajuda extends JDialog implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JTextPane texto;
	private Map<String,String> topicos;
	
	public static final String AJUDA_ABRIR = "Abrir";
	public static final String AJUDA_ABRIR_NOVO = "Abrir Novo";
	public static final String AJUDA_COMBINAR_CANAIS = "Combinar Canais";
	public static final String AJUDA_COMPRIMIR = "Comprimir";
	public static final String AJUDA_COORDENADA_PIXEL = "Coordenada por Pixel";
	public static final String AJUDA_DECOMPOR_CANAIS = "Decompor Canais";
	public static final String AJUDA_DESFAZER = "Desfazer";
	public static final String AJUDA_ESCALA_CINZA = "Escala de Cinza";
	public static final String AJUDA_FECHAR = "Fechar";
	public static final String AJUDA_FILTRO = "Filtro";
	public static final String AJUDA_HISTOGRAMA_CANAL = "Histograma por Canal";
	public static final String AJUDA_HISTOGRAMA_COLORIDO = "Histograma Colorido";
	public static final String AJUDA_ILUSAO_OPTICA = "Ilusão de Ótica";
	public static final String AJUDA_OP_ARIT_BASICA = "Operações Aritméticas Básicas";
	public static final String AJUDA_OP_ARIT_PESSOAL = "Operação Aritmética Pessoal";
	public static final String AJUDA_OP_LOGICA = "Operações Lógicas";
	public static final String AJUDA_PERFIL_COLUNA = "Perfil de Coluna Simples";
	public static final String AJUDA_PERFIL_LINHA = "Perfil de Linha Simples";
	public static final String AJUDA_PSEUDOCOLORIZACAO = "Pseudo Colorização";
	public static final String AJUDA_REDIMENSIONAR = "Redimensionar";
	public static final String AJUDA_FOURIER = "Fourier";
	
	/**
	 * Construtor da Ajuda
	 *
	 */
	public Ajuda(String nome, String titulo){
		texto = new JTextPane();
		
		texto.setText(leArquivo(new File("Resource/Help/"+ nome)));
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		
		setLocation(screenWidth/2 - 400/2,screenHeight/2 - 400/2);
		setSize(400,400);
		texto.setBackground(new Color(255,255,225));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Ajuda - " + titulo);
		setModal(true);
		getContentPane().add(texto);
		
		setResizable(true);
		texto.setEditable(false);
		texto.addKeyListener(this);
		setVisible(true);	
	}
	
	public Ajuda(String topico) {
		initializeHash();
		String fileName = (String)topicos.get(topico);
		new Ajuda(fileName, topico);
	}
	
	private void initializeHash() {
		topicos = new HashMap<String, String>();
		topicos.put(AJUDA_ABRIR, "Abrir.txt");
		topicos.put(AJUDA_ABRIR_NOVO, "AbrirNovo.txt");
		topicos.put(AJUDA_COMBINAR_CANAIS, "CombinarCanais.txt");
		topicos.put(AJUDA_COMPRIMIR, "Comprimir.txt");
		topicos.put(AJUDA_COORDENADA_PIXEL,"CoordenadasPixels.txt");
		topicos.put(AJUDA_DECOMPOR_CANAIS,"DecomporCanais.txt");
		topicos.put(AJUDA_DESFAZER, "Desfazer.txt");
		topicos.put(AJUDA_ESCALA_CINZA,"EscalaCinza.txt");
		topicos.put(AJUDA_FECHAR, "Fechar.txt");
		topicos.put(AJUDA_FILTRO, "Filtro.txt");
		topicos.put(AJUDA_HISTOGRAMA_CANAL, "HistogramaCanal.txt");
		topicos.put(AJUDA_HISTOGRAMA_COLORIDO, "HistogramaColorido.txt");
		topicos.put(AJUDA_ILUSAO_OPTICA, "IlusaoOtica.txt");
		topicos.put(AJUDA_OP_ARIT_BASICA,"OperacoesAritmeticasBasica.txt");
		topicos.put(AJUDA_OP_ARIT_PESSOAL,"OperacoesAritmeticasPessoal.txt");
		topicos.put(AJUDA_OP_LOGICA,"OperacoesLogicas.txt");
		topicos.put(AJUDA_PERFIL_COLUNA, "PerfilColunaSimples.txt");
		topicos.put(AJUDA_PERFIL_LINHA, "PerfilLinhaSimples.txt");
		topicos.put(AJUDA_PSEUDOCOLORIZACAO,"Pseudo-Colorizacao.txt");
		topicos.put(AJUDA_REDIMENSIONAR,"Redimensionar.txt");
		topicos.put(AJUDA_FOURIER,"Fourier.txt");
		
		
	}

	/**
	 * Método responsável por ler do arquivo as mensagens de ajuda
	 * @param texto arquivo que contem o texto
	 * @return o texto lido do arquivo
	 */
	public String leArquivo(File texto){
		String textoAjuda = "";
		try {
			Scanner sc = new Scanner(texto);
			while(sc.hasNextLine()){
				textoAjuda += sc.nextLine() + "\n";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return textoAjuda;
	}
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER)
			this.dispose();
	}
	
	public void keyReleased(KeyEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
}
