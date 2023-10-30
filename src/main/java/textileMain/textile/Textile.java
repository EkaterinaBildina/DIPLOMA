package textileMain.textile;

// Класс, который представляет раздел Ткани
public class Textile {
    private int id; // ID ткани
    private String name; // название
    private double active_qty; // количество ткани в наличии

    // Конструктор по умолчанию, необходим для Jackson
    public Textile() {}


    // конструктор параметров Тканей
    public Textile(int id, String name, double active_qty) {
        this.id = id;
        this.name = name;
        this.active_qty = active_qty;
    }



    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getActive_qty() { return active_qty; }
    public void setActive_qty(double active_qty) { this.active_qty = active_qty; }



    @Override
    public String toString() {
        return "Textile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", active_qty=" + active_qty + " м." +
                '}';
    }
}
