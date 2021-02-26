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
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultFormatter;

import org.apache.commons.lang3.ArrayUtils;



public class YENTEN_2CH_CPUMINER_BATCH_CONFIGURATOR {
	private JCheckBox ShowCPU;
	private JCheckBox NoColor;
	private JComboBox<String> NumberThreads;
	private JComboBox<String> Alghorytm;
	private JComboBox<String> Pool;
	private JComboBox<String> CPUPriority;
	private JComboBox<String> WalletAdress;
	private JTextField NamePC;
	private JCheckBox ApportionCPU;
	private JCheckBox HideMinerStats;
	private JCheckBox BackgroundMode;
	private JCheckBox DebugCheck;
	
	private JButton DefaultSettings;
	private JToggleButton StartMining;
	private JButton AddPool;
	
	private String CommandOutput;
		
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
	
	private JButton AddWallet;
	private JButton DelPool;
	private JButton DelWallet;
	private String WalletDefault;
	private String PoolDeleted;
	private Preferences AppPrefs;
	private String PoolURLDefaultText;
	private String PoolURLCustomPoolText;
	private String DelPoolDefaultText;
	private String DelPoolToolTipCustom;
	private String TextMinerInBackground;
	private String TextSaveDestination;
	private String TextDeleteWallet;
	private String TextDeletePool;
	
	private JToggleButton ExpertMode;	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	private JToggleButton SimpleFormExpertMode;
	
	private JToggleButton SimpleFormStartMining;
	private JTextField SimpleFormWalletAdress;
	private JLabel SimpleFormWalletLabel;
	private JButton SimpleFormStats;
	private JTextArea SimpleFormOutput;
	private JScrollPane scrollPane1;
	private boolean CanStartMining;
	
	private RunMining MiningRuntime;
	private Thread MiningThread;
	private String TextWalletNotFound;
	
	public YENTEN_2CH_CPUMINER_BATCH_CONFIGURATOR() {
		JFrame wid = new JFrame();
		wid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wid.setTitle("YENTEN 2CH CPUMINER BATCH CONFIGURATOR");
		ImageIcon img = new ImageIcon("logo.png");
		wid.setIconImage(img.getImage());
		
		JFrame wid_sf = new JFrame();
		wid_sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wid_sf.setTitle("YENTEN 2CH CPUMINER BATCH CONFIGURATOR");
		ImageIcon img2 = new ImageIcon("logo.png");
		wid_sf.setIconImage(img2.getImage());
		
		Init(wid,wid_sf);
	}
	
	private void Init(final JFrame window, final JFrame simpleform) {
		PoolDefault = "YENTEN-POOL";
		 
		int cores = Runtime.getRuntime().availableProcessors();
		String[] NumberCoresValues = new String[cores];
		for(int i=1;i<=cores;i++) {
			NumberCoresValues[i-1] =  i+"";
		}

		String[] AlghorytmsList = new String[] {"yespowerR16"};
		String[] PriorityList = new String[] {"[0] IDLE","[1] BELOW NORMAL","[2] NORMAL","[3] ABOVE NORMAL","[4] HIGH","[5] REALTIME"};
		String[] CoinsTypeList = new String[] {"YTN"};
		
		//createform
		StartMining = new JToggleButton();  
		DefaultSettings = new JButton();
		AddPool = new JButton("+");
		AddWallet = new JButton("+");
		DelPool = new JButton("-");
		DelWallet = new JButton("-");
		WalletAdress = new JComboBox<String>();
		Benchmark = new JButton();
		DebugCheck = new JCheckBox();
		ShowCPU = new JCheckBox();
		NoColor = new JCheckBox();
		ApportionCPU = new JCheckBox();
		HideMinerStats = new JCheckBox();
		BackgroundMode = new JCheckBox();
		NumberThreads = new JComboBox<String>(NumberCoresValues);
	    NumberThreadsLabel = new JLabel();
	    Alghorytm = new JComboBox<String>(AlghorytmsList);
	    AlghorytmLabel = new JLabel ();
	    Pool = new JComboBox<String>();
	    PoolLabel = new JLabel ();
	    CPUPriority = new JComboBox<String>(PriorityList);
	    CPUPriorityLabel = new JLabel ();
	    WalletAdressLabel = new JLabel();
	    NamePC = new JTextField();
	    NamePCLabel = new JLabel();
	    Pause = new JCheckBox();
		InfiniteLoop = new JCheckBox();
		Password = new JTextField();
		PasswordLabel = new JLabel();
		TimeStratumLabel = new JLabel();
		TimeStratum = new JSpinner(new SpinnerNumberModel(300, 30, null, 30));
		SaveBAT = new JButton();
		CloseMiners = new JButton();
		Registration = new JButton();
		CoinType = new JComboBox<String>(CoinsTypeList);
		CoinTypeLabel = new JLabel();
		PoolURL = new JTextField();
		PoolURLLabel = new JLabel();
		AditionalParameters = new JTextField();
		AditionalParametersLabel = new JLabel();
		Ping = new JLabel();
		Solo = new JCheckBox();
		Profiles = new JButton();
		ExpertMode = new JToggleButton();
		
		
		Solo.setEnabled(false);
		Profiles.setEnabled(false);
		CloseMiners.setEnabled(false);
		
		
		WalletDefault = "Select or Add new Wallet/Username...";
		
		BackgroundMode.setText				("Background mode");
		AlghorytmLabel.setText				("Alghorytm");
		HideMinerStats.setText				("Don't display Miner Stats");
		ApportionCPU.setText				("Apportion load CPU");
		NumberThreadsLabel.setText			("Number of threads");
		NoColor.setText						("Disable colored output");
		ShowCPU.setText						("Show CPU hashmeter output");
		DebugCheck.setText					("Debug");
		Benchmark.setText					("Start Benchmark");
		DefaultSettings.setText				("Default Settings");
		StartMining.setText					("Start Mining");
		PoolLabel.setText					("Pool");
		WalletAdressLabel.setText			("Wallet/Username");
		NamePCLabel.setText					("PC Name");
		Pause.setText						("Pause after crash");
		CPUPriorityLabel.setText			("Miner Priority");
		InfiniteLoop.setText				("Infinite Loop");
		TimeStratumLabel.setText			("Timeout/Stratum (sec)");
		PasswordLabel.setText				("Password");
		SaveBAT.setText						("Save BAT File");
		AditionalParametersLabel.setText	("Adtitional parameters");
		Solo.setText						("Solo");
		CoinTypeLabel.setText				("CoinType");
		Registration.setText				("Pool Registration");
		Ping.setText						("Pool ping: None");
		Profiles.setText					("Profiles...");
		PoolURLLabel.setText				("Mining URL");
		CloseMiners.setText					("Close All Miners");
		ExpertMode.setText					("ExpertMode");
		
		
		PoolURLDefaultText = "You caan't change default pool name.";
		PoolURLCustomPoolText = "Enter Pool URL to connect as stratum+tcp://pool.com:port";
		DelPoolDefaultText = "Can't delete the default pool";
		DelPoolToolTipCustom = "Delete the pool";
		
		TextMinerInBackground = "You start miner in background!";
		TextSaveDestination = "Save Destination...";
		TextDeleteWallet = "Do you want delete your current wallet address - ";
		TextDeletePool = "Do you want delete your current pool - ";
		TextWalletNotFound = "Wallets not found. Please start wallet application and enter wallet adress in wallet address field.";
		
		ApportionCPU.setToolTipText("CPU Load will be apportioned evenly across all threads");
		WalletAdress.setToolTipText("<html>Enter your Wallet Adress or your Worker Username<br>"
				+ "You can find the wallet address in your wallet application menu <b>File->Address to receiver</b></html>");
		Benchmark.setToolTipText("Test your PC");
		AddPool.setToolTipText("Add new Pool");
		DebugCheck.setToolTipText("Output cpuminer debug info");
		StartMining.setToolTipText("Launch cpuminer with current settings");
		DefaultSettings.setToolTipText("Clear all fields");
		BackgroundMode.setToolTipText("Run miner without console");
		NumberThreads.setToolTipText("Number of threads to mining");
		Alghorytm.setToolTipText("Alghorytm");
	    Pool.setToolTipText("Select Pool or Add New Pool Adress");
	    CPUPriority.setToolTipText("Set cpuminer process priority");
	    NamePC.setToolTipText("Enter any worker nick");
	    Pause.setToolTipText("if cpuminer will crash set not close console");
		InfiniteLoop.setToolTipText("if cpuminer will crash he will continue working");
		Password.setToolTipText("Enter pool password or user password or nothing");
		TimeStratum.setToolTipText("Set timeout of one long job calculation and stratum");
		SaveBAT.setToolTipText("Save BAT File on computer");
		CloseMiners.setToolTipText("Close All Running Miners");
		
		PoolURLLabel.setLabelFor(PoolURL);
		PoolLabel.setLabelFor(Pool);
		WalletAdressLabel.setLabelFor(WalletAdress);
		AlghorytmLabel.setLabelFor(Alghorytm);
		NamePCLabel.setLabelFor(NamePC);
		NumberThreadsLabel.setLabelFor(NumberThreads);
		CPUPriorityLabel.setLabelFor(CPUPriority);
		PasswordLabel.setLabelFor(Password);
		TimeStratumLabel.setLabelFor(TimeStratum);
		
		WalletAdress.addItem(WalletDefault);
		
		NoColor.setMargin(new Insets(2, 0, 2, 2));
		StartMining.setBackground(new Color(204, 204, 204));
		DefaultSettings.setBackground(new Color(204, 204, 204));
		Benchmark.setBackground(new Color(204, 204, 204));
		SaveBAT.setBackground(new Color(204, 204, 204));
		CloseMiners.setBackground(new Color(204, 204, 204));
		Registration.setBackground(new Color(204, 204, 204));
		Registration.setMargin(new Insets(2, 2, 2, 2));
		Profiles.setBackground(new Color(204, 204, 204));
		AddWallet.setMargin(new Insets(2, 2, 2, 2));
		AddWallet.setBackground(new Color(204, 204, 204));
		DelPool.setBackground(new Color(204, 204, 204));
		DelPool.setIcon(null);
		DelPool.setMargin(new Insets(2, 0, 2, 0));
		DelWallet.setMargin(new Insets(2, 2, 2, 2));
		DelWallet.setBackground(new Color(204, 204, 204));
		AddPool.setMargin(new Insets(2, 0, 2, 0));
		AddPool.setBackground(new Color(204, 204, 204));
		AddPool.setIcon(null);
		PoolURL.setDisabledTextColor(Color.black);
		PoolURL.setBorder(BorderFactory.createLineBorder(Color.gray));
		ExpertMode.setMargin(new Insets(2, 2, 2, 2));
		ExpertMode.setBackground(new Color(204, 204, 204));
		
		NumberThreads.setSelectedItem(NumberCoresValues.length-1);
		
		//create simple form
		SimpleFormStartMining = new JToggleButton();
		SimpleFormWalletAdress = new JTextField();
		SimpleFormExpertMode = new JToggleButton();
		SimpleFormWalletLabel = new JLabel();
		SimpleFormStats = new JButton();
		SimpleFormOutput = new JTextArea();

		SimpleFormStartMining.setText("Start Mining");
		SimpleFormExpertMode.setText("Expert Mode");
		SimpleFormWalletLabel.setText("Wallet");
		SimpleFormWalletLabel.setLabelFor(SimpleFormWalletAdress);
		SimpleFormStats.setText("Stats");
		SimpleFormOutput.setText("Enter Wallet Address");

		
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setViewportView(SimpleFormOutput);
		SimpleFormOutput.setBackground(UIManager.getColor("Button.background"));
		SimpleFormOutput.setEditable(false);
		SimpleFormOutput.setBorder(null);
		SimpleFormOutput.setAutoscrolls(false);
		CanStartMining = true;
				
		 WindowInterfacePlacement(window);
		 SimpleFormWindowPlacement(simpleform);
		 
		 //load settings
		 SetDefaultPoolsList();
		 
		 AppPrefs = Preferences.userRoot().node("cpu-miner-batch-configurator");
		 ProfileName = AppPrefs.get("lastprofilename", "default");
		 ChangeProfile(ProfileName);
		 
		 LoadWalletsList();
		 
		 LoadPoolList();
		 	       
		 
		 ShowExpertMode(window, simpleform);
		 LoadSettingsSimpleForm(window, simpleform);
		 
		 AddAllEEventsListeners(window, simpleform);
		 
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
	}

	private void SimpleFormSetWallet(String wallet, JFrame win, JFrame simpleform) {
		if ( ! wallet.equals("null") ) {
			SimpleFormWalletAdress.setEditable(false);
			SimpleFormWalletAdress.setText(wallet);
			SimpleFormOutput.setText("Enter Wallet Address");
		} else {
			boolean isExpertMode = ProfilePrefs.getBoolean("expertmode", false);
			if (isExpertMode == true) {
				JOptionPane.showMessageDialog(win,
					    TextWalletNotFound,
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(simpleform,
						TextWalletNotFound,
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			SimpleFormWalletAdress.setEditable(true);
			SimpleFormOutput.setText("Press Start");
		}
	}

	private void ShowExpertMode(JFrame win, JFrame simpleform) {
		boolean isExpertMode = ProfilePrefs.getBoolean("expertmode", false);
		ExpertMode.setSelected(isExpertMode);
		SimpleFormExpertMode.setSelected(isExpertMode);
		win.setVisible(isExpertMode);
		simpleform.setVisible( ! isExpertMode );
		
		if (isExpertMode == false) {
			LoadSettingsSimpleForm(win, simpleform);
			SaveSettings((String) Pool.getSelectedItem());
		} else {
			LoadLastPool();
			SaveSettingsSimpleForm();
		}
	}

	private void ToggleExpertMode() {
		ProfilePrefs.putBoolean("expertmode", ! ProfilePrefs.getBoolean("expertmode", false));
	}
	
	private void SaveSettingsSimpleForm() {
		ProfilePrefs.put("simpleformwalletadress", SimpleFormWalletAdress.getText());
	}

	private void LoadSettingsSimpleForm(JFrame win,JFrame simplewin) {
		String LoadedWallet = ProfilePrefs.get("simpleformwalletadress", "");
		SimpleFormWalletAdress.setText( LoadedWallet );
		SimpleFormOutput.setText("Press Start");
		if (LoadedWallet.equals("")) {
			SimpleFormSetWallet(FindWalletAdresses().split("@")[0] , win, simplewin);
		} else {
			SimpleFormWalletAdress.setEditable(false);
		}
		
	}
	
	@SuppressWarnings("resource")
	private String FindWalletAdresses() {
		String DataDir = WindowsReqistry.readRegistry("HKEY_CURRENT_USER\\Software\\Yenten\\Yenten-Qt", "strDataDir");
        
        if (DataDir != null) {
        	DataDir = DataDir.trim();
            File walletdat1 = new File(DataDir+"\\wallet.dat"); 
            File walletdat2 = new File(DataDir+"\\wallets\\wallet.dat"); 
           
            if (walletdat1.exists() == false && walletdat2.exists() == false) {
            	return "null";
            }
            
            File walletDat;
            if (walletdat1.exists()) {
            	walletDat = walletdat1;
            } else {
            	walletDat = walletdat2;
            }
            
            try {
	            String	result = null; //default
	            
	            BufferedReader br = new BufferedReader(new FileReader(walletDat)); 
	            	            
	            ArrayList<String> myList = new ArrayList<String>();
	            try {     	
	            	///инициализаци€
	            	char[] buff = new char[7];
	            	char[] adressstring = new char[54];
	            	int symbol = 0;
	            	//подготовка буфера
	            	for (int i=0;i<7;i++) {
	            		symbol = br.read();
	            		buff[i] = ((char)symbol);
	            	}
	                String string = new String(buff);
	                
	                //читаем файл по байтово
	                while (symbol != -1) {
	                	symbol = br.read();
	                	//сдвигаем буфер на 1 и заносим симввол в конец 
	                	buff[0] = buff[1];
	                	buff[1] = buff[2];
	                	buff[2] = buff[3];
	                	buff[3] = buff[4];
	                	buff[4] = buff[5];
	                	buff[5] = buff[6];
	                	buff[6] = ((char)symbol);
	                	string = new String(buff);
	                	
	                	//если найдена строчка с ключевого слова, возможно кошелек
	                	if (string.indexOf("receive")!=-1) {
	                		//читааем длину по размеру записи кошелька
	                		for (int i=0;i<47;i++) {
	                			adressstring[i] = (char) br.read();
	                		}
	                		String temp_wallet = new String(adressstring);
	                		//если найдено второе ключевое слово - кошелек найден
	                		if (temp_wallet.substring(5, 12).equals("purpose") ) {
	                			myList.add(temp_wallet.substring(13).trim());
	                		}
	                	}
	                }
	                
	                //формируем гЋист (список с адресами кошельков)
					if (myList.size() > 0) {
						result = "";
						for (int i=0;i<myList.size();i++) {
							result = result+myList.get(i);
							if (i<myList.size()-1) {
								result = result + "@";
							}
						}
						return result;//возвращаем список кошельков разделенных @
		            } else {
		            	return "null";//если кошельков 0
		            }
				} catch (IOException e) {
					return "null";//файл не открываетс€
				} 
          } catch (FileNotFoundException e) {
            return "null";//файл не существует
          }
        } else {
        	return "null";//если не найдена директори€
        }
	}
	

	private void UpdateStartMiningButtons(JFrame win,JFrame simplewin){
		if (SimpleFormWalletAdress.getText().length()==34 | win.isVisible()) {
			SimpleFormStartMining.setSelected(CanStartMining);
			StartMining.setSelected(CanStartMining);
			if (CanStartMining == true) {
				SimpleFormStartMining.setText("Stop Mining");
				StartMining.setText("Stop Mining");
				SimpleFormOutput.setText("Mining started!");
		        CanStartMining = false;
			} else {
				SimpleFormStartMining.setText("Start Mining");
				StartMining.setText("Start Mining");
				SimpleFormOutput.setText("Mining stoped. Press Start for continue");
				CanStartMining = true;
			}
		} else {
			SimpleFormStartMining.setSelected(false);
			StartMining.setSelected(false);
			JOptionPane.showMessageDialog((win.isVisible()?win:simplewin),
	 			    "Please enter valid Wallet Address",
	 			    "Error Wallet",
	 			    JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void SimpleFormStartMiningAction(JFrame win,JFrame simplewin) {
		if (SimpleFormWalletAdress.getText().length()==34) {
			if (CanStartMining == true) {
				//action
				MiningRuntime = new RunMining();
				MiningThread = new Thread(MiningRuntime);
				MiningThread.start();
			} else {
				//stop mining
				MiningRuntime.threadactive = false;
				MiningThread.interrupt();
				CloseMinersCommand(simplewin);
			}
		}
	}
	
	class RunMining implements Runnable {
		public boolean threadactive;
	    @Override
	    public void run() {
	    	
	    	try {
	    		SimpleFormCreateBat("start_mining.bat");
		   		String path="start_mining.bat";
		   		Process p;
		   		Runtime rt = Runtime.getRuntime();
		   		p = rt.exec(path);
	   		 String line = " ";
	   		 InputStreamReader input = new InputStreamReader(p.getInputStream());
	   		 
	   		 BufferedReader buffer = new BufferedReader(input);
	   		
	   		 threadactive = true;
				while (threadactive == true) {	
					try {
					if ((line = buffer.readLine()) != null) {
						line = line.trim();
						
						if (line.indexOf("New")!=-1) {
							line = line.substring(line.indexOf("New")-1,line.length()-4);
							line = line.replaceAll("[^a-zA-Z0-9 .,:]", "");
							//SimpleFormOutput
							SimpleFormOutput.setText(line);
							SimpleFormOutput.update(SimpleFormOutput.getGraphics());
						}
					}
					
					Thread.sleep(1000);
					} catch (InterruptedException e) {
						input.close();
						buffer.close();
						p.destroyForcibly();
						threadactive = false;
					}
				 }
				input.close();
				buffer.close();
				p.destroyForcibly();
				
			} catch (IOException e) {
				     
				
			}
	    }
	}
	
	private void StartMiningAction(JFrame win) {
		if (CanStartMining == true) {
			try {
	 		  CreateBat("start_mining.bat");
	 		  String path="cmd /c start start_mining.bat";
	 		  Runtime rn=Runtime.getRuntime();
	 		  rn.exec(path);
	 		  if (BackgroundMode.isSelected()==true) {
	 			 JOptionPane.showMessageDialog(win,
				    TextMinerInBackground,
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
	 		  }
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else {
			CloseMinersCommand(win);
		}
	}
	
	private void SimpleFormCreateBat(String FileNameBAT) throws IOException {
		SaveSettingsSimpleForm();
		
		//make command
		String CommandText = "";
		
		String MinerPath = "";
		try {
			MinerPath = "\"" + new File(".").getCanonicalPath() + "\\cpuminer-bin\\cpuminer-sse2.exe\" ";
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CommandText = CommandText + MinerPath;
		
		CommandText = CommandText + "-o stratum+tcp://cpu-pool.com:63368 ";
		
		CommandText = CommandText + "-a yespowerR16 ";
	
		CommandText = CommandText + "-u " + SimpleFormWalletAdress.getText() + " ";
						
		CommandText = CommandText + "-T 1000 ";
		
		CommandText = CommandText + "-q ";
					
		CommandOutput = CommandText;
		
		File file=new File(FileNameBAT);
		
		
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dataOutputStream = new DataOutputStream(fos);
				
		dataOutputStream.writeBytes(System.getProperty("line.separator"));
		
		dataOutputStream.writeBytes(CommandOutput);
				
		dataOutputStream.close();
	}
	
	private void OpenSimpleFormStatsURL() {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		    try {
				Desktop.getDesktop().browse(new URI("http://cpu-pool.com/workers/"+SimpleFormWalletAdress.getText()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void SetDefaultPoolsList() {
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
	}
	
	private void AddAllEEventsListeners(JFrame win,JFrame simplewin) {
		  win.addWindowListener(new WindowAdapter() {
	    	 @Override
	    	 public void windowClosing(WindowEvent e) {
	    		 SaveSettings((String) Pool.getSelectedItem());
	    		 SaveSettingsSimpleForm();
	    		 CanStartMining = true;
	    		// CloseMinersCommand(win);
	    	     System.exit(0);
	    	 }
	     });
		  
		 simplewin.addWindowListener(new WindowAdapter() {
	    	 @Override
	    	 public void windowClosing(WindowEvent e) {
	    		 SaveSettings((String) Pool.getSelectedItem());
	    		 SaveSettingsSimpleForm();
	    		 CanStartMining = true;
	    		 CloseMinersCommand(simplewin);
	    	     System.exit(0);
	    	 }
	     });
		  
		 SimpleFormExpertMode.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	  	ToggleExpertMode();
            	  	ShowExpertMode(win, simplewin);
              }
          });
		 
		 SimpleFormWalletAdress.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  SaveSettingsSimpleForm();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  SaveSettingsSimpleForm();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  SaveSettingsSimpleForm();
			  }
		 });
		 
		 SimpleFormStats.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 OpenSimpleFormStatsURL();
			 }
		 });
		 
		SimpleFormWalletAdress.addActionListener(new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent event) {
				  SaveSettingsSimpleForm();
			  }
		});
		 
		
		SimpleFormStartMining.addActionListener(new java.awt.event.ActionListener() {
			  public void actionPerformed(ActionEvent event) {
					SimpleFormStartMiningAction(win,simplewin);
					UpdateStartMiningButtons(win, simplewin);
			  }
		});
		
		 ExpertMode.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
            	  	ToggleExpertMode();
            	  	ShowExpertMode(win, simplewin);
              }
          });
		 
		 Pool.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
        	   if(e.getStateChange() == ItemEvent.DESELECTED) {
        		   SaveSettings(e.getItem().toString());
        		   
        	   }
        	   if(e.getStateChange() == ItemEvent.SELECTED) {
        		   SetDefaultSettings();
    			   String LastPoolName = e.getItem().toString();
        		   ProfilePrefs.put("lastpool", LastPoolName);
        		   LastPool = ProfilePrefs.node(LastPoolName);
        		   LoadLastPool();
        	   }
            }
         });
		 
		 StartMining.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	  	StartMiningAction(win);
           	  	UpdateStartMiningButtons( win, simplewin);
             }
         });
		 
		 Registration.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 OpenRegistrationURL();
			 }
		 });
		 
		 DelWallet.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ShowDeleteWalletDialog(win);
			 }
		 });
		 
		 AddWallet.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ShowDialogAddWalletAdress(win);
			 }
		 });
		 
		 DefaultSettings.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	 	SetDefaultSettings();
           	 	SaveSettings((String) Pool.getSelectedItem());
             }
         });
		 
		 AddPool.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ShowAddPoolDialog(win);
				 SetDefaultSettings();
				 SaveSettings((String) Pool.getSelectedItem());
			 }
		 });
		 
		 DelPool.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ShowDialogDeletePool(win);
			 }
		 });
		 
		 WalletAdress.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
        	   if(e.getStateChange() == ItemEvent.SELECTED) {
        		   	if (WalletAdress.getSelectedIndex() == 0) {
        		   		DelWallet.setEnabled(false);
        		   	} else {
        		   		DelWallet.setEnabled(true);
        		   	}
        	   }
            }
         });
		 	 
		 CloseMiners.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 CloseMinersCommand(win);
			 }
		 });
		 
		 SaveBAT.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 SaveBATFile(win);
			 }
		 });
		 
		 Benchmark.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 StartBenchmark(win);
			 }
		 });
		
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
	
	private void SimpleFormWindowPlacement(JFrame win){
		Container contentPane = win.getContentPane();    
		win.setResizable(false);
		 //simple form
        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
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
		win.pack();
        
        win.setLocationRelativeTo(null);
        win.setVisible(false);  
	}
	
	private void WindowInterfacePlacement(JFrame window) {
		Container contentPane = window.getContentPane();    
		 window.setResizable(false);

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
						.addGap(5, 5, 5)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addComponent(AditionalParametersLabel, GroupLayout.Alignment.LEADING)
							.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
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
							.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
								.addGroup(contentPaneLayout.createParallelGroup()
									.addComponent(NumberThreadsLabel)
									.addComponent(WalletAdressLabel)
									.addComponent(NamePCLabel)
									.addComponent(PasswordLabel)
									.addComponent(CPUPriorityLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(contentPaneLayout.createParallelGroup()
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addGroup(contentPaneLayout.createParallelGroup()
											.addComponent(CPUPriority, 0, 200, Short.MAX_VALUE)
											.addComponent(NumberThreads, 0, 200, Short.MAX_VALUE)
											.addComponent(Password, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
											.addComponent(NamePC, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
										.addGap(5, 5, 5)
										.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
											.addComponent(Registration, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
											.addComponent(Ping, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
											.addComponent(ApportionCPU, GroupLayout.Alignment.LEADING)))
									.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
										.addComponent(WalletAdress, 0, 267, Short.MAX_VALUE)
										.addGap(2, 2, 2)
										.addComponent(AddWallet, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addGap(2, 2, 2)
										.addComponent(DelWallet, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))))
							.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
								.addComponent(TimeStratumLabel)
								.addGap(5, 5, 5)
								.addGroup(contentPaneLayout.createParallelGroup()
									.addComponent(TimeStratum, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addComponent(AditionalParameters, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)))
							.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
								.addComponent(PoolLabel)
								.addGap(5, 5, 5)
								.addComponent(Pool, 0, 298, Short.MAX_VALUE)
								.addGap(2, 2, 2)
								.addComponent(AddPool, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(2, 2, 2)
								.addComponent(DelPool, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(Solo))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addComponent(PoolURLLabel)
								.addGap(5, 5, 5)
								.addComponent(PoolURL, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addComponent(CoinTypeLabel)
								.addGap(5, 5, 5)
								.addComponent(CoinType, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(AlghorytmLabel)
								.addGap(5, 5, 5)
								.addComponent(Alghorytm, 0, 156, Short.MAX_VALUE))
							.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
									.addComponent(ExpertMode, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(StartMining, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(contentPaneLayout.createParallelGroup()
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addComponent(SaveBAT, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(DefaultSettings, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addComponent(Benchmark)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(Profiles, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))))
						.addGap(5, 5, 5))
			);
			contentPaneLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {Benchmark, SaveBAT, StartMining});
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
						.addGap(12, 12, 12)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(PoolLabel)
							.addComponent(Pool)
							.addComponent(Solo)
							.addComponent(DelPool, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(AddPool, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(DelWallet, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(AddWallet, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(WalletAdress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(5, 5, 5)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(NamePCLabel)
							.addComponent(NamePC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(Registration, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
						.addGap(5, 5, 5)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(PasswordLabel)
							.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(Ping, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
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
						.addGap(5, 5, 5)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(ExpertMode)
							.addComponent(Benchmark)
							.addComponent(Profiles))
						.addGap(10, 10, 10))
			);
			window.setSize(455, 435);
			 
	        window.pack();
	        
	        window.setLocationRelativeTo(null);
	        window.setVisible(false);  
	}
	
	private String GetEscapedPathString(String inputPath) {
		final int[] illegalChars = {34, 60, 62, 124, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 58, 42, 63, 92, 47};
		
		    Arrays.sort(illegalChars);
		
		
		    StringBuilder cleanName = new StringBuilder();
		    for (int i = 0; i < inputPath.length(); i++) {
		        int c = (int)inputPath.charAt(i);
		        if (Arrays.binarySearch(illegalChars, c) < 0) {
		            cleanName.append((char)c);
		        }
		    }
		    return cleanName.toString();
		
	}
	
	private String getTitle() {
		String Result = ((String)Pool.getSelectedItem()).trim()+
				" ("+CoinType.getSelectedItem()+") ["+
				NumberThreads.getSelectedItem()+" Threads] "+
				CPUPriority.getSelectedItem()+" Priority"+
				(DebugCheck.isSelected()?" -Debug mode":"")+
				(AditionalParameters.getText().length()>0?" ":"")+
				AditionalParameters.getText().trim();
	
		return GetEscapedPathString(Result);
	}
	
	private void CreateBat(String FileNameBAT) throws IOException {
		SaveSettings((String) Pool.getSelectedItem());
		File file=new File(FileNameBAT);
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dataOutputStream = new DataOutputStream(fos);
		
		dataOutputStream.writeBytes("COLOR 0F");
		dataOutputStream.writeBytes(System.getProperty("line.separator"));
		
		dataOutputStream.writeBytes("TITLE "+getTitle());
		
		if(InfiniteLoop.isSelected()) {
			dataOutputStream.writeBytes(System.getProperty("line.separator"));
			dataOutputStream.writeBytes(":infiniteloop");
		}
		
		dataOutputStream.writeBytes(System.getProperty("line.separator"));
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
		 		if (CanStartMining == false) {
			 		JOptionPane.showMessageDialog(win,
		 			    "All your miners closed.",
		 			    "Close Miners",
		 			    JOptionPane.WARNING_MESSAGE);
		 		}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	

	
	private void StartBenchmark(JFrame win) {
		try {
			SaveSettings((String) Pool.getSelectedItem());
			String path="cmd /c start \""+getTitle()+"\" " + CommandOutput + " --benchmark ";
			Runtime rn=Runtime.getRuntime();
	 		rn.exec(path);
	 		 if (BackgroundMode.isSelected()==true) {
	 			 JOptionPane.showMessageDialog(win,
	 				TextMinerInBackground,
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
	 		  }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void SaveBATFile(JFrame window) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(TextSaveDestination);   
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setSelectedFile(new File(getTitle()+".bat"));
		fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setFileFilter(new FileNameExtensionFilter("bat","bat"));
		
		if(fileChooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
		    String filename = fileChooser.getSelectedFile().getAbsolutePath();
		    if ( ! filename.endsWith(".bat"))
		        filename += ".bat";
		    try {
				CreateBat(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private void SetDefaultSettings() {
		ShowCPU.setSelected(true);
		NoColor.setSelected(false);
		HideMinerStats.setSelected(false);
		BackgroundMode.setSelected(false);
		NumberThreads.setSelectedIndex(NumberThreads.getItemCount()-1);
		ApportionCPU.setSelected(false);
		Alghorytm.setSelectedIndex(0);
		WalletAdress.setSelectedIndex(0);
		DelWallet.setEnabled(false);
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
		ProfilePrefs = AppPrefs.node(profile_name);
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
	
	private void ShowDialogAddWalletAdress(JFrame win) {
		String NewAdressName = "";
	    
	    String result = JOptionPane.showInputDialog(win,  "New Wallet/Username", NewAdressName );
	    	    
	    if (result != null) {
		    if (result.length()>0  ) {
		    	boolean isWalletExists = false;
		    	int WalletAdressIndex = 0;
		    	for (int i=0;i<WalletAdress.getItemCount();i++) {
		    		if ( ((String) WalletAdress.getItemAt(i)).equals(result) ) {
		    			isWalletExists = true;
		    			WalletAdressIndex = i;
		    		}
		    	}
		    	if (isWalletExists == false) {
		    		WalletAdress.addItem(result);
		    		WalletAdress.setSelectedIndex(WalletAdress.getItemCount()-1);
		    	} else {
		    		WalletAdress.setSelectedIndex(WalletAdressIndex);
		    	}
		    	
		        UpdateCommand();
		    } 
	    }
		    
	}
	
	private void ShowDeleteWalletDialog(JFrame win) {
		int input = JOptionPane.showConfirmDialog(win, 
                TextDeleteWallet+WalletAdress.getSelectedItem(), "Delete Wallet ", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (input == 0 ) {
			DeleteWallet();
		}
	}
	
	private void ShowDialogDeletePool(JFrame win) {
		int input = JOptionPane.showConfirmDialog(win, 
                TextDeletePool+Pool.getSelectedItem(), "Delete Pool ", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (input == 0 ) {
			DeletePool();
		}
	}
	
	private void DeletePool() {
		try {
			ProfilePrefs.node(LastPool.name()).removeNode();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PoolDeleted = LastPool.name();
	    LoadLastPool();
	    Pool.removeItem(PoolDeleted);
	    
	    PoolDeleted = "";
	}

	private void DeleteWallet() {
		String WalletToDelete = (String) WalletAdress.getSelectedItem();
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
			
			for (int i=0;i<CountSavedPools;i++) {
				String SearchWallet = ProfilePrefs.node(SavedPools[i]).get("walletadress", "");
				if (SearchWallet.equals(WalletToDelete)) {
					ProfilePrefs.node(SavedPools[i]).remove("walletadress");
				}
			}
		}
		WalletAdress.removeItem(WalletToDelete);
		WalletAdress.setSelectedIndex(0);
		DelWallet.setEnabled(false);
	}
	
	private void LoadWalletsList() {
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
			
			String[] WalletsPools = new String[CountSavedPools];
			for (int i=0;i<CountSavedPools;i++) {
				String NewWallet = ProfilePrefs.node(SavedPools[i]).get("walletadress", "");
				WalletsPools[i] = NewWallet;
			}
			
			String[] unique = Arrays.stream(WalletsPools).distinct().toArray(String[]::new);
			unique = ArrayUtils.removeElement(unique, "");
			if (unique.length>0) {
				for(int i= 0; i<unique.length; i++ ) {
					WalletAdress.addItem(unique[i]);
				}
			}
		}
			
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
		
		//Pool.removeItemAt(0);

		LoadLastPool();		
	}
	

	
	private void LoadLastPool() {
		String LastPoolName = ProfilePrefs.get("lastpool", PoolDefault);
		try {
			if (LastPoolName == null | LastPoolName == "" | ProfilePrefs.nodeExists(LastPoolName)==false ) LastPoolName = PoolDefault;
		} catch (BackingStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		ShowCPU.setSelected(LastPool.getBoolean("showcpu", true));
		NoColor.setSelected(LastPool.getBoolean("nocolor", false));
		HideMinerStats.setSelected(LastPool.getBoolean("hidedif", false));
		BackgroundMode.setSelected(LastPool.getBoolean("backgroundmode", false));
		NumberThreads.setSelectedIndex(LastPool.getInt("numberthreads", NumberThreads.getItemCount()-1));
		ApportionCPU.setSelected(LastPool.getBoolean("apportioncpu", false));
		Alghorytm.setSelectedIndex(LastPool.getInt("alghorytm", 0));
		WalletAdress.setSelectedItem(LastPool.get("walletadress", WalletAdress.getItemAt(0)));
		if 	(WalletAdress.getSelectedIndex()==0) {
			DelWallet.setEnabled(false);
		} else {
			DelWallet.setEnabled(true);
		}
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
				//disable delete
				DelPool.setEnabled(false);
				//settooltip pool url
				PoolURL.setToolTipText(PoolURLDefaultText);
				DelPool.setToolTipText(DelPoolDefaultText);
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
			DelPool.setEnabled(true);
			PoolURL.setToolTipText(PoolURLCustomPoolText);
			DelPool.setToolTipText(DelPoolToolTipCustom);
		}
		
		
		GetProcessorType();
	}
	
	private void SaveSettings(String LastPoolName) {	
		//сохранение выбранного профил€ в будущем
		AppPrefs.put("lastprofilename", "default");
		
		if (LastPoolName == null | LastPoolName == "" | PoolDeleted==LastPoolName) LastPoolName = PoolDefault;
		ProfilePrefs.put("lastpool", LastPoolName);
		LastPool = ProfilePrefs.node(LastPoolName);
					
		SavePool();
	}
	
	private void SavePool() {
		String SavedWalletAdress;
		LastPool.put("poolurl", PoolURL.getText());
		LastPool.putBoolean("showcpu", ShowCPU.isSelected());
		LastPool.putBoolean("nocolor", NoColor.isSelected());
		LastPool.putBoolean("apportioncpu", ApportionCPU.isSelected());
		LastPool.putBoolean("hidedif", HideMinerStats.isSelected());
		LastPool.putBoolean("backgroundmode", BackgroundMode.isSelected());
		LastPool.putInt("numberthreads", NumberThreads.getSelectedIndex());
		LastPool.putInt("alghorytm", Alghorytm.getSelectedIndex());
		if (WalletAdress.getSelectedIndex()==0) {
			SavedWalletAdress = "";
			LastPool.remove("walletadress");
		} else {
			SavedWalletAdress = (String) WalletAdress.getSelectedItem();
			LastPool.put("walletadress", SavedWalletAdress);
		}
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
	
		if (WalletAdress.getSelectedIndex()!=0) {
			if (NamePC.getText().length()==0) {
				CommandText = CommandText + "-u " + WalletAdress.getSelectedItem() + " ";
			} else {
				CommandText = CommandText + "-u " + WalletAdress.getSelectedItem() + "." + NamePC.getText() + " ";
			}
		}
		
		if (Password.getText().length()>0) {
			CommandText = CommandText + "-p " + Password.getText() + " ";
		}
		
		if (NumberThreads.getSelectedIndex()!=(NumberThreads.getItemCount()-1)) {
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
