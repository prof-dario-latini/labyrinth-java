import it.edu.iistommasosalvini.labyrinth.Direction;
import it.edu.iistommasosalvini.labyrinth.Hero;
import it.edu.iistommasosalvini.labyrinth.Labyrinth;
import it.edu.iistommasosalvini.labyrinth.Persona;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    System.out.println("Welcome in the Labyrinth!");

    Labyrinth labyrinth = new Labyrinth(4, 5);

    System.out.println("What is your hero name?");
    Scanner in = new Scanner(System.in);
    Persona hero = new Hero(in.nextLine(), 3);
    hero.joinLabyrinth(labyrinth);
    System.out.println(hero.getName() + " your journey begins!'");
    Labyrinth.printCommands();
    String command;

    System.out.println("You are in the room" + hero.getPosition().toString());

    do {
      command = in.nextLine().trim().toUpperCase();
      if (command.equals("QUIT")) {
        break;
      }

      if (command.equals("HELP")) {
        Labyrinth.printCommands();
        continue;
      }

      try {
        Direction direction = Direction.valueOf(command);
        hero.move(direction);

      } catch (IllegalArgumentException e ) {
        System.out.println("Invalid direction!");
        Labyrinth.printCommands();
      }

    } while (!hero.getPosition().equals(labyrinth.getEnd().getPosition()));

    if (command.equals("QUIT")) {
      System.out.println(hero.getName() + " quits... you better try hard next time.");
    } else {
      System.out.println(hero.getName() + " wins!");
    }
    
    in.close();
  }
}
