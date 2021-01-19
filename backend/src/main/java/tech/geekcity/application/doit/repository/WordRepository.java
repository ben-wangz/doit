package tech.geekcity.application.doit.repository;

import org.springframework.data.repository.CrudRepository;
import tech.geekcity.application.doit.entity.Word;
import tech.geekcity.application.doit.entity.pojo.WordPojo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface WordRepository extends CrudRepository<WordPojo, Long> {

    @Transactional
    default List<Word> findAllWord(int limit) {
        return findAllBy()
                .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                .map(pojo -> Word.Builder.newInstance().fromPojo(pojo))
                .collect(Collectors.toList());
    }

    default Word saveWord(Word word) {
        return Word.Builder.newInstance()
                .fromPojo(save(word.asPojo()));
    }

    default void deleteWord(long wordId) {
        deleteById(wordId);
    }

    Stream<WordPojo> findAllBy();
}