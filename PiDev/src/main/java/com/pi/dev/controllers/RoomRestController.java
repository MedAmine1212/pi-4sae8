package com.pi.dev.controllers;

import com.pi.dev.models.Post;
import com.pi.dev.models.PostLike;
import com.pi.dev.models.Rating;
import com.pi.dev.models.Room;
import com.pi.dev.serviceInterface.IPostService;
import com.pi.dev.serviceInterface.IRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Manage rooms")
@RequestMapping("/room")
public class RoomRestController {

	@Autowired
	IRoomService roomService;

    //http://localhost:8085/swagger-ui/index.html
	@ApiOperation(value = "Get rooms list")
	@GetMapping("/getAllRooms")
	@ResponseBody
	List<Room> findAll() {
		return roomService.findAll();
	}


	@ApiOperation(value = "Get rooms list by subject")
	@GetMapping("/getRoomsBySubject/{subject}")
	@ResponseBody
	List<Room> findRoomBySubject(String subject) {
		return roomService.findRoomBySubject(subject);
	}

	@ApiOperation(value = "Create room")
	@GetMapping("/createRoom")
	@ResponseBody
	Room createRoom(Room room){
		return roomService.createRoom(room);
	}

	@ApiOperation(value = "Update room")
	@PostMapping("/updateRoom")
	@ResponseBody
	Room updateRoom(Room room, Long roomId){
		return roomService.updateRoom(room, roomId);
	}

	@ApiOperation(value = "Delete room")
	@DeleteMapping("/deleteRoom")
	@ResponseBody
	boolean removeRoom(Long roomId){
		return roomService.removeRoom(roomId);
	}


	@ApiOperation(value = "Join room")
	@GetMapping("/jointRoom/{roomId}/{userId}")
	@ResponseBody
	boolean joinRoom(@PathVariable Long roomId,@PathVariable Long userId){
		return roomService.joinRoom(roomId, userId);
	}


	@ApiOperation(value = "Leave room")
	@GetMapping("/leaveRoom/{roomId}/{userId}")
	@ResponseBody
	boolean leaveRoom(@PathVariable Long roomId,@PathVariable Long userId){
		return roomService.leaveRoom(roomId, userId);
	}


}
