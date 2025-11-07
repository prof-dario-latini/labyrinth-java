package it.edu.iistommasosalvini.labyrinth;

import java.util.EnumMap;

public class Room {
  private final EnumMap<Direction, Boolean> doors;
  private final Position position;

  public Room(
      boolean nord,
      boolean sud,
      boolean west,
      boolean est,
      Position position
  ) {
    this.doors = new EnumMap<>(Direction.class);
    this.doors.put(Direction.NORD, nord);
    this.doors.put(Direction.SUD, sud);
    this.doors.put(Direction.WEST, west);
    this.doors.put(Direction.EST, est);
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }

  public boolean hasDoor(Direction direction) {
    return this.doors.get(direction);
  }
}
