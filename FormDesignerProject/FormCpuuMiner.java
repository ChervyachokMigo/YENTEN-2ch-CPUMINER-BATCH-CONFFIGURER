import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
/*
 * Created by JFormDesigner on Sun Feb 14 19:37:46 YEKT 2021
 */



/**
 * @author 3wwa3
 */
public class FormCpuuMiner extends JFrame {
	public FormCpuuMiner() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		PoolLabel = new JLabel();
		Pool = new JComboBox();
		AlghorytmLabel = new JLabel();
		Alghorytm = new JComboBox();
		WalletAdressLabel = new JLabel();
		WalletAdress = new JTextField();
		NamePCLabel = new JLabel();
		NamePC = new JTextField();
		NumberThreads = new JComboBox();
		NumberThreadsLabel = new JLabel();
		CPUPriorityLabel = new JLabel();
		CPUPriority = new JComboBox();
		ApportionCPU = new JCheckBox();
		HideCPU = new JCheckBox();
		NoColor = new JCheckBox();
		HideDiff = new JCheckBox();
		BackgroundMode = new JCheckBox();
		StartMining = new JButton();
		DefaultSettings = new JButton();
		AddPool = new JButton();
		DebugCheck = new JCheckBox();
		Pause = new JCheckBox();
		InfiniteLoop = new JCheckBox();
		Benchmark = new JButton();
		Password = new JTextField();
		PasswordLabel = new JLabel();
		TimeStratumLabel = new JLabel();
		TimeStratum = new JSpinner();
		SaveBAT = new JButton();
		button1 = new JButton();

		//======== this ========
		setResizable(false);
		setBackground(Color.lightGray);
		setForeground(Color.black);
		setTitle("YENTEN 2CH CPUMINER BATCH CONFIGURATOR");
		Container contentPane = getContentPane();

		//---- PoolLabel ----
		PoolLabel.setText("Pool");

		//---- AlghorytmLabel ----
		AlghorytmLabel.setText("Alghorytm");

		//---- WalletAdressLabel ----
		WalletAdressLabel.setText("Wallet/Username");

		//---- NamePCLabel ----
		NamePCLabel.setText("Name PC");

		//---- NumberThreadsLabel ----
		NumberThreadsLabel.setText("Number of Threads");

		//---- CPUPriorityLabel ----
		CPUPriorityLabel.setText("Miner Priority");

		//---- ApportionCPU ----
		ApportionCPU.setText("Apportion CPU");

		//---- HideCPU ----
		HideCPU.setText("Hide CPU Hashmeter");

		//---- NoColor ----
		NoColor.setText("Disable colored output");
		NoColor.setMargin(new Insets(2, 0, 2, 2));

		//---- HideDiff ----
		HideDiff.setText("Don't display diff");

		//---- BackgroundMode ----
		BackgroundMode.setText("Background mode");

		//---- StartMining ----
		StartMining.setText("Start miner");

		//---- DefaultSettings ----
		DefaultSettings.setText("Default Settings");

		//---- AddPool ----
		AddPool.setText("Add");
		AddPool.setBackground(new Color(204, 204, 204));
		AddPool.setIcon(null);
		AddPool.setMargin(new Insets(2, 0, 2, 0));

		//---- DebugCheck ----
		DebugCheck.setText("Debug");

		//---- Pause ----
		Pause.setText("Pause after crash");

		//---- InfiniteLoop ----
		InfiniteLoop.setText("Infinite Loop");

		//---- Benchmark ----
		Benchmark.setText("Start benchmark");

		//---- PasswordLabel ----
		PasswordLabel.setText("Password");

		//---- TimeStratumLabel ----
		TimeStratumLabel.setText("Timeout/Stratum (sec)");

		//---- TimeStratum ----
		TimeStratum.setModel(new SpinnerNumberModel(300, 30, null, 30));

		//---- SaveBAT ----
		SaveBAT.setText("Save BAT File");

		//---- button1 ----
		button1.setText("Close All Miners");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(6, 6, 6)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup()
									.addComponent(button1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
									.addComponent(StartMining, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
								.addComponent(HideDiff)
								.addComponent(HideCPU))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(Pause)
								.addComponent(Benchmark, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
								.addComponent(SaveBAT, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
								.addComponent(InfiniteLoop))
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGap(18, 18, 18)
									.addGroup(contentPaneLayout.createParallelGroup()
										.addComponent(DebugCheck)
										.addComponent(BackgroundMode))
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE))
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(DefaultSettings, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))))
						.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
							.addComponent(PoolLabel)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(Pool, 0, 363, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(AddPool, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(WalletAdressLabel)
								.addComponent(NamePCLabel)
								.addComponent(PasswordLabel)
								.addComponent(CPUPriorityLabel)
								.addComponent(NumberThreadsLabel))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addGroup(contentPaneLayout.createParallelGroup()
										.addComponent(NamePC, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(Password, GroupLayout.Alignment.LEADING)
												.addComponent(CPUPriority, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(NumberThreads, GroupLayout.Alignment.LEADING, 0, 155, Short.MAX_VALUE))
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
									.addGroup(contentPaneLayout.createParallelGroup()
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addGap(5, 5, 5)
											.addComponent(AlghorytmLabel)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(Alghorytm, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
										.addGroup(contentPaneLayout.createSequentialGroup()
											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
											.addComponent(ApportionCPU))))
								.addComponent(WalletAdress, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)))
						.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
							.addComponent(TimeStratumLabel)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(TimeStratum, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addGap(40, 40, 40))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(NoColor)
					.addContainerGap(343, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(Pool)
						.addComponent(PoolLabel)
						.addComponent(AddPool, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(WalletAdressLabel)
						.addComponent(WalletAdress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(NamePCLabel, GroupLayout.Alignment.LEADING)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(NamePC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(AlghorytmLabel)
							.addComponent(Alghorytm, 0, 0, Short.MAX_VALUE)))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(PasswordLabel)
						.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(CPUPriorityLabel)
						.addComponent(CPUPriority, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(NumberThreadsLabel)
						.addComponent(NumberThreads, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(ApportionCPU))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(TimeStratumLabel)
								.addComponent(TimeStratum, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(18, 18, 18)
							.addComponent(NoColor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(InfiniteLoop, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(HideCPU, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
						.addComponent(DebugCheck, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(BackgroundMode, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Pause)
						.addComponent(HideDiff, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(StartMining)
						.addComponent(SaveBAT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button1)
						.addComponent(Benchmark)
						.addComponent(DefaultSettings))
					.addContainerGap())
		);
		setSize(485, 390);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel PoolLabel;
	private JComboBox Pool;
	private JLabel AlghorytmLabel;
	private JComboBox Alghorytm;
	private JLabel WalletAdressLabel;
	private JTextField WalletAdress;
	private JLabel NamePCLabel;
	private JTextField NamePC;
	private JComboBox NumberThreads;
	private JLabel NumberThreadsLabel;
	private JLabel CPUPriorityLabel;
	private JComboBox CPUPriority;
	private JCheckBox ApportionCPU;
	private JCheckBox HideCPU;
	private JCheckBox NoColor;
	private JCheckBox HideDiff;
	private JCheckBox BackgroundMode;
	private JButton StartMining;
	private JButton DefaultSettings;
	private JButton AddPool;
	private JCheckBox DebugCheck;
	private JCheckBox Pause;
	private JCheckBox InfiniteLoop;
	private JButton Benchmark;
	private JTextField Password;
	private JLabel PasswordLabel;
	private JLabel TimeStratumLabel;
	private JSpinner TimeStratum;
	private JButton SaveBAT;
	private JButton button1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
