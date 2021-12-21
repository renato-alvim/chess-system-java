package Chess;

import java.util.ArrayList;
import java.util.List;

import BoardGame.Board;
import BoardGame.BoardExeption;
import BoardGame.Piece;
import BoardGame.Position;
import Chess.pieces.Bishop;
import Chess.pieces.King;
import Chess.pieces.Knight;
import Chess.pieces.Pawn;
import Chess.pieces.Queen;
import Chess.pieces.Rook;

public class ChessMatch {

    private Board board;
    private int turn;
    private Color currentPlayer;

    private List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
    private List<ChessPiece> capturedPieces = new ArrayList<>();

    public ChessMatch ()
    {
        board = new Board(8,8);
        turn=1;
        currentPlayer=Color.WHITE;
        initialSetup();
    }

    public int getTurn()
    {
        return turn;
    }
    public Color getCurrentPlayer()
    {
        return currentPlayer;
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
        piecesOnTheBoard.add(piece);
    }
    private void initialSetup()
    {
        ChessConscience conscience = new ChessConscience(piecesOnTheBoard, board);

        placeNewPiece('a',8, new Rook(board,Color.BLACK,conscience));
        placeNewPiece('h',8, new Rook(board,Color.BLACK,conscience));
        placeNewPiece('e',8, new King(board,Color.BLACK,conscience));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK, conscience));

        placeNewPiece('b', 8, new Knight(board, Color.BLACK, conscience));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK, conscience));

        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, conscience));

        placeNewPiece('f',8, new Bishop(board,Color.BLACK,conscience));
        placeNewPiece('c',8, new Bishop(board,Color.BLACK,conscience));

        placeNewPiece('c',1, new Bishop(board,Color.WHITE,conscience));
        placeNewPiece('f',1, new Bishop(board,Color.WHITE,conscience));

        placeNewPiece('a',1, new Rook(board,Color.WHITE,conscience));
        placeNewPiece('h',1, new Rook(board,Color.WHITE,conscience));
        placeNewPiece('e',1, new King(board,Color.WHITE,conscience));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE, conscience));




        placeNewPiece('b', 1, new Knight(board, Color.WHITE, conscience));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE, conscience));
        
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, conscience));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, conscience));


    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition)
    {
        Position position = sourcePosition.toPositon();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition)
    {
        Position source = sourcePosition.toPositon();
        Position target = targetPosition.toPositon();

        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturedPiece = makeMove(source,target);
        nextTurn();

        return (ChessPiece)capturedPiece;
    }

    private void validateSourcePosition(Position position)
    {
        if(!board.thereIsPiece(position)){
            throw new ChessException("Não existe peça na posição de origem ");
        }
        if(currentPlayer!=((ChessPiece)board.piece(position)).getColor())
        {
            throw new ChessException("A peça escolhida não é sua");
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
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.incraseMoveCount();

        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if(capturedPiece!=null)
        {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add((ChessPiece)capturedPiece);
        }
        return capturedPiece;
    }

    private void nextTurn()
    {
        turn++;
        currentPlayer=(currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
