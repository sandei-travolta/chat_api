# A chat APP API implementation
API implementation of a chat based app with realtime websocket implementation.

## Dependencies
* mysql-connector-j
* lombok
* mockito-core
* JPA

## HTTP WEBSOCKETS COMPARISON
<p>
HTTP and WebSocket are both communication protocols used in client-server communication
</p>
<p>
HTTP is unidirectional where the client sends the request and the server sends the response.
each request is associated with a corresponding response, and after sending the response the connection gets closed, each HTTP or HTTPS request establish a new connection to the server every time and after getting the response the connection gets terminated by itself. 
HTTP is a stateless protocol that runs on top of TCP which is a connection-oriented protocol it guarantees the delivery of data packet transfer using the three-way handshaking methods and re-transmits the lost packets.
</p>
<p>
WebSockets on the other hand is bidirectional, a full-duplex protocol and is a stateful protocol, which means the connection between client and server will stay alive until it gets terminated by either party (client or server). After closing the connection by either of the client or server, the connection is terminated from both ends. 
The client-server makes the handshaking and decides to create a new connection and this connection will keep alive until terminated by any of them. When the connection is established and alive the communication takes place using the same connection channel until it is terminated. 
</p>
