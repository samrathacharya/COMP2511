package unsw.enrolment;


//OBSERVER PATTERN
public interface Subject {
    public void removeObserver(Observer o);
    public void notifyObservers();
    public void registerObserver(Observer o);

}
