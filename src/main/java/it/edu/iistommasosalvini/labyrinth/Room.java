package it.edu.iistommasosalvini.labyrinth;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Room {
  private final EnumMap<Direction, Boolean> doors;
  private final Position position;
  private final List<Persona> occupants;
  // private final int capacity;

  public Room(
      boolean north,
      boolean south,
      boolean west,
      boolean east,
      Position position
  ) {
    this.doors = new EnumMap<>(Direction.class);
    this.doors.put(Direction.NORTH, north);
    this.doors.put(Direction.SOUTH, south);
    this.doors.put(Direction.WEST, west);
    this.doors.put(Direction.EAST, east);
    this.occupants = new ArrayList<>();
    this.position = position;
  }

  public Position getPosition() {
    return position;
  }

  public boolean hasDoor(Direction direction) {
    return this.doors.get(direction);
  }

  public boolean enter(Persona persona) {
    occupants.add(persona);
    return true;
  }

  public boolean leave(Persona persona) {
    occupants.remove(persona);
    return true;
  }

  public List<Persona> getOccupants() {
	  return occupants;
  }

  public int getOccupantsNumber() {
	  return occupants.size();
  }
}
