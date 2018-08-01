import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author yesiam77
 */

@SuppressWarnings("serial")
public class GUI extends JFrame {

    public GUI() {
        super("GUI Miner");
        ImageIcon image1 = new ImageIcon(getClass().getResource("Icon.png"));
        Image img = image1.getImage().getScaledInstance(image1.getIconWidth(),image1.getIconHeight(),Image.SCALE_SMOOTH);
        
        super.setIconImage(img);
        
        try
        {
        	for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            	if ("Windows".equals(info.getName()))
            	{
            		UIManager.setLookAndFeel(info.getClassName());
                    break;
            	}
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        initComponents();
    }
    
    public JTabbedPane getTabPane() {
    	return Tabs;
    }
    
    public JList<String> getGPUsAvailableList() {
    	return GPUsAvailableList;
    }
    
    public JComboBox<String> getAlgoCombobox() {
    	return AlgorithmCombobox;
    }
    
    public JTextArea getConsoleTextArea() {
    	return ConsoleTextArea;
    }
    
    public JTextArea getMonitorTextArea() {
    	return jTextArea1;
    }
    
    public JButton getStartMinerButton() {
    	return StartMinerButton;
    }
    
    public JTextField getPoolURLField() {
    	return PoolURLTextInput;
    }
    
    public JTextField getUsernameField() {
    	return UsernameTextInput;
    }
    
    public JTextField getPasswordField() {
    	return PasswordTextInput;
    }
    
    public JTextField getAdvCMDField() {
    	return AdvancedCommandlineTextInput;
    }
    
    public JTextField getTotalHashrateField() {
    	return TotalHashrateField;
    }
    
    public JTextField getTotalGPUsHashingField() {
    	return TotalGPUsHashingField;
    }
    
    public JTextField getAlgorithmField() {
    	return AlgorithmField;
    }
    
    public JTextField getAvgTempField() {
    	return AvgTempField;
    }

    @SuppressWarnings("deprecation")
	private void initComponents() {

        jPanel1 = new JPanel();
        Tabs = new JTabbedPane();
        jPanel2 = new JPanel();
        jSplitPane2 = new JSplitPane();
        jScrollPane2 = new JScrollPane();
        GPUsIntensityList = new JList<>();
        jLabel10 = new JLabel();
        jSplitPane3 = new JSplitPane();
        jScrollPane1 = new JScrollPane();
        GPUsAvailableList = new JList<>();
        jLabel9 = new JLabel();
        jSplitPane1 = new JSplitPane();
        jLabel1 = new JLabel();
        jPanel4 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        PoolURLTextInput = new JTextField();
        UsernameTextInput = new JTextField();
        PasswordTextInput = new JTextField();
        StartMinerButton = new JButton();
        SelectConfigButton = new javax.swing.JButton();
        AdvancedCommandlineTextInput = new JTextField();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel11 = new JLabel();
        AlgorithmCombobox = new JComboBox<>();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        MonitorPane = new JPanel();
        jSplitPane4 = new JSplitPane();
        jPanel3 = new JPanel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jLabel15 = new JLabel();
        TotalHashrateField = new JTextField();
        TotalGPUsHashingField = new JTextField();
        AlgorithmField = new JTextField();
        AvgTempField = new JTextField();
        jPanel7 = new JPanel();
        jScrollPane3 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jPanel5 = new JPanel();
        jScrollPane4 = new JScrollPane();
        ConsoleTextArea = new JTextArea();
        jPanel6 = new JPanel();
        HelpScrollPane = new JScrollPane();
        HelpTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);

        GPUsIntensityList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        GPUsIntensityList.setNextFocusableComponent(AlgorithmCombobox);
        jScrollPane2.setViewportView(GPUsIntensityList);
        GPUsIntensityList.getAccessibleContext().setAccessibleName("GPUsIntensityList");

        jSplitPane2.setRightComponent(jScrollPane2);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel10.setText("GPU Intensity");
        jSplitPane2.setTopComponent(jLabel10);

        jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);

        GPUsAvailableList.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        GPUsAvailableList.setNextFocusableComponent(GPUsIntensityList);
        jScrollPane1.setViewportView(GPUsAvailableList);
        GPUsAvailableList.getAccessibleContext().setAccessibleName("GPUsAvailableList");

        jSplitPane3.setRightComponent(jScrollPane1);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel9.setText("Available GPUS");
        jSplitPane3.setTopComponent(jLabel9);

        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Options");
        jSplitPane1.setTopComponent(jLabel1);

        jLabel2.setText("Pool URL* (-o)");

        jLabel3.setText("Username* (-u)");

        jLabel4.setText("Password (-p)");

        PoolURLTextInput.setText("stratum+tcp://");
        PoolURLTextInput.setToolTipText("This is a required field.");
        PoolURLTextInput.setNextFocusableComponent(UsernameTextInput);


        UsernameTextInput.setToolTipText("This is a required field.");
        UsernameTextInput.setNextFocusableComponent(PasswordTextInput);


        PasswordTextInput.setToolTipText("This is an optional field.");
        PasswordTextInput.setNextFocusableComponent(AdvancedCommandlineTextInput);


        StartMinerButton.setText("Start Miner");
        StartMinerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartMinerButtonActionPerformed(evt);
            }
        });

        AdvancedCommandlineTextInput.setToolTipText("This is an optional field.");
        AdvancedCommandlineTextInput.setNextFocusableComponent(StartMinerButton);


        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("Advanced Commandline Options");

        jLabel6.setForeground(java.awt.Color.gray);
        jLabel6.setText("* required");

        jLabel11.setText("Algorithm* (-a)");

        AlgorithmCombobox.setModel(new DefaultComboBoxModel<>(Wrapper.getFullAlgoList()));
        AlgorithmCombobox.setToolTipText("This is a required field.");
        AlgorithmCombobox.setNextFocusableComponent(PoolURLTextInput);
        
        SelectConfigButton.setText("Select Config");
        SelectConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectConfigButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(SelectConfigButton)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                                    .addComponent(AdvancedCommandlineTextInput))
                                .addGap(10, 10, 10)
                                .addComponent(StartMinerButton))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PoolURLTextInput)
                                    .addComponent(AlgorithmCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UsernameTextInput, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PasswordTextInput, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(AlgorithmCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PoolURLTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(UsernameTextInput))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PasswordTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(StartMinerButton)
                        .addComponent(AdvancedCommandlineTextInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SelectConfigButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PoolURLTextInput.getAccessibleContext().setAccessibleName("PoolURLTextInput");
        UsernameTextInput.getAccessibleContext().setAccessibleName("UsernameTextInput");
        PasswordTextInput.getAccessibleContext().setAccessibleName("PasswordTextInput");
        PasswordTextInput.getAccessibleContext().setAccessibleDescription("This is an optional field.");
        StartMinerButton.getAccessibleContext().setAccessibleDescription("Push this to start the miner.");
        AdvancedCommandlineTextInput.getAccessibleContext().setAccessibleName("AdvancedCommandlineTextInput");
        AdvancedCommandlineTextInput.getAccessibleContext().setAccessibleDescription("This is an optional field.");
        AlgorithmCombobox.getAccessibleContext().setAccessibleName("AlgorithmCombobox");

        jSplitPane1.setRightComponent(jPanel4);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Options");
        jSplitPane1.setTopComponent(jLabel7);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel8.setText("Options");
        jSplitPane1.setTopComponent(jLabel8);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSplitPane1, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1)
                    .addComponent(jSplitPane3, GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        Tabs.addTab("Settings", jPanel2);

        jSplitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel12.setText("Total Hashrate");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel13.setText("# of GPUs Hashing");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel14.setText("Algorithm");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel15.setText("Avg Temp (C)");

        TotalHashrateField.setEditable(false);
        TotalHashrateField.setText("N/A");

        TotalGPUsHashingField.setEditable(false);
        TotalGPUsHashingField.setText("N/A");

        AlgorithmField.setEditable(false);
        AlgorithmField.setText("N/A");

        AvgTempField.setEditable(false);
        AvgTempField.setText("N/A");

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(TotalHashrateField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalGPUsHashingField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                .addGap(199, 199, 199)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jLabel14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(AvgTempField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlgorithmField, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(TotalHashrateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlgorithmField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(TotalGPUsHashingField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(AvgTempField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jSplitPane4.setTopComponent(jPanel3);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        );

        jSplitPane4.setRightComponent(jPanel7);

        GroupLayout MonitorPaneLayout = new GroupLayout(MonitorPane);
        MonitorPane.setLayout(MonitorPaneLayout);
        MonitorPaneLayout.setHorizontalGroup(
            MonitorPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4)
        );
        MonitorPaneLayout.setVerticalGroup(
            MonitorPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4)
        );

        Tabs.addTab("Monitor", MonitorPane);

        ConsoleTextArea.setEditable(false);
        ConsoleTextArea.setColumns(20);
        ConsoleTextArea.setRows(5);
        jScrollPane4.setViewportView(ConsoleTextArea);

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        Tabs.addTab("Console", jPanel5);

        HelpTextArea.setEditable(false);
        HelpTextArea.setColumns(20);
        HelpTextArea.setRows(5);
        HelpTextArea.setText("------------------------------CCMiner----------------------------------------------------------------\n>>> Command Line Interface Arguments <<<\n\n  -a, --algo=ALGO       specify the algorithm to use\n                          allium      use to mine Garlic\n                          bastion     use to mine Joincoin\n                          bitcore     use to mine Bitcore's Timetravel10\n                          blake       use to mine Saffroncoin (Blake256)\n                          blakecoin   use to mine Old Blake 256\n                          blake2s     use to mine Nevacoin (Blake2-S 256)\n                          bmw         use to mine Midnight\n                          cryptolight use to mine AEON cryptonight variant 1 (MEM/2)\n                          cryptonight use to mine original cryptonight\n                          c11/flax    use to mine Chaincoin and Flax\n                          decred      use to mine Decred 180 bytes Blake256-14\n                          deep        use to mine Deepcoin\n                          dmd-gr      use to mine Diamond-Groestl\n                          equihash    use to mine ZEC, HUSH and KMD\n                          fresh       use to mine Freshcoin\n                          fugue256    use to mine Fuguecoin\n                          groestl     use to mine Groestlcoin\n                          hsr         use to mine Hshare\n                          jackpot     use to mine Sweepcoin\n                          keccak      use to mine Maxcoin\n                          keccakc     use to mine CreativeCoin\n                          lbry        use to mine LBRY Credits\n                          luffa       use to mine Joincoin\n                          lyra2       use to mine CryptoCoin\n                          lyra2v2     use to mine Vertcoin\n                          lyra2z      use to mine Zerocoin (XZC)\n                          monero      use to mine Monero (XMR)\n                          myr-gr      use to mine Myriad-Groest\n                          neoscrypt   use to mine FeatherCoin, Trezarcoin, Orbitcoin, etc\n                          nist5       use to mine TalkCoin\n                          penta       use to mine Joincoin / Pentablake\n                          phi1612     use to mine Seraph\n                          phi2        use to mine LUXCoin\n                          polytimos   use to mine Polytimos\n                          quark       use to mine Quarkcoin\n                          qubit       use to mine Qubit\n                          scrypt      use to mine Scrypt coins (Litecoin, Dogecoin, etc)\n                          scrypt:N    use to mine Scrypt-N (:10 for 2048 iterations)\n                          scrypt-jane use to mine Chacha coins like Cache and Ultracoin\n                          s3          use to mine 1coin (ONE)\n                          sha256t     use to mine OneCoin (OC)\n                          sia         use to mine SIA\n                          sib         use to mine Sibcoin\n                          skein       use to mine Skeincoin\n                          skein2      use to mine Woodcoin\n                          skunk       use to mine Signatum\n                          sonoa       use to mine Sono\n                          stellite    use to mine Stellite (a cryptonight variant)\n                          timetravel  use to mine MachineCoin\n                          tribus      use to mine Denarius\n                          x11evo      use to mine Revolver\n                          x11         use to mine DarkCoin\n                          x12         use to mine GalaxyCash\n                          x13         use to mine X13\n                          x14         use to mine X14\n                          x15         use to mine Halcyon\n                          x16r        use to mine Raven\n                          x16s        use to mine Pigeon and Eden\n                          x17         use to mine X17\n                          vanilla     use to mine Vanilla (Blake256)\n                          veltor      use to mine VeltorCoin\n                          whirlpool   use to mine Joincoin\n                          wildkeccak  use to mine Boolberry (Stratum only)\n                          zr5         use to mine ZiftrCoin\n\n  -d, --devices         gives a comma separated list of CUDA device IDs\n                        to operate on. Device IDs start counting from 0!\n                        Alternatively give string names of your card like\n                        gtx780ti or gt640#2 (matching 2nd gt640 in the PC).\n\n  -i, --intensity=N[,N] GPU threads per call 8-25 (2^N + F, default: 0=auto)\n                        Decimals and multiple values are allowed for fine tuning\n      --cuda-schedule   Set device threads scheduling mode (default: auto)\n  -f, --diff-factor     Divide difficulty by this factor (default 1.0)\n  -m, --diff-multiplier Multiply difficulty by this value (default 1.0)\n  -o, --url=URL         URL of mining server\n  -O, --userpass=U:P    username:password pair for mining server\n  -u, --user=USERNAME   username for mining server\n  -p, --pass=PASSWORD   password for mining server\n      --cert=FILE       certificate for mining server using SSL\n  -x, --proxy=[PROTOCOL://]HOST[:PORT]  connect through a proxy\n  -t, --threads=N       number of miner threads (default: number of nVidia GPUs in your system)\n  -r, --retries=N       number of times to retry if a network call fails\n                          (default: retry indefinitely)\n  -R, --retry-pause=N   time to pause between retries, in seconds (default: 15)\n      --shares-limit    maximum shares to mine before exiting the program.\n      --time-limit      maximum time [s] to mine before exiting the program.\n  -T, --timeout=N       network timeout, in seconds (default: 300)\n  -s, --scantime=N      upper bound on time spent scanning current work when\n                        long polling is unavailable, in seconds (default: 5)\n      --submit-stale    ignore stale job checks, may create more rejected shares\n  -n, --ndevs           list cuda devices\n  -N, --statsavg        number of samples used to display hashrate (default: 30)\n      --no-gbt          disable getblocktemplate support (height check in solo)\n      --no-longpoll     disable X-Long-Polling support\n      --no-stratum      disable X-Stratum support\n  -q, --quiet           disable per-thread hashmeter output\n      --no-color        disable colored output\n  -D, --debug           enable debug output\n  -P, --protocol-dump   verbose dump of protocol-level activities\n  -b, --api-bind=port   IP:port for the miner API (default: 127.0.0.1:4068), 0 disabled\n      --api-remote      Allow remote control, like pool switching, imply --api-allow=0/0\n      --api-allow=...   IP/mask of the allowed api client(s), 0/0 for all\n      --max-temp=N      Only mine if gpu temp is less than specified value\n      --max-rate=N[KMG] Only mine if net hashrate is less than specified value\n      --max-diff=N      Only mine if net difficulty is less than specified value\n      --max-log-rate    Interval to reduce per gpu hashrate logs (default: 3)\n      --pstate=0        will force the Geforce 9xx to run in P0 P-State\n      --plimit=150W     set the gpu power limit, allow multiple values for N cards\n                          on windows this parameter use percentages (like OC tools)\n      --tlimit=85       Set the gpu thermal limit (windows only)\n      --keep-clocks     prevent reset clocks and/or power limit on exit\n      --hide-diff       Hide submitted shares diff and net difficulty\n  -B, --background      run the miner in the background\n      --benchmark       run in offline benchmark mode\n      --cputest         debug hashes from cpu algorithms\n      --cpu-affinity    set process affinity to specific cpu core(s) mask\n      --cpu-priority    set process priority (default: 0 idle, 2 normal to 5 highest)\n  -c, --config=FILE     load a JSON-format configuration file\n                        can be from an url with the http:// prefix\n  -V, --version         display version information and exit\n  -h, --help            display this help text and exit\n\n\nScrypt specific options:\n  -l, --launch-config   gives the launch configuration for each kernel\n                        in a comma separated list, one per device.\n      --interactive     comma separated list of flags (0/1) specifying\n                        which of the CUDA device you need to run at inter-\n                        active frame rates (because it drives a display).\n  -L, --lookup-gap      Divides the per-hash memory requirement by this factor\n                        by storing only every N'th value in the scratchpad.\n                        Default is 1.\n      --texture-cache   comma separated list of flags (0/1/2) specifying\n                        which of the CUDA devices shall use the texture\n                        cache for mining. Kepler devices may profit.\n      --no-autotune     disable auto-tuning of kernel launch parameters\n\nCryptoNight specific options:\n  -l, --launch-config   gives the launch configuration for each kernel\n                        in a comma separated list, one per device.\n      --bfactor=[0-12]  Run Cryptonight core kernel in smaller pieces,\n                        From 0 (ui freeze) to 12 (smooth), win default is 11\n                        This is a per-device setting like the launch config.\n\nWildkeccak specific:\n  -l, --launch-config   gives the launch configuration for each kernel\n                        in a comma separated list, one per device.\n  -k, --scratchpad url  Url used to download the scratchpad cache.\n\n\n  \n------------------------------CryptoDredge-----------------------------------------------------------\nCOMMAND-LINE ARGUMENTS\n\n  -v, --version       Print version information\n  -a, --algo          Specify algorithm to use\n                      allium\n                      blake2s\n                      lyra2v2\n                      lyra2v2-old (see the \"Lyra2REv2 Issues\" item)\n                      lyra2z\n                      neoscrypt\n                      phi\n                      phi2\n                      skein\n                      skunk\n  -d, --device        List of comma-separated device IDs to use for mining.\n                      IDs are numbered 0,1...,N - 1\n  -h, --help          Print help information\n  -i, --intensity     Mining intensity (0 - 6) (default: 6)\n  -o, --url           URL of mining pool\n  -p, --pass          Password/Options for mining pool\n  -u, --user          Username for mining pool\n      --log           Log output to file\n      --no-color      Force color off\n      --no-watchdog   Force watchdog off\n      --cpu-priority  Set process priority in the range 0 (low) to 5 (high)\n                      (default: 3)\n      --api-type      Specify API type to use\n                      ccminer-tcp (TCP)\n                      ccminer-ws (WebSocket)\n                      off\n                      (default: ccminer-tcp)\n  -b, --api-bind      IP:port for the miner API, 0 disabled\n                      (default: 127.0.0.1:4068)\n      --retries       N number of times to retry if a network call fails\n                      (default: retry indefinitely)\n      --retry-pause   N time to pause between retries, in seconds (default: 15)\n      --timeout       N network timeout, in seconds (default: 30)\n\n\n\n------------------------------Z-Enemy----------------------------------------------------------------\nOptions:\n  -a, --algo=ALGO         Coin hash algorithm to use:\n\t\t\t\t\taeriumx\t\t(AeriumX: AEX)\n\t\t\t\t\tbitcore\t\t(Bitcore: BTX)\n\t\t\t\t\tx16r\t\t(X16R: Raven)\n\t\t\t\t\tx16s\t\t(X16S: Pigeon)\n\t\t\t\t\tx17\t\t(X17: Verge)\n\t\t\t\t\tc11\t\t(C11: CHC)\n\t\t\t\t\tphi\t\t(PHI1612: Phi)\n\t\t\t\t\tphi2\t\t(PHI2: LUXCoin)\n\t\t\t\t\ttribus\t\t(Tribus: Denarius)\n\t\t\t\t\tpoly\t\t(Poly: Polytimos)\n\t\t\t\t\tskunk\t\t(Skunk: Skunk)\n\t\t\t\t\tsonoax\t\t(Sonoa: SONO)\n\t\t\t\t\ttimetravel\t(Machinecoin: MAC)\n\t\t\t\t\txevan\t\t(Xevan: Transend)\n  -d, --devices           Comma separated list of CUDA devices to use (0,1 etc).\n                          Alternatively takes\n                          string names of your cards like MSI 1080 Ti or MX150#2\n                          (matching 2nd gt640 in the PC)\n  -i  --intensity=N[,N]   GPU intensity 8.0-31.0, decimals allowed (default: 19) \n      --cuda-schedule     set CUDA scheduling option:\n\t                         0: BlockingSync (default)\n\t                         1: Spin\n\t                         2: Yield\n  -f, --diff-factor       Divide difficulty by this factor (default 1.0) \n  -l, --log=FILE          Duplicate output into log file. Sample: --log=logfile.txt\n  -m, --diff-multiplier   Multiply difficulty by this value (default 1.0) \n  -o, --url=URL           URL of mining server\n  -O, --userpass=U:P      username:password pair for mining server\n  -u, --user=USERNAME     username for mining server\n  -p, --pass=PASSWORD     password for mining server\n      --cert=FILE         certificate for mining server using SSL\n  -x, --proxy=[PROTOCOL://]HOST[:PORT]  connect through a proxy\n  -t, --threads=N         number of miner threads (default: number of nVidia GPUs)\n  -r, --retries=N         number of times to retry if a network call fails\n                          (default: retry indefinitely)\n  -R, --retry-pause=N     time to pause between retries, in seconds (default: 30)\n  -T, --timeout=N         network timeout, in seconds (default: 300)\n  -s, --scantime=N        upper bound on time spent scanning current work when\n                          long polling is unavailable, in seconds (default: 60)\n  -n, --ndevs             list cuda devices\n  -N, --statsavg          number of samples used to compute hashrate (default: 30)\n      --no-extranonce     disable extranonce subscribe on stratum\n  -q, --quiet             disable per-thread hashmeter output\n      --no-color          disable colored output\n      --cpu-affinity      set process affinity to cpu core(s), mask 0x3 for cores 0 and 1\n      --cpu-priority      set process priority (default: 3) 0 idle, 2 normal to 5 highest\n  -b, --api-bind=port     IP:port for the miner API (default: 127.0.0.1:4068), 0 disabled\n      --api-allow=...     IP/mask of the allowed api client(s), 0/0 for all\n      --api-remote        Allow remote control, like pool switching\n      --mem-clock=3505    Set the gpu memory boost clock\n      --gpu-clock=1150    Set the gpu engine boost clock\n      --plimit=100        Set the gpu power limit in percentage\n      --tlimit=80         Set the gpu thermal limit in degrees\n  -B, --background        run the miner in the background\n  -c, --config=FILE       load a JSON-format configuration file Sample: --config=config.json\n  -V, --version           display version information and exit\n  -h, --help              display this help text and exit\n\n");
        HelpTextArea.setCaretPosition(0);
        HelpScrollPane.setViewportView(HelpTextArea);

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(HelpScrollPane, GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(HelpScrollPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        Tabs.addTab("Help", jPanel6);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Tabs)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                     

    private void StartMinerButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	if(AlgorithmCombobox.getSelectedItem() != null)
        	Wrapper.setAlgo(AlgorithmCombobox.getSelectedItem().toString());
    		
    	Wrapper.setDevices(GPUsAvailableList.getSelectedIndices());
    	Wrapper.setPoolURL(PoolURLTextInput.getText());
    	Wrapper.setUsername(UsernameTextInput.getText());
    	Wrapper.setPassword(PasswordTextInput.getText());
        Wrapper.setAdvCMDText(AdvancedCommandlineTextInput.getText());
    	
        if(Wrapper.isMinerRunning())
        	Wrapper.stopMiner();
        else if(Wrapper.checkReady())
        	Wrapper.startMiner();
    	else
    		JOptionPane.showMessageDialog(this,"You must fill in all required fields before starting the miner!");
    }
    
    private void SelectConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	if(Wrapper.getConfigSelector() == null)
    	{
    		ConfigSelector cs = new ConfigSelector();
    		Wrapper.setConfigSelector(cs);
    		Point loc = Wrapper.getGUI().getLocationOnScreen();
    		Dimension dim = Wrapper.getGUI().getSize();
			cs.setLocation((int)(loc.getX()+dim.getWidth()/2-cs.getSize().width/2),(int)(loc.getY()+dim.getHeight()/2-cs.getSize().height/2));
			cs.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			cs.setResizable(false);
    		cs.setVisible(true);
    	}
    	else
    	{
    		Wrapper.getConfigSelector().setState(NORMAL);
    		Wrapper.getConfigSelector().requestFocus();
    	}
    }


    private JTextField AdvancedCommandlineTextInput;
    private JComboBox<String> AlgorithmCombobox;
    private JTextField AlgorithmField;
    private JTextField AvgTempField;
    private JTextArea ConsoleTextArea;
    private JList<String> GPUsAvailableList;
    private JList<String> GPUsIntensityList;
    private JScrollPane HelpScrollPane;
    private JTextArea HelpTextArea;
    private JPanel MonitorPane;
    private JTextField PasswordTextInput;
    private JTextField PoolURLTextInput;
    private JButton StartMinerButton;
    private JButton SelectConfigButton;
    private JTabbedPane Tabs;
    private JTextField TotalGPUsHashingField;
    private JTextField TotalHashrateField;
    private JTextField UsernameTextInput;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JSplitPane jSplitPane1;
    private JSplitPane jSplitPane2;
    private JSplitPane jSplitPane3;
    private JSplitPane jSplitPane4;
    private JTextArea jTextArea1;
}

@SuppressWarnings("serial")
class ConfigSelector extends JFrame {

    public ConfigSelector() {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        AvailableConfigsCombobox = new javax.swing.JComboBox<>();
        SaveCurrentConfigButton = new javax.swing.JButton();
        RenameSelectedConfigButton = new javax.swing.JButton();
        DeleteSelectedConfigButton = new javax.swing.JButton();
        SwitchConfigsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                Wrapper.setConfigSelector(null);
            }
        });

        AvailableConfigsCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(Wrapper.getConfigNames(Wrapper.getAvailableConfigs()).toArray(new String[0])));

        SaveCurrentConfigButton.setText("Save Current");
        SaveCurrentConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	SaveCurrentConfigButtonActionPerformed(evt);
            }
        });
        
        RenameSelectedConfigButton.setText("Rename");
        RenameSelectedConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	RenameSelectedConfigButtonActionPerformed(evt);
            }
        });
        
        DeleteSelectedConfigButton.setText("Delete");
        DeleteSelectedConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	DeleteSelectedConfigButtonActionPerformed(evt);
            }
        });
        
        SwitchConfigsButton.setText("Switch Config");
        SwitchConfigsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchConfigsButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Load saved pool info");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AvailableConfigsCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RenameSelectedConfigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeleteSelectedConfigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SwitchConfigsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(SaveCurrentConfigButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveCurrentConfigButton))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(AvailableConfigsCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(RenameSelectedConfigButton)))
                .addGap(10, 10, 10)
                .addComponent(DeleteSelectedConfigButton)
                .addGap(25, 25, 25)
                .addComponent(SwitchConfigsButton)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void SaveCurrentConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	String newName = "##"+JOptionPane.showInputDialog(this,"What would you like to name the current config?");
    	if(newName.equals("##null") || newName.equals("##") || newName.isEmpty())
    		newName = "Config "+(Wrapper.getAvailableConfigs().size()+1);
    	
    		String algo = "";
    		String url = Wrapper.getGUI().getPoolURLField().getText();
    		String user = Wrapper.getGUI().getUsernameField().getText();
    		String pass = Wrapper.getGUI().getPasswordField().getText();
    		String adv = Wrapper.getGUI().getAdvCMDField().getText();
    		
    		if(Wrapper.getGUI().getAlgoCombobox().getSelectedItem() == null)
    			algo = "#";
    		else
    			algo = Wrapper.getGUI().getAlgoCombobox().getSelectedItem().toString();
    		
    		if(algo.isEmpty())
    			algo = "#";
    		    		
    		if(url.isEmpty())
    			url = "#";
    			
    		if(user.isEmpty())
    			user = "#";
    				
    		if(pass.isEmpty())
    			pass = "#";
    			
    		if(adv.isEmpty())
    			adv = "#";
    			
    	Wrapper.getAvailableConfigs().add(0,new Config(newName,algo,url,user,pass,adv));
    	AvailableConfigsCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(Wrapper.getConfigNames(Wrapper.getAvailableConfigs()).toArray(new String[0])));
    }                                                   
    
    private void RenameSelectedConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {    
    	String newName = "##"+JOptionPane.showInputDialog(this,"What would you like to rename \""+Wrapper.getAvailableConfigs().get(AvailableConfigsCombobox.getSelectedIndex()).getConfigName().replaceAll("##","")+"\" to?");
    	if(newName != null && !newName.equals("null") && !newName.isEmpty())
    		Wrapper.getAvailableConfigs().get(AvailableConfigsCombobox.getSelectedIndex()).setConfigName(newName);
    	AvailableConfigsCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(Wrapper.getConfigNames(Wrapper.getAvailableConfigs()).toArray(new String[0])));
    }
    
    private void DeleteSelectedConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        if(JOptionPane.showConfirmDialog(this,"Are you sure you wish to delete \""+Wrapper.getConfigNames(Wrapper.getAvailableConfigs()).get(AvailableConfigsCombobox.getSelectedIndex())+"\"") == 0)
        	Wrapper.getAvailableConfigs().remove(AvailableConfigsCombobox.getSelectedIndex());
        AvailableConfigsCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(Wrapper.getConfigNames(Wrapper.getAvailableConfigs()).toArray(new String[0])));
    }
    
    private void SwitchConfigsButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	if(Wrapper.getAvailableConfigs().size() >= 1)
    	{
	    	Wrapper.getAvailableConfigs().add(0,Wrapper.getAvailableConfigs().remove(AvailableConfigsCombobox.getSelectedIndex()));
	        Wrapper.loadConfig(0);
    	}
        Wrapper.getConfigSelector().dispatchEvent(new WindowEvent(Wrapper.getConfigSelector(),WindowEvent.WINDOW_CLOSING));
        Wrapper.setConfigSelector(null);
    }                                                  

    private javax.swing.JComboBox<String> AvailableConfigsCombobox;
    private javax.swing.JButton DeleteSelectedConfigButton;
    private javax.swing.JButton RenameSelectedConfigButton;
    private javax.swing.JButton SaveCurrentConfigButton;
    private javax.swing.JButton SwitchConfigsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;                
}