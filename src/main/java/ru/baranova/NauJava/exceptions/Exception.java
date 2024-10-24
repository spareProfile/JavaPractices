package ru.baranova.NauJava.exceptions;

public class Exception
{
    private String message;
    private Exception(String message)
    {
    this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Exception create(String e) {
        return new Exception(e);
    }

    public static Exception create(Throwable e) {
        return new Exception(e.getMessage());
    }
    
}
