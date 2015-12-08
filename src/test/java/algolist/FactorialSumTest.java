package algolist;

import org.junit.Assert;
import org.junit.Test;

public class FactorialSumTest {
	private FactorialSum factorialSum = new FactorialSum();
	private FactorialSum2 factorialSum2 = new FactorialSum2();

	@Test
	public void testGetSum() throws Exception {
		Assert.assertEquals(1, factorialSum.getSum(1), 0.01);
		Assert.assertEquals(1, factorialSum.getSum(1), 0.01);
		Assert.assertEquals(1.7083, factorialSum.getSum(4), 0.0001);
	}

	@Test
	public void testGetSum2() throws Exception {
		Assert.assertEquals(1, factorialSum2.getSum(1), 0.01);
		Assert.assertEquals(1, factorialSum2.getSum(1), 0.01);
		Assert.assertEquals(1.7083, factorialSum2.getSum(4), 0.0001);
	}

	@Test
	public void testPerformance() throws Exception {
		factorialSum.getSum(1000000000);
		factorialSum2.getSum(1000000000);
	}
}