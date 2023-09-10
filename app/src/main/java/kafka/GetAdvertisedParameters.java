package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.Node;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GetAdvertisedParameters {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Kafka broker configuration
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Replace with your broker address

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            // Use the consumer to create an AdminClient
            try (AdminClient adminClient = AdminClient.create(properties)) {
                // Describe the Kafka cluster
                DescribeClusterResult clusterInfo = adminClient.describeCluster();

                // Get the controller node (the leader broker)
                Node controller = clusterInfo.controller().get();

                System.out.println("Controller Broker Host: " + controller.host());
                System.out.println("Controller Broker Port: " + controller.port());
            }
        }
    }
}

