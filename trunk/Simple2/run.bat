@echo off
set JAVA=java -cp bin\; simple.fourier.facade.Facade

%JAVA% -fft .\images\Figura1.jpg
%JAVA% -fft .\images\block.jpg
%JAVA% -fft .\images\block2.jpg
%JAVA% -fft .\images\block3.jpg
%JAVA% -fft .\images\block4.jpg
%JAVA% -fft .\images\block5.bmp
%JAVA% -fft .\images\lena.bmp

