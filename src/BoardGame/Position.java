package BoardGame;

public class Position implements Cloneable {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position(Position p) throws Exception
    {
        if(p==null)
        {
            throw new Exception("Posicao ausente para copiar");
        }
        this.row = p.row;
        this.column =p.column;
    }
    
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    public void setValues(int row, int column)
    {
        this.row=row;
        this.column=column;
    }
    @Override
    public String toString() {
        return row+", "+column;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

    @Override
    public Object clone()
    {
        Position ret = null;
        try
        {
            ret = new Position(this);
        }
        catch(Exception erro)
        {}
        return ret;
    }




}
