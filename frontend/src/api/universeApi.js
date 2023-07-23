import SockJS from 'sockjs-client/dist/sockjs';
import Stomp from 'stompjs';

let stompClient = null;
let resolveFunction = null;


export const connect = () => {
    if (stompClient != null) {
        return;
    }
    const socket = new SockJS(`${import.meta.env.VITE_API_BASE_URL}/ws`);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

const onError = (error) => {
    console.log(error);
}

const onCellsReceived = (payload) => {
    const cells = JSON.parse(payload.body);
    resolveFunction(cells);
}

const onConnected = () => {
    stompClient.subscribe('/topic/universe', onCellsReceived);
}

export const getRandomCells = (width, height) => {
    return new Promise((resolve, reject) => {
        if (!stompClient) {
            return reject(new Error('Not connected'));
        }
        resolveFunction = resolve;
        const dimensions = {width, height};
        stompClient.send("/app/random", {}, JSON.stringify(dimensions));
    });
}

export const getNextGeneration = (width, height, cells) => {
    return new Promise((resolve, reject) => {
        if (!stompClient) {
            return reject(new Error('Not connected'));
        }
        resolveFunction = resolve;
        const dimensions = {width, height};
        stompClient.send("/app/next", {}, JSON.stringify({dimensions, cells}));
    });
}

export const disconnect = () => {
    if (stompClient != null) {
        stompClient.disconnect();
        console.log("Disconnected");
    }
    stompClient = null;
}