package Piece;

import Main.Board;
import Main.GamePanel;
import Main.Type;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Piece {

    /*
       - variable image for our chess pieces
       - x and y are the coordinates of where i pieces should be
       - know the previous and current movement so we could check if it is valid...
       - what colour should our piece be...
     */

    public Type type;
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int colour;
    public Piece hittingP;
    public boolean moved, twoStepped;

    public Piece(int colour, int col, int row){

        this.colour = colour;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }
    public BufferedImage getImage(String imagePath){

        BufferedImage image = null;

        try{
            /*
            - We use Image IO because we need to add an image and this helps insert it.
            - When we first send an image it is first just a byte/code, we add read() method to help convert it into a
              Buffered Image.
             */
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        }
        catch(IOException e){
            // this helps us know where exactly we went wrong.
            // so we use StackTrace.
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col){
        return col * Board.SQUARE_SIZE;
    }
    public int getY(int row){
        return row * Board.SQUARE_SIZE;
    }
    public int getCol(int x){
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;
    }
    public int getIndex(){
        for(int index = 0; index < GamePanel.simPieces.size(); index++){
            if(GamePanel.simPieces.get(index) == this){
                return index;
            }
        }
        return 0;
    }
    public void updatePosition(){

        // To check En Passant
        if(type == Type.PAWN){
            if(Math.abs(row - preRow) == 2){
                twoStepped = true;
            }
        }

        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }
    public void resetPosition(){
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }
    public boolean isWithinBoard(int targetCol, int targetRow){
        if (targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7){
            return true;
        }
        return false;
    }
    public boolean isSameSquare(int targetCol, int targetRow){
        if(targetCol == preCol && targetRow == preRow){
            return true;
        }
        return false;
    }
    public Piece getHittingP(int targetCol, int targetRow){
        for(Piece piece : GamePanel.simPieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }
    public boolean isValidSquare(int targetCol, int targetRow){

        hittingP = getHittingP(targetCol, targetRow);

        if(hittingP == null){
            return true;  // This square is vacant
        }
        else { // This square is OCCUPIED
            if(hittingP.colour != this.colour){ // If the colour is different, it can be captured.
                return true;
            }
            else {
                hittingP = null;
            }
        }
      return false;
    }
    public boolean pieceIsOnStraightLine(int targetCol, int targetRow){
        //When this piece is moving to the left
        for(int c = preCol - 1; c > targetCol; c--){
            for(Piece piece : GamePanel.simPieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }

        //When this piece is moving to the right
        for(int c = preCol + 1; c < targetCol; c++){
            for(Piece piece : GamePanel.simPieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }

        //When this piece is moving up
        for(int r = preRow - 1; r > targetRow; r--){
            for(Piece piece : GamePanel.simPieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingP = piece;
                    return true;
                }
            }
        }

        //When this piece is moving down
        for(int r = preRow + 1; r < targetRow; r++){
            for(Piece piece : GamePanel.simPieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingP = piece;
                    return true;
                }
            }
        }

        return false;
    }
    public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow){

        if(targetRow < preRow){
            // Up left
            for(int c = preCol - 1; c > targetCol; c--){
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow - diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }

            // Up right
            for(int c = preCol + 1; c < targetCol; c++){
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow - diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }

        if(targetRow > preRow){
            // Down left
            for(int c = preCol - 1; c > targetCol; c--){
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow + diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
            // Down right
            for(int c = preCol + 1; c < targetCol; c++){
                int diff = Math.abs(c - preCol);
                for(Piece piece : GamePanel.simPieces){
                    if(piece.col == c && piece.row == preRow + diff){
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
    }
}
