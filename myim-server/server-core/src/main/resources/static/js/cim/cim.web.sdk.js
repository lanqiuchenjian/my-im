/**
 服务端 websocket端口
 */
var CIM_URI;

const APP_VERSION = "1.0.0";
const APP_CHANNEL = "browser";
const APP_PACKAGE = "com.farsunset.cim";

const CLIENT_BIND = "client_bind"
const CLIENT_PUSH = "client_push"
const CLIENT_CLOSED = "client_closed"

/*
 *特殊的消息类型，代表被服务端强制下线
 */
const ACTION_999 = "999";
const DATA_HEADER_LENGTH = 1;

const MESSAGE = 2;
const REPLY_BODY = 4;

let socket;
let manualStop = false;
const CIMPushManager = {};

CIMPushManager.connect = function () {
    manualStop = false;
    window.localStorage.account = '';

    var json = {};
    $.ajax({
        type: "POST",
        url: "http://127.0.0.1:9080/api/im/server/target/info",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.status === "success") {
                CIM_URI = "ws://" + data.host + ":" + data.port;

            } else {
                alert("系统错误" + data.msg)
            }
        },
        error: function (message) {
            // alert("提交数据失败！");
        }
    });

    CIM_URI = "ws://" + "192.168.1.102" + ":" + "34567"
    socket = new WebSocket(CIM_URI);
    socket.cookieEnabled = false;
    socket.binaryType = 'arraybuffer';
    socket.onopen = CIMPushManager.innerOnConnectFinished;
    socket.onmessage = CIMPushManager.innerOnMessageReceived;
    socket.onclose = CIMPushManager.innerOnConnectionClosed;
};

CIMPushManager.bindAccount = function (account) {

    window.localStorage.account = account;

    let deviceId = window.localStorage.deviceId;
    if (deviceId == '' || deviceId == undefined) {
        deviceId = generateUUID();
        window.localStorage.deviceId = deviceId;
    }

    let browser = getBrowser();
    let body = new proto.com.myim.web.model.SentBody();
    body.setKey(guid());
    body.setTimestamp(new Date().getTime());
    body.getDataMap().set("account", account);
    body.getDataMap().set("channel", APP_CHANNEL);
    body.getDataMap().set("appVersion", APP_VERSION);
    // body.getDataMap().set("osVersion", browser.version);
    body.getDataMap().set("packageName", APP_PACKAGE);
    body.getDataMap().set("type", CLIENT_BIND);
    body.getDataMap().set("deviceId", deviceId);
    body.getDataMap().set("device", browser.name);
    CIMPushManager.sendRequest(body);
};

CIMPushManager.stop = function () {
    manualStop = true;
    socket.close();
};

CIMPushManager.resume = function () {
    manualStop = false;
    CIMPushManager.connect();
};


CIMPushManager.innerOnConnectFinished = function () {
    let account = window.localStorage.account;
    if (account === '' || account === undefined) {
        onConnectFinished();
    } else {
        CIMPushManager.bindAccount(account);
    }
};


function replyServer(msg) {
    //返回服务端响应
    let body = new proto.com.myim.web.model.SentBody();
    body.setKey(msg.key);
    body.setTimestamp(new Date().getTime());
    body.getDataMap().set("type", CLIENT_PUSH);
    body.getDataMap().set("action", "reply");
    body.getDataMap().set("status", "success");
    body.getDataMap().set("reply", "200 ok");
    CIMPushManager.sendRequest(body);
}

CIMPushManager.innerOnMessageReceived = function (e) {
    let data = new Uint8Array(e.data);
    let type = data[0];
    let body = data.subarray(DATA_HEADER_LENGTH, data.length);

    if (type === MESSAGE) {
        let message = proto.com.myim.web.model.Message.deserializeBinary(body);
        var msg = message.toObject(false);

        // alert("1....")
        replyServer(msg);

        onInterceptMessageReceived(msg);
        return;
    }

    if (type === REPLY_BODY) {
        let message = proto.com.myim.web.model.ReplyBody.deserializeBinary(body);
        /**
         * 将proto对象转换成json对象，去除无用信息
         */
        let reply = {};
        reply.code = message.getCode();
        // reply.data.type = message.getDataMap().get("type");
        reply.message = message.getMessage();
        reply.timestamp = message.getTimestamp();
        reply.data = {};

        /**
         * 注意，遍历map这里的参数 value在前key在后
         */
        message.getDataMap().forEach(function (v, k) {
            reply.data[k] = v;
        });

        onReplyReceived(reply);
    }
};

CIMPushManager.innerOnConnectionClosed = function (e) {
    if (!manualStop) {
        let time = Math.floor(Math.random() * (30 - 15 + 1) + 15);
        setTimeout(function () {
            CIMPushManager.connect();
        }, time);
    }
};

CIMPushManager.sendRequest = function (body) {
    let data = body.serializeBinary();
    let protobuf = new Uint8Array(data.length);
    protobuf.set(data, 0);
    socket.send(protobuf);
};

function onInterceptMessageReceived(message) {
    /*
     *被强制下线之后，不再继续连接服务端
     */
    if (message.action == ACTION_999) {
        manualStop = true;
    }
    /*
     *收到消息后，将消息发送给页面
     */
    if (onMessageReceived instanceof Function) {
        // alert("2.....")
        onMessageReceived(message);
    }
}

function getBrowser() {
    let explorer = window.navigator.userAgent.toLowerCase();
    if (explorer.indexOf("msie") >= 0) {
        let ver = explorer.match(/msie ([\d.]+)/)[1];
        return {name: "IE", version: ver};
    }
    else if (explorer.indexOf("firefox") >= 0) {
        let ver = explorer.match(/firefox\/([\d.]+)/)[1];
        return {name: "Firefox", version: ver};
    }
    else if (explorer.indexOf("chrome") >= 0) {
        let ver = explorer.match(/chrome\/([\d.]+)/)[1];
        return {name: "Chrome", version: ver};
    }
    else if (explorer.indexOf("opera") >= 0) {
        let ver = explorer.match(/opera.([\d.]+)/)[1];
        return {name: "Opera", version: ver};
    }
    else if (explorer.indexOf("Safari") >= 0) {
        let ver = explorer.match(/version\/([\d.]+)/)[1];
        return {name: "Safari", version: ver};
    }

    return {name: "Other", version: "1.0.0"};
}

function generateUUID() {
    let d = new Date().getTime();
    let uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        let r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid.replace(/-/g, '');
}

/**********************************************webRtc******************************************************************/
'use strict';

var isChannelReady = false;
var isInitiator = false;
var isStarted = false;
var localStream;
var pc;
var remoteStream;
var turnReady;

var pcConfig = {
    'iceServers': [{
        'urls': 'stun:stun.l.google.com:19302'
    }]
};

// Set up audio and video regardless of what devices are present.
var sdpConstraints = {
    offerToReceiveAudio: true,
    offerToReceiveVideo: true
};

function sendMessage(message) {
    let body = new proto.com.myim.web.model.SentBody();
    body.setKey(guid());
    body.setTimestamp(new Date().getTime());
    body.getDataMap().set("action", "webrtc:SingleChatWebrtcServiceImpl:webrtcMessage");
    body.getDataMap().set("type", CLIENT_PUSH);
    body.getDataMap().set("message", message);
    if (singleChatToName == undefined || singleChatToName == null)
        body.getDataMap().set("toImUserName", "none");
    else
        body.getDataMap().set("toImUserName", singleChatToName);

    CIMPushManager.sendRequest(body);
}

////////////////////////////////////////////////////

var localVideo = document.querySelector('#localVideo');
var remoteVideo = document.querySelector('#remoteVideo');

//55555
function gotStream(stream) {
    console.log('Adding local stream.');
    localStream = stream;
    localVideo.srcObject = stream;
    sendMessage('got user media');
    if (isInitiator) {
        maybeStart();
    }
}

var constraints = {
    video: true
};


if (location.hostname !== 'localhost') {
    requestTurn(
        'https://computeengineondemand.appspot.com/turn?username=41784574&key=4080218913'
    );
}

//channelReady
function maybeStart() {
    console.log('>>>>>>> maybeStart() ', isStarted, localStream, isChannelReady);
    if (!isStarted && typeof localStream !== 'undefined' && isChannelReady) {
        console.log('>>>>>> creating peer connection');
        createPeerConnection();
        pc.addStream(localStream);
        isStarted = true;
        console.log('isInitiator', isInitiator);
        if (isInitiator) {
            doCall();
        }
    }
}

window.onbeforeunload = function() {
    sendMessage('bye');
};

/////////////////////////////////////////////////////////

function createPeerConnection() {
    try {
        pc = new RTCPeerConnection(null);
        pc.onicecandidate = handleIceCandidate;
        pc.onaddstream = handleRemoteStreamAdded;
        pc.onremovestream = handleRemoteStreamRemoved;
        console.log('Created RTCPeerConnnection');
    } catch (e) {
        console.log('Failed to create PeerConnection, exception: ' + e.message);
        alert('Cannot create RTCPeerConnection object.');
        return;
    }
}



function handleCreateOfferError(event) {
    console.log('createOffer() error: ', event);
}

function doCall() {
    console.log('Sending offer to peer');
    pc.createOffer(setLocalAndSendMessage, handleCreateOfferError);
}

function doAnswer() {
    console.log('Sending answer to peer.');
    pc.createAnswer().then(
        setLocalAndSendMessage,
        onCreateSessionDescriptionError
    );
}

function setLocalAndSendMessage(sessionDescription) {
    pc.setLocalDescription(sessionDescription);
    console.log('setLocalAndSendMessage sending message', sessionDescription);
    sendMessage(JSON.stringify(sessionDescription));
}

function onCreateSessionDescriptionError(error) {
    trace('Failed to create session description: ' + error.toString());
}

function requestTurn(turnURL) {
    var turnExists = false;
    for (var i in pcConfig.iceServers) {
        if (pcConfig.iceServers[i].urls.substr(0, 5) === 'turn:') {
            turnExists = true;
            turnReady = true;
            break;
        }
    }
    if (!turnExists) {
        console.log('Getting TURN server from ', turnURL);
        // No TURN server. Get one from computeengineondemand.appspot.com:
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var turnServer = JSON.parse(xhr.responseText);
                console.log('Got TURN server: ', turnServer);
                pcConfig.iceServers.push({
                    'urls': 'turn:' + turnServer.username + '@' + turnServer.turn,
                    'credential': turnServer.password
                });
                turnReady = true;
            }
        };
        xhr.open('GET', turnURL, true);
        xhr.send();
    }
}



function hangup() {
    console.log('Hanging up.');
    stop();
    sendMessage('bye');
}

function handleRemoteHangup() {
    console.log('Session terminated.');
    stop();
    isInitiator = false;
}

function stop() {
    isStarted = false;
    pc.close();
    pc = null;
}

//pc的事件
function handleIceCandidate(event) {
    console.log('icecandidate event: ', event);
    if (event.candidate) {
        var message = {
            type: 'candidate',
            label: event.candidate.sdpMLineIndex,
            id: event.candidate.sdpMid,
            candidate: event.candidate.candidate
        };
        sendMessage(JSON.stringify(message));
    } else {
        console.log('End of candidates.');
    }
}

function handleRemoteStreamAdded(event) {
    console.log('Remote stream added.');
    remoteStream = event.stream;
    remoteVideo.srcObject = remoteStream;
}

function handleRemoteStreamRemoved(event) {
    console.log('Remote stream removed. Event: ', event);
}



	 