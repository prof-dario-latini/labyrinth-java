package it.edu.iistommasosalvini.labyrinth;

import java.awt.*;
import java.util.List;

public class TextUI {
  static public void printLabyrinth(Labyrinth labyrinth) {
    Position[][] positions = labyrinth.getPositions();
    int nRows = positions.length;
    int nColumns = positions[0].length;

    for (int r = 0; r < nRows; r++) {
      for (int l = 0; l < 5; l++) {
        for (int c = 0; c < nColumns; c++) {
          Position position = positions[r][c];
          Room room = labyrinth.getRoomByPosition(position);
          List<Persona> occupants = room.getOccupants();
          switch (l) {
            case 0 -> {
              System.out.print("===");
              if (room.hasDoor(Direction.NORD)) {
                System.out.print("--");
              } else {
                System.out.print("==");
              }
              System.out.print("===");
            }
            case 1,3 -> {
              System.out.print("║  ➕  ║");
            }
            case 2 -> {
              if (room.hasDoor(Direction.WEST)) {
                System.out.print("|  ");
              } else {
                System.out.print("║  ");
              }
              if (!occupants.isEmpty()) {
                if (occupants.size() == 1) {
                  if (occupants.getFirst().getClass() == Hero.class) {
                    // 🧙‍♂️
                    System.out.print("\uD83E\uDDD9\u200D♂\uFE0F");
                  } else {
                    // 🧟‍♂️
                    System.out.print("\uD83E\uDDDF\u200D♂\uFE0F");
                  }
                } else {
                  // 💀
                  System.out.print("\uD83D\uDC80");
                }
              } else {
                System.out.print("➕");
              }
              if (room.hasDoor(Direction.EST)) {
                System.out.print("  |");
              } else {
                System.out.print("  ║");
              }
            }
            case 4 -> {
              System.out.print("===");
              if (room.hasDoor(Direction.SUD)) {
                System.out.print("--");
              } else {
                System.out.print("==");
              }
              System.out.print("===");
            }
          }
        }
        System.out.println();
      }

    }
  }
  static public void clearScreen() {
    System.out.print("\033\143");
    System.out.flush();
  }
}
