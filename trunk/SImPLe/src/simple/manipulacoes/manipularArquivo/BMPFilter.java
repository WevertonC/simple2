package simple.manipulacoes.manipularArquivo;
/*
 * BMPFilter
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
 * Classe que cria um filtro para salvar arquivos do tipo "BMP" na Interface "Salvar".
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class BMPFilter extends FileFilter {
    
	final static String bmp = "bmp";
       
    /**
     * M�todo que verifica se arquivo � da extens�o BMP
     * @return True se o arquivo � do tipo BMP e false caso contr�rio 
     */
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (bmp.equals(extension)){
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
        return "bmp";
    }
}
