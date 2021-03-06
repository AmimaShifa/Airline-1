package airlines.service;

import airlines.model.Client;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Created by winio_000 on 2015-12-13.
 */

@Named
@Transactional
public class ClientServiceImpl implements ClientService {

    private static Logger logger = Logger.getLogger(ClientServiceImpl.class);

    @Inject
    private ClientRepository clientRepository;

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public void delete(long id) {
        clientRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findOne(long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public void update(Long id, Client client) {
        client.setId(id);
        clientRepository.save(client);
    }

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
