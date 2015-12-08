package algolist;

/**
 * @author O. Tedikova
 * @version 1.0
 */
public class HappyTicket {
	public static final int MAX_NUMERIC = 9;
	public static final int MIN_NUMERIC = 0;

	public long getHappyTicketCount(int N) {
		long count = 0;
		for (int i = 0; i <= N * MAX_NUMERIC / 2; i++) {
			long combinationCount = getCombinationCount(N, i);
			count += combinationCount * combinationCount;
		}
		return count * 2;
	}

	public long getHappyTicketCount2(int N) {
		return getCombinationCount(2 * N, 9 * N);
	}

	public long getCombinationCount(int N, long sum) {
		int maxNumeric = sum < MAX_NUMERIC ? (int) sum : 9;
		int minNumeric = sum - (N - 1) * MAX_NUMERIC > MIN_NUMERIC ? (int) (sum - (N - 1) * MAX_NUMERIC) : MIN_NUMERIC;
		long combinationCount = 0;
		for (int i = minNumeric; i <= maxNumeric; i++) {
			if (sum - i == 0) {
				combinationCount += 1;
			} else {
				combinationCount += getCombinationCount(N - 1, sum - i);
			}
		}
		return combinationCount;
	}
}
