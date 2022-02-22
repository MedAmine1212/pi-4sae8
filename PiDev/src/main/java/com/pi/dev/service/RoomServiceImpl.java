package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.IRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return roomRepository.findAll();
	}

	@Override
	public List<Room> findRoomBySubject(String subject) {
		return roomRepository.findOneBySubject(subject);
	}

	@Override
	public Room createRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Room room, Long roomId) {
		room.setRoomId(roomId);
		return roomRepository.save(room);
	}

	@Override
	public boolean removeRoom(Long roomId) {
		try{
			roomRepository.deleteById(roomId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean joinRoom(Long roomId, Long userId) {

		try{
		Room room = roomRepository.getById(roomId);
		User user = userRepository.getById(userId);
		room.getUsers().add(user);
		roomRepository.save(room);
		return true;
	}  catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean leaveRoom(Long roomId, Long userId) {
		try{
		Room room = roomRepository.getById(roomId);
		User user = userRepository.getById(userId);
		room.getUsers().remove(user);
		roomRepository.save(room);
			return true;
		}  catch (Exception ex) {
			return false;
		}
	}
}