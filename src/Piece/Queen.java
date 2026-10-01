package Piece;

import Main.GamePanel;
import Main.Type;

public class Queen extends Piece{

    public Queen(int colour, int col, int row){
        super(colour,col,row);

        type = Type.QUEEN;

        if(colour == GamePanel.WHITE){
            image = getImage("/piece/W-Queen");
        }
        else{
            image = getImage("/piece/B-Queen");
        }
    }
    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false){

            // Vertical & Horizontal
            if(targetCol == preCol || targetRow == preRow){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol,targetRow) == false){
                    return true;
                }
            }

            // Diagonal
            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol,targetRow) == false){
                    return true;
                }
            }
        }

        return false;
    }
}
