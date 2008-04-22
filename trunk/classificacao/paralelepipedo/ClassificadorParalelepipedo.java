package paralelepipedo;

import imagem.IntensityImage;
import imagem.ModeloCromatico;
import imagem.Monocromatico;
import imagem.RGB;
import imagem.RGBImage;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;




public class ClassificadorParalelepipedo {
	
	private static RGBImage imagemOriginal;
	
	public static RGBImage classificaImagem(ModeloCromatico _modeloCromatico, RGBImage _imagem,
			                           List<ClasseParalelepipedo> classes) throws IOException {
		
		if (_modeloCromatico instanceof RGB) {
			imagemOriginal = new RGBImage(_imagem);
			return classificaImagemRGB(imagemOriginal, (RGB)_modeloCromatico, classes);
		} else if (_modeloCromatico instanceof Monocromatico) {
			IntensityImage imagemMonocromatica = new IntensityImage(_imagem);
			imagemOriginal = new RGBImage(imagemMonocromatica);
			return classificaImagemMonocromatica(imagemOriginal, classes);
		}
		return imagemOriginal;
	}
	
	@SuppressWarnings("unchecked")
	private static RGBImage classificaImagemMonocromatica(RGBImage imagemOriginal, List<ClasseParalelepipedo> classes) {
		IntensityImage imagemMonocromatica = new IntensityImage(imagemOriginal);
		imagemOriginal = new RGBImage(imagemMonocromatica);
		
		//recuperando o número de linhas e colunas da matriz da imagem
		int rows = imagemOriginal.getHeight();
		int cols = imagemOriginal.getWidth();
		
		
		//recuperando as matrizes red, green e blue da imagem
		short[][] red = imagemOriginal.getRed();
		short[][] green = imagemOriginal.getGreen();
		short[][] blue = imagemOriginal.getBlue();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				boolean imagemClassificada = false;
				for (Iterator iter = classes.iterator(); iter.hasNext();) {
					ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
					if (!imagemClassificada) {
						if (red[row][col]      >= classe.getMinTonCinza() && red[row][col]   < classe.getMaxTonCinza()
								&& green[row][col] >= classe.getMinTonCinza() && green[row][col] < classe.getMaxTonCinza()
								&& blue[row][col]  >= classe.getMinTonCinza() && blue[row][col]  < classe.getMaxTonCinza()) 
						{
							imagemClassificada = true;
							red[row][col]   = (short)classe.getCor().getCorR();
							green[row][col] = (short)classe.getCor().getCorG();
							blue[row][col]  = (short)classe.getCor().getCorB();
						}
					}
				}
				if (!imagemClassificada) {
					red[row][col]   = 0;
					green[row][col] = 0;
					blue[row][col]  = 0;
				}
			}
		}
		imagemMonocromatica = new IntensityImage(imagemOriginal);
		imagemOriginal = new RGBImage(imagemMonocromatica);
		return imagemOriginal;		
	}

	@SuppressWarnings("unchecked")
	private static RGBImage classificaImagemRGB(RGBImage imagemOriginal2, RGB modeloRGB, List<ClasseParalelepipedo> classes) {
		//recuperando o número de linhas e colunas da matriz da imagem
		int rows = imagemOriginal.getHeight();
		int cols = imagemOriginal.getWidth();
		
		//recuperando as matrizes red, green e blue da imagem
		short[][] red = imagemOriginal.getRed();
		short[][] green = imagemOriginal.getGreen();
		short[][] blue = imagemOriginal.getBlue();
		
		
		
		switch (modeloRGB.getCombinacaoDePlanosCromaticos()) {
		case RGB.RGB: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaR() && red[row][col]   < classe.getMaxTonCinzaR()
									&& green[row][col] >= classe.getMinTonCinzaG() && green[row][col] < classe.getMaxTonCinzaG()
									&& blue[row][col]  >= classe.getMinTonCinzaB() && blue[row][col]  < classe.getMaxTonCinzaB()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}
			return imagemOriginal;
		}	
		case RGB.R: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaR() && red[row][col]   < classe.getMaxTonCinzaR()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}			
			return imagemOriginal;
		}
		case RGB.G: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaG() && red[row][col]   < classe.getMaxTonCinzaG()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}			
			return imagemOriginal;
		}
		case RGB.B: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaB() && red[row][col]   < classe.getMaxTonCinzaB()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}	
			return imagemOriginal;			
		}
		case RGB.RG: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaR() && red[row][col]   < classe.getMaxTonCinzaR()
									&& green[row][col] >= classe.getMinTonCinzaG() && green[row][col] < classe.getMaxTonCinzaG()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}
			return imagemOriginal;			
		}
		case RGB.RB: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaR() && red[row][col]   < classe.getMaxTonCinzaR()
									&& green[row][col] >= classe.getMinTonCinzaB() && green[row][col] < classe.getMaxTonCinzaB()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}
			return imagemOriginal;				
		}
		case RGB.GB: {
			for (int row = 0; row < rows; row++) {
				for (int col = 0; col < cols; col++) {
					boolean imagemClassificada = false;
					for (Iterator iter = classes.iterator(); iter.hasNext();) {
						ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
						if (!imagemClassificada) {
							if (red[row][col]      >= classe.getMinTonCinzaG() && red[row][col]   < classe.getMaxTonCinzaG()
									&& green[row][col] >= classe.getMinTonCinzaB() && green[row][col] < classe.getMaxTonCinzaB()) 
							{
								imagemClassificada = true;
								red[row][col]   = (short)classe.getCor().getCorR();
								green[row][col] = (short)classe.getCor().getCorG();
								blue[row][col]  = (short)classe.getCor().getCorB();
							}
						}
					}
					if (!imagemClassificada) {
						red[row][col]   = 0;
						green[row][col] = 0;
						blue[row][col]  = 0;
					}
				}
			}
			return imagemOriginal;				
		}
		default:
			break;
		}
		return imagemOriginal;
	}

}