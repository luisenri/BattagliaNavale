package client1;


public class Griglia {

	private static int lunghezza = 8;
	private StatoBarche[][] stato;

	public Griglia() {
		this(lunghezza, lunghezza);
	}

	public Griglia(int righe, int colonne) {
		stato = new StatoBarche[righe][colonne];
		this.reset();
	}

	/*public static void main(String[] args) {
		Griglia g = new Griglia();
		System.out.println(g.inserisciNave(1, 2));
		System.out.println(g.attaccaAvversario(2, 2));
		System.out.println(g.stampaGriglia());
	}*/
	public StatoBarche inserisciNave(int riga, int colonna) {
		StatoBarche st = null;
		st = stato[riga][colonna] = StatoBarche.BARCA;
		return st;
	}

	/***
	 * da modificare
	 * 
	 * @param riga
	 * @param colonna
	 * @return
	 */
	public StatoBarche attaccaAvversario(int riga, int colonna) {
        StatoBarche temp = null;

        switch ( stato[riga][colonna] ) {
			case NESSUNO:
				temp = StatoBarche.ACQUA;
				break;
			case BARCA:
				temp = StatoBarche.BARCA_COLPITA;
				break;
			default:
				break;
		}
        if(temp!=null)
        	stato[riga][colonna] = temp;
        return temp;
	}
	
	public String stampaGriglia() {
		String griglia = "";

        for (int i = 0; i < stato.length; i++){
            for (int j = 0; j < stato[0].length; j++) 
                griglia += (stato[i][j] == StatoBarche.NESSUNO) ? i+""+j+"(      )" : i+""+j+"(" + stato[i][j].toString() + ")";//se non c'e' nessuna barca stampa uno spazio
        	griglia += "\n";
        }
        return griglia;
	}

	public boolean reset() {
        for (int i = 0; i < stato.length; i++) {
            for (int j = 0; j < stato[0].length; j++) 
                stato[i][j] = StatoBarche.NESSUNO;
        }
        return true;
	}

}
