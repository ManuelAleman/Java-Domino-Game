package org.maleman.proyectos.proyecto4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePanel extends JPanel {
    private List<Piece> pieces = new ArrayList<>(28);
    JButton shuffle;
    JButton give;
    JButton show;

    public GamePanel() {
        createPieces();
        makePanel();
    }

    public void makePanel(){
        setSize(1400, 765);
        setBackground(new Color(157, 252, 165));
        setLayout(null);

        paintPiece(getWidth(), getHeight());

        shuffle = new JButton("Shuffle");
        shuffle.setBounds( 500, 10, 100, 30 );
        add(shuffle);

        give = new JButton("Deal");
        give.setBounds( 650, 10, 100, 30 );
        give.addActionListener( e-> {

        });
        add(give);

        show = new JButton("Show");
        show.setBounds( 800, 10, 100, 30 );
        add(show);

        setVisible(true);
    }

    public void paintPiece(int xW, int yH){
        int xGap = 10;
        int yGap = 70;
        int piecesPerRow = 7;
        int actualRow = 0;

        for (int i = 0; i < 28; i++) {
            Piece piece = pieces.get(i);
            int fichaAncho = 30;
            int fichaAlto = 60;

            int x = (xW - (piecesPerRow * fichaAncho + (piecesPerRow - 1) * xGap)) / 2 + (i % piecesPerRow)
                    * (fichaAncho + xGap);
            int y = (yH - (4 * fichaAlto + 3 * yGap)) / 2 + actualRow * (fichaAlto + yGap);

            if (i % piecesPerRow == piecesPerRow - 1) {
                actualRow++;
            }
            piece.setBounds(x, y, fichaAncho, fichaAlto);
            add(piece);
        }

    }

    public void createPieces(){
        int cont = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < 7; j++) {
                String img = "images/PIECES/" + cont + ".png";
                pieces.add(new Piece(new DominoPiece(i, j), img));
                cont++;
            }
        }

    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public JButton getShuffle() {
        return shuffle;
    }

    public JButton getGive() {
        return give;
    }

    public JButton getShow() {
        return show;
    }
}
