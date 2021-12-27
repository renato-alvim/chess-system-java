package Chess;

import java.util.ArrayList;
import java.util.List;

import BoardGame.Board;
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
    private boolean draw;
    private boolean checkmate;
    private boolean check;

    private List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
    private List<ChessPiece> capturedPieces = new ArrayList<>();

    private ChessConscience conscience;
    private ChessPiece promoted;
    private ChessPiece enPassantVulnerable;

    public ChessMatch ()
    {
        board = new Board(8,8);
        turn=1;
        currentPlayer=Color.WHITE;
        conscience = new ChessConscience(piecesOnTheBoard);
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

    public boolean isDraw()
    {
        return draw;
    }

    public boolean isCheckMate()
    {
        return checkmate;
    }

    public boolean isCheck()
    {
        return checkmate;
    }

    public ChessPiece getPromoted(){
        return promoted;
    }

    public ChessConscience getConscience()
    {
        return conscience;
    }

    public ChessPiece isEnPassantVulnerable(){
        return enPassantVulnerable;
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

    public Color opponent()
    {
        return (currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;
    }


    private void initialSetup()
    {

        
        //BLACK PIECES
        placeNewPiece('a',8, new Rook(board,Color.BLACK,conscience));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK, conscience));
        placeNewPiece('c',8, new Bishop(board,Color.BLACK,conscience));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK, conscience));
        placeNewPiece('e',8, new King(board,Color.BLACK,conscience));
        placeNewPiece('f',8, new Bishop(board,Color.BLACK,conscience));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK, conscience));
        placeNewPiece('h',8, new Rook(board,Color.BLACK,conscience));

        //BLACK PAWNS
        //placeNewPiece('a', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, conscience));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, conscience));

        //WHITE PIECES
        placeNewPiece('a',1, new Rook(board,Color.WHITE,conscience));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE, conscience));
        placeNewPiece('c',1, new Bishop(board,Color.WHITE,conscience));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE, conscience));
        placeNewPiece('e',1, new King(board,Color.WHITE,conscience));
        placeNewPiece('f',1, new Bishop(board,Color.WHITE,conscience));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE, conscience));
        placeNewPiece('h',1, new Rook(board,Color.WHITE,conscience));
        
        //WHITE PAWNS
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

        ChessPiece movedPiece = (ChessPiece)board.piece(target);


        promoted = null;
		if (movedPiece instanceof Pawn) {
			if ((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) || (movedPiece.getColor() == Color.BLACK && target.getRow() == 7)) {
				promoted = (ChessPiece)board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}

        if(movedPiece instanceof Pawn && (target.getRow()==source.getRow()-2||target.getRow()==source.getRow()+2))
        {
            enPassantVulnerable = movedPiece;
            conscience.setMovedPiece(movedPiece);
        }
        else
        {
            enPassantVulnerable=null;
            conscience.setMovedPiece(null);
        }

        if(isMatchAlive(opponent()))
        {
            nextTurn();
        }

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

    public boolean isMatchAlive(Color opponent)
    {
        if(conscience.isCheck(opponent))
        {
            check = true;
            if(conscience.isCheckMate(opponent))
            {
                checkmate=true;
                return false;
            }
            return true;
        }
        else
        {
            check = false;
            if(conscience.testStalemate(opponent))
            {
                draw = true;
                return false;
            }
        }
        return true;
    }

    private Piece makeMove(Position source,Position target)
    {

        Piece capturedPiece=null;

        capturedPiece = board.removePiece(target);

        if(board.piece(source) instanceof Pawn)
        {
            Position pos = ((Pawn)board.piece(source)).possibleMoveEnPassant();

            if(pos!=null&&target.equals(pos))
            {
                capturedPiece = board.removePiece(enPassantVulnerable.getPosition());
            }
        }    


        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.incraseMoveCount();


        board.placePiece(p, target);



        if(capturedPiece!=null)
        {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add((ChessPiece)capturedPiece);
        }

        // #special move short castle
        if(p instanceof King && target.getColumn() == source.getColumn()+2)
        {
            Position sourceT =  new Position(source.getRow(),source.getColumn()+3);
            Position targetT =  new Position(source.getRow(),source.getColumn()+1);

            ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
            board.placePiece(rook, targetT);
            rook.incraseMoveCount();
        }

        // #special move long castle
        if(p instanceof King && target.getColumn() == source.getColumn()-2)
        {
            Position sourceT =  new Position(source.getRow(),source.getColumn()-4);
            Position targetT =  new Position(source.getRow(),source.getColumn()-1);

            ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
            board.placePiece(rook, targetT);
            rook.incraseMoveCount();
        }


        return capturedPiece;
    }

    private ChessPiece newPiece(String type, Color color) {
		if (type.equals("B")) return new Bishop(board, color,conscience);
		if (type.equals("N")) return new Knight(board, color,conscience);
		if (type.equals("Q")) return new Queen(board, color,conscience);
		return new Rook(board, color,conscience);
	}

    public ChessPiece replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("There is no piece to be promoted");
		}
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
			return promoted;
		}
		
		Position pos = promoted.getPosition();
        
		Piece p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);
		
		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);
		
		return newPiece;
	}



    private void nextTurn()
    {
        turn++;
        currentPlayer=(currentPlayer==Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

}
