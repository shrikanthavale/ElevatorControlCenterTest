package at.fhooe.mc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sqelevator.IElevator;
import at.fhooe.mc.controller.ElevatorAdapter;
import at.fhooe.mc.model.ElevatorMock;

public class ElevatorAdapterTest {
    private static ElevatorAdapter m_adapter = null;
    private ElevatorMock m_mock = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        m_adapter = new ElevatorAdapter();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        m_adapter = null;
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        m_mock = new ElevatorMock();
        m_adapter.setiElevatorReference(m_mock);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        m_mock = null;
    }

    @Test
    public void testGetCommittedDirection(){
        try {
            assertEquals(IElevator.ELEVATOR_DIRECTION_UP, m_adapter.getCommittedDirection(1));
            assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, m_adapter.getCommittedDirection(2));
            assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, m_adapter.getCommittedDirection(3));
            assertEquals(IElevator.ELEVATOR_DIRECTION_DOWN, m_adapter.getCommittedDirection(4));
        } catch (RemoteException e) {
            fail();
        }

        try {
        	m_adapter.getCommittedDirection(5);
        	fail();
        } catch (RemoteException e){
        	assertEquals("Not existing elevator number.",e.getMessage());
        }
    }

    @Test
    public void testGetElevatorDoorStatus(){
    	try{
    		assertEquals(IElevator.ELEVATOR_DOORS_CLOSED, m_adapter.getElevatorDoorStatus(1));
    		assertEquals(IElevator.ELEVATOR_DOORS_OPENING, m_adapter.getElevatorDoorStatus(2));
    		assertEquals(IElevator.ELEVATOR_DOORS_OPEN, m_adapter.getElevatorDoorStatus(3));
    		assertEquals(IElevator.ELEVATOR_DOORS_CLOSING, m_adapter.getElevatorDoorStatus(4));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getElevatorDoorStatus(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    }

    @Test
    public void testGetElevatorFloor(){
    	try{
    		assertEquals(1, m_adapter.getElevatorFloor(1));
    		assertEquals(1, m_adapter.getElevatorFloor(2));
    		assertEquals(1, m_adapter.getElevatorFloor(3));
    		assertEquals(1, m_adapter.getElevatorFloor(4));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getElevatorFloor(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    }

    @Test
    public void testGetElevatorSpeed(){
    	try{
    		assertEquals(30, m_adapter.getElevatorSpeed(1));
    		assertEquals(0, m_adapter.getElevatorSpeed(2));
    		assertEquals(10, m_adapter.getElevatorSpeed(3));
    		assertEquals(50, m_adapter.getElevatorSpeed(4));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getElevatorSpeed(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    }

    @Test
    public void testGetElevatorWeight(){
    	try{
    		assertEquals(500, m_adapter.getElevatorWeight(1));
    		assertEquals(550, m_adapter.getElevatorWeight(2));
    		assertEquals(800, m_adapter.getElevatorWeight(3));
    		assertEquals(720, m_adapter.getElevatorWeight(4));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getElevatorWeight(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    }

    @Test
    public void testGetElevatorCapacity(){
    	try{
    		assertEquals(0, m_adapter.getElevatorCapacity(1));
    		assertEquals(5, m_adapter.getElevatorCapacity(2));
    		assertEquals(7, m_adapter.getElevatorCapacity(3));
    		assertEquals(10, m_adapter.getElevatorCapacity(4));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getElevatorCapacity(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    }

    @Test
    public void testGetFloorNum(){
    	try{
    		assertEquals(4, m_adapter.getFloorNum());
    	} catch (RemoteException e){
    		fail();
    	}
    }

    @Test
    public void testSetCommittedDirection(){
    	try{
    		assertEquals(IElevator.ELEVATOR_DIRECTION_UP, m_adapter.getCommittedDirection(1));
    		assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, m_adapter.getCommittedDirection(2));
    		m_adapter.setCommittedDirection(1, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
    		assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, m_adapter.getCommittedDirection(1));
    		assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, m_adapter.getCommittedDirection(2));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.setCommittedDirection(5, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.", e.getMessage());
    	}
    	try{
    		m_adapter.setCommittedDirection(1, 10);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing direction.",e.getMessage());
    	}
    }

    @Test
    public void testGetTarget(){
    	try{
    		assertEquals(1,m_adapter.getTarget(1));
    		assertEquals(4,m_adapter.getTarget(2));
    		assertEquals(3,m_adapter.getTarget(3));
    		assertEquals(2,m_adapter.getTarget(4));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getTarget(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.", e.getMessage());
    	}
    }

    @Test
    public void testSetTarget(){
    	try{
    		assertEquals(1,m_adapter.getTarget(1));
    		assertEquals(4,m_adapter.getTarget(2));
    		m_adapter.setTarget(1, 4);
    		assertEquals(4,m_adapter.getTarget(1));
    		assertEquals(4,m_adapter.getTarget(2));
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.setTarget(5, 1);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    	try{
    		m_adapter.setTarget(1, 10);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing target.",e.getMessage());
    	}
    }

    @Test
    public void testGetPressedButtonsElevator(){
    	boolean test[];

    	try{
    		test = m_adapter.getPressedButtonsElevator(1);
    		assertEquals(4,test.length);
    		assertTrue(test[0]);
    		assertFalse(test[1]);
    		assertTrue(test[2]);
    		assertFalse(test[3]);

    		test = m_adapter.getPressedButtonsElevator(2);
    		assertEquals(4,test.length);
    		assertTrue(test[0]);
    		assertTrue(test[1]);
    		assertTrue(test[2]);
    		assertTrue(test[3]);

    		test = m_adapter.getPressedButtonsElevator(3);
    		assertEquals(4,test.length);
    		assertFalse(test[0]);
    		assertFalse(test[1]);
    		assertFalse(test[2]);
    		assertFalse(test[3]);

    		test = m_adapter.getPressedButtonsElevator(4);
    		assertEquals(4,test.length);
    		assertFalse(test[0]);
    		assertTrue(test[1]);
    		assertTrue(test[2]);
    		assertFalse(test[3]);
    	} catch (RemoteException e){
    		fail();
    	}
    	try{
    		m_adapter.getPressedButtonsElevator(5);
    		fail();
    	} catch (RemoteException e){
    		assertEquals("Not existing elevator number.",e.getMessage());
    	}
    }

    @Test
    public void testGetPressedButtonsFloorDown(){
    	boolean test[];

    	try{
    		test = m_adapter.getPressedButtonsFloorDown();
    		assertEquals(4,test.length);
    		assertFalse(test[0]);
    		assertFalse(test[1]);
    		assertTrue(test[2]);
    		assertFalse(test[3]);
    	} catch(RemoteException e){
    		fail();
    	}
    }

    @Test
    public void testGetPressedButtonsFloorUp(){
    	boolean test[];

    	try{
    		test = m_adapter.getPressedButtonsFloorUp();
    		assertEquals(4,test.length);
    		assertTrue(test[0]);
    		assertFalse(test[1]);
    		assertTrue(test[2]);
    		assertFalse(test[3]);
    	} catch(RemoteException e){
    		fail();
    	}
    }

    @Test
    public void testGetiElevatorReference(){
    	assertEquals(m_mock, m_adapter.getiElevatorReference());
    }

    @Test
    public void testSetiElevatorReference(){
    	assertEquals(m_mock, m_adapter.getiElevatorReference());
    	ElevatorMock test = new ElevatorMock();
    	m_adapter.setiElevatorReference(test);
    	assertEquals(test, m_adapter.getiElevatorReference());
    	assertNotEquals(m_mock, m_adapter.getiElevatorReference());

    	try{
    		m_adapter.setiElevatorReference(null);
    		fail();
    	} catch(NullPointerException e){
    		assertEquals("Null reference.", e.getMessage());
    	}
    }
}
