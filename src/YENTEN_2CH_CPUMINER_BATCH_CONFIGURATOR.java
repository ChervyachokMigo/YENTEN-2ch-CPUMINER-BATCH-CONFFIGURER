import java.awt.Container;
import java.awt.Insets;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultFormatter; 

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
	private JCheckBox DebugCheck;
	
	private JButton DefaultSettings;
	private JButton StartMining;
	private JButton AddPool;
	
	private String CommandOutput;
	
	private int cores;
	
	private Preferences userPrefs;
	private String SettingsName;
	
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
		wid.setTitle("YENTEN 2CH CPUMINER BATCH CONFIGURATOR");
		ImageIcon img = new ImageIcon("logo.png");
		wid.setIconImage(img.getImage());
		Init(wid);
	}

	private void Init(final JFrame window) {
		SettingsName = "cpu-miner-batch-configurator";
		userPrefs = Preferences.userRoot().node(SettingsName);
		
		cores = Runtime.getRuntime().availableProcessors();
				 
		 StartMining = new JButton("Start Mining");  
		 StartMining.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	  	StartMiningAction();
             }
         });
		 
		 DefaultSettings = new JButton("Default Settings");
		 DefaultSettings.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
           	 	SetDefaultSettings();
             }
         });
			 
		 AddPool = new JButton("Add");
		 AddPool.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ShowAddPoolDialog(window);
			 }
		 });
		 AddPool.setMargin(new Insets(2, 0, 2, 0));
		 
		 Benchmark = new JButton("Start Benchmark");
		 Benchmark.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 StartBenchmark();
			 }
		 });
		 
		 DebugCheck = new JCheckBox("Debug");
		 
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
	     
	     String[] PoolList = new String[] {"stratum+tcp://yenten-pool.info:63368"};
	     Pool = new JComboBox<String>(PoolList);
	     Pool.setToolTipText("Select Pool");
	     Pool.setEditable(false);
	   
	     PoolLabel = new JLabel ("Pool");
	     
	     String[] PriorityList = new String[] {"[0] IDLE","[1] BELOW NORMAL","[2] NORMAL","[3] ABOVE NORMAL","[4] HIGH","[5] REALTIME"};
	     CPUPriority = new JComboBox<String>(PriorityList);
	     CPUPriority.setToolTipText("CPU Priority");
	     CPUPriority.setSelectedIndex(2);
	     
	     CPUPriorityLabel = new JLabel ("Miner Priority");
	     
	     WalletAdress = new JTextField("");
	     WalletAdressLabel = new JLabel("Wallet/Username");
	     
	     NamePC = new JTextField("");
	     NamePCLabel = new JLabel("PC Name");
	     	     
		 Pause = new JCheckBox("Pause after crash");
		 InfiniteLoop = new JCheckBox("Infinite Loop");
		 Password = new JTextField("");
		 PasswordLabel = new JLabel("Password");
		 TimeStratumLabel = new JLabel("Timeout/Stratum (sec)");
		 TimeStratum = new JSpinner(new SpinnerNumberModel(300, 30, null, 30));
		 SaveBAT = new JButton("Save BAT File");
		 SaveBAT.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 SaveBATFile(window);
			 }
		 });
		 
		 CloseMiners = new JButton("Close All Miners");
		 CloseMiners.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 CloseMinersCommand();
			 }
		 });
	     
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
		 AddUpdateElement(DebugCheck);
		 AddUpdateElement(Pause);
		 AddUpdateElement(InfiniteLoop);
		 AddUpdateElement(Password);
		 AddUpdateElement(TimeStratum);		 
		 
	     UpdateCommand();

	}

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
						.addGap(6, 6, 6)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGroup(contentPaneLayout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup()
										.addComponent(CloseMiners, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
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
							.addComponent(CloseMiners)
							.addComponent(Benchmark)
							.addComponent(DefaultSettings))
						.addContainerGap())
			);
			window.setSize(485, 390);
			 
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
	    } else {
	        log ("cancel");
	    }
	}
	
	private void CreateBat(String FileNameBAT) throws IOException {
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

	private void CloseMinersCommand() {
		try {
		 		Runtime.getRuntime().exec("taskkill /F /IM cpuminer.exe");
		 		Runtime.getRuntime().exec("taskkill /F /IM cpuminer-aes-sse42.exe");
		 		Runtime.getRuntime().exec("taskkill /F /IM cpuminer-avx.exe");
		 		Runtime.getRuntime().exec("taskkill /F /IM cpuminer-avx2.exe");
		 		Runtime.getRuntime().exec("taskkill /F /IM cpuminer-avx2-sha.exe");
		 		Runtime.getRuntime().exec("taskkill /F /IM cpuminer-sse2.exe");
		 		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	
	private void StartMiningAction() {
		try {
 		  SaveSettings();
 		  CreateBat("start_mining.bat");
 		  String path="cmd /c start start_mining.bat";
 		  Runtime rn=Runtime.getRuntime();
 		  rn.exec(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void StartBenchmark() {
		try {
			SaveSettings();
			String path="cmd /c start " + CommandOutput + " --benchmark ";
			Runtime rn=Runtime.getRuntime();
	 		  rn.exec(path);
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
		HideCPU.setSelected(false);
		NoColor.setSelected(false);
		HideDiff.setSelected(false);
		BackgroundMode.setSelected(false);
		NumberThreads.setSelectedIndex(cores-1);
		ApportionCPU.setSelected(false);
		Alghorytm.setSelectedIndex(0);
		Pool.setSelectedItem("stratum+tcp://yenten-pool.info:63368");
		WalletAdress.setText("");
		NamePC.setText("");
		CPUPriority.setSelectedIndex(2);
		DebugCheck.setSelected(false);
		Pause.setSelected(false);
		InfiniteLoop.setSelected(false);
		Password.setText("");
		TimeStratum.setValue(300);
		SaveSettings();
	}

	private void LoadSettings() {
		HideCPU.setSelected(userPrefs.getBoolean("hidecpu", false));
		NoColor.setSelected(userPrefs.getBoolean("nocolor", false));
		HideDiff.setSelected(userPrefs.getBoolean("hidedif", false));
		BackgroundMode.setSelected(userPrefs.getBoolean("backgroundmode", false));
		NumberThreads.setSelectedIndex(userPrefs.getInt("numberthreads", cores-1));
		ApportionCPU.setSelected(userPrefs.getBoolean("apportioncpu", false));
		Alghorytm.setSelectedIndex(userPrefs.getInt("alghorytm", 0));
		Pool.setSelectedItem(userPrefs.get("pool", "stratum+tcp://yenten-pool.info:63368"));
		WalletAdress.setText(userPrefs.get("walletadress", ""));
		NamePC.setText(userPrefs.get("namepc", ""));
		CPUPriority.setSelectedIndex(userPrefs.getInt("cpupriority", 2));
		DebugCheck.setSelected(userPrefs.getBoolean("debug", false));
		Pause.setSelected(userPrefs.getBoolean("pause", false));
		InfiniteLoop.setSelected(userPrefs.getBoolean("infiniteloop", false));
		Password.setText(userPrefs.get("password", ""));
		TimeStratum.setValue(Integer.parseInt(userPrefs.get("timestratum", "300")));
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
		userPrefs.putBoolean("debug", DebugCheck.isSelected());
		userPrefs.putBoolean("pause", Pause.isSelected());
		userPrefs.putBoolean("infiniteloop", InfiniteLoop.isSelected());
		userPrefs.put("password", Password.getText());
		userPrefs.put("timestratum", TimeStratum.getValue().toString());
	}
	
	private void ExportSettings() {
		try {
			 log("save\\/");
           userPrefs.exportNode(new FileOutputStream(SettingsName + ".xml"));
           userPrefs.exportSubtree(System.out);
           log("save/\\");
       }
       catch(Exception e) {
           e.printStackTrace();
       }
	}
	
	private void ImportSettings(){
		try {
            Preferences.importPreferences(new BufferedInputStream(new FileInputStream(SettingsName + ".xml")));
            userPrefs = Preferences.userRoot().node(SettingsName);
            log("load\\/");
            userPrefs.exportSubtree(System.out);
            log("load/\\");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}
	

	private void UpdateCommand() {
		String CommandText = "";
		
		String MinerPath = "";
		try {
			MinerPath = "\"" + new File(".").getCanonicalPath() + "\\cpuminer-bin\\cpuminer.exe\"";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CommandText = CommandText + MinerPath + " ";
		
		
		CommandText = CommandText + "-o " + Pool.getSelectedItem() + " ";
		
		
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
		} else {
			ApportionCPU.setSelected(false);
		}
		
		CommandText = CommandText + "--cpu-priority " + CPUPriority.getSelectedIndex() + " ";
				
		CommandText = CommandText + "-T " + TimeStratum.getValue() + " ";
		
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
		
		if(DebugCheck.isSelected()) {
			CommandText = CommandText + "-D ";
		}
				
		CommandOutput = CommandText;
	}
	
	private static void log(String s) {
		System.out.println(s);
	}
}
