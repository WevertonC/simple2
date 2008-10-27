package simple.tests;

import java.util.HashMap;

public class TestModa {
	
	private static int maiorOcorrencia(int valores[]){
		HashMap<Integer, Integer> ocorrencia = new HashMap<Integer, Integer>();
		for (int i = 0; i < valores.length; i++) {
			if(ocorrencia.containsKey(valores[i])){
				int valor = ocorrencia.get(valores[i])+1;
				ocorrencia.put(valores[i], valor);
			} 
			else {
				ocorrencia.put(valores[i], 1);
			}
		}
		System.out.println(ocorrencia);
		
		int maior = ocorrencia.get(valores[0]);
		int chave = 0;
		for (int i = 1; i < valores.length; i++) {
			if(ocorrencia.get(valores[i]) > maior){
				chave = i;
				maior = ocorrencia.get(valores[i]);
			}
		}
		return valores[chave];
		
	}
	
	public static void main(String[] args) {
		int[] valores = new int[8];
		valores[0] = 1;
		valores[1] = 1;
		valores[2] = 3;
		valores[3] = 3;
		valores[4] = 4;
		valores[5] = 4;
		valores[6] = 4;
		valores[7] = 4;
		System.out.println(maiorOcorrencia(valores));
		
	}
}
