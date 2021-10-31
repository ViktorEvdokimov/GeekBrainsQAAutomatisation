package HW8.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UI extends JFrame {

    private final int WINDOW_SIZE = 300;
    private JButton[][] buttons;
    public UI(char[][] map){
        setBounds(500, 500, WINDOW_SIZE, WINDOW_SIZE);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        JPanel all = new JPanel();
        all.setLayout(new GridLayout(TicTacToe.SIZE,TicTacToe.SIZE));
        buttons = new JButton[TicTacToe.SIZE][TicTacToe.SIZE];
        for (int i = 0; i < TicTacToe.SIZE; i++) {
            for (int j = 0; j < TicTacToe.SIZE; j++) {
                buttons[i][j] = new JButton(String.valueOf(map[i][j]));
                buttons[i][j].addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonPressEventListener(e);
                    }
                });
                all.add(buttons[i][j]);
            }
            add(all);
            setVisible(true);
        }
    }

    private void buttonPressEventListener (ActionEvent e){
        JButton button = (JButton) e.getSource();
        int a = getSize().width/TicTacToe.SIZE;
        int col = (button.getX() + a/2) / a;
        a = getSize().height/TicTacToe.SIZE;
        int row = (button.getY() + a*2/3) / a;
        TicTacToe.humanTurn(row, col);
    }

    protected void refreshMap (char[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                buttons[i][j].setText(String.valueOf(map[i][j]));
            }
        }
    }

    protected void setWinnMessage (String message){
        setTitle(message);
    }
}
