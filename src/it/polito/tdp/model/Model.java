package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.dao.EsameDAO;

public class Model {

	// Esami letti dal database
	private List<Esame> esami;

	// Gestione della ricorsione
	private List<Esame> best;
	private double media_best;

	public Model() {
		EsameDAO dao = new EsameDAO();
		this.esami = dao.getTuttiEsami();
	    // Collections.sort(this.esami);
	}

	/**
	 * Restituisce la combinazione di corsi richiesti con media massima
	 * 
	 * @param numeroCrediti
	 * @return elenco dei corsi ottimale o null se non esiste alcuna combinazione
	 *         che somma numCrediti
	 */
	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		best = null;
		media_best = 0.0;
		Set<Esame> parziale = new HashSet<>();
		cerca(parziale, 0, numeroCrediti);
		return best;
	}

	private void cerca(Set<Esame> parziale, int L, int m) {

		// casi terminali
		int crediti = sommaCrediti(parziale);
		if (crediti > m)
			return;
		if (crediti == m) {
			double media = calcolaMedia(parziale);

			if (media > media_best) {
				best = new ArrayList<Esame>(parziale);
			} else {
				return;
			}
		}

		// di sicuro crediti<m
		if (L == esami.size()) {
			return;
		}

		// generiamo sotto-problemi
		cerca(parziale, L + 1, m);

		// provo ad aggiungerlo
		parziale.add(esami.get(L));
		cerca(parziale, L + 1, m);
		parziale.remove(esami.get(L));

	}

	private double calcolaMedia(Set<Esame> parziale) {
		double media = 0;
		int crediti = 0;
		for (Esame e : parziale) {
			media += e.getVoto() * e.getCrediti();
			crediti += e.getCrediti();
		}
		return media / crediti;
	}

	private int sommaCrediti(Set<Esame> parziale) {
		int somma = 0;
		for (Esame e : parziale)
			somma += e.getCrediti();
		return somma;
	}

}
