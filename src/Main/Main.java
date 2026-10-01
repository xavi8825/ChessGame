package Main;

import javax.swing.*;

public class  Main {

    public static void main(String[] args){

        /*
         Create a Main Class to start our game.
         Create a Jframe in order to build a chess game
        */

        JFrame window = new JFrame("Simple Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

         /*
         - Add Game panel to the window so we can see the class in our screen.
         - use pack(); to make sure the window can readjust it's size...
         */
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null); // null places it at middle
        window.setVisible(true);
        gp.launchGame();




    }


}



/*
main()

│
▼
Create JFrame

│
▼
Create GamePanel

│
▼
GamePanel constructor
    │
    ├── create Mouse
    ├── create Board
    ├── create all Pieces
    └── copy pieces → simPieces

│
▼
launchGame()

│
▼
run()  (60 times/sec)

│
├── update()
│      │
│      ├── Check mouse
│      ├── Select piece
│      ├── simulate()
│      ├── Validate move
│      ├── Commit move
│      └── Change turn
│
▼
repaint()

│
▼
paintComponent()
      │
      ├── draw board
      ├── draw pieces
      ├── draw highlights
      └── draw messages
 */