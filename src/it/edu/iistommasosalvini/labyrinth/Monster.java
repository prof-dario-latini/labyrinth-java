package it.edu.iistommasosalvini.labyrinth;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Persona{
  List<Direction> availableDirections = new ArrayList<>();

  public Monster(String name) {
    super(name);

  }

  private void initAvailableDirections() {
	  availableDirections.clear();
	  availableDirections.add(Direction.NORD);
	  availableDirections.add(Direction.SUD);
	  availableDirections.add(Direction.WEST);
	  availableDirections.add(Direction.EST);
  }

  public void routine() {
	 initAvailableDirections();
	 boolean canMove;
	 do {
		int nextMove = (int) (Math.random() * (availableDirections.size() - 1));
		// System.out.println("nextMove " + nextMove);
		Direction direction = availableDirections.get(nextMove);
		canMove = this.move(direction) != null;
		if (!canMove) {
			availableDirections.remove(nextMove);
		}
	 } while (!availableDirections.isEmpty() && !canMove);
  }
}
