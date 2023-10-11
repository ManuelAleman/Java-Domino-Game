package org.maleman.proyectos.proyecto4;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Piece> pieces = new ArrayList<>(7);


    public int getTotal(){
        int total = 0;
        for(Piece piece: pieces){
            total += piece.getPiece().getLeft() + piece.getPiece().getRight();
        }
        return total;
    }

    public List<Piece> getPieces(){
        return pieces;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
    }



}
