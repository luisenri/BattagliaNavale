package client1;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Giocatore {
	
	private String nome;
	private int idGiocatore;
	private Griglia griglia;
	private StatoClient statoClient;
	
	private int navi = 9;
	private static int naviColpiteDaAvversario = 0;
	private static int naviInserite = 0;
	
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.griglia = new Griglia();
	}
	
	public Giocatore(String nome,int idGiocatore){
		this(nome);
		this.idGiocatore = idGiocatore;
	}
	public Giocatore(String nome,int idGiocatore,String url){
		this(nome);
		this.idGiocatore = idGiocatore;
		try {
			System.out.println("Url usato: "+ url);
			this.statoClient = (StatoClient) Naming.lookup(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNome(){
		return nome;
	}
	public int getIdGiocatore(){
		return this.idGiocatore;
	}
	
	public Griglia getGriglia(){
		return this.griglia;
	}
	
	public void setGriglia(Griglia griglia){
		this.griglia = griglia;
	}
		
	public int getNaviColpiteDaAvversario(){
		return this.naviColpiteDaAvversario;
	}
	
	public StatoBarche inserisciNave(int riga,int colonna){
		StatoBarche stato = griglia.inserisciNave(riga, colonna);
		if(stato != null) naviInserite++;
		return stato;
	}
	
	public StatoBarche attaccaAvversario(Giocatore avversario,int riga,int colonna){
		StatoBarche stato = null;
		int naviRimaste = 0;
		stato = avversario.griglia.attaccaAvversario(riga, colonna);
		if( stato != null ){
			if( stato == StatoBarche.BARCA_COLPITA){ 
				naviColpiteDaAvversario++;
				naviRimaste = naviInserite - naviColpiteDaAvversario;
			}
			try {
				avversario.statoClient.invioAggiornamentoAttacco(riga,colonna,stato);
				avversario.statoClient.invioMsg(naviRimaste,"Nave Colpita");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return stato;
	}

	public boolean resetNavi() {
		return griglia.reset();
	}
	
	/*public void inserisciNaviRandom(){
		int naviInserite = 0;
		Random random = new Random();
		int lunghezzaGriglia = griglia.getRighe();// da cambiare per quantita' massima delle righe o colonne
		
		while(naviInserite<navi){
			int riga = random.nextInt(lunghezzaGriglia);
			int colonna = random.nextInt(lunghezzaGriglia);
			
			if(this.griglia.controlloCellaVuota(riga, colonna)){
				this.inserisciNave(riga, colonna);
				naviInserite++;
			}
		}
	}*/
	
}
