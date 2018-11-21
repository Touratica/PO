public class Blinking extends TrafficLight.State {

	/**
	 * @param light
	 */
	Blinking(TrafficLight light) {
		light.super();
	}

	/**
	 * Return to "ticking" (normal) mode.
	 *
	 * @see TrafficLight.State#on()
	 */
	@Override
	public void on() {
		setState(new Red(getLight()));
	}

	/**
	 * @see TrafficLight.State#off()
	 */
	@Override
	public void off() {
		// do nothing: already off
	}

	/**
	 * @see TrafficLight.State#panic()
	 */
	@Override
	public void panic() {
		setState(new Panic(getLight()));
	}

	/**
	 * @see TrafficLight.State#tick()
	 */
	@Override
	public void tick() {
		// do nothing: ignore ticks
	}

	/**
	 * @see TrafficLight.State#status()
	 */
	@Override
	public String status() {
		return "Blinking";
	}
}