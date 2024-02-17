package javasolve.level3.tablemerge;

import java.util.ArrayList;
import java.util.List;

class Solution {

    static class Group {

        String str;
        List<int[]> points = new ArrayList<>();
        public Group(String str) {
            this.str = str;
        }

        public void add(int row, int col) {
            points.add(new int[]{row, col});
        }

        public boolean contain(int row, int col) {
            for (int[] point : points) {
                if (point[0] == row && point[1] == col) {
                    return true;
                }
            }
            return false;
        }
    }


    static class Table {
        List<String> result;
        List<Group> groups;

        public Table() {
            result = new ArrayList<>();
            groups = new ArrayList<>();

            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    Group group = new Group(null);
                    group.add(i, j);
                    groups.add(group);
                }
            }

        }


        public void input(String str) {
            String[] split = str.split(" ");
            String command = split[0];

            switch (command) {
                case "UPDATE":
                    if (split.length == 3) {
                        update(split[1], split[2]);
                    } else {
                        update(split[1], split[2], split[3]);
                    }
                    break;
                case "MERGE":
                    merge(split[1], split[2], split[3], split[4]);
                    break;
                case "UNMERGE":
                    unmerge(split[1], split[2]);
                    break;
                case "PRINT":
                    print(split[1], split[2]);

                    break;
            }

        }

        public void update(String value1, String value2) {

            for (Group group : groups) {
                if (group.str == null) {
                    continue;
                }
                if (group.str.equals(value1)) {
                    group.str = value2;
                }
            }

        }

        public void update(String row, String col, String value) {
            int r = Integer.parseInt(row);
            int c = Integer.parseInt(col);

            for (Group group : groups) {
                if (group.contain(r, c)) {
                    group.str = value;
                }
            }
        }


        public void merge(String row1, String col1, String row2, String col2) {
            int r1 = Integer.parseInt(row1);
            int c1 = Integer.parseInt(col1);
            int r2 = Integer.parseInt(row2);
            int c2 = Integer.parseInt(col2);


            if (r1 == r2 && c1 == c2) {
                return;
            }

            Group group1 = null;
            Group group2 = null;

            for (Group group : groups) {
                if (group.contain(r1, c1)) {
                    group1 = group;
                }
                if (group.contain(r2, c2)) {
                    group2 = group;
                }
            }


            if (group1 == null || group2 == null) {
                return;
            }


            String str;

            if (group1.str != null) {
                str = group1.str;
            } else {
                str = group2.str;
            }

            Group group = new Group(str);

            for (int[] point : group1.points) {
                group.add(point[0], point[1]);
            }

            for (int[] point : group2.points) {
                group.add(point[0], point[1]);
            }

            groups.remove(group1);
            groups.remove(group2);

            groups.add(group);
        }

        public void unmerge(String row, String col) {
            int r = Integer.parseInt(row);
            int c = Integer.parseInt(col);

            Group removeGroup = null;

            for (Group group : groups) {
                if (group.contain(r, c)) {
                    removeGroup = group;

                    for (int[] point : group.points) {
                        Group addGroup;
                        if (point[0] == r && point[1] == c) {
                            addGroup = new Group(removeGroup.str);
                        } else {
                            addGroup = new Group(null);
                        }
                        addGroup.add(point[0], point[1]);
                        groups.add(addGroup);
                    }
                    break;
                }
            }
            groups.remove(removeGroup);
        }

        public void print(String row, String col) {
            int r = Integer.parseInt(row);
            int c = Integer.parseInt(col);


            for (Group group : groups) {
                if (group.contain(r,c)) {
                    if (group.str == null) {
                        result.add("EMPTY");
                        return;
                    }
                    result.add(group.str);
                    return;
                }
            }
        }
    }

    public String[] solution(String[] commands) {
        Table table = new Table();

        for (String command : commands) {
            table.input(command);

        }


        return table.result.toArray(new String[0]);

    }

}

public class Main {
    public static void main(String[] args) {
        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik",
                "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};

        String[] result = new Solution().solution(commands);
        for (String s : result) {
            System.out.print(s + " ");
        }
    }
}

