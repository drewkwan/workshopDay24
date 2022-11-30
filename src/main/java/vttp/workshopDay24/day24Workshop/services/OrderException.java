package vttp.workshopDay24.day24Workshop.services;

public class OrderException extends Exception {
    
    //checked exception

    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }
}
