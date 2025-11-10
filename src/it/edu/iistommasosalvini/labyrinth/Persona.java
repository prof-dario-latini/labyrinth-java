package it.edu.iistommasosalvini.labyrinth;

import java.util.ArrayList;
import java.util.List;

public class Persona {
  private String name;
  private int life;
  private final int maxLife;
  private Position position;
  private final List<Direction> movements;
  private Labyrinth labyrinth;

  public Persona(String name) {
    this(name, 1);
  }

  public Persona(String name, int life) {
    this.name = name;
    this.life = life;
    this.maxLife = life;
    this.movements = new ArrayList<>();
  }

  public void joinLabyrinth(Labyrinth labyrinth) {
    this.labyrinth = labyrinth;
    this.position = this.labyrinth.getStartPosition();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLife() {
    return life;
  }

  public void setLife(int life) {
    this.life = life;
  }

  public int changeLife(Integer amount) {
    if (amount == null) {
      return this.life;
    }

    this.life += amount;

    if (this.life < 0) {
      this.life = 0;
    }

    if (this.life > this.maxLife) {
      this.life = this.maxLife;
    }

    return this.getLife();
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Position move(Direction direction) {
    if (this.labyrinth == null) {
      System.out.println("Error: "+ this.name +" is not in the labyrinth!");
      return null;
    }
    if (this.labyrinth.changeRoom(this, direction)) {
      this.movements.add(direction);
      return this.position;
    }
    
    return null;
  }
}
