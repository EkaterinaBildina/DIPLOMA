package fabricsDataBase.textileDB;

import exeptions_common.TextileNotFountException;
import fabricsDataBase.textile.Textile;

import java.util.List;

public interface ITextileDB {
    Textile addTextile(String TextileNew);
    Textile deleteUser(int userId) throws TextileNotFountException;

    Textile deleteTextile(int id) throws TextileNotFountException;

    List<Textile> getTextile();
    void addAll(List<Textile> listTextile);
}
