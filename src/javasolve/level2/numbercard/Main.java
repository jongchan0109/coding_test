import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
	public int solution(int[] arrayA, int[] arrayB) {

		int gcdA = totalGcd(arrayA);
		int gcdB = totalGcd(arrayB);

		List<Integer> listA = divisor(gcdA);
		List<Integer> listB = divisor(gcdB);

		int resultA = 0;
		int resultB = 0;

        for(int divisor : listA) {
            boolean flag = true;
            for(int numB : arrayB) {
                if(numB % divisor == 0) {
                    flag = false;
                    break;
                }
            }
                
            if(flag) {
                resultA = divisor;
                break;
            }
        }
        
        for(int divisor : listB) {
            boolean flag = true;
            for(int numA : arrayA) {
                if(numA % divisor == 0) {
                    flag = false;
                    break;
                }
            }
                
            if(flag) {
                resultB = divisor;
                break;
            }
        }
		return Math.max(resultA,resultB);
	}

	public int gcd(int n, int r) {
		int m = n % r;
		if(m == 0) {
			return r;
		}
		return gcd(r, m);
	}

	public int totalGcd(int[] array) {

		int total = array[0];

		for(int num : array) {
			total = gcd(total, num);
		}
		return total;
	}

	public List<Integer> divisor(int n) {
		int sqrt = (int)Math.sqrt(n);

		List<Integer> list = new ArrayList<>();

		for(int i = 1 ; i<= sqrt ; i++) {
			if(n % i == 0 ){
				list.add(i);
				if(i != n / i) {
					list.add(n / i);
				}
			}
		}
		list.sort(Comparator.reverseOrder());

		return list;
	}
}
