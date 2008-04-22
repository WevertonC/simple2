/* Classe MyDragList
* 
* version 1.0
* 
* Data 09/11/2005
* 
* CopyRight FePDI all rigths reserved 
*/ 

package util;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
* Classe responsável por operar sobre a lista de imagens da janelas de Operações 
* @author Andre Cavalcente Hora
* @author Eduardo Santiago Moura
* @author Paulo de Tarso Firmino Junior
* @author Vinicius de Araujo Porto
* @author Yuska Paola Aguiar
*/
public class MyDragList extends JPanel implements ActionListener, MouseListener{
    
	private static final long serialVersionUID = 1L;
	private JButton esq, dir;
	private JList list1, list2;
	private DefaultListModel list1Model, list2Model;
	private int contaClick = 0;
	private int[] posicaoClickada = {-2,-2};
	private String[] listaClickada = {"nenhuma","nenhuma"};
	
	/**
	 * Construtor da classe
	 * @param lista  A lista dos frames abertos
	 */
    public MyDragList(Object[] lista) {
    	addMouseListener(this);
        list1Model = new DefaultListModel();
        for (int i = 0; i < lista.length; i++) {
        	if(lista[i] instanceof MyJInternalFrame)
        		list1Model.addElement(((MyJInternalFrame) lista[i]).getName());
		}
        
        list1 = new JList(list1Model);
        list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list1.setDragEnabled(true);
        list1.addMouseListener(this);
        
        JScrollPane list1View = new JScrollPane(list1);
        list1View.setPreferredSize(new Dimension(200, 100));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(list1View, BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel1.setBounds(0,0,200,100);
        
        list2Model = new DefaultListModel();
        list2 = new JList(list2Model);
        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list2.setDragEnabled(true);
        list2.addMouseListener(this);
        
        JScrollPane list2View = new JScrollPane(list2);
        list2View.setPreferredSize(new Dimension(200, 100));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(list2View, BorderLayout.CENTER);
        panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel2.setBounds(220,0,200,100);
        
        esq = new JButton();
        esq.setIcon(new ImageIcon("Resource/Icones/left.gif"));
        esq.setActionCommand("esq");
        esq.setBounds(200,60,20,20);
        esq.addActionListener(this);
        
        dir = new JButton();
        dir.setIcon(new ImageIcon("Resource/Icones/right.gif"));
        dir.setActionCommand("dir");
        dir.setBounds(200,20,20,20);
        dir.addActionListener(this);
        
        setLayout(null);
        add(panel1);
        add(panel2);
        add(dir);
        add(esq);
        setSize(420,135);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /**
     * Captura o evento no actionComand
     */
	public void actionPerformed(ActionEvent arg0) {
		contaClick = 0;
		String evento = arg0.getActionCommand();
		if(evento.equals("dir")){
			if(!list1Model.isEmpty())
				if(list1.getSelectedIndex() != -1){
					list2Model.addElement(list1Model.get(list1.getSelectedIndex()));
					list1Model.removeElement(list1Model.get(list1.getSelectedIndex()));
				}			
		}
		else if(evento.equals("esq")){
			if(!list2Model.isEmpty())
				if(list2.getSelectedIndex() != -1){
					list1Model.addElement(list2Model.get(list2.getSelectedIndex()));
					list2Model.removeElement(list2Model.get(list2.getSelectedIndex()));
				}			
		}		
	}
	
	/**
	 * Recupera a lista selecionada pelo usuário
	 * @return A lista selecionada pelo usuário
	 */
	public ArrayList<String> getListaSelecionada(){
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i < list2Model.size(); i++) array.add((String) list2Model.get(i));
		return array;
	}

	/**
	 * Método responsável pela seleção ou não através do duplo click do mouse
	 */
	public void mouseClicked(MouseEvent arg0) {
		if(list1.getSelectedIndex() != -1 && !(list2.getSelectedIndex() != -1)) {
			posicaoClickada[contaClick] = list1.getSelectedIndex();
			listaClickada[contaClick] = "list1";
			contaClick++;			
			if(contaClick == 2){
				if((posicaoClickada[0] == posicaoClickada[1]) && (listaClickada[0] == listaClickada[1])){
					list2Model.addElement(list1Model.get(list1.getSelectedIndex()));
					list1Model.removeElement(list1Model.get(list1.getSelectedIndex()));
				}
				contaClick = 0;
				listaClickada[0] = "nenhuma";
				listaClickada[1] = "nenhuma";
			}
			else if(contaClick > 2){
				contaClick = 0;
				listaClickada[0] = "nenhuma";
				listaClickada[1] = "nenhuma";
			}
		}
		else if(list2.getSelectedIndex() != -1 && !(list1.getSelectedIndex() != -1)) {
			posicaoClickada[contaClick] = list2.getSelectedIndex();	
			listaClickada[contaClick] = "list2";
			contaClick++;
			if(contaClick == 2){
				if((posicaoClickada[0] == posicaoClickada[1]) && (listaClickada[0] == listaClickada[1])){
					list1Model.addElement(list2Model.get(list2.getSelectedIndex()));
					list2Model.removeElement(list2Model.get(list2.getSelectedIndex()));
				}
				contaClick = 0;
				listaClickada[0] = "nenhuma";
				listaClickada[1] = "nenhuma";
			}
			else if(contaClick > 2){
				contaClick = 0;
				listaClickada[0] = "nenhuma";
				listaClickada[1] = "nenhuma";
			}
		}		
	}

	//Método apenas para a implementação das interfaces
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
}