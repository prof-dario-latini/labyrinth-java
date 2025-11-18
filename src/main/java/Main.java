import it.edu.iistommasosalvini.labyrinth.*;

import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        initWindow();
      }
    });
  }

  static void oldGame() {
    TextUI.clearScreen();
    System.out.println("Welcome in the Labyrinth!");

    Labyrinth labyrinth = new Labyrinth(4, 5);

    System.out.println("What is your hero name?");
    Scanner in = new Scanner(System.in);
    Hero hero = new Hero(in.nextLine(), 3);
    hero.joinLabyrinth(labyrinth);
    System.out.println(hero.getName() + " your journey begins!'");
    Labyrinth.printCommands();
    System.out.println("Enter any key to start");
    Main.waitReturnKey();
    System.out.println("Your run will start in:");
    for (int cont = 5; cont > 0; cont--) {
      System.out.println(cont + " seconds.");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.out.println(e.toString());
      }
    }

    String command;
    do {
      TextUI.clearScreen();
      System.out.println(hero.getName() + " is in the room" + hero.getPosition().toString());
      TextUI.printLabyrinth(labyrinth);
      System.out.println("What is your next move?");
      command = in.nextLine().trim().toUpperCase();
      if (command.equals("QUIT")) {
        break;
      }

      if (command.equals("HELP")) {
        Labyrinth.printCommands();
        Main.waitReturnKey();
        continue;
      }

      try {
        Direction direction = Direction.valueOf(command);
        hero.move(direction);
        labyrinth.moveMonsters();

        if (labyrinth.getRoomByPosition(hero.getPosition()).getOccupantsNumber() > 1) {
          System.out.println("You are not alone...");
          Main.waitReturnKey();
        }

      } catch (IllegalArgumentException e ) {
        System.out.println("Invalid direction!");
        Labyrinth.printCommands();
        Main.waitReturnKey();
      }

    } while (!hero.getPosition().equals(labyrinth.getEnd().getPosition()));

    TextUI.clearScreen();
    TextUI.printLabyrinth(labyrinth);

    if (command.equals("QUIT")) {
      System.out.println(hero.getName() + " quits... you better try hard next time.");
    } else {
      System.out.println(hero.getName() + " wins!");
    }

    in.close();
  }

  static void waitReturnKey() {
    try{
      System.in.read();
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }

  private static void initWindow() {
    // create a window frame and set the title in the toolbar
    JFrame window = new JFrame("Can't Stop, Won't Stop, GameStop");
    // when we close the window, stop the app
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create the jpanel to draw on.
    // this also initializes the game loop
    Board board = new Board();
    // add the jpanel to the window
    window.add(board);
    // pass keyboard inputs to the jpanel
    window.addKeyListener(board);

    // don't allow the user to resize the window
    window.setResizable(false);
    // fit the window size around the components (just our jpanel).
    // pack() should be called after setResizable() to avoid issues on some platforms
    window.pack();
    // open window in the center of the screen
    window.setLocationRelativeTo(null);
    // display the window
    window.setVisible(true);
  }
}
