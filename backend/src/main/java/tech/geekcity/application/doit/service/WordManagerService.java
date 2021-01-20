package tech.geekcity.application.doit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.geekcity.application.doit.entity.Word;
import tech.geekcity.application.doit.repository.WordRepository;

import java.util.List;

@Service
public class WordManagerService {
    private final WordRepository wordRepository;

    @Autowired
    public WordManagerService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> wordList(int limit) {
        return wordRepository.findAllWord(limit);
    }

    public Word add(Word word) {
        return update(word);
    }

    public Word update(Word word) {
        return wordRepository.saveWord(word);
    }

    public void delete(long wordId) {
        wordRepository.deleteWord(wordId);
    }
}
