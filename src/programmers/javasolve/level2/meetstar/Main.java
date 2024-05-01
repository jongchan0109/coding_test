package programmers.javasolve.level2.meetstar;

import java.util.*;

class Solution {

    static class Point{
        int x;
        int y;
        Point(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Line {
        int a;
        int b;
        int c;

        Line(int[] line) {
            this.a = line[0];
            this.b = line[1];
            this.c = line[2];
        }

        Point findMeet(Line line) {
            int d = line.a;
            int e = line.b;
            int f = line.c;


            if (a * e == b * d) {
                return null;
            }

            if ((b * f - c * e) % (a * e - b * d) == 0 && (c * d - a * f) % (a * e - b * d) == 0) {
                return new Point((b * f - c * e) / (a * e - b * d), (c * d - a * f) / (a * e - b * d));
            }
            return null;
        }
    }

    public String[] solution(int[][] line) {
        List<Line> lines = new ArrayList<>();
        List<Point> points = new ArrayList<>();



        for(int[] li : line) {
            lines.add(new Line(li));
        }

        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Point p = lines.get(i).findMeet(lines.get(j));
                if (p != null) {
                    points.add(p);
                }
            }
        }

        int xMin = points.get(0).x;
        int yMin = points.get(0).y;
        int xMax = points.get(0).x;
        int yMax = points.get(0).y;


        for(Point point: points) {
            if (point.x > xMax) {
                xMax = point.x;
            }
            if (point.x < xMin) {
                xMin = point.x;
            }

            if (point.y > yMax) {
                yMax = point.y;
            }
            if (point.y < yMin) {
                yMin = point.y;
            }
        }


        String[] answer = new String[yMax - yMin + 1];
        Arrays.fill(answer, ".".repeat(xMax - xMin + 1));

        for(Point point: points) {
            int x = point.x - xMin;
            int y = point.y - yMin;
            answer[y] = answer[y].substring(0, x) + "*" + answer[y].substring(x + 1);

        }
        int length = answer.length;

        for (int i = 0; i < length / 2; i++) {
            String temp = answer[i];
            answer[i] = answer[length - i - 1];
            answer[length - i - 1] = temp;
        }
        return answer;
    }
}
public class Main {
    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};

        String[] answer = new Solution().solution(line);

        for (String s : answer) {
            System.out.println(s);
        }
    }
}
