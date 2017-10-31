package com.barbarum.tutorial.code;

public class TicTacToe {

    private int temp[][];

    private int n;

    private boolean end = false;

    public TicTacToe(int n) {
        this.temp = new int[2][n * 2 + 2];

        this.n = n;
    }

    public int move(int h, int v, int player) {
        if (end) {
            throw new IllegalStateException();
        }

        // horizontal
        if ((++this.temp[player - 1][h]) == n) {
            end = true;
            return player;
        }

        // vertical
        if ((++this.temp[player - 1][this.n + v]) == n) {
            end = true;
            return player;
        }

        // diagonal \
        if (h == v && (++this.temp[player - 1][this.n * 2]) == n) {
            end = true;
            return player;
        }

        // diagonal /
        if ((h + v) == this.n - 1 && (++this.temp[player - 1][this.n * 2 + 1]) == n) {
            end = true;
            return player;
        }

        return 0;
    }

    public static void main(String[] args) {
        TicTacToe test = new TicTacToe(3);
        System.out.println(test.move(0, 0, 1));
        System.out.println(test.move(0, 2, 2));
        System.out.println(test.move(2, 2, 1));
        System.out.println(test.move(1, 1, 2));
        System.out.println(test.move(2, 0, 1));
        System.out.println(test.move(1, 0, 2));
        System.out.println(test.move(2, 1, 1));
    }
}
