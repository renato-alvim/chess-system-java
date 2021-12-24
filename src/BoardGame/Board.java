package BoardGame;

public class Board {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows<1 || columns<1){
            throw new BoardExeption("Erro ao criar o tabuleiro: é necessario haver pelo menos uma linha e uma coluna");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece (int row, int column)
    {
        if(!positionExists(row,column))
        {
            throw new BoardExeption("Posição inexistente");
        }

        return pieces[row][column];
    }

    public Piece piece (Position position)
    {
        if(!positionExists(position))
        {
            throw new BoardExeption("Posição inexistente");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position)
    {
        if(thereIsPiece(position)){
            throw new BoardExeption("A posição ("+position+") já contem uma peça");
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row,int column)
    {
        return row>=0 && row<rows && column<columns && column>=0;
    }

    public boolean positionExists(Position position)
    {
        return positionExists(position.getRow(),position.getColumn());
    }

    public boolean thereIsPiece(Position position)
    {
        if(!positionExists(position))
        {
            throw new BoardExeption("Posição inexistente");
        }
        return piece(position) != null;
    }

    public Piece removePiece(Position position)
    {
        if(!positionExists(position))
        {
            throw new BoardExeption("Posição inexistente");
        }

        if(!thereIsPiece(position))
        {   
            return null;
        }

        Piece aux = piece(position);
        aux.position=null;
        pieces[position.getRow()][position.getColumn()]=null;
        return aux;
    }

    
    
}
