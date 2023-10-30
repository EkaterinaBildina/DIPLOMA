package test;

import exeptions.TextileNotFountException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import textileMain.textile.Textile;
import textileMain.textileDB.TextileDB;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;



public class TextileDBTest {

    TextileDB textileDBTest = new TextileDB();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Проверка добавления Ткани")
    public void addTextile() {
        Textile result = textileDBTest.addTextile(1, "Россыпь", 6.5);
        assertEquals("Textile{id='1', name='Россыпь', active_qty=6.5 м.}", result);
    }

    @Test
    @DisplayName("Проверка удаления Ткани")
    public void deleteTextile() throws TextileNotFountException {
        textileDBTest.addTextile(1, "Россыпь", 6.5);
        Textile result = textileDBTest.deleteTextile(1);
        assertNull(result);
    }

    @Test
    @DisplayName("Проверка работы получения значения Ткани из списка")
    public void getTextile() {
        List<Textile> array = new ArrayList<>();
        Textile result = (Textile) textileDBTest.getTextile();
        assertEquals(array, result);
    }

}
