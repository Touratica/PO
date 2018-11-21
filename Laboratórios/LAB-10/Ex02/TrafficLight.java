public class TrafficLight {

    /** The traffic light's state. */
    private State _state;

    /**
     * Abstract state class.
     *
     * This class is internal so that it has access to the traffic light's
     * internal state. Actual states are subclasses which must use this class'
     * protected interface.
     */
    public abstract class State {

        /** Tick behavior. */
        public abstract void tick();

        /** Panic behavior. */
        public abstract void panic();

        /** "On" behavior. */
        public void on() { /* ignore by default */ }

        /** "Off" behavior. */
        public abstract void off();

        /** @return traffic light status. */
        public abstract String status();

        /**
         * Define the traffic light's new state.
         *
         * @param newState
         *            the new state.
         */
        protected void setState(State newState) {
            _state = newState;
        }

        /**
         * This method is needed so that new states can be created.
         *
         * @return the traffic light.
         */
        protected TrafficLight getLight() {
            return TrafficLight.this;
        }
    }

    /** Initialize traffic light. Starts blinking. */
    public TrafficLight() {
        _state = new Blinking(this);
    }

    /** Process on button press. */
    public void on() {
        System.out.print("[" + status() + "]");
        _state.on();
        System.out.println(" --(on)-> [" + status() + "]");
    }

    /** Process off button press. */
    public void off() {
        System.out.print("[" + status() + "]");
        _state.off();
        System.out.println(" --(off)-> [" + status() + "]");
    }

    /** Process panic button press. */
    public void panic() {
        System.out.print("[" + status() + "] --(panic)-> ");
        _state.panic();
        System.out.println("[" + status() + "]");
    }

    /** Process tick: switch light color. */
    public void tick() {
        System.out.print("[" + status() + "] --(tick)-> ");
        _state.tick();
        System.out.println("[" + status() + "]");
    }

    /** @return traffic light status. */
    public String status() {
        return _state.status();
    }
}