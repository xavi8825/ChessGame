package Piece;

import Main.GamePanel;
import Main.Type;


public class Knight extends Piece{

    public Knight(int colour, int col, int row){
        super(colour,col,row);

        type = Type.KNIGHT;

        if(colour == GamePanel.WHITE){
            image = getImage("/piece/W-Knight");
        }
        else{
            image = getImage("/piece/B-Knight");
        }
    }
    public boolean canMove(int targetCol, int targetRow){

        if (isWithinBoard(targetCol,targetRow)){
            // knight can move if its movement ratio of col and row is 1:2 or 2:1
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
}
