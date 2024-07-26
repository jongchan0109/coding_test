package swea.d5.d5_1242;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static String[] binary = {
            "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
            "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    static int[][][] subCode;

    static int n;
    static int m;

    static int[][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        subCode = new int[5][5][5];

        subCode[2][1][1] = 0;
        subCode[2][2][1] = 1;
        subCode[1][2][2] = 2;
        subCode[4][1][1] = 3;
        subCode[1][3][2] = 4;
        subCode[2][3][1] = 5;
        subCode[1][1][4] = 6;
        subCode[3][1][2] = 7;
        subCode[2][1][3] = 8;
        subCode[1][1][2] = 9;

        StringBuilder sb = new StringBuilder();


        for (int t = 1; t <= T; t++) {

            arr = new int[2000][2000];
            // 입력 및 초기화

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int sum = 0;


            for (int i = 0; i < n; i++) {
                String line = br.readLine();

                for (int j = 0; j < m; j++) {
                    String binary = toBinary(line.charAt(j));

                    for (int k = 0; k < 4; k++) {
                        arr[i][j * 4 + k] = binary.charAt(k) - '0';
                    }

                }
            }

            //배열 순회

            int[] numbers = new int[9];
            int idx = 8;

            for (int i = 1; i < n; i++) {
                for (int j = m * 4 - 1; j >= 8; j--) {

                    if (arr[i][j] == 1 && arr[i - 1][j] == 0) {
                        int x = 0;
                        int y = 0;
                        int z = 0;

                        while (arr[i][j] == 1) {
                            z++;
                            j--;
                        }

                        while (arr[i][j] == 0) {
                            y++;
                            j--;
                        }
                        while (arr[i][j] == 1) {
                            x++;
                            j--;
                        }

                        int min = Math.min(Math.min(x, y), z);

                        x /= min;
                        y /= min;
                        z /= min;


                        numbers[idx] = subCode[x][y][z];

                        idx--;

                        if (idx == 0) {

                            int s = 0;

                            s += numbers[1] + numbers[3] + numbers[5] + numbers[7];
                            s *= 3;
                            s += numbers[2] + numbers[4] + numbers[6] + numbers[8];

                            if (s % 10 == 0) {

                                for (int k = 1; k <= 8; k++) {
                                    sum += numbers[k];
                                }
                            }

                            idx = 8;
                        }


                    }


                }
            }

            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }


    private static String toBinary(char ch) {
        return ('0' <= ch && ch <= '9') ? binary[ch - '0'] : binary[ch - 'A' + 10];
    }
}
