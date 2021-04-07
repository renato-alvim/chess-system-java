package Chess;

import BoardGame.BoardExeption;

public class ChessException extends BoardExeption {
    private static final long serialVersionUID = 1L;

    public ChessException(String message) {
        super(message);
    }
}
