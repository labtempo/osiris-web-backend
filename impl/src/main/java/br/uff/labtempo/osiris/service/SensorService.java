package br.uff.labtempo.osiris.service;

import br.uff.labtempo.osiris.mapper.SensorMapper;
import br.uff.labtempo.osiris.model.generator.sensor.SensorGenerator;
import br.uff.labtempo.osiris.model.request.SensorRequest;
import br.uff.labtempo.osiris.model.response.SensorResponse;
import br.uff.labtempo.osiris.repository.SensorRepository;
import br.uff.labtempo.osiris.to.collector.CollectorCoTo;
import br.uff.labtempo.osiris.to.collector.SensorCoTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    private SensorRepository sensorRepository;
    private SensorGenerator sensorGenerator;

    @Autowired
    public SensorService(SensorRepository sensorRepository, SensorGenerator sensorGenerator) {
        this.sensorRepository = sensorRepository;
        this.sensorGenerator = sensorGenerator;
    }

    public SensorResponse getRandom() {
        return SensorMapper.toResponse(this.sensorGenerator.generate());
    }

    public SensorResponse getByCollectorIdAndNetworkId(String networkId, String collectorId, String sensorId) {
        return SensorMapper.toResponse(this.sensorRepository.getByCollectorIdAndNetworkId(networkId, collectorId, sensorId));
    }

    public List<SensorResponse> getAllByCollectorIdAndNetworkId(String networkId, String collectorId) {
        return SensorMapper.toResponse(this.sensorRepository.getAllByCollectorIdAndNetworkId(networkId, collectorId));
    }

    public List<SensorResponse> getAllByNetworkId(String networkId) {
        return SensorMapper.toResponse(this.sensorRepository.getAllByNetworkId(networkId));
    }
}
