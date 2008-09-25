package simple.manipulacoes.imagem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import simple.ui.janelas.SImPLe;






@SuppressWarnings("serial")
public class TransformacaoGeometrica extends JFrame {
  DisplayPanel displayPanel;

  JComboBox scaleXval, scaleYval, shearXval, shearYval;

  String[] scaleValues = { "0.10", "0.25", "0.50", "0.75", "1.00", "1.25",
      "1.50", "1.75", "2.00" };

  String[] shearValues = { "0.00", "0.25", "0.50", "0.75", "1.00", "-0.25", "-0.50", "-0.75", "-1.00" };

  public TransformacaoGeometrica(Image imagem, final SImPLe fePDI, final String imageName) {
    super();
    setTitle("Transformações Geométricas");
    Container container = getContentPane();

    displayPanel = new DisplayPanel();
    displayPanel.loadImage(imagem);
    container.add(displayPanel);

    JButton okButton = new JButton();
    okButton.setText("OK");
    okButton.addMouseListener(new MouseAdapter(){

		public void mouseReleased(MouseEvent e) {
			fePDI.drawImage(displayPanel.getImage(), "Transfomação geométrica - " + imageName);
			setVisible(false);
			dispose();
		}
    });    
    
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 4, 5, 5));
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    scaleXval = new JComboBox(scaleValues);
    scaleXval.setSelectedItem("1.00");
    scaleXval.addActionListener(new ComboBoxListener());
    scaleYval = new JComboBox(scaleValues);
    scaleYval.setSelectedItem("1.00");
    scaleYval.addActionListener(new ComboBoxListener());

    shearXval = new JComboBox(shearValues);
    shearXval.setSelectedItem("0.00");
    shearXval.addActionListener(new ComboBoxListener());
    shearYval = new JComboBox(shearValues);
    shearYval.setSelectedItem("0.00");
    shearYval.addActionListener(new ComboBoxListener());

    panel.add(new JLabel("Escala do X:"));
    panel.add(scaleXval);
    panel.add(new JLabel("Escala do Y:"));
    panel.add(scaleYval);
    panel.add(new JLabel("Inclinação X:"));
    panel.add(shearXval);
    panel.add(new JLabel("Inclinação Y:"));
    panel.add(shearYval);
    container.add(BorderLayout.NORTH, panel);
    panel.add(okButton);
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 3, 0, 0));
    JLabel l1 = new JLabel();
    l1.setOpaque(true);
    l1.setBackground(Color.white);
    JLabel l2 = new JLabel();
    l2.setOpaque(true);
    l2.setBackground(Color.white);
    panel2.add(l1);
    panel2.add(okButton);
    panel2.add(l2);
    container.add(BorderLayout.SOUTH, panel2);

    setSize(displayPanel.getWidth(), displayPanel.getHeight() + 10);
    setVisible(true);
  }

  class ComboBoxListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JComboBox temp = (JComboBox) e.getSource();

      if (temp == scaleXval) {
        displayPanel.scalex = Double.parseDouble((String) temp
            .getSelectedItem());
        displayPanel.applyValue(true, false);
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (temp == scaleYval) {
        displayPanel.scaley = Double.parseDouble((String) temp
            .getSelectedItem());
        displayPanel.applyValue(true, false);
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (temp == shearXval) {
        displayPanel.shearx = Double.parseDouble((String) temp
            .getSelectedItem());
        displayPanel.applyValue(false, true);
        displayPanel.applyFilter();
        displayPanel.repaint();
      } else if (temp == shearYval) {
        displayPanel.sheary = Double.parseDouble((String) temp
            .getSelectedItem());
        displayPanel.applyValue(false, true);
        displayPanel.applyFilter();
        displayPanel.repaint();
      }
    }
  }
}


