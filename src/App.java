import BoardGame.Board;
import BoardGame.Position;
import Chess.ChessMatch;

public class App {
    public static void main(String[] args) throws Exception {

       ChessMatch chessMatch = new ChessMatch();
       UI.printboard(chessMatch.getPieces());

       
        
    }
}
