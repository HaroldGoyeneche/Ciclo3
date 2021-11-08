package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int idClient){
        return clientRepository.getClient(idClient);
    }
    
    public Client save(Client client){
         if (client.getIdClient()==null){

         
        return clientRepository.save(client);
        }else{
            Optional<Client> caux=clientRepository.getClient(client.getIdClient());
            if(caux.isEmpty()){
                return clientRepository.save(client); 
            }else{
                return client;
            }
        }
    }
    public Client update(Client client){
        if (client.getIdClient()!=null){
            Optional<Client> caux=clientRepository.getClient(client.getIdClient());
            if(!caux.isEmpty()){
                if (client.getName()!=null){
                    caux.get().setName(client.getName());
                }
                if (client.getPassword()!=null) {
                    caux.get().setPassword(client.getPassword());
                }
                if (client.getAge()!=null) {
                    caux.get().setAge(client.getAge());
                }
                clientRepository.save(caux.get());
                return caux.get();
            }else{
                return client;
            }
        }else{
            return client;
        }

    }

    public boolean deleteClient(int clientId){
        Boolean aBoolean =  getClient(clientId).map(costume ->{
            clientRepository.delete(costume);
            return  true;
        }).orElse(false);
        return aBoolean;
    }
    
}
