package bg.softuni.pathfinder.exceptions;

public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException(String message, Throwable ex) {
        super(message, ex);
    }
}
