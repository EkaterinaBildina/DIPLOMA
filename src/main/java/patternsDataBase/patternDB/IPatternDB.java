package patternsDataBase.patternDB;


import exeptions_common.PatternNotFountException;
import patternsDataBase.pattern.Pattern;

import java.util.List;

public interface IPatternDB {
    Pattern addPattern(String name);
    Pattern deletePattern(int id) throws PatternNotFountException;
    List<Pattern> getPattern();
    void addAll(List<Pattern> listPattern);
}
