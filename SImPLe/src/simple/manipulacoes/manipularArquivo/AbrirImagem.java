package simple.manipulacoes.manipularArquivo;

/*
 * AbrirImagem
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.Image;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import simple.excecoes.ImagemNaoExisteException;
import simple.excecoes.NomeInvalidoException;

/**
 * Classe destinada a abrir uma imagem.
 * @version 1.0 28/09/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */

public class AbrirImagem {
	
	private static Image imagemAberta; 
	private static String nome;
	private static JFileChooser fc;
	private static ImagePreview preview;
	public static String caminho = ".";
	public static double tamanhoKbytes = 0;
	public static double tamanhoBytes = 0;
	public static String tipo = "";
	
	/**
	 * Metodo que abre o dialog responsavel por abrir uma imagem.
	 * @throws NomeInvalidoException
	 * @throws ImagemNaoExisteException
	 */
	public static void abrir() throws NomeInvalidoException, ImagemNaoExisteException {
		fc = new JFileChooser(new File(caminho));
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setFileView(new ImageFileView());
		preview = new ImagePreview(fc);
		fc.setAccessory(preview);
				
		int returnVal = fc.showDialog(new JFrame(), "Abrir Arquivo");
		
		if (returnVal == JFileChooser.CANCEL_OPTION){
			imagemAberta = null;
			return;
		}		
		File imageFile = fc.getSelectedFile();
		caminho = imageFile.getAbsolutePath();
		SalvarImagem.caminhoImagem = caminho;
		nome = imageFile.getName();
		abrirArquivo(imageFile);
	}
		
	/**
	 * Metodo responsavel por abrir a imagem a partir do arquivo(caminho) dado.
	 * @param imageFile arquivo pelo qual sera aberta ou seja o caminho da imagem
	 * @throws NomeInvalidoException
	 * @throws ImagemNaoExisteException
	 */
	public static void abrirArquivo(File imageFile) throws NomeInvalidoException, ImagemNaoExisteException {
		if (imageFile == null || imageFile.getName().equals("")) 
			throw new NomeInvalidoException("Nome do arquivo inválido");
		if (!imageFile.exists()) {
			throw new ImagemNaoExisteException("A imagem selecionada não existe");
		}
		
		tamanhoBytes = imageFile.length();
		tamanhoKbytes = (double)tamanhoBytes/1024;
		tipo = imageFile.getName().substring(imageFile.getName().indexOf(".")+1,imageFile.getName().length());
						
		imagemAberta = preview.imagem.getImage();
		if(imagemAberta.getWidth(null) == -1)
			JOptionPane.showMessageDialog(null,"Formato Inválido! Selecione arquivos com formatos: GIF, JPG ou PNG.","ERRO NAS INFORMAÇÕES", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Metodo que retorna a imagem aberta na classe
	 * @return a imagem aberta pelo usuario
	 */
	public static Image getImagem() {
		return imagemAberta;
	}
	/**
	 * Metodo que retorna o nome da imagem a ser aberta
	 * @return O nome da imagem
	 */
	public static String getNome(){
		return nome;
	}	
}