package model;

import java.util.ArrayList;
import java.util.List;

import core.SnakeController;
import model.Board.PieceCoordinates;

import static model.Direction.RIGHT;

public class Snake
{
    private int x;
    private int y;
    private SnakeController controller;
    private Board board;
    private List<PieceCoordinates> list;
    private Direction direction;

    public Snake(int x, int y, SnakeController controller)
    {
        this.x = x;
        this.y = y;
        this.controller = controller;
        this.board = controller.getBoard();
        direction = RIGHT;
        list = new ArrayList<>(0);
        add(x, y);
        updateXY();
        add(x, y);
    }

    public boolean advance()
    {
        updateXY();
        switch (board.get(x, y))
        {
            case BlankPiece:
                removeLast();
                add(x, y);
                break;
            case SnakePiece:
                return false;
            case FoodPiece:
                add(x, y);
                controller.newFood();
                break;
        }
        return true;
    }

    private void updateXY()
    {
        x = (board.nrX + x + direction.x) % board.nrX;
        y = (board.nrY + y + direction.y) % board.nrY;
    }

    private void removeLast()
    {
        PieceCoordinates piece = list.get(0);
        board.setBlank(piece);
        list.remove(0);
    }

    private void add(int x, int y)
    {
        list.add(new PieceCoordinates(x, y));
        board.setSnakePiece(x, y);
    }

    public void setDirection(Direction direction)
    {
        if (list.size() >= 2)
        {
            PieceCoordinates piece1 = list.get(list.size() - 1);
            PieceCoordinates piece2 = list.get(list.size() - 2);
            if (piece1.getX() + direction.x == piece2.getX() && piece1.getY() + direction.y == piece2
                    .getY()) return;
        }
        this.direction = direction;
    }

    public int size()
    {
        return list.size();
    }

}
