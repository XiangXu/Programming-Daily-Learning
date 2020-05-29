# Local Storage vs Session Storage vs Cookie

**localStorage** and **sessionStorage** both extend Storage. 

## LocalStorage

* Store data with no expireation date, and gets cleared only through JavaScript, or clearing the Browser cache / Locally Stored Data.
* Storage limit is the maximum amongest three(10 MB per origin in Google ChromeMozilla Firefox, and Opera; 10 MB per storage area in Internet Explorer).
* Data is never transferred to server.

## SessionStorage

* The sessionStorage object stores data only for a session, meaning that the data is stored until the browser(or tab) is closed.
* Storage limit is larger than a cookie. 
* Data is never transferred to server.

## Cookie

* Stores data that has to be sent back to the server with subsequent requests. 
* Its expiration varies based on the type and the expirationd duration can be set from either from server-side or client-side(normally from server-side).
* Cookies are primarily for server-side reading(can also be read on client-side).
* Size must be less than 4KB.
* Cookies can be made more secure by setting the httpOnly falg as true for that cookie. This prevents client-side access to that cookie.

Reference:

https://scotch.io/@PratyushB/local-storage-vs-session-storage-vs-cookie

https://stackoverflow.com/questions/5523140/html5-local-storage-vs-session-storage?answertab=votes#tab-top