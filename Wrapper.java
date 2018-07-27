import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
*
* @author yesiam77
*/
public class Wrapper {
	
	private static double version = 1.3;
	private static boolean compileWithMiners = false;
	private static String OS = "";
	private static String homeDir = "";
	private static String selectedMiner = "";
	private static ArrayList<String> knownMiners = new ArrayList<String>();
	private static DefaultListModel<String> availableMiners = new DefaultListModel<String>();

	private static DefaultListModel<String> availableGPUs = new DefaultListModel<String>();
	private static BufferedReader minerOutput = null;
	private static Runtime rt = Runtime.getRuntime();
	private static Process minerProcess = null;
	private static File jarFile = null;
	private static GUI gui = null;

	private static String advCMDText = "";
	private static String username = "";
	private static String password = "";
	private static String devices = "";
	private static String poolURL = "";
	private static String algo = "";
		
	private static boolean isMinerRunning = false;
	private static int consoleMaxLength = 20000;
	private static int monitorMaxLength = 10000;
	private static String console = "";
	private static String monitor = "";
	
	private static String[] ccMinerAlgos = new String[] { "allium", "bastion", "bitcore", "blake", "blakecoin", "blake2s", "bmw",
			"cryptolight", "cryptonight", "c11/flax", "decred", "deep", "dmd-gr", "equihash", "fresh", "fugue256", "groestl", "hsr",
			"jackpot", "keccak", "keccakc", "lbry", "luffa", "lyra2", "lyra2v2", "lyra2z", "monero", "myr-gr", "neoscrypt", "nist5",
			"penta", "phi1612", "phi2", "polytimos", "quark", "qubit", "scrypt", "scrypt:10", "scrypt:11", "scrypt:12", "scrypt:13",
			"scrypt:14", "scrypt:15", "scrypt:16", "scrypt:17", "scrypt:18", "scrypt:19", "scrypt:20", "scrypt:21", "scrypt-jane",
			"s3", "sha256t", "sia", "sib", "skein", "skein2", "skunk", "sonoa", "stellite", "timetravel", "tribus", "x11evo", "x11",
			"x12", "x13", "x14", "x15", "x16r", "x16s", "x17", "vanilla", "veltor", "whirlpool", "wildkeccak", "zr5" };
	//private static String[] claymoreAlgos = new String[] { "ethash" };
	private static String[] cryptodredgeAlgos = new String[] { "allium", "lyra2rev2", "lyra2z", "neoscrypt", "phi1612", "tribus" };
	//private static String[] sgminerAlgos = new String[] { "" };
	private static String[] zenemyAlgos = new String[] { "aeriumx", "bitcore", "x16r", "x16s", "x17", "c11", "phi", "phi2",
			"tribus", "poly", "skunk", "sonoax", "timetravel", "xevan" };
    
	
	public static void prepare(GUI newGUI, File jarFileLoc)
	{
		gui = newGUI;
		jarFile = jarFileLoc;
		
		String temp = System.getProperty("os.name");
		if(temp.toLowerCase().contains("win"))
		{
			OS = "Windows";
			homeDir = System.getenv("APPDATA");
		}
		else if(temp.toLowerCase().contains("nux"))
		{
			OS = "Linux";
			homeDir = System.getenv("user.home");
		}
		else if(temp.toLowerCase().contains("mac") || temp.toLowerCase().contains("darwin"))
		{
			OS = "Mac";
			homeDir = System.getenv("user.home");
			System.setProperty("apple.eawt.quitStrategy","CLOSE_ALL_WINDOWS");
			JOptionPane.showMessageDialog(gui,"This program does not work on Mac, Sorry!");
			System.exit(0);
		}
				
		ArrayList<String> save = Wrapper.readFile("save.dat");
		if(save.size() >= 5)
		{
			gui.getAlgoCombobox().setSelectedItem(save.get(0).replaceAll("#",""));
			gui.getPoolURLField().setText(save.get(1).replaceAll("#",""));
			gui.getUsernameField().setText(save.get(2).replaceAll("#",""));
			gui.getPasswordField().setText(save.get(3).replaceAll("#",""));
			gui.getAdvCMDField().setText(save.get(4).replaceAll("#",""));
				
			algo = save.get(0).replaceAll("#","");
			poolURL = save.get(1).replaceAll("#","");
			username = save.get(2).replaceAll("#","");
			password = save.get(3).replaceAll("#","");
			advCMDText = save.get(4).replaceAll("#","");
		}
		
		ArrayList<String> settings = Wrapper.readFile("settings.dat");
		if(settings.size() >= 5)
		{
			//TODO
			//algo = save.get(0).replaceAll("#","");
		}
		
		try
		{
			//TODO find linux and mac equivalents of the window cmd for finding system GPUs
			if(OS.equals("Windows"))
			{
				Process pr = rt.exec("wmic path win32_VideoController get name");
				BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				
				String input = "";
				while((input = br.readLine()) != null)
					if(!input.contains("Name") && !input.isEmpty())
						availableGPUs.addElement(input);
				
				gui.getGPUsAvailableList().setModel(availableGPUs);
			}
			else
			{
				gui.getGPUsAvailableList().setModel(availableGPUs);
				JOptionPane.showMessageDialog(gui,"The auto-populate feature for the GPU list does not work on Linux.\n"
						+ " Try using the Advanced Commandline Options box to specify the devices to use via the -d argument.\n"
						+ " More information about this argument can be found in the help tab.");
			}
			
			checkForUpdate();
			
			if(!compileWithMiners)
			{
				knownMiners.add("ccminer");
				knownMiners.add("cryptodredge");
				knownMiners.add("z-enemy");
			}
						
			checkAvailableMiners();
			
		} catch (IOException e) {}
	}
	
	//TODO Needs adjusting after adding new miners
	public static void startMiner()
	{
		try
		{
			checkAvailableMiners();
			
			if(availableMiners.size() > 1)
			{
				
				if(!checkIfContains(ccMinerAlgos,algo))
					for(int k = 0; k < availableMiners.size(); k++)
					{
						if(availableMiners.getElementAt(k).toLowerCase().contains("ccminer"))
							availableMiners.removeElementAt(k);
					}
				
				if(!checkIfContains(cryptodredgeAlgos,algo))
					for(int k = 0; k < availableMiners.size(); k++)
					{
						if(availableMiners.getElementAt(k).toLowerCase().contains("cryptodredge"))
							availableMiners.removeElementAt(k);
					}
				
				/*if(!checkIfContains(claymoreAlgos,algo))
					for(int k = 0; k < availableMiners.size(); k++)
					{
						if(availableMiners.getElementAt(k).toLowerCase().contains("claymore"))
							availableMiners.removeElementAt(k);
					}
				*/
				
				if(!checkIfContains(zenemyAlgos,algo))
					for(int k = 0; k < availableMiners.size(); k++)
					{
						if(availableMiners.getElementAt(k).toLowerCase().contains("z-enemy"))
							availableMiners.removeElementAt(k);
					}
				
				
				JList<String> list = new JList<String>();
				list.setModel(availableMiners);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JOptionPane.showMessageDialog(gui,list,"Choose a miner",JOptionPane.DEFAULT_OPTION);
				
				if(list.getSelectedIndex() > -1)
					selectedMiner = list.getSelectedValue();
				else
					return;
			}
			else if(availableMiners.size() == 1)
				selectedMiner = availableMiners.get(0);
			else
			{
				if(compileWithMiners)
					JOptionPane.showMessageDialog(gui,"No compatible miner was found for algo \""+algo+"\"\nThis is a bug, please report this!");
				else
					JOptionPane.showMessageDialog(gui,"No compatible miner was found for algo \""+algo+"\"");
			}
			
			if(compileWithMiners)
				if(selectedMiner.contains("(Prepackaged)"))
					extractFile(selectedMiner);
			
			if(selectedMiner.toLowerCase().contains("ccminer"))
			{
				String args = "-a "+algo+" -o "+poolURL+" -u "+username;
				
				if(!password.isEmpty())
					args += " -p "+password;
				
				if(OS.equals("Windows"))
					if(!devices.isEmpty())
						args += " -d "+devices;
				
				if(!advCMDText.isEmpty())
					args += " "+advCMDText;
				
				System.out.println("Full CMD Args: "+args);
				
				minerProcess = rt.exec(selectedMiner+" "+args);
				minerOutput = new BufferedReader(new InputStreamReader(minerProcess.getInputStream()));

			}
			else if(selectedMiner.toLowerCase().contains("cryptodredge"))
			{
				String args = "-a "+algo+" -o "+poolURL+" -u "+username;
				
				if(!password.isEmpty())
					args += " -p "+password;
				
				if(OS.equals("Windows"))
					if(!devices.isEmpty())
						args += " -d "+devices;
				
				System.out.println("Full CMD Args: "+args);
				
				minerProcess = rt.exec(selectedMiner+" "+args);
				minerOutput = new BufferedReader(new InputStreamReader(minerProcess.getInputStream()));
			}
			/*else if(selectedMiner.toLowerCase().contains("claymore"))
			{
				String args = " -epool "+poolURL+" -ewal "+username;
				
				if(!password.isEmpty())
					args += " -epsw "+password;
				
				if(OS.equals("Windows"))
					if(!devices.isEmpty())
						args += " -di "+devices.replaceAll(",","");
				
				if(!args.contains("-allpools"))
					args += " -allpools 1";
				
				if(!args.contains("-allcoins"))
					args += " -allcoins 1";
				
				if(!args.contains(" -mode"))
					args += " -mode 1";
				
				if(!args.contains(" -dbg"))
					args += " -dbg -1";
				
				if(!args.contains(" -wd"))
					args += " -wd 0";
				
				if(!args.contains(" -r"))
					args += " -r 1";
				
				System.out.println("Full CMD Args: "+args);
				
				minerProcess = rt.exec(selectedMiner+" "+args);
				minerOutput = new BufferedReader(new InputStreamReader(minerProcess.getInputStream()));
			}*/
			else if(selectedMiner.toLowerCase().contains("z-enemy"))
			{
				String args = "-a "+algo+" -o "+poolURL+" -u "+username;
				
				if(!password.isEmpty())
					args += " -p "+password;
				
				if(OS.equals("Windows"))
					if(!devices.isEmpty())
						args += " -d "+devices;
				
				if(!advCMDText.isEmpty())
					args += " "+advCMDText;
				
				System.out.println("Full CMD Args: "+args);
				
				minerProcess = rt.exec(selectedMiner+" "+args);
				minerOutput = new BufferedReader(new InputStreamReader(minerProcess.getInputStream()));
			}
			else
			{
				JOptionPane.showMessageDialog(gui,"Unrecognized Miner, Try using CCMiner, CryptoDredge or Z-Enemy.");
				return;
			}
						        				
			String temp = minerOutput.readLine();
			if(temp.contains("***") || temp.contains("[INFO   ]"))// || selectedMiner.toLowerCase().contains("claymore"))
			{
	        	Wrapper.writeConsole("Starting "+selectedMiner+"...");
				writeConsole(temp);
				gui.getStartMinerButton().setText("Stop Miner");
				isMinerRunning = true;
			}
			else
			{
				writeConsole("Failed to start "+selectedMiner+"...");
				writeConsole(temp);
				
				String input = "";
				while((input = minerOutput.readLine()) != null)
				{
					writeConsole(input);
				}
			}
			
			
			new Thread()
			{
				public void run()
				{
					ArrayList<GPU> gpuMonitor = new ArrayList<GPU>();
					String input = "";
					
					try
					{
						while((input = minerOutput.readLine()) != null && isMinerRunning)
						{
							if(selectedMiner.toLowerCase().contains("ccminer"))
							{
								if(input.contains("GPU #") && !input.contains("kH/W") && input.contains("/s"))//find card hashrates
								{
									int num = Integer.parseInt(input.substring(input.indexOf("#")+1,input.indexOf("#")+2));
									String hash = input.substring(input.indexOf(",")+2);
									
									boolean found = false;
									for(int k = 0; k < gpuMonitor.size(); k++)
									{
										
										if(gpuMonitor.get(k).gpuNum == num)
										{
											found = true;
											gpuMonitor.get(k).setHash(hash);
										}
									}
									
									if(!found)
									{
										gpuMonitor.add(new GPU(num,0,hash));
									}
									
									writeMonitor(input);
								}
								else if(input.contains("GPU #") && input.contains("kH/W"))//find card temps
								{
									int num = Integer.parseInt(input.substring(input.indexOf("#")+1,input.indexOf("#")+2));
									int temp = 0;
	
									String[] split = input.split(" ");
									
									for(int k = 0; k < split.length; k++)
									{
										if(split[k].contains("C"))
											temp = Integer.parseInt(split[k].replaceAll("[^0-9]+",""));
									}
									
									boolean found = false;
									for(int k = 0; k < gpuMonitor.size(); k++)
									{
										
										if(gpuMonitor.get(k).gpuNum == num)
										{
											found = true;
											gpuMonitor.get(k).gpuTemp = temp;
										}
									}
									
									if(!found)
									{
										gpuMonitor.add(new GPU(num,temp,"0"));
									}
									
									writeMonitor(input);
								}
							}
							
							
							/*else if(selectedMiner.toLowerCase().contains("claymore"))
							{
								if(input.contains("GPU") && input.contains("t="))//find card temps
								{
									if(input.contains(","))
									{
										String[] inputSplit = input.split(",");
										
										for(int j = 0; j < inputSplit.length; j++)
										{
											int temp = 0;
											int num = Integer.parseInt(input.substring(input.indexOf("GPU")+3,input.indexOf("GPU")+4));
											
											String[] split = input.split(" ");
											for(int k = 0; k < split.length; k++)
											{
												if(split[k].contains("C"))
													temp = Integer.parseInt(split[k].replaceAll("[^0-9]+",""));
											}
											
											boolean found = false;
											for(int k = 0; k < gpuMonitor.size(); k++)
											{
												
												if(gpuMonitor.get(k).gpuNum == num)
												{
													found = true;
													gpuMonitor.get(k).gpuTemp = temp;
												}
											}
											
											if(!found)
											{
												gpuMonitor.add(new GPU(num,temp,"0 kH/s"));
											}
										}
									}
									else
									{
										int temp = 0;
										int num = Integer.parseInt(input.substring(input.indexOf("GPU")+3,input.indexOf("GPU")+4));
										
										String[] split = input.split(" ");
										for(int k = 0; k < split.length; k++)
										{
											if(split[k].contains("C"))
												temp = Integer.parseInt(split[k].replaceAll("[^0-9]+",""));
										}
										
										boolean found = false;
										for(int k = 0; k < gpuMonitor.size(); k++)
										{
											
											if(gpuMonitor.get(k).gpuNum == num)
											{
												found = true;
												gpuMonitor.get(k).gpuTemp = temp;
											}
										}
										
										if(!found)
										{
											gpuMonitor.add(new GPU(num,temp,"0 kH/s"));
										}
									}
									
									writeMonitor(input);
								}
								else if(input.contains("GPU") && input.contains("ETH: ") && input.contains("/s"))//find card hashrates
								{
									if(input.contains(","))
									{
										String[] inputSplit = input.split(",");
										
										for(int j = 0; j < inputSplit.length; j++)
										{
											int num = Integer.parseInt(input.substring(input.indexOf("GPU")+3,input.indexOf("GPU")+4));
											String hash = "";
											
											String[] split = input.split(" ");
												hash = split[1]+" "+split[2];
											
											boolean found = false;
											for(int k = 0; k < gpuMonitor.size(); k++)
											{
												
												if(gpuMonitor.get(k).gpuNum == num)
												{
													found = true;
													gpuMonitor.get(k).setHash(hash);
												}
											}
											
											if(!found)
											{
												gpuMonitor.add(new GPU(num,0,hash));
											}
										}										
									}
									else
									{
										int num = Integer.parseInt(input.substring(input.indexOf("GPU")+3,input.indexOf("GPU")+4));
										String hash = "";
										
										String[] split = input.split(" ");
											hash = split[1]+" "+split[2];
										
										boolean found = false;
										for(int k = 0; k < gpuMonitor.size(); k++)
										{
											
											if(gpuMonitor.get(k).gpuNum == num)
											{
												found = true;
												gpuMonitor.get(k).setHash(hash);
											}
										}
										
										if(!found)
										{
											gpuMonitor.add(new GPU(num,0,hash));
										}
									}
									
									writeMonitor(input);
								}
								else if(input.contains("ETH - Total Speed:"))
								{									
									writeMonitor(input);
								}
							}
							*/
							
							
							else if(selectedMiner.toLowerCase().contains("cryptodredge"))
							{
								if(input.contains("- GPU") && input.contains("T="))//find card temps and hashrates
								{
									int temp = 0;
									int num = Integer.parseInt(input.substring(input.indexOf("GPU")+3,input.indexOf("GPU")+4));
									String hash = input.substring(input.indexOf("Avr ")+4,input.indexOf(")"));
									
									String[] split = input.split(" ");
									for(int k = 0; k < split.length; k++)
									{
										if(split[k].contains("C"))
											temp = Integer.parseInt(split[k].replaceAll("[^0-9]+",""));
									}
									
									boolean found = false;
									for(int k = 0; k < gpuMonitor.size(); k++)
									{
										
										if(gpuMonitor.get(k).gpuNum == num)
										{
											found = true;
											gpuMonitor.get(k).setHash(hash);
											gpuMonitor.get(k).gpuTemp = temp;
										}
									}
									
									if(!found)
									{
										gpuMonitor.add(new GPU(num,temp,hash));
									}
									
									writeMonitor(input);
								}
							}
							
							
							else if(selectedMiner.toLowerCase().contains("z-enemy"))
							{
								if(input.contains("GPU#") && input.contains("/s"))//find card hashrates (no temps in z-enemy output)
								{
									int num = Integer.parseInt(input.substring(input.indexOf("#")+1,input.indexOf("#")+2));
									String hash = input.substring(input.indexOf(",")+2);
									
									boolean found = false;
									for(int k = 0; k < gpuMonitor.size(); k++)
									{
										
										if(gpuMonitor.get(k).gpuNum == num)
										{
											found = true;
											gpuMonitor.get(k).setHash(hash);
										}
									}
									
									if(!found)
									{
										gpuMonitor.add(new GPU(num,0,hash));
									}
									
									writeMonitor(input);
								}
							}
							
							
							if(gpuMonitor.size() > 0)
							{
								int avgTemp = 0;
								double totalHashrate = 0;
								
								String suffix = "";
								for(int k = 0; k < gpuMonitor.size(); k++)
								{
									avgTemp += gpuMonitor.get(k).gpuTemp;
									totalHashrate += gpuMonitor.get(k).gpuHash;
								}
								
								if(totalHashrate > 1000)
								{
									totalHashrate /= 1000;
									suffix = " kH/s";
									
									if(totalHashrate > 1000)
									{
										totalHashrate /= 1000;
										suffix = " MH/s";
										
										if(totalHashrate > 1000)
										{
											totalHashrate /= 1000;
											suffix = " GH/s";
											
											if(totalHashrate > 1000)
											{
												totalHashrate /= 1000;
												suffix = " TH/s";
											}
										}
									}
								}
								
								gui.getAlgorithmField().setText(algo);
								gui.getTotalGPUsHashingField().setText(String.valueOf(gpuMonitor.size()));
								gui.getAvgTempField().setText(String.valueOf((double)avgTemp/(double)gpuMonitor.size()));
								gui.getTotalHashrateField().setText(round(totalHashrate,2)+suffix);
							}
							
							if(!input.isEmpty())
								writeConsole(input);
						}
						
					} catch (IOException e) {}
				}
				
				class GPU
				{
					int gpuNum = -1;
					int gpuTemp = 0;
					double gpuHash = 0;
					
					GPU(int num, int temp, String hash)
					{
						gpuNum = num;
						gpuTemp = temp;
						setHash(hash);
					}
					
					public void setHash(String hash)
					{
						if(hash.toLowerCase().contains("th/s"))
							gpuHash = Double.parseDouble(hash.replaceAll("\\[0m","").replaceAll("[^0-9\\.]+",""))*1000*1000*1000*1000;
						else if(hash.toLowerCase().contains("gh/s"))
							gpuHash = Double.parseDouble(hash.replaceAll("\\[0m","").replaceAll("[^0-9\\.]+",""))*1000*1000*1000;
						else if(hash.toLowerCase().contains("mh/s"))
							gpuHash = Double.parseDouble(hash.replaceAll("\\[0m","").replaceAll("[^0-9\\.]+",""))*1000*1000;
						else if(hash.toLowerCase().contains("kh/s"))
							gpuHash = Double.parseDouble(hash.replaceAll("\\[0m","").replaceAll("[^0-9\\.]+",""))*1000;
						else
							gpuHash = Double.parseDouble(hash.replaceAll("\\[0m","").replaceAll("[^0-9\\.]+",""));
					}
				}
				
			}.start();
			
		} catch (IOException e) {}		
	}
		
	public static boolean checkReady()
	{
		if(!poolURL.isEmpty() && !username.isEmpty() && !isMinerRunning)
			return true;
		else
			return false;
	}
	
	public static void stopMiner()
	{
		if(isMinerRunning)
		{
			Wrapper.writeConsole("Stopping "+selectedMiner+"...");
			gui.getStartMinerButton().setText("Start Miner");
			selectedMiner = "";
			
			minerProcess.destroy();
			minerProcess.destroy();
			isMinerRunning = false;
		}
	}
	
	//TODO adjust after adding new miners
	public static void checkAvailableMiners() throws IOException
	{
		selectedMiner = "";
		availableMiners.clear();
		
		List<String> results = new ArrayList<String>();
		File[] files = new File(jarFile.getParentFile().getCanonicalPath()).listFiles();

		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		
		for(int k = 0; k < results.size(); k++)
		{
			for(int j = 0; j < knownMiners.size(); j++)
			if(results.get(k).toLowerCase().replaceAll("-","").contains(knownMiners.get(j).replaceAll("-","")))
			{
				availableMiners.addElement(results.get(k));
			}
		}
		
		if(OS.equals("Windows"))
		{
			boolean nVidia = false;
			boolean AMD = false;
			for(int k = 0; k < availableGPUs.size(); k++)
			{
				if(availableGPUs.getElementAt(k).toLowerCase().contains("nvidia") || availableGPUs.getElementAt(k).toLowerCase().contains("geforce"))
					nVidia = true;
				else if(availableGPUs.getElementAt(k).toLowerCase().contains("amd") || availableGPUs.getElementAt(k).toLowerCase().contains("radeon"))
					AMD = true;
			}
			
			//These come prepackaged in .jar
			if(compileWithMiners)
			{
				if(nVidia)
				{
					availableMiners.addElement("ccminer-x64(Prepackaged).exe");
					availableMiners.addElement("CryptoDredge(Prepackaged).exe");
					availableMiners.addElement("z-enemy(Prepackaged).exe");
				}
				
				if(AMD)
				{
					
				}
			}
			
			for(int k = 0; k < availableMiners.size(); k++)
				if(!availableMiners.getElementAt(k).contains(".exe"))
				{
					availableMiners.removeElementAt(k);
					k--;
				}
		}
		else if(OS.equals("Linux"))
		{
			boolean nVidia = false;
			boolean AMD = false;
			for(int k = 0; k < availableGPUs.size(); k++)
			{
				if(availableGPUs.getElementAt(k).toLowerCase().contains("nvidia") || availableGPUs.getElementAt(k).toLowerCase().contains("geforce"))
					nVidia = true;
				else if(availableGPUs.getElementAt(k).toLowerCase().contains("amd") || availableGPUs.getElementAt(k).toLowerCase().contains("radeon"))
					AMD = true;
			}

			//These come prepackaged in .jar
			if(compileWithMiners)
			{
				if(nVidia)
				{
					availableMiners.addElement("z-enemy-ubuntu-cuda9_1(Prepackaged)");
				}
				
				if(AMD)
				{
					
				}
			}
		}
		
		if(availableMiners.size() == 0)
		{
			JOptionPane.showMessageDialog(gui,"Unable to find a compatible miner either prepackaged or in the same directory as this GUI.\n"
					+ "Compatible miners include: CCMiner, CryptoDredge and Z-Enemy");
			System.exit(0);
		}
		else
		{
			gui.getAlgoCombobox().setModel(new javax.swing.DefaultComboBoxModel<>(Wrapper.getFullAlgoList()));
		}
	}
	
	public static void checkForUpdate()
	{
		try
		{
			URL url = new URL("https://api.github.com/repos/yesiam77/GUIMiner/releases/latest");
			URLConnection conn = url.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String response = "";
			String input = "";
			while ((input = br.readLine()) != null) {
				response += input+"\n";
			}
			is.close();
			
			if(response.contains("\"tag_name\""))
			{
				String versionStr = response.substring(response.indexOf("\"tag_name\"")+12);
				versionStr = versionStr.substring(0,versionStr.indexOf(","));
				double newVersion = Double.parseDouble(versionStr.replaceAll("[^0-9\\.]+",""));
				
				if(newVersion > version)
				{
					JLabel label = new JLabel();
				    Font font = label.getFont();

				    StringBuffer style = new StringBuffer("font-family:" + font.getFamily() + ";");
				    style.append("font-weight:" + (font.isBold() ? "bold" : "normal") + ";");
				    style.append("font-size:" + font.getSize() + "pt;");

				    JEditorPane ep = new JEditorPane("text/html","<html><body style=\"" + style + "\">"
				            + "There is a new version of the miner available <a href=\"https://github.com/yesiam77/GUIMiner/releases\">here</a>."
				            + "</body></html>");

				    ep.setEditable(false);
				    ep.setBackground(label.getBackground());
				    ep.addHyperlinkListener(new HyperlinkListener()
				    {
				        @Override
				        public void hyperlinkUpdate(HyperlinkEvent e)
				        {
				        	if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				            if(Desktop.isDesktopSupported())
				            {
				                Desktop desktop = Desktop.getDesktop();
				                try
				                {
				                    desktop.browse(new URI("https://github.com/yesiam77/GUIMiner/releases"));
				                    
				                } catch (IOException | URISyntaxException e1) {
				                    e1.printStackTrace();
				                }
				            }
				        }
				    });
					
					JOptionPane.showMessageDialog(gui,ep);
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//TODO add all supported miners here
	public static String[] getFullAlgoList()
	{
		ArrayList<String> fullAlgoList = new ArrayList<String>();
		Set<String> temp = new LinkedHashSet<>();
		
		for(int k = 0; k < availableMiners.size(); k++)
		{
			System.out.println("Miner Found: "+availableMiners.getElementAt(k).toLowerCase());
			if(availableMiners.getElementAt(k).toLowerCase().contains("ccminer"))
				Collections.addAll(fullAlgoList,ccMinerAlgos);
			//if(availableMiners.getElementAt(k).toLowerCase().contains("claymore"))
			//	Collections.addAll(fullAlgoList,claymoreAlgos);
			if(availableMiners.getElementAt(k).toLowerCase().contains("cryptodredge"))
				Collections.addAll(fullAlgoList,cryptodredgeAlgos);
			if(availableMiners.getElementAt(k).toLowerCase().contains("z-enemy"))
				Collections.addAll(fullAlgoList,zenemyAlgos);
		}

		temp.addAll(fullAlgoList);
		fullAlgoList.clear();
		fullAlgoList.addAll(temp);
		Collections.sort(fullAlgoList);
		
		return fullAlgoList.toArray(new String[0]);
	}

	public static void writeConsole(String newLine)
	{
		newLine = newLine.replaceAll("\\[01;37m","").replaceAll("\\[32m","").replaceAll("\\[0m","").replaceAll("\\[22;31m","").replaceAll("\\[22;33m","")
				.replaceAll("\\[01;30m","").replaceAll("\\[33m","").replaceAll("\\[31m","").replaceAll("\\[22;37m","").replaceAll("\\[22;32m","").replaceAll("\\[36m","");
		console += newLine+"\n";
		
		if(console.length() > consoleMaxLength)
			console = console.substring(console.indexOf("\n")+1);
		
		gui.getConsoleTextArea().setText(console);
		gui.getConsoleTextArea().setCaretPosition(gui.getConsoleTextArea().getDocument().getLength());
	}
	
	public static void writeMonitor(String newLine)
	{
		newLine = newLine.replaceAll("\\[01;37m","").replaceAll("\\[32m","").replaceAll("\\[0m","").replaceAll("\\[22;31m","").replaceAll("\\[22;33m","")
				.replaceAll("\\[01;30m","").replaceAll("\\[33m","").replaceAll("\\[31m","").replaceAll("\\[22;37m","").replaceAll("\\[22;32m","").replaceAll("\\[36m","");
		monitor += newLine+"\n";
		
		if(monitor.length() > monitorMaxLength)
			monitor = monitor.substring(monitor.indexOf("\n")+1);
		
		gui.getMonitorTextArea().setText(monitor);
		gui.getMonitorTextArea().setCaretPosition(gui.getMonitorTextArea().getDocument().getLength());
	}
	
	public static boolean checkIfContains(String[] myStringArray, String stringToLocate) {
	    for (String element:myStringArray ) {
	        if ( element.equals( stringToLocate)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static void extractFile(String resourceName)
	{
		try
		{
			File tempFile = new File(jarFile.getParentFile()+"/"+resourceName);
			tempFile.deleteOnExit();
			Files.copy(Wrapper.class.getResourceAsStream(resourceName),Paths.get(tempFile.getCanonicalPath()), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static ArrayList<String> readFile(String fileName)
	{
		String tableData = "";
		String line = "";
		ArrayList<String> fileContent = new ArrayList<String>();
		
		try
		{
			File saveFile = new File(homeDir+"/GUIMiner/"+fileName);
		
			if(saveFile.exists())
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(saveFile),"UTF-8"));
			
				while((line = br.readLine()) != null)
				{
					if(!line.contains("%"))
					{
						tableData += line;
					}
				}
				br.close();
								
				for(int k = 0; k < tableData.split(";").length; k++)
				{
					fileContent.add(tableData.split(";")[k]);
				}
			}
					
		} catch (IOException e){}	
		
		return fileContent;
	}
	
	public static void clearFile(String fileName)
	{	
		try
		{
			File saveFile = new File(homeDir+"/GUIMiner/"+fileName);
			
			if(!saveFile.exists())
			{
				new File(homeDir+"/GUIMiner/").mkdirs();
				saveFile.createNewFile();
			}
			else
				new FileOutputStream(saveFile,false).close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addToFile(String fileName, String content)
	{	
		try
		{
			File saveFile = new File(homeDir+"/GUIMiner/"+fileName);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFile,true),"UTF-8"));
					
			bw.write(content);
			bw.newLine();
			bw.close();
			return;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static double round(double value, int places)
	{
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static void setDevices(int[] newDevices)
	{
		if(newDevices.length == 0)
			return;
		
		String temp = "";
		for(int k = 0; k < newDevices.length; k++)
			temp += newDevices[k]+",";
		
		devices = temp.substring(0,temp.length()-1);
	}
	
	public static String getDevices()
	{
		return devices;
	}
	
	public static GUI getGUI()
	{
		return gui;
	}
	
	public static void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	public static String getPassword()
	{
		return password;
	}
	
	public static void setUsername(String newUsername)
	{
		username = newUsername;
	}
	
	public static String getUsername()
	{
		return username;
	}
	
	public static void setPoolURL(String newPoolURL)
	{
		poolURL = newPoolURL;
	}
	
	public static String getPoolURL()
	{
		return poolURL;
	}
	
	public static void setAlgo(String newAlgo)
	{
		algo = newAlgo;
	}
	
	public static String getAlgo()
	{
		return algo;
	}
	
	public static void setAdvCMDText(String newAdvCMD)
	{
		advCMDText = newAdvCMD;
	}
	
	public static String getAdvCMDText()
	{
		return advCMDText;
	}
	
	public static boolean isMinerRunning()
	{
		return isMinerRunning;
	}
}

class Miner
{
	String executableName = "";
	String[] algos = new String[0];
	
	Miner()
	{
		
	}
}
