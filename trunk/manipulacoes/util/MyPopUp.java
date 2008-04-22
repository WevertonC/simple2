package util;

import janelas.SImPLe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;






public class MyPopUp extends JPopupMenu implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	public JMenuItem recortar, copiar, colar,salvar, fechar;
	private SImPLe fepdi;
	
	public MyPopUp(SImPLe fepdi, MyJInternalFrame frame){
		super();
		this.fepdi = fepdi;
		recortar = new JMenuItem("Recortar");
	    recortar.addActionListener(this);
	    recortar.setIcon(new ImageIcon("Resource/Icones/cut.gif"));
	    recortar.setEnabled(false);
	    add(recortar);
	    copiar = new JMenuItem("Copiar");
	    copiar.addActionListener(this);
	    copiar.setIcon(new ImageIcon("Resource/Icones/copy.gif"));
	    copiar.setEnabled(false);
	    add(copiar);
	    colar = new JMenuItem("Colar");
	    colar.addActionListener(this);
	    colar.setIcon(new ImageIcon("Resource/Icones/paste.gif"));
	    colar.setEnabled(false);
	    add(colar);
	    addSeparator();
	    salvar = new JMenuItem("Salvar");
	    salvar.addActionListener(this);
	    salvar.setIcon(new ImageIcon("Resource/Icones/save16.gif"));
	    salvar.setEnabled(false);
	    add(salvar);
	    addSeparator();
	    fechar = new JMenuItem("Fechar");
	    fechar.addActionListener(this);
	    fechar.setIcon(new ImageIcon("Resource/Icones/cancel.gif"));
	    add(fechar);
	    
	}

	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if(evento.equals("Fechar")) fepdi.fechar();
		else if(evento.equals("Salvar")) fepdi.salvar();
		else if(evento.equals("Recortar")) fepdi.recortar();
		else if(evento.equals("Copiar")) fepdi.copiar();
		else if(evento.equals("Colar"))fepdi.colar();
		
	}
}
