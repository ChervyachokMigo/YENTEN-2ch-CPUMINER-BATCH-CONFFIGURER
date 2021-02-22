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
		CloseMiners = new JButton();
		Solo = new JCheckBox();
		AditionalParameters = new JTextField();
		AditionalParametersLabel = new JLabel();
		CoinType = new JComboBox();
		CoinTypeLabel = new JLabel();
		Registration = new JButton();
		Ping = new JLabel();
		Profiles = new JButton();
		PoolURL = new JTextField();
		PoolURLLabel = new JLabel();

		//======== this ========
		setResizable(false);
		setBackground(Color.lightGray);
		setForeground(Color.black);
		setTitle("YENTEN 2CH CPUMINER BATCH CONFIGURATOR");
		Container contentPane = getContentPane();

		//---- PoolLabel ----
		PoolLabel.setText("Pool");
		PoolLabel.setLabelFor(Pool);

		//---- AlghorytmLabel ----
		AlghorytmLabel.setText("Alghorytm");
		AlghorytmLabel.setLabelFor(Alghorytm);

		//---- WalletAdressLabel ----
		WalletAdressLabel.setText("Wallet/Username");
		WalletAdressLabel.setLabelFor(WalletAdress);

		//---- NamePCLabel ----
		NamePCLabel.setText("Name PC");
		NamePCLabel.setLabelFor(NamePC);

		//---- NumberThreadsLabel ----
		NumberThreadsLabel.setText("Number of Threads");
		NumberThreadsLabel.setLabelFor(NumberThreads);

		//---- CPUPriorityLabel ----
		CPUPriorityLabel.setText("Miner Priority");
		CPUPriorityLabel.setLabelFor(CPUPriority);

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
		PasswordLabel.setLabelFor(Password);

		//---- TimeStratumLabel ----
		TimeStratumLabel.setText("Timeout/Stratum (sec)");
		TimeStratumLabel.setLabelFor(TimeStratum);

		//---- TimeStratum ----
		TimeStratum.setModel(new SpinnerNumberModel(300, 30, null, 30));

		//---- SaveBAT ----
		SaveBAT.setText("Save BAT File");

		//---- CloseMiners ----
		CloseMiners.setText("Close All Miners");

		//---- Solo ----
		Solo.setText("Solo");

		//---- AditionalParametersLabel ----
		AditionalParametersLabel.setText("Adtitional parameters");

		//---- CoinTypeLabel ----
		CoinTypeLabel.setText("CoinType");

		//---- Registration ----
		Registration.setText("Registration");

		//---- Ping ----
		Ping.setText("Pool ping: None");

		//---- Profiles ----
		Profiles.setText("Profiles...");

		//---- PoolURLLabel ----
		PoolURLLabel.setText("Mining URL");
		PoolURLLabel.setLabelFor(PoolURL);

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(PoolLabel, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(Pool, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(AddPool, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(5, 5, 5)
							.addComponent(Solo))
						.addComponent(AditionalParametersLabel)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(HideDiff)
								.addComponent(HideCPU)
								.addComponent(NoColor))
							.addGap(0, 0, 0)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(Pause)
								.addComponent(InfiniteLoop))
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(DebugCheck)
									.addGap(68, 68, 68))
								.addComponent(BackgroundMode)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(NumberThreadsLabel)
								.addComponent(WalletAdressLabel)
								.addComponent(NamePCLabel)
								.addComponent(PasswordLabel)
								.addComponent(CPUPriorityLabel))
							.addGap(5, 5, 5)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(CPUPriority, 0, 211, Short.MAX_VALUE)
								.addComponent(NumberThreads, 0, 211, Short.MAX_VALUE)
								.addComponent(Password, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(NamePC, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(WalletAdress, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
							.addGap(5, 5, 5)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(Registration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(ApportionCPU)
								.addComponent(Ping, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(TimeStratumLabel)
							.addGap(5, 5, 5)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(TimeStratum, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(AditionalParameters, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(StartMining, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(SaveBAT, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(DefaultSettings, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(CloseMiners, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(Benchmark)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(Profiles, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(PoolURLLabel)
							.addGap(5, 5, 5)
							.addComponent(PoolURL, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(CoinTypeLabel)
							.addGap(5, 5, 5)
							.addComponent(CoinType, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(AlghorytmLabel)
							.addGap(5, 5, 5)
							.addComponent(Alghorytm, 0, 156, Short.MAX_VALUE)))
					.addGap(5, 5, 5))
		);
		contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {Benchmark, CloseMiners, SaveBAT, StartMining});
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(Pool)
						.addComponent(PoolLabel)
						.addComponent(AddPool, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(Solo))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(CoinTypeLabel)
						.addComponent(CoinType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AlghorytmLabel)
						.addComponent(Alghorytm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(PoolURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PoolURLLabel))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(WalletAdressLabel)
						.addComponent(WalletAdress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Registration, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(NamePCLabel)
						.addComponent(NamePC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Ping, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(PasswordLabel)
						.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(CPUPriorityLabel)
						.addComponent(CPUPriority, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(ApportionCPU)
						.addComponent(NumberThreadsLabel)
						.addComponent(NumberThreads, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(TimeStratumLabel)
						.addComponent(TimeStratum, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(AditionalParametersLabel)
						.addComponent(AditionalParameters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(NoColor, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(HideCPU, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(HideDiff, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(InfiniteLoop, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(DebugCheck, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(Pause)
								.addComponent(BackgroundMode, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addGap(5, 5, 5)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(StartMining)
						.addComponent(SaveBAT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(DefaultSettings))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(CloseMiners)
						.addComponent(Benchmark)
						.addComponent(Profiles))
					.addGap(0, 0, 0))
		);
		setSize(455, 435);
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
	private JButton CloseMiners;
	private JCheckBox Solo;
	private JTextField AditionalParameters;
	private JLabel AditionalParametersLabel;
	private JComboBox CoinType;
	private JLabel CoinTypeLabel;
	private JButton Registration;
	private JLabel Ping;
	private JButton Profiles;
	private JTextField PoolURL;
	private JLabel PoolURLLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
