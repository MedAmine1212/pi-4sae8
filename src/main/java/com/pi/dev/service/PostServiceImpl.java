package com.pi.dev.service;

import java.util.List;

import com.pi.dev.models.Post;
import com.pi.dev.repository.PostRepository;
import com.pi.dev.serviceInterface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return  postRepository.findAll();
	}
}