package Chess.pieces;
import java.util.List;

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

    public Position possibleMoveEnPassant(){

            Position p = new Position(0,0);

            p.setValues(getPosition().getRow(),getPosition().getColumn()-1);

            if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
            {
                ChessPiece pawn1 = (ChessPiece)getBoard().piece(p);
                if(pawn1!=null&&pawn1.getMoveCount()==1&&pawn1==getChessConscience().getMovedPiece())
                {
                    if(getColor()==Color.WHITE)
                    {
                        mat[getPosition().getRow()-1][getPosition().getColumn()-1]=true;
                        return new Position(getPosition().getRow()-1,getPosition().getColumn()-1);
                    }
                    else{
                        mat[getPosition().getRow()+1][getPosition().getColumn()+1]=true;
                        return new Position(getPosition().getRow()+1,getPosition().getColumn()+1);
                    }
                }
            }

            p.setValues(getPosition().getRow(),getPosition().getColumn()+1);

            if(getBoard().positionExists(p)&&isThereOpponentPiece(p))
            {
                ChessPiece pawn2 = (ChessPiece)getBoard().piece(p);
                if(pawn2!=null&&pawn2.getMoveCount()==1&&pawn2==getChessConscience().getMovedPiece())
                {
                    if(getColor()==Color.WHITE)
                    {
                        mat[getPosition().getRow()-1][getPosition().getColumn()+1]=true;
                        return new Position(getPosition().getRow()-1,getPosition().getColumn()+1);
                    }
                    else{
                        mat[getPosition().getRow()+1][getPosition().getColumn()-1]=true;
                        return new Position(getPosition().getRow()+1,getPosition().getColumn()-1);
                    }
                }
            }

            return null;
        }

    public boolean[][] possibleMoves()
    {
        if(getChessConscience().DirectionOfPin(this)=="Increasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            possibleMovesIncreasing();
            possibleMoveEnPassant();
            return mat;
        }
        else if(getChessConscience().DirectionOfPin(this)=="Decreasing")
        {
            mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
            setcanDefend(false);
            possibleMovesDecreasing();
            possibleMoveEnPassant();
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
            possibleMovesIncreasing();
            possibleMovesDecreasing();
            possibleMovesVertical();
            possibleMoveEnPassant();
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
