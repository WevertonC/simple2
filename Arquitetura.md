# Projeto Arquitetural #

O projeto arquitetural para o módulo em construção segue a estrutura _Pipes and Filters_, na qual a imagem é a entrada e de acordo com os módulos utilizados, temos uma nova imagem como saída.

O primeiro passo após a entrada de uma imagem, é a realização de um pré-processamento que permitirá então a transformação da imagem do domínio do espaço para o domínio da freqüência. A passagem da imagem para este domínio se dá através da utilização da Transformada de Fourier, discreta ou rápida.

A imagem no domínio da freqüência pode ser utilizada como saída, mas também é possível a realização de alguma operação ainda neste domínio, a exemplo de filtragem.

O módulo de processamento digital no domínio da freqüência visa a ser parte integrante da Ferramenta Simple.

![http://simple2.googlecode.com/files/projetoArquitetural2.png](http://simple2.googlecode.com/files/projetoArquitetural2.png)