import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static int Answer;
    static int[][] grid = new int[101][101];

    static int[] x_d = {1, 0, -1, 0};
    static int[] y_d = {0, -1, 0, 1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        // the number of dragon curves
        int N = sc.nextInt();
        Answer = 0;

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int g = sc.nextInt();
            LinkedList<Location> curves = new LinkedList<>();
            curves.add(new Location(x, y));
            dragonCurve(curves, d, g, 0);
        }
        // check curves in square
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (grid[i][j] == 1 && grid[i + 1][j] == 1 && grid[i][j + 1] == 1 && grid[i + 1][j + 1] == 1) {
                    Answer++;
                }
            }
        }

        System.out.println(Answer);
    }

    private static void dragonCurve(LinkedList<Location> curves, int d, int g, int currentGeneration) {
        int x = curves.peekLast().x;
        int y = curves.peekLast().y;

        if (currentGeneration == 0) {
            grid[x][y] = 1;
            x += x_d[d];
            y += y_d[d];
            grid[x][y] = 1;
            curves.add(new Location(x, y));
        } else {
            for (int i = curves.size() - 2; i >= 0; i--) {
                int temp_x = curves.get(i).x - x;
                int temp_y = curves.get(i).y - y;
                int n_x = -temp_y + x;
                int n_y = temp_x + y;
                if (n_x >= 0 && n_x <= 100 && n_y >= 0 && n_y <= 100) {
                    curves.add(new Location(n_x, n_y));
                    grid[n_x][n_y] = 1;
                }
            }
        }

        if (currentGeneration == g) return;
        else {
            dragonCurve(curves, d, g, currentGeneration + 1);
        }
    }
}

class Location {
    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
