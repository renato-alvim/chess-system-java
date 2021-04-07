package Chess;

import BoardGame.Board;
import BoardGame.BoardExeption;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch ()
    {
        board = new Board(8,8);
        initialSetup();
    }

    public ChessPiece[][] getPieces(){

        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i=0;i<board.getRows();i++)
        {
            for(int j=0; j<board.getColumns();j++)
            {
                mat[i][j]=(ChessPiece)board.piece(i,j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece)
    {
        board.placePiece(piece,new ChessPosition(column, row).toPositon());
    }
    private void initialSetup()
    {
        placeNewPiece('a',8, new Rook(board,Color.BLACK));
        placeNewPiece('h',8, new Rook(board,Color.BLACK));
        placeNewPiece('e',8, new King(board,Color.BLACK));


        placeNewPiece('a',1, new Rook(board,Color.WHITE));
        placeNewPiece('h',1, new Rook(board,Color.WHITE));
        placeNewPiece('e',1, new King(board,Color.WHITE));

    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition)
    {
        Position source = sourcePosition.toPositon();
        Position target = targetPosition.toPositon();

        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturedPiece = makeMove(source,target);

        return (ChessPiece)capturedPiece;
    }

    private void validateSourcePosition(Position position)
    {
        if(!board.thereIsPiece(position)){
            throw new ChessException("Não existe peça na posição de origem ");
        }
        if(!board.piece(position).isThereAnyPossibleMove())
        {
            throw new ChessException("Não há movimentos possiveis para a peça");
        }
    }

    public void validateTargetPosition(Position source,Position target)
    {
        if(!board.piece(source).possibleMoves(target))
        {
            throw new ChessException("A peça escolhida não pode se mover para a posição de destino");
        }
    }

    private Piece makeMove(Position source,Position target)
    {
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);

        board.placePiece(p, target);
        return capturedPiece;
    }

}
