package ajuda;
/*
 * Classe JanelaAjuda
 * 
 * version 1.0
 * 
 * Data 29/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */ 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

/**
 * Classe que mostra um MYJInternalFrame
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class JanelaAjuda extends JDialog implements MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JList list1;
	private DefaultListModel list1Model;
	private JTextPane texto;
	private String[] lista = new String[]{"Abrir Novo","Abrir Arquivo","Salvar","Salvar Como",
			"Fechar","Ilusões de Otica", "Sair","Desfazer", "Refazer", "Recortar", "Copiar", "Colar",
			"Histograma Colorido","Histograma por Canal","Perfil de Linha Simples","Perfil de Coluna Simples",
			"Coordenada de Pixel", "Rotacionar", "Redimensionar", "Zoom", "Comprimir","Operações Lógicas",
			"Operações Aritméticas - Básica","Operações Aritméticas - Pessoal","Decompor Canais",
			"Combinar Canais","Escala de Cinza", "Pseudo-Colorização", "Requantização", "Segementação Básica",
			"Segementação Adaptativa","Média","Gaussiano","Sobel Vertical","Sobel Horizontal","Mediana",
			"Prewit","Laplace"};
	
	private String[] caminhos = new String[]{"Abrir.txt","AbrirNovo.txt","Colar.txt",
			"CombinarCanais.txt","Comprimir.txt","CoordenadasPixels.txt","Copiar.txt","DecomporCanais.txt",
			"Desfazer.txt","EscalaCinza.txt","Fechar.txt","Gaussiano.txt","HistogramaColorido.txt",
			"HistogramaCanal.txt","IlusaoOtica.txt","Laplace.txt","Mediana.txt","Média.txt","OperacoesAritmeticasBasica.txt",
			"OperacoesAritmeticasPessoal.txt","OperacoesLogicas.txt","PerfilColunaSimples.txt",
			"PerfilLinhaSimples.txt","Prewitt.txt","Pseudo-Colorizacao.txt","Recortar.txt","Redimensionar.txt","Refazer.txt",
			"Requantizacao.txt","Rotacionar.txt","Sair.txt","Salvar.txt","SalvarComo.txt",
			"Segmentação Adaptativa Básica.txt","Segmentação Global.txt","SobelHorizontal.txt",
			"SobelVertical.txt","Zoom.txt"};
		
	/**
	 * Construtor da Janela Ajuda
	 */
	public JanelaAjuda(){
		setTitle("Ajuda");
		setLayout(null);
		setSize(700,500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - 700/2,d.height/2 - 500/2);
		Arrays.sort(lista);
		
		list1Model = new DefaultListModel();
        for (int i = 0; i < lista.length; i++) list1Model.addElement(lista[i]);
		list1 = new JList(list1Model);
		list1.setBackground(new Color(255,255,220));
        list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list1.setDragEnabled(false);
        list1.addMouseListener(this);
        list1.setForeground(Color.BLUE);
        list1.addKeyListener(this);
        
        JScrollPane list1View = new JScrollPane(list1);
        list1View.setPreferredSize(new Dimension(200, 100));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(list1View, BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel1.setBounds(5,20,220,445);
        
        JLabel topico = new JLabel("Tópicos de Ajuda:");
        topico.setBounds(10,5,100,15);
        
        texto = new JTextPane();
        texto.setBackground(new Color(255,255,220));
        texto.setPreferredSize(new Dimension(250,400));
        texto.setEditable(false);
        texto.addKeyListener(this);
        JScrollPane textoView = new JScrollPane(texto);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(textoView, BorderLayout.CENTER);
        panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel2.setBounds(230,20,455,445);
       
        JLabel descricao = new JLabel("Descrição:");
        descricao.setBounds(235,5,100,15);
        
        add(topico);
        add(panel1);
        add(descricao);
        add(panel2);
        
        setVisible(true);      
	}
	
	/**
	 * Captura o evento do clique do mouse 
	 */
	public void mouseClicked(MouseEvent arg0) {
		setTexto();
	}
	
	private void setTexto() {
		switch(list1.getSelectedIndex()){
		case 0:
			leEscreveTexto(caminhos[0]);
			break;
		case 1:
			leEscreveTexto(caminhos[1]);
			break;
		case 2:
			leEscreveTexto(caminhos[2]);
			break;
		case 3:
			leEscreveTexto(caminhos[3]);
			break;
		case 4:
			leEscreveTexto(caminhos[4]);
			break;
		case 5:
			leEscreveTexto(caminhos[5]);
			break;
		case 6:
			leEscreveTexto(caminhos[6]);
			break;
		case 7:
			leEscreveTexto(caminhos[7]);
			break;
		case 8:
			leEscreveTexto(caminhos[8]);
			break;
		case 9:
			leEscreveTexto(caminhos[9]);
			break;
		case 10:
			leEscreveTexto(caminhos[10]);
			break;			
		case 11:
			leEscreveTexto(caminhos[11]);
			break;
		case 12:
			leEscreveTexto(caminhos[12]);
			break;
		case 13:
			leEscreveTexto(caminhos[13]);
			break;
		case 14:
			leEscreveTexto(caminhos[14]);
			break;
		case 15:
			leEscreveTexto(caminhos[15]);
			break;
		case 16:
			leEscreveTexto(caminhos[16]);
			break;
		case 17:
			leEscreveTexto(caminhos[17]);
			break;
		case 18:
			leEscreveTexto(caminhos[18]);
			break;
		case 19:
			leEscreveTexto(caminhos[19]);
			break;
		case 20:
			leEscreveTexto(caminhos[20]);
			break;			
		case 21:
			leEscreveTexto(caminhos[21]);
			break;
		case 22:
			leEscreveTexto(caminhos[22]);
			break;
		case 23:
			leEscreveTexto(caminhos[23]);
			break;
		case 24:
			leEscreveTexto(caminhos[24]);
			break;
		case 25:
			leEscreveTexto(caminhos[25]);
			break;
		case 26:
			leEscreveTexto(caminhos[26]);
			break;
		case 27:
			leEscreveTexto(caminhos[27]);
			break;
		case 28:
			leEscreveTexto(caminhos[28]);
			break;
		case 29:
			leEscreveTexto(caminhos[29]);
			break;
		case 30:
			leEscreveTexto(caminhos[30]);
			break;			
		case 31:
			leEscreveTexto(caminhos[31]);
			break;
		case 32:
			leEscreveTexto(caminhos[32]);
			break;
		case 33:
			leEscreveTexto(caminhos[33]);
			break;
		case 34:
			leEscreveTexto(caminhos[34]);
			break;
		case 35:
			leEscreveTexto(caminhos[35]);
			break;
		case 36:
			leEscreveTexto(caminhos[36]);
			break;
		case 37:
			leEscreveTexto(caminhos[37]);
			break;
		}
	}
	
	/**
	 * Escreve o texto desejado
	 * @param string O texto a ser escrito
	 */
	private void leEscreveTexto(String string) {
		texto.setText(loadText(string));
	}
	
	/**
	 * Metodo que le o texto do arquivo
	 * @param fileName O nome do arquivo
	 * @return Uma string ocm o texto
	 */
	public String loadText(String fileName){
		Scanner sc = null;
		try {
			sc = new Scanner(new File("Resource/Help/"+fileName));
		} catch (FileNotFoundException e) {}
		String sobre = "";
		while(sc.hasNextLine()) sobre += sc.nextLine() + "\n";
		return sobre.trim();
	}
	
	public void mouseEntered(MouseEvent arg0) {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public void mouseExited(MouseEvent arg0) {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
//	Método apenas para a implementação da interface
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	
	/**
	 * Metodo captura os eventos do teclado para ENETER e ESCAPE
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			this.dispose();
		else if (e.getKeyCode() == KeyEvent.VK_ENTER)
			setTexto();
	}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	public static void main(String[] args) {
		new JanelaAjuda();
	}
}