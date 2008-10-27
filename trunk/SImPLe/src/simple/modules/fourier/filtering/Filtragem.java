package simple.modules.fourier.filtering;

import simple.modules.fourier.core.Complexo;
import simple.modules.fourier.core.FourierImagem;
import simple.modules.fourier.exceptions.FourierException;

/**
 * Realiza filtragens no dominio da frequencia.
 * 
 * @author Elloa B. Guedes - elloa@dsc.ufcg.edu.br
 * @author Odilon F. Lima Junior - odilonflj@dsc.ufcg.edu.br
 *
 */
public class Filtragem {

	/**
	 * Realiza a filtragem passa alta ideal em uma imagem espectral (no dominio da frequencia).
	 * @param img imagem no dominio espectral
	 * @param raio raio do filtro
	 * @param dominio o domínio de retorno. 1 = espaço; 0 = freqüência
	 * @return FourierImagem filtrada
	 * @throws FourierException se a imagem não estiver no dominio da frequencia ou 
	 * 						se o raio do filtro for invalido.
	 */
	public static FourierImagem passaAlta(FourierImagem img, double raio) throws FourierException {

		if ((raio < 0)||(raio > (double)1)){
			throw new FourierException(FourierException.RAIO_INVALIDO);
		}

		if (!img.isEspectral()) {
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);
		}

		int meiaLarg = img.getLargura()/2;
		int meiaAlt = img.getAltura()/2;


		int su, sv, i = 0;
		double r, rmax = Math.min(meiaLarg, meiaAlt);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, meiaAlt) - meiaAlt;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, meiaLarg) - meiaLarg;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				if (r < raio) {
					Complexo c = new Complexo(img.getData(i));
					c.real = c.im = (double)0;
					img.setData(c, i);
				}


			}
		}

		return img;
	}

	/**
	 * Realiza a filtragem passa baixa em uma imagem espectral (no dominio da frequencia).
	 * @param img imagem no dominio espectral
	 * @param raio raio do filtro
	 * @return FourierImagem filtrada
	 * @throws FourierException se a imagem não estiver no dominio da frequencia ou 
	 * 						se o raio do filtro for invalido.
	 */
	public static FourierImagem passaBaixa(FourierImagem img, double raio) throws FourierException {

		if ((raio < 0)||(raio > (double)1)){
			throw new FourierException(FourierException.RAIO_INVALIDO);
		}

		if (!img.isEspectral()) {
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);
		}

		int meiaLarg = img.getLargura()/2;
		int meiaAlt = img.getAltura()/2;


		int su, sv, i = 0;
		double r, rmax = Math.min(meiaLarg, meiaAlt);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, meiaAlt) - meiaAlt;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, meiaLarg) - meiaLarg;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				if (r > raio) {
					Complexo c = new Complexo(img.getData(i));
					c.real = c.im = (double)0;
					img.setData(c, i);
				}


			}
		}

		return img;

	}

	/**
	 * Realiza a filtragem passa faixa em uma imagem espectral (no dominio da frequencia).
	 * 
	 * @param img imagem no dominio espectral
	 * @param raioInterno raio interno do filtro
	 * @param raioExterno raio externo do filtro
	 * @return FourierImagem filtrada
	 * @throws FourierException se a imagem não estiver no dominio da frequencia ou 
	 * 						se o raio do filtro for invalido.
	 */
	public static FourierImagem passaFaixa(FourierImagem img, double raioInterno, double raioExterno) throws FourierException{

		if ((raioInterno < 0)||(raioInterno > (double)1)&&
				(raioExterno < 0)||(raioExterno > (double)1)){
			throw new FourierException(FourierException.RAIO_INVALIDO);
		}

		FourierImagem f = passaBaixa(img,raioInterno);
		return passaAlta(f, raioExterno);
	}

	/**
	 * Realiza a filtragem rejeita faixa em uma imagem espectral (no dominio da frequencia).
	 * 
	 * @param img imagem no dominio espectral
	 * @param raioInterno raio interno do filtro
	 * @param raioExterno raio externo do filtro
	 * @return FourierImagem filtrada
	 * @throws FourierException se a imagem não estiver no dominio da frequencia ou 
	 * 						se o raio do filtro for invalido.
	 */
	public static FourierImagem rejeitaFaixa(FourierImagem img, double raioInterno, double raioExterno) throws FourierException{

		if (!img.isEspectral())
			throw new FourierException(FourierException.DOMINIO_FREQUENCIA);

		double r1 = raioExterno;
		double r2 = raioInterno;
		if (r1 < 0.0 || r2 > 1.0)
			throw new FourierException(FourierException.RAIO_INVALIDO);

		int u2 = img.getLargura()/2;
		int v2 = img.getAltura()/2;
		int su, sv, i = 0;
		double r, rmax = Math.min(u2, v2);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, v2) - v2;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, u2) - u2;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				if (r >= r1 && r <= r2){
					Complexo c = new Complexo(img.getData(i));
					c.real = c.im = (double)0;
					img.setData(c, i);
				}
			}
		}
		
		return img;

	}

	/**
	 * Computa a função transferência para um filtro Butterworth passa baixa.
	 * @param n ordem do filtro
	 * @param raio raio do filtro (frequência de corte)
	 * @param r distância para o centro do espectro (domínio da frequência)
	 * @return valor da função transferência.
	 */
	public static final double funcaoButterworthPassaBaixa(int n, double raio, double r) {
		double p = Math.pow(r/raio, 2.0*n);
		return 1.0/(1.0 + p);
	}

	/**
	 * Computa a função transferência para um filtro Butterworth passa alta.
	 * @param n ordem do filtro
	 * @param raio raio do filtro (frequência de corte)
	 * @param r distância para o centro do espectro (domínio da frequência)
	 * @return valor da função transferência.
	 */
	public static final double funcaoButterworthPassaAlta(int n, double raio, double r) {
		try {
			double p = Math.pow(raio/r, 2.0*n);
			return 1.0/(1.0 + p);
		}
		catch (ArithmeticException e) {
			return 0.0;
		}
	}

	/**
	 * Computa a função transferência para um filtro Gaussiano passa baixa (Suavização Gaussiana - Gaussian Smoothing).
	 * @param raio raio do filtro (frequência de corte)
	 * @param r distância para o centro do espectro (domínio da frequência)
	 * @return valor da função transferência.
	 */
	public static final double funcaoGaussianaPassaBaixa(double raio, double r) {
		return Math.exp( -(r*r)/(2.0*(raio*raio)) );
	}

	/**
	 * Computa a função transferência para um filtro Gaussiano passa alta (Aguçamento Gaussiano - Gaussian Sharpening).
	 * @param raio raio do filtro (frequência de corte)
	 * @param r distância para o centro do espectro (domínio da frequência)
	 * @return valor da função transferência.
	 */
	public static final double funcaoGaussianaPassaAlta(double raio, double r) {
		try {
			double exp = Math.exp( -(r*r)/(2.0*raio*raio) );
			return 1.0 - exp;
		}
		catch (ArithmeticException e) {
			return 0.0;
		}
	}

	/**
	 * Realiza filtragem Butterworth passa baixa em uma imagem espectral (no dominio da frequência).
	 * @param img imagem no domínio da frequência
	 * @param n ordem do filtro
	 * @param raio raio do filtro (frequência de corte)
	 * @return FourierImagem imagem filtrada com o filtro Butterworth Passa-Baixa
	 * @throws FourierException se os parâmetros do filtro são inválidos.
	 */
	public static FourierImagem filtroButterworthPassaBaixa(FourierImagem img, int n, double raio)
	throws FourierException {

		if (n < 1 || raio <= 0.0 || raio > 1.0){
			throw new FourierException(FourierException.RAIO_OU_ORDEM_INVALIDO);
		}

		int meiaLarg = img.getLargura()/2;
		int meiaAlt = img.getAltura()/2;
		int su, sv, i = 0;
		double mag, r, rmax = Math.min(meiaLarg, meiaAlt);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, meiaAlt) - meiaAlt;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, meiaLarg) - meiaLarg;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				mag = funcaoButterworthPassaBaixa(n, raio, r)*img.getData(i).getMagnitude();
				img.getData(i).setPolar(mag, img.getData(i).getFase());	        
			}
		}

		return img;

	}

	/**
	 * Realiza filtragem Butterworth passa alta em uma imagem espectral (no dominio da frequência).
	 * @param img imagem no domínio da frequência
	 * @param n ordem do filtro
	 * @param raio raio do filtro (frequência de corte)
	 * @return FourierImagem imagem filtrada com o filtro Butterworth Passa-Alta
	 * @throws FourierException se os parâmetros do filtro são inválidos.
	 */
	public static FourierImagem filtroButterworthPassaAlta(FourierImagem img, int n, double raio)
	throws FourierException {

		if (n < 1 || raio <= 0.0 || raio > 1.0){
			throw new FourierException(FourierException.RAIO_OU_ORDEM_INVALIDO);
		}

		int meiaLarg = img.getLargura()/2;
		int meiaAlt = img.getAltura()/2;
		int su, sv, i = 0;
		double mag, r, rmax = Math.min(meiaLarg, meiaAlt);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, meiaAlt) - meiaAlt;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, meiaLarg) - meiaLarg;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				mag = funcaoButterworthPassaAlta(n, raio, r)*img.getData(i).getMagnitude();
				img.getData(i).setPolar(mag, img.getData(i).getFase());
			}
		}

		return img;

	}

	/**
	 * Realiza filtragem Gaussiana passa baixa em uma imagem espectral (no dominio da frequência).
	 * @param img imagem no domínio da frequência
	 * @param n ordem do filtro
	 * @param raio raio do filtro (frequência de corte)
	 * @return FourierImagem imagem filtrada com o filtro Gaussiano Passa-Baixa
	 * @throws FourierException se os parâmetros do filtro são inválidos.
	 */
	public static FourierImagem filtroGaussianoPassaBaixa(FourierImagem img, double raio)
	throws FourierException {

		if (raio <= 0.0 || raio > 1.0){
			throw new FourierException(FourierException.RAIO_INVALIDO);
		}

		int meiaLarg = img.getLargura()/2;
		int meiaAlt = img.getAltura()/2;
		int su, sv, i = 0;
		double mag, r, rmax = Math.min(meiaLarg, meiaAlt);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, meiaAlt) - meiaAlt;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, meiaLarg) - meiaLarg;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				mag = funcaoGaussianaPassaBaixa(raio, r)*img.getData(i).getMagnitude();
				img.getData(i).setPolar(mag, img.getData(i).getFase());	        
			}
		}

		return img;

	}

	/**
	 * Realiza filtragem Gaussiana passa alta em uma imagem espectral (no dominio da frequência).
	 * @param img imagem no domínio da frequência
	 * @param n ordem do filtro
	 * @param raio raio do filtro (frequência de corte)
	 * @return FourierImagem imagem filtrada com o filtro Butterworth Passa-Alta
	 * @throws FourierException se os parâmetros do filtro são inválidos.
	 */
	public static FourierImagem filtroGaussianoPassaAlta(FourierImagem img, double raio)
	throws FourierException {

		if (raio <= 0.0 || raio > 1.0){
			throw new FourierException(FourierException.RAIO_INVALIDO);
		}

		int meiaLarg = img.getLargura()/2;
		int meiaAlt = img.getAltura()/2;
		int su, sv, i = 0;
		double mag, r, rmax = Math.min(meiaLarg, meiaAlt);

		for (int v = 0; v < img.getAltura(); ++v) {
			sv = FourierImagem.shift(v, meiaAlt) - meiaAlt;
			for (int u = 0; u < img.getLargura(); ++u, ++i) {
				su = FourierImagem.shift(u, meiaLarg) - meiaLarg;
				r = Math.sqrt(su*su + sv*sv) / rmax;
				mag = funcaoGaussianaPassaAlta(raio, r)*img.getData(i).getMagnitude();
				img.getData(i).setPolar(mag, img.getData(i).getFase());
			}
		}

		return img;

	}

}
