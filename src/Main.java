import it.edu.iistommasosalvini.labyrinth.*;

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
    System.out.println("Enter any key to start");
    in.next();
    TextUI.clearScreen();

    do {
      System.out.println("You are in the room" + hero.getPosition().toString());
      command = in.nextLine().trim().toUpperCase();
      if (command.equals("QUIT")) {
        break;
      }

      if (command.equals("HELP")) {
        Labyrinth.printCommands();
        continue;
      }

      TextUI.clearScreen();

      try {
        Direction direction = Direction.valueOf(command);
        hero.move(direction);
        labyrinth.moveMonsters();

        if (labyrinth.getRoomByPosition(hero.getPosition()).getOccupantsNumber() > 1) {
      	  System.out.println("You are not alone...");
        }

      } catch (IllegalArgumentException e ) {
        System.out.println("Invalid direction!");
        Labyrinth.printCommands();
      }

      TextUI.printLabyrinth(labyrinth);

    } while (!hero.getPosition().equals(labyrinth.getEnd().getPosition()));

    if (command.equals("QUIT")) {
      System.out.println(hero.getName() + " quits... you better try hard next time.");
    } else {
      System.out.println(hero.getName() + " wins!");
    }

    in.close();
  }
}
