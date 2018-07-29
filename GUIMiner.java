import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.File;

/**
 *
 * @author yesiam77
 */

public class GUIMiner {
	
	public static GUI window = new GUI();
	
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
                {	
					System.out.println("Hello");
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
                    window.setTitle("GUI Miner");
                    window.setVisible(true);
                    window.setMinimumSize(new Dimension(1000,450));
                    window.addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowClosing(java.awt.event.WindowEvent e)
                        {
                        	String algo = "";
                        	if(Wrapper.getGUI().getAlgoCombobox().getSelectedItem().toString() != null)
                        		algo = Wrapper.getGUI().getAlgoCombobox().getSelectedItem().toString();
                        		
                        	String poolURL = Wrapper.getGUI().getPoolURLField().getText();
                        	String username = Wrapper.getGUI().getUsernameField().getText();
                        	String password = Wrapper.getGUI().getPasswordField().getText();
                        	String advCMD = Wrapper.getGUI().getAdvCMDField().getText();
                        	
                        	if(algo.isEmpty())
                        		algo = "#";
                        	if(poolURL.isEmpty())
                        		poolURL = "stratum+tcp://";
                        	if(username.isEmpty())
                        		username = "#";
                        	if(password.isEmpty())
                        		password = "#";
                        	if(advCMD.isEmpty())
                        		advCMD = "#";

                        	Wrapper.stopMiner();
                    		Wrapper.clearFile("save.dat");
                    		Wrapper.addToFile("save.dat",algo+";");
                    		Wrapper.addToFile("save.dat",poolURL+";");
                    		Wrapper.addToFile("save.dat",username+";");
                    		Wrapper.addToFile("save.dat",password+";");
                    		Wrapper.addToFile("save.dat",advCMD+";");
                    		
                    		Wrapper.clearFile("settings.dat");
                    		//Wrapper.addToFile("settings.dat",algo+";");
                    		
                    		System.out.println("Saved run data");
                    		System.out.println("Goodbye");
                    		System.exit(0);
                        }
                    });
                    
                    File jarFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                    Wrapper.prepare(window,jarFile);
                    
                } catch (Exception e) {
                	e.printStackTrace();
                }
			}
		});
	}
    
}
