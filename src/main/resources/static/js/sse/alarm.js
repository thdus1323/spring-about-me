// // 클라이언트에게 알림
// function initializeClientEventSource() {
//     if (!window.clientEventSource) {
//         const clientEventSource = new EventSource("/clientEmitter");
//
//         clientEventSource.onmessage = (event) => {
//             const div = document.createElement("div");
//             div.textContent = `Event received: ${event.data}`;
//             document.getElementById("clientEvent").appendChild(div);
//         };
//
//         clientEventSource.onerror = (error) => {
//             console.error("Error occurred:", error);
//             clientEventSource.close();
//             // 연결이 끊어졌을 때 재연결 시도 (백오프 전략 적용 가능)
//             setTimeout(() => {
//                 initializeClientEventSource();
//             }, 3000); // 3초 후 재연결 시도
//         };
//
//         window.clientEventSource = clientEventSource;
//     }
// }
//
// initializeClientEventSource();
//
// // 익스퍼트에게 알림
// function initializeExpertEventSource() {
//     if (!window.expertEventSource) {
//         const expertEventSource = new EventSource("/expertEmitter");
//
//         expertEventSource.onmessage = (event) => {
//             const div = document.createElement("div");
//             div.textContent = `Event received: ${event.data}`;
//             document.getElementById("expertEvent").appendChild(div);
//         };
//
//         expertEventSource.onerror = (error) => {
//             console.error("Error occurred:", error);
//             expertEventSource.close();
//             // 연결이 끊어졌을 때 재연결 시도 (백오프 전략 적용 가능)
//             setTimeout(() => {
//                 initializeExpertEventSource();
//             }, 3000); // 3초 후 재연결 시도
//         };
//
//         window.expertEventSource = expertEventSource;
//     }
// }
//
// initializeExpertEventSource();