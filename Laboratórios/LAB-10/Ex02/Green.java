public class Green extends Ticking {

    /**
     * @param light
     */
    Green(TrafficLight light) {
        super(light);
    }

    /**
     * @see TrafficLight.State#tick()
     */
    @Override
    public void tick() {
        setState(new Yellow(getLight()));
    }

    /**
     * @see TrafficLight.State#status()
     */
    @Override
    public String status() {
        return "Green";
    }
}