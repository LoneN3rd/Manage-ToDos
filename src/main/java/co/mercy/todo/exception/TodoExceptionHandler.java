package co.mercy.todo.exception;

public class TodoExceptionHandler extends Exception{

    private static final long serialVersionUID = 1L;

    public TodoExceptionHandler(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return "Todo with id "+id+" not found";
    }

    public static String AlreadyExists(){
        return "Todo with given name already exists";
    }
}
