package model;

import static model.Board.PieceType.BlankPiece;
import static model.Board.PieceType.FoodPiece;
import static model.Board.PieceType.SnakePiece;

public class Board
{
    private PieceType[][] mat;
    public int nrX;
    public int nrY;

    public Board(int width, int height, int lat)
    {
        nrX = width / lat;
        nrY = height / lat;
        this.mat = new PieceType[nrX][nrY];
        for (int x = 0; x < nrX; x++)
        {
            for (int y = 0; y < nrY; y++)
            {
                mat[x][y] = BlankPiece;
            }
        }
    }

    public void setSnakePiece(int x, int y)
    {
        mat[x][y] = SnakePiece;
    }

    public void setBlank(int x, int y)
    {
        mat[x][y] = BlankPiece;
    }

    public PieceType get(int x, int y)
    {
        return mat[x][y];
    }

    public boolean newFood(int x, int y)
    {
        if (mat[x][y] != BlankPiece) return false;
        mat[x][y] = FoodPiece;
        return true;
    }

    public void setBlank(PieceCoordinates piece)
    {
        setBlank(piece.getX(),piece.getY());
    }

    public enum PieceType
    {
        BlankPiece,
        SnakePiece,
        FoodPiece
    }

    public static class PieceCoordinates
    {
        private int x;
        private int y;

        public PieceCoordinates(int x, int y)
        {
            this.x=x;
            this.y=y;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }
    }
}
