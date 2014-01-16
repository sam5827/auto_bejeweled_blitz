import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grid
{
	public static final double CELL_SIZE = MouseTest.GRID_SIZE/40.0;
	public static final double CELL_DIMS = 40.0;

	private Point[][] midCell;

	public Grid()
	{
		midCell = new Point[(int)CELL_SIZE][(int)CELL_SIZE];
	}

	public Point[][] getMidCells()
	{
		return midCell;
	}

	public Point getCellPoint(int row, int col)
	{
		return midCell[row][col];
	}

	public void setCellPoints(Point topLeft, Point bottomRight)
	{
		for (int i = 0; i < midCell.length; i++)
		{
			for (int j = 0; j < midCell[i].length; j++)
			{

				midCell[i][j] = new Point((int)(topLeft.getX()+(j * CELL_DIMS) + (CELL_DIMS/2)), (int)(topLeft.getY()+(i * CELL_DIMS) + (CELL_DIMS/2)));
			}
		}
	}
}