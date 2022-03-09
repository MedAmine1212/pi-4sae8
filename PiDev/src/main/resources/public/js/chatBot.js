'use strict';

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var userPage = document.querySelector('#userPage');
var chatPage = document.querySelector('#chat-page');

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
        chatPage.classList.remove('hidden');
    }
});

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    addMessage(messageContent, 1);
        $.ajax({
            url: '/chatbot/search/'+messageContent,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function (response) {
                console.log(response);
                addMessage(response, 2);
            },
            error: function () {
                alert("error");
            }
        });
        messageInput.value = '';
    event.preventDefault();
}

function addMessage(text, id) {

    var messageElement = document.createElement('li');
    let sender;
    if(id === 2) {
        sender = "Help Bot";
    } else {
        sender = username;
    }
    messageElement.classList.add('chat-message');

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(sender[0]);
    if(id === 1) {
        avatarElement.style["position"] = "relative";
        avatarElement.style["float"] = "right";
        messageElement.style["text-align"] = "right";
    }
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(sender);

    messageElement.appendChild(avatarElement);

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(text);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
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
    userid = document.getElementById("userId").value;
    $.ajax({
        url: '/getUserName/'+userid,
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
                username = response.sender;
                userPage.classList.add('hidden');
                chatPage.classList.remove('hidden');
        },
        error: function () {
            alert("error");
        }
    });

    event.preventDefault();
}

userPage.addEventListener('submit', setUser, true)
messageForm.addEventListener('submit', sendMessage, true)
