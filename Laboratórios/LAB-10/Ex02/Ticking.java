public abstract class Ticking extends TrafficLight.State {

	/**
	 * @param light
	 */
	Ticking(TrafficLight light) {
		light.super();
	}

	/**
	 * All colors switch to blinking when "off" is pressed.
	 *
	 * @see TrafficLight.State#off()
	 */
	@Override
	public void off() {
		setState(new Blinking(getLight()));
	}

	/**
	 * All colors switch to panic when the button is pressed.
	 *
	 * @see TrafficLight.State#panic()
	 */
	@Override
	public void panic() {
		setState(new Panic(getLight()));
	}
}