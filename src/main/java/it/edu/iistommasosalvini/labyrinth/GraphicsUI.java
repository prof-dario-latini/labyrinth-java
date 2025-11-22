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

  static int heroFrameCount = 0;
  static final int heroTotalFrame = 4;

  static int monsterFrameCount = 0;
  static final int monsterTotalFrame = 4;

  static final String ASSETS_PATH = "assets/graphics/";

  static final Map<String, String> SPRITES = Map.of(
      "room", "dungeon/room/room",
      "south-perimeter", "dungeon/room/south-perimeter",
      "north-door", "dungeon/room/doors/north",
      "south-door", "dungeon/room/doors/south",
      "west-door", "dungeon/room/doors/west",
      "east-door", "dungeon/room/doors/east",
      "hero", "hero/hero",
      "monster", "monster/monster",
      "logo",  "interface/logo",
      "background",  "interface/bg"
  );

  static final Map<Direction, Point> DOORS_RELATIVE_POSITIONS = Map.of(
      Direction.NORTH, new Point(60, 0),
      Direction.SOUTH, new Point(60, 155),
      Direction.WEST, new Point(0, 63),
      Direction.EAST, new Point(164, 63)
  );

  static public void drawLogo(Point pos, Graphics g, ImageObserver observer) {
    BufferedImage logo = loadImage(getPathname("logo"));
    Image scaledLogo = logo.getScaledInstance(322, 154, Image.SCALE_SMOOTH);
    draw(scaledLogo, pos, g, observer);
  }

  static public void drawBackground(Point pos, Graphics g, ImageObserver observer) {
    Point bgPoint = (Point) pos.clone();
    bgPoint.translate(0, 17);
    BufferedImage bg = loadImage(getPathname("background"));
    Image scaledBg = bg.getScaledInstance(947, 172, Image.SCALE_SMOOTH);
    draw(scaledBg, bgPoint, g, observer);
  }

  static public void printLabyrinth(Labyrinth labyrinth, Graphics g, ImageObserver observer) {
    Position[][] positions = labyrinth.getPositions();
    int nRows = positions.length;
    int nColumns = positions[0].length;

    monsterFrameCount = (monsterFrameCount + 1) % monsterTotalFrame;

    for (int r = 0; r < nRows; r++) {
      for (int c = 0; c < nColumns; c++) {
        Point p = new Point(c * 190, r * 177);
        Position position = positions[r][c];
        Room room = labyrinth.getRoomByPosition(position);
        drawRoom(room, (Point) p.clone(), g, observer);
        if (r == nRows - 1) {
          Point perimeterPoint = (Point) p.clone();
          perimeterPoint.translate(0, 177 );
          draw(loadImage(getPathname("south-perimeter")), perimeterPoint, g, observer);
        }
        List<Persona> occupants = room.getOccupants();
        if (!occupants.isEmpty()) {
          if (occupants.size() == 1) {
            p.translate(78, 78);
            if (occupants.getFirst().getClass() == Hero.class) {
              draw(loadHero(), p, g, observer);
            } else {
              draw(loadMonster(), p, g, observer);
            }
          } else {
            p.translate(48, 78);
            draw(loadHero(), p, g, observer);
            p.translate(58, 0);
            draw(loadMonster(), p, g, observer);
          }
        }
      }
    }
  }

  static private String getPathname(String type) {
    return ASSETS_PATH + SPRITES.get(type) + ".png";
  }

  static private String getPathname(String type, int frame) {
    return ASSETS_PATH + SPRITES.get(type) + frame + ".png";
  }

  static private BufferedImage loadImage(String pathname) {
    try {
      // you can use just the filename if the image file is in your
      // project folder, otherwise you need to provide the file path.
      return ImageIO.read(new File(pathname));
    } catch (IOException exc) {
      System.out.println("Error opening image file: " + pathname + exc.getMessage());
    }
    return null;
  }

  static private BufferedImage loadHero() {
    heroFrameCount = (heroFrameCount + 1) % heroTotalFrame;
    return loadImage(getPathname("hero", heroFrameCount));
  }

  static private BufferedImage loadMonster() {
    return loadImage(getPathname("monster", monsterFrameCount));
  }

  static public void draw(Image image, Point pos, Graphics g, ImageObserver observer) {
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
    draw(loadImage(getPathname("room")), pos, g, observer);
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
    draw(loadImage(getPathname(name)), doorPosition, g, observer);
  }
}
