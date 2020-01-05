package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.detail.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setFull_name(createUserRequest.getFullName());
        user.setNumberphone(createUserRequest.getNumberphone());
        user.setPassword(createUserRequest.getPassword());
        user.setAvatar(createUserRequest.getAvatar());
        user.setBirthday(createUserRequest.getBirthday());
//        user.setTotal_cash(createUserRequest.getTotal_cash());
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(CreateUserRequest createUserRequest, int id) {
        User user = userRepository.findById(id).get();
        user.setEmail(createUserRequest.getEmail());
        user.setFull_name(createUserRequest.getFullName());
        user.setNumberphone(createUserRequest.getNumberphone());
        user.setPassword(createUserRequest.getPassword());
        user.setAvatar(createUserRequest.getAvatar());
        user.setBirthday(createUserRequest.getBirthday());
//        user.setTotal_cash(createUserRequest.getTotal_cash());
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto deleteUser(int id) {

        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return UserMapper.toUserDto(user);

    }

    @Override
    public UserDto getUserById(int id) {
        UserDto userDto = null;
        try {
            User user = userRepository.findById(id).get();
            userDto = UserMapper.toUserDto(user);
        }catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());

        }


        return userDto;
    }

    @Override
    public List<User> getAllUser() {
        List<UserDto> userDtos = null;

//        try{
//            List<User> users = userRepository.findAll();
//
//            for (int i = 0; i< users.size();i++){
//                userDtos.add(UserMapper.toUserDto(users.get(i)));
//            }
//        }catch (Exception e) {
//            System.out.println("ERROR: " +e.getMessage());
//
//        }

//        return userDtos;
        return userRepository.findAll();
    }
}
