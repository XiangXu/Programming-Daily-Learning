# Cookie vs Session

## Stateless Applications

Web application servers are generally "stateless":

* Each HTTP Request is independent; server cannot tell if 2 requests came from the same browser or user.
* Web server applications maintain no information in memory from request to request.
* Stateless not always convenient for application developers: need to tie together a series of requests from the same user.

## Cookie

**Cookies are name-value pair messages that web servers pass to you web browser when you visit websites**.

Common uses of cookies are authentication, storing of site perference, shopping cart item, and server session identification.

* The first time a browser connects with a particular server, there are no cookies.
* When the server responses it includes a **Set-Cookie**: header that defines a cookie.
* In the future whenever the browser connects with the same server, it includes a **Cookie**: header containing the name and value, which the server can use to connect related requests.
* Data size limited by browsers(typically < 4KB).
* A server can define multiple cookies with different names, but browsers limit the number of cookies per server(around 50).
* Domain of this cookie: server, port(optional), URL prefix(optional). The cookie is only included in requests matching its domain.
* Expiration data: browser can delete old cookies.


Cookies are considered **highly insecure** because the user can easily manipulate their content. That is why you should **always validate cookie data**.

## Session

* A session is a global variable stored on the server.
* Each session is assigned an unique id which is used to retrive stored values.
* Whenever a session is created, a cookie containing the unique session id is stored on the user's computer and returned with every request to the server. If the client browser does not support cookies, the unique session id is displayed in the URL
* Session have capacity to store relatively large data compared cookie.
* The session values are automatically deleted when the browser is closed. If you want to store the value permanently, then you should store them in database.

Reference:

https://www.jianshu.com/p/25802021be63

https://web.stanford.edu/~ouster/cgi-bin/cs142-fall10/lecture.php?topic=cookie

https://www.guru99.com/cookies-and-sessions.html
  


