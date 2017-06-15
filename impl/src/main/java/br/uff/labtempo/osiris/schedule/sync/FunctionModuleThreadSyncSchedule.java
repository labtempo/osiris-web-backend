package br.uff.labtempo.osiris.schedule.sync;

import br.uff.labtempo.omcp.common.exceptions.AbstractRequestException;
import br.uff.labtempo.osiris.factory.function.FunctionModuleFactory;
import br.uff.labtempo.osiris.generator.link.LinkGenerator;
import br.uff.labtempo.osiris.model.domain.function.FunctionData;
import br.uff.labtempo.osiris.model.domain.function.FunctionModule;
import br.uff.labtempo.osiris.repository.*;
import br.uff.labtempo.osiris.to.common.data.ValueTo;
import br.uff.labtempo.osiris.to.sensornet.NetworkSnTo;
import br.uff.labtempo.osiris.to.sensornet.SensorSnTo;
import br.uff.labtempo.osiris.to.virtualsensornet.DataTypeVsnTo;
import br.uff.labtempo.osiris.to.virtualsensornet.LinkVsnTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Handle Function Module threads (OmcpServers) in runtime
 * @author andre.ghigo
 * @since 1.8
 * @version 1.0
 */
@EnableScheduling
@Service
@Slf4j
@Profile("virtualsensornet_sync_schedule")
public class FunctionModuleThreadSyncSchedule {

    @Autowired
    private FunctionDataRepository functionDataRepository;

    @Autowired
    private FunctionModuleFactory functionModuleFactory;

    private Map<FunctionModule, Thread> functionModuleThreadMap = new HashMap<>();

    /**
     * Synchronize Function data on Osiris Web with Function Modules on OSIRIS Framework
     * Checks every function on OSIRIS Web Database and check if its running or not.
     * If not running, starts the corresponding OmcpServer
     * If a running Function module does not exist on Osiris web database, stop the function module
     * because it was removed by another service.
     * @throws Exception
     */
    @Scheduled(cron = "${sensornet.schedule.sync.function.cron:*/2 * * * * ?}")
    public void handleFunctionModuleThreadsFromFunctionData() throws Exception {
        Iterable<FunctionData> functionDataInterable = this.functionDataRepository.findAll();
        Iterator<FunctionData> functionDataIterator = functionDataInterable.iterator();
        while(functionDataIterator.hasNext()) {
            FunctionData functionData = functionDataIterator.next();
            boolean found = false;
            for(FunctionModule module : this.functionModuleThreadMap.keySet()) {
                if(module.getFunctionData().getName().equals(functionData.getName())) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                FunctionModule newModule = this.functionModuleFactory.getInstance(functionData.getOperationName(), functionData.getDescription(), functionData.getImplementation(), functionData.getDataTypeId());
                Thread functionThread = new Thread(newModule, newModule.getFunctionData().getName());
                this.functionModuleThreadMap.put(newModule, functionThread);
                this.functionModuleThreadMap.get(newModule).start();
                log.info(String.format("New thread started for function module with name '%s'", newModule.getFunctionData().getName()));
            }
        }
    }
}
