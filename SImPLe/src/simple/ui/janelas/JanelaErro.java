package simple.ui.janelas;

import javax.swing.JOptionPane;

public class JanelaErro extends JOptionPane  {
	
	public static String ERRO = "ERRO!";
	
	public static String JANELA_INVALIDA = "Seleciona uma janela que contenha uma imagem!";
	
	public JanelaErro(String errorMessage){
		super.showMessageDialog(
				null,
				errorMessage,
				ERRO,
				JOptionPane.ERROR_MESSAGE);
	}


}
