# java-movie-title

For use:
- You need a client application to connect with server.
- You need to have a internet connection.

How it works:
- You need to connect with the server by IP and PORT. After connect, you're able to send your request.
- Request exemple: queryLength:query
- The response will follow the request exemple.


For developers:
- This application was developed using Java 8 and Gradle. You can import this application as a Gradle Project.

How was developed:
- It was created a SocketRunnable that contains the main method. The SocketRunnable create the SocketServer and when recieve a connection, create a new Thread thats create a new ClientHandle instance.
- The ClientHandle responsability is to get the request, process and build the response. This class implements Runnable to be used on a Thread.
- The ProtocolHandler class will process the request, build a Protocol object to be used on APIProxy. This class also build the response.
- The APIProxy class will send the request to the OMDBAPI with the APIKEY and T parameter (to search by title). The class also have a method to build a list of strings to be used on ProtocolHandler.



OMDBAPI - http://www.omdbapi.com/
