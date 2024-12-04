# OOP25puyo-blast
Il gruppo si pone come obiettivo di realizzare un gioco ispirato a Puyo Pop di Sega Superstar Tennis.

Il gioco prevede un campo in cui i "Puyo" (sfere colorate) cadono in una griglia. Il giocatore deve controllare un cannone per sparare una pallina. Quando la pallina colpisce un Puyo singolo, esplode solo quello; se colpisce più Puyo dello stesso colore, esplodono tutti i Puyo dello stesso colore. I livelli si vincono accumulando un punteggio minimo per ottenere 1-3 stelle. Si perde invece quando, una volta riempita la griglia, non si è raggiunta almeno la prima stella.

## Funzionalità minimali

- Menù di gioco e scelta dei livelli
- Gestione dei comandi e degli input del giocatore
- Visualizzazione del punteggio, della griglia di gioco e delle stelle
- Gestione dello stato della partita
- Condizioni di fine gioco 
- Movimento del cannone e della pallina generica
- Esplosione dei Puyo e gestione dei post-esplosioni
- Implementazione della pausa
- Animazioni degli sprite, transizioni fluide e freeze dei puyo
- Punteggio minimo per passare al livello successivo (1-2-3 stelle)

## Funzionalità opzionali

- Cambiare skin dei Puyo e del cannone
- Salvataggio della partita
- Tutorial interattivo
- Memorizzazione dei punteggi locali

## Challenge principali

- Gestione della caduta e delle esplosioni dei Puyo nella griglia
- Implementazione di logiche di esplosione e correzione post-esplosione
- Gestione di più entità (Puyo, cannone, animazioni)
- Gestione della pausa del gioco
- Condizione di sconfitta
- Sincronizzazione degli input dell’utente con la logica di gioco

## Suddivisione del lavoro

- _Chiara De Nardi_: Grafica dei Puyo, automatizzazione della caduta nella griglia, gestione dei livelli, del menù iniziale e della pagina dei comandi
- _Beatrice Di Gregorio_: Grafica del cannone e della barra di caricamento, mappatura input del cannone e visualizzazione del punteggio, gestione della condizione di sconfitta
- _Federica Guiducci_: Grafica di sfondo e avvio del gioco, gestione dello stato della partita, gestione della pausa e freeze dei puyo
- _Aisja Baglioni_: Animazioni degli sprite, gestione delle transizioni, gestione della finestra desktop e implementazione delle logiche di esplosione e post esplosione

