package com.pi.dev.serviceInterface;

import com.pi.dev.models.Room;

import java.util.List;

public interface IRoomService {

	List<Room> findAll();

	List<Room> findRoomBySubject(String subject);

	Room createRoom(Room comment);

	Room updateRoom(Room room, Long roomId);

	boolean removeRoom(Long commentId);

	boolean joinRoom(Long roomId, Long userId);

	boolean leaveRoom(Long roomId, Long userId);

}
