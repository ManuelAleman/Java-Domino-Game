package org.maleman.proyectos.proyecto4;

import javax.swing.*;
import java.awt.*;

public class TableFrame extends JFrame {

    public TableFrame() {
        super("Domino");
        makeIntercafe();
    }

    public void makeIntercafe(){
        setSize(1400, 805);
        setLocationRelativeTo(null);
        setLayout(null);

        GamePanel gP = new GamePanel();
        gP.setLocation(0, 0);
        add(gP);

        PlayerPanel player1 = new PlayerPanel();
        player1.setLocation(0, 0);
        player1.setBackground(new Color(169, 252, 229));
        PlayerPanel player2 = new PlayerPanel();
        player2.setLocation(1100, 0);
        player2.setBackground(new Color(  194, 138, 254 ));
        PlayerPanel player3 = new PlayerPanel();
        player3.setLocation(1100, 465);
        player3.setBackground(new Color(  233, 165, 250 ));
        PlayerPanel player4 = new PlayerPanel();
        player4.setLocation(0, 465);
        player4.setBackground(new Color(254, 236, 165));
        gP.add(player1);
        gP.add(player2);
        gP.add(player3);
        gP.add(player4);

        PlayerPanel[] players = new PlayerPanel[4];
        players[0] = player1;
        players[1] = player2;
        players[2] = player3;
        players[3] = player4;

        new GameLogic(gP, players);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TableFrame();
    }
}
