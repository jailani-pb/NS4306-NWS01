# Chatting Application

## Server

The chatting application server consists of Three classes.

- **ChatServer.java (Run this class if you want to start the server)**

    1) This class contains the main method where when it is run it will wait for client to connect. It will listen for any connection to port 9101. Change the number if you want to listen connection to other port.

    ![image](https://cloud.githubusercontent.com/assets/16147064/24079570/f1c4ac8c-0cc5-11e7-92a1-853a4b321025.png)

    2) Once a client has connected it will create a thread with SessionInitial object.

    3) Then it will wait for another client to connect.


- **SessionInitial.java**

    1) This class is to handle session when client is initially connected to the server.

    2) The server will send the following options to the client.

    ![image](https://cloud.githubusercontent.com/assets/16147064/24079492/2d00cf30-0cc4-11e7-961c-6e19d864d7f9.png)

    3) If option 1 is selected, it will create a new session and wait for other client to connect.

    4) If option 2 is selected, it will list down all available session.


- **SessionHandler.java**

    1) This class is to handle session when two clients are connected.

    2) If message received from client1 then it will send the message to client2.

    3) If message received from client2 then it will send the message to client1.

## Client

The chatting application client consists of One class.

- **ChatClient.java (Run this class if you want to start the client)**

    1) This class contains the main method where when it is run it will try to connect to a server.

    2) Current implementation will try to connect to server in localhost at port 9101. Change the value for ipaddress and portnumber if your server is hosted in different location.

    ![image](https://cloud.githubusercontent.com/assets/16147064/24079579/1496d992-0cc6-11e7-86e2-1cd2d08384f9.png)

    3) Once connected you will receive options that you can choose.

    ![image](https://cloud.githubusercontent.com/assets/16147064/24079492/2d00cf30-0cc4-11e7-961c-6e19d864d7f9.png)
