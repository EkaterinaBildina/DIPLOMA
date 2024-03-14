package textileMain.textileDB;

import exeptions.TextileNotFountException;
import textileMain.textile.Textile;

import java.util.List;

public interface ITextileDB {
    Textile addTextile(int id, String TextileNew, double qty);
    Textile deleteTextile(int id) throws TextileNotFountException;
    List<Textile> getTextile();
    void addAll(List<Textile> listTextile);


}
