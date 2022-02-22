package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.IPostService;
import com.pi.dev.serviceInterface.IRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class RoomServiceImpl implements IRoomService {


	@Autowired
	UserRepository userRepository;

	@Autowired
	RoomRepository roomRepository;


	@Override
	public List<Room> findAll() {
		return null;
	}

	@Override
	public List<Room> findRoomBySubject(String subject) {
		return null;
	}

	@Override
	public Room createRoom(Room comment) {
		return null;
	}

	@Override
	public Room updateRoom(Room room, Long roomId) {
		return null;
	}

	@Override
	public boolean removeRoom(Long commentId) {
		return false;
	}

	@Override
	public boolean joinRoom(Long roomId, Long userId) {
		return false;
	}

	@Override
	public boolean leaveRoom(Long roomId, Long userId) {
		return false;
	}
}