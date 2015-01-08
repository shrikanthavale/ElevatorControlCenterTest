/**
 * 
 */
package at.fhooe.mc.test;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.fhooe.mc.controller.ElevatorAdapter;
import at.fhooe.mc.controller.ElevatorController;
import at.fhooe.mc.controller.ElevatorUpdater;
import at.fhooe.mc.controller.IElevatorControls;
import at.fhooe.mc.model.Elevator;
import at.fhooe.mc.model.ElevatorMock;

/**
 * @author Metrics_Testing Team Dec 17, 2014
 * 
 */
public class ElevatorManualModeUsingMockTest {

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
		iElevatorControls.setiElevatorReference(new ElevatorMock());
		elevatorController = new ElevatorController(iElevatorControls);
		elevatorUpdater = new ElevatorUpdater(iElevatorControls);

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

		// go to floor number 4 , for example
		elevatorController.setTarget(4);
		
		Thread thread = new Thread(elevatorUpdater);
		thread.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Elevator elevator = elevatorUpdater.getElevator();
		String actualDoorStatus = "";
		String actualDirection = "";

		Assert.assertEquals("Target Floor Don't Match", 4, elevator.getPosition());

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
				ELEVATOR_DIRECTION_UP, actualDirection);
		
		// go to floor number 4 , for example
		elevatorController.setTarget(1);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		elevator = elevatorUpdater.getElevator();
		
		Assert.assertEquals("Target Floor Don't Match", 1, elevator.getPosition());

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
				ELEVATOR_DIRECTION_DOWN, actualDirection);

	}

	@Test
	public final void testCommittedDirectionException(){
		
		boolean exceptionOcurred = false;
		
		// currently mock is supporting only 4 elevators, numbered from 1-4, so
		try {
			iElevatorControls.setCommittedDirection(5, IElevatorControls.ELEVATOR_DIRECTION_UP);
		} catch (RemoteException e) {
			exceptionOcurred = true;
		}
		
		Assert.assertTrue(exceptionOcurred);
		
		exceptionOcurred = false;
		
		// currently mock is supporting only 4 elevators, numbered from 1-4, so
		try {
			iElevatorControls.getCommittedDirection(5);
		} catch (RemoteException e) {
			exceptionOcurred = true;
		}

		Assert.assertTrue(exceptionOcurred);
	}
	
}
