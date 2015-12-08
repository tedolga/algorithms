package algolist;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class FactorialSum {

	public double getSum(int k) {
		long startTime = System.currentTimeMillis();
		long factorial = 1;
		double sum = 0;
		for (int i = 1; i <= k; i++) {
			factorial *= i;
			sum += 1.0 / factorial;
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println(String.format("Sum1: %d",duration));
		return sum;
	}
}
