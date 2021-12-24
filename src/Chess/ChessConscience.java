package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Chess.pieces.King;
import Chess.pieces.Knight;
import Chess.pieces.Pawn;
import Chess.pieces.Rook;
import Chess.pieces.Queen;
import Chess.pieces.Bishop;




import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

public class ChessConscience {

    private List<ChessPiece> piecesOnTheBoard;

    Board board;
    ChessPiece king;

    private boolean check;
    private boolean checkMate;
    private ChessPiece movedPiece;


    public ChessConscience(List<ChessPiece> piecesOnTheBoard) {
        this.piecesOnTheBoard = piecesOnTheBoard;
    }

    

    public boolean getCheckMate()
    {
        return checkMate;
    }

    public ChessPiece getMovedPiece() {
        return movedPiece;
    }



    public void setMovedPiece(ChessPiece movedPiece) {
        this.movedPiece = movedPiece;
    }



    public boolean isCheck(Color player) {
        MovesToDefendKing(player);
        return check;
    }

    public boolean isCheckMate(Color player) {
        testCheckMate(player);
        return checkMate;
    }

    private void testCheckMate(Color player)
    {
        List<ChessPiece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == player).collect(Collectors.toList());

        for (ChessPiece chessPiece : list) {
            chessPiece.possibleMoves();
            if(chessPiece.canDefend()==true)
            {
                checkMate = false;
                return;
            }
        }
        checkMate = true;
        return;
    }



    public ChessPiece King(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no "+color+" king on the board");
    }



    public String DirectionOfPin(ChessPiece cp)
    {
        Position kPosition = King(cp.getColor()).getPosition();

        Board board = cp.getBoard();

        Position p = new Position(0,0);

        //acima 

        p.setValues(kPosition.getRow()-1, kPosition.getColumn());
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setRow(p.getRow()-1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow()-1, p.getColumn());
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setRow(p.getRow()-1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Rook||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Vertical";
                    }
                }
            }
        }

        //abaixo

        p.setValues(kPosition.getRow()+1, kPosition.getColumn());
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setRow(p.getRow()+1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow()+1, p.getColumn());
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setRow(p.getRow()+1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Rook||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Vertical";
                    }
                }
            }
        }

        //esquerda

        p.setValues(kPosition.getRow(), kPosition.getColumn()-1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setColumn(p.getColumn()-1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow(), p.getColumn()-1);
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setColumn(p.getColumn()-1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Rook||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Horizontal";
                    }
                }
            }
        }

        //direita

        p.setValues(kPosition.getRow(), kPosition.getColumn()+1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setColumn(p.getColumn()+1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow(), p.getColumn()+1);
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setColumn(p.getColumn()+1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Rook||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Horizontal";
                    }
                }
            }
        }

        p.setValues(kPosition.getRow()+1, kPosition.getColumn()+1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setColumn(p.getColumn()+1);
            p.setRow(p.getRow()+1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow()+1, p.getColumn()+1);
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setColumn(p.getColumn()+1);
                        p.setRow(p.getRow()+1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Bishop||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Decreasing";
                    }
                }
            }
        }

        p.setValues(kPosition.getRow()-1, kPosition.getColumn()-1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setColumn(p.getColumn()-1);
            p.setRow(p.getRow()-1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow()-1, p.getColumn()-1);
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setColumn(p.getColumn()-1);
                        p.setRow(p.getRow()-1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {   
                    if((ChessPiece)board.piece(p) instanceof Bishop||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Decreasing";
                    }

                }
            }
        }

        p.setValues(kPosition.getRow()-1, kPosition.getColumn()+1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setColumn(p.getColumn()+1);
            p.setRow(p.getRow()-1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow()-1, p.getColumn()+1);
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setColumn(p.getColumn()+1);
                        p.setRow(p.getRow()-1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Bishop||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Increasing";
                    }
                }
            }
        }

        p.setValues(kPosition.getRow()+1, kPosition.getColumn()-1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            p.setColumn(p.getColumn()-1);
            p.setRow(p.getRow()+1);
        }
        if(board.positionExists(p)&&cp.isThereAllyPiece(p))
        {
            if(cp.getPosition().equals(p))
            {
                p.setValues(p.getRow()+1, p.getColumn()-1);
                while(board.positionExists(p)&&!board.thereIsPiece(p))
                    {
                        p.setColumn(p.getColumn()-1);
                        p.setRow(p.getRow()+1);
                    }
                if(board.positionExists(p)&&cp.isThereOpponentPiece(p))
                {
                    if((ChessPiece)board.piece(p) instanceof Bishop||(ChessPiece)board.piece(p) instanceof Queen)
                    {
                        return "Increasing";
                    }
                }
            }
        }
        return "None";
    }
    
    private List<Position> CheckSquaresAbove(Position kPosition)
    {
        Position p = new Position(0,0);

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow()-1, kPosition.getColumn());
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setRow(p.getRow()-1);
        }


        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Rook|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }

        return null;
    }

    private List<Position> CheckSquaresDown(Position kPosition)
    {
        Position p = new Position(0,0);

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow()+1, kPosition.getColumn());
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setRow(p.getRow()+1);
        }
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Rook|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }

        return null;
    }

    private List<Position> CheckSquaresLeft(Position kPosition)
    {
        Position p = new Position(0,0);

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow(), kPosition.getColumn()-1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setColumn(p.getColumn()-1);
        }
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Rook|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }

        return null;
    }

    private List<Position> CheckSquaresRight(Position kPosition)
    {
        Position p = new Position(0,0);

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow(), kPosition.getColumn()+1);
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setColumn(p.getColumn()+1);
        }
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Rook|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }
        return null;
    }

    private List<Position> CheckSquaresUpLeft(Position kPosition)
    {
        Position p = new Position(0,0);
        Board board = king.getBoard();

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow()-1, kPosition.getColumn()-1);


        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);
            if(enemy instanceof Pawn)
                {
                    if(enemy.getColor()==Color.BLACK)
                    {
                        positionsToBlock.add((Position)p.clone());
                        return positionsToBlock;
                    }
                }
        }

        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setRow(p.getRow()-1);
            p.setColumn(p.getColumn()-1);
        }


        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Bishop|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }


        return null;
    }

    private List<Position> CheckSquaresDownRight(Position kPosition)
    {
        Position p = new Position(0,0);

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow()+1, kPosition.getColumn()+1);


        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);
            if(enemy instanceof Pawn)
                {
                    if(enemy.getColor()==Color.WHITE)
                    {
                        positionsToBlock.add((Position)p.clone());
                        return positionsToBlock;
                    }
                }
        }
        
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setRow(p.getRow()+1);
            p.setColumn(p.getColumn()+1);
        }
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Bishop|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }
        return null;
    }

    private List<Position> CheckSquaresUpRight(Position kPosition)
    {
        Position p = new Position(0,0);
        Board board = king.getBoard();

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow()-1, kPosition.getColumn()+1);


        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);
            if(enemy instanceof Pawn)
                {
                    if(enemy.getColor()==Color.BLACK)
                    {
                        positionsToBlock.add((Position)p.clone());
                        return positionsToBlock;
                    }
                }
        }
        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setRow(p.getRow()-1);
            p.setColumn(p.getColumn()+1);
        }
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Bishop|| enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }
        return null;
    }

    private List<Position> CheckSquaresDownLeft(Position kPosition)
    {
        Position p = new Position(0,0);

        List<Position> positionsToBlock = new ArrayList<>();

        p.setValues(kPosition.getRow()+1, kPosition.getColumn()-1);


        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);
            if(enemy instanceof Pawn)
                {
                    if(enemy.getColor()==Color.WHITE)
                    {
                        positionsToBlock.add((Position)p.clone());
                        return positionsToBlock;
                    }
                }
        }

        while(board.positionExists(p)&&!board.thereIsPiece(p))
        {
            positionsToBlock.add((Position)p.clone());
            p.setRow(p.getRow()+1);
            p.setColumn(p.getColumn()-1);
        }
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {

            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Bishop || enemy instanceof Queen)
            {
                positionsToBlock.add((Position)p.clone());
                return positionsToBlock;
            }
        }
        return null;
    }


    private Position CheckKnight1(Position kPosition)
    {

        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()-1,kPosition.getColumn()-2);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }
    
    private Position CheckKnight2(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()-2,kPosition.getColumn()-1);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }

    private Position CheckKnight3(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()-2,kPosition.getColumn()+1);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }

    private Position CheckKnight4(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()-1,kPosition.getColumn()+2);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }

    private Position CheckKnight5(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()+1,kPosition.getColumn()+2);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }

    private Position CheckKnight6(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()+2,kPosition.getColumn()+1);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }

    private Position CheckKnight7(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()+2,kPosition.getColumn()-1);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }

    private Position CheckKnight8(Position kPosition)
    {
        Position p = new Position(0,0);

        p.setValues(kPosition.getRow()+1,kPosition.getColumn()-2);
        if(board.positionExists(p)&&king.isThereOpponentPiece(p))
        {
            ChessPiece enemy = (ChessPiece)board.piece(p);

            if(enemy instanceof Knight)
            {
                return (Position)p.clone();
            }
        }
        return null;
    }


    public List<Position> SafePositionsKing(ChessPiece piece)
    {

        board = piece.getBoard();
        king = piece;
        Position kPosition = (Position)king.getPosition().clone();

        board.removePiece(kPosition);

        Position p = new Position(0,0);


        List<Position> KingMoves = new ArrayList<>();

            p.setValues(kPosition.getRow()-1, kPosition.getColumn());
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow()+1, kPosition.getColumn());
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow(), kPosition.getColumn()-1);
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow(), kPosition.getColumn()+1);
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow()-1, kPosition.getColumn()-1);
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow()-1, kPosition.getColumn()+1);
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow()+1, kPosition.getColumn()-1);
            if(board.positionExists((Position)p.clone())&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }
            p.setValues(kPosition.getRow()+1, kPosition.getColumn()+1);
            if(board.positionExists(p)&&!king.isThereAllyPiece(p))
            {
                if(PositionsToDefendCheck(p)==null)
                KingMoves.add((Position)p.clone());
            }

            

            p.setValues(kPosition.getRow(), kPosition.getColumn());{
                if(PositionsToDefendCheck(p)!=null)
                {
                    check = true;
                }
            }



            if(check==false)
            {
                if(king.getMoveCount()==0)
                {
                    p.setValues(kPosition.getRow(), kPosition.getColumn()+1);
                    if(board.positionExists(p)&&board.piece(p)==null)
                    {
                        if(PositionsToDefendCheck(p)==null)
                        {
                            p.setValues(kPosition.getRow(), kPosition.getColumn()+2);
                            if(board.positionExists(p)&&board.piece(p)==null)
                            {
                                if(PositionsToDefendCheck(p)==null)
                                {
                                    ChessPiece rook = (ChessPiece)board.piece(kPosition.getRow(), kPosition.getColumn()+3);
                                    if(rook instanceof Rook && rook.getColor()==king.getColor() && rook.getMoveCount()==0)
                                    {
                                        KingMoves.add((Position)p.clone());
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(check==false)
            {
                if(king.getMoveCount()==0)
                {
                    p.setValues(kPosition.getRow(), kPosition.getColumn()-1);
                    if(board.positionExists(p)&&board.piece(p)==null)
                    {
                        if(PositionsToDefendCheck(p)==null)
                        {
                            p.setValues(kPosition.getRow(), kPosition.getColumn()-2);
                            if(board.positionExists(p)&&board.piece(p)==null)
                            {
                                if(PositionsToDefendCheck(p)==null)
                                {
                                    ChessPiece rook = (ChessPiece)board.piece(kPosition.getRow(), kPosition.getColumn()-4);
                                    if(rook instanceof Rook && rook.getColor()==king.getColor() && rook.getMoveCount()==0)
                                    {
                                        KingMoves.add((Position)p.clone());
                                    }
                                }
                            }
                        }
                    }
                }
            }


        board.placePiece(king,kPosition);

        if(KingMoves.size()==0)
        {
            return null;
        }    
        return KingMoves;
    }

    public List<List<Position>> MovesToDefendKing(Color color)
    {
            king = King(color);
            Position kPosition = (Position)king.getPosition().clone();
            board = king.getBoard();
            return PositionsToDefendCheck(kPosition);
    }


    private List<List<Position>> PositionsToDefendCheck(Position kPosition)
    {
        List<List<Position>> ListPositionsToBlock = new ArrayList<>();


        List<Position> aux = new ArrayList<>();

        aux = CheckSquaresAbove( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresDown( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresDownLeft( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresDownRight( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresLeft( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresRight( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresUpLeft( kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }
        aux = CheckSquaresUpRight(kPosition);
        if(aux!=null){
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight1(kPosition)!=null){
            aux.add(CheckKnight1(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight2(kPosition)!=null){
            aux.add(CheckKnight2(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight3(kPosition)!=null){
            aux.add(CheckKnight3(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight4(kPosition)!=null){
            aux.add(CheckKnight4(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight5(kPosition)!=null){
            aux.add(CheckKnight5(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight6(kPosition)!=null){
            aux.add(CheckKnight6(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight7(kPosition)!=null){
            aux.add(CheckKnight7(kPosition));
            ListPositionsToBlock.add(aux);
        }

        aux = new ArrayList<>();
        if(CheckKnight8(kPosition)!=null){
            aux.add(CheckKnight8(kPosition));
            ListPositionsToBlock.add(aux);
        }

        if(ListPositionsToBlock.size()>=1){
            check = true;
            return ListPositionsToBlock;
        }
        else
            check = false;

        return null;
    }
}
