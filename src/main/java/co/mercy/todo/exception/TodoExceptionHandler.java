package co.mercy.todo.exception;

public class TodoExceptionHandler extends Exception{

    private static final long serialVersionUID = 1L;

    public TodoExceptionHandler(String message){
        super(message);
    }

    public static String IDNotFoundException(String id){
        return "Todo with id "+id+" not found";
    }

    public static String AlreadyExists(String todoName){
        return "A todo with name '"+ todoName +"' was created today";
    }


    // return new ResponseEntity<>("Todo with name "+ name +" not found", HttpStatus.NOT_FOUND);
    // return new ResponseEntity<>("No todos created today", HttpStatus.NOT_FOUND);
}
