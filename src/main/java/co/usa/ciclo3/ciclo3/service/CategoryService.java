package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }
    
    public Category save(Category c){
         if (c.getId()==null){

         
        return categoryRepository.save(c);
        }else{
            Optional<Category> caux=categoryRepository.getCategory(c.getId());
            if(caux.isEmpty()){
                return categoryRepository.save(c); 
            }else{
                return c;
            }
        }
    }
    public Category Update(Category c){
        if (c.getId()!=null){
            Optional<Category> g=categoryRepository.getCategory(c.getId());
            if(!g.isEmpty()){
                if (c.getName()!=null){
                    g.get().setName(c.getName());
                }
                if (c.getDescription()!=null) {
                    g.get().setDescription(c.getDescription());
                }
                categoryRepository.save(g.get());
                return g.get();
            }else{
                return c;
            }
        }else{
            return c;
        }

    }

    public boolean deleteCategory(int categoryId){
        Boolean d =  getCategory(categoryId).map(category ->{
            categoryRepository.delete(category);
            return  true;   
        }).orElse(false);
        return d;
    }
    
}
