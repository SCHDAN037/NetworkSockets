import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
* Test class agregator.
*
* @author Daniel Schwartz
* @version 19/06/2016
*
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestHelloWorld.class, TestMessage.class
	/* <TestExample.class>, <TestExample2.class>, ...; */
	
})

public class TestSuite { /* Holder for above annotations. */}
