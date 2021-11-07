package co.usa.ciclo3.ciclo3.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import co.usa.ciclo3.ciclo3.model.Costume;
import co.usa.ciclo3.ciclo3.repository.crud.CostumeCrudRepository;

@Repository
public class CostumeRepository {
    
    @Autowired
    private CostumeCrudRepository costumeCrudRepository;

    public List<Costume> getAll(){
        return (List<Costume>) costumeCrudRepository.findAll();
    }
    public Optional<Costume> getCostume(int id){
        return costumeCrudRepository.findById(id);
    }
    public Costume save(Costume c){
        return costumeCrudRepository.save(c);
    }

    public void delete(Costume c){
        costumeCrudRepository.delete(c);
    }

}
