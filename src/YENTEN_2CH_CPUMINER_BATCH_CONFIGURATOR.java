import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultFormatter;


public class YENTEN_2CH_CPUMINER_BATCH_CONFIGURATOR {
	private JCheckBox ShowCPU;
	private JCheckBox NoColor;
	private JComboBox<String> NumberThreads;
	private JComboBox<String> Alghorytm;
	private JComboBox<String> Pool;
	private JComboBox<String> CPUPriority;
	private JTextField WalletAdress;
	private JTextField NamePC;
	private JCheckBox ApportionCPU;
	private JCheckBox HideMinerStats;
	private JCheckBox BackgroundMode;
	private JCheckBox DebugCheck;
	
	private JButton DefaultSettings;
	private JButton StartMining;
	private JButton AddPool;
	
	private String CommandOutput;
	
	private int cores;
	
	private Preferences ProfilePrefs;
	private String ProfileName;
	
	private JLabel NumberThreadsLabel;
	private JLabel WalletAdressLabel;
	private JLabel AlghorytmLabel;
	private JLabel PoolLabel;
	private JLabel NamePCLabel;
	private JLabel CPUPriorityLabel;
	private JLabel PasswordLabel;
	private JLabel TimeStratumLabel;
			
	private JCheckBox Pause;
	private JCheckBox InfiniteLoop;
	private JButton Benchmark;
	private JTextField Password;
	private JSpinner TimeStratum;
	private JButton SaveBAT;
	
	private JButton CloseMiners;
	private String PoolDefault;
	private String[][] DefaultPoolsList;
	private Preferences LastPool;
	
	private JCheckBox Solo;
	private JTextField AditionalParameters;
	private JLabel AditionalParametersLabel;
	private JComboBox<String> CoinType;
	private JLabel CoinTypeLabel;
	private JButton Registration;
	private JLabel Ping;
	private JButton Profiles;
	private JTextField PoolURL;
	private JLabel PoolURLLabel;
	private String LastPoolRegistrationURL;
	
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	
	public YENTEN_2CH_CPUMINER_BATCH_CONFIGURATOR(String windowName) {
		JFrame wid = new JFrame(windowName);
		wid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wid.addWindowListener(new WindowAdapter() {
	    	 @Override
	    	 public void windowClosing(WindowEvent e) {
	    		 SaveSettings((String) Pool.getSelectedItem());
	    	     System.exit(0);
	    	 }
	     });
		wid.setTitle("YENTEN 2CH CPUMINER BATCH CONFIGURATOR");
		ImageIcon img = new ImageIcon("logo.png");
		wid.setIconImage(img.getImage());
		Init(wid);
	}
	
	private void Init(final JFrame window) {
		 cores = Runtime.getRuntime().availableProcessors();
				 
		 PoolDefault = "YENTEN-POOL";
		 String[] PoolList = new String[] {null};
		
		//createform
		 StartMining = new JButton("Start Mining");  
		 StartMining.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	  	StartMiningAction(window);
             }
         });
		 StartMining.setToolTipText("Launch cpuminer with current settings");
		 
		 DefaultSettings = new JButton("Default Settings");
		 DefaultSettings.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	 	SetDefaultSettings();
           	 	SaveSettings((String) Pool.getSelectedItem());
             }
         });
		 DefaultSettings.setToolTipText("Clear all fields");
			 
		 AddPool = new JButton("Add...");
		 AddPool.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ShowAddPoolDialog(window);
				 SetDefaultSettings();
				 SaveSettings((String) Pool.getSelectedItem());
			 }
		 });
		 AddPool.setMargin(new Insets(2, 0, 2, 0));
		 AddPool.setToolTipText("Add new Pool");
		 
		 Benchmark = new JButton("Start Benchmark");
		 Benchmark.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 StartBenchmark(window);
			 }
		 });
		 Benchmark.setToolTipText("Test your PC");
		 
		 DebugCheck = new JCheckBox("Debug");
		 DebugCheck.setToolTipText("Output cpuminer debug info");
		 
		 ShowCPU = new JCheckBox("Show CPU hashmeter output");
		 
		 NoColor = new JCheckBox("Disable colored output");
		    
		 ApportionCPU = new JCheckBox("Apportion load CPU");
		 
		 HideMinerStats = new JCheckBox("Don't display Miner Stats");
		 
		 
		 BackgroundMode = new JCheckBox("Background mode");
		 BackgroundMode.setToolTipText("Run miner without console");
		 
		 String[] NumberCoresValues = new String[cores];
		 for(int i=1;i<=cores;i++) {
			 NumberCoresValues[i-1] =  i+"";
		 }
		 NumberThreads = new JComboBox<String>(NumberCoresValues);
		 NumberThreads.setSelectedIndex(cores-1);
		 NumberThreads.setToolTipText("Number of threads to mining");
	     
	     NumberThreadsLabel = new JLabel("Number of threads");
		 
	     String[] AlghorytmsList = new String[]{"yespowerR16"};
	     Alghorytm = new JComboBox<String>(AlghorytmsList);
	     Alghorytm.setToolTipText("Alghorytm");
	     
	     AlghorytmLabel = new JLabel ("Alghorytm");
	     
	     
	     Pool = new JComboBox<String>(PoolList);
	     Pool.setToolTipText("Select Pool or Add New Pool Adress");
	     
	     PoolLabel = new JLabel ("Pool");
	     
	     String[] PriorityList = new String[] {"[0] IDLE","[1] BELOW NORMAL","[2] NORMAL","[3] ABOVE NORMAL","[4] HIGH","[5] REALTIME"};
	     CPUPriority = new JComboBox<String>(PriorityList);
	     CPUPriority.setToolTipText("Set cpuminer process priority");
	     CPUPriority.setSelectedIndex(2);
	     
	     CPUPriorityLabel = new JLabel ("Miner Priority");
	     
	     WalletAdress = new JTextField("");
	     WalletAdressLabel = new JLabel("Wallet/Username");
	     WalletAdress.setToolTipText("Enter your Wallet Adress or your Worker Username");
	     
	     NamePC = new JTextField("");
	     NamePCLabel = new JLabel("PC Name");
	     NamePC.setToolTipText("Enter any worker nick");
	     	     
		 Pause = new JCheckBox("Pause after crash");
		 Pause.setToolTipText("if cpuminer will crash set not close console");
		 
		 InfiniteLoop = new JCheckBox("Infinite Loop");
		 InfiniteLoop.setToolTipText("if cpuminer will crash he will continue working");
		 
		 Password = new JTextField("");
		 Password.setToolTipText("Enter pool password or user password or nothing");
		 PasswordLabel = new JLabel("Password");
		 
		 TimeStratumLabel = new JLabel("Timeout/Stratum (sec)");
		 TimeStratum = new JSpinner(new SpinnerNumberModel(300, 30, null, 30));
		 TimeStratum.setToolTipText("Set timeout of one long job calculation and stratum");
		 
		 SaveBAT = new JButton("Save BAT File");
		 SaveBAT.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 SaveBATFile(window);
			 }
		 });
		 SaveBAT.setToolTipText("Save BAT File on computer");
		 
		 CloseMiners = new JButton("Close All Miners");
		 CloseMiners.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 CloseMinersCommand(window);
			 }
		 });
		 CloseMiners.setToolTipText("Close All Running Miners");
		 
		Registration = new JButton();
		
		CoinType = new JComboBox<String>();
		CoinType.addItem("YTN");
		CoinTypeLabel = new JLabel();
		
		PoolURL = new JTextField();
		PoolURLLabel = new JLabel();
		PoolURL.setDisabledTextColor(Color.black);
		PoolURL.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		AditionalParameters = new JTextField();
		AditionalParametersLabel = new JLabel();
		
		Ping = new JLabel();
		
		Solo = new JCheckBox();
		Solo.setEnabled(false);
		Solo.setText("Solo");
		Profiles = new JButton();
		Profiles.setEnabled(false);
		
		
		AditionalParametersLabel.setText("Adtitional parameters");
		CoinTypeLabel.setText("CoinType");
		Registration.setText("Registration");
		Ping.setText("Pool ping: None");
		Profiles.setText("Profiles...");
		PoolURLLabel.setText("Mining URL");
		
		PoolURLLabel.setLabelFor(PoolURL);
		PoolLabel.setLabelFor(Pool);
		WalletAdressLabel.setLabelFor(WalletAdress);
		AlghorytmLabel.setLabelFor(Alghorytm);
		NamePCLabel.setLabelFor(NamePC);
		NumberThreadsLabel.setLabelFor(NumberThreads);
		CPUPriorityLabel.setLabelFor(CPUPriority);
		PasswordLabel.setLabelFor(Password);
		TimeStratumLabel.setLabelFor(TimeStratum);
		
		 
		 WindowInterfacePlacement(window);
		 
		 //load settings
		 
		 String[][] DefaultPoolsList_t = {
			//Name  URL  RegistrationURL pingpoolurl password walletor/username
			{ "YENTEN-POOL" , "stratum+tcp://yenten-pool.info:63368", "No", "yenten-pool.info", "" , "Wallet" },
			{ "CPU-POOL", "stratum+tcp://cpu-pool.com:63368", "No", "cpu-pool.com", "" , "Wallet" , "Wallet"  },
			{ "RPLANT-POOL (varDiff 0.2) RUS", "stratum+tcp://stratum-ru.rplant.xyz:3382", "No", "stratum-ru.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL (varDiff 0.8) RUS", "stratum+tcp://stratum-ru.rplant.xyz:3383", "No", "stratum-ru.rplant.xyz", ""  , "Wallet" },
			{ "RPLANT-POOL SSL (varDiff 0.2) RUS", "stratum+tcps://stratum-ru.rplant.xyz:13382", "No", "stratum-ru.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL SSL (varDiff 0.8) RUS", "stratum+tcps://stratum-ru.rplant.xyz:13383", "No", "stratum-ru.rplant.xyz", ""  , "Wallet"},
			{ "RPLANT-POOL (varDiff 0.2) EU", "stratum+tcp://stratum-eu.rplant.xyz:3382", "No", "stratum-eu.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL (varDiff 0.8) EU", "stratum+tcp://stratum-eu.rplant.xyz:3383", "No", "stratum-eu.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL SSL (varDiff 0.2) EU", "stratum+tcps://stratum-eu.rplant.xyz:13382", "No", "stratum-eu.rplant.xyz", "" , "Wallet"},
			{ "RPLANT-POOL SSL (varDiff 0.8) EU", "stratum+tcps://stratum-eu.rplant.xyz:13383", "No", "stratum-eu.rplant.xyz", "" , "Wallet"},
			{ "RPLANT-POOL (varDiff 0.2) ASIA", "stratum+tcp://stratum-asia.rplant.xyz:3382", "No", "stratum-asia.rplant.xyz", "" , "Wallet"},
			{ "RPLANT-POOL (varDiff 0.8) ASIA", "stratum+tcp://stratum-asia.rplant.xyz:3383", "No", "stratum-asia.rplant.xyz", "" , "Wallet"},
			{ "RPLANT-POOL (varDiff 0.2) ASIA", "stratum+tcps://stratum-asia.rplant.xyz:13382", "No", "stratum-asia.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL (varDiff 0.8) ASIA", "stratum+tcps://stratum-asia.rplant.xyz:13383", "No", "stratum-asia.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL (varDiff 0.2) USA", "stratum+tcp://stratum-na.rplant.xyz:3382", "No", "stratum-na.rplant.xyz", "" , "Wallet"},
			{ "RPLANT-POOL (varDiff 0.8) USA", "stratum+tcp://stratum-na.rplant.xyz:3383", "No", "stratum-na.rplant.xyz", "" , "Wallet" },
			{ "RPLANT-POOL (varDiff 0.2) USA", "stratum+tcps://stratum-na.rplant.xyz:13382", "No", "stratum-na.rplant.xyz", "" , "Wallet"},
			{ "RPLANT-POOL (varDiff 0.8) USA", "stratum+tcps://stratum-na.rplant.xyz:13383", "No", "stratum-na.rplant.xyz", "" , "Wallet"},
			{ "MINING DUTCH EU", "stratum+tcp://yespowerr16.mining-dutch.nl:9987", "https://www.mining-dutch.nl/pools/account.php?page=register", "yespowerr16.mining-dutch.nl", ""  , "Username" },
			{ "MINING DUTCH ASIA", "stratum+tcp://asia.yespowerr16.mining-dutch.nl:9987", "https://www.mining-dutch.nl/pools/account.php?page=register", "asia.yespowerr16.mining-dutch.nl", "" , "Username" },
			{ "MINING DUTCH USA", "stratum+tcp://americas.yespowerr16.mining-dutch.nl:9987", "https://www.mining-dutch.nl/pools/account.php?page=register", "americas.yespowerr16.mining-dutch.nl", "" , "Username" },
			{ "ZERGPOOL ANYCAST", "stratum+tcp://yespowerR16.mine.zergpool.com:6534", "No", "yespowerR16.mine.zergpool.com", "c=YTN,mc=YTN" , "Wallet" },
			{ "ZERGPOOL EU", "stratum+tcp://yespowerR16.eu.mine.zergpool.com:6534", "No", "yespowerR16.eu.mine.zergpool.com", "c=YTN,mc=YTN" , "Wallet"  },
			{ "ZERGPOOL ASIA", "stratum+tcp://yespowerR16.asia.mine.zergpool.com:6534", "No", "yespowerR16.asia.mine.zergpool.com", "c=YTN,mc=YTN" , "Wallet" },
			{ "ZERGPOOL USA", "stratum+tcp://yespowerR16.na.mine.zergpool.com:6534", "No", "yespowerR16.na.mine.zergpool.com", "c=YTN,mc=YTN" , "Wallet"  },
			{ "LEYWA MINING POOL JP1", "stratum+tcp://yespowerR16.jp1.mine.leywapool.com:6342", "No", "yespowerR16.jp1.mine.leywapool.com", "c=YTN" , "Wallet" },
			{ "LEYWA MINING POOL JP2", "stratum+tcp://yespowerR16.jp2.mine.leywapool.com:6342", "No", "yespowerR16.jp2.mine.leywapool.com", "c=YTN" , "Wallet" },
			{ "LEYWA MINING POOL JP3", "stratum+tcp://yespowerR16.jp3.mine.leywapool.com:6342", "No", "yespowerR16.jp3.mine.leywapool.com", "c=YTN" , "Wallet" },
			{ "YENTEN LUCKY POOL (EXTRA LOW DIFF)", "stratum+tcp://stratum.luckypool.org:3311", "https://yenten.luckypool.org/index.php?page=register", "stratum.luckypool.org", "" , "Username" },
			{ "YENTEN LUCKY POOL (LOW DIFF)", "stratum+tcp://stratum.luckypool.org:3344", "https://yenten.luckypool.org/index.php?page=register", "stratum.luckypool.org", "" , "Username" },
			{ "YENTEN LUCKY POOL (HIGH LOW DIFF)", "stratum+tcp://stratum.luckypool.org:3399", "https://yenten.luckypool.org/index.php?page=register", "stratum.luckypool.org", "" , "Username"},
			{ "YENTEN LUCKY POOL (FIXED DIFF 0.4)", "stratum+tcp://stratum.luckypool.org:3355", "https://yenten.luckypool.org/index.php?page=register", "stratum.luckypool.org", "" , "Username"},
			{ "YENTEN LUCKY POOL (FIXED DIFF 1)", "stratum+tcp://stratum.luckypool.org:3333", "https://yenten.luckypool.org/index.php?page=register", "stratum.luckypool.org", "" , "Username"},
			{ "NLPOOL", "stratum+tcp://mine.nlpool.nl:6244", "No", "mine.nlpool.nl", "c=YTN" , "Wallet" },
			{ "SUPERNOVAS YENTEN POOL", "stratum+tcp://ytn.suprnova.cc:4932", "No", "ytn.suprnova.cc", "" , "Wallet" },
			{ "BLOCK MASTERS EU", "stratum+tcp://eu.blockmasters.co:6236", "No", "eu.blockmasters.co", "" , "Wallet" },
			{ "BLOCK MASTERS ASIA", "stratum+tcp://as.blockmasters.co:6236", "No", "as.blockmasters.co", "" , "Wallet" },
			{ "BLOCK MASTERS USA", "stratum+tcp://blockmasters.co:6236", "No", "blockmasters.co", "" , "Wallet" },
			{ "TROSS MINING POOL", "stratum+tcp://trossmining.de:6236", "No", "trossmining.de", "c=YTN" , "Wallet" },
			{ "ZPOOL EU", "stratum+tcp://yespowerR16.eu.mine.zpool.ca:6534", "No", "yespowerR16.eu.mine.zpool.ca", "c=YTN,zap=YTN" , "Wallet" },
			{ "ZPOOL ASIA", "stratum+tcp://yespowerR16.sea.mine.zpool.ca:6534", "No", "yespowerR16.sea.mine.zpool.ca", "c=YTN,zap=YTN" , "Wallet" },
			{ "ZPOOL USA", "stratum+tcp://yespowerR16.na.mine.zpool.ca:6534", "No", "yespowerR16.na.mine.zpool.ca", "c=YTN,zap=YTN" , "Wallet" },
			{ "PHI PHI POOL", "stratum+tcp://asia.phi-phi-pool.com:6333", "No", "asia.phi-phi-pool.com", "c=YTN" , "Wallet" },
			{ "MATRIX POOL", "stratum+tcp://matrix-pool.info:63040", "No", "matrix-pool.info", "" , "Wallet" },
			{ "AIKA POOL", "stratum+tcp://stratum.aikapool.com:7954", "https://aikapool.com/ytn/index.php?page=register", "stratum.aikapool.com", "" , "Username"}
		 };
		 DefaultPoolsList = DefaultPoolsList_t;
		 ProfileName = Preferences.userRoot().node("cpu-miner-batch-configurator").get("lastprofilename", "default");
		 ChangeProfile(ProfileName);
		 
		 LoadPoolList();
		 	        	
		 Pool.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
        	   if(e.getStateChange() == ItemEvent.DESELECTED) {
        		   SaveSettings(e.getItem().toString());
        		   SetDefaultSettings();
        	   }
        	   if(e.getStateChange() == ItemEvent.SELECTED) {
        			   String LastPoolName = e.getItem().toString();
	        		   ProfilePrefs.put("lastpool", LastPoolName);
	        		   LastPool = ProfilePrefs.node(LastPoolName);
	        		   LoadLastPool();
        	   }
            }
         });
		 
		 Registration.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 OpenRegistrationURL();
			 }
		 });
		 	 
	     AddUpdateElement(ShowCPU);
		 AddUpdateElement(NoColor);
		 AddUpdateElement(HideMinerStats);
		 AddUpdateElement(BackgroundMode);
		 AddUpdateElement(NumberThreads);
		 AddUpdateElement(Alghorytm);
		 AddUpdateElement(Pool);
		 AddUpdateElement(CPUPriority);
		 AddUpdateElement(ApportionCPU);
		 AddUpdateElement(WalletAdress);
		 AddUpdateElement(NamePC);
		 AddUpdateElement(DebugCheck);
		 AddUpdateElement(Pause);
		 AddUpdateElement(InfiniteLoop);
		 AddUpdateElement(Password);
		 AddUpdateElement(TimeStratum);		 
		 
	     UpdateCommand();


		//final IcmpPingUtil test = new IcmpPingUtil ();
	}

	protected void OpenRegistrationURL() {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		    try {
				Desktop.getDesktop().browse(new URI(LastPoolRegistrationURL));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void AddUpdateElement(JComponent Element) {
		if (Element instanceof JComboBox) {
			((JComboBox<String>) Element).addItemListener(new ItemListener() {
	           public void itemStateChanged(ItemEvent e) {
	               UpdateCommand();
	           }
	        });
		}
		if (Element instanceof JCheckBox) {
			((JCheckBox) Element).addItemListener(new ItemListener() {
	           public void itemStateChanged(ItemEvent e) {
	               UpdateCommand();
	           }
	        });
		}
		if (Element instanceof JCheckBox) {
			((JCheckBox) Element).addItemListener(new ItemListener() {
	           public void itemStateChanged(ItemEvent e) {
	               UpdateCommand();
	           }
	        });
		}
		if (Element instanceof JTextField ) {
			Element.addKeyListener(new KeyAdapter() {   
	           public void keyPressed(KeyEvent e) {
	           	 UpdateCommand();
	           }
	           public void keyReleased(KeyEvent e) {
	          	 UpdateCommand();
	          }
	           public void keyTyped(KeyEvent e) {
	           	 UpdateCommand();
	           }
	       });
		}
		if (Element instanceof JSpinner) {
			JComponent comp = ((JSpinner) Element).getEditor();
		    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    ((JSpinner) Element).addChangeListener(new ChangeListener() {
		        public void stateChanged(ChangeEvent e) {
		        	UpdateCommand();
		        }
		    });
		}
	}
	
	private void WindowInterfacePlacement(JFrame window) {
		Container contentPane = window.getContentPane();    
		 window.setResizable(false);

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
									.addComponent(HideMinerStats)
									.addComponent(ShowCPU)
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
								.addComponent(ShowCPU, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(HideMinerStats, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
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
			
			window.setSize(455, 435);
			 
	        window.pack();
	        
	        window.setLocationRelativeTo(null);
	        window.setVisible(true);
	}

	private void ShowAddPoolDialog(JFrame window){
	    JTextField NewPoolName = new JTextField();
	    JComponent[] AddPoolInputField = new JComponent[] {
			        new JLabel("New Pool Name"),
			        NewPoolName
			};
	    int result = JOptionPane.showConfirmDialog(window, AddPoolInputField, "Add New Pool", JOptionPane.PLAIN_MESSAGE);
	    	    
	    if (result == JOptionPane.OK_OPTION && NewPoolName.getText().length()>0) {
	        Pool.addItem(NewPoolName.getText());
	        Pool.setSelectedIndex(Pool.getItemCount()-1);
	        UpdateCommand();
	    } 
	    
	}
	
	private void CreateBat(String FileNameBAT) throws IOException {
		SaveSettings((String) Pool.getSelectedItem());
		File file=new File(FileNameBAT);
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dataOutputStream = new DataOutputStream(fos);
		
		if(InfiniteLoop.isSelected()) {
			dataOutputStream.writeBytes(":infiniteloop");
			dataOutputStream.writeBytes(System.getProperty("line.separator"));
		}
		
		
		dataOutputStream.writeBytes(CommandOutput);
		
		
		if(Pause.isSelected()) {
			dataOutputStream.writeBytes(System.getProperty("line.separator"));
			dataOutputStream.writeBytes("pause");
		}
		
		if(InfiniteLoop.isSelected()) {
			dataOutputStream.writeBytes(System.getProperty("line.separator"));
			dataOutputStream.writeBytes("goto infiniteloop");
		}
		dataOutputStream.close();
	}

	private void CloseMinersCommand(JFrame win) {
		try {
		 		Runtime.getRuntime().exec("taskkill /F /FI \"IMAGENAME eq cpuminer*\"");
		 		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		 		JOptionPane.showMessageDialog(win,
	 			    "All your miners closed.",
	 			    "Close Miners",
	 			    JOptionPane.WARNING_MESSAGE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	
	private void StartMiningAction(JFrame win) {
		try {
 		  CreateBat("start_mining.bat");
 		  String path="cmd /c start start_mining.bat";
 		  Runtime rn=Runtime.getRuntime();
 		  rn.exec(path);
 		  if (BackgroundMode.isSelected()==true) {
 			 JOptionPane.showMessageDialog(win,
			    "You start miner in background!",
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
 		  }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void StartBenchmark(JFrame win) {
		try {
			SaveSettings((String) Pool.getSelectedItem());
			String path="cmd /c start \"\" " + CommandOutput + " --benchmark ";
			Runtime rn=Runtime.getRuntime();
	 		rn.exec(path);
	 		 if (BackgroundMode.isSelected()==true) {
	 			 JOptionPane.showMessageDialog(win,
				    "You start miner in background!",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
	 		  }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void SaveBATFile(JFrame window) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save Destination...");   
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setSelectedFile(new File("start_mining.bat"));
		fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setFileFilter(new FileNameExtensionFilter("bat","bat"));
		
		if(fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
		    String filename = fileChooser.getSelectedFile().toString();
		    if (!filename.toString().endsWith(".bat"))
		        filename += ".bat";
		    try {
				CreateBat(fileChooser.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private void SetDefaultSettings() {
		ShowCPU.setSelected(false);
		NoColor.setSelected(false);
		HideMinerStats.setSelected(false);
		BackgroundMode.setSelected(false);
		NumberThreads.setSelectedIndex(cores-1);
		ApportionCPU.setSelected(false);
		Alghorytm.setSelectedIndex(0);
		WalletAdress.setText("");
		NamePC.setText("");
		CPUPriority.setSelectedIndex(2);
		DebugCheck.setSelected(false);
		Pause.setSelected(false);
		InfiniteLoop.setSelected(false);
		Password.setText("");
		TimeStratum.setValue(300);
		boolean isPoolCustom = true;
		for (int i=0;i<DefaultPoolsList.length;i++) {
			if (DefaultPoolsList[i][0].equals(LastPool.name())) {
				isPoolCustom = false;
			}
		}
		if (isPoolCustom == true) PoolURL.setText("");
		AditionalParameters.setText("");
	}

	private void ChangeProfile(String profile_name) {
		ProfilePrefs = Preferences.userRoot().node("cpu-miner-batch-configurator").node(profile_name);
		
	}
	
	private void LoadPoolList() {
		//custom pools
		//stored pools 
		int CountSavedPools = 0;
		try {
			CountSavedPools = ProfilePrefs.childrenNames().length;
			
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		if (CountSavedPools>0) {
			String[] SavedPools = null;
			try {
				SavedPools = ProfilePrefs.childrenNames();
			} catch (BackingStoreException e) {
				e.printStackTrace();
			}
			
			int found = 0;
			
			for (int i=0;i<CountSavedPools;i++) {
				found = 0;
				for (int j=0;j<DefaultPoolsList.length;j++) {
					if (SavedPools[i].equals(DefaultPoolsList[j][0])) {
						found = 1;
					}
				}
				if (found==0) {
					String tmp_poolname = SavedPools[i];
					if (tmp_poolname!="") {
						Pool.addItem(tmp_poolname);
					}
				}
			}
		}
				
		//default pools
		for (int i=0;i<DefaultPoolsList.length;i++) {
			Pool.addItem(DefaultPoolsList[i][0]);
		}
		
		Pool.removeItemAt(0);

		LoadLastPool();		
	}
	
	private void LoadLastPool() {
		String LastPoolName = ProfilePrefs.get("lastpool", PoolDefault);
		if (LastPoolName == null | LastPoolName == "") LastPoolName = PoolDefault;
		LastPool = ProfilePrefs.node(LastPoolName);

		Pool.setSelectedItem(LastPoolName);
		
		try {
			LoadSettings();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//***do action
	}
				
	private void LoadSettings() throws InterruptedException {
		ShowCPU.setSelected(LastPool.getBoolean("showcpu", false));
		NoColor.setSelected(LastPool.getBoolean("nocolor", false));
		HideMinerStats.setSelected(LastPool.getBoolean("hidedif", false));
		BackgroundMode.setSelected(LastPool.getBoolean("backgroundmode", false));
		NumberThreads.setSelectedIndex(LastPool.getInt("numberthreads", cores-1));
		ApportionCPU.setSelected(LastPool.getBoolean("apportioncpu", false));
		Alghorytm.setSelectedIndex(LastPool.getInt("alghorytm", 0));
		WalletAdress.setText(LastPool.get("walletadress", ""));
		NamePC.setText(LastPool.get("namepc", ""));
		CPUPriority.setSelectedIndex(LastPool.getInt("cpupriority", 2));
		DebugCheck.setSelected(LastPool.getBoolean("debug", false));
		Pause.setSelected(LastPool.getBoolean("pause", false));
		InfiniteLoop.setSelected(LastPool.getBoolean("infiniteloop", false));
		Password.setText(LastPool.get("password", ""));
		TimeStratum.setValue(Integer.parseInt(LastPool.get("timestratum", "300")));	
		AditionalParameters.setText(LastPool.get("aditionalparameters", ""));
		
		boolean isPoolCustom = true;
		for (int i=0;i<DefaultPoolsList.length;i++) {
			if (DefaultPoolsList[i][0].equals(LastPool.name())) {
				isPoolCustom = false;
				//registration
				Registration.setVisible(true);
				if (DefaultPoolsList[i][2].equals("No")) {
					LastPoolRegistrationURL = "";
					Registration.setEnabled(false);
				} else {
					LastPoolRegistrationURL = DefaultPoolsList[i][2];
					Registration.setEnabled(true);
				}
				//poolurl
				PoolURL.setText(DefaultPoolsList[i][1]);
				PoolURL.setEnabled(false);
				//setwallet or username
				WalletAdressLabel.setText(DefaultPoolsList[i][5]);
				//ping
				Ping.setVisible(false);
				//Ping.setVisible(true);
				//Ping.setText(ping(DefaultPoolsList[i][3]));
			}
		}
		
		if (isPoolCustom == true) {
			LastPoolRegistrationURL = "";
			Registration.setEnabled(false);
			Registration.setVisible(false);
			PoolURL.setEnabled(true);
			PoolURL.setText(LastPool.get("poolurl", ""));
			WalletAdressLabel.setText("Wallet/Username");
			Ping.setVisible(false);
		}
		
		
		GetProcessorType();
	}
	
	private void SaveSettings(String LastPoolName) {	
		//сохранение выбранного профиля в будущем
		Preferences.userRoot().node("cpu-miner-batch-configurator").put("lastprofilename", "default");
		
		if (LastPoolName == null | LastPoolName == "") LastPoolName = PoolDefault;
		ProfilePrefs.put("lastpool", LastPoolName);
		LastPool = ProfilePrefs.node(LastPoolName);
				
		SavePool();
	}
	
	private void SavePool() {
		LastPool.put("poolurl", PoolURL.getText());
		LastPool.putBoolean("showcpu", ShowCPU.isSelected());
		LastPool.putBoolean("nocolor", NoColor.isSelected());
		LastPool.putBoolean("apportioncpu", ApportionCPU.isSelected());
		LastPool.putBoolean("hidedif", HideMinerStats.isSelected());
		LastPool.putBoolean("backgroundmode", BackgroundMode.isSelected());
		LastPool.putInt("numberthreads", NumberThreads.getSelectedIndex());
		LastPool.putInt("alghorytm", Alghorytm.getSelectedIndex());
		LastPool.put("walletadress", WalletAdress.getText());
		LastPool.put("namepc", NamePC.getText());
		LastPool.putInt("cpupriority", CPUPriority.getSelectedIndex());
		LastPool.putBoolean("debug", DebugCheck.isSelected());
		LastPool.putBoolean("pause", Pause.isSelected());
		LastPool.putBoolean("infiniteloop", InfiniteLoop.isSelected());
		LastPool.put("password", Password.getText());
		LastPool.put("timestratum", TimeStratum.getValue().toString());		
		LastPool.put("aditionalparameters", AditionalParameters.getText());
	}

	@SuppressWarnings("unused")
	private void ExportSettings() {
		try {
			 log("save\\/");
           //ProfilePrefs.exportNode(new FileOutputStream(SettingsName + ".xml"));
           ProfilePrefs.exportSubtree(System.out);
           log("save/\\");
       }
       catch(Exception e) {
           e.printStackTrace();
       }
	}
	
	@SuppressWarnings("unused")
	private void ImportSettings(){
		try {
           // Preferences.importPreferences(new BufferedInputStream(new FileInputStream(SettingsName + ".xml")));
           /// ProfilePrefs = Preferences.userRoot().node(SettingsName);
            log("load\\/");
            //ProfilePrefs.exportSubtree(System.out);
            log("load/\\");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	private void GetProcessorType() {
		/*System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
		System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
		System.out.println(System.getenv("PROCESSOR_ARCHITEW6432"));
		System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));*/
		/*SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		CentralProcessor cpu = hal.getProcessor();
		CentralProcessor.ProcessorIdentifier processorIdentifier = cpu.getProcessorIdentifier();
		log(processorIdentifier.getMicroarchitecture());*/
		//SystemInfo o = new oshi.SystemInfo();
		//log(o.getHardware().getProcessor();
	}
	
	private void UpdateCommand() {
		String CommandText = "";
		
		String MinerPath = "";
		try {
			MinerPath = "\"" + new File(".").getCanonicalPath() + "\\cpuminer-bin\\cpuminer-sse2.exe\"";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CommandText = CommandText + MinerPath + " ";
		
		
		CommandText = CommandText + "-o " + PoolURL.getText() + " ";
		
		
		CommandText = CommandText + "-a " + Alghorytm.getSelectedItem().toString() + " ";
	
		if (WalletAdress.getText().length()>0) {
			if (NamePC.getText().length()==0) {
				CommandText = CommandText + "-u " + WalletAdress.getText() + " ";
			} else {
				CommandText = CommandText + "-u " + WalletAdress.getText() + "." + NamePC.getText() + " ";
			}
		}
		
		if (Password.getText().length()>0) {
			CommandText = CommandText + "-p " + Password.getText() + " ";
		}
		
		if (NumberThreads.getSelectedIndex()!=(cores-1)) {
			CommandText = CommandText + "-t "+ NumberThreads.getSelectedItem() + " ";
			if(ApportionCPU.isSelected()) {
				CommandText = CommandText + "--cpu-affinity 0 ";
			}
			ApportionCPU.setEnabled(true);
		} else {
			ApportionCPU.setSelected(false);
			ApportionCPU.setEnabled(false);
		}
		
		CommandText = CommandText + "--cpu-priority " + CPUPriority.getSelectedIndex() + " ";
				
		CommandText = CommandText + "-T " + TimeStratum.getValue() + " ";
		
		if (ShowCPU.isSelected()) {
			CommandText = CommandText + "--hash-meter ";
		}
	
		if(NoColor.isSelected()) {
			CommandText = CommandText + "--no-color ";
		}
		if(HideMinerStats.isSelected()) {
			CommandText = CommandText + "--q ";
		}
		if(BackgroundMode.isSelected()) {
			CommandText = CommandText + "-B ";
		}
		
		if(DebugCheck.isSelected()) {
			CommandText = CommandText + "-D ";
		}
		
		CommandText = CommandText + AditionalParameters.getText();
		
				
		CommandOutput = CommandText;
	}
	
	private static void log(String s) {
		System.out.println(s);
	}
}
