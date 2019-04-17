/*
 * C343 Summer 2018
 * Homework 7
 * Ian Polito
 * ipolito
 */

/*
 * The Dynamic Programming solution is based on the pseudo-code found at this Wikipedia page
 * https://en.wikipedia.org/wiki/Subset_sum_problem#Pseudo-polynomial_time_dynamic_programming_solution
 */

public class SubsetSum {
	
	//method takes an array of integers and an int and determines if a subset of numbers in the
	//array equals the given total int using recursion.
	public boolean subSum(int[] set, int total) {
		if (total == 0) {
			return true;
		} else {
			for (int i : set) {
				if (total - i >= 0) {
					int[] temp = remove(set, i);
					if (subSum(temp, total-i)) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	//removes an int i from an array of ints and returns that new array
	public int[] remove(int[] set, int i) {
		int result[] = new int[set.length - 1];
		int index = 0;
		for (int k : set) {
			if (i != k) {
				result[index] = k;
				index++;
			}
		}
		return result;
	}

	//method takes an array of integers and an int and determines if a subset of numbers in the
	//array equals the given total int using dynamic programming.
	public boolean subSumDP(int [] set, int total) {
        boolean subset[][] = new boolean[total+1][set.length+1];
        for (int i = 0; i <= set.length; i++) {
            subset[0][i] = true;
        }
        for (int i = 1; i <= total; i++) {
            subset[i][0] = false;
        }
        for (int i = 1; i <= total; i++) {
            for (int j = 1; j <= set.length; j++) {
                subset[i][j] = subset[i][j-1];
                if (i >= set[j-1]) {
                	subset[i][j] = subset[i][j] || subset[i - set[j-1]][j-1];
                }
            }
        }
        return subset[total][set.length];
	}
	
	public static void main(String[] args) {
		//Test the subSum() methods
		SubsetSum tester = new SubsetSum();
		
		int[] test1 = {2, 5, 7, 9, 3, 8, 14, 1, 19, 22};
		int[] test2 = {18, 14, 12, 20, 19, 2, 3, 5, 7, 15};
		int[] test3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.println("Testing Recursive Solution");
		System.out.println(tester.subSum(test1, 23)); //should print true, ex: 14 + 9
		System.out.println(tester.subSum(test1, 10)); //should print true, ex: 2 + 5 + 3
		System.out.println(tester.subSum(test1, 140)); //should print false
		System.out.println();
		System.out.println(tester.subSum(test2, 21)); //should print true, ex: 18 + 3
		System.out.println(tester.subSum(test2, 4)); //should print false
		System.out.println();
		System.out.println(tester.subSum(test3, 23)); //should print true, ex: 10 + 9 + 1 + 3
		System.out.println(tester.subSum(test3, 57)); //should print false
		System.out.println("Testing Dynamic Programming Solution");
		
		System.out.println(tester.subSumDP(test1, 23)); //should print true, ex: 14 + 9
		System.out.println(tester.subSumDP(test1, 10)); //should print true, ex: 2 + 5 + 3
		System.out.println(tester.subSumDP(test1, 140)); //should print false
		System.out.println();
		System.out.println(tester.subSumDP(test2, 21)); //should print true, ex: 18 + 3
		System.out.println(tester.subSumDP(test2, 4)); //should print false
		System.out.println();
		System.out.println(tester.subSumDP(test3, 23)); //should print true, ex: 10 + 9 + 1 + 3
		System.out.println(tester.subSumDP(test3, 57)); //should print false
	}
}
