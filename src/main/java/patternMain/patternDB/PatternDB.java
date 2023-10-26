package patternMain.patternDB;

import exeptions.PatternNotFountException;
import patternMain.pattern.Pattern;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PatternDB implements IPatternDB {
    private final List<Pattern> patterns = new ArrayList<>();
    private final AtomicInteger id_add = new AtomicInteger(1);

    @Override
    public Pattern addPattern(String name) {
        int id = id_add.getAndIncrement();
        int size = 0;
        int height = 0;
        Pattern newPattern = new Pattern(id, name, size, height);
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
