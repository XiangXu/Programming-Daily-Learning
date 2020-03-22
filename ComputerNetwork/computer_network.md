# Computer Network

## Terms

**LAN**: local area network is a group of computers and network devices connected together, usually within the same building.

**MAN**: metropolitan area network is a larger network that usually spans several buildings in the same city or town.

**WAN**: wide area network, in comparison to a MAN, is not restricted to a geographical location, although it might be confined within the bounds of a state or country. A WAN connects several LANs, and may be limited to an enterprise (a corporation or an organization) or accessible to the public. The technology is high speed and relatively expensive. The Internet is an example of a worldwide public WAN.

**Router**: A router is a networking device that forwards data packets between computer networks. 

**Hub->Bridge->Switch**: A switch is is designed to connect computer within a network.

**Subnet Mask(子网掩码)**: It is a 32 bit number that masks IP address, and divides the IP address into network address and host address. 

**IP**: An Internet Protocol address is also known as IP address. It is a numerical label which assigned to each device connected to a computer network which use the IP for communicaiton. 

**MAC(Media Access Control ) Address**: it is a unique identifier assigned to network interface controller for use as a network address in communicaiton within a network segment.

**DNS(Domain Name System)**: The Domain Name System (DNS) is the phonebook of the Internet. Humans access information online through domain names, like nytimes.com or espn.com. 

**NFS(Network File System)**: it allows a user on a client computer to access files over a computer network much like local storage is accessed. 

**SNMP(Simple Network Management Protocol)****: it is an Internet Standard protocol for collecting and organizing information about managed devices on IP network and for modifying that information to change device behavior. 

**HTTP(Hypertext Transfer Protocol)**: it is a protocol that allows communication between different systems. WWW is about coummunicaiton between web clients and services. Communication between client computers and web server is done by sending HTTP Requests and receving HTTP Response. 

**HTTP(Hypertext Transfer Protocol Secure)**: it is used for secure communication over a computer network. In HTTPS is encrypted using TLS(Transport Layer Security) or, formerly, its predecessor, SSL(Secure Socket Layer). The authentication aspect of HTTPS requires a trusted third party to sign server side digital certificates.




## Data-rate Units

Speed:  
**1kb/s** = 10<sup>3</sup>b/s  
1Mb/s = 10<sup>3</sup>kb/s = 10<sup>6</sup>b/s  
1Gb/s = 10<sup>3</sup>Mb/s = 10<sup>6</sup>kb/s = 10<sup>9</sup>b/s  
1Tb/s = 10<sup>3</sup>Gb/s = 10<sup>6</sup>Mb/s = 10<sup>9</sup>kb/s = 10<sup>12</sup>b/s 

Memory:  
**1KB** = 1024B = 1024 * 8b   
1MB = 2<sup>10</sup>KB = 1024KB  
1GB = 1024MB  
1TB = 1024GB


## OSI and TCP/IP Model

Read: https://zhuanlan.zhihu.com/p/105060370

### OSI Model

| English  	| 中文     	 | 口诀	|
|--------------------	|------------	|----	|
| Application Layer  	| 应用层     	| 用 	|
| Presentation Layer 	| 表示层     	| 试 	|
| Session Layer      	| 会话层     	| 慧 	|
| Transport Layer    	| 传输层     	| 淑 	|
| Network Layer      	| 网络层     	| 网 	|
| Data Link Layer    	| 数据链路层 	 | 联 	|
| Physical Layer     	| 物理层     	| 物 	|

### Application Layer

It supports application and end-user processes. Communication partners are identified, quality of service is identified, user authentication and privacy are considered, and any constraints on data syntax are identified. Everything at this layer is application-specific. This layer provides application services for file transfers, e-mail, and other network software services. Telnet and FTP are applications that exist entirely in the application level. Tiered application architectures are part of this layer.

### Presentation Layer

This layer provides independence from differences in data presentation(e.g., encryption) by translating from application to newtork format, and vice versa(反之亦然). The presentation layer works to tranform data into the form that the application layer can accept. This layer formats and encrypts data to be sent across a network, providing freedom from compability problems. 

### Session Layer

This layer establishes, manages and terminates connections between applications. The session layer sets up, coordinates, and terminates conversations, exchanges, and dialogues between the applications at each end. It deals with session and connection coordination.

### Transport Layer

This layer provides transparent transfer of data between end systems, or hosts, and is responsible for end-to-end error recovery and flow control. It ensures complete data transfer.

### Network Layer

This layer provides switching and routing technoligies, create logical paths, known as virtual circuits, for transmitting data from node to node. Routing and forwarding are functions of this layer, as well as addressing, internetworking, error handling, congestion(拥挤) control and packet sequencing. 

### Data Link Layer

Data packets are encoded and decoded into bits. It furnishes(配备) transmission protocol knowledge and management and handles errors in the physical layer, flow control and frame synchronization. 

The data link layer is divided into two sub layers: The Media Access Control(MAC) layers and Logical Link Control(LLC) layer. 

The MAC sub layer controls how a computer on the network gains access to the data and permission to transmit it. 

The LLC layer controls frame synchronization, flow control and error check. 

### Physical Layer

It provides the hardware means of sending and receiving data on a carrier, including defining cables, cards and physical aspects. 

### TCP/IP Model

| English  	| 中文     	 | 口诀	|
|--------------------	|------------	|----	|
| Application Layer  	| 应用层     	| 用 	|
| Transport Layer    	| 传输层     	| 淑 	|
| Internet Layer      	| 网络层     	| 网 	|
| Network Access / Link Layer    	| 网络接口层 	 | 连 	|


### Application Layer

This Layers performs the functions of top three layers of the OSI model: Application, Presentation, Session Layer. It is responsible for node-to-node communication and controls user-interface specifications. 

### Transport Layer

Same like transport layer of the OSI model.

### Internet Layer

Samoe like network layer of the OSI model

### Network Access Layer

This layer corresponds to the combination of Data Link Layer and Physical Layer of the OSI model.


Difference between TCP/IP and OSI Model:

| TCP/IP                                                                           | OSI                                                 |
|----------------------------------------------------------------------------------|-----------------------------------------------------|
| TCP refers to Transmission Control Protocol.                                     | OSI refers to Open Systems Interconnection          |
| TCP/IP has 4 layers.                                                             | OSI has 7 layers.                                   |
| TCP/IP is more reliable                                                          | OSI is less reliable                                |
| TCP/IP does not have very strict boundaries.                                     | OSI has strict boundaries                           |
| TCP/IP follow a horizontal approach.                                             | OSI follows a vertical approach.                    |
| TCP/IP uses both session and presentation layer in the application layer itself. | OSI uses different session and presentation layers. |
| TCP/IP developed protocols then model.                                           | OSI developed model then protocol.                  |


## TCP/IP protocol three-way handshake and four wave analysis

Read this: https://juejin.im/post/5d9c284b518825095879e7a5

**Sequence number**: Sequence number, which is 32 bits, is used to identify the byte stream sent from the TCP source to the destination. This is marked when the initiator sends data.

**Confirmation serial number**: Ack serial number, which occupies 32 digits. Only when the ACK flag is 1, the confirmation sequence number field is valid, Ack=Seq+1.

**SYN**: Initiate a new connection.

**FIN**: Release a leveling (finish).

**ACK**: The confirmation serial number is valid.

**RST**: reconnect.

**URG**: Urgent pointer is valid.

### Three-way handshake 

The so-called three-way handshake (Three-Way Handshake) means establishing a TCP connection,

Do not confuse the confirmation sequence number Ack with the ACK in the flag bit.

**First handshake**: 

Client sets the flag SYN to 1, randomly generates a value seq=J, and sends the packet to the server. The client enters the **SYN_SENT** state and waits for the server to confirm.

**Second handshake**:

After the server receives the data packet, it is known by the flag bit SYN=1 that the client requests to establish a connection. The server sets the flag bits SYN and ACK to 1, ack=J+1, and randomly generates a value seq = K, and the datagram is sent to the Client to confirm the connection request, and the Server enters the **SYN_RCVD** state.

**Third handshake**:

After the client receives the acknowledgment, it checks whether ack is J+1, whether the ACK is 1, and if it is correct, sets the flag ACK to 1, ack=K+1, and sends the packet. Server, Server checks whether ack is K+1, ACK is 1. If it is correct, the connection is successful, Client and Server enter **ESTABLISHED** state, complete three-way handshake, and then the client and server can start to transfer data.

1. SYN=1, seq=x
2. SYN=1, ACK=1, seq=y, ack=x+1
3. ACK=1, seq=x+1, ack=y+1


### Four-way handshake 

The so-called Four-Way Wavehand terminates the TCP connection.

**The first wave**: The client sends a FIN to close the data transfer from the client to the server, and the client enters the **FIN_WAIT_1** state.

**The second wave**: After the server receives the FIN, it sends an ACK to the client, confirming that the serial number is the received sequence number +1 (same as SYN, one FIN occupies a sequence number), and the server enters the **CLOSE_WAIT** state.

**The third wave**: The server sends a FIN to close the data transfer from the server to the client, and the server enters the **LAST_ACK** state.

**The fourth wave**: After the client receives the FIN, the client enters the TIME_WAIT state, and then sends an ACK to the server, confirming that the serial number is the received sequence number +1, the server enters the CLOSED state, and completes the wave wave four times.

1. FIN=1, seq=u
2. ACK=1, seq=v, ack=u+1
3. FIN=1, ACK=1, seq=w, ack=u+1
4. ACK=1, seq=u+1, ack=w+1


Reference:

https://kb.iu.edu/d/agki

https://www.webopedia.com/quick_ref/OSI_Layers.asp#OSI-5

https://en.wikipedia.org/

https://www.geeksforgeeks.org/tcp-ip-model/

https://www.cloudflare.com/learning/dns/what-is-dns/