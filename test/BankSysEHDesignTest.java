import org.junit.Assert;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import arcatch.ArCatch;
import arcatch.rule.ArCatchException;
import arcatch.rule.ArCatchModule;
import arcatch.rule.DesignRule;

public class BankSysEHDesignTest {
	private static ArCatchModule view = ArCatch.newModule("VIEW");
	private static ArCatchModule controller = ArCatch.newModule("CTL");
	private static ArCatchModule account = ArCatch.newModule("ACC");
	private static ArCatchModule persistence = ArCatch.newModule("PER");
	private static ArCatchException accountExceptions = ArCatch.newException("ACCEx");
	private static ArCatchException persistenceExceptions = ArCatch.newException("PEREx");
	private static ArCatchException controllerExceptions = ArCatch.newException("CTLEx");
	

	public BankSysEHDesignTest() {
		
		ArCatch.targetSystem("./build/jar/BankSys.jar");
		
	}
	
	@BeforeClass
	public static void oneTimeSetUp() {

		view.setMappingRegex("banksys.atm.ATM24H");

		controller.setMappingRegex("banksys.control.[A-Za-z_$]+[a-zA-Z0-9_$]Controller*");

		account.setMappingRegex("banksys.account.[A-Za-z_$]+[a-zA-Z0-9_$]*Account");

		persistence.setMappingRegex("banksys.persistence.[A-Za-z_$]+[a-zA-Z0-9_$]*");

		accountExceptions.setMappingRegex("banksys.account.exception.[A-Za-z_$]+[a-zA-Z0-9_$]*Exception");

		persistenceExceptions.setMappingRegex("banksys.persistence.exception.[A-Za-z_$]+[a-zA-Z0-9_$]*Exception");

		controllerExceptions.setMappingRegex("banksys.control.exception.[A-Za-z_$]+[a-zA-Z0-9_$]*Exception");
		
	}
	
	@AfterClass
	public static void oneTimeTearDown() {

		ArCatch.printReport();

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
	
	@Test
	public void testOnlyAccoutCanRaiseAccountExceptions() {
		DesignRule rule = ArCatch.newRule().only(account).canRaise(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}

	
	@Test
	public void testOnlyPersistenceCanSignalPersistenceExceptions() {
		DesignRule rule = ArCatch.newRule().only(persistence).canSignal(persistenceExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyControllerCanSignalControlExceptions() {
		DesignRule rule = ArCatch.newRule().only(controller).canSignal(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	
	@Test
	public void testOnlyPersistenceCanRaisePersistenceExceptions() {
		DesignRule rule = ArCatch.newRule().only(persistence).canRaise(persistenceExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyControllerCanRaiseControlExceptions() {
		DesignRule rule = ArCatch.newRule().only(controller).canRaise(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	
	@Test
	public void testViewCanHandleOnlyControlExceptions() {
		DesignRule rule = ArCatch.newRule().module(view).canHandleOnly(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCanSignalOnlyControlExceptionsToView() {
		DesignRule rule = ArCatch.newRule().module(controller).canSignalOnly(controllerExceptions).to(view).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testAccountCanSignalOnlyAccountExceptionsToController() {
		DesignRule rule = ArCatch.newRule().module(account).canSignalOnly(accountExceptions).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testPersistenceCanSignalOnlyPersistenceExceptionsToAccount() {
		DesignRule rule = ArCatch.newRule().module(persistence).canSignalOnly(persistenceExceptions).to(account).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testPersistenceCanSignalOnlyPersistenceExceptionsToController() {
		DesignRule rule = ArCatch.newRule().module(persistence).canSignalOnly(persistenceExceptions).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testPersistenceCannotHandlePersistenceExceptions() {
		DesignRule rule = ArCatch.newRule().module(persistence).cannotHandle(persistenceExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCannotHandleControlExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).cannotHandle(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testPersistenceCanHandleOnlyAccountExceptions() {
		DesignRule rule = ArCatch.newRule().module(persistence).canHandleOnly(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyAccountCanSignalAccountExceptionsToPersistence() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(accountExceptions).to(persistence).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyPersistenceCanSignalPersistenceExceptionsToController() {
		DesignRule rule = ArCatch.newRule().only(persistence).canSignal(persistenceExceptions).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyAccountCanSignalAccountExceptionsToController() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(accountExceptions).to(controller).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyPersistenceCanSignalPersistenceExceptionsToAccount() {
		DesignRule rule = ArCatch.newRule().only(persistence).canSignal(persistenceExceptions).to(account).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyControllerCanSignalControlExceptionsToPersistence() {
		DesignRule rule = ArCatch.newRule().only(controller).canSignal(controllerExceptions).to(persistence).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyControllerCanSignalControlExceptionsToAccount() {
		DesignRule rule = ArCatch.newRule().only(controller).canSignal(controllerExceptions).to(account).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyControllerCanSignalControlExceptionsToView() {
		DesignRule rule = ArCatch.newRule().only(controller).canSignal(controllerExceptions).to(view).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyPersistenceCanSignalPersistenceExceptionsToView() {
		DesignRule rule = ArCatch.newRule().only(persistence).canSignal(persistenceExceptions).to(view).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyAccountCanSignalAccountExceptionsToView() {
		DesignRule rule = ArCatch.newRule().only(account).canSignal(accountExceptions).to(view).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerMustSignalControlExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).mustSignal(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerMustHandlePersistenceExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).mustHandle(persistenceExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testOnlyControllerCanHandlePersistenceExceptions() {
		DesignRule rule = ArCatch.newRule().only(controller).canHandle(persistenceExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCanRaiseControlExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).canRaiseOnly(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCanSignalOnlyControlExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).canSignalOnly(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCannotRaiseAccountExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).cannotRaise(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCannotSignalAccountExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).cannotSignal(accountExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerCannotSignalControlExceptionsToAccount() {
		DesignRule rule = ArCatch.newRule().module(controller).cannotSignal(controllerExceptions).to(account).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
	@Test
	public void testControllerMustRaiseControlExceptions() {
		DesignRule rule = ArCatch.newRule().module(controller).mustRaise(controllerExceptions).build();
		Assert.assertTrue(ArCatch.check(rule));
	}
	
}
