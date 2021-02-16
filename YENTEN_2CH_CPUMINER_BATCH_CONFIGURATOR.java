import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.prefs.Preferences;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle; 

public class YENTEN_2CH_CPUMINER_BATCH_CONFIGURATOR {
	private JCheckBox HideCPU;
	private JCheckBox NoColor;
	private JComboBox<String> NumberThreads;
	private JComboBox<String> Alghorytm;
	private JComboBox<String> Pool;
	private JComboBox<String> CPUPriority;
	private JTextField WalletAdress;
	private JTextField NamePC;
	private JCheckBox ApportionCPU;
	private JCheckBox HideDiff;
	private JCheckBox BackgroundMode;
	
	private JButton DefaultSettings;
	private JButton StartMining;
	
	private String CommandOutput;
	
	private int cores;
	
	private Preferences userPrefs;
	
	private JLabel NumberThreadsLabel;
	private JLabel WalletAdressLabel;
	private JLabel AlghorytmLabel;
	private JLabel PoolLabel;
	private JLabel NamePCLabel;
	private JLabel CPUPriorityLabel;
	
	public YENTEN_2CH_CPUMINER_BATCH_CONFIGURATOR(String windowName) {
		JFrame wid = new JFrame(windowName);
		wid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wid.addWindowListener(new WindowAdapter() {
	    	 @Override
	    	 public void windowClosing(WindowEvent e) {
	    		 SaveSettings();
	    	     System.exit(0);
	    	 }
	     });
		Init(wid);
	}

	private void Init(JFrame window) {
		userPrefs = Preferences.userRoot().node("cpu-miner-batch-configurator");
		
		cores = Runtime.getRuntime().availableProcessors();
				 
		 StartMining = new JButton("Start Mining");  
		 
		 StartMining.addActionListener(new ActionListener()
        {
             public void actionPerformed(ActionEvent e) 
             {
           	  	StartMiningAction();
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
		 
		 String[] NumberCoresValues = new String[cores];
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
	     
	     LoadSettings();
	        
	     WindowInterfacePlacement(window);
	     
	     AddUpdateElement(HideCPU);
		 AddUpdateElement(NoColor);
		 AddUpdateElement(HideDiff);
		 AddUpdateElement(BackgroundMode);
		 AddUpdateElement(NumberThreads);
		 AddUpdateElement(Alghorytm);
		 AddUpdateElement(Pool);
		 AddUpdateElement(CPUPriority);
		 AddUpdateElement(ApportionCPU);
		 AddUpdateElement(WalletAdress);
		 AddUpdateElement(NamePC);
	     
	     UpdateCommand();
	}
	
	private void AddUpdateElement(final JComponent Element) {
		if (Element instanceof JComboBox) {
			((JComboBox<String>) Element).addKeyListener(new KeyAdapter() {   
		           public void keyPressed(KeyEvent e) {
		        	     ((JComboBox<String>) Element).setSelectedIndex(((JComboBox<String>) Element).getSelectedIndex());
			           	 UpdateCommand();
			           }
			           public void keyReleased(KeyEvent e) {
			        	   ((JComboBox<String>) Element).setSelectedIndex(((JComboBox<String>) Element).getSelectedIndex());
			          	 UpdateCommand();
			          }
			           public void keyTyped(KeyEvent e) {
			        	   ((JComboBox<String>) Element).setSelectedIndex(((JComboBox<String>) Element).getSelectedIndex());
			           	 UpdateCommand();
			           }
			       });
			((JComboBox<String>) Element).addItemListener(new ItemListener() {
	           public void itemStateChanged(ItemEvent e) {
	        	   ((JComboBox<String>) Element).setSelectedIndex(((JComboBox<String>) Element).getSelectedIndex());
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
	}
	
	private void WindowInterfacePlacement(JFrame window) {
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

	private void CreateBat() throws IOException {
		File file=new File("start_mining.bat");
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dataOutputStream = new DataOutputStream(fos);
		dataOutputStream.writeBytes(CommandOutput);
	}

	private void StartMiningAction() {
		try {
 		  SaveSettings();
 		  CreateBat();
 		  String path="cmd /c start start_mining.bat";
 		  Runtime rn=Runtime.getRuntime();
 		  rn.exec(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	private void SetDefaultSettings() {
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
		SaveSettings();
	}

	private void LoadSettings() {
			/*try {
	            Preferences.importPreferences(new BufferedInputStream(new FileInputStream("config.xml")));
	            userPrefs = Preferences.userRoot().node("cpu-miner-batch-configurator");
	            log("load\\/");
	            userPrefs.exportSubtree(System.out);
	            log("load/\\");
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }*/
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

	}

	private void UpdateCommand() {
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
		
		CommandOutput = CommandText;
	}

	private void SaveSettings() {	
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
	}
	
	private void ExportSettings() {
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
	
	
	private static void log(String s) {
		System.out.println(s);
	}
}
