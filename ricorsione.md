INPUT: Set di esame di PARTENZA

INPUT: Numero di iscritti

OUTPUT: Set di Esame, sottoinsieme di quello di partenza per cui:

    - Somma Esame.crediti == m
    - Media Esame.voto MAX

Strategia: Generare tutti i possibili sottoinsiemi dell'insieme di partenza

# Approccio 1: genero l'insieme decidendo corso per corso se esso fa parte dell'insieme

**Complessità: 2^N (con N numero di esami)**

Livello L della ricorsione --> quale corso sto decidendo se includere o meno nell'insieme soluzione

Soluzione parziale -> Un sottoinsieme composto dai corsi tra 0 ed L-1.

ESEMPIO: L=4

    soluzione parziale: { 0, 3 }
    soluzione parziale: { 0, 1, 2, 3 }
    soluzione parziale: { }

Generazione del sottoproblema: decidere se inserire l'esame [L] oppure no.

    1. Sotto-problema = soluzione parziale (non aggiungo)
    2. Sotto-problema = soluzione parziale + { L }

Casi terminali:

    - L = max -> non ci sono più corsi
        - somma crediti == m => calcola media
        - somma crediti != m => niente

_Genero tutte le soluzioni e solo se sensata la guardo per il problema. Posso migliorare interrompendo la ricorsione prima della fine._

    - somma crediti == m
        - valuta la media
        - non genera sotto problemi
    - somma crediti > m
        - esce senza generare sotto problemi

**Cosa faccio nel caso terminale?**

Se la somma crediti != m => return (ho perso tempo), se uguale ad m valuto la media, se migliore di quella conosciuta la sostituisco.

# Approccio 2, ad ogni livello aggiungo un corso, devo decidere quale

**Complessità: N! (con N numero di esami)**

Soluzione parziale al livello L: un insieme di L corsi.

Generazione del sotto-problema: aggiungere un nuovo corso all'insieme esistente. Per tutti i corsi (i) possibili (non ancora nell'insieme)

    - Sotto-problema = parziale + corso(i)

**Casi terminali vedi sopra.**
