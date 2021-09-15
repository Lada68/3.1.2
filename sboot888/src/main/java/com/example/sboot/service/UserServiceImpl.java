package com.example.sboot.service;

import com.example.sboot.model.Role;
import com.example.sboot.model.User;
import com.example.sboot.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUsername(s);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

    }
public List<User> getAll(){
        return (List<User>) userRepository.findAll();
}
@Transactional
public Optional<User> getOne(Integer id){
      return   userRepository.findById(id);
}
@Transactional
public User findById(int id){return userRepository.findById(id);}

    @Override
    public Object findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
public void addNew(User user){
        userRepository.save(user);
    }

public void update(User user){

    userRepository.save(user);

}
public void delete(Integer id){
    System.out.println("service " +id);
        userRepository.deleteById(id);
}
//    public void updateUser(int id, User user) {
//        User userForDB = userRepository.findById(id);
//        userForDB.setId(user.getId());
//        userForDB.setName(user.getName());
//        userForDB.setSurname(user.getSurname());
//        userForDB.setAge(user.getAge());
//        userRepository.save(userForDB);
//    }
}
