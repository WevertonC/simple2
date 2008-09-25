package simple.manipulacoes.manipularArquivo;
/*
 * GIFFilter
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
 * Classe que cria um filtro para salvar arquivos do tipo "GIF" na Interface "Salvar". 
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class GIFFilter extends FileFilter {
    
	final static String gif = "gif";
    
       
	/**
     * Método que verifica se arquivo é da extensão GIF
     * @return True se o arquivo é do tipo GIF e false caso contrário 
     */
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (gif.equals(extension)){
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
        return "gif";
    }
}
