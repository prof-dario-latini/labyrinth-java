package it.edu.iistommasosalvini.labyrinth;

import java.util.ArrayList;

public class Persona {
  private String name;
  private int life;
  private final int maxLife;
  private Room actualRoom;
  private ArrayList<Direction> movements;
  private final Labyrinth labyrinth;

  public Persona(Labyrinth labyrinth, String name) {
    this(labyrinth, name, 1);
  }

  public Persona(Labyrinth labyrinth, String name, int life) {
    this.labyrinth = labyrinth;
    this.name = name;
    this.life = life;
    this.maxLife = life;
    this.actualRoom = this.labyrinth.getStart();
    this.movements = new ArrayList<>();
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

  public Room getActualRoom() {
    return actualRoom;
  }

  public void setActualRoom(Room actualRoom) {
    this.actualRoom = actualRoom;
  }

  public void move(Direction direction) {
    if (this.labyrinth.changeRoom(this, direction)) {
      this.movements.add(direction);
    }
  }
}
