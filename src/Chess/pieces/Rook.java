package Chess.pieces;
import java.util.List;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessConscience;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {


    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    public Rook(Board board, Color color,ChessConscience conscience) {
        super(board, color,conscience);
    }

    @Override
    public String toString() {
        return "R";
    }


    public void possibleMovesVertical()
    {
        Position p = new Position(0,0);

        p.setValues(position.getRow()-1,position.getColumn());
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setRow(p.getRow()-1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }

        p.setValues(position.getRow()+1,position.getColumn());
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setRow(p.getRow()+1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }
    }

    public void possibleMovesHorizontal()
    {

        Position p = new Position(0,0);

        p.setValues(position.getRow(),position.getColumn()-1);
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setColumn(p.getColumn()-1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }

        //direita 

        p.setValues(position.getRow(),position.getColumn()+1);
        while(getBoard().positionExists(p)&&!getBoard().thereIsPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
            p.setColumn(p.getColumn()+1);
        }
        if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
        {
            mat[p.getRow()][p.getColumn()]=true;
        }
    }





    @Override
    public boolean[][] possibleMoves() {

        //acima

        if(getChessConscience().DirectionOfPin(this)=="Increasing"||getChessConscience().DirectionOfPin(this)=="Decreasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            return mat;
        }

        if(getChessConscience().DirectionOfPin(this)=="Horizontal")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            possibleMovesHorizontal();
            return mat;
        }
        else if(getChessConscience().DirectionOfPin(this)=="Vertical")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            possibleMovesVertical();
            return mat;
        }
        else if(getChessConscience().DirectionOfPin(this)=="None")
        {
        mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        possibleMovesHorizontal();
        possibleMovesVertical();
        }



        List<List<Position>> allcheckers = getChessConscience().MovesToDefendKing(getColor());
        
        if(allcheckers!=null)
        {
            setcanDefend(false);
            if(allcheckers.size()>=2)
            {
                return new boolean[getBoard().getRows()][getBoard().getColumns()];
            }


            List<Position> enemyChecker = allcheckers.get(0);

            boolean[][] matAux = new boolean[getBoard().getRows()][getBoard().getColumns()];

            for (Position x : enemyChecker) {

                if(mat[x.getRow()][x.getColumn()] == true)
                {
                    setcanDefend(true);
                    matAux[x.getRow()][x.getColumn()] = true;
                }
            }

            return matAux;
        }

        return mat;
    }
}
