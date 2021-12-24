package Chess.pieces;
import java.util.List;

import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessConscience;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {


    public King(Board board, Color color, ChessConscience conscience) {
        super(board, color,conscience);
    }

    private boolean canMove(Position position)
    {
        return !getBoard().thereIsPiece(position)||isThereOpponentPiece(position);
    }

    @Override
    public String toString() {
        return "K";
    }

    public boolean[][] naturalMoves(){
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //ACIMA
        p.setValues(position.getRow()-1,position.getColumn());
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        //ABAIXO
        p.setValues(position.getRow()+1,position.getColumn());
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        //ESQUERDA
        p.setValues(position.getRow(),position.getColumn()-1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        //DIREITA
        p.setValues(position.getRow(),position.getColumn()+1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;
    
        //NOROESTE
        p.setValues(position.getRow()-1,position.getColumn()-1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        //NORDESTE
        p.setValues(position.getRow()-1,position.getColumn()+1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        //SUDOESTE
        p.setValues(position.getRow()+1,position.getColumn()-1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;

        //SUDESTE
        p.setValues(position.getRow()+1,position.getColumn()+1);
        if(getBoard().positionExists(p)&&canMove(p))
            mat[p.getRow()][p.getColumn()]=true;
        
        return mat;
    }

    public boolean[][] movesOutCheck(){

        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        List<Position> KingPossibleMoves = getChessConscience().SafePositionsKing(this);

    
        setcanDefend(false);

        if(KingPossibleMoves!=null)
        {
            for (Position x : KingPossibleMoves) {
                    setcanDefend(true);
                    mat[x.getRow()][x.getColumn()] = true;
            }
        }


        return mat;
    }
    

    @Override
    public boolean[][] possibleMoves() {

        King enemyKing = (King)getChessConscience().King(getOpositeColor());

        
        boolean[][] enemyKingMoves = enemyKing.naturalMoves();

        boolean[][] allyKingMoves = movesOutCheck();

        for(int i=0;i<getBoard().getRows();i++)
        {
            for(int j=0; j<getBoard().getColumns();j++)
            {
                if(enemyKingMoves[i][j]==true)
                {
                    allyKingMoves[i][j]=false;
                }
            }
        }        
        
        return allyKingMoves;
    }
}