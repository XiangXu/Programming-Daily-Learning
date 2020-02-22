# Computer Network

## Terms

**LAN**: local area network is a group of computers and network devices connected together, usually within the same building.

**MAN**: metropolitan area network is a larger network that usually spans several buildings in the same city or town.

**WAN**: wide area network, in comparison to a MAN, is not restricted to a geographical location, although it might be confined within the bounds of a state or country. A WAN connects several LANs, and may be limited to an enterprise (a corporation or an organization) or accessible to the public. The technology is high speed and relatively expensive. The Internet is an example of a worldwide public WAN.

**Router**: A router is a networking device that forwards data packets between computer networks. 

**Hub->Bridge->Switch**: A switch is is designed to connect computer within a network.

**IP**: An Internet Protocol address is also known as IP address. It is a numerical label which assigned to each device connected to a computer network which use the IP for communicaiton. 

**NFS(Network File System)**: it allows a user on a client computer to access files over a computer network much like local storage is accessed. 

**SNMP(Simple Network Management Protocol)****: it is an Internet Standard protocol for collecting and organizing information about managed devices on IP network and for modifying that information to change device behavior. 

**HTTP(Hypertext Transfer Protocol)**: it is a protocol that allows communication between different systems. WWW is about coummunicaiton between web clients and services. Communication between client computers and web server is done by sending HTTP Requests and receving HTTP Response. 

**HTTP(Hypertext Transfer Protocol Secure)**: it is used for secure communication over a computer network. In HTTPS is encrypted using TLS(Transport Layer Security) or, formerly, its predecessor, SSL(Secure Socket Layer). The authentication aspect of HTTPS requires a trusted third party to sign server side digital certificates.




## Data-rate Units

Speed:  
**1kb/s** = $10^3$b/s  
1Mb/s = $10^3$kb/s = $10^6$b/s  
1Gb/s = $10^3$Mb/s = $10^6$kb/s = $10^9$b/s  
1Tb/s = $10^3$Gb/s = $10^6$Mb/s = $10^9$kb/s = 10<sup>12</sup>b/s 

Memory:  
**1KB** = 1024B = 1024 * 8b   
1MB = 2<sup>10</sup>KB = 1024KB  
1GB = 1024MB  
1TB = 1024GB


## OSI and TCP/IP Model

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







Reference:

https://kb.iu.edu/d/agki

https://www.webopedia.com/quick_ref/OSI_Layers.asp#OSI-5

https://en.wikipedia.org/