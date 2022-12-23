package solver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

import main.*;
import util.Cell;

public class DijkstraSolve {
	
	private final Queue<Cell> queue;

	private Cell current;
	private final List<Cell> grid;

	public DijkstraSolve(List<Cell> grid, MazeGridPanel panel) {
		this.grid = grid;
		queue = new PriorityQueue<Cell>(new CellDistanceFromGoalComparator());
		current = grid.get(0);
		current.setDistance(0);
		queue.offer(current);
		final Timer timer = new Timer(Maze.speed, null);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!current.equals(grid.get(grid.size() - 1))) {
					flood();
				} else {
					drawPath();
					Maze.solved = true;
					timer.stop();
				}
				panel.setCurrent(current);
				panel.repaint();
				timer.setDelay(Maze.speed);
			}
		});
		timer.start();
	}
	
	private void flood() {
		current.setDeadEnd(true);
		current = queue.poll();
		List<Cell> adjacentCells = current.getValidMoveNeighbours(grid);
		for (Cell c : adjacentCells) {
			if (c.getDistance() == -1) {
				c.setDistance(current.getDistance() + 1);
				c.setParent(current);
				queue.offer(c);
			}
		}
	}
	
	private void drawPath() {
		System.out.println("X = " + grid.get(0).getX() + ", Y = " + grid.get(0).getY());
		System.out.println("---------------------------------------------------------");
		Cell.pathArray.add(grid.get(grid.size() - 1));
		while (current != grid.get(0)) {
			Cell.pathArray.add(current);
			System.out.println("X = " + current.getX() + ", Y = " + current.getY());
			current.setPath(true);
			current = current.getParent();
		}
		Cell.pathArray.add(grid.get(0));
	}
	
	private class CellDistanceFromGoalComparator implements Comparator<Cell> {
		Cell goal = grid.get(grid.size() - 1);
		
		@Override
		public int compare(Cell arg0, Cell arg1) {
			if (getDistanceFromGoal(arg0) > getDistanceFromGoal(arg1)) {
				return 1;
			} else {
				return getDistanceFromGoal(arg0) < getDistanceFromGoal(arg1) ? -1 : 0;
			}
		}
		
		private double getDistanceFromGoal(Cell c) {
			return Math.hypot(c.getX() - goal.getX(), c.getY() - goal.getY());
		}
	}
}