package airlines.service;

import airlines.model.Client;

/**
 * Created by winio_000 on 2015-12-13.
 */
public interface ClientService {

    void deleteAll();

    void delete(Client client);

    void delete(long id);

    void save(Client client);

    Iterable<Client> findAll();

    Client findOne(long id);

    void update(Long id, Client client);
}
