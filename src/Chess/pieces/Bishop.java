package Chess.pieces;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Bishop extends ChessPiece{

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves()
    {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);
        //NOROESTE

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

        //NORDESTE

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

        //SUDOESTE

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

        //SUDESTE

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

        //MEMENTO MORI - FABÂO   OWO


        return mat;

    }
    
}
