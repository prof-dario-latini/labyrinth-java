package it.edu.iistommasosalvini.labyrinth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class GraphicsUI {


  static final Map<String, String> SPRITES = Map.of(
      "room", "assets/graphics/dungeon/room/room.png",
      "north-door", "assets/graphics/dungeon/room/doors/north.png",
      "south-door", "assets/graphics/dungeon/room/doors/south.png",
      "west-door", "assets/graphics/dungeon/room/doors/west.png",
      "east-door", "assets/graphics/dungeon/room/doors/east.png",
      "hero", "assets/graphics/hero/hero.png",
      "monster", "assets/graphics/monster/monster.png"
  );

  static final Map<Direction, Point> DOORS_RELATIVE_POSITIONS = Map.of(
      Direction.NORTH, new Point(60, 0),
      Direction.SOUTH, new Point(60, 155),
      Direction.WEST, new Point(0, 63),
      Direction.EAST, new Point(164, 63)
  );

  static public void printLabyrinth(Labyrinth labyrinth, Graphics g, ImageObserver observer) {
    Position[][] positions = labyrinth.getPositions();
    int nRows = positions.length;
    int nColumns = positions[0].length;

    for (int r = 0; r < nRows; r++) {
      for (int c = 0; c < nColumns; c++) {
        Point p = new Point(c * 190, r * 177);
        Position position = positions[r][c];
        Room room = labyrinth.getRoomByPosition(position);
        drawRoom(room, (Point) p.clone(), g, observer);
        List<Persona> occupants = room.getOccupants();
        if (!occupants.isEmpty()) {
          if (occupants.size() == 1) {
            p.translate(80, 80);
            if (occupants.getFirst().getClass() == Hero.class) {
              draw(loadImage("hero"), p, g, observer);
            } else {
              draw(loadImage("monster"), p, g, observer);
            }
          } else {
            p.translate(50, 80);
            draw(loadImage("hero"), p, g, observer);
            p.translate(60, 0);
            draw(loadImage("monster"), p, g, observer);
          }
        }
      }
    }
  }

  static private BufferedImage loadImage(String type) {
    try {
      // you can use just the filename if the image file is in your
      // project folder, otherwise you need to provide the file path.
      return ImageIO.read(new File(SPRITES.get(type)));
    } catch (IOException exc) {
      System.out.println("Error opening image file: " + exc.getMessage());
    }
    return null;
  }

  static public void draw(BufferedImage image, Point pos, Graphics g, ImageObserver observer) {
    // with the Point class, note that pos.getX() returns a double, but
    // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
    // this is also where we translate board grid position into a canvas pixel
    // position by multiplying by the tile size.
    g.drawImage(
        image,
        pos.x,
        pos.y,
        observer
    );
  }

  static private void drawRoom(Room room, Point pos, Graphics g, ImageObserver observer) {
    draw(loadImage("room"), pos, g, observer);
    if (room.hasDoor(Direction.NORTH)) {
      drawDoor(room, "north-door", Direction.NORTH, pos, g, observer);
    }
    if (room.hasDoor(Direction.SOUTH)) {
      drawDoor(room, "south-door", Direction.SOUTH, pos, g, observer);
    }
    if (room.hasDoor(Direction.WEST)) {
      drawDoor(room, "west-door", Direction.WEST, pos, g, observer);
    }
    if (room.hasDoor(Direction.EAST)) {
      drawDoor(room, "east-door", Direction.EAST, pos, g, observer);
    }
  }

  static private void drawDoor(Room room, String name, Direction d, Point pos, Graphics g, ImageObserver observer) {
    Point doorPosition = (Point) pos.clone();
    doorPosition.translate(DOORS_RELATIVE_POSITIONS.get(d).x, DOORS_RELATIVE_POSITIONS.get(d).y);
    draw(loadImage(name), doorPosition, g, observer);
  }
}
