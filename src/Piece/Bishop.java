package Piece;

import Main.GamePanel;
import Main.Type;

public class Bishop extends Piece{

    public Bishop(int colour, int col, int row){
        super(colour,col,row);

        type = Type.BISHOP;

        if(colour == GamePanel.WHITE){
            image = getImage("/piece/W-Bishop");
        }
        else{
            image = getImage("/piece/B-Bishop");
        }
    }
    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false) {

            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
                if(isValidSquare(targetCol,targetRow) && pieceIsOnDiagonalLine(targetCol,targetRow) == false){
                    return true;
                }

            }
        }
        return false;
    }

}
