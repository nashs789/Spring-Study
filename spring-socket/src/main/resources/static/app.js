const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    let subsUrl = '/topic/greetings/' + $("#room_no").val();
    console.log(subsUrl);
    setConnected(true);

    stompClient.subscribe(subsUrl, (greeting) => {
        // let res = JSON.parse(greeting.body);

        showGreeting(greeting.body);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);

    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }

    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
}

let myChatRoom = "/msg/chatting"

function sendName() {

    let param = {
          'userName': $("#name").val()
        , 'content': $("#content").val()
        , 'roomNo' : $("#room_no").val()
    }

    console.log("param = ", param);

    stompClient.publish({
        destination: myChatRoom,
        body: JSON.stringify(param)
    });
}

function showGreeting(message) {
    console.log("call showGreeting");
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

