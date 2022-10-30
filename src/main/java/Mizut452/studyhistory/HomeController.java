package Mizut452.studyhistory;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    record UserList(String id, String mailaddress, String username, String password) {}
    private List<UserList> userLists = new ArrayList<>();
    private UserListDao dao;

    @Autowired
    HomeController(UserListDao dao) {
        this.dao =dao;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/createaccount")
    public String createAccount() {
        return "createAccount";
    }

    @GetMapping("/userlist")
    public String userlistpage(Model model) {
        List<UserList> userLists = dao.findAll();
        model.addAttribute("userList", userLists);
        return "UserList";
    }

    @RequestMapping("/add")
    String addItem(@RequestParam("mailaddress") String mailaddress,
                   @RequestParam("username") String username,
                   @RequestParam("password") String password) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);

        UserList item = new UserList(id, mailaddress, username, password);
        dao.add(item);

        return "redirect:/userlist";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
