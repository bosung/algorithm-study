import java.util.Scanner;

public class Main {

    static int Answer = 0;

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        int[][] room = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                room[i][j] = sc.nextInt();
            }
        }

        int curY = r;
        int curX = c;
        int curD = d;

        int xd[] = {0, 1, 0, -1};
        int yd[] = {-1, 0, 1, 0};

        boolean nowhere = false;
        while(true) {
            if (room[curY][curX] == 0 || room[curY][curX] == 2) {
                if (room[curY][curX] == 0) {
                    room[curY][curX] = 2;
                    Answer++;
                }

                int rotate = 0;
                // search left
                int di = (curD - 1);
                if (di == -1) di = 3;
                while (true) {
                    if (room[curY + yd[di]][curX + xd[di]] == 0) {
                        curX = curX + xd[di];
                        curY = curY + yd[di];
                        curD = di;
                        break;
                    } else {
                        di = di - 1;
                        if (di == -1) di = 3;
                        rotate++;
                    }

                    if (rotate == 4) {
                        nowhere = true;
                        curD = di+1;
                        if(curD == 4) curD = 0;
                        break;
                    }
                }
            }
            if (nowhere) {
                if (room[curY - yd[curD]][curX - xd[curD]] == 1) {
                    break;
                } else {
                    curX = curX - xd[curD];
                    curY = curY - yd[curD];
                    nowhere = false;
                }
            }
        }

        System.out.println(Answer);
    }
}
