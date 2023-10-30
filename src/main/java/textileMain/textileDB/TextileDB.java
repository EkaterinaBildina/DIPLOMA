package textileMain.textileDB;

import exeptions.TextileNotFountException;
import textileMain.textile.Textile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


// класс имплементирует интерфейс Базы Данных Тканей
public class TextileDB implements ITextileDB {

    private final List<Textile> textile = new ArrayList<>(); // Ар.Лист с тканями в налии
    private final AtomicInteger id_add = new AtomicInteger(1); // новый id


    // метод отвечает за добавление Ткани
    @Override
    public Textile addTextile(int id, String name, double active_qty) {
        id = id_add.getAndIncrement(); // инкрементируем ID по порядку от последнего
        //double active_qty = 0; // количество по умолчанию равно 0
        Textile newTextile = new Textile(id, name, active_qty);
        textile.add(newTextile); // добавляет новые данные в Ар.Лист
        return newTextile;
    }


    // метод отвечает за удаление Ткани
    // используя Исключение - проверка ID в базе данных


    @Override
    public Textile deleteTextile(int id) throws TextileNotFountException {
        // проверка id есть ли в списке
        // в случае положительного результата - удаляем из списка
        for (int i = 0; i < textile.size(); i++) {
            if (textile.get(i).getId() == id) {
                return textile.remove(i);
            }
        }
        // Срабатывает исключение - не найдена ткань
        throw new TextileNotFountException("Textile id is not found in DataBase");
    }

    @Override
    public List<Textile> getTextile() {
        return textile;
    }

    @Override
    public void addAll(List<Textile> listTextile) {
        textile.addAll(listTextile);
    }
}
