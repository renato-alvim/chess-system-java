package Chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessConscience;
import Chess.ChessPiece;
import Chess.Color;

public class Bishop extends ChessPiece{

    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    public Bishop(Board board, Color color,ChessConscience conscience) {
        super(board, color,conscience);
    }

    @Override
    public String toString() {
        return "B";
    }

    public void possibleMovesDecreasing()
    {
        Position p = new Position(0,0);

        p.setValues(position.getRow()-1,position.getColumn()-1);
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setRow(p.getRow()-1);
            p.setColumn(p.getColumn()-1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }

        p.setValues(position.getRow()+1,position.getColumn()+1);
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setRow(p.getRow()+1);
            p.setColumn(p.getColumn()+1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }
    }

    

    public void possibleMovesIncreasing()
    {
        Position p = new Position(0,0);

        p.setValues(position.getRow()-1,position.getColumn()+1);
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setRow(p.getRow()-1);
            p.setColumn(p.getColumn()+1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }

        p.setValues(position.getRow()+1,position.getColumn()-1);
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setRow(p.getRow()+1);
            p.setColumn(p.getColumn()-1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }

    }


    @Override
    public boolean[][] possibleMoves()
    {
        Position p = new Position(0,0);

        if(getChessConscience().DirectionOfPin(this)=="Horizontal"||getChessConscience().DirectionOfPin(this)=="Vertical")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            return mat;
        }

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
        else if(getChessConscience().DirectionOfPin(this)=="None")
        {
        mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        possibleMovesDecreasing();
        possibleMovesIncreasing();
        }

        //MEMENTO MORI - FABÂO   OWO


        return mat;

    }
    
}
