import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import arcatch.ArCatch;
import arcatch.rule.ArCatchException;
import arcatch.rule.ArCatchModule;
import arcatch.rule.DesignRule;

public class BankSysEHDTest {
	private ArCatchModule view;
	private ArCatchModule controller;
	private ArCatchModule account;
	private ArCatchModule data;

	private ArCatchException nae;

	public BankSysEHDTest() {
		ArCatch.targetSystem("./build/classes");
		view = ArCatch.newModule("BankView");
		view.setMappingRegex("");

		controller = ArCatch.newModule("BankCTL");
		controller.setMappingRegex("");

		account = ArCatch.newModule("BanckAccount");
		account.setMappingRegex("banksys.account.[A-Za-z_$]+[a-zA-Z0-9_$]Account*");

		data = ArCatch.newModule("BanckData");
		data.setMappingRegex("");

		nae = ArCatch.newException("NAE");
		nae.setMappingRegex("banksys.account.exception.NegativeAmountException");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnlyAccoutCanRaiseNegativeAmountException() {
		DesignRule rule = ArCatch.newRule().only(account).canRaise(nae).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccoutCanSignalNegativeAmountException() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(nae).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testAccoutCannotHandleNegativeAmountException() {
		DesignRule rule = ArCatch.newRule().module(account).cannotHandle(nae).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccoutCanSignalNegativeAmountExceptionToController() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(nae).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
}
