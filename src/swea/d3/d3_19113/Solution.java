package swea.d3.d3_19113;

import java.util.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++)
        {

            System.out.print("#" + test_case);

            int count = sc.nextInt();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < count * 2; i++) {
                list.add(sc.nextInt());
            }

            while (count != 0) {
                int start = 0;
                int product = list.get(start);

                for (int i = 0; i < list.size(); i++) {
                    int num = list.get(i);
                    if (num / 4 * 3 == product){
                        list.remove(i);
                        list.remove(start);
                        System.out.print(" " + product);
                        count--;
                        break;
                    }
                }

            }
            System.out.println();
        }
    }
}
