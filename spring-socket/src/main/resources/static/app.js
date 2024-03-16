const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    console.log("call onConnect");

    setConnected(true);
    console.log('Connected: ' + frame);

    stompClient.subscribe('/topic/greetings', (greeting) => {
        let res = JSON.parse(greeting.body);

        showGreeting(res.body);
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
    console.log("call setConnected");

    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    console.log("call connect");
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

let myChatRoom = "/msg/hello"

function sendName() {

    let param = {
          'user_name': $("#name").val()
        , 'content': $("#content").val()
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

