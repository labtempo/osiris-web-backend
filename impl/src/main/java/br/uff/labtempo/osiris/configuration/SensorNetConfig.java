package br.uff.labtempo.osiris.configuration;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@PropertySource(value = "classpath:application.properties")
public class SensorNetConfig {

    @Value("${sensornet.hostname:localhost}")
    private String hostname;

    @Value("${sensornet.ip:127.0.0.1}")
    private String ip;

    @Value("${sensornet.port:8090}")
    private int port;

    @Value("${sensornet.username:guest}")
    private String username;

    @Value("${sensornet.password:guest}")
    private String password;

    @Value("${sensornet.messgegroup.collector:omcp://collector.messagegroup/}")
    private String collectorMessageGroupUri;

    @Value("${sensornet.uri:omcp://sensornet.osiris/}")
    private String moduleUri;

    @Value("${sensornet.uri.networks:omcp://sensornet.osiris/}")
    private String networksUri;

    @Value("${sensornet.uri.networkId:omcp://sensornet.osiris/%1$s/}")
    private String networkIdUri;

    @Value("${sensornet.uri.networkId.collectors:omcp://sensornet.osiris/{%1$s}/collector/}")
    private String networkIdCollectorsUri;

    @Value("${sensornet.uri.networkId.sensors:omcp://sensornet.osiris/{%1$s}/sensor/}")
    private String networkIdSensorsUri;

    @Value("${sensornet.uri.networkId.collectorId:omcp://sensornet.osiris/{%1$s}/collector/{%2$s}/}")
    private String networkIdCollectorIdUri;

    @Value("${sensornet.uri.networkId.collectorId.sensors:omcp://sensornet.osiris/{%1$s}/collector/{%2$s}/sensor/}")
    private String networkIdCollectorIdSensorsUri;

    @Value("${sensornet.uri.networkId.collectorId.sensorId:omcp://sensornet.osiris/{%1$s}/collector/{%2$s}/sensor/{%3$s}/}")
    private String networkIdCollectorIdSensorIdUri;
}
