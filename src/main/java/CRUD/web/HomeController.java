package CRUD.web;

import CRUD.User;
import CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanzhar on 8/20/16.
 */

@Controller
public class HomeController {

        private UserService userService;

        @Autowired(required = true)
        @Qualifier(value = "userService")
        public void setUserService(UserService userService) {
            this.userService = userService;
        }

        @RequestMapping(value = "users", method = RequestMethod.GET)
        public String listUsers(@RequestParam(value = "page", required = false) Long page, Model model) {
            if (null == page)
                page = 1L;
            model.addAttribute("user", new User());
            model.addAttribute("searcheduser", new User());
            model.addAttribute("listUsers", userService.getUsers(page));
            model.addAttribute("page", page);

            return "users";
        }

        @RequestMapping(value = "/users/add", method = RequestMethod.POST)
        public String addUser(@ModelAttribute("user") User user) {
            if (user.getId() == 0) {
                userService.addUser(user);
            } else {
                userService.updateUser(user);
            }

            return "redirect:/users";
        }

        @RequestMapping("/remove/{id}")
        public String removeUser(@PathVariable("id") int id) {
            userService.removeUser(id);

            return "redirect:/users";
        }

        @RequestMapping("edit/{id}")
        public String editUser(@PathVariable("id") int id, @RequestParam(value = "page", required = false) Long page, Model model) {
            if (null == page)
                page = 1L;
            model.addAttribute("user", userService.getUser(id));
            model.addAttribute("searcheduser", new User());
            model.addAttribute("listUsers", userService.getUsers(page));
            model.addAttribute("page", page);

            return "users";
        }

        @RequestMapping("userdata/{id}")
        public String userData(@PathVariable("id") int id, Model model) {
            model.addAttribute("user", userService.getUser(id));

            return "userdata";
        }

        @RequestMapping(value = "searchresults", method = RequestMethod.POST)
        public String searchResults(@ModelAttribute("searcheduser") User user, @RequestParam String action, Model model) {
            List<User> searchResult = new ArrayList<>();
            switch (action.toLowerCase()) {
                case "search by name":
                    searchResult = userService.getUsers(user.getName());
                    break;
                case "search admins":
                    searchResult = userService.getAdmins();
                    break;
                default:
                    break;
            }
            model.addAttribute("listUsers", searchResult);

            return "searchresults";
        }

}
