package swea.d5.d5_3135;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        UserSolution dictManager = new UserSolution();

        for (int TestCase = in.nextInt(), tc = 1; tc <= TestCase; tc = tc + 1) {

            int Query_N = in.nextInt();

            out.print("#" + tc);

            dictManager.init();

            for (int i = 1; i <= Query_N; i++) {
                int type = in.nextInt();

                String buf = in.next();
                if (type == 1) {
                    dictManager.insert(buf.length(), buf);
                }
                else {
                    int answer = dictManager.query(buf.length(), buf);
                    out.print(" " + answer);
                }
            }
            out.println("");
        }
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

class UserSolution {

    Node root;

    static class Node {
        char ch;
        Node[] children;
        int wordCount; // 이 노드를 포함한 하위 트리의 단어 수

        public Node() {
            this.children = new Node[26];
            this.wordCount = 0;
        }

        public Node(char ch) {
            this.ch = ch;
            this.children = new Node[26];
            this.wordCount = 0;
        }
    }

    void init() {
        root = new Node();
    }

    void insert(int bufferSize, String buf) {
        Node cur = root;

        for (int i = 0; i < bufferSize; i++) {
            int index = buf.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Node(buf.charAt(i));
            }
            cur = cur.children[index];
            cur.wordCount++; // 이 노드를 지나가는 모든 단어를 카운트
        }
    }

    int query(int bufferSize, String buf) {
        Node cur = root;

        for (int i = 0; i < bufferSize; i++) {
            int index = buf.charAt(i) - 'a';
            if (cur.children[index] == null) {
                return 0;
            }
            cur = cur.children[index];
        }
        return cur.wordCount;
    }
}

