package sth;

public class ClosedState extends Survey.State{
    public ClosedState(Survey survey){
        survey.super();
    }

    /**
     * Reopens the survey.
     */
    @Override
    public void cancel() {
        setState(new OpenState(getSurvey()));
    }

    /**
     * Reopens the survey.
     */
    @Override
    public void open() {
        setState(new OpenState(getSurvey()));
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
    }

    public String renderResults(Person p) {
        return super.renderResults(p) + " (fechado)";
    }
    
    @Override
    public String notifyState() {
        return "closed";
    }
}