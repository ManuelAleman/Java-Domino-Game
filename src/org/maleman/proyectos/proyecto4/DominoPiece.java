package org.maleman.proyectos.proyecto4;

public class DominoPiece {

    private int left;
    private int right;
    private int weight;

    public DominoPiece(int left, int right) {
        this.left = left;
        this.right = right;
        this.weight = left + right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public void swap() {
        int aux = left;
        left = right;
        right = aux;
    }

    @Override
    public String toString() {
        return "[" + left + "|" + right + "]";
    }
}