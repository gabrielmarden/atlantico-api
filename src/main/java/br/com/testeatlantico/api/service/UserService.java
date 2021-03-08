package br.com.testeatlantico.api.service;



import br.com.testeatlantico.api.exceptions.ResourceNotFoundException;
import br.com.testeatlantico.api.model.User;
import br.com.testeatlantico.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> findAll(){
        List<User> users =  userRepository.findAll();
        return users;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found!"));
        return user;
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if(findById(id).equals(null)) throw new ResourceNotFoundException("User not found!");
        userRepository.deleteById(id);
    }

    public List<String> findUserAdminEmail() {
        List<User> users = userRepository.findUserAdmin();
        return users.stream().map(u->u.getEmail()).collect(Collectors.toList());
    }

    public User updateUser(Long id, User user){
        Optional<User> userFound = userRepository.findById(id);

        return userFound.map(record->{
            record.setLogin(user.getLogin());
            record.setEmail(user.getEmail());
            record.setName(user.getName());
            record.setAdmin(user.getAdmin());
            record.setCreatedDate(user.getCreatedDate());
            return userRepository.save(record);
        }).orElseThrow(()-> new ResourceNotFoundException("User not Found!"));
    }
}
