package it.unibo.model.interfaces;

public interface GridInterface {

    // Ottieni il numero di righe
    int getRows();

    // Ottieni il numero di colonne
    int getCols();

    // Aggiungi un Puyo alla griglia
    boolean addPuyo(PuyoInterface puyo, int x, int y);

    // Rimuovi un Puyo dalla griglia
    void removePuyo(int x, int y);

    // Controlla se una posizione è valida
    boolean isValidPosition(int x, int y);

    // Ottieni un Puyo dalla griglia
    PuyoInterface getPuyo(int x, int y);

    // Controlla se una colonna è piena
    boolean isColumnFull(int x);

    // Controlla se la griglia è piena
    boolean isGridFull();

    // Controlla se una riga è piena
    boolean isRowFull(int y);

    // Metodo per aggiornare la griglia (es. dopo un'esplosione)
    void updateGrid();

    // Stampa lo stato della griglia (per debug)
    void printGrid();

    void clear();
}
