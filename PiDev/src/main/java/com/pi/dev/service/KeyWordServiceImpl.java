package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.IKeyWordService;
import com.pi.dev.serviceInterface.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class KeyWordServiceImpl implements IKeyWordService {

	@Autowired
	KeyWordRepository keyWordRepository;
	@Override
	public void addWord(String word) {
		KeyWord w = keyWordRepository.findOneByKeyWord(word.toLowerCase());
		if(w != null) {
			w.setCount(w.getCount()+1);
			keyWordRepository.save(w);
		} else {
			KeyWord kw = new KeyWord();
			kw.setCount(1);
			LocalDate localDate = LocalDate.now();
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
			kw.setAddedDate(date);
			kw.setKeyWord(word.toLowerCase());
			keyWordRepository.save(kw);
		}
	}

	@Override
	public List<KeyWord> findAll() {
		return keyWordRepository.findAll();
	}

	@Override
	public boolean checkIfKeyWordExists(String word) {
		return keyWordRepository.findOneByKeyWord(word) != null;
	}

	@Scheduled(cron = "@weekly")
//	@Scheduled(fixedRate = 30000)
	@Override
	public void autoRefreshKeyWords() {
		List<KeyWord> keyWords = keyWordRepository.getKeyWordsWithLowRate();
		LocalDate localDate = LocalDate.now();
		if(!keyWords.isEmpty()) {
			for (KeyWord w : keyWords) {
				LocalDate dt = w.getAddedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (Duration.between(localDate, dt).toDays() > 10) {
					keyWordRepository.delete(w);
				}
			}
		}
	}
}