package simple.ui.janelas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import simple.modules.operacoes.filtro.FiltroBilateralAdaptativo;
import simple.modules.operacoes.filtro.FiltroBilateralAdaptativo.KernelSize;

/**
 * @author Renato Miceli
 */
public class JanelaFiltroBilateralAdaptativo extends JDialog {

	private static final long serialVersionUID = 6181618010227700672L;

	private static final String OK_LABEL = "OK";

	private static final String CANCEL_LABEL = "Cancelar";

	private final JRadioButton x7;
	private final JRadioButton x9;

	private static class JanelaFiltroBilateralAdaptativoActionListener implements ActionListener {

		private final JanelaFiltroBilateralAdaptativo enclosingWindow;

		private final SImPLe simple;

		public JanelaFiltroBilateralAdaptativoActionListener(JanelaFiltroBilateralAdaptativo enclosingWindow, SImPLe simple) {
			this.enclosingWindow = enclosingWindow;
			this.simple = simple;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event) {

			String eventName = event.getActionCommand();
			if (eventName.equals(OK_LABEL)) {
				BufferedImage bufImg = simple.getImagefromFrame();
				FiltroBilateralAdaptativo img = new FiltroBilateralAdaptativo(bufImg, enclosingWindow.getKernelSize());
				bufImg = img.getBufferedImage();

				simple.buildFrame(bufImg, " Filtro Bilateral Adaptativo "
						+ ((enclosingWindow.getKernelSize() == KernelSize.kernel7x7) ? "7x7" : "9x9"));
			}

			this.enclosingWindow.dispose();
		}
	}

	private static class JanelaFiltroBilateralAdaptativoKeyListener extends KeyAdapter {

		private final JanelaFiltroBilateralAdaptativo enclosingWindow;

		public JanelaFiltroBilateralAdaptativoKeyListener(JanelaFiltroBilateralAdaptativo enclosingWindow) {
			this.enclosingWindow = enclosingWindow;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				this.enclosingWindow.dispose();
			}
		}
	}

	public JanelaFiltroBilateralAdaptativo(SImPLe simple) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dimension.width / 2 - 295 / 2, dimension.height / 2 - 190 / 2);
		this.setSize(295, 190);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Filtro Bilateral Adaptativo");
		this.setLayout(null);
		this.setResizable(false);

		final JLabel pleasingInfoLabel = new JLabel("Informe o tamanho da máscara.");
		pleasingInfoLabel.setBounds(40, 20, 250, 20);

		x7 = new JRadioButton("7x7");
		x7.setBounds(75, 60, 60, 40);
		x7.setSelected(true);

		x9 = new JRadioButton("9x9");
		x9.setBounds(160, 60, 60, 40);

		final ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(x7);
		buttonGroup.add(x9);

		final JButton okButton = new JButton(OK_LABEL);
		okButton.setBounds(60, 120, 57, 25);
		okButton.addActionListener(new JanelaFiltroBilateralAdaptativoActionListener(this, simple));

		final JButton cancelButton = new JButton(CANCEL_LABEL);
		cancelButton.setBounds(130, 120, 85, 25);
		cancelButton.addActionListener(new JanelaFiltroBilateralAdaptativoActionListener(this, simple));

		getContentPane().add(pleasingInfoLabel);
		getContentPane().add(x7);
		getContentPane().add(x9);
		getContentPane().add(okButton);
		getContentPane().add(cancelButton);
		okButton.addKeyListener(new JanelaFiltroBilateralAdaptativoKeyListener(this));
		cancelButton.addKeyListener(new JanelaFiltroBilateralAdaptativoKeyListener(this));
		setVisible(true);
	}

	public KernelSize getKernelSize() {
		if (!x7.isSelected() && !x9.isSelected() || x7.isSelected() && x9.isSelected()) {
			return null;
		}

		if (x7.isSelected()) {
			return KernelSize.kernel7x7;
		} else {
			return KernelSize.kernel9x9;
		}
	}
	
	public static void main(String[] args) {
		new JanelaFiltroBilateralAdaptativo(null);
	}

}
