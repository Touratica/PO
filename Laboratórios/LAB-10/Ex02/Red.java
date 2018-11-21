public class Red extends Ticking {

    /**
     * @param light
     */
    Red(TrafficLight light) {
        super(light);
    }

    /**
     * @see TrafficLight.State#tick()
     */
    @Override
    public void tick() {
        setState(new Green(getLight()));
    }

    /**
     * @see TrafficLight.State#status()
     */
    @Override
    public String status() {
        return "Red";
    }
}