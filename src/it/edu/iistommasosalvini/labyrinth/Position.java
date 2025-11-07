package it.edu.iistommasosalvini.labyrinth;

public record Position(int row, int column) {

  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position(int row1, int column1))) return false;
    return row == row1 && column == column1;
  }

}
