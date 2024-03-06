package javasolve.level3.newtable;

import java.util.*;

class Solution {

    static class Node {
        int index;
        Node prev;
        Node next;

        public Node(int index) {
            this.index = index;
            this.prev = null;
            this.next = null;
        }

        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }

        public void restore() {
            this.next.prev = this;
            this.prev.next = this;
        }

    }

    Node init(int n) {
        Node head = new Node(-1);
        Node tail = new Node(-1);
        Node curNode = head;

        for (int i = 0; i < n; i++) {
            Node node = new Node(i);
            curNode.next = node;
            node.prev = curNode;
            curNode = node;
        }
        curNode.next = tail;
        tail.prev = curNode;

        return head.next;

    }


    public String solution(int n, int k, String[] cmd) {

        Stack<Node> stack = new Stack<>();
        char[] result = new char[n];
        Arrays.fill(result,'O');
        Node curNode = init(n);

        for (int i = 0; i < k; i++) {
            curNode = curNode.next;
        }

        for(String command: cmd) {
            char c = command.charAt(0);
            int count;

            switch(c) {
                case 'U':
                    count = Integer.parseInt(command.substring(2));
                    for (int i = 0; i < count; i++) {
                        curNode = curNode.prev;
                    }
                    break;
                case 'D':
                    count = Integer.parseInt(command.substring(2));
                    for (int i = 0; i < count; i++) {
                        curNode = curNode.next;
                    }
                    break;
                case 'C':
                    Node delNode = curNode;
                    result[delNode.index] = 'X';
                    if (curNode.next.index == -1) {
                        curNode = curNode.prev;
                    } else {
                        curNode = curNode.next;
                    }
                    delNode.remove();
                    stack.push(delNode);
                    break;
                case 'Z':
                    Node restoreNode = stack.pop();
                    result[restoreNode.index] = 'O';

                    restoreNode.restore();
            }

        }

        return new String(result);
    }


}



public class Main {
    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        String result = new Solution().solution(n,k,cmd);
        System.out.println("result = " + result);
    }
}
