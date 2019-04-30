package com.railroad.table.jms;

import com.rabbitmq.client.*;
import com.railroad.table.ejb.TableService;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class Recv {

    @Inject
    private TableService tableService;

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;
    private Consumer consumer;

    public byte[] receiveMessage(String queueName){
        final CountDownLatch countDown = new CountDownLatch(1);
        final AtomicReference<byte[]> result = new AtomicReference<>();
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);
            consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String[] data = new String(body).split("/");
                    if(data[0].equals("init")){
                        result.set(body);

                    }else if(data[0].equals("updateStation")){
                        tableService.updateStations("updateStation", body);
                    }else if(data[0].equals("updateSchedule")){
                        tableService.updateSchedule("updateSchedule", body);
                    }
                    countDown.countDown();

                }
            };
            channel.basicConsume(queueName, true, consumer);
            countDown.await(5, TimeUnit.SECONDS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result.get();
    }

}
