package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int idMessage){
        return messageRepository.getMessage(idMessage);
    }
    
    public Message save(Message m){
         if (m.getIdMessage()==null){

         
        return messageRepository.save(m);
        }else{
            Optional<Message> caux=messageRepository.getMessage(m.getIdMessage());
            if(caux.isEmpty()){
                return messageRepository.save(m); 
            }else{
                return m;
            }
        }
    }
    public Message Update(Message m){
        if (m.getIdMessage()!=null){
            Optional<Message> e=messageRepository.getMessage(m.getIdMessage());
            if(!e.isEmpty()){
                if (m.getMessageText()!=null){
                    e.get().setMessageText(m.getMessageText());
                }
                messageRepository.save(e.get());
                return e.get();
            }else{
                return m;
            }
        }else{
            return m;
        }

    }

    public boolean deleteMessage(int messageId){
        Boolean aBoolean =  getMessage(messageId).map(message ->{
            messageRepository.delete(message);
            return  true;
        }).orElse(false);
        return aBoolean;
    }
    
}
