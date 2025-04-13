# A chat APP API implementation
API implementation of a chat based app with realtime websocket implementation.

## Dependencies
* mysql-connector-j
* lombok
* mockito-core
* JPA
* Java JWT

## Spring Security JWT Implementation
*glossary*
### JSON Web Token
>  A string representing a set of claims as a JSON object that is
encoded in a JWS or JWE, enabling the claims to be digitally
signed or MACed and/or encrypted.

### JWT Claims Set
> A JSON object that contains the claims conveyed by the JWT.

### Claim
>  A piece of information asserted about a subject.  A claim is
represented as a name/value pair consisting of a Claim Name and a
Claim Value.

### Claim Name
>The name portion of a claim representation.  A Claim Name is
always a string.

### Claim Value
>The value portion of a claim representation.  A Claim Value can be
any JSON value.

*implementation*

A JWT token is divided into 3 parts namely header, payload, and signature in the format of
>[Header].[Payload].[Signature]
* <b>Header
>Contains the list cryptographic operations that are applied to the JWT.
* Payload
>contains the actual data to be transferred using the token. 
> This part is also known as the "claims" part of the JWT token.
* Signature
> The signature part of the JWT is used for the verification that the message wasn't changed along the way. 
> If the tokens are signed with private key, it also verifies that the sender is who it says it is. It is created using the encoded header, encoded payload, a secret and the algorithm specified in the header.
> 
The token is generated in the server and sent to the client where it is stored in the session storage or local storage.

The JWT includes a secret which we will define in our application.properties

<code>
SECRET_KEY=********************************************************
</code>

set your secret here

The utils/JwtService.java class is responsible for the creation and validation of tokens using io.jsonwebtoken.Jwts.

<code>
@Component <br>
public class JwtService{<br>
public static final long TOKEN_VALIDITY = 10 * 60 * 60;<br>
//Set JwtSecret<br>
private String jwtSecret;<br>
//generateJwtToken method<br>
//validateToken implementation<br>
//getUserName implementation<br>
}
</code>

The utils/JwtAuthenticationFilter.java class is responsible for tracking requests and detect if they contain the valid token in the header.
If the token is valid the request is processed otherwise a 401 error (Unauthorized) is sent.

<code>
@Component<br>
@AllArgsConstructor<br>
public class JwtAuthenticationFilter extends OncePerRequestFilter {<br>
    private final JwtService jwtService;<br>
    private final UserDetailsService userDetailsService;<br>
    @Override<br>
    protected void doFilterInternal(<br>
            @NonNull HttpServletRequest request,<br>
            @NonNull HttpServletResponse response,<br>
            @NonNull FilterChain filterChain<br>
    ) throws ServletException, IOException {<br>
        final String authHeader = request.getHeader("Authorization");<br>
        final String jwt;<br>
        final String userEmail;<br>
///Implementation<br>
}
</code>

The utils/SecurityConfig.java is used for security configuration

<code>
@Configuration<br> 
@EnableWebSecurity<br>
public class WebSecurityConfig {<br>
@Autowired<br>
private JwtAuthenticationEntryPoint authenticationEntryPoint;<br>
@Autowired<br>
private JwtFilter filter;<br>
}

</code>

implement login service

<code>
authenticationManager.authenticate(<br>
                    new UsernamePasswordAuthenticationToken(<br>
                            request.getEmail(),<br>
                            request.getPassword()<br>
                    )<br>
...............<br>
var user = userRepository.findByEmail(request.getEmail())<br>
                    .orElseThrow(() -> new RuntimeException("User not found"));<br>
            var jwtToken = jwtService.generateToken(user);<br>
......................
</code>

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
