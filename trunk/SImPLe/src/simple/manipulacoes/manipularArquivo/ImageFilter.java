package simple.manipulacoes.manipularArquivo;
/*
 * ImageFilter
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */


import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * Classe que cria um filtro para salvar arquivos do tipo "BMP, GIF, JPG, JPEG ,PNG" na Interface "Abrir".
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImageFilter extends FileFilter {
    
	final static String jpeg = "jpeg";
    final static String jpg = "jpg";
    final static String gif = "gif";
    final static String png = "png";
    
       
    /**
     * M�todo que verifica se arquivo � da extensão BMP, GIF, JPG, JPEG ou PNG
     * @return True se o arquivo � do tipo BMP, GIF, JPG, JPEG ou PNG  
     */
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (gif.equals(extension) ||
                jpeg.equals(extension) ||
                jpg.equals(extension) || 
                png.equals(extension)){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
    
    /**
     * Descri��o do tipo do Filtro.
     */
    public String getDescription() {
        return "Images (*.jpg, *.gif, *.png)";
    }
}