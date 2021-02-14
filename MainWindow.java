package cpuminer.configurer;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;
import java.util.prefs.*;
import static javax.swing.GroupLayout.Alignment.*; 

public class MainWindow {
	private static JFrame window;
	private static JPanel panel;
	private static JButton StartMining;
	private static JCheckBox HideCPU;
	private static JCheckBox NoColor;
	private static JComboBox<String> NumberThreads;
	private static JComboBox<String> Alghorytm;
	private static JComboBox<String> Pool;
	private static JTextField WalletAdress;
	private static JTextField NamePC;
	private static JCheckBox ApportionCPU;
	private static JCheckBox HideDiff;
	private static JCheckBox BackgroundMode;
	private static JComboBox<String> CPUPriority;
	
	private static JButton DefaultSettings;
	
	private static JLabel CommandOutput;
	
	private static int cores;
	private static String[] NumberCoresValues;
	
	private static Preferences userPrefs;
	private static int Loaded = 0;
	
	private static JLabel NumberThreadsLabel;
	private static JLabel WalletAdressLabel;
	private static JLabel AlghorytmLabel;
	private static JLabel PoolLabel;
	private static JLabel NamePCLabel;
	private static JLabel CPUPriorityLabel;
	
	public static void Init() {
		userPrefs = Preferences.userRoot().node("cpu-miner-batch-configurator");
		
		cores = Runtime.getRuntime().availableProcessors();
		
		 window = new JFrame("CPU Miner GUI batch configurer");
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 		 
		 CommandOutput = new JLabel("");
		 
		 StartMining = new JButton("Start Mining");  
		 
		 StartMining.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent e) 
             {
           	  try {
           		  SaveSettings();
           		  CreateBat();
           		  
           		  String path="cmd /c start start_mining.bat";
           		  Runtime rn=Runtime.getRuntime();
           		  rn.exec(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             }
        });
		 
		 DefaultSettings = new JButton("Default");
		 DefaultSettings.addActionListener(new ActionListener()
	        {
	             public void actionPerformed(ActionEvent e) 
	             {
	           	  	SetDefaultSettings();
	             }
	        });
			 
		 
		 HideCPU = new JCheckBox("Hide CPU hashmeter output");
		 
		 NoColor = new JCheckBox("Disable colored output");
		    
		 ApportionCPU = new JCheckBox("Apportion load CPU");
		 
		 HideDiff = new JCheckBox("Don't display diff");
		 
		 BackgroundMode = new JCheckBox("Background mode");
		 
		 NumberCoresValues = new String[cores];
		 for(int i=1;i<=cores;i++) {
			 NumberCoresValues[i-1] =  i+"";
		 }
		 NumberThreads = new JComboBox<String>(NumberCoresValues);
		 NumberThreads.setSelectedIndex(cores-1);
		 NumberThreads.setToolTipText("Number of threads");
	     
	     NumberThreadsLabel = new JLabel("Number of threads");
		 
	     String[] AlghorytmsList = new String[]{"yespowerr16"};
	     Alghorytm = new JComboBox<String>(AlghorytmsList);
	     Alghorytm.setToolTipText("Alghorytm");
	     
	     AlghorytmLabel = new JLabel ("Alghorytm");
	     
	     String[] PoolList = new String[] {"[YENTEN] yenten-pool.info"};
	     Pool = new JComboBox<String>(PoolList);
	     Pool.setToolTipText("Select Pool");
	     Pool.setEditable(true);
	     
	     PoolLabel = new JLabel ("Pool");
	     
	     String[] PriorityList = new String[] {"[0] IDLE","[1] BELOW NORMAL","[2] NORMAL","[3] ABOVE NORMAL","[4] HIGH","[5] REALTIME"};
	     CPUPriority = new JComboBox<String>(PriorityList);
	     CPUPriority.setToolTipText("CPU Priority");
	     CPUPriority.setSelectedIndex(2);
	     
	     CPUPriorityLabel = new JLabel ("Miner Priority");
	     
	     WalletAdress = new JTextField("",24);
	     WalletAdressLabel = new JLabel("Wallet Adress");
	     
	     NamePC = new JTextField("",12);
	     NamePCLabel = new JLabel("PC Name");
	     
	     
		 HideCPU.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });
		 NoColor.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });
		 ApportionCPU.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });
		 HideDiff.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });
		 BackgroundMode.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });
		 NumberThreads.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });
	     Alghorytm.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
        });  
	     Pool.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
	     });
	     CPUPriority.addItemListener(new ItemListener() {
           public void itemStateChanged(ItemEvent e) {
               UpdateCommand();
           }
	     });
	     WalletAdress.addKeyListener(new KeyAdapter() {   
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
	     NamePC.addKeyListener(new KeyAdapter() {   
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
	     
	     window.addWindowListener(new WindowAdapter() {

	    	 @Override

	    	 public void windowClosing(WindowEvent e) {

	    		 SaveSettings();
	    	     System.exit(0);

	    	 }
	     });
	     
	     ComponentsPlacement();

	}
	
	protected static void CreateBat() throws IOException {
		File file=new File("start_mining.bat");
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeBytes(CommandOutput.getText());
	}

	private static void ComponentsPlacement() {

		 Container contentPane = window.getContentPane();    
		 window.setResizable(false);
			
			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addGap(6, 6, 6)
						.addGroup(contentPaneLayout.createParallelGroup()
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addComponent(PoolLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(Pool, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(AlghorytmLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(Alghorytm, 0, 120, Short.MAX_VALUE))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGroup(contentPaneLayout.createParallelGroup()
									.addComponent(WalletAdressLabel)
									.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(CPUPriorityLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(NamePCLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
								.addGap(22, 22, 22)
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addGroup(contentPaneLayout.createParallelGroup()
											.addComponent(NamePC, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
											.addComponent(CPUPriority, 0, 0, Short.MAX_VALUE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(contentPaneLayout.createParallelGroup()
											.addComponent(ApportionCPU, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
											.addGroup(contentPaneLayout.createSequentialGroup()
												.addComponent(NumberThreadsLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(NumberThreads, 0, 61, Short.MAX_VALUE))))
									.addComponent(WalletAdress, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
											.addComponent(StartMining, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
											.addGroup(contentPaneLayout.createParallelGroup()
												.addComponent(HideDiff)
												.addComponent(HideCPU)))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
											.addGroup(contentPaneLayout.createSequentialGroup()
												.addGap(21, 21, 21)
												.addComponent(DefaultSettings, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGroup(contentPaneLayout.createParallelGroup()
												.addComponent(NoColor, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
												.addComponent(BackgroundMode, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))))))
						.addContainerGap())
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(Pool, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addComponent(PoolLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(AlghorytmLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(Alghorytm, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(WalletAdressLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(WalletAdress, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(NamePCLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(NamePC, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumberThreadsLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumberThreads, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(CPUPriorityLabel, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
								.addComponent(CPUPriority))
							.addComponent(ApportionCPU, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(HideDiff, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(NoColor, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(HideCPU, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(BackgroundMode))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(StartMining)
							.addComponent(DefaultSettings, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(203, 203, 203))
			);
			window.setSize(455, 260);
			 
	        window.pack();
	        
	        window.setLocationRelativeTo(null);
	        window.setVisible(true);
			
	}

	protected static void SetDefaultSettings() {
		HideCPU.setSelected(false);
		NoColor.setSelected(false);
		HideDiff.setSelected(false);
		BackgroundMode.setSelected(false);
		NumberThreads.setSelectedIndex(cores-1);
		ApportionCPU.setSelected(false);
		Alghorytm.setSelectedIndex(0);
		Pool.setSelectedItem("[YENTEN] yenten-pool.info");
		WalletAdress.setText("");
		NamePC.setText("");
		CPUPriority.setSelectedIndex(2);
	}

	private static void LoadSettings() {
		if (Loaded == 0) {
			try {
	            Preferences.importPreferences(new BufferedInputStream(new FileInputStream("config.xml")));
	            userPrefs = Preferences.userRoot().node("cpu-miner-batch-configurator");
	            log("load\\/");
	            userPrefs.exportSubtree(System.out);
	            log("load/\\");
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
			
			HideCPU.setSelected(userPrefs.getBoolean("hidecpu", false));
			NoColor.setSelected(userPrefs.getBoolean("nocolor", false));
			HideDiff.setSelected(userPrefs.getBoolean("hidedif", false));
			BackgroundMode.setSelected(userPrefs.getBoolean("backgroundmode", false));
			NumberThreads.setSelectedIndex(userPrefs.getInt("numberthreads", cores-1));
			ApportionCPU.setSelected(userPrefs.getBoolean("apportioncpu", false));
			Alghorytm.setSelectedIndex(userPrefs.getInt("alghorytm", 0));
			Pool.setSelectedItem(userPrefs.get("pool", "[YENTEN] yenten-pool.info"));
			WalletAdress.setText(userPrefs.get("walletadress", ""));
			NamePC.setText(userPrefs.get("namepc", ""));
			CPUPriority.setSelectedIndex(userPrefs.getInt("cpupriority", 2));
			
			Loaded = 1;
		}
	}

	protected static void UpdateCommand() {
		String CommandText = "";
		
		String MinerPath = "cpuminer-bin\\cpuminer.exe";
		
		CommandText = CommandText + MinerPath + " ";
		
		CommandText = CommandText + "-a " + Alghorytm.getSelectedItem().toString() + " ";
	
		if (NumberThreads.getSelectedIndex()!=(cores-1)) {
			CommandText = CommandText + "-t "+ NumberThreads.getSelectedItem() + " ";
			if(ApportionCPU.isSelected()) {
				CommandText = CommandText + "--cpu-affinity 0 ";
			}
		} else {
			ApportionCPU.setSelected(false);
		}
		
		CommandText = CommandText + "--cpu-priority " + CPUPriority.getSelectedIndex() + " ";
				
		if (HideCPU.isSelected()) {
			CommandText = CommandText + "-q ";
		}
	
		if(NoColor.isSelected()) {
			CommandText = CommandText + "--no-color ";
		}
		if(HideDiff.isSelected()) {
			CommandText = CommandText + "--hide-diff ";
		}
		if(BackgroundMode.isSelected()) {
			CommandText = CommandText + "-B ";
		}
		
		if (Pool.getSelectedItem().toString()=="[YENTEN] yenten-pool.info") {
			CommandText = CommandText + "-o stratum+tcp://yenten-pool.info:63368 ";
		} else {
			CommandText = CommandText + "-o " + Pool.getSelectedItem() + " ";
		}
		if (WalletAdress.getText().length()>0) {
			if (NamePC.getText().length()==0) {
				CommandText = CommandText + "-u " + WalletAdress.getText() + " ";
			} else {
				CommandText = CommandText + "-u " + WalletAdress.getText() + "." + NamePC.getText() + " ";
			}
		}
		
		CommandOutput.setText(CommandText);
	}

	private static void SaveSettings() {	
		userPrefs.putBoolean("hidecpu", HideCPU.isSelected());
		userPrefs.putBoolean("nocolor", NoColor.isSelected());
		userPrefs.putBoolean("apportioncpu", ApportionCPU.isSelected());
		userPrefs.putBoolean("hidedif", HideDiff.isSelected());
		userPrefs.putBoolean("backgroundmode", BackgroundMode.isSelected());
		userPrefs.putInt("numberthreads", NumberThreads.getSelectedIndex());
		userPrefs.putInt("alghorytm", Alghorytm.getSelectedIndex());
		userPrefs.put("pool", Pool.getSelectedItem().toString());
		userPrefs.put("walletadress", WalletAdress.getText());
		userPrefs.put("namepc", NamePC.getText());
		userPrefs.putInt("cpupriority", CPUPriority.getSelectedIndex());
		if (Loaded == 1) {
			 try {
				 log("save\\/");
	            userPrefs.exportNode(new FileOutputStream("config.xml"));
	            userPrefs.exportSubtree(System.out);
	            log("save/\\");
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	

	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		Init();
		LoadSettings();
		UpdateCommand();
	}

}
