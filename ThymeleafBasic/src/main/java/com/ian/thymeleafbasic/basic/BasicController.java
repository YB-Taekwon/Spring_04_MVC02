package com.ian.thymeleafbasic.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");

        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");

        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> userList = new ArrayList<>();
        userList.add(userA);
        userList.add(userB);

        Map<String, User> userMap = new HashMap<>();
        userMap.put("userA", userA);
        userMap.put("userB", userB);

        model.addAttribute("userA", userA);
        model.addAttribute("userList", userList);
        model.addAttribute("userMap", userMap);

        return "basic/variable";
    }

    @Getter
    @AllArgsConstructor
    public static class User {

        private String name;
        private int age;
    }

    @GetMapping("/basic-objects")
    public String basicObjects(
            HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model
    ) {
        model.addAttribute("request", request); // 스프링 부트 3 이전에는 생략이 가능했으나, 이후부터 생략 불가능
        model.addAttribute("response", response); // 스프링 부트 3 이전에는 생략이 가능했으나, 이후부터 생략 불가능
        model.addAttribute("servletContext", request.getServletContext()); // 스프링 부트 3 이전에는 생략이 가능했으나, 이후부터 생략 불가능
        session.setAttribute("sessionData", "Hello Session");

        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());

        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");

        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");

        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");

        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUser(model);

        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUser(model);

        return "basic/condition";
    }

    private void addUser(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User("userA", 10));
        users.add(new User("userB", 20));
        users.add(new User("userC", 30));

        model.addAttribute("users", users);
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");

        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User("userA", 10));
        users.add(new User("userB", 20));
        users.add(new User("userC", 30));

        model.addAttribute("users", users);

        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("user", 20));

        List<User> users = new ArrayList<>();
        users.add(new User("userA", 10));
        users.add(new User("userB", 20));
        users.add(new User("userC", 30));

        model.addAttribute("users", users);

        return "basic/javascript";
    }
}
