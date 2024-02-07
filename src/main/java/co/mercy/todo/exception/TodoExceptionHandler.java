package co.mercy.todo.exception;

public class TodoExceptionHandler extends Exception{

    private static final long serialVersionUID = 1L;

    public TodoExceptionHandler(String message){
        super(message);
    }

    public static String IDNotFound(String id){
        return "Todo with id "+ id +" not found";
    }

    public static String NameNotFoundException(String name){
        return "Todo with id "+ name +" not found";
    }

    public static String AlreadyExists(String todoName){
        return "A todo with name '"+ todoName +"' was created today";
    }
}
