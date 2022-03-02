package com.pi.dev.serviceInterface;

import com.pi.dev.models.Post;
import com.pi.dev.models.PostLike;
import com.pi.dev.models.Rating;

import java.util.List;

public interface IKeyWordService {

	void addWord(String word);

	boolean checkIfKeyWordExists(String word);

	void autoRefreshKeyWords();
}
