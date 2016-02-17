

====
                                Demo for Embedded World:

       I changed the Original HelloWorld example application to use TCP and to use the Vortex Cloud for message passing as a Demo for embedded world. 
       Apologies to the colleagues for having made it so dirty!. 
====

Description
-----------
HelloWorld example demonstrates a simple usage of DDS.
The HelloWorldPublisher class publishes a "SensorData" message on a "SensorData_Msg" Topic.
The HelloWorldSubscriber class subscribes to this Topic and display the received messages.

Building the demo
-----------------
Open a terminal in the helloworld/ directory.

make -f Make_Subscriber

make -f Make_Publisher 

If you want logging messages, put slf4j-simple-1.7.14.jar(downloading from slf4j.com) and needs to be put in the ClassPath.


Running the demo
----------------
Open a command prompt and change directory to "helloworld".
Enter the following command to start the HelloWorldSubscriber:
1. start Subscriber first:
  ./ddssubscriber-demo_linux-x86_64 
   
2. start Publishers  after:
  ./ddspublisher-demo_linux-x86_64 Karlsruhe
 

You can start as many publishers as you want. The subscriber keeps checking for incoming messages every 10 seconds while the publishers send messages every 100 seconds.

To terminate safely, kill subscriber first and then the publisher.