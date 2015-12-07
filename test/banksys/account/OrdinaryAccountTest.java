package banksys.account;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class OrdinaryAccountTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDebit() {
		OrdinaryAccount account = new OrdinaryAccount("1234");
		try {
			account.credit(200);
			account.debit(100);
			Assert.assertEquals(100, account.getBalance(), 0);
		} catch (NegativeAmountException e) {
		} catch (InsufficientFundsException e) {
		}
	}

	@Test
	public void testCredit() {
		OrdinaryAccount account = new OrdinaryAccount("1234");
		try {
			account.credit(200);
			Assert.assertEquals(200, account.getBalance(), 0);
		} catch (NegativeAmountException e) {
		}
	}

	@Test
	public void testGetNumber() {
		OrdinaryAccount account = new OrdinaryAccount("1234");
		Assert.assertEquals("1234", account.getNumber());
	}

	@Test
	public void testGetBalance() {
		OrdinaryAccount account = new OrdinaryAccount("1234");
		Assert.assertEquals(0, account.getBalance(), 0);
	}

}
