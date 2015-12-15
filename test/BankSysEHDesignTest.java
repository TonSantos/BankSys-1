import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import arcatch.ArCatch;
import arcatch.rule.ArCatchException;
import arcatch.rule.ArCatchModule;
import arcatch.rule.DesignRule;

public class BankSysEHDesignTest {
	private ArCatchModule view = ArCatch.newModule("VIEW");
	private ArCatchModule controller = ArCatch.newModule("CTL");
	private ArCatchModule account = ArCatch.newModule("ACC");
	private ArCatchModule persistence = ArCatch.newModule("PER");
	private ArCatchException accountExceptions = ArCatch.newException("ACCEx");
	private ArCatchException persistenceExceptions = ArCatch.newException("PEREx");
	private ArCatchException controllerExceptions = ArCatch.newException("CTLEx");

	public BankSysEHDesignTest() {
		ArCatch.targetSystem("./build/jar/BankSys.jar");

		view.setMappingRegex("banksys.atm.ATM24H");

		controller.setMappingRegex("banksys.control.[A-Za-z_$]+[a-zA-Z0-9_$]Controller*");

		account.setMappingRegex("banksys.account.[A-Za-z_$]+[a-zA-Z0-9_$]*Account");

		persistence.setMappingRegex("banksys.persistence.[A-Za-z_$]+[a-zA-Z0-9_$]*");

		accountExceptions.setMappingRegex("banksys.account.exception.[A-Za-z_$]+[a-zA-Z0-9_$]*Exception");

		persistenceExceptions.setMappingRegex("banksys.persistence.exception.[A-Za-z_$]+[a-zA-Z0-9_$]*Exception");

		controllerExceptions.setMappingRegex("banksys.control.exception.[A-Za-z_$]+[a-zA-Z0-9_$]*Exception");

	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOnlyAccountCanRaiseAccountExceptions() {
		DesignRule rule = ArCatch.newRule().only(account).canRaise(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccountCanSignalAccountExceptions() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	@Test
	public void testOnlyAccoutCanSignalAccountExceptionsToController() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(accountExceptions).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
}
