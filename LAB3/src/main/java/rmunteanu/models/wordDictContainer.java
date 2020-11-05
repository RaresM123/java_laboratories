package rmunteanu.models;

import rmunteanu.models.exceptions.DuplicateWordException;

import java.util.HashMap;
import java.util.Map;

public class wordDictContainer {

    Map<String, wordsDict> wordsRecords;

    public wordDictContainer() {
        wordsRecords= new HashMap<String, wordsDict>();
    }

    public wordDictContainer(wordDictContainer container) {
        wordsRecords = container.getAllRecords();
    }

    public void addRecord(wordsDict record)
            throws DuplicateWordException {

        if (wordsRecords.containsKey(record.getWord()))
            throw new DuplicateWordException("Word " + record.getWord() + " already exists!");

        wordsRecords.put(record.getWord(), record);
    }

    public Map<String, wordsDict> getAllRecords()
    {
        return new HashMap<>(wordsRecords);
    }
}
