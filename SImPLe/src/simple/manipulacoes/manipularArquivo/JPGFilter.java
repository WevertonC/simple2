/*
 * JPGFilter
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */
package simple.manipulacoes.manipularArquivo;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Classe que cria um filtro para salvar arquivos do tipo "JPG" na Interface "Salvar".
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class JPGFilter extends FileFilter {
    
	final static String jpeg = "jpeg";
    final static String jpg = "jpg";
    
       
	/**
     * Método que verifica se arquivo é da extensão JPG
     * @return True se o arquivo é do tipo JPG 
     */
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (jpeg.equals(extension) ||
                jpg.equals(extension)){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
    
    /**
     * Descrição do tipo do Filtro.
     */
    public String getDescription() {
        return "jpg";
    }
}
