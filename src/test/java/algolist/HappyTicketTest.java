package algolist;

import org.junit.Assert;
import org.junit.Test;

public class HappyTicketTest {
	private HappyTicket happyTicket=new HappyTicket();

	@Test
	public void testCombinationCount() throws Exception {
		Assert.assertEquals(0, happyTicket.getCombinationCount(1, 10));
		Assert.assertEquals(1, happyTicket.getCombinationCount(1, 8));
		Assert.assertEquals(9, happyTicket.getCombinationCount(2, 8));
		Assert.assertEquals(3, happyTicket.getCombinationCount(3, 1));
		Assert.assertEquals(6, happyTicket.getCombinationCount(3, 2));
	}

	@Test
	public void testHappyTicketCount() throws Exception {
		Assert.assertEquals(55252, happyTicket.getHappyTicketCount(3));
		Assert.assertEquals(55252, happyTicket.getHappyTicketCount2(3));
	}
}