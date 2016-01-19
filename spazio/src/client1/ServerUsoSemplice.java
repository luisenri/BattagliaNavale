package client1;

import java.rmi.Naming;

public class ServerUsoSemplice {
	
	//private final int porta = 2000;
	//private String urlServer = "rmi://127.0.0.1:"+porta+"/server";
	private final String 	PATH_POLICY = Impostazioni.PATH_POLICY;
	private final int 		PORT 		= Impostazioni.PORTA_SERVER;
	private final String 	URL 		= Impostazioni.URL_SERVER;
	
	private MetodiServerImpl serv = null;
	
	public ServerUsoSemplice() {
		//System.setProperty("java.security.policy","file:/C:/Users/PERS/workspacePersonale/BattagliaNavaleServer/src/client1/server.policy");
		System.setProperty("java.security.policy",PATH_POLICY);
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try {
			serv = new MetodiServerImpl();
			java.rmi.registry.LocateRegistry.createRegistry(PORT);
			Naming.rebind(URL, serv);
			
			System.out.println("Server attivo (computer)...");
			System.out.println("In attesa di invocazioni dai client");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
