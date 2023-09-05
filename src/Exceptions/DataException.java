package Exceptions;

abstract class DataException extends Exception {

    public DataException(String message, String text) {
        super(message);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    private String text;


}
