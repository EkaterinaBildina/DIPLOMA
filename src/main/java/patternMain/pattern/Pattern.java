package patternMain.pattern;

public class Pattern {

    private int id;
    private String name;
    private int size;
    private int height;

    // Конструктор по умолчанию, необходим для Jackson
    public Pattern() {}

    public Pattern(int id, String name, int size, int height) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String toString() {
        return "Pattern{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size =" + size + '\'' +
                ", height =" + height +
                '}';
    }

}
