# NetworkSockets
Network assignments 2a


# Instructions to run

You need 4 terminals to show 3 user interaction.

cd into the ServerApp directory and run the following
	make
	make run
Then follow the server prompts to setup the Socket to listen on

Next 3 terminals must cd into ClientApp directory and run
	make
	make run

Each client must be given a name. The you must point to the server IP (just use localhost for demonstration) and then the same socket as the server

Once all users are connected and you would like to send an image do the following

Make sure the image is in the same directory as the makefile. in user client window type
	:send <filename> 
Then on the other client side you can accept or reject by using
	:Y
	or
	:N

To quit the clients type
	:q
	or
	:quit