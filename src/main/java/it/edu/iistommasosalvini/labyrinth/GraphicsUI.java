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
      "hero", "assets/graphics/hero/hero.png",
      "room", "assets/graphics/dungeon/room.png",
      "monster", "assets/graphics/monster/monster.png"
  );

  static public void printLabyrinth(Labyrinth labyrinth, Graphics g, ImageObserver observer) {
    Position[][] positions = labyrinth.getPositions();
    int nRows = positions.length;
    int nColumns = positions[0].length;

    for (int r = 0; r < nRows; r++) {
      for (int c = 0; c < nColumns; c++) {
        Point p = new Point(r * 200, c * 201);
        Position position = positions[r][c];
        Room room = labyrinth.getRoomByPosition(position);
        List<Persona> occupants = room.getOccupants();
        draw(loadImage("room"), g, p, observer);
        if (!occupants.isEmpty()) {
          if (occupants.size() == 1) {
            p.translate(80, 80);
            if (occupants.getFirst().getClass() == Hero.class) {
              draw(loadImage("hero"), g, p, observer);
            } else {
              draw(loadImage("monster"), g, p, observer);
            }
          } else {
            p.translate(40, 80);
            draw(loadImage("hero"), g, p, observer);
            p.translate(40, 0);
            draw(loadImage("monster"), g, p, observer);
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

  static public void draw(BufferedImage image, Graphics g, Point pos, ImageObserver observer) {
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
}
