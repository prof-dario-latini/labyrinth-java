package it.edu.iistommasosalvini.labyrinth;

public class Hero extends Persona {
  public Hero(String name, int life) {
    super(name, life);
  }
  public void enterLabyrinth() {
    setPosition(labyrinth.placeHero(this));
  }

  @Override
  public void joinLabyrinth(Labyrinth labyrinth) {
    super.joinLabyrinth(labyrinth);
    enterLabyrinth();
  }
}
