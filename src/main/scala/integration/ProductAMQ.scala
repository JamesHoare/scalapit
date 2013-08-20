package integration

import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.{ConnectionFactory, TextMessage, DeliveryMode, Session}
import scala.util.Random

/**
 *
 * User: jameshoare
 * Date: 19/08/2013
 * Project: scalapit
 *
 */
object ProductAMQ extends App {


  val connectionFactory: ConnectionFactory = new ActiveMQConnectionFactory("vm://localhost")
  val connection = connectionFactory.createConnection
  connection.setClientID("ProducerSynchronous")
  connection.start

  val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
  val sendQueue = session.createQueue("SendSynchronousMsgQueue")
  val replyQueue = session.createQueue("ReplyToSynchronousMsgQueue")

  val producer = session.createProducer(sendQueue)
  producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)

  val correlationId = new Random().nextString(32)
  val replyConsumer = session.createConsumer(replyQueue, "JMSCorrelationID = '%s'".format(correlationId))

  val textMessage = session.createTextMessage("Hello, please reply immediately to my message!")
  textMessage.setJMSReplyTo(replyQueue)
  textMessage.setJMSCorrelationID(correlationId)

  println("Sending message...")

  producer.send(textMessage)

  println("Waiting for reply...")

  val reply = replyConsumer.receive(1000)
  replyConsumer.close()

  reply match {
    case txt: TextMessage => println("Received reply: " + txt.getText())
    case _ => throw new Exception("Invalid Response:" + reply)
  }

  connection.close


}
