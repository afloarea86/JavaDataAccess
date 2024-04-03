package Budget.calculator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
public class ORMController {



    @Autowired
    JdbcTemplate jdbcTemplate;



    @GetMapping("/create_user")
    public ModelAndView create_user(@RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password){
        ModelAndView mov = new ModelAndView("create_user");

        List<Users> userList = jdbcTemplate.query("SELECT * FROM Login WHERE email = '" + email + " ' ", new UsersRowMapper());
        if(!userList.isEmpty()){
            mov.addObject("ERROR", "* User already exists!");
        } else if(email.length()<=3 || password.length()<=3){
            mov.addObject("ERROR", "* Email/Password length must be more than 3 characters");
        }else{
            jdbcTemplate.update("INSERT INTO Login VALUES(null,?,?,?,?)", name, email, username, password);
            return  new ModelAndView("login_user");
        }
        return mov;
    }

        @GetMapping("/allUsers")
        public ModelAndView allUsers(){
            ModelAndView mov2 = new ModelAndView("allUsers");

            List<Users> usersList = jdbcTemplate.query("SELECT * FROM Login", new UsersRowMapper());
            mov2.addObject("Userslist", usersList);
            return mov2;
        }

    @GetMapping(value = "/login_user")
    public ModelAndView login_user(@RequestParam("username") String username,
                                   @RequestParam("password") String password){
        ModelAndView mov3 = new ModelAndView();
           List<Users> usersList2 = jdbcTemplate.query("SELECT * FROM Login", new UsersRowMapper());
                for(Users users: usersList2){
                    if(users.getUsername().equals(username) && users.getPassword().equals(password)){
                        return new ModelAndView("main");
                    }else{
                        mov3.addObject("ERROR", "* Email/Password are incorrect. Please try again");

                    }
                }
            return mov3;
    }

    @GetMapping(value="/budget")
    public ModelAndView budget(){
        return new ModelAndView("budget");
    }

    @GetMapping(value="/mortgage")
    public ModelAndView mortgage(){
        return new ModelAndView("mortgage");
    }

    @GetMapping(value="/salary")
    public ModelAndView salary(){
        return new ModelAndView("salary");
    }

    @GetMapping(value="/main")
    public ModelAndView mainPage(){
        return new ModelAndView("main");
    }
    

}

