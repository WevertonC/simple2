package simple.ui.menus;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import simple.manipulacoes.imagem.DeteccaoBordas;
import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.modules.fourier.exceptions.FourierException;
import simple.modules.operacoes.ruido.GeradorDeRuido;
import simple.modules.operacoes.ruido.RuidoGaussiano;
import simple.ui.janelas.JanelaFiltro;
import simple.ui.janelas.JanelaFrequencia;
import simple.ui.janelas.JanelaFrequenciaFaixa;
import simple.ui.janelas.JanelaRuidoGaussiano;
import simple.ui.janelas.SImPLe;
import simple.excecoes.ImageProcessingException;

public class MenuOperacoesLocais extends SimpleMenu {

	private static final long serialVersionUID = -1866526303421367909L;
	
	private JMenu locais, filtros,ruidos, espaciais, passaBaixa, passaAlta, frequencia, filtroMorfologico, abertura,
	fechamento, geradorRuido;

	private JMenuItem media,mediana,moda, sobel, prewitt, roberts, laplace, gaussiano, emboss, freichen, passaAltaFreq, 
	passaBaixaFreq, passaFaixaFreq, abertura4, abertura8, fechamento4, fechamento8, rank, morMediana, ruidoSaltPepper,
	ruidoGaussiano;

	private simple.modules.fourier.facade.Facade fourierFacade = new simple.modules.fourier.facade.Facade();

	public MenuOperacoesLocais(SImPLe s){
		super(OP_LOCAIS,s);
		setMnemonic(KeyEvent.VK_L);
		configureMenu();
	}

	private void configureMenu(){

		locais = new JMenu("Locais");
		locais.setMnemonic(KeyEvent.VK_L);
		configureLocais();
		configureFrequencia();
		configureMorfologicas();
		configureGeraRuido();


	}

	private void configureLocais(){
		filtros = new JMenu("Filtros");
		filtros.setMnemonic(KeyEvent.VK_F);
		filtros.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));

		espaciais = new JMenu("Espaciais");
		espaciais.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));

		// ***************************//
		// ****   Passa-baixas   *****//
		// ***************************//

		passaBaixa = new JMenu("Passa-Baixas");
		passaBaixa.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));

		media = configureMenuItem("Média", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_M, "Resource/Icones/filtro.gif");
		mediana = configureMenuItem("Mediana", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_D, "Resource/Icones/filtro.gif");
		moda = configureMenuItem("Moda", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_A, "Resource/Icones/filtro.gif");

		passaBaixa.add(media);
		passaBaixa.add(mediana);
		passaBaixa.add(moda);

		// ***************************//
		// ****   Passa-altas    *****//
		// ***************************//

		passaAlta = new JMenu("Passa-Altas");
		passaAlta.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));

		sobel = configureMenuItem("Sobel", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_O, "Resource/Icones/filtro.gif");
		prewitt = configureMenuItem("Prewitt", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_P,"Resource/Icones/filtro.gif");
		roberts = configureMenuItem("Roberts", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_R,"Resource/Icones/filtro.gif");
		laplace = configureMenuItem("Laplace", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_L,"Resource/Icones/filtro.gif");
		gaussiano = configureMenuItem("Gaussiano", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_G,"Resource/Icones/filtro.gif");
		emboss = configureMenuItem("Emboss", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_E,"Resource/Icones/filtro.gif");
		freichen = configureMenuItem("Frei & Chen", NO_VALUE, NO_VALUE, NO_VALUE, KeyEvent.VK_F,"Resource/Icones/filtro.gif");

		passaAlta.add(sobel);
		passaAlta.add(prewitt);
		passaAlta.add(roberts);
		passaAlta.add(laplace);
		passaAlta.add(gaussiano);
		passaAlta.add(emboss);
		passaAlta.add(freichen);

		espaciais.add(passaBaixa);
		espaciais.add(passaAlta);

		filtros.add(espaciais);
		add(filtros);

		ruidos = new JMenu("Geradores de Ruído");
		ruidos.setMnemonic(KeyEvent.VK_R);
		ruidos.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
	}

	public void media(){

		JanelaFiltro j = new JanelaFiltro("Média");
		MyImage myImage;
		if (j.getMaskara() > 0) {
			try {
				myImage = new MyImage(getSimple().getFacade()
						.mean(getSimple().getImagefromFrame(), j.getMaskara(), j
								.getMaskara()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Média");	
			} catch (ImageProcessingException e) {
			}
		}
	}

	public void mediana(){
		try {
			MyImage myImage = new MyImage(getSimple().getFacade().median(getSimple().getImagefromFrame()));
			BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
			getSimple().buildFrame(img, "  Mediana");	
		} catch (ImageProcessingException e) {
		}

	}

	public void moda(){
		try {
			MyImage myImage = new MyImage(getSimple().getFacade().moda(getSimple().getImagefromFrame()));
			BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
			getSimple().buildFrame(img, "  Moda");	
		} catch (ImageProcessingException e) {
		}
	}

	public void sobel(){
		DeteccaoBordas bordas = new DeteccaoBordas(getSimple().getImagefromFrame(),"sobel");
		MyImage myImage = new MyImage(bordas.getBufferedImage());
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Sobel");	
	}

	public void prewitt(){
		DeteccaoBordas bordas = new DeteccaoBordas(getSimple().getImagefromFrame(),"prewitt");
		MyImage myImage = new MyImage(bordas.getBufferedImage());
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Prewitt");	
	}

	public void roberts(){
		DeteccaoBordas bordas = new DeteccaoBordas(getSimple().getImagefromFrame(),"roberts");
		MyImage myImage = new MyImage(bordas.getBufferedImage());
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Roberts");	
	}

	public void laplace(){
		DeteccaoBordas bordas = new DeteccaoBordas(getSimple().getImagefromFrame(),"laplace");
		MyImage myImage = new MyImage(bordas.getBufferedImage());
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Laplace");	
	}

	public void gaussiano(){
		JanelaFiltro j = new JanelaFiltro("Gaussiano");
		if (j.getMaskara() > 0) {
			MyImage myImage;
			try {
				myImage = new MyImage(getSimple().getFacade().gaussian(getSimple().getImagefromFrame(), j.getMaskara(), j.getMaskara(), 1));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Gaussiano");
			} catch (ImageProcessingException e) {
			}
		}
	}

	public void freiChen(){
		DeteccaoBordas bordas = new DeteccaoBordas(getSimple().getImagefromFrame(),"freichen");
		MyImage myImage = new MyImage(bordas.getBufferedImage());
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, " - Frei & Chen");	
	}

	private void configureFrequencia(){
		frequencia = new JMenu("Frequência");
		frequencia.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));

		passaAltaFreq = configureMenuItem("Passa-Alta", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		passaBaixaFreq = configureMenuItem("Passa-Baixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		passaFaixaFreq = configureMenuItem("Passa-Faixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");

		// adicione ao menu
		frequencia.add(passaAltaFreq);
		frequencia.add(passaBaixaFreq);
		frequencia.add(passaFaixaFreq);

		filtros.add(frequencia);

	}

	public void passaAlta(){
		// Janela contendo o slider
		JanelaFrequencia jf = new JanelaFrequencia();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.passaAltaFreq(getSimple().getImagefromFrame(),jf.getValorRaio()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Passa-Alta no domínio da frequência");	
			} catch (FourierException e) {
			}
		}
	}

	public void passaBaixa(){
		// Janela contendo o slider
		JanelaFrequencia jf = new JanelaFrequencia();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.passaBaixaFreq(getSimple().getImagefromFrame(),jf.getValorRaio()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Passa-Baixa no domínio da frequência");	
			} catch (FourierException e) {
			}
		}
	}

	public void passaFaixa(){
		JanelaFrequenciaFaixa jf = new JanelaFrequenciaFaixa();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.passaFaixaFreq(getSimple().getImagefromFrame(),jf.getRaioExterno(),jf.getRaioInterno()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Passa-Faixa no domínio da frequência");	
			} catch (FourierException e) {
			}
		}
	}

	private void configureMorfologicas(){

		filtroMorfologico = new JMenu("Morfológicos");
		filtroMorfologico.setIcon(new ImageIcon("Resource/Icones/transparente.GIF"));
		filtroMorfologico.setMnemonic(KeyEvent.VK_M);

		abertura = new JMenu("Abertura");

		abertura4 = new JMenuItem("Abertura 4");
		abertura4.addActionListener(getSimple());

		abertura8 = new JMenuItem("Abertuta 8");
		abertura8.addActionListener(getSimple());

		abertura.add(abertura4);
		abertura.add(abertura8);

		fechamento = new JMenu("Fechamento");

		fechamento4 = new JMenuItem("Fechamento 4");
		fechamento4.addActionListener(getSimple());

		fechamento8 = new JMenuItem("Fechamento 8");
		fechamento8.addActionListener(getSimple());

		fechamento.add(fechamento4);
		fechamento.add(fechamento8);

		rank = new JMenuItem("Rank");
		rank.addActionListener(getSimple());
		rank.setEnabled(false);
		morMediana = new JMenuItem("Mediana");
		morMediana.addActionListener(getSimple());

		morMediana.setEnabled(false);

		filtroMorfologico.add(abertura);
		filtroMorfologico.add(fechamento);
		filtroMorfologico.add(rank);
		filtroMorfologico.add(morMediana);
		filtros.add(filtroMorfologico);
	}
	
	public void fechamento4(){
		
		MyImage myImage = new MyImage(getSimple().getFacade().fechamento4(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Fechamento 4");	
	}
	
	public void fechamento8(){
		
		MyImage myImage = new MyImage(getSimple().getFacade().fechamento8(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Fechamento 8");	
	}
	
	public void abertura4(){
		MyImage myImage = new MyImage(getSimple().getFacade().abertura4(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Abertura 4");	
	}
	
	public void abertura8(){
		MyImage myImage = new MyImage(getSimple().getFacade().abertura8(getSimple().getImagefromFrame()));
		BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(img, "  Abertura 8");	
	}
	
	public void configureGeraRuido(){
		
		geradorRuido = new JMenu("Geradores de Ruído");
		geradorRuido.setMnemonic(KeyEvent.VK_R);
		geradorRuido.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		ruidoSaltPepper = new JMenuItem("Salt-and-Pepper");
		ruidoSaltPepper.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		ruidoSaltPepper.addActionListener(getSimple());
		
		ruidoGaussiano = new JMenuItem("Gaussiano");
		ruidoGaussiano.setText("Ruído Gaussiano");
		ruidoGaussiano.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		ruidoGaussiano.addActionListener(getSimple());
		
		geradorRuido.add(ruidoSaltPepper);
		geradorRuido.add(ruidoGaussiano);
		add(geradorRuido);
		
	}
	
	public void saltPepper(){
		
		GeradorDeRuido img = new GeradorDeRuido(getSimple().getImagefromFrame());
		MyImage myImage = new MyImage(img.salt_and_pepper());
		
		BufferedImage resultingImage = MyBufferedImage.makeBufferedImage(myImage.getImage());
		getSimple().buildFrame(resultingImage, "  Salt-and-pepper");	
		
	}
	
	public void ruidoGaussiano(){
		
		JanelaRuidoGaussiano j = new JanelaRuidoGaussiano();
		
		if (j.getDesvioPadrao() != 0){
			RuidoGaussiano img = new RuidoGaussiano(getSimple().getImagefromFrame());
			MyImage myImage = new MyImage(img.Gaussian(j.getDesvioPadrao()));
			
			BufferedImage resultingImage = MyBufferedImage.makeBufferedImage(myImage.getImage());
			getSimple().buildFrame(resultingImage, "  Ruído Gaussiano");	
		
		}
			
	}




}

