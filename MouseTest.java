import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
Play a calibration game that detects where the grid is on screen and starts playing.
In this first iteration, the calibration game is used to select the top left corner.

CURRENTLY WORKING:
- given the top left corner, the cursor will snap to the centre of each grid cell

TODO:
- Implement the calibration game
- Implement a Random Bejeweled Blitz Player to test with...
*/
public class MouseTest
{
	// grid size in pixels
	public static final double GRID_SIZE = 320;

	// Grid instance
	private Grid g;

	private Robot robotMouse;
	private Point topLeft;
	private Point bottomRight;

	public MouseTest() throws AWTException
	{
		robotMouse = new Robot();
		g = new Grid();
	}

	public void moveAndWait(int x, int y, int delay) throws AWTException
	{
		// set the mouse x and y position
		robotMouse.mouseMove(x,y);
		// give a delay before continuing execution
		robotMouse.delay(delay);
	}

	public Dimension screenSize()
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		return d;
	}

	public Point getCurrentMouseLocation()
	{
		PointerInfo pInf = MouseInfo.getPointerInfo();
		Point currentLoc = pInf.getLocation();
		return currentLoc;
	}

	public void click()
	{
		this.press();
		this.release();
	}

	public void press()
	{
		robotMouse.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	}

	public void release()
	{
		robotMouse.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	/**
	*  This method assumes that the grid width and grid height are almost certainly correct.
	*  The failsafe will then check for division by 39, 40 or 41 so the gems can be clicked correctly.
	*
	**/
	public void setGrid()
	{
		JOptionPane.showMessageDialog(null, "Move your mouse to the top left corner of the grid and press the enter key.", "Top Left", JOptionPane.INFORMATION_MESSAGE);
		topLeft = this.getCurrentMouseLocation();
		// JOptionPane.showMessageDialog(null, "Move your mouse to the bottom right corner of the grid and press the enter key.", "Top Left", JOptionPane.INFORMATION_MESSAGE);
		// bottomRight = this.getCurrentMouseLocation();

		// get the height and width of the grid from the points
		// gridWidth = (int)(bottomRight.getX() - topLeft.getX());
		// gridHeight = (int)(bottomRight.getY() - topLeft.getY());

		// get the bottom right by adding the grid size to the x and y coordinates
		bottomRight = new Point((int)(topLeft.getX() + GRID_SIZE), (int)(topLeft.getY() + GRID_SIZE));
		// bottomRight.setLocation(topLeft.getX() + GRID_SIZE, topLeft.getY() + GRID_SIZE);

		// if either is less than 300, then user not on 100% zoom OR the mouse was in the incorrect position
		// if (gridWidth < 310 || gridWidth > 330|| gridHeight < 310 || gridWidth > 330)
		// {
		// 	// should create an exception: InvalidGridDimensionsException to throw here
		// 	return;
		// }

		// this.checkModWidthHeight();
		g.setCellPoints(topLeft, bottomRight);


	}

	public void printCellPoints() throws AWTException
	{
		Point[][] midCell = g.getMidCells();

		for (int i = 0; i < midCell.length; i++)
		{
			for (int j = 0; j < midCell[i].length; j++)
			{
				this.moveAndWait((int)midCell[i][j].getX(), (int)midCell[i][j].getY(), 200);
			}
		}
	}

	public static void main(String[] args) throws AWTException
	{
		MouseTest mt = new MouseTest();

		// screen size test
		// Dimension mtD = mt.screenSize();
		// System.out.println("Width: " + mtD.getWidth());
		// System.out.println("Height: " + mtD.getHeight());

		// mouse location test
		// Point mousePoint = mt.getCurrentMouseLocation();
		// System.out.println("X: " + mousePoint.getX());
		// System.out.println("Y: " + mousePoint.getY());

		// test move mouse
		// mt.moveAndWait(1014, 525, 3000);
		// mt.moveAndWait(1114, 525, 3000);

		// test clicking the primary button
		// mt.click();
		// mt.click();

		// test dragging a window
		// mt.moveAndWait(548, 59, 2000);
		// mt.press();
		// mt.moveAndWait(748, 159, 200);
		// mt.release();

		// test centre points of grid cells
		mt.setGrid();
		mt.printCellPoints();


	}
}
