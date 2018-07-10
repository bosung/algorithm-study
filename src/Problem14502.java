import java.util.*;

class Problem14502 {

    static int Answer;

    public void solve() throws Exception {
        Scanner sc = new Scanner(System.in);
        //Scanner sc = new Scanner(new FileInputStream("input.txt"));

        Answer = 0;

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] lab = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                lab[i][j] = sc.nextInt();
            }
        }

        int total = N * M;
        for (int i = 0; i < total; i++) {
            int r1 = i / M;
            int c1 = i % M;
            if (lab[r1][c1] != 0) { continue; }
            for (int j = i + 1; j < total; j++) {
                int r2 = j / M;
                int c2 = j % M;
                if (lab[r2][c2] != 0) { continue; }
                for (int k = j + 1; k < total; k++) {
                    int r3 = k / M;
                    int c3 = k % M;
                    if (lab[r3][c3] != 0) { continue; }
                    int[][] tempLab = new int[N][M];

                    // deep copy
                    for (int x = 0; x < N; x++) {
                        for (int y = 0; y < M; y++) {
                            tempLab[x][y] = lab[x][y];
                        }
                    }

                    tempLab[r1][c1] = tempLab[r2][c2] = tempLab[r3][c3] = 1;

                    // virus spread
                    bfs(tempLab, N, M);
                    int zeros = count(tempLab, N, M);
                    if (zeros > Answer) {
                        Answer = zeros;
                    }
                }
            }
        }

        System.out.println(Answer);
    }

    public static void bfs(int[][] lab, int N, int M) {
        LinkedList<Integer> q = new LinkedList<>();

        for (int i = 0; i < N * M; i++) {
            int row = i / M;
            int col = i % M;
            if (lab[row][col] == 2) {
                q.add(i);
            }
            while(q.size() > 0) {
                int cur = q.remove(0);
                int curRow = cur / M;
                int curCol = cur % M;
                if (curCol + 1 < M && lab[curRow][curCol+1] == 0) {
                    q.add(cur+1);
                    lab[curRow][curCol+1] = 2;
                }
                if (curRow + 1 < N && lab[curRow+1][curCol] == 0) {
                    q.add(cur+M);
                    lab[curRow+1][curCol] = 2;
                }
                if (curCol - 1 >= 0 && lab[curRow][curCol-1] == 0) {
                    q.add(cur-1);
                    lab[curRow][curCol-1] = 2;
                }
                if (curRow - 1 >= 0 && lab[curRow-1][curCol] == 0) {
                    q.add(cur-M);
                    lab[curRow-1][curCol] = 2;
                }
            }
        }
    }

    public static int count(int[][] lab, int N, int M) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}
