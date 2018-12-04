package sth;

import sth.exceptions.SurveyWithAnswersException;

public class CreatedState extends Survey.State{
    public CreatedState(Survey survey){
        survey.super(); 
    }

    public void cancel(){
        if (getSurvey().getAnswersNumber() == 0){
			getSurvey().remove();
		} else {
			throw new SurveyWithAnswersException();
		}     //FIXME isto pode dar erro 
    }
    public void open(){ // FIXME mandar throw;ler enunciado   
        
    }

    public void close(){ // FIXME mandar throw

    }
    
    public void finalize(){ //FIXME mandar throw

    }
    public String render(){
        return super.render() + " (por abrir)";
    }

    @Override
    public String notifyState() {
        return "created";
    }
    
}