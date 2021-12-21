package Chess.pieces;
import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessConscience;
import Chess.ChessPiece;
import Chess.Color;

public class Pawn extends ChessPiece {

    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    public Pawn(Board board, Color color,ChessConscience conscience) {
        super(board, color,conscience);
    }

    @Override
    public String toString() {
        return "P";
    }

    public void possibleMovesVertical()
    {
        Position p = new Position(0,0);

        if(getColor()==Color.WHITE){
            p.setValues(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-2, position.getColumn());
            Position p2 = new Position(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p)&&getBoard().positionExists(p2)&&!getBoard().thereIsPiece(p2)&&getMoveCount() == 0)
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        else
        {
            p.setValues(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+2, position.getColumn());
            Position p2 = new Position(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p)&&getBoard().positionExists(p2)&&!getBoard().thereIsPiece(p2)&&getMoveCount() == 0)
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
    }

    public void possibleMovesDecreasing()
    {
        Position p = new Position(0,0);

        if(getColor()==Color.WHITE)
        {
            p.setValues(position.getRow()-1, position.getColumn()-1);
            if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        else
        {
            p.setValues(position.getRow()+1, position.getColumn()-1);
            if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
    }

    public void possibleMovesIncreasing()
    {
        Position p = new Position(0,0);

        if(getColor()==Color.WHITE)
        {
            p.setValues(position.getRow()-1, position.getColumn()+1);
            if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        else
        {
            p.setValues(position.getRow()+1, position.getColumn()+1);
            if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
            {
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
    }

    public boolean[][] possibleMoves()
    {
        if(getChessConscience().DirectionOfPin(this)=="Increasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            possibleMovesIncreasing();
        }
        else if(getChessConscience().DirectionOfPin(this)=="Decreasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            possibleMovesDecreasing();
        }
        else if(getChessConscience().DirectionOfPin(this)=="Vertical")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            possibleMovesVertical();
        }
        else if(getChessConscience().DirectionOfPin(this)=="None")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            possibleMovesIncreasing();
            possibleMovesDecreasing();
            possibleMovesVertical();
        }

        return mat;

    }

    

}
