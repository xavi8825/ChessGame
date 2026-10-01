package Piece;

import Main.GamePanel;
import Main.Type;

public class Pawn extends Piece{

    public Pawn(int colour, int col, int row){
        super(colour, col, row);

        type = Type.PAWN;

        if(colour == GamePanel.WHITE){
            image = getImage("/piece/W-Pawn");
        }
        else{
            image = getImage("/piece/B-Pawn");
        }
    }
    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false){

            // Define the move value based on its colour
            int moveValue;
            if(colour == GamePanel.WHITE){
                moveValue = -1;
            }
            else{
                moveValue = 1;
            }

            // Check the hitting piece
            hittingP = getHittingP(targetCol,targetRow);

            // 1 square movement
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null){
                return true;
            }
            // 2 square movement
            if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null && moved == false
               && pieceIsOnStraightLine(targetCol,targetRow) == false){
                return true;
            }
            // Diagonal movement & Capture (if a piece is on a square diagonally in front of it)
            if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingP != null &&
                    hittingP.colour != colour){
                return true;
            }

            // En Passant
            if(Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue){
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == targetCol && piece.row == preRow && piece.twoStepped == true){
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
