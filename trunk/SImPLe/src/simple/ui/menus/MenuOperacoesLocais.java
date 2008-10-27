package simple.ui.menus;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import simple.manipulacoes.imagem.DeteccaoBordas;
import simple.manipulacoes.util.Emboss;
import simple.manipulacoes.util.MyBufferedImage;
import simple.manipulacoes.util.MyImage;
import simple.manipulacoes.util.MyJInternalFrame;
import simple.modules.fourier.exceptions.FourierException;
import simple.modules.operacoes.ruido.GeradorDeRuido;
import simple.modules.operacoes.ruido.RuidoGaussiano;
import simple.ui.janelas.JanelaFiltro;
import simple.ui.janelas.JanelaFrequencia;
import simple.ui.janelas.JanelaFrequenciaFaixa;
import simple.ui.janelas.JanelaRuidoGaussiano;
import simple.ui.janelas.SImPLe;
import simple.excecoes.ImageProcessingException;

/**
 * Constrói o menu que realiza todas as operações locais em uma imagem. Apresenta
 * métodos que contém todas as funcionalidades providas por estes menus.
 */
public class MenuOperacoesLocais extends SimpleMenu {

	private static final long serialVersionUID = -1866526303421367909L;

	private JMenu locais, filtros,ruidos, espaciais, passaBaixa, passaAlta, frequencia, filtroMorfologico, abertura,
	fechamento, geradorRuido,homomorfico, gaussianoFourier, butterworth, filtroIdeal;

	private JMenuItem media,mediana,moda, sobel, prewitt, roberts, laplace, gaussiano, emboss, freichen, passaAltaFreq, 
	passaBaixaFreq, passaFaixaFreq, abertura4, abertura8, fechamento4, fechamento8, rank, morMediana, ruidoSaltPepper,
	ruidoGaussiano, reflectancia, iluminacao, rejeitaFaixaFreq, gaussianoPassaAlta, gaussianoPassaBaixa, butterworthAlta, butterworthBaixa;

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
		
		filtroIdeal = new JMenu("Filtro Ideal");
		filtroIdeal.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		passaAltaFreq = configureMenuItem("Passa-Alta", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		passaBaixaFreq = configureMenuItem("Passa-Baixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		passaFaixaFreq = configureMenuItem("Passa-Faixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		rejeitaFaixaFreq = configureMenuItem("Rejeita-Faixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		
		filtroIdeal.add(passaAltaFreq);
		filtroIdeal.add(passaBaixaFreq);
		filtroIdeal.add(passaFaixaFreq);
		filtroIdeal.add(rejeitaFaixaFreq);
		
		// ---- Homomórfico 
		homomorfico = new JMenu("Filtragem Homomórfica");
		homomorfico.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
				
		reflectancia = configureMenuItem("Reflectância", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		iluminacao = configureMenuItem("Iluminação", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		
		homomorfico.add(reflectancia);
		homomorfico.add(iluminacao);
		
		// ---- Gaussiano
		gaussianoFourier = new JMenu("Filtragem Gaussiana");
		gaussianoFourier.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		gaussianoPassaAlta = configureMenuItem("Gaussiano Passa-Alta", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		gaussianoPassaBaixa = configureMenuItem("Gaussiano Passa-Baixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		
		gaussianoFourier.add(gaussianoPassaAlta);
		gaussianoFourier.add(gaussianoPassaBaixa);
		
		// ---- Butterworth
		
		butterworth = new JMenu("Filtragem Butterworth");
		butterworth.setIcon(new ImageIcon("Resource/Icones/filtro.gif"));
		
		butterworthAlta = configureMenuItem("Butterworth Passa-Alta", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		butterworthBaixa = configureMenuItem("Butterworth Passa-Baixa", NO_VALUE, NO_VALUE, NO_VALUE, NO_VALUE, "Resource/Icones/filtro.gif");
		
		butterworth.add(butterworthAlta);
		butterworth.add(butterworthBaixa);

		// adicione ao menu
		frequencia.add(filtroIdeal);
		frequencia.add(homomorfico);
		frequencia.add(gaussianoFourier);
		frequencia.add(butterworth);

		filtros.add(frequencia);

	}

	public void passaAlta(){
		// Janela contendo o slider
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.passaAltaFreq(src,jf.getValorRaio()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Passa-Alta no domínio da frequência");	
			
				MyImage myImage2 = new MyImage(fourierFacade.passaAltaEsp(src,jf.getValorRaio()));
				BufferedImage img2 = MyBufferedImage.makeBufferedImage(myImage2.getImage());
				getSimple().buildFrame(img2, "  Passa-Alta no domínio do espaço");	
			} catch (FourierException e) {
				JOptionPane
				.showMessageDialog(
						null,
						e.getMessage(),
						"ERRO NAS INFORMAÇÕES",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void passaBaixa(){
		// Janela contendo o slider
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.passaBaixaFreq(src,jf.getValorRaio()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Passa-Baixa no domínio da frequência");	
			
				MyImage myImage2 = new MyImage(fourierFacade.passaBaixaEsp(src,jf.getValorRaio()));
				BufferedImage img2 = MyBufferedImage.makeBufferedImage(myImage2.getImage());
				getSimple().buildFrame(img2, "  Passa-Baixa no domínio do espaço");	
			} catch (FourierException e) {
				JOptionPane
				.showMessageDialog(
						null,
						e.getMessage(),
						"ERRO NAS INFORMAÇÕES",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void passaFaixa(){
		JanelaFrequenciaFaixa jf = new JanelaFrequenciaFaixa();
		BufferedImage src = getSimple().getImagefromFrame();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.passaFaixaFreq(src,jf.getRaioExterno(),jf.getRaioInterno()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Passa-Faixa no domínio da frequência");	
			
				MyImage other = new MyImage(fourierFacade.passaFaixaEsp(src,jf.getRaioExterno(),jf.getRaioInterno()));
				BufferedImage otherIm = MyBufferedImage.makeBufferedImage(other.getImage());
				getSimple().buildFrame(otherIm, "  Passa-Faixa no domínio do espaço");	
			} catch (FourierException e) {
				JOptionPane
				.showMessageDialog(
						null,
						e.getMessage(),
						"ERRO NAS INFORMAÇÕES",
						JOptionPane.ERROR_MESSAGE);
			}

			
		}
	}
	
	public void rejeitaFaixa(){
		JanelaFrequenciaFaixa jf = new JanelaFrequenciaFaixa();
		BufferedImage src = getSimple().getImagefromFrame();

		if (jf.isAplicaFiltro()) {
			try {
				MyImage myImage = new MyImage(fourierFacade.rejeitaFaixaFreq(src,jf.getRaioExterno(),jf.getRaioInterno()));
				BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
				getSimple().buildFrame(img, "  Rejeita-Faixa no domínio da frequência");	
			
				MyImage other = new MyImage(fourierFacade.rejeitaFaixaEsp(src,jf.getRaioExterno(),jf.getRaioInterno()));
				BufferedImage otherIm = MyBufferedImage.makeBufferedImage(other.getImage());
				getSimple().buildFrame(otherIm, "  Rejeita-Faixa no domínio do espaço");	
			} catch (FourierException e) {
				JOptionPane
				.showMessageDialog(
						null,
						e.getMessage(),
						"ERRO NAS INFORMAÇÕES",
						JOptionPane.ERROR_MESSAGE);
			}

			
		}
	}

	public void reflectancia(){
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();

			if (jf.isAplicaFiltro()) {
				try {
					MyImage myImage = new MyImage(fourierFacade.reflectanciaFreq(src,jf.getValorRaio()));
					BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Reflectância no domínio da frequência");
					
					myImage = new MyImage(fourierFacade.reflectanciaEsp(src,jf.getValorRaio()));
					img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Reflectância no domínio do espaço");
				} catch (FourierException e) {
					JOptionPane
					.showMessageDialog(
							null,
							e.getMessage(),
							"ERRO NAS INFORMAÇÕES",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	public void iluminacao(){
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();

			if (jf.isAplicaFiltro()) {
				try {
					MyImage myImage = new MyImage(fourierFacade.iluminacaoFreq(src,jf.getValorRaio()));
					BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Iluminação no domínio da frequência");
					
					myImage = new MyImage(fourierFacade.iluminacaoEsp(src,jf.getValorRaio()));
					img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Iluminação no domínio do espaço");
				} catch (FourierException e) {
					JOptionPane
					.showMessageDialog(
							null,
							e.getMessage(),
							"ERRO NAS INFORMAÇÕES",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	public void gaussianoPassaAlta(){
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();

			if (jf.isAplicaFiltro()) {
				try {
					MyImage myImage = new MyImage(fourierFacade.gaussianoPassaAltaFreq(src,jf.getValorRaio()));
					BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Gaussiano Passa-Alta no domínio da frequência");
					
					myImage = new MyImage(fourierFacade.gaussianoPassaAltaEsp(src,jf.getValorRaio()));
					img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Gaussiano Passa-Alta no domínio do espaço");
				} catch (FourierException e) {
					JOptionPane
					.showMessageDialog(
							null,
							e.getMessage(),
							"ERRO NAS INFORMAÇÕES",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	public void gaussianoPassaBaixa(){
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();

			if (jf.isAplicaFiltro()) {
				try {
					MyImage myImage = new MyImage(fourierFacade.gaussianoPassaBaixaFreq(src,jf.getValorRaio()));
					BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Gaussiano Passa-Baixa no domínio da frequência");
					
					myImage = new MyImage(fourierFacade.gaussianoPassaBaixaEsp(src,jf.getValorRaio()));
					img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Gaussiano Passa-Baixa no domínio do espaço");
				} catch (FourierException e) {
					JOptionPane
					.showMessageDialog(
							null,
							e.getMessage(),
							"ERRO NAS INFORMAÇÕES",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	public void butterworthPassaAlta(){
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();
		int n = 1;

			if (jf.isAplicaFiltro()) {
				try {
					MyImage myImage = new MyImage(fourierFacade.butterworthPassaAltaFreq(src,jf.getValorRaio(),n));
					BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Butterworth Passa-Alta no domínio da frequência");
					
					myImage = new MyImage(fourierFacade.butterworthPassaAltaEsp(src,jf.getValorRaio(),n));
					img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Butterworth Passa-Alta no domínio do espaço");
				} catch (FourierException e) {
					JOptionPane
					.showMessageDialog(
							null,
							e.getMessage(),
							"ERRO NAS INFORMAÇÕES",
							JOptionPane.ERROR_MESSAGE);
				}
			}
	}
	
	public void butterworthPassaBaixa(){
		JanelaFrequencia jf = new JanelaFrequencia();
		BufferedImage src = getSimple().getImagefromFrame();
		int n =1;

			if (jf.isAplicaFiltro()) {
				try {
					MyImage myImage = new MyImage(fourierFacade.butterworthPassaBaixaFreq(src,jf.getValorRaio(),n));
					BufferedImage img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Butterworth Passa-Baixa no domínio da frequência");
					
					myImage = new MyImage(fourierFacade.butterworthPassaBaixaEsp(src,jf.getValorRaio(),n));
					img = MyBufferedImage.makeBufferedImage(myImage.getImage());
					getSimple().buildFrame(img, "  Butterworth Passa-Baixa no domínio do espaço");
				} catch (FourierException e) {
					JOptionPane
					.showMessageDialog(
							null,
							e.getMessage(),
							"ERRO NAS INFORMAÇÕES",
							JOptionPane.ERROR_MESSAGE);
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

	public void emboss() {
		Emboss img = new Emboss(getSimple().getImagefromFrame());

		BufferedImage resultingImage = img.getBufferedImage();
		getSimple().buildFrame(resultingImage, "  Emboss");

	}




}

