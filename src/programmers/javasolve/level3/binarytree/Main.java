package programmers.javasolve.level3.binarytree;


class Solution {
    static int treeIndex;

    static class Tree {
        boolean dummy;
        Tree leftChild;
        Tree rightChild;
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int count = 0;

        for (long number : numbers) {
            treeIndex = 0;

            String binary = binaryNumber(number);
            int element = binary.length();
            int height = (int) (Math.log10(element) / Math.log10(2)) + 1;

            Tree tree = new Tree();
            createTree(tree, height, 1);
            inorderReset(tree, binary);

            inorderPrint(tree);


            StringBuilder builder = new StringBuilder();
            inorderSearch(tree, builder);

            if (builder.toString().equals(binary)) {
                answer[count] = 1;
            } else {
                answer[count] = 0;
            }
            count++;
        }

        return answer;
    }

    public void inorderPrint(Tree tree) {
        if (tree == null) {
            return;
        }

        inorderPrint(tree.leftChild);
        inorderPrint(tree.rightChild);
    }

    public void inorderSearch(Tree tree, StringBuilder builder) {

        if (tree == null) {
            return;
        }

        if (tree.dummy) {
            if (tree.leftChild != null && tree.leftChild.dummy && tree.rightChild.dummy) {
                inorderSearch(tree.leftChild, builder);
                builder.append(0);
                inorderSearch(tree.rightChild, builder);
                return;
            }
            builder.append(0);
            return;
        }

        inorderSearch(tree.leftChild, builder);
        builder.append(1);
        inorderSearch(tree.rightChild, builder);

    }


    public void inorderReset(Tree tree, String binary) {
        if (tree == null) {
            return;
        }
        inorderReset(tree.leftChild, binary);
        char c = binary.charAt(treeIndex);

        tree.dummy = c == '0';
        treeIndex += 1;
        inorderReset(tree.rightChild, binary);
    }

    public void createTree(Tree tree, int height, int index) {
        if (index == height) {
            return;
        }
        tree.leftChild = new Tree();
        tree.rightChild = new Tree();
        createTree(tree.leftChild, height, index + 1);
        createTree(tree.rightChild, height, index + 1);
    }

    public String binaryNumber(long number) {

        StringBuilder result = new StringBuilder();

        while (number != 0) {
            int divide = (int) (number % 2);
            result.append(divide);
            number /= 2;
        }

        int length = result.length();
        int size = 0;

        if (length == 1) {
            size = 1;
        } else if (length <= 3) {
            size = 3;
        } else if (length <= 7) {
            size = 7;
        } else if (length <= 15) {
            size = 15;
        } else if (length <= 31) {
            size = 31;
        } else if (length <= 63) {
            size = 63;
        }

        result.append("0".repeat(Math.max(0, size - length)));

        result.reverse();

        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        long[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 128, 129, 16512, 2147516555L};
        int[] answer = new Solution().solution(numbers);

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }
}
