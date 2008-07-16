package simple.fourier.filtering;

import simple.fourier.core.Complexo;
import simple.fourier.core.FourierImagem;
import simple.fourier.exceptions.FourierException;

public class PassaAlta {

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

}
