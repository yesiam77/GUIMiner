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
                        	Wrapper.stopMiner();
                    		Wrapper.clearFile("save.dat");
                    		
                    		for(int k = 0; k < Wrapper.getAvailableConfigs().size(); k++)
                    		{
	                    		Wrapper.addToFile("save.dat",Wrapper.getAvailableConfigs().get(k).getConfigName()+";");
	                    		Wrapper.addToFile("save.dat",Wrapper.getAvailableConfigs().get(k).getAlgo()+";");
	                    		Wrapper.addToFile("save.dat",Wrapper.getAvailableConfigs().get(k).getUrl()+";");
	                    		Wrapper.addToFile("save.dat",Wrapper.getAvailableConfigs().get(k).getUser()+";");
	                    		Wrapper.addToFile("save.dat",Wrapper.getAvailableConfigs().get(k).getPass()+";");
	                    		Wrapper.addToFile("save.dat",Wrapper.getAvailableConfigs().get(k).getAdv()+";");
                    		}
                    		
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
