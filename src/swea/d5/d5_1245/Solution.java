package swea.d5.d5_1245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int[] positions;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            positions = new int[n];
            weights = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                positions[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < n - 1; i++) {
                double result = binarySearch(i, i + 1);
                sb.append(String.format("%.10f", result)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static double binarySearch(int i, int j) {
        double left = positions[i];
        double right = positions[j];

        int maxCount = 100;  // 무한 루프 방지를 위해 충분히 큰 값 설정
        int count = 0;

        while (count < maxCount) {
            double mid = (left + right) / 2;

            double s = sum(0, i, mid);
            s -= sum(j, n - 1, mid);

            if (s > 0) {
                left = mid;
            } else {
                right = mid;
            }
            count++;
        }

        return (left + right) / 2;
    }

    private static double sum(int start, int end, double pos) {
        double s = 0.0;

        for (int i = start; i <= end; i++) {
            s += weights[i] / Math.pow(pos - positions[i], 2);
        }
        return s;
    }
}

