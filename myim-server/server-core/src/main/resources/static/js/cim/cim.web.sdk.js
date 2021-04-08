
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
            alert("提交数据失败！");
        }
    });

    CIM_URI = "ws://" + "127.0.0.1" + ":" + "34567"
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

/****************************************************************************************************************/
'use strict';

/****************************************************************************
 * Initial setup
 ****************************************************************************/

// var configuration = {
//   'iceServers': [{
//     'urls': 'stun:stun.l.google.com:19302'
//   }]
// };

var configuration = null;

// var roomURL = document.getElementById('url');
var video = document.querySelector('video');
var photo = document.getElementById('photo');
var photoContext = photo.getContext('2d');
var trail = document.getElementById('trail');
var snapBtn = document.getElementById('snap');
var sendBtn = document.getElementById('send');
var snapAndSendBtn = document.getElementById('snapAndSend');

var photoContextW;
var photoContextH;

// Attach event handlers
snapBtn.addEventListener('click', snapPhoto);
sendBtn.addEventListener('click', sendPhoto);
snapAndSendBtn.addEventListener('click', snapAndSend);

// Disable send buttons by default.
sendBtn.disabled = true;
snapAndSendBtn.disabled = true;

// Create a random room if not already present in the URL.
var isInitiator;
var room = window.location.hash.substring(1);
if (!room) {
    room = window.location.hash = randomToken();
}


/****************************************************************************
 * Signaling server
 ****************************************************************************/


// socket.on('created', function(room, clientId) {
//     console.log('Created room', room, '- my client ID is', clientId);
//     isInitiator = true;
//     grabWebCamVideo();
// });
//
// socket.on('joined', function(room, clientId) {
//     console.log('This peer has joined room', room, 'with client ID', clientId);
//     isInitiator = false;
//     createPeerConnection(isInitiator, configuration);
//     grabWebCamVideo();
// });
//
// socket.on('full', function(room) {
//     alert('Room ' + room + ' is full. We will create a new room for you.');
//     window.location.hash = '';
//     window.location.reload();
// });
//
// socket.on('ready', function() {
//     console.log('Socket is ready');
//     createPeerConnection(isInitiator, configuration);
// });
//
// socket.on('log', function(array) {
//     console.log.apply(console, array);
// });
//
// socket.on('message', function(message) {
//     console.log('Client received message:', message);
//     signalingMessageCallback(message);
// });
//
// // Joining a room.
// // socket.emit('create or join', room);
//
// if (location.hostname.match(/localhost|127\.0\.0/)) {
//     socket.emit('ipaddr');
// }
//
// // Leaving rooms and disconnecting from peers.
// socket.on('disconnect', function(reason) {
//     console.log(`Disconnected: ${reason}.`);
//     sendBtn.disabled = true;
//     snapAndSendBtn.disabled = true;
// });
//
// socket.on('bye', function(room) {
//     console.log(`Peer leaving room ${room}.`);
//     sendBtn.disabled = true;
//     snapAndSendBtn.disabled = true;
//     // If peer did not create the room, re-enter to be creator.
//     if (!isInitiator) {
//         window.location.reload();
//     }
// });

window.addEventListener('unload', function() {
    console.log(`Unloading window. Notifying peers in ${room}.`);
    socket.emit('bye', room);
});


/**
 * Send message to signaling server
 */
function sendMessage(message) {
    console.log('Client sending message: ', message);
    socket.emit('message', message);
}

/**
 * Updates URL on the page so that users can copy&paste it to their peers.
 */
// function updateRoomURL(ipaddr) {
//   var url;
//   if (!ipaddr) {
//     url = location.href;
//   } else {
//     url = location.protocol + '//' + ipaddr + ':2013/#' + room;
//   }
//   roomURL.innerHTML = url;
// }

/****************************************************************************
 * User media (webcam)
 ****************************************************************************/

function grabWebCamVideo() {
    console.log('Getting user media (video) ...');
    navigator.mediaDevices.getUserMedia({
        audio: false,
        video: true
    })
        .then(gotStream)
        .catch(function(e) {
            alert('getUserMedia() error: ' + e.name);
        });
}

function gotStream(stream) {
    console.log('getUserMedia video stream URL:', stream);
    window.stream = stream; // stream available to console
    video.srcObject = stream;
    video.onloadedmetadata = function() {
        photo.width = photoContextW = video.videoWidth;
        photo.height = photoContextH = video.videoHeight;
        console.log('gotStream with width and height:', photoContextW, photoContextH);
    };
    show(snapBtn);
}

/****************************************************************************
 * WebRTC peer connection and data channel
 ****************************************************************************/

var peerConn;
var dataChannel;

function signalingMessageCallback(message) {
    if (message.type === 'offer') {
        console.log('Got offer. Sending answer to peer.');
        peerConn.setRemoteDescription(new RTCSessionDescription(message), function() {},
            logError);
        peerConn.createAnswer(onLocalSessionCreated, logError);

    } else if (message.type === 'answer') {
        console.log('Got answer.');
        peerConn.setRemoteDescription(new RTCSessionDescription(message), function() {},
            logError);

    } else if (message.type === 'candidate') {
        peerConn.addIceCandidate(new RTCIceCandidate({
            candidate: message.candidate,
            sdpMLineIndex: message.label,
            sdpMid: message.id
        }));

    }
}

function createPeerConnection(isInitiator, config) {
    console.log('Creating Peer connection as initiator?', isInitiator, 'config:',
        config);
    peerConn = new RTCPeerConnection(config);

// send any ice candidates to the other peer
    peerConn.onicecandidate = function(event) {
        console.log('icecandidate event:', event);
        if (event.candidate) {
            sendMessage({
                type: 'candidate',
                label: event.candidate.sdpMLineIndex,
                id: event.candidate.sdpMid,
                candidate: event.candidate.candidate
            });
        } else {
            console.log('End of candidates.');
        }
    };

    if (isInitiator) {
        console.log('Creating Data Channel');
        dataChannel = peerConn.createDataChannel('photos');
        onDataChannelCreated(dataChannel);

        console.log('Creating an offer');
        peerConn.createOffer().then(function(offer) {
            return peerConn.setLocalDescription(offer);
        })
            .then(() => {
            console.log('sending local desc:', peerConn.localDescription);
        sendMessage(peerConn.localDescription);
    })
    .catch(logError);

    } else {
        peerConn.ondatachannel = function(event) {
            console.log('ondatachannel:', event.channel);
            dataChannel = event.channel;
            onDataChannelCreated(dataChannel);
        };
    }
}

function onLocalSessionCreated(desc) {
    console.log('local session created:', desc);
    peerConn.setLocalDescription(desc).then(function() {
        console.log('sending local desc:', peerConn.localDescription);
        sendMessage(peerConn.localDescription);
    }).catch(logError);
}

function onDataChannelCreated(channel) {
    console.log('onDataChannelCreated:', channel);

    channel.onopen = function() {
        console.log('CHANNEL opened!!!');
        sendBtn.disabled = false;
        snapAndSendBtn.disabled = false;
    };

    channel.onclose = function () {
        console.log('Channel closed.');
        sendBtn.disabled = true;
        snapAndSendBtn.disabled = true;
    }

    channel.onmessage = (adapter.browserDetails.browser === 'firefox') ?
        receiveDataFirefoxFactory() : receiveDataChromeFactory();
}

function receiveDataChromeFactory() {
    var buf, count;

    return function onmessage(event) {
        if (typeof event.data === 'string') {
            buf = window.buf = new Uint8ClampedArray(parseInt(event.data));
            count = 0;
            console.log('Expecting a total of ' + buf.byteLength + ' bytes');
            return;
        }

        var data = new Uint8ClampedArray(event.data);
        buf.set(data, count);

        count += data.byteLength;
        console.log('count: ' + count);

        if (count === buf.byteLength) {
// we're done: all data chunks have been received
            console.log('Done. Rendering photo.');
            renderPhoto(buf);
        }
    };
}

function receiveDataFirefoxFactory() {
    var count, total, parts;

    return function onmessage(event) {
        if (typeof event.data === 'string') {
            total = parseInt(event.data);
            parts = [];
            count = 0;
            console.log('Expecting a total of ' + total + ' bytes');
            return;
        }

        parts.push(event.data);
        count += event.data.size;
        console.log('Got ' + event.data.size + ' byte(s), ' + (total - count) +
            ' to go.');

        if (count === total) {
            console.log('Assembling payload');
            var buf = new Uint8ClampedArray(total);
            var compose = function(i, pos) {
                var reader = new FileReader();
                reader.onload = function() {
                    buf.set(new Uint8ClampedArray(this.result), pos);
                    if (i + 1 === parts.length) {
                        console.log('Done. Rendering photo.');
                        renderPhoto(buf);
                    } else {
                        compose(i + 1, pos + this.result.byteLength);
                    }
                };
                reader.readAsArrayBuffer(parts[i]);
            };
            compose(0, 0);
        }
    };
}


/****************************************************************************
 * Aux functions, mostly UI-related
 ****************************************************************************/

function snapPhoto() {
    photoContext.drawImage(video, 0, 0, photo.width, photo.height);
    show(photo, sendBtn);
}

function sendPhoto() {
// Split data channel message in chunks of this byte length.
    var CHUNK_LEN = 64000;
    console.log('width and height ', photoContextW, photoContextH);
    var img = photoContext.getImageData(0, 0, photoContextW, photoContextH),
        len = img.data.byteLength,
        n = len / CHUNK_LEN | 0;

    console.log('Sending a total of ' + len + ' byte(s)');

    if (!dataChannel) {
        logError('Connection has not been initiated. ' +
            'Get two peers in the same room first');
        return;
    } else if (dataChannel.readyState === 'closed') {
        logError('Connection was lost. Peer closed the connection.');
        return;
    }

    dataChannel.send(len);

// split the photo and send in chunks of about 64KB
    for (var i = 0; i < n; i++) {
        var start = i * CHUNK_LEN,
            end = (i + 1) * CHUNK_LEN;
        console.log(start + ' - ' + (end - 1));
        dataChannel.send(img.data.subarray(start, end));
    }

// send the reminder, if any
    if (len % CHUNK_LEN) {
        console.log('last ' + len % CHUNK_LEN + ' byte(s)');
        dataChannel.send(img.data.subarray(n * CHUNK_LEN));
    }
}

function snapAndSend() {
    snapPhoto();
    sendPhoto();
}

function renderPhoto(data) {
    var canvas = document.createElement('canvas');
    canvas.width = photoContextW;
    canvas.height = photoContextH;
    canvas.classList.add('incomingPhoto');
    // trail is the element holding the incoming images
    trail.insertBefore(canvas, trail.firstChild);

    var context = canvas.getContext('2d');
    var img = context.createImageData(photoContextW, photoContextH);
    img.data.set(data);
    context.putImageData(img, 0, 0);
}

function show() {
    Array.prototype.forEach.call(arguments, function(elem) {
        elem.style.display = null;
    });
}

function hide() {
    Array.prototype.forEach.call(arguments, function(elem) {
        elem.style.display = 'none';
    });
}

function randomToken() {
    return Math.floor((1 + Math.random()) * 1e16).toString(16).substring(1);
}

function logError(err) {
    if (!err) return;
    if (typeof err === 'string') {
        console.warn(err);
    } else {
        console.warn(err.toString(), err);
    }
}

	 