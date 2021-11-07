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
    
    public Client save(Client c){
         if (c.getIdClient()==null){

         
        return clientRepository.save(c);
        }else{
            Optional<Client> caux=clientRepository.getClient(c.getIdClient());
            if(caux.isEmpty()){
                return clientRepository.save(c); 
            }else{
                return c;
            }
        }
    }
    public Client Update(Client c){
        if (c.getIdClient()!=null){
            Optional<Client> e=clientRepository.getClient(c.getIdClient());
            if(!e.isEmpty()){
                if (c.getName()!=null){
                    e.get().setName(c.getName());
                }
                if (c.getPassword()!=null) {
                    e.get().setPassword(c.getPassword());
                }
                if (c.getAge()!=null) {
                    e.get().setAge(c.getAge());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return c;
            }
        }else{
            return c;
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
