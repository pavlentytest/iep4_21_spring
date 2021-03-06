package com.example.demo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Controller
public class MyController {

    // comment 12312312323123123
    @Autowired
    UserRespository respository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add")
    public @ResponseBody String addNewUser(@RequestParam String name,
                                           @RequestParam Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        respository.save(user);
        return "Saved successefull!";
    }

    @GetMapping("/all")
    public @ResponseBody String getAll() {

        Iterable<User> users = respository.findAll();

        ArrayList<User> arrayList = new ArrayList<>();

        for(User u: users) {
              arrayList.add(u);
        }

        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(arrayList,comparator);
        Gson gson = new Gson();
        return gson.toJson(arrayList);
    }

}
