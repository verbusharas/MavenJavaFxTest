package lt.verbus.exception;

public class EmptyRepositoryException extends Exception{

    public EmptyRepositoryException() {
    }

    public EmptyRepositoryException(String message) {
        super(message);
    }

}
