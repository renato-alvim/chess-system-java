package Chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

public abstract class ChessPiece extends Piece{
    
    private Color color;
    private ChessConscience conscience;
    private int moveCount;

    public ChessPiece(Board board, Color color, ChessConscience conscience) {
        super(board);
        this.color = color;
        this.conscience = conscience;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount()
    {
        return moveCount;
    }

    public void incraseMoveCount()
    {
        moveCount++;
    }

    public void decraseMoveCount()
    {
        moveCount--;
    }

    public ChessConscience getChessConscience()
    {
        return conscience;
    }

    public Color getOpositeColor()
    {
        if(color==Color.WHITE)
            return Color.BLACK;
        else
            return Color.WHITE;

    }

    public boolean isThereOpponentPiece(Position position)
    {
        ChessPiece p =(ChessPiece)getBoard().piece(position);
        return p!=null && p.getColor()!=color;
    }

    public boolean isThereAllyPiece(Position position)
    {
        ChessPiece p =(ChessPiece)getBoard().piece(position);
        return p!=null && p.getColor() == color;
    }
    
}
