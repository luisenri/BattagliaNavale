package client1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MetodiServerImpl extends UnicastRemoteObject implements MetodiServer {
	
	private static final long serialVersionUID = 1L;
	private Gioco gioco; 
	
	protected MetodiServerImpl() throws RemoteException {
		gioco = new Gioco();
	}

	@Override
	public StatoBarche inserisci(String nomeGiocatore,int riga,int colonna) throws RemoteException {
		return gioco.inserisciNave(nomeGiocatore, riga, colonna);
	}

	@Override
	public StatoBarche attacco(String nomeGiocatore,int riga,int colonna) throws RemoteException {
		return gioco.attacco(nomeGiocatore, riga, colonna);
	}

	@Override
	public String msgVincitore() throws RemoteException {
		return gioco.msgVincitore();
	}

	@Override
	public boolean registraGiocatore(String nomeGiocatore, String url) throws RemoteException {
		return gioco.registraGiocatore(nomeGiocatore, url);
	}

//	@Override
//	public void inserisciNaviRandom(CellaServer cella) throws RemoteException {
//		gioco.inserisciNaviRandom(cella.getNomeGiocatore());
//	}
	
	public Gioco getGioco(){
		return gioco;
	}

	@Override
	public String getNomeAvversario(String nomeGiocatore) throws RemoteException {
		return gioco.nomeAvversario(nomeGiocatore);
	}

	@Override
	public boolean reset(String nomeGiocatore) throws RemoteException {
		boolean risp = false;
		risp = gioco.resetNavi(nomeGiocatore);
		return risp;
	}
	
}
