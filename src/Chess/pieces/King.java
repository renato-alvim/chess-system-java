package Chess.pieces;
import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position)
    {
        return !getBoard().thereIsPiece(position)||isThereOpponentPiece(position);
    }

    @Override
    public boolean[][] possibleMoves() {
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
}