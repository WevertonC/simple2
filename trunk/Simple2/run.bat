@echo off
set JAVA=java -cp bin\; simple.fourier.facade.Facade

REM Execucao da transformada de fourier na imagem

%JAVA% -fft .\images\Figura1.jpg
%JAVA% -fft .\images\block.jpg
%JAVA% -fft .\images\block2.jpg
%JAVA% -fft .\images\block3.jpg
%JAVA% -fft .\images\block4.jpg
%JAVA% -fft .\images\block5.bmp
%JAVA% -fft .\images\lena.bmp

REM Execucao do filtro passa-alta

%JAVA% -passaAlta .\images\Figura1.jpg
%JAVA% -passaAlta .\images\block.jpg
%JAVA% -passaAlta .\images\block2.jpg
%JAVA% -passaAlta .\images\block3.jpg
%JAVA% -passaAlta .\images\block4.jpg
%JAVA% -passaAlta .\images\block5.bmp
%JAVA% -passaAlta .\images\lena.bmp

REM Execucao do filtro passa-baixa

%JAVA% -passaBaixa .\images\Figura1.jpg
%JAVA% -passaBaixa .\images\block.jpg
%JAVA% -passaBaixa .\images\block2.jpg
%JAVA% -passaBaixa .\images\block3.jpg
%JAVA% -passaBaixa .\images\block4.jpg
%JAVA% -passaBaixa .\images\block5.bmp
%JAVA% -passaBaixa .\images\lena.bmp

REM Execucao do filtro passa-faixa

%JAVA% -passaFaixa .\images\Figura1.jpg
%JAVA% -passaFaixa .\images\block.jpg
%JAVA% -passaFaixa .\images\block2.jpg
%JAVA% -passaFaixa .\images\block3.jpg
%JAVA% -passaFaixa .\images\block4.jpg
%JAVA% -passaFaixa .\images\block5.bmp
%JAVA% -passaFaixa .\images\lena.bmp


