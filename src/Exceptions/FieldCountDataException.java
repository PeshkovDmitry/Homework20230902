package Exceptions;

public class FieldCountDataException extends DataException {

    public FieldCountDataException(String message, String text, int fieldCount) {
        super(message, text);
        this.fieldCount = fieldCount;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    private int fieldCount;


}
