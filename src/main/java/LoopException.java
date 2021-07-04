public class LoopException extends RuntimeException{
    public LoopException() {
        super("There is a loop in this family tree.");
    }
}
