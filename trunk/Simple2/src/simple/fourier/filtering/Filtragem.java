package simple.fourier.filtering;

import simple.fourier.core.Complexo;
import simple.fourier.core.FourierImagem;
import simple.fourier.exceptions.FourierException;

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
	public static FourierImagem passaAlta(FourierImagem img, double raio, int dominio) throws FourierException {

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
		
		if (dominio == 1) img.transform();


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
	
	
	public static FourierImagem passaFaixa(FourierImagem img, double raioInterno, double raioExterno) throws FourierException{
		
		if ((raioInterno < 0)||(raioInterno > (double)1)&&
				(raioExterno < 0)||(raioExterno > (double)1)){
			throw new FourierException(FourierException.RAIO_INVALIDO);
		}
		
		FourierImagem f = passaBaixa(img,raioInterno);
		return passaAlta(f, raioExterno,0); // TODO
	}
	

}
