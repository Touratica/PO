public class Panic extends TrafficLight.State {

    /**
     * @param light
     */
    Panic(TrafficLight light) {
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
        // do nothing: already in panic mode
    }

    /**
     * @see TrafficLight.State#tick()
     */
    @Override
    public void tick() {
        // do nothing: ignore ticks in panic mode
    }

    /**
     * @see TrafficLight.State#status()
     */
    @Override
    public String status() {
        return "Panic";
    }
}