/**
 *                             Vortex Cafe
 *
 *    This software and documentation are Copyright 2010 to 2015 PrismTech
 *    Limited and its licensees. All rights reserved. See file:
 *
 *                           docs/LICENSE.html
 *
 *    for full copyright notice and license terms.
 */
package helloworld;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.Durability;
import org.omg.dds.core.policy.Partition;
import org.omg.dds.core.policy.PolicyFactory;
import org.omg.dds.core.policy.Reliability;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.Publisher;
import org.omg.dds.topic.Topic;
import SensorData.Msg;

public class HelloWorldPublisher
{
   public static void main(String[] args)
   {
      // Set "serviceClassName" property to Vortex Cafe implementation
      System.setProperty(ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY,
            "com.prismtech.cafe.core.ServiceEnvironmentImpl");

      // Instantiate a DDS ServiceEnvironment
      ServiceEnvironment env = ServiceEnvironment.createInstance(
            HelloWorldPublisher.class.getClassLoader());

      // Get the DomainParticipantFactory
      DomainParticipantFactory dpf = DomainParticipantFactory.getInstance(env);

      // Create a DomainParticipant with domainID=0
      DomainParticipant p = dpf.createParticipant(0);

      // Create a Topic named "SensorData_Msg" and with "HelloWorldData.Msg" as a type.
      Topic<Msg> topic = p.createTopic("SensorData_Msg", Msg.class);

      // Create a Partition QoS with "HelloWorld example" as partition.
      Partition partition = PolicyFactory.getPolicyFactory(env)
            .Partition().withName("HelloWorld example");

      // Create a Subscriber using default QoS except partition
      Publisher pub = p.createPublisher(p.getDefaultPublisherQos().withPolicy(partition));

      // Create Reliability and Durability QoS
      Reliability r = PolicyFactory.getPolicyFactory(env).Reliability().withReliable();
      Durability d = PolicyFactory.getPolicyFactory(env).Durability().withTransient();

      // Create DataReader on our topic with default QoS except Reliability and Durability
      DataWriter<Msg> writer = pub.createDataWriter(topic,
            pub.getDefaultDataWriterQos().withPolicies(r, d));
      
      
  	String userId = args[0];
      // The message we want to publish
      double temperature;
      double power;
      double vibrations;
      double amplitudeRatio;
      String time;
		while (true) {
			 temperature = (Math.random() * (99 - (-99)) + (-99));
			 power = (Math.random() * (1000 - (1)) + (1));
			 vibrations = (Math.random() * (1000 - (1)) + (1));
			 amplitudeRatio = (Math.random() * (1000 - (1)) + (1));
			 DecimalFormat df = new DecimalFormat("#.00");
			 
			 Date date = new Date();
			 time  = date.toString();
			Msg msg = new Msg(userId, temperature,power, vibrations, amplitudeRatio, time);
			try {
				System.out
						.println(" ________________________________________________________________");
				System.out.println("|");
				System.out.println("| Sending message!! Publisher: " + userId + " at: "
						+ time
						+ " Temperature (degree Celcius) "
						+ df.format(temperature)
						+ " Power (Watts) "  
						+ df.format(power)
						+ " Vibrations (Hertz) "
						+ df.format(vibrations)
						+ " Amplitude Ratio (dB) "
						+ df.format(amplitudeRatio));
				System.out
						.println("|________________________________________________________________");
				System.out.println("");

				// Publish the message
				writer.write(msg);
			} catch (TimeoutException e) {
				// TimeoutException may happen using Reliable QoS (if
				// publication buffers are full)
				e.printStackTrace();
			}

			try {
				// Wait to ensure data is received before we delete writer
				Thread.sleep(10000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
      // Close Participant (closing also chlidren entities: Topic, Publisher, DataWriter)
      //p.close();

   }
}

