package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Costume;
import co.usa.ciclo3.ciclo3.repository.CostumeRepository;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id) {
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume c) {
        if (c.getId() == null) {

            return costumeRepository.save(c);
        } else {
            Optional<Costume> caux = costumeRepository.getCostume(c.getId());
            if (caux.isEmpty()) {
                return costumeRepository.save(c);
            } else {
                return c;
            }
        }
    }

    public Costume Update(Costume c){
        if (c.getId()!=null){
            Optional<Costume> e=costumeRepository.getCostume(c.getId());
            if(!e.isEmpty()){
                if (c.getName()!=null){
                    e.get().setName(c.getName());
                }
                if (c.getBrand()!=null) {
                    e.get().setBrand(c.getBrand());
                }
                if (c.getYear()!=null) {
                    e.get().setYear(c.getYear());
                }
                if (c.getCategory()!=null) {
                    e.get().setCategory(c.getCategory());
                }
                costumeRepository.save(e.get());
                return e.get();
            }else{
                return c;
            }
        }else{
            return c;
        }

    }

    public boolean deleteCostume(int costumeId){
        Boolean aBoolean =  getCostume(costumeId).map(costume ->{
            costumeRepository.delete(costume);
            return  true;
        }).orElse(false);
        return aBoolean;
    }
}
