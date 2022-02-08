package master;

public class ClassCreationFields {
    private int id;
    private String class_Name;

    public ClassCreationFields() {
    }

    public ClassCreationFields(int id, String class_Name) {
        this.id = id;
        this.class_Name = class_Name;
    }

    public ClassCreationFields(String class_Name) {
        this.class_Name = class_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }
}
