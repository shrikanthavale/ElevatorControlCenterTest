package at.fhooe.mc.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * This class executes all the test cases
 * 
 * @author Metrics_Testing Team
 * Jan 8, 2015
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ElevatorAdapterTest.class, ElevatorManualModeUsingMockTest.class, ElevatorManualModeUsingSimulatorTest.class })
public class AllTests {

}
