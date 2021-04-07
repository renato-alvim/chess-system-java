package Application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import BoardGame.Board;
import BoardGame.BoardExeption;
import BoardGame.Position;
import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        for(int i=0;i<=100;i++)
        {
            try{
            UI.clearScreen();
            UI.printMatch(chessMatch,captured);
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(sc);

            boolean[][] possibleMoves = chessMatch.possibleMoves(source);
            UI.clearScreen();
            UI.printBoard(chessMatch.getPieces(),possibleMoves);

            System.out.println();
            System.out.println("Target: ");
            ChessPosition target = UI.readChessPosition(sc);

            ChessPiece capturedPiece= chessMatch.performChessMove(source, target);

            if(capturedPiece!=null)
            {
                captured.add(capturedPiece);
            }
            }
            catch(ChessException e)
            {
                System.out.println(e.getMessage()+"\n\nPressione ENTER para continuar");
                sc.nextLine();
            }
            catch(InputMismatchException e)
            {
                System.out.println(e.getMessage()+"\n\nPressione ENTER para continuar");
                sc.nextLine();
            }
            catch(BoardExeption e)
            {
                System.out.println(e.getMessage()+"\n\nPressione ENTER para continuar");
                sc.nextLine();
            }
        }

        sc.close();

    }
    
}
