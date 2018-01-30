package core;

import model.Board;
import model.Direction;
import processing.core.PApplet;

import static core.Settings.*;

public class SnakeView extends PApplet
{
    private SnakeController controller;

    public static void main(String[] args)
    {
        PApplet.main("core.SnakeView", args);
    }

    @Override
    public void settings()
    {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup()
    {
        controller = new SnakeController(this);
    }

    @Override
    public void draw()
    {
        background(BACKGROUND_RED, BACKGROUND_GREEN, BACKGROUND_BLUE);
        drawBoard();
        if (!controller.logic(frameCount)) lose();
    }

    private void drawBoard()
    {
        Board board = controller.getBoard();
        for (int x = 0; x < board.nrX; x++)
        {
            for (int y = 0; y < board.nrY; y++)
            {
                drawPiece(board.get(x, y), x, y, board);
            }
        }
    }

    private void drawPiece(
            Board.PieceType pieceType,
            int x,
            int y,
            Board board
    )
    {
        noStroke();
        switch (pieceType)
        {
            case BlankPiece:
                break;
            case SnakePiece:
                fill(COLOR1_RED, COLOR1_GREEN, COLOR1_BLUE);
                rect(x * LAT, y * LAT, board.nrX, board.nrY);
                break;
            case FoodPiece:
                fill(COLOR2_RED, COLOR2_GREEN, COLOR2_BLUE);
                ellipseMode(CORNER);
                ellipse(x * LAT, y * LAT, board.nrX, board.nrY);
                break;
        }
    }

    private void lose()
    {
        fill(0);
        textSize(30);
        text("You died! Score: " + controller.getScore(), 0, 50);
        noLoop();
    }

    @Override
    public void keyPressed()
    {
        if (key == CODED)
            switch (keyCode)
            {
                case UP:
                    controller.setDirection(Direction.UP);
                    break;
                case DOWN:
                    controller.setDirection(Direction.DOWN);
                    break;
                case LEFT:
                    controller.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    controller.setDirection(Direction.RIGHT);
                    break;
            }
    }
    //TODO: background
    //TODO: FoodPiece skin
    //TODO: snake skin
}
