package simple.modules.operacoes.radiometricas;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.util.Hashtable;

import javax.media.jai.Histogram;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simple.ui.janelas.SImPLe;





import com.sun.media.jai.widget.DisplayJAI;


/**
 * This class demonstrates the use of the binarize operator. It allows the
 * user to set a threshold interactively but can also calculate it automatically.
 */
@SuppressWarnings("serial")
public class Binarize extends JFrame implements ChangeListener, ActionListener
  {
  private JSlider slider; // a slider to set the threshold
  private PlanarImage image; // the original image.
  private DisplayJAI display; // the display component.
  private PlanarImage thresholdedImage;

 /**
  * The constructor of the class creates the user interface and registers
  * the event listeners.
  * @param filename the file name of the image (we'll use it on the title bar)
  * @param image the PlanarImage to be rendered/binarized
  */
  public Binarize(PlanarImage image2, final SImPLe fePDI, final String imageName)
    {
    super("Binarização Interativa");
    this.image = image2;
    
   
    
    // Convert color images to one-band using weights for the three
    // bands.
    if (image.getNumBands() == 3)
      {
      double[][] matrix = {{ 0.114, 0.587, 0.299, 0 }};
      ParameterBlock pb = new ParameterBlock();
      pb.addSource(image);
      pb.add(matrix);
      image = JAI.create("bandcombine", pb, null);
      }
    // Set the content pane's layout
    getContentPane().setLayout(new BorderLayout());
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((d.width - image.getWidth())/2,(d.height - image.getHeight())/2);
    /*System.out.println((d.height - image.getHeight())/2);
    System.out.println((d.width - image.getWidth())/2);
    System.out.println("*********");
    System.out.println(d.height/2);
    System.out.println(d.width/2);
    System.out.println("*********");
    System.out.println(image.getHeight()/2);
    System.out.println(image.getWidth()/2);*/
    
    // Create and set the image display component
    display = new DisplayJAI(image);
    getContentPane().add(new JScrollPane(display),BorderLayout.CENTER);
    // Create a small control panel with a checkbox and the slider
    JPanel controlPanel = new JPanel(new BorderLayout());

    // Create the slider, and set its labels using a label table
    slider = new JSlider(0,255,0);
    Hashtable<Integer,JLabel> sliderLabels = new Hashtable<Integer,JLabel>();
    for(int label=0;label<=255;label+=32)
      sliderLabels.put(new Integer(label),new JLabel(""+label));
    sliderLabels.put(new Integer(255),new JLabel("255"));
    slider.setLabelTable(sliderLabels);
    slider.setPaintLabels(true);
    // Registers the change listener for the slider and add it to the
    // control panel
    slider.addChangeListener(this);
    controlPanel.add(slider,BorderLayout.CENTER);
    // Create a combobox with the automatic threshold methods on
    // the Histogram class.
    JButton okButton = new JButton();
    okButton.setText("OK");
    okButton.addMouseListener(new MouseAdapter(){

		public void mouseReleased(MouseEvent e) {
			fePDI.drawImage(getImage(), "Binarização - " + imageName);
			setVisible(false);
			dispose();
		}
    });    
    controlPanel.add(okButton,BorderLayout.EAST);
    // Add the control panel to the frame
    getContentPane().add(controlPanel,BorderLayout.NORTH);
    // Set the closing operation so the application is finished.
    pack(); // adjust the frame size using preferred dimensions.
    setVisible(true); // show the frame.
    }

 protected BufferedImage getImage() {
	return thresholdedImage.getAsBufferedImage();
}

/**
  * This method will be executed when the "automatic" button is 
  * pushed or the threshold selection mode is changed.
  */
  public void actionPerformed(ActionEvent e)
    {
    // We get the threshold using histograms and the minimum fuzziness method.
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(image);
    pb.add(null); // The ROI
    pb.add(1);
    pb.add(1);
    pb.add(new int[]{256});
    pb.add(new double[]{0});
    pb.add(new double[]{256});
    // Calculate the histogram of the image.
    PlanarImage dummyImage = JAI.create("histogram", pb);
    Histogram h = (Histogram)dummyImage.getProperty("histogram");
    // Calculate the thresholds based on the selected method.
    double[] thresholds = null;
    switch(1)
      {
      case  0: // Iterative Bisection
        thresholds = h.getIterativeThreshold(); break;
      case  1: // Maximum Entropy
        thresholds = h.getMaxEntropyThreshold(); break;
      case  2: // Maximum Variance
        thresholds = h.getMaxVarianceThreshold(); break;
      case  3: // Minimum Error
        thresholds = h.getMinErrorThreshold(); break;
      case  4: // Minimum Fuzziness
        thresholds = h.getMinFuzzinessThreshold(); break;
      case  5: // Mode
        thresholds = h.getModeThreshold(1.0); break;
      case  6: // Mode
        thresholds = h.getModeThreshold(0.5); break;
      case  7: // Mode
        thresholds = h.getModeThreshold(0.2); break;
      case  8: // Mode
        thresholds = h.getModeThreshold(0.1); break;
      case  9: // "P-Tile"
        thresholds = h.getPTileThreshold(0.75); break;
      case 10: // "P-Tile"
        thresholds = h.getPTileThreshold(0.50); break;
      case 11: // "P-Tile"
        thresholds = h.getPTileThreshold(0.25); break;
      case 12: // "P-Tile"
        thresholds = h.getPTileThreshold(0.10); break;
      }  
    int threshold = (int)thresholds[0];
    // Change the UI to use the new threshold.
    slider.setValue(threshold);
    binarize(threshold);
    }

 /**
  * This method will be executed when the slider position changes.
  */
  public void stateChanged(ChangeEvent e)
    {
    // If interactivity is off and we're still adjusting, return.
    if (slider.getValueIsAdjusting() && false) return;
    // Gets the threshold value.
    int threshold = slider.getValue();
    // Modify/display the image.
    binarize(threshold);
    }
  
 /*
  * This methods applies the binarization and display the binarized image.
  */
  private void binarize(int threshold)
    {
    // Binarizes the original image.
    ParameterBlock pb = new ParameterBlock();
    pb.addSource(image);
    pb.add(1.0*threshold);
    // Creates a new, thresholded image and uses it on the DisplayJAI component
    thresholdedImage = JAI.create("binarize", pb);
    display.set(thresholdedImage);
    }

  } // end class