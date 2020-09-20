package ru.advancedtraining;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Krylov Sergey (13.08.2020)
 */
public class ProducerDemoWithCallback {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemoWithCallback.class);

	public static void main(String[] args) {
		// create Producer properties
		var bootstrapServers = "127.0.0.1:9092";

		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		LOGGER.info("Create Producer properties");

		// create the producer
		// key, value
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		LOGGER.info("Create the producer");

		// create a producer record
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "hello world");
		LOGGER.info("Create a producer record");

		// send data - asynchronous
		producer.send(record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				// executes every time a record is successfully send or an exception is thrown
				if (exception != null) {
					// the record is successfully send
					metadata.
				} else {

				}
			}
		});
		LOGGER.info("Send data");

		// flush data
		producer.flush();

		// close producer
		producer.close();
	}
}
