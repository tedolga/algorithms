package algolist;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class FactorialSum2 {
	public double getSum(int k) {
		if (k < 2) {
			return 1;
		}
		long startTime = System.currentTimeMillis();
		double factorial = 1;
		double sum = 1;
		for (int i = 2; i <= k; i++) {
			factorial /= i;
			sum += factorial;
		}
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println(String.format("Sum2: %d",duration));
		return sum;
	}
}
