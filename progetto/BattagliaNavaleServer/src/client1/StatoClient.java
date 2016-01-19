package client1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StatoClient extends Remote{
	public void aggiornaStatiClient(int naviRimaste,int naviRimasteAvversario,String messaggio) throws RemoteException;
	public void invioAggiornamentoAttacco(int riga,int colonna,StatoBarche stato) throws RemoteException;
	public void invioMsg(int naviRimaste,String messaggio) throws RemoteException;
	public void invioNomeAvversario(String nome) throws RemoteException;
}
