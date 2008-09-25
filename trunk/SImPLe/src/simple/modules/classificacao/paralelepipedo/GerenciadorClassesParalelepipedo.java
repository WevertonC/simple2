package simple.modules.classificacao.paralelepipedo;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import simple.manipulacoes.imagem.ModeloCromatico;
import simple.manipulacoes.imagem.RGBImage;


import simple.excecoes.FaixaEmUsoException;


public class GerenciadorClassesParalelepipedo {
	
	private List<ClasseParalelepipedo> classes;
	private BufferedImage imagemAWT;
	private ModeloCromatico modeloCromatico;
	private static GerenciadorClassesParalelepipedo instancia = null;
	
	private GerenciadorClassesParalelepipedo() {
		classes = new ArrayList<ClasseParalelepipedo>();
	}
	
	public static GerenciadorClassesParalelepipedo getInstance() {
		if (instancia == null) {
			instancia = new GerenciadorClassesParalelepipedo();
		}
		return instancia;
	}
	
	public void addClasse(ClasseParalelepipedo classe) throws FaixaEmUsoException{
		if (!limitesEmUso(classe)) {
			classes.add(classe);
		}
	}

	private boolean limitesEmUso(ClasseParalelepipedo classe) throws FaixaEmUsoException {
//		if (classes.size() == 0) {
//			return false;
//		} else {
//			//varre todas as classes e verifica se os limites escolhidos não estão sendo usados
//			for (Iterator iter = classes.iterator(); iter.hasNext();) {
//				ClasseParalelepipedo element = (ClasseParalelepipedo) iter.next();
//				if ((classe.getMinTonCinza() >= element.getMinTonCinza() && classe.getMinTonCinza() < element.getMaxTonCinza()) ||
//					(classe.getMaxTonCinza() >= element.getMinTonCinza() && classe.getMaxTonCinza() < element.getMaxTonCinza()))  {
//					throw new FaixaEmUsoException();
//				}
//			}
			return false;
//		}
	}
	
	public RGBImage classificaImagem() throws IOException {
		RGBImage aimagem = new RGBImage(imagemAWT);
//		for (Iterator iter = classes.iterator(); iter.hasNext();) {
//			ClasseParalelepipedo classe = (ClasseParalelepipedo) iter.next();
			aimagem = ClassificadorParalelepipedo.classificaImagem(modeloCromatico, aimagem, classes);
//		}
		return aimagem;
	}

	public List<ClasseParalelepipedo> getClasses() {
		return classes;
	}

	public int getNumeroClasses() {
		return classes.size();
	}
	

	public ModeloCromatico getModeloCromatico() {
		return modeloCromatico;
	}

	public void setModeloCromatico(ModeloCromatico modeloCromatico) {
		this.modeloCromatico = modeloCromatico;
	}	
	
	public void zeraClasses() {
		classes = new ArrayList<ClasseParalelepipedo>();
	}
	
	public void setImagem(BufferedImage imagem) {
		imagemAWT = imagem;
	}
	
	public BufferedImage getImagem() {
		return imagemAWT;
	}	

}
