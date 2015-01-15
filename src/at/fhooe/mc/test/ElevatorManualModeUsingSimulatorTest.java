/**
 * 
 */
package at.fhooe.mc.test;

import java.rmi.Naming;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sqelevator.IElevator;
import at.fhooe.mc.controller.ElevatorAdapter;
import at.fhooe.mc.controller.ElevatorController;
import at.fhooe.mc.controller.ElevatorUpdater;
import at.fhooe.mc.controller.IElevatorControls;
import at.fhooe.mc.model.Elevator;
import at.fhooe.mc.model.ElevatorMock;

/**
 * Test case will fall back to use Mock, if simulator is closed
 * 
 * @author Metrics_Testing Team Dec 17, 2014
 * 
 */
public class ElevatorManualModeUsingSimulatorTest {

	/**
	 * Elevator Adapter
	 */
	private static IElevatorControls iElevatorControls;

	/**
	 * Elevator Controller
	 */
	private static ElevatorController elevatorController;

	/**
	 * Elevator Updater
	 */
	private static ElevatorUpdater elevatorUpdater;

	private static final String DOOR_STATUS_OPEN = "open";
	private static final String DOOR_STATUS_OPENING = "opening";
	private static final String DOOR_STATUS_CLOSED = "closed";
	private static final String DOOR_STATUS_CLOSING = "closing";
	private static final String ELEVATOR_DIRECTION_UP = "up";
	private static final String ELEVATOR_DIRECTION_DOWN = "down";
	private static final String ELEVATOR_DIRECTION_NOT_SET = "not set";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		iElevatorControls = new ElevatorAdapter();
		try {
			// get the reference from remote
			iElevatorControls.setiElevatorReference((IElevator) Naming.lookup("rmi://localhost/ElevatorSim"));
		} catch (Exception e) {
			// if not just mock it
			iElevatorControls.setiElevatorReference(new ElevatorMock());
		}
		
		//setup the controls
		elevatorController = new ElevatorController(iElevatorControls);
		elevatorUpdater = new ElevatorUpdater(iElevatorControls);
		
		// before starting the test case make sure that elevator is on first floor
		elevatorController.setTarget(1);
		
		// wait for sometime till it actually goes there
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link at.fhooe.mc.model.Elevator#getCommittedDirection(int)}.
	 */
	@Test
	public final void testElevatorManualOperation() {

		Thread thread = new Thread(elevatorUpdater);
		thread.start();
		
		// go to floor number 4 , for example
		elevatorController.setTarget(3);
		
		// give some time to elevator to actually go there
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Elevator elevator = elevatorUpdater.getElevator();
		String actualDoorStatus = "";
		String actualDirection = "";

		Assert.assertEquals("Target Floor Don't Match", 3, elevator.getPosition());
		Assert.assertEquals("Elevator Capacity is less than zero", true, elevator.getCapacity() > 0);

		switch (elevator.getDoorStatus()) {
		case 1:
			actualDoorStatus = DOOR_STATUS_OPEN;
			break;
		case 2:
			actualDoorStatus = DOOR_STATUS_CLOSED;
			break;
		case 3:
			actualDoorStatus = DOOR_STATUS_OPENING;
			break;
		case 4:
			actualDoorStatus = DOOR_STATUS_CLOSING;
			break;
		default:
			actualDoorStatus = "Default";
		}

		Assert.assertEquals("Door Status Don't Match", DOOR_STATUS_OPEN,
				actualDoorStatus);

		switch (elevator.getCurrentDirection()) {
		case 0:
			actualDirection = ELEVATOR_DIRECTION_UP;
			break;
		case 1:
			actualDirection = ELEVATOR_DIRECTION_DOWN;
			break;
		default:
			actualDirection = ELEVATOR_DIRECTION_NOT_SET;
		}

		Assert.assertEquals("Elevator Direction Status Don't match",
				ELEVATOR_DIRECTION_NOT_SET, actualDirection);
		
		// go to floor number 1 , for example
		elevatorController.setTarget(1);
		
		// give some time to elevator to actually go there
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		elevator = elevatorUpdater.getElevator();
		
		Assert.assertEquals("Target Floor Don't Match", 1, elevator.getPosition());
		Assert.assertEquals("Elevator Capacity is less than zero", true, elevator.getCapacity() > 0);

		switch (elevator.getDoorStatus()) {
		case 1:
			actualDoorStatus = DOOR_STATUS_OPEN;
			break;
		case 2:
			actualDoorStatus = DOOR_STATUS_CLOSED;
			break;
		case 3:
			actualDoorStatus = DOOR_STATUS_OPENING;
			break;
		case 4:
			actualDoorStatus = DOOR_STATUS_CLOSING;
			break;
		default:
			actualDoorStatus = "Default";
		}

		Assert.assertEquals("Door Status Don't Match", DOOR_STATUS_OPEN,
				actualDoorStatus);

		switch (elevator.getCurrentDirection()) {
		case 0:
			actualDirection = ELEVATOR_DIRECTION_UP;
			break;
		case 1:
			actualDirection = ELEVATOR_DIRECTION_DOWN;
			break;
		default:
			actualDirection = ELEVATOR_DIRECTION_NOT_SET;
		}

		Assert.assertEquals("Elevator Direction Status Don't match",
				ELEVATOR_DIRECTION_NOT_SET, actualDirection);

	}

}
