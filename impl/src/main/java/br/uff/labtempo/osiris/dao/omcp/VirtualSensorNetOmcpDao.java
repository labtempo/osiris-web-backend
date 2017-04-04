package br.uff.labtempo.osiris.dao.omcp;

import br.uff.labtempo.omcp.common.Response;
import br.uff.labtempo.omcp.common.exceptions.*;
import br.uff.labtempo.omcp.common.exceptions.client.AbstractClientRuntimeException;
import br.uff.labtempo.osiris.connection.SensorNetConnection;
import br.uff.labtempo.osiris.connection.VirtualSensorNetConnection;
import br.uff.labtempo.osiris.repository.OmcpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component("virtualSensorNetOmcpDao")
public class VirtualSensorNetOmcpDao<T> implements OmcpRepository<T> {

    private VirtualSensorNetConnection virtualSensorNetConnection;

    @Autowired
    public VirtualSensorNetOmcpDao(VirtualSensorNetConnection virtualSensorNetConnection) {
        this.virtualSensorNetConnection = virtualSensorNetConnection;
    }

    public T doGet(String uri) throws AbstractRequestException, AbstractClientRuntimeException {
        Response omcpResponse = this.virtualSensorNetConnection.getConnection().doGet(uri);
        switch(omcpResponse.getStatusCode()) {
            case BAD_REQUEST:
                throw new BadRequestException();
            case FORBIDDEN:
                throw new ForbiddenException();
            case INTERNAL_SERVER_ERROR:
                break;
            case METHOD_NOT_ALLOWED:
                throw new MethodNotAllowedException();
            case NOT_FOUND:
                throw new NotFoundException();
            case NOT_IMPLEMENTED:
                throw new NotImplementedException();
            case REQUEST_TIMEOUT:
                throw new RequestTimeoutException();
        }
        T content = (T) omcpResponse.getContent();
        if(content == null) {
            throw new NotFoundException();
        }
        return content;
    }

    public URI doPost(String uri, T t) throws AbstractRequestException, AbstractClientRuntimeException, URISyntaxException {
        Response omcpResponse = this.virtualSensorNetConnection.getConnection().doPost(uri, t);
        switch(omcpResponse.getStatusCode()) {
            case BAD_REQUEST:
                throw new BadRequestException();
            case FORBIDDEN:
                throw new ForbiddenException();
            case INTERNAL_SERVER_ERROR:
                break;
            case METHOD_NOT_ALLOWED:
                throw new MethodNotAllowedException();
            case NOT_FOUND:
                throw new NotFoundException();
            case NOT_IMPLEMENTED:
                throw new NotImplementedException();
            case REQUEST_TIMEOUT:
                throw new RequestTimeoutException();
        }
        return new URI(omcpResponse.getLocation());
    }

    public void doPut(String uri, T t) throws AbstractRequestException, AbstractClientRuntimeException {
        Response omcpResponse = this.virtualSensorNetConnection.getConnection().doPut(uri, t);
        switch(omcpResponse.getStatusCode()) {
            case BAD_REQUEST:
                throw new BadRequestException();
            case FORBIDDEN:
                throw new ForbiddenException();
            case INTERNAL_SERVER_ERROR:
                break;
            case METHOD_NOT_ALLOWED:
                throw new MethodNotAllowedException();
            case NOT_FOUND:
                throw new NotFoundException();
            case NOT_IMPLEMENTED:
                throw new NotImplementedException();
            case REQUEST_TIMEOUT:
                throw new RequestTimeoutException();
        }
    }

    public void doDelete(String uri) throws AbstractRequestException, AbstractClientRuntimeException {
        Response omcpResponse = this.virtualSensorNetConnection.getConnection().doDelete(uri);
        switch(omcpResponse.getStatusCode()) {
            case BAD_REQUEST:
                throw new BadRequestException();
            case FORBIDDEN:
                throw new ForbiddenException();
            case INTERNAL_SERVER_ERROR:
                break;
            case METHOD_NOT_ALLOWED:
                throw new MethodNotAllowedException();
            case NOT_FOUND:
                throw new NotFoundException();
            case NOT_IMPLEMENTED:
                throw new NotImplementedException();
            case REQUEST_TIMEOUT:
                throw new RequestTimeoutException();
        }
    }

    public void doNotify(String uri, T t) throws AbstractRequestException, AbstractClientRuntimeException {
        this.virtualSensorNetConnection.getConnection().doNofity(uri, t);
    }
}