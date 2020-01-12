package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.detail.ListUserDto;
import com.example.demo.model.detail.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.ultil.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

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
        user.setRoles(createUserRequest.getRole());
        //neu khi tao, nguoi dung k yeu cau gi, thi yeu cau thanh store se coi nhu k co
        if(createUserRequest.getStatus()==null){
            user.setStatus(false);
        }else {
            user.setStatus(createUserRequest.getStatus());
        }

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

        user.setRoles(createUserRequest.getRole());
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
    public UserDto login(String email, String password){
        try{
            User user = userRepository.findByEmail(email);

            if(user!=null){
                if(password.equalsIgnoreCase(user.getPassword())){
                    UserDto userDto = UserMapper.toUserDto(user);
                    userDto.setToken(JwtUtils.generateToken(user));
                    return userDto;
                }
                return null;
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserDto> getCustomerWantToStore(){

        try {
            List<User> listUser = userRepository.findAllByStatus(true);
            List<UserDto> listUserDto = new ArrayList<>();

            for(User u : listUser){
                listUserDto.add(UserMapper.toUserDto(u));
            }
            return listUserDto;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ListUserDto getUserByWithPagging(String keyword, int page) {
        // Phân trang + sắp xếp
        if(keyword==null){
            keyword="";
        }
         Page<User> rs = userRepository.searchUser(keyword, PageRequest.of(page,5));


        List<User> listUser = rs.getContent();
        List<UserDto> listUserDto = new ArrayList<>();

        for(User u : listUser){
            listUserDto.add(UserMapper.toUserDto(u));
        }

        ListUserDto list = new ListUserDto();
        list.setTotalItems(rs.getTotalElements());
        list.setTotalPages(rs.getTotalPages());
        list.setList(listUserDto);
        return list;
    }

}
