package Piece;

import Main.GamePanel;

public class Rook extends Piece{

    public Rook(int colour, int col, int row){
        super(colour,col,row);

        if(colour == GamePanel.WHITE){
            image = getImage("/piece/W-Rook");
        }
        else{
            image = getImage("/piece/B-Rook");
        }
    }
    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false){
            // Rook can move as long as either its col or row is the same
            if(targetCol == preCol || targetRow == preRow){
                 if(isValidSquare(targetCol,targetRow) && pieceIsOnStraightLine(targetCol,targetRow) == false){
                     return true;
                 }
            }
        }
        return false;
    }
}
