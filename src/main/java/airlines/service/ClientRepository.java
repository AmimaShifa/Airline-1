package airlines.service;

import airlines.model.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by winio_000 on 2015-12-13.
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

}
