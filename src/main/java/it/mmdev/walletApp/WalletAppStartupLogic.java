package it.mmdev.walletApp;

import org.springframework.stereotype.Component;

@Component
public class WalletAppStartupLogic {
	
	// logica di startup per validazione iniziale di applicazione
	/*
	 * Esistenza di tutte le varie tabelle su db, parametri in tabella, 
	 * enum vari necessari per l'applicazione (con rispettivi valori anagrafici su db)
	 * */
	public void onApplicationEvent() throws Exception {
		
		// check dei vari enum necessari su db (tuttel le anagrafiche, es. tipi conto, permessi utente, tipi transazioni ecc...)
		
		// check parametri nella tabella dei parametri
		
		
	}

}
