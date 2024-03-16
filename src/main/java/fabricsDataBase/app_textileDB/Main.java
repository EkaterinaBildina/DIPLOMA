package fabricsDataBase.app_textileDB;

import fabricsDataBase.textileDB.ConnectionTextile;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from TextileDB");
        ConnectionTextile.connect();

        }
}
