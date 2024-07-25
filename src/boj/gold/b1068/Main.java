package boj.gold.b1068;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Node {
        int num;
        List<Node> children;

        public Node(int num) {
            this.num = num;
            this.children = new ArrayList<>();
        }
    }

    static List<Node> tree;
    static Node root;
    static Node removeNode;
    static int count;

    public static void main(String[] args) {
        tree = new ArrayList<>();
        count = 0;
        input();

        dfs(root);

        System.out.println(count);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            tree.add(new Node(i));
        }

        for (int i = 0; i < n; i++) {
            Node current = tree.get(i);
            int num = scanner.nextInt();

            if (num == -1) {
                root = current;
                continue;
            }

            Node parent = tree.get(num);
            parent.children.add(current);
        }

        removeNode = tree.get(scanner.nextInt());

    }

    private static void dfs(Node node) {

        if (node.equals(removeNode)) {
            return;
        }

        if (node.children.isEmpty() || (node.children.size() == 1 && node.children.get(0).equals(removeNode))) {
            count++;
            return;
        }

        for (Node child : node.children) {
            dfs(child);
        }
    }
}
