package simple.ui.janelas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import simple.modules.operacoes.filtro.FiltroBilateral;

/**
 * @author Renato Miceli
 */
public class JanelaFiltroBilateral extends JDialog {

	private static final String OK_LABEL = "OK";

	private static final String CANCEL_LABEL = "Cancelar";

	private static class JanelaFiltroBilateralActionListener implements ActionListener {

		private final JanelaFiltroBilateral enclosingWindow;

		private final SImPLe simple;

		public JanelaFiltroBilateralActionListener(JanelaFiltroBilateral enclosingWindow, SImPLe simple) {
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
				try {
					if (enclosingWindow.getMaskSize() <= 0) {
						JOptionPane.showMessageDialog(null,
								"O tamanho da máscara não pode ser negativo nem nulo. Por favor, informe um valor inteiro positivo!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						enclosingWindow.resetMaskInputField();
						return;
					}

					if (enclosingWindow.getMaskSize() < 3) {
						JOptionPane
								.showMessageDialog(
										null,
										"O tamanho da máscara deve ser maior que ou igual a três. Por favor, informe um valor inteiro positivo maior que ou igual a três!",
										"Erro", JOptionPane.ERROR_MESSAGE);
						enclosingWindow.resetMaskInputField();
						return;
					}

					if (enclosingWindow.getMaskSize() % 2 != 1) {
						JOptionPane.showMessageDialog(null,
								"O tamanho da máscara deve ser ímpar! Por favor, informe um valor inteiro positivo ímpar!", "Erro",
								JOptionPane.ERROR_MESSAGE);
						enclosingWindow.resetMaskInputField();
						return;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Tamanho de máscara inválido! Por favor, informe um tamanho de máscara inteiro positivo!",
							"Erro", JOptionPane.ERROR_MESSAGE);
					enclosingWindow.resetMaskInputField();
					return;
				}

				try {
					if (enclosingWindow.getSigmaD() <= 0) {
						JOptionPane.showMessageDialog(null, "O valor de Sigma D deve ser real positivo! Por favor, informe um valor real positivo!",
								"Erro", JOptionPane.ERROR_MESSAGE);
						enclosingWindow.resetSigmaDInputField();
						return;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sigma D inválido! Por favor, informe um valor de Sigma D real positivo!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					enclosingWindow.resetSigmaDInputField();
					return;
				}

				try {
					if (enclosingWindow.getSigmaR() <= 0) {
						JOptionPane.showMessageDialog(null, "O valor de Sigma R deve ser real positivo! Por favor, informe um valor real positivo!",
								"Erro", JOptionPane.ERROR_MESSAGE);
						enclosingWindow.resetSigmaRInputField();
						return;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Sigma R inválido! Por favor, informe um valor de Sigma R real positivo!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					enclosingWindow.resetSigmaRInputField();
					return;
				}

				try {
					if (enclosingWindow.getNumberOfIterations() <= 0) {
						JOptionPane
								.showMessageDialog(
										null,
										"O número de iterações do filtro sobre a imagem deve ser inteiro maior que zero! Por favor, informe um valor inteiro positivo!",
										"Erro", JOptionPane.ERROR_MESSAGE);
						enclosingWindow.resetNumberOfIterationsInputField();
						return;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Número de iterações inválido! Por favor, informe um valor do número de iterações inteiro positivo!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					enclosingWindow.resetNumberOfIterationsInputField();
					return;
				}

				BufferedImage bufImg = simple.getImagefromFrame();
				for (int i = 0; i < enclosingWindow.getNumberOfIterations(); i++) {
					FiltroBilateral img = new FiltroBilateral(bufImg, (enclosingWindow.getMaskSize() - 1) / 2, enclosingWindow.getSigmaD(),
							enclosingWindow.getSigmaR());
					bufImg = img.getBufferedImage();
				}

				simple.buildFrame(bufImg, " Filtro Bilateral (" + enclosingWindow.getNumberOfIterations() + " Iterações)");
			}

			this.enclosingWindow.dispose();
		}

	}

	private static class JanelaFiltroBilateralKeyListener extends KeyAdapter {

		private final JanelaFiltroBilateral enclosingWindow;

		public JanelaFiltroBilateralKeyListener(JanelaFiltroBilateral enclosingWindow) {
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

	private static class PositiveRealValueLabel extends JLabel {

		private static final long serialVersionUID = -907286962596461530L;

		public PositiveRealValueLabel() {
			super("(valor real positivo)");
		}

	}

	private static final long serialVersionUID = -1828797687952684629L;

	private final JTextField maskInputField;
	private final JTextField sigmaDInputField;
	private final JTextField sigmaRInputField;
	private final JTextField iterationsInputField;

	public JanelaFiltroBilateral(SImPLe simple) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dimension.width / 2 - 295 / 2, dimension.height / 2 - 315 / 2);
		this.setSize(295, 315);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Filtro Bilateral");
		this.setLayout(null);
		this.setResizable(false);

		final JLabel pleasingInfoLabel = new JLabel("Por favor, informe...");
		pleasingInfoLabel.setBounds(40, 20, 100, 20);

		final JLabel maskLabel = new JLabel("Comprimento da Máscara:");
		maskLabel.setBounds(40, 60, 160, 15);

		final JLabel maskObsLabel = new JLabel("(valor positivo ímpar)");
		maskObsLabel.setBounds(40, 75, 120, 15);

		maskInputField = new JTextField();
		maskInputField.setBounds(200, 60, 35, 20);

		final JLabel sigmaDLabel = new JLabel("Sigma D:");
		sigmaDLabel.setBounds(40, 100, 160, 15);

		final PositiveRealValueLabel sigmaDObsLabel = new PositiveRealValueLabel();
		sigmaDObsLabel.setBounds(40, 115, 120, 15);

		sigmaDInputField = new JTextField();
		sigmaDInputField.setBounds(200, 100, 35, 20);

		final JLabel sigmaRLabel = new JLabel("Sigma R:");
		sigmaRLabel.setBounds(40, 140, 200, 15);

		final PositiveRealValueLabel sigmaRObsLabel = new PositiveRealValueLabel();
		sigmaRObsLabel.setBounds(40, 155, 120, 15);

		sigmaRInputField = new JTextField();
		sigmaRInputField.setBounds(200, 140, 35, 20);

		final JLabel iterationsLabel = new JLabel("Número de iterações:");
		iterationsLabel.setBounds(40, 180, 200, 15);

		final JLabel iterationsObsLabel = new JLabel("(valor inteiro positivo)");
		iterationsObsLabel.setBounds(40, 195, 120, 15);

		iterationsInputField = new JTextField();
		iterationsInputField.setBounds(200, 180, 35, 20);

		final JButton okButton = new JButton(OK_LABEL);
		okButton.setBounds(60, 235, 57, 25);
		okButton.addActionListener(new JanelaFiltroBilateralActionListener(this, simple));

		final JButton cancelButton = new JButton(CANCEL_LABEL);
		cancelButton.setBounds(130, 235, 85, 25);
		cancelButton.addActionListener(new JanelaFiltroBilateralActionListener(this, simple));

		getContentPane().add(pleasingInfoLabel);
		getContentPane().add(maskLabel);
		getContentPane().add(maskObsLabel);
		getContentPane().add(maskInputField);
		getContentPane().add(sigmaDLabel);
		getContentPane().add(sigmaDObsLabel);
		getContentPane().add(sigmaDInputField);
		getContentPane().add(sigmaRLabel);
		getContentPane().add(sigmaRObsLabel);
		getContentPane().add(sigmaRInputField);
		getContentPane().add(iterationsLabel);
		getContentPane().add(iterationsObsLabel);
		getContentPane().add(iterationsInputField);
		getContentPane().add(okButton);
		getContentPane().add(cancelButton);
		maskInputField.addKeyListener(new JanelaFiltroBilateralKeyListener(this));
		okButton.addKeyListener(new JanelaFiltroBilateralKeyListener(this));
		cancelButton.addKeyListener(new JanelaFiltroBilateralKeyListener(this));
		setVisible(true);

	}

	public int getMaskSize() {
		return Integer.parseInt(this.maskInputField.getText());
	}

	public double getSigmaD() {
		return Double.parseDouble(this.sigmaDInputField.getText());
	}

	public double getSigmaR() {
		return Double.parseDouble(this.sigmaRInputField.getText());
	}

	public int getNumberOfIterations() {
		return Integer.parseInt(this.iterationsInputField.getText());
	}

	public void resetMaskInputField() {
		this.maskInputField.setText("");
	}

	public void resetSigmaDInputField() {
		this.sigmaDInputField.setText("");
	}

	public void resetSigmaRInputField() {
		this.sigmaRInputField.setText("");
	}

	public void resetNumberOfIterationsInputField() {
		this.iterationsInputField.setText("");
	}

}
