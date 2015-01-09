package at.fhooe.mc.test;

import com.windowtester.runtime.IUIContext;
import com.windowtester.runtime.swing.UITestCaseSwing;
import com.windowtester.runtime.swing.locator.NamedWidgetLocator;

public class ElevatorControllerGUITest extends UITestCaseSwing {

	/**
	 * Create an Instance
	 */
	public ElevatorControllerGUITest() {
		super(at.fhooe.mc.view.ElevatorControllerGUI.class);
	}

	/**
	 * Main test method.
	 */
	@SuppressWarnings("deprecation")
	public void testElevatorControllerGUI() throws Exception {
		IUIContext ui = getUI();
		ui.assertThat(new NamedWidgetLocator("manual").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("manual").isEnabled(true));
		ui.assertThat(new NamedWidgetLocator("automatic").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("automatic").isEnabled(true));
		
		// few assertions of UI
		ui.assertThat(new NamedWidgetLocator("listInElFloor4").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listInElFloor4").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listInElFloor3").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listInElFloor3").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listInElFloor2").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listInElFloor2").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listInElFloor1").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listInElFloor1").isEnabled(false));
		
		
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp4").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp4").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp3").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp3").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp2").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp2").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp1").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorUp1").isEnabled(false));
		
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown4").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown4").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown3").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown3").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown2").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown2").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown1").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listOnFloorDown1").isEnabled(false));
		
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor4").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor4").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor3").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor3").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor2").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor2").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor1").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listCurrentFloor1").isEnabled(false));
		
		//initially manual mode, target buttons are enabled
		ui.assertThat(new NamedWidgetLocator("listTargetFloor4").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor4").isEnabled(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor3").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor3").isEnabled(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor2").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor2").isEnabled(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor1").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor1").isEnabled(true));
		
		
		ui.click(new NamedWidgetLocator("listTargetFloor4"));
		// wait till elevator goes
		ui.pause(10000);
		
		ui.click(new NamedWidgetLocator("listTargetFloor1"));
		// wait till elevator goes
		ui.pause(10000);

		ui.click(new NamedWidgetLocator("automatic"));
		// wait for automatic mode to operate for sometime
		ui.pause(10000);
		//then automatic mode, target buttons are enabled
		ui.assertThat(new NamedWidgetLocator("listTargetFloor4").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor4").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor3").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor3").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor2").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor2").isEnabled(false));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor1").isVisible(true));
		ui.assertThat(new NamedWidgetLocator("listTargetFloor1").isEnabled(false));

		ui.click(new NamedWidgetLocator("manual"));
		
		ui.click(new NamedWidgetLocator("listTargetFloor4"));
		// wait till elevator goes
		ui.pause(10000);
		ui.click(new NamedWidgetLocator("listTargetFloor1"));
		
		// wait till elevator goes
		ui.pause(10000);
	}

}