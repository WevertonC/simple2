package simple.manipulacoes.compressorImagens;
/*
 * Ilusao de Optica (Pontos Pretos)
 * 
 * @version 1.0
 * 
 * Date: 03/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import simple.excecoes.FormatoInvalidoException;


/**
 * Classe que faz a compressão de uma imagem em vários formatos(GIF, JPG, PNG, BMP)
 * @version 1.0 03/11/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class CompressorImagens {
	
	//Constantes que identificam o formato da imagem
	public final static int PNG = 1; 
	public final static int BMP = 2;
	public final static int GIF = 3;
	public final static int JPG = 4;
	
	private String sistemaOperacional;
	private String comando;
	private String tmpFileName; 
	private String outFileName;
	@SuppressWarnings("unused")
	private File tmpFile, outFile;
	private BufferedImage bf;
		
	/**
	 * Construtor de um Compressor de imagens que verifica em qual sistema operacional o
	 * programa se encontra, possui uma string que guarda os comandos que devem ser dados
	 * para o sistema operacional e a mensagem apos a realização da compressao, além de possuir
	 * um arquivo temporário onde se realiza as operações de conversão para caso haja algum
	 * problema mantenha a integridade física do arquivo original.
	 *
	 */
	public CompressorImagens(BufferedImage bf, File outFile){
		 sistemaOperacional = System.getProperty("os.name");
		 comando = "";
		 tmpFileName = "";
		 this.bf = bf;
		 this.outFile = outFile;
	}
	
	/**
	 * Método responsável pela conversão de um imagem para GIF, JPG, BMP ou PNG, de acordo 
	 * com a constatnte utilizada. Esse método usa os algoritmos default do sistema operacional
	 * no qual ele esta rodando.
	 * @param caminho O caminho que deseja-se salvar o arquivo convertido
	 * @param formato O formato da imagem desejada (ver constantes)
	 * @throws FormatoInvalidoException 
	 * @throws InterruptedException 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void converteImagem(String caminho, int formato) throws FormatoInvalidoException, InterruptedException {
		outFileName = caminho; 		
		int dot = outFileName.indexOf(".");
		tmpFile = new File("");
		tmpFileName = tmpFile.getAbsolutePath()+"/Imagens/"+"tmpfile.jpg";
		tmpFile = new File(tmpFileName);
		try {
			ImageIO.write(bf,"jpg",tmpFile);
		} catch (IOException e) {
			throw new FormatoInvalidoException(); 
		}
		if ( dot == -1 ){
			switch ( formato ) {
			case PNG :
				outFileName += ".png";
				break;
			case BMP :
				outFileName += ".bmp";
				break;
			case GIF :
				outFileName += ".gif";
				break;
			case JPG :
				outFileName += ".jpg";
				break;
			default :
				throw new FormatoInvalidoException();
			}
			if(formato == 4){
				if ( sistemaOperacional.equals("Linux")) {								
					comando += "convert -quality "+ 50.0 +" " + tmpFileName + " " + outFileName;
				}else {    	         		
					comando +=  "cmd /c Lib\\convert.exe -quality " + 50.0 + " ";
					comando += "\""+ tmpFileName  +"\"" + " " + "\"" + outFileName + "\"";
				}
			} else {
				if ( sistemaOperacional.equals("Linux")) {
					comando += "convert "+ tmpFileName + " " + outFileName;
				}else {    	         		
					comando +=  "cmd /c Lib\\convert.exe ";
					comando += "\""+ tmpFileName  +"\"" + " " + "\"" + outFileName + "\"";
				}
			}
			Process p;
			try {
				p = Runtime.getRuntime().exec(comando);
				p.waitFor();
				tmpFile = new File(tmpFileName);
				outFile = new File(outFileName);  
				if ( sistemaOperacional.equals("Linux")) Runtime.getRuntime().exec("rm -f "+ tmpFileName );
				else Runtime.getRuntime().exec("del "+ tmpFileName );
			} catch (IOException e) {
				throw new FormatoInvalidoException();
			} catch (InterruptedException e) {
				throw new InterruptedException("Erro Inesperado!! Reinicie o programa!");
			}				
			
		}
	}
	
	/**
	 * Método que converte um arquivo de imagem para o formato PNG, o algoritmo de
	 * conversão utilizado é default do sistema operacional (LZ77) no qual a classe está
	 * rodando.
	 * @param caminho O caminho que deseja-se salvar o arquivo convertido
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void convertePNG(String caminho) throws IOException, InterruptedException{
			
		outFileName = caminho; 		
		int dot = outFileName.indexOf(".");
		tmpFile = new File("");
		tmpFileName = tmpFile.getAbsolutePath()+"/Images/"+"tmpfile.jpg";
		tmpFile = new File(tmpFileName);
		ImageIO.write(bf,"jpg",tmpFile);
		
		if ( dot == -1 )
			outFileName += ".png";
			
		if ( sistemaOperacional.equals("Linux")) {
			comando += "convert "+ tmpFileName + " " + outFileName;
		}else {    	         		
			comando +=  "cmd /c Lib\\convert.exe ";
			comando += "\""+ tmpFileName  +"\"" + " " + "\"" + outFileName + "\"";
		}
		Process p;
		p = Runtime.getRuntime().exec(comando);				
		p.waitFor();
		tmpFile = new File(tmpFileName);
		outFile = new File(outFileName);  

	}
	
	/**
	 * Método que converte um arquivo de imagem para o formato BMP, o algoritmo de
	 * conversão utilizado é default do sistema operacional (RLE) no qual a classe está
	 * rodando.
	 * @param caminho O caminho que deseja-se salvar o arquivo convertido
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void converteBMP(String caminho) throws IOException, InterruptedException{
		
		outFileName = caminho;
		int dot = outFileName.indexOf(".");
		tmpFile = new File("");
		tmpFileName = tmpFile.getAbsolutePath()+"/Images/"+"tmpfile.jpg";
		tmpFile = new File(tmpFileName);
		ImageIO.write(bf,"jpg",tmpFile);
		
		if ( dot == -1 )
			outFileName += ".bmp";
		
		if ( sistemaOperacional.equals("Linux")) {
			comando += "convert "+ tmpFileName + " " + outFileName;
		}else {    	         		
			comando +=  "cmd /c Lib\\convert.exe ";
			comando += "\""+ tmpFileName  +"\"" + " " + "\"" + outFileName + "\"";
		}
		Process p;
		p = Runtime.getRuntime().exec(comando);				
		p.waitFor();
		tmpFile = new File(tmpFileName);
		outFile = new File(outFileName);  
	}
	
	/**
	 * Método que converte um arquivo de imagem para o formato GIF, o algoritmo de
	 * conversao utilizado é default do sistema operacional (LZW) no qual a classe está
	 * rodando.
	 * @param caminho O caminho que deseja-se salvar o arquivo convertido
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void converteGIF(String caminho) throws IOException, InterruptedException{
		outFileName = caminho;
		int dot = outFileName.indexOf(".");
		tmpFile = new File("");
		tmpFileName = tmpFile.getAbsolutePath()+"/Images/"+"tmpfile.jpg";
		tmpFile = new File(tmpFileName);
		ImageIO.write(bf,"jpg",tmpFile);
				
		if ( dot == -1 )
			outFileName += ".gif";
		
		if ( sistemaOperacional.equals("Linux")) {
			comando += "convert "+ tmpFileName + " " + outFileName;
		}else {    	         		
			comando +=  "cmd /c Lib\\convert.exe ";
			comando += "\""+ tmpFileName  +"\"" + " " + "\"" + outFileName + "\"";
		}
		Process p;
		p = Runtime.getRuntime().exec(comando);				
		p.waitFor();
		tmpFile = new File(tmpFileName);
		outFile = new File(outFileName);  
	}
	
	/**
	 * Método que converte um arquivo de imagem para o formato JPG de acordo com a
	 * porcentagem de conversão que o usuario escolher, o algoritmo de conversão 
	 * utilizado é default do sistema operacional (DCT) no qual a classe está rodando.
	 * @param caminho O caminho que deseja-se salvar o arquivo convertido
	 * @param porcentagem Porcentagem de compressão
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void converteJPG(String caminho, double porcentagem) throws IOException, InterruptedException{
		
		outFileName = caminho;
		int dot = outFileName.indexOf(".");
		tmpFile = new File("");
		tmpFileName = tmpFile.getAbsolutePath()+"/Images/"+"tmpfile.jpg";
		tmpFile = new File(tmpFileName);
		ImageIO.write(bf,"jpg",tmpFile);
		
		if ( porcentagem > 0.0 && porcentagem < 100.0  ) {
			
			if ( dot == -1 ) outFileName += ".jpg";        				
			if ( sistemaOperacional.equals("Linux")) {								
				comando += "convert -quality "+ porcentagem +" " + tmpFileName + " " + outFileName;
			}else {    	         		
				comando +=  "cmd /c Lib\\convert.exe -quality " + porcentagem + " ";
				comando += "\""+ tmpFileName  +"\"" + " " + "\"" + outFileName + "\"";
			}
			Process p;
			p = Runtime.getRuntime().exec(comando);				
			p.waitFor();
			tmpFile = new File(tmpFileName);
			outFile = new File(outFileName);  
		}
	}
	
	/**
	 * Método que pega uma string e verifica qual é o formato que o usuário deseja salvar
	 * @param formato A String a ser reconhecida
	 * @return O inteiro correpondente ao formato
	 */
	public int reconheceFormato(String formato){
		if(formato.equalsIgnoreCase("PNG")){
			return PNG;
		} else if(formato.equalsIgnoreCase("BMP")){
			return BMP;
		} else if(formato.equalsIgnoreCase("GIF")){
			return GIF;
		} else if(formato.equalsIgnoreCase("JPG")){
			return JPG;
		}
		return 0;
	}
	
	/**
	 * Compara se duas imagens sao iguais, verificando se cada pixel é igual
	 * @param imagem1 A primeira imagem
	 * @param imagem2 A segunda imagem
	 * @return True se forem iguais e falso se forem diferentes
	 */
	public boolean imageEquals(ImageIcon imagem1, ImageIcon imagem2){
		int[] pixelsMeu = new int[imagem1.getIconWidth()*imagem1.getIconHeight()];
		int[] pixelsOutro = new int[imagem2.getIconWidth()*imagem2.getIconHeight()];
		PixelGrabber pg1 = new PixelGrabber(imagem1.getImage(),0,0,imagem1.getIconWidth(),imagem1.getIconHeight(),pixelsMeu,0,imagem1.getIconWidth());
		PixelGrabber pg2 = new PixelGrabber(imagem2.getImage(),0,0,imagem2.getIconWidth(),imagem2.getIconHeight(),pixelsOutro,0,imagem2.getIconWidth());
		try {
			pg1.grabPixels();
			pg2.grabPixels();
		} catch (InterruptedException e) {}
	
		for (int i = 0; i < imagem1.getIconWidth()*imagem1.getIconHeight(); i++) 
			if(!(pixelsMeu[i] == pixelsOutro[i])) return false;
		return true;
	}
}	
