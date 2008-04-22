package som;

/*
 * Sound
 * 
 * @version 1.0
 * 
 * Date: 11/11/05
 * 
 * Copyright FEDPI all rights reserved
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe base para as funcionalidades sonoras do sistema
 * 
 * @version 1.0 11/11/05
 * @author Andre Cavalcante Hora
 * @author Eduardo Santiago Moura
 * @author Paulo de Tarso Firmino Junior
 * @author Vinicius de Araujo Porto
 * @author Yuska Paola Aguiar
 */
public class Sound  {
     	 
	private static final long serialVersionUID = 1L;
    private static URL codeBase;
    
    /**
     * Método que produz o som quando necessário no programa
     * @param nomeSom O som a ser utilizado
     */
    public static void play(String nomeSom) {
    	URL url = null;
        try {
            codeBase = new URL("file:" + System.getProperty("user.dir") + "/Resource/Sons/");
            url = new URL(codeBase, nomeSom);
        } catch (MalformedURLException e) {}
        AudioClip a = Applet.newAudioClip(url);
        a.play(); 
    }   
}