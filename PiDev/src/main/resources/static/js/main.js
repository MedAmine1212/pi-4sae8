'use strict';

var chatRoomPage = document.querySelector('#chatroom-page');
var chatPage = document.querySelector('#chat-page');
var chatRoomForm = document.querySelector('#chatRoomForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var allRooms = document.querySelector('#allRooms');
var userPage = document.querySelector('#userPage');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var roomId = null;
var subject = null;
var username = null;
var userid = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

window.addEventListener('load', function (e) {
    if(userid == null) {
        userPage.classList.remove('hidden');
    } else {
        getRooms();
    }
});
function getRooms() {
    $.post( "/chatRoom/getChatRooms", function( data ) {
        if(jQuery.isEmptyObject(data)) {
            chatRoomPage.classList.remove('hidden');
        } else {
            console.log(data);
            data.forEach(item=>{
                let field ="<h3 style='cursor: pointer' onclick='connect("+item.roomId+")' id='h3"+item.roomId+"'>"+item.subject+"</h3>";
                document.getElementById("roomList").innerHTML = document.getElementById("roomList").innerHTML+field;
            })
            allRooms.classList.remove('hidden');

            chatRoomPage.classList.remove('hidden');

        }
    });
}
window.addEventListener('beforeunload', function (e) {
    if (roomId != null) {
    stompClient.subscribe('/topic/public/'+roomId, onMessageReceived);

    stompClient.send("/app/chat.leave/"+roomId,
        {},
        JSON.stringify({sender: username, senderId: userid,roomId: roomId, type: 'LEAVE'})
    )
    }
});

function connect(roomid) {
    roomId = roomid;

    allRooms.classList.add('hidden');

    chatRoomPage.classList.add('hidden');
    if(subject == null) {
        subject = document.getElementById("h3"+roomid).innerHTML;
    }
    document.getElementById("topicField").innerHTML = subject;
    chatPage.classList.remove('hidden');
    var socket = new SockJS('/chatRoom/'+roomId);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function createChatRoom(event) {
    $.ajax({
        url: '/chatRoom/createChatRoom/'+document.getElementById("chatRoomTopic").value+'/'+userid,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            chatRoomPage.classList.add('hidden');
            allRooms.classList.add('hidden');
            console.log(response);
            roomId = response.roomId;
            subject = response.subject;
            document.getElementById("topicField").innerHTML = subject;
            chatPage.classList.remove('hidden');

            var socket = new SockJS('/chatRoom/'+roomId);
            stompClient = Stomp.over(socket);

            stompClient.connect({}, onConnected, onError);
        },
        error: function () {
            alert("error");
        }
    });
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public/'+roomId, onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.register/"+roomId,
        {},
        JSON.stringify({sender: username, senderId: userid,roomId: roomId, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function send(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender:  username,
            senderId: userid,
            roomId:roomId,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.send/"+roomId, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    let addElement = true;
    var message = JSON.parse(payload.body);
    let doIt = true;
    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        doIt = false;
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        document.getElementById("roomList").innerHTML ="";
        doIt = false;
        if(message.sender !== "roomDestroyed"){
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
        } else {
            chatPage.classList.add("hidden");
            alert("Room destroyed by owner");
            roomId = null;
            document.getElementById("messageArea").innerHTML = "";
            addElement = false;
            getRooms();
        }
    } else if (message.type === 'REFRESH'){
        if(message.requestedBy != userid){
            doIt = false;
            addElement = false;
        }
    }
    console.log("____________________________________________");
    console.log(doIt);
    console.log(addElement);
    console.log("____________________________________________");
    if (doIt){
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        if(message.senderId == userid) {
            avatarElement.style["position"] = "relative";
            avatarElement.style["float"] = "right";
            messageElement.style["text-align"] = "right";
        }
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
if(addElement) {
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

function setUser(event) {
    subject = null;
    userid = document.getElementById("userId").value;
    $.ajax({
        url: '/chatRoom/getUserName/'+userid,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            console.log(response);
                if(response.roomId != null) {
                  alert("This user is allready connected to a room, please leave the old room to connect.")
                } else {
                    username = response.sender;
                getRooms();
                    userPage.classList.add('hidden');
                }
        },
        error: function () {
            alert("error");
        }
    });

    event.preventDefault();
}
chatRoomForm.addEventListener('submit', createChatRoom, true)
messageForm.addEventListener('submit', send, true)

userPage.addEventListener('submit', setUser, true)
