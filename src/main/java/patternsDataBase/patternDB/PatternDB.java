package patternsDataBase.patternDB;

import exeptions_common.PatternNotFountException;
import patternsDataBase.pattern.Pattern;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// класс отвечает за Базу Данных выкроек
// имплементируе интерфейс БД Выкроек
public class PatternDB implements IPatternDB {
    private final List<Pattern> patterns = new ArrayList<>(); // список выкроек
    private final AtomicInteger id_add = new AtomicInteger(1); // добавление нового id

    @Override
    public Pattern addPattern(String name) {
        int id = id_add.getAndIncrement();
        int size = 0;
        int height = 0;
        Pattern newPattern = new Pattern(name, size, height);
        patterns.add(newPattern);
        return newPattern;
    }


    @Override
    public Pattern deletePattern(int id) throws PatternNotFountException {
        for (int i = 0; i < patterns.size(); i++) {
            if (patterns.get(i).getId() == id) {
                return patterns.remove(i);
            }
        }
        throw new PatternNotFountException("Pattern id is not found in DataBase");
    }

    @Override
    public List<Pattern> getPattern() {
        return patterns;
    }

    @Override
    public void addAll(List<Pattern> listPattern) {

    }
}
