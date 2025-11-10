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
