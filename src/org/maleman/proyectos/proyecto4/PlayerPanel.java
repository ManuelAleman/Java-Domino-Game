package org.maleman.proyectos.proyecto4;

import javax.swing.*;

public class PlayerPanel extends JPanel {
    JButton pass;
    public PlayerPanel() {
        makePanel();
    }

    public void makePanel(){
        setSize(300, 300);
        setLayout(null);

        pass = new JButton("Pasar");
        pass.setBounds( 10, 10, 100, 30 );
        pass.setEnabled(false);
        add(pass);


        setVisible(true);
    }

    public JButton getPass() {
        return pass;
    }

}
