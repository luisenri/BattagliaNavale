package client1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MetodiServer extends Remote{
	
	public StatoBarche inserisci(String nomeGiocatore,int riga,int colonna) throws RemoteException;
	
	public StatoBarche attacco(String nomeGiocatore,int riga,int colonna) throws RemoteException;
	
	public String msgVincitore() throws RemoteException;
	
	public boolean registraGiocatore(String nomeGiocatore, String url) throws RemoteException;

	//public void inserisciNaviRandom(CellaServer cella) throws RemoteException;
	
	public boolean reset(String nomeGiocatore) throws RemoteException;
	
	public String getNomeAvversario(String nomeGiocatore) throws RemoteException;
}
