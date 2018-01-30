package core;

import model.Board;
import model.Direction;
import model.Snake;
import processing.core.PApplet;

import static core.Settings.HEIGHT;
import static core.Settings.LAT;
import static core.Settings.SPEED;
import static core.Settings.WIDTH;

public class SnakeController
{

    private Board board;
    private Snake snake;
    private PApplet p;

    public SnakeController(PApplet p)
    {
        this.p = p;
        board = new Board(WIDTH, HEIGHT, LAT);
        snake = new Snake((int) p.random(board.nrX), (int) p.random(board.nrY), this);
        newFood();
    }

    public void newFood()
    {
        int x, y;
        do
        {
            x = (int) p.random(board.nrX);
            y = (int) p.random(board.nrY);
        }
        while (!board.newFood(x, y));
    }

    public boolean logic(int frame)
    {
        if (frame % SPEED == 0)
            return snake.advance();
        return true;
    }

    public int getScore()
    {
        return snake.size();
    }

    public void setDirection(Direction direction)
    {
        snake.setDirection(direction);
    }

    public Board getBoard()
    {
        return board;
    }

    public Snake getSnake()
    {
        return snake;
    }
}
