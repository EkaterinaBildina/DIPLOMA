package patternsDataBase.pattern;


import javax.persistence.*;

// класс представляет раздел Выкройки
@Entity
@Table(name = "patternsDataBase/pattern")
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // id выкройки
    @Column(name = "name")
    private String name; // название
    @Column(name = "size")
    private int size; // параметр размер
    @Column(name = "height")
    private int height; // параметр рост

    // Конструктор по умолчанию, необходим для Jackson
    public Pattern() {}

    public Pattern( String name, int size, int height) {
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
