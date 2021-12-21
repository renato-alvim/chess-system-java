package Chess.pieces;
import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessConscience;
import Chess.ChessPiece;
import Chess.Color;

public class Knight extends ChessPiece {

    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    public Knight(Board board, Color color,ChessConscience conscience) {
        super(board, color,conscience);
    }

    @Override
    public String toString() {
        return "N";
    }

    private boolean canMove(Position position)
    {
        return !getBoard().thereIsPiece(position)||isThereOpponentPiece(position);
    }

    @Override
    public boolean[][] possibleMoves() {

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0,0);

        if(getChessConscience().DirectionOfPin(this)!="None")
        {
            return mat;
        }
        else{
        p.setValues(position.getRow()-1,position.getColumn()-2);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        
        p.setValues(position.getRow()-2,position.getColumn()-1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        
        p.setValues(position.getRow()-2,position.getColumn()+1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        
        p.setValues(position.getRow()-1,position.getColumn()+2);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;
    
        
        p.setValues(position.getRow()+1,position.getColumn()+2);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        
        p.setValues(position.getRow()+2,position.getColumn()+1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        
        p.setValues(position.getRow()+2,position.getColumn()-1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        
        p.setValues(position.getRow()+1,position.getColumn()-2);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;
        }
        
        return mat;
    }
}
