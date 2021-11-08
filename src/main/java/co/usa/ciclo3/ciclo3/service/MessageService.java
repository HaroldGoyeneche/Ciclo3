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
    
    public Message save(Message message){
         if (message.getIdMessage()==null){

         
        return messageRepository.save(message);
        }else{
            Optional<Message> caux=messageRepository.getMessage(message.getIdMessage());
            if(caux.isEmpty()){
                return messageRepository.save(message); 
            }else{
                return message;
            }
        }
    }
    public Message update(Message message){
        if (message.getIdMessage()!=null){
            Optional<Message> maux=messageRepository.getMessage(message.getIdMessage());
            if(!maux.isEmpty()){
                if (message.getMessageText()!=null){
                    maux.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(maux.get());
                return maux.get();
            }else{
                return message;
            }
        }else{
            return message;
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
