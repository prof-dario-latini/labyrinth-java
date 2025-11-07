package it.edu.iistommasosalvini.labyrinth;

public class Labyrinth {
  private Room[][] rooms;
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
      this.rooms = this.generateRooms();
      this.start = this.rooms[0][0];
      this.end = this.rooms[this.nRows - 1][this.nColumns - 1];
  }

  private Room[][] generateRooms() {
    this.rooms = new Room[this.nRows][this.nColumns];
    for (int r = 0; r < this.nRows; r++) {
      for (int c = 0; c < this.nColumns; c++) {
        this.rooms[r][c] = new Room(
            r > 0,
            r < this.nRows -1,
            c > 0,
            c < this.nColumns -1,
            new Position(r, c));
      }
    }
    return this.rooms;
  }

  public Room getStart() {
    return start;
  }

  public Room getEnd() {
    return end;
  }

  public boolean changeRoom(Persona persona, Direction direction) {
    Room actualRoom = persona.getActualRoom();
    Position roomPosition = actualRoom.getPosition();

    if (!actualRoom.hasDoor(direction)) {
      System.out.println("No way");
      return false;
    }

    switch (direction) {
      case NORD -> {
        persona.setActualRoom(this.rooms[roomPosition.row() - 1][roomPosition.column()]);
      }
      case SUD -> {
        persona.setActualRoom(this.rooms[roomPosition.row() + 1][roomPosition.column()]);
      }
      case WEST -> {
        persona.setActualRoom(this.rooms[roomPosition.row()][roomPosition.column() - 1]);
      }
      case EST -> {
        persona.setActualRoom(this.rooms[roomPosition.row()][roomPosition.column() + 1]);
      }
    }

    System.out.println("You are in the room: " + persona.getActualRoom().getPosition().toString());

    return true;
  }
}
