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
	List<Room> findRoomBySubject(@PathVariable String subject) {
		return roomService.findRoomBySubject(subject);
	}

	@ApiOperation(value = "Create room")
	@PostMapping("/createRoom/{userId}")
	@ResponseBody
	Room createRoom(@RequestBody Room room,  @PathVariable Long userId){
		return roomService.createRoom(room, userId);
	}

	@ApiOperation(value = "Update room")
	@PostMapping("/updateRoom/{roomId}")
	@ResponseBody
	Room updateRoom(@RequestBody Room room,@PathVariable Long roomId){
		return roomService.updateRoom(room, roomId);
	}

	@ApiOperation(value = "Delete room")
	@DeleteMapping("/deleteRoom/{roomId}")
	@ResponseBody
	boolean removeRoom(@PathVariable Long roomId){
		return roomService.removeRoom(roomId);
	}


	@ApiOperation(value = "Join room")
	@GetMapping("/jointRoom/{roomId}/{userId}")
	@ResponseBody
	boolean joinRoom(@PathVariable Long roomId,@PathVariable Long userId){
		return roomService.joinRoom(roomId, userId);
	}


	@ApiOperation(value = "Leave room")
	@GetMapping("/leaveRoom/{userId}")
	@ResponseBody
	boolean leaveRoom(@PathVariable Long userId){
		return roomService.leaveRoom(userId);
	}


}
