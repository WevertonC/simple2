package manipularArquivo;
/*
 * ImageFileView
 * 
 * @version 1.0
 * 
 * Date: 20/09/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileView;

/**
 * Classe que cria uma forma de vizualizar os arquivos na Janela Abrir.
 * @version 1.0 28/09/05
 * @author Andre Cavalcente Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class ImageFileView extends FileView {
        
    /**
     * Retorna o nome dos Arquivos.
     */
    public String getName(File f) {
        return null; // let the L&F FileView figure this out
    }
    
    /**
     * Retorna a descrição dos Arquivos
     */
    public String getDescription(File f) {
        return null; // let the L&F FileView figure this out
    }
    
    /**
     * Captura a descrição do arquivo
     * @return descrição do arquivo 
     */
    public String getTypeDescription(File f) {
        String extension = getExtension(f);
        String type = null;

        if (extension != null) {
            if (extension.equals("jpeg") ||
                extension.equals("jpg")) {
                type = "JPEG Image";
            } else if (extension.equals("gif")){
                type = "GIF Image";
            } else if (extension.equals("tiff") ||
                       extension.equals("tif")) {
                type = "TIFF Image";
            } else if (extension.equals("png")){
            	type = "PNG Imagem";
            } else if (extension.equals("bmp")){
            	type = "BMP Image";
            }
            	
        }
        return type;
    }
    
   /**
    * Retorna um Ícone do arquivo
    * @return Ícone
    */
    public Icon getIcon(File f) {
        String extension = getExtension(f);
        Icon icon = null;
        if (extension != null) {
            if (extension.equals("jpeg") ||
                extension.equals("jpg")) {
            } else if (extension.equals("gif")) {
            } 
             
        }
        return icon;
    }
    
    /**
     * Método que retorna a extensãoo do arquivo
     * @param f Arquivo
     * @return A extensão do arquivo
     */
    private String getExtension(File f) {

        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
