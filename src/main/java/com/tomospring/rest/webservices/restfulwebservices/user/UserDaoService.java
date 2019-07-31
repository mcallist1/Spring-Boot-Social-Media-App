package com.tomospring.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 5;

    static {
        users.add(new User(1,"Dave", new Date()));
        users.add(new User(2,"Mary", new Date()));
        users.add(new User(3,"David", new Date()));
        users.add(new User(4,"Susan", new Date()));
        users.add(new User(5,"Ed", new Date()));
    }

    public User findOne(int id){
        for(User user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
}
