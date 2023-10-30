package test;

import exeptions.PatternNotFountException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import patternMain.pattern.Pattern;
import patternMain.patternDB.PatternDB;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class PatternDBTest {
    private PatternDB patternDBTest;

    @BeforeEach
    void setUp() {
        patternDBTest = new PatternDB();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Проверка добавления Выкройки")
    public void addPattern() {
        Pattern result = patternDBTest.addPattern("Keil");
        assertEquals("Keil", result);
    }

    @Test
    @DisplayName("Проверка удаления Выкройки")
    public void deletePattern() throws PatternNotFountException {
        Pattern result = patternDBTest.deletePattern(5);
        assertNull(result);
    }

}
