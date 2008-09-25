/*
 * Classe MYJInternalFrame
 * 
 * version 1.0
 * 
 * Data 09/11/2005
 * 
 * CopyRight FePDI all rigths reserved 
 */ 

package simple.manipulacoes.util;



import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.RenderedImage;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import simple.ui.janelas.JanelaFechar;
import simple.ui.janelas.JanelaSair;
import simple.ui.janelas.SImPLe;

import simple.manipulacoes.manipularArquivo.SalvarImagem;
import simple.facade.Facade;


/**
 * Classe que mostra um MYJInternalFrame
 * 
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class MyJInternalFrame extends JInternalFrame implements InternalFrameListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	private MyScrollPane scrollPane;
	private MyImage myImage;
	private String name;
	private Dimension dimensao, dimensaoMaxima;
	private boolean foiModificado;
	private Facade facade;
	private SImPLe fepdi;
	private boolean salvou = false;
	private boolean foiOperacao = false;
	private String caminhoImagem;
	private boolean abriu = true;
	private MyPopUp popup;
	
	
	/**
	 * Construtor da classe
	 * @param facade
	 * @param name
	 * @param resizeable
	 * @param closeable
	 * @param maximizable
	 * @param iconifiable
	 * @param imagem
	 */
	public MyJInternalFrame(SImPLe fepdi, Facade facade, String name, MyImage imagem){
		this.fepdi = fepdi;
		this.facade = facade;
		this.name = name;
		this.setFrameIcon(new ImageIcon("Resource/Icones/simpleIcon.jpg"));
		myImage = imagem;
		foiModificado = false;
		this.setTitle(name + " (" + imagem.getdadosDaImagem() + ") - "+imagem.getLargura() + " x " + imagem.getAltura());
		this.setResizable(true);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setIconifiable(true);
		caminhoImagem = "";
		dimensao = new Dimension(imagem.getLargura()+11,imagem.getAltura()+36);
		dimensaoMaxima = new Dimension(800+11,600+36);
		popup = new MyPopUp(fepdi,this);
		MyPopUpListener popupListener = new MyPopUpListener(popup);
		
		
		NumberFormat formato = NumberFormat.getNumberInstance(); // obtém um formatador para x
		String tamanhoBytes = formato.format(facade.tamanhoBytes); // formata o valor de x através do método format(x)
		String tamanhoKbytes = formato.format(formatNumber(facade.tamanhoKbytes));
				
		scrollPane = new MyScrollPane(imagem.getRenderedImage(),popupListener);
		scrollPane.setToolTipText("Nome: " +name+ " / " + "Tipo: "+ facade.tipo.toUpperCase() + " / " + "Tamanho: "+ tamanhoKbytes 
				+ " KB ("+tamanhoBytes + " bytes)" + " / " + "Dimensão: "+ imagem.getLargura() + " x " + imagem.getAltura() + " pixels");
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		scrollPane.addMouseListener(this);
		scrollPane.setJanelaSelecionada(true);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		if(imagem.getLargura() <= 100 && imagem.getAltura() <= 100)	this.setSize(100+11,100+36);
		else if((imagem.getLargura() <= dimensaoMaxima.width) && (imagem.getAltura() <= dimensaoMaxima.height)){
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.setSize(imagem.getLargura()+11,imagem.getAltura()+36);
		}
		else{ 
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.setSize(dimensaoMaxima.width,dimensaoMaxima.height);
		}
//		else{ 
//			abriu = false;
//			JOptionPane.showMessageDialog(null,"Imagem não pode ser exibida!!! Tamanho Máximo 1024 x 1024","ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
//		}
		
		this.setMaximumSize(dimensaoMaxima);
		this.setPreferredSize(dimensao);
		this.addInternalFrameListener(this);
		this.setVisible(true);			
	}
	
	public MyJInternalFrame(){
		
	}
	
	/**
	 * Metodo que formata o valor do tamanho do arquivo
	 * @param valor O tamanho do arquivo
	 * @return O valor do tamanho com duas casas decimais 
	 */
	private static double formatNumber(double valor) {
		BigDecimal bd;
		try {
			bd = new BigDecimal(valor);
		} catch (NumberFormatException e) {
			return valor;
		}
		return (bd.setScale(1,BigDecimal.ROUND_DOWN)).doubleValue();
	}	
	
	public void setCaminhoImagem(String caminho){
		this.caminhoImagem = caminho;
	}
	
	public String getCaminhoImagem(){
		return this.caminhoImagem;
	}
	
	public void setSalvou(boolean salvou){
		this.salvou = salvou; 
	}
	
	public boolean getSalvou(){
		return salvou;
	}
	
	public void setFoiOperacao(boolean foiOperacao){
		this.foiOperacao = foiOperacao; 
	}
	
	public boolean getFoiOperacao(){
		return foiOperacao;
	}
	
	public boolean getFoiModificado(){
		return foiModificado;
	}
	
	public void setFoiModificado(boolean foiModificado){
		this.foiModificado = foiModificado; 
	}
	public boolean getAbriu(){
		return abriu;
	}
	
	/**
	 * Método que modifica a imagem 
	 * @param novaImagem A nova imagem a ser exibida
	 */
	public void setImage(RenderedImage novaImagem){
		try{
			foiModificado = true;
			salvou = false;
			if(novaImagem.getWidth() <= 100 && novaImagem.getHeight() <= 100){	
				this.setSize(100+11,100+36);
				scrollPane.setImage(novaImagem);
			}
			else if((novaImagem.getWidth() <= dimensaoMaxima.width) && (novaImagem.getHeight() <= dimensaoMaxima.height)){
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				this.setSize(novaImagem.getWidth()+11,novaImagem.getHeight()+36);
				scrollPane.setImage(novaImagem);
			}
			else{
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				this.setSize(dimensaoMaxima.width,dimensaoMaxima.height);
				scrollPane.setImage(novaImagem);
			}
			this.setTitle(name + " (" + myImage.getdadosDaImagem() + ") - "+ novaImagem.getWidth() + " x " + novaImagem.getHeight());
//			else if((novaImagem.getWidth() <= 1024) && (novaImagem.getHeight() <= 1024)){ 
//				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//				this.setSize(dimensaoMaxima.width,dimensaoMaxima.height);
//				scrollPane.setImage(novaImagem);	
//			}
//			else{
//				abriu = false;
//				JOptionPane.showMessageDialog(null,"Imagem não pode ser exibida!!! Tamanho Máximo 1024 x 1024","ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
//			}
		} catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}
	}		
	
	/**
	 * Método que modifica a imagem 
	 * @param novaImagem A nova imagem a ser exibida
	 */
	public void setImage(Image novaImagem){
		try{
			foiModificado = true;
			salvou = false;
			//if(novaImagem.getWidth(null) <= 1024 && novaImagem.getHeight(null) <= 1024){
				if(novaImagem.getWidth(null) <= 100 && novaImagem.getHeight(null) <= 100){
					this.setSize(100+11,100+36);
					myImage.setImage(novaImagem);
					scrollPane.setImage(myImage.getRenderedImage());
				}
				else if((novaImagem.getWidth(null) <= dimensaoMaxima.width) && (novaImagem.getHeight(null) <= dimensaoMaxima.height)){
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					this.setSize(novaImagem.getWidth(null)+11,novaImagem.getHeight(null)+36);
					myImage.setImage(novaImagem);
					scrollPane.setImage(myImage.getRenderedImage());
				}
				else{
					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					this.setSize(dimensaoMaxima.width,dimensaoMaxima.height);
					myImage.setImage(novaImagem);
					scrollPane.setImage(myImage.getRenderedImage());
				}
				this.setTitle(name + " (" + myImage.getdadosDaImagem() + ") - "+ novaImagem.getWidth(null) + " x " + novaImagem.getHeight(null));
//				else if((novaImagem.getWidth(null) <= 1024) && (novaImagem.getHeight(null) <= 1024)){ 
//					scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//					this.setSize(dimensaoMaxima.width,dimensaoMaxima.height);
//					myImage.setImage(novaImagem);
//					scrollPane.setImage(myImage.getRenderedImage());
//				}
			//}
//			else{
//				abriu = false;
//				JOptionPane.showMessageDialog(null,"Imagem não pode ser exibida!!! Tamanho Máximo 1024 x 1024","ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
//			}
		} catch (OutOfMemoryError e) {
			JOptionPane.showMessageDialog(null,"Estouro de memoria, por favor reinicie o aplicativo.","ERRO INTERNO",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Recupera uma MyImage
	 * @return Recupera uma MyImage
	 */
	public MyImage getMyImage(){
		return myImage;
	}
	
	/**
	 * Recupera a imagem da vez
	 * @return A imagem da vez
	 */
	public Image getImage(){
		return myImage.getImage();
	}
	
	/**
	 * Recupera o nome do frame
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Método que fecha ao sair do programa
	 */
	public void fecharAoSair(){
		if((foiModificado || foiOperacao) && !salvou){
			JanelaSair j = new JanelaSair("Atenção - " + this.getName(),"Deseja salvar as modificações?");
			String t = j.getAcaoSelecionada();
			if(t.equals("Sim")){
				j.dispose();
				if(caminhoImagem.equals("")){
					int valor = -1;
					try {
						valor = facade.salvarComo(getImage());
					} catch (Exception e) {}
					if(valor != 0){
						this.setSalvou(true);
					}					
				}
				else{
					try {
						facade.salvar(this);
					} catch (Exception e) {}
					facade.decrementaPosicao();
					fepdi.verificaUltimo();
					dispose();
				}
			}else if(t.equals("Não"))j.dispose();
			dispose();
		}
		fepdi.verificaFrames();
		//Chamar o Garbage Collector
		System.gc();
	}
	
	/**
	 * Fecha as janelas abertas
	 */
	public void fechar(){
		if((foiModificado || foiOperacao) && !salvou){
			JanelaFechar j = new JanelaFechar(this.name);
			String t = j.getAcaoSelecionada();
			if(t.equals("Sim")){
				j.dispose();
				if(caminhoImagem.equals("")){
					int valor = -1;
					try {
						valor = facade.salvarComo(getImage());
					} catch (Exception e) {}
					if(valor != 0){
						this.setSalvou(true);
						MyJInternalFrame n = new MyJInternalFrame(fepdi,facade,SalvarImagem.nome,new MyImage(SalvarImagem.caminhoImagem));
						n.setCaminhoImagem(SalvarImagem.caminhoImagem);
						fepdi.getDesktopPane().add(n);
						fepdi.getDesktopPane().setSelectedFrame(n);
						n.setLocation(facade.getPosicao(), facade.getPosicao());
						try {
							n.setSelected(true);
						} catch (PropertyVetoException e) {}
						facade.incrementaPosicao();
					} 				
				}
				else{
					try {
						facade.salvar(this);
					} catch (Exception e) {}
					facade.decrementaPosicao();
					fepdi.verificaUltimo();
					dispose();
				}
			}				
			else if(t.equals("Não")){
				j.dispose();
				this.dispose();
				facade.decrementaPosicao();
				fepdi.verificaFrames();
				fepdi.verificaRefazer();
			}
			else if(t.equals("Cancelar")) j.dispose();
			
		}
		else{
			facade.decrementaPosicao();
			fepdi.verificaUltimo();
			dispose();
		}
		
		JInternalFrame[] frames = fepdi.getDesktopPane().getAllFrames();
		if(frames.length > 0){
			JInternalFrame i = frames[0];
			fepdi.getDesktopPane().setSelectedFrame(i);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		
		fepdi.verificaFrames();
		//Chamar o Garbage Collector
		System.gc();
	}
	
	/**
	 * Método que fecha uma janela
	 */
	public void internalFrameClosing(InternalFrameEvent arg0) {
		if((foiModificado || foiOperacao) && !salvou){
			JanelaFechar j = new JanelaFechar(this.name);
			String t = j.getAcaoSelecionada();
			if(t.equals("Sim")){
				j.dispose();
				if(caminhoImagem.equals("")){
					int valor = -1;
					try {
						valor = facade.salvarComo(getImage());
					} catch (Exception e) {}
					if(valor != 0){
						this.setSalvou(true);
						MyJInternalFrame n = new MyJInternalFrame(fepdi,facade,SalvarImagem.nome,new MyImage(SalvarImagem.caminhoImagem));
						n.setCaminhoImagem(SalvarImagem.caminhoImagem);
						fepdi.getDesktopPane().add(n);
						fepdi.getDesktopPane().setSelectedFrame(n);
						try {
							n.setSelected(true);
						} catch (PropertyVetoException e) {}
						facade.decrementaPosicao();
						n.setLocation(facade.getPosicao(), facade.getPosicao());
						facade.incrementaPosicao();
					}	
					else this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
				else{
					try {
						facade.salvar(this);
					} catch (Exception e) {}
					facade.decrementaPosicao();
					dispose();
				}
				fepdi.verificaFrames();
			}				
			else if(t.equals("Não")){
				j.dispose();
				this.dispose();
				facade.decrementaPosicao();
				fepdi.verificaFrames();
				fepdi.verificaRefazer();
			}
			else if(t.equals("Cancelar")){
				j.dispose();
				this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		}else{
			this.dispose();
			facade.decrementaPosicao();
			fepdi.verificaUltimo();
		}
		
		JInternalFrame[] frames = fepdi.getDesktopPane().getAllFrames();
		if(frames.length > 0){
			JInternalFrame i = frames[0];
			fepdi.getDesktopPane().setSelectedFrame(i);
			try {
				i.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		
		fepdi.verificaFrames();
		
		//Chamar o Garbage Collector
		System.gc();
	}
	
	public MyScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public int getLocalizacaoNaTelaX() {
		return this.getLocationOnScreen().x;
	}
	
	public int getLocalizacaoNaTelaY() {
		return this.getLocationOnScreen().y;
	}	
	
	public void mouseReleased(MouseEvent e) {
		scrollPane.selecionadoTrue();
	}
	
	public void mousePressed(MouseEvent e) {
		scrollPane.selecionadoTrue();
	}	
	
	public void mouseClicked(MouseEvent e) {}	
	public void mouseExited(MouseEvent e) {}	
	public void mouseEntered(MouseEvent e) {}
	
	public void internalFrameOpened(InternalFrameEvent arg0) {}	
	public void internalFrameClosed(InternalFrameEvent arg0) {}	
	public void internalFrameIconified(InternalFrameEvent arg0) {}	
	public void internalFrameDeiconified(InternalFrameEvent arg0) {}	
	public void internalFrameDeactivated(InternalFrameEvent arg0) {}
	
	public void internalFrameActivated(InternalFrameEvent arg0) {
		if(abriu)fepdi.habilitaBotoes(true);
		else fepdi.habilitaBotoes(false);
	}	
}
