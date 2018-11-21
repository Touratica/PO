public class Yellow extends Ticking {

    /**
     * @param light
     */
    Yellow(TrafficLight light) {
        super(light);
    }

    /**
     * @see TrafficLight.State#tick()
     */
    @Override
    public void tick() {
        setState(new Red(getLight()));
    }

    /**
     * @see TrafficLight.State#status()
     */
    @Override
    public String status() {
        return "Yellow";
    }
}