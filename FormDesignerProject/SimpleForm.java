import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Thu Feb 25 00:26:58 YEKT 2021
 */



/**
 * @author 3wwa3
 */
public class SimpleForm extends JPanel {
	public SimpleForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		SimpleFormStartMining = new JButton();
		SimpleFormWalletAdress = new JTextField();
		SimpleFormExpertMode = new JToggleButton();
		SimpleFormWalletLabel = new JLabel();
		SimpleFormStats = new JButton();
		scrollPane1 = new JScrollPane();
		SimpleFormOutput = new JTextArea();

		//======== this ========

		//---- SimpleFormStartMining ----
		SimpleFormStartMining.setText("Start Mining");

		//---- SimpleFormExpertMode ----
		SimpleFormExpertMode.setText("Expert Mode");

		//---- SimpleFormWalletLabel ----
		SimpleFormWalletLabel.setText("Wallet");
		SimpleFormWalletLabel.setLabelFor(SimpleFormWalletAdress);

		//---- SimpleFormStats ----
		SimpleFormStats.setText("Stats");

		//======== scrollPane1 ========
		{

			//---- SimpleFormOutput ----
			SimpleFormOutput.setText("Output");
			SimpleFormOutput.setBackground(UIManager.getColor("Button.background"));
			SimpleFormOutput.setEditable(false);
			SimpleFormOutput.setBorder(null);
			SimpleFormOutput.setAutoscrolls(false);
			scrollPane1.setViewportView(SimpleFormOutput);
		}

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(SimpleFormWalletLabel)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(SimpleFormWalletAdress, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(SimpleFormStartMining, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(SimpleFormStats, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addComponent(SimpleFormExpertMode))
					.addGap(0, 0, 0))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(9, 9, 9)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(SimpleFormWalletAdress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(SimpleFormWalletLabel))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(SimpleFormStartMining)
						.addComponent(SimpleFormStats))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(SimpleFormExpertMode)
					.addGap(10, 10, 10))
		);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JButton SimpleFormStartMining;
	private JTextField SimpleFormWalletAdress;
	private JToggleButton SimpleFormExpertMode;
	private JLabel SimpleFormWalletLabel;
	private JButton SimpleFormStats;
	private JScrollPane scrollPane1;
	private JTextArea SimpleFormOutput;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
