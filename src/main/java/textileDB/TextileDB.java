package textileDB;

import exeptions.TextileNotFountException;
import textile.Textile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TextileDB implements ITextileDB {

    private final List<Textile> textile = new ArrayList<>();
    private final AtomicInteger id_add = new AtomicInteger(1);

    @Override
    public Textile addTextile(String name) {
        int id = id_add.getAndIncrement();
        double active_qty = 0;
        Textile newTextile = new Textile(name, active_qty);
        textile.add(newTextile);
        return newTextile;
    }

    @Override
    public Textile deleteUser(int userId) throws TextileNotFountException {
        return null;
    }

    @Override
    public Textile deleteTextile(int id) throws TextileNotFountException {
        for (int i = 0; i < textile.size(); i++) {
            if (textile.get(i).getId() == id) {
                return textile.remove(i);
            }
        }
        throw new TextileNotFountException("Textile name is not found in DataBase");
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
