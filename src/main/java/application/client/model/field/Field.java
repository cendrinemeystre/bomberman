package application.client.model.field;

public abstract class Field {
    protected FieldType type;

    protected Field() {

    }

    public FieldType getType() {
        return type;
    }
}
