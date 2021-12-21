package Chess;

import java.util.List;
import java.util.stream.Collectors;
import Chess.pieces.King;
import Chess.pieces.Rook;
import Chess.pieces.Queen;
import Chess.pieces.Bishop;




import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

public class ChessConscience {

    private List<ChessPiece> piecesOnTheBoard;

    public ChessConscience(List<ChessPiece> piecesOnTheBoard, Board board) {
        this.piecesOnTheBoard = piecesOnTheBoard;
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

    public ChessPiece Rook(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof Rook){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no "+color+" king on the board");
    }

    public ChessPiece Bishop(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof Bishop){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no "+color+" king on the board");
    }

    public ChessPiece Queen(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if(p instanceof Queen){
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
                    if(Rook(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Rook(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Rook(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Rook(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Bishop(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Bishop(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Bishop(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
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
                    if(Bishop(cp.getOpositeColor()).getPosition().equals(p)||Queen(cp.getOpositeColor()).getPosition().equals(p))
                    {
                        return "Increasing";
                    }
                }
            }
        }

        





        return "None";
    }
    
}
