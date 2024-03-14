package textile;

import javax.persistence.*;


@Entity
@Table(name = "textile")
public class Textile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "active_qty")
    private Double activeQTY;

    public Textile() {
    }

    public Textile(String name, Double activeQTY) {

        this.name = name;
        this.activeQTY = activeQTY;
    }

    public static Textile create() {
        return new Textile();
    }


    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getActiveQTY() {
        return activeQTY;
    }

    public void setActiveQTY(Double activeQTY) {
        this.activeQTY = activeQTY;
    }

    @Override
    public String toString() {
        return "Textile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", active_qty=" + activeQTY + " м." +
                '}';
    }

    public void updateName(String nameNew) {
        name = nameNew;
    }

    public void updateQTY(double order) {
        activeQTY = activeQTY - order;
    }
}
