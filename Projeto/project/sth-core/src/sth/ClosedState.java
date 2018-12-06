package sth;

public class ClosedState extends Survey.State {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201812060205L;

    public ClosedState(Survey survey){
        survey.super();
    }

    /**
     * Reopens the survey.
     */
    @Override
    public void cancel() {
        setState(new OpenState(getSurvey()));
        getSurvey().notifyObservers();
    }

    /**
     * Reopens the survey.
     */
    @Override
    public void open() {
        setState(new OpenState(getSurvey()));
        getSurvey().notifyObservers();
    }

    /**
     * Does nothing, survey is already closed.
     */
    @Override
    public void close() {}

    /**
     * Finalizes the survey.
     */
    @Override
    public void finalize() {
        setState(new FinalState(getSurvey()));
        getSurvey().notifyObservers();
    }

    public String renderResults(Person p) {
        return super.renderResults(p) + " (fechado)";
    }

    @Override
    public String notifyState() {
        return "closed";
    }
}