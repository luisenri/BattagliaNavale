package client1;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Gioco {
		
	private static final int MAX_GIOCATORI = 2;
	private static int numeroGiocatori = 0;
	private List<Giocatore> giocatori;
	private static int turno = 0;

	public Gioco() throws RemoteException{
		this.giocatori = new ArrayList<Giocatore>();
	}
	
	/***
	 * Registra un giocatore
	 * 
	 */

	public boolean registraGiocatore(String nomeGiocatore, String url) {
		boolean risp = false;
		if(numeroGiocatori<MAX_GIOCATORI){
			int id = numeroGiocatori++;
			risp = giocatori.add(new Giocatore(nomeGiocatore,id,url));
			if(risp)
				System.out.println("Giocatore "+nomeGiocatore+" registrato");
		}
		else
			System.out.println("Raggiunto il limite massimo dei giocatori.");
		return risp;
	}
	
	public List<Giocatore> getGiocatori(){
		return giocatori;
	}

	/**
	 * inserisce la nave seconto del giocatore passato come parametro e nelle posizioni indicate
	 */
	public StatoBarche inserisciNave(String nomeGiocatore, int riga, int colonna) throws RemoteException {
		StatoBarche stato = null;
		
		for(Giocatore giocatore: giocatori){
			if(giocatore.getNome().equals(nomeGiocatore)){
				stato = giocatore.inserisciNave(riga, colonna);
				stampaGriglie();
				return stato;
			}
		}
		return stato;
	}
	
	/***
	 * attacca l'avversario
	 * @param idAttaccante id di chi attacca
	 * @param riga 
	 * @param colonna
	 * @return restituisce la cella con lo stato ACQUA o BARCA_COLPITA 
	 */
	public StatoBarche attacco(String nomeGiocatore, int riga, int colonna) {
		StatoBarche stato = null;
		int idAttaccante = idGiocatore(nomeGiocatore);
		int turnoAttuale = turno%2;
		if(idAttaccante == turnoAttuale){//entra solo s'e' il proprio turno
			int idAvversario = turnoAttuale == 0 ? 1 : 0;
			stato = giocatori.get(turnoAttuale).attaccaAvversario(giocatori.get(idAvversario), riga, colonna);
			turno++;
			stampaGriglie();
			return stato;
		}else
			System.out.println("Turno del giocatore "+turno%2+" non e' il tuo turno");
		
		return stato;
	}
	
	private int idGiocatore(String nomeGiocatore){
		int idGiocatore = -1;
		for(Giocatore giocatore: giocatori){
			if(giocatore.getNome().equalsIgnoreCase(nomeGiocatore))
				return giocatore.getIdGiocatore();
		}
		return idGiocatore;
	}

	/**
	 * da modificare: deve confrontare le navi colpite quando finiscono i colpi a disposizione
	 */
	public String msgVincitore() {
		String msg = "";
//		int idVincitore = -1;
//		
//		if(giocatori.get(0).getColpiRimasti()<= 0 && giocatori.get(1).getColpiRimasti() <= 0){
//			idVincitore = giocatori.get(0).getNaviColpiteDaAvversario() > giocatori.get(1).getNaviColpiteDaAvversario()? 0 : 1;
//			msg = "vincitore Giocatore "+giocatori.get(idVincitore).getNome()+" Navi colpite = "+giocatori.get(idVincitore).getNaviColpiteDaAvversario();
//		}
		return msg;
	}
	
	/***
	 * inserisce navi di un giocatore
	 
	public void inserisciNaviRandom(String nomeGiocatore){
		for(Giocatore giocatore: giocatori){
			if(giocatore.getNome().equalsIgnoreCase(nomeGiocatore)){
				giocatore.inserisciNaviRandom();
			}
		}
		stampaGriglie();
	}*/
	
	/***
	 * stampa le griglie
	 */
	public void stampaGriglie(){
		for(Giocatore giocatore : giocatori){
			System.out.println(giocatore.getNome()+" "+giocatore.getIdGiocatore()+"\n"+giocatore.getGriglia().stampaGriglia());	
		}
	}

	public boolean resetNavi(String nomeGiocatore) {
		boolean risp = false;
		for(Giocatore giocatore: giocatori){
			if(giocatore.getNome().equalsIgnoreCase(nomeGiocatore)){
				risp = giocatore.resetNavi();
			}
		}
		return risp;
	}
}
