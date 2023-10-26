package textileMain.textileDB;

import exeptions.TextileNotFountException;
import textileMain.textile.Textile;

import java.util.List;

public interface ITextileDB {
    Textile addTextile(String TextileNew);
    Textile deleteUser(int userId) throws TextileNotFountException;
    List<Textile> getTextile();
    void addAll(List<Textile> listTextile);
}
