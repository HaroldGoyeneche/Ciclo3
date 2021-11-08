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
    
    public Category save(Category category){
         if (category.getId()==null){

         
        return categoryRepository.save(category);
        }else{
            Optional<Category> caux=categoryRepository.getCategory(category.getId());
            if(caux.isEmpty()){
                return categoryRepository.save(category); 
            }else{
                return category;
            }
        }
    }
    public Category update(Category category){
        if (category.getId()!=null){
            Optional<Category> caux=categoryRepository.getCategory(category.getId());
            if(!caux.isEmpty()){
                if (category.getName()!=null){
                    caux.get().setName(category.getName());
                }
                if (category.getDescription()!=null) {
                    caux.get().setDescription(category.getDescription());
                }
                categoryRepository.save(caux.get());
                return caux.get();
            }else{
                return category;
            }
        }else{
            return category;
        }

    }

    public boolean deleteCategory(int categoryId){
        Boolean catboolean =  getCategory(categoryId).map(category ->{
            categoryRepository.delete(category);
            return  true;   
        }).orElse(false);
        return catboolean;
    }
    
}
