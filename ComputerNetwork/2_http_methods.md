# HTTP methods

## The GET Method

**GET** is used to request data from a special resource.

1. GET requests can be cached.
2. GET requests remain in the browser history.
3. GET requests can be bookmarked. 
4. GET requests should never be used when dealing with sensitive data.
5. GET requests has length restrictions. 
6. GET requests are only used to request data. (Not modify)

## The POST Method

**POST** is used to send data to server to create/update a resource.

1. POST requests are never cached.
2. POST requests do not remain in the browser history.
3. POST requests cannot be bookmarked.
4. POST requests have no restrictions on data length.

## The PUT Method

**PUT** is used to send data to server to create/update a resrouce.

The difference between POST and PUT is that PUT requests are idempotent(幂等的). That is, calling the same PUT request multiple times will always produce the same result. In contrast, calling a POST request repeatedly have side effects of creating the same resource multiple times.

## The HEAD Method

**HEAD** is almost identical to GET, but without response. 

In other words, if GET /users returns a list of users, then HEAD /users will make the same request but will not return the list of users.

HEAD requests are useful for checking what a GET request will return before actually making a GET request - like before downloading a large file or response body.


## The DELETE Method

The **DELETE** method deletes the specified resource.

## The OPTIONS Method

The **OPTIONS** method describes the communication options for the target resource.


## Compare GET vs POST

|   	| GET     	 | POST	|
|--------------------	|------------	|----	|
| BACK button/Reload  	| 	Harmless     	| 	Data will be re-submitted (the browser should alert the user that the data are about to be re-submitted) 	|
| Bookmarked 	| Can be bookmarked     	| Cannot be bookmarked 	|
| Cached      	| Can be cached     	| 	Not cached 	|
| Encoding type    	| 	application/x-www-form-urlencoded     	| application/x-www-form-urlencoded or multipart/form-data. Use multipart encoding for binary data 	|
| History      	| Parameters remain in browser history     	| 	Parameters are not saved in browser history 	|
| Restrictions on data length    	| Yes, when sending data, the GET method adds the data to the URL; and the length of a URL is limited (maximum URL length is 2048 characters) 	 | No restrictions 	|
| estrictions on data type     	| Only ASCII characters allowed     	| No restrictions. Binary data is also allowed 	|
| Security     	| GET is less secure compared to POST because data sent is part of the URL Never use GET when sending passwords or other sensitive information!.| POST is a little safer than GET because the parameters are not stored in browser history or in web server logs.	|
| Visibility    	| Data is visible to everyone in the URL     	| Data is not displayed in the URL 	|


The **GET** Method sends http header and data at at same time. The **POST** Method sends header first, when it receives 100 contine from server, it will send data.
(Firefox only send once)


Reference:

https://www.w3schools.com/tags/ref_httpmethods.asp

https://www.cnblogs.com/logsharing/p/8448446.html