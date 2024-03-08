package javasolve.level3.sell;

import java.util.*;

class Solution {

    static Node center = new Node("center");
    static Map<String, Node> map = new HashMap<>();

    static class Node {
        String name;
        int profit;
        Node parent;

        public Node(String name) {
            this.name = name;
            this.profit = 0;
            this.parent = null;
        }
    }

    Node findByName(String name) {

        return map.get(name);
    }

    void sell(Node node, int money) {

        if (node.parent == null) {
            node.profit += money;
            return;
        }
        node.profit += (money - money / 10);
        sell(node.parent, money / 10);

    }


    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        int[] answer = new int[enroll.length];


        for (String name : enroll) {
            Node node = new Node(name);
            map.put(name, node);
        }


        for (int i = 0; i < enroll.length; i++) {
            Node child = findByName(enroll[i]);
            String parentName = referral[i];

            Node parent;

            if (parentName.equals("-")) {
                parent = center;
            } else {
                parent = findByName(parentName);
            }
            child.parent = parent;
        }

        for (int i = 0; i < seller.length; i++) {
            Node sellNode = findByName(seller[i]);
            sell(sellNode, 100 * amount[i]);
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = findByName(enroll[i]).profit;
        }

        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        int[] result = new Solution().solution(enroll, referral, seller, amount);
        for (int i : result) {
            System.out.println("i = " + i);
        }
    }
}
