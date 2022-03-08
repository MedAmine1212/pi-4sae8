package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.IRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoomServiceImpl implements IRoomService {


	@Autowired
	UserRepository userRepository;

	@Autowired
	RoomRepository roomRepository;


	@Autowired
	MessageRepository messageRepository;


	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public List<Room> findRoomBySubject(String subject) {
		return roomRepository.findOneBySubject(subject);
	}

	@Override
	public Room createRoom(Room room, Long userId) {
		User user = userRepository.findById(userId).get();
		user.setRoomOwner(true);
		List<User> users = new ArrayList<>();
		users.add(user);
		room.setUsers(users);
		Room rm = roomRepository.save(room);
		user.setActualRoom(rm);
		userRepository.save(user);
		return rm;
	}

	@Override
	public Room updateRoom(Room room, Long roomId) {
		room.setRoomId(roomId);
		return roomRepository.save(room);
	}

	@Transactional
	@Override
	public boolean removeRoom(Long roomId) {
		try {
			for (User u : userRepository.findAllByActualRoom(roomRepository.findById(roomId).get())) {
				u.setActualRoom(null);
				if (u.isRoomOwner()) {
					u.setRoomOwner(false);
				}

				userRepository.save(u);
			}
			roomRepository.deleteById(roomId);
			return true;
		}catch (Exception ex) {
			log.info(ex.getMessage());
			return false;
		}

	}

	@Override
	public boolean joinRoom(Long roomId, Long userId) {

		try{
		Room room = roomRepository.findById(roomId).get();
		User user = userRepository.findById(userId).get();
		user.setActualRoom(room);
		userRepository.save(user);
		return true;
	}  catch (Exception ex) {
			log.info(ex.getMessage());
			return false;
		}
	}

	@Override
	public boolean leaveRoom(Long userId) {
		try{
		User user = userRepository.findById(userId).get();
		user.setActualRoom(null);
		userRepository.save(user);
			return true;
		}  catch (Exception ex) {
			log.info(ex.getMessage());
			return false;
		}
	}

	@Override
	public Room findById(Long roomId) {
		return roomRepository.findById(roomId).get();
	}
}