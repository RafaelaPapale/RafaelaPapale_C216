package br.inatel.labs.labmqtt.client;

import org.eclipse.paho.client.mqttv3.*;

import java.util.Random;
import java.util.UUID;

public class SensorTemperaturaPublisher {
    public static void main(String[] args) throws MqttException {
        // Criar o publisher
        String publisherId = UUID.randomUUID().toString();
        IMqttClient publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);

        // Cria a mensagem
        MqttMessage msg = getTemperaturaSolo();
        msg.setQos(0);
        msg.setRetained(true);

        // Conecta
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);

        // Publica
        publisher.publish(MyConstants.TOPIC_1, msg);

        // Desconecta
        publisher.disconnect();
    }

    private static MqttMessage getTemperaturaSolo() {
        Random r = new Random();
        double temperatura = 80 + r.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f", temperatura).getBytes();
        return new MqttMessage(payload);
    }
}
