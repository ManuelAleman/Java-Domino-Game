package org.maleman.proyectos.proyecto4;

import javax.swing.*;
import java.awt.*;

public class Piece extends JButton{
    private DominoPiece piece;
    private ImageIcon image;
    private ImageIcon backImg = scaleImg("images/PIECES/backImg.png", 30, 60);
    private boolean isFlipped;

    public Piece(DominoPiece piece, String imagen) {
        this.piece = piece;
        image = scaleImg(imagen, 30, 60);
        this.setIcon(backImg);
        this.isFlipped = false;
    }

    public ImageIcon scaleImg(String ico,int Ancho,int Alto)
    {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        return new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
                Alto, Image.SCALE_SMOOTH));
    }

    public void flip(){
        if(isFlipped){
            this.setIcon(image);
            isFlipped = false;
        }else{
            this.setIcon(backImg);
            isFlipped = true;
        }
    }

    public DominoPiece getPiece() {
        return piece;
    }




}