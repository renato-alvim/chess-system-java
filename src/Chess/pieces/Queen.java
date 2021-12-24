package Chess.pieces;
import java.util.List;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessConscience;
import Chess.ChessPiece;
import Chess.Color;

public class Queen extends ChessPiece {

    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    public Queen(Board board, Color color,ChessConscience conscience) {
        super(board, color,conscience);
    }

    @Override
    public String toString() {
        return "Q";
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

    public boolean[][] possibleMoves()
    {
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
        else if(getChessConscience().DirectionOfPin(this)=="Increasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            possibleMovesIncreasing();
            return mat;
        }
        else if(getChessConscience().DirectionOfPin(this)=="Decreasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            possibleMovesDecreasing();
            return mat;
        }
        else if(getChessConscience().DirectionOfPin(this)=="None")
        {
        mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        possibleMovesHorizontal();
        possibleMovesVertical();
        possibleMovesIncreasing();
        possibleMovesDecreasing();
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
