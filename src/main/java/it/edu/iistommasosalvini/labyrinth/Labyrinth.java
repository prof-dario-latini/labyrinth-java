package it.edu.iistommasosalvini.labyrinth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Labyrinth {
  private Position[][] positions;
  private Map<Position, Room> rooms;
  private ArrayList<Monster> monsters;
  private final int nColumns;
  private final int nRows;
  private final Room start;
  private final Room end;

  static public void printCommands() {
    String color = "\u001B[33m";
    System.out.println(color + "To move, type 'nord', 'sud', 'west', 'est'.");
    System.out.println(color + "To quit, type 'quit'.");
    System.out.print(color + "To list of the commands, type 'help'.");
    System.out.println("\033[0m");
  }

  public Labyrinth(int rows, int columns) {
      this.nRows = rows <= 0 ? 1 : rows;
      this.nColumns = columns <= 0 ? 1 : columns;
      this.positions = new Position[this.nRows][this.nColumns];
      this.rooms = Labyrinth.generateRooms(this.positions);
      this.start = this.rooms.get(this.positions[0][0]);
      this.end = this.rooms.get(this.positions[this.nRows - 1][this.nColumns - 1]);
      this.placeMonsters();
  }

  static Map<Position, Room> generateRooms(Position[][] positions) {
    Map<Position, Room> rooms = new HashMap<>();
    int nRows = positions.length;
    int nColumns = positions[0].length;

    for (int r = 0; r < nRows; r++) {
      for (int c = 0; c < nColumns; c++) {
        Position position = new Position(r, c);
        positions[r][c] = position;
        Room room = new Room(
            r > 0,
            r < nRows -1,
            c > 0,
            c < nColumns -1,
            position
        );
        rooms.put(position, room);
      }
    }
    return rooms;
  }

  static ArrayList<Monster> generateMonsters(Position[][] positions) {
    int nRows = positions.length;
    int nColumns = positions[0].length;
    ArrayList<Monster> monsters = new ArrayList<>();
    int monstersAmount = Math.round((float) Math.abs(nRows * nColumns) / 5);
    for (int r = 0; r < monstersAmount; r++) {
      monsters.add(new Monster( "monster" + r));
    }
    return monsters;
  }

  static void alertPlayer(String message, Persona persona) {
    if (persona.getClass() != Hero.class) {
      return;
    }
    System.out.println(message);
  }

  public Position placeHero(Hero hero) {
    start.enter(hero);
    return start.getPosition();
  }

  public int placeMonsters() {
	  this.monsters = Labyrinth.generateMonsters(this.positions);
	  int joinedMonsters = 0;
	  for (Monster monster : monsters) {
      Room freeRoom = searchFreeRoom();
      if (freeRoom == null) {
        break;
      }
      monster.joinLabyrinth(this);
      monster.setPosition(freeRoom.getPosition());
      freeRoom.enter(monster);
      // System.out.println("Monster " + monster.getName() + " entered in the labyrinth at room " + monster.getPosition().toString());
      joinedMonsters++;
    }

	  return joinedMonsters;
  }

  public boolean moveMonsters() {
	  for (int m = 0; m < monsters.size(); m++) {
		  Monster monster = monsters.get(m);
		  monster.routine();
		  // System.out.println("Monster " + monster.getName() + "is in the room " + monster.getPosition().toString());
	  }
	  return false;
  }

  public boolean movePersonaToRoomByPosition(Persona persona, Position position) {
    Room room = this.rooms.get(position);
    return room.enter(persona);
  }

  public Position[][] getPositions() {
    return positions;
  }

  public Map<Position, Room> getRooms() {
    return rooms;
  }

  public Room getStart() {
    return start;
  }

  public Position getStartPosition() {
    return start.getPosition();
  }

  public Room getEnd() {
    return end;
  }

  public Room getRoomByPosition(Position position) {
	  return rooms.get(position);
  }

  public Room searchFreeRoom() {
    if (this.nRows * this.nRows < 3) {
      System.out.println("No free room");
      return null;
    }
    for (int r = this.nRows - 1; r >= 0; r--) {
      for (int c = this.nColumns - 1; c >= 0; c--) {
    	Position position = new Position(r, c);
    	Room room = this.rooms.get(position);
        if ( room != this.start && room != this.end && room.getOccupantsNumber() == 0) {
        	return room;
        }
      }
    }

    return null;
  }

  public boolean changeRoom(Persona persona, Direction direction) {
    Position personaPosition = persona.getPosition();
    Room currentRoom = rooms.get(personaPosition);
    Room newRoom;
    Position newPosition = null;
    boolean canMove = false;
    boolean alreadyOccupied = false;

    if (!currentRoom.hasDoor(direction)) {
      Labyrinth.alertPlayer("No way", persona);
      return false;
    }


    switch (direction) {
      case NORD -> {
    	  newPosition = this.positions[personaPosition.row() - 1][personaPosition.column()];
      }
      case SUD -> {
    	  newPosition = this.positions[personaPosition.row() + 1][personaPosition.column()];
      }
      case WEST -> {
    	  newPosition = this.positions[personaPosition.row()][personaPosition.column() - 1];
      }
      case EST -> {
    	  newPosition = this.positions[personaPosition.row()][personaPosition.column() + 1];
      }
    }

    if (newPosition == null) {
    	return false;
    }

    newRoom = this.rooms.get(newPosition);

    if (!newRoom.getOccupants().isEmpty()) {
      for (Persona occupant : newRoom.getOccupants()) {
        if (occupant.getClass() == persona.getClass()) {
          alreadyOccupied = true;
          break;
        }
      }
    }

    if (!alreadyOccupied) {
      canMove = newRoom.enter(persona);
    }

    if (canMove) {
      persona.setPosition(newPosition);
      this.rooms.get(currentRoom.getPosition()).leave(persona);
    } else {
    	Labyrinth.alertPlayer("No way", persona);
    }

    return true;
  }
}
