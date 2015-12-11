import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import arcatch.ArCatch;
import arcatch.rule.ArCatchException;
import arcatch.rule.ArCatchModule;
import arcatch.rule.DesignRule;

public class BankSysEHDesignTest {
	private ArCatchModule view;
	private ArCatchModule controller;
	private ArCatchModule account;
	private ArCatchModule data;

	private ArCatchException negativeAccountException;
	private ArCatchException accountExceptions;

	public BankSysEHDesignTest() {
		ArCatch.targetSystem("./build/classes");
		view = ArCatch.newModule("BankView");
		view.setMappingRegex("");

		controller = ArCatch.newModule("Controller");
		controller.setMappingRegex("");

		account = ArCatch.newModule("Account");
		account.setMappingRegex("banksys.account.[A-Za-z_$]+[a-zA-Z0-9_$]Account*");

		data = ArCatch.newModule("Data");
		data.setMappingRegex("");

		negativeAccountException = ArCatch.newException("NegativeAccountException");
		negativeAccountException.setMappingRegex("banksys.account.exception.NegativeAmountException");

		accountExceptions = ArCatch.newException("AccountExceptions");
		accountExceptions.setMappingRegex("banksys.account.exception..[A-Za-z_$]+[a-zA-Z0-9_$]Exception*");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnlyAccoutCanRaiseAccountExceptions() {
		DesignRule rule = ArCatch.newRule().only(account).canRaise(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccoutCanRaiseNegativeAmountException() {
		DesignRule rule = ArCatch.newRule().only(account).canRaise(negativeAccountException).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccoutCanSignalNegativeAmountException() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(negativeAccountException).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testAccoutCannotHandleNegativeAmountException() {
		DesignRule rule = ArCatch.newRule().module(account).cannotHandle(negativeAccountException).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccoutCanSignalNegativeAmountExceptionToController() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(negativeAccountException).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
}
