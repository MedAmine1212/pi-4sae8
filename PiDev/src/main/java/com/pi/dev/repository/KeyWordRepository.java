package com.pi.dev.repository;

import com.pi.dev.models.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KeyWordRepository extends JpaRepository<KeyWord, Long> {
    KeyWord findOneByKeyWord(String word);

    @Query("select kw from KeyWord kw where kw.count < 10")
    List<KeyWord> getKeyWordsWithLowRate();
}
