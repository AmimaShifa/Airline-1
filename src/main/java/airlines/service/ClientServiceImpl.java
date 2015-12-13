package airlines.service;

import airlines.model.Client;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Created by winio_000 on 2015-12-13.
 */

@Named
@Transactional
public class ClientServiceImpl implements ClientService {

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

    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
