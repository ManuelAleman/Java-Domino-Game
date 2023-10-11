package org.maleman.proyectos.proyecto4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class GameLogic implements ActionListener {
    private GamePanel gP;
    private PlayerPanel[] pP;
    private List<Piece> pieces;
    private Player[] players = new Player[4];
    private int left, right, skips;
    private int gapN = 0, gap = 30;
    Piece lastPiece;
    JLabel startLabel;

    private int currentPlayer;

    public GameLogic(GamePanel gP, PlayerPanel[] pP) {
        this.gP = gP;
        this.pP = pP;
        pieces = gP.getPieces();

        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }
        listeners();
    }

    public void listeners(){
        gP.getShuffle().addActionListener(this);
        gP.getGive().addActionListener(this);
        gP.getShow().addActionListener(this);
        for( PlayerPanel p: pP){
            p.getPass().addActionListener(this);
        }
    }

    public void startGame() {
        for (int i = 0; i < players.length; i++) {
            for (Piece piece : players[i].getPieces()) {
                if (piece.getPiece().getLeft() == 6 && piece.getPiece().getRight() == 6) {
                    currentPlayer = i;
                    lastPiece = piece;
                    piece.setEnabled(true);

                    startLabel = new JLabel("Player " + (currentPlayer + 1) + " starts");
                    startLabel.setBounds(310, 10, 200, 30);
                    gP.add(startLabel);
                }
            }
        }
        left = 6;
        right = 6;
    }


    public void nextPiece(Piece playedPiece) {
        if(playedPiece == null){
            disablePlayer(currentPlayer);
            currentPlayer++;
            currentPlayer%=4;
            enablePlayer(currentPlayer);
            return;
        }
        int leftValue = playedPiece.getPiece().getLeft();
        int rightValue = playedPiece.getPiece().getRight();
        if(leftValue == left) {
            left = rightValue;
        }else if(rightValue == left){
            left = leftValue;
        } else if(leftValue == right){
            right = rightValue;
        } else if(rightValue == right) {
            right = leftValue;
        }
        disablePlayer(currentPlayer);
        currentPlayer++;
        currentPlayer%=4;
        startLabel.setText("Next player " + (currentPlayer + 1));
        enablePlayer(currentPlayer);
    }

    public void enablePlayer(int playerIndex) {
        boolean canPLace = false;
        for (Piece piece : players[playerIndex].getPieces()) {
            int leftValue = piece.getPiece().getLeft();
            int rightValue = piece.getPiece().getRight();
            if (leftValue == left || rightValue == right || rightValue == left || leftValue == right) {
                piece.setEnabled(true);
                canPLace = true;
            }
        }
        
        if(!canPLace){
            int winner = 0, total = Integer.MAX_VALUE;
            pP[currentPlayer].getPass().setEnabled(true);
            skips++;
            if (skips == 4) {
                for( int i = 0; i < 4; i++){
                    if(total > players[i].getTotal()){
                        total = players[i].getTotal();
                        winner =( i + 1);
                    }
                }
                JOptionPane.showMessageDialog(null, "No one can continue, so the winner is: " + winner +
                        " with " + total + " points");
            }
        }

    }

    public void disablePlayer(int playerIndex) {
        for (Piece piece : players[playerIndex].getPieces()) {
            piece.setEnabled(false);
        }
        pP[currentPlayer].getPass().setEnabled(false);
    }

    public void paintPiecePlaced(Piece piece){

        if(piece.getPiece().getLeft() == left || piece.getPiece().getRight() == left){
            piece.setLocation((gP.getWidth()/2 - 15)+gapN, gP.getHeight()/2 - 30);
            gapN -= 30;
        }else{
            piece.setLocation((gP.getWidth()/2 - 15)+gap, gP.getHeight()/2 - 30);
            gap += 30;
        }

        piece.setEnabled(false);
        piece.removeActionListener(this);
        gP.add(piece);
        gP.repaint();
    }


    public void give(){
        int startingX = 10;
        currentPlayer = 0;
        for (int i=0; i<28; i++){
            Piece aux = pieces.get(i);
            aux.setLocation(startingX , 100);
            startingX += 9;

            aux.setEnabled(false);
            aux.addActionListener(this);
            pP[i%4].add(aux);
            pP[i%4].repaint();

            players[i%4].addPiece(aux);

        }
        gP.repaint();
        startGame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == gP.getShuffle()){
            for(Piece p: pieces){
                gP.remove(p);
            }
            Collections.shuffle(pieces);
            gP.paintPiece(gP.getWidth(), gP.getHeight());
            gP.repaint();
            return;
        }

        if(e.getSource() == gP.getGive()){
            gP.getShuffle().setEnabled(false);
            gP.getGive().setEnabled(false);

            give();
            return;
        }

        if(e.getSource() == gP.getShow()){
            for(Piece p: pieces){
                p.flip();
            }
        }
        for(PlayerPanel p: pP){
            if(e.getSource() == p.getPass()){
                nextPiece(null);
            }
        }

        if (e.getSource() instanceof Piece playedPiece) {
            skips = 0;
            players[currentPlayer].getPieces().remove(playedPiece);
            pP[currentPlayer].remove(playedPiece);
            pP[currentPlayer].repaint();

            paintPiecePlaced(playedPiece);
            if(players[currentPlayer].getPieces().isEmpty()){
                JOptionPane.showMessageDialog(null, "Player " + (currentPlayer + 1) + " wins");
                startLabel.setText("Player  " + (currentPlayer + 1) + " wins!!!");
                return;
            }

            nextPiece(playedPiece);
        }

    }

}
