package MyWallet.domain.controller;

import MyWallet.domain.dao.*;
import MyWallet.domain.model.*;
import MyWallet.domain.repository.RoleRepository;
import MyWallet.domain.repository.TransactionRepository;
import MyWallet.domain.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequestMapping
@RestController("/wallet")
public class MainController {

    @Autowired
    TypeOTransactionDao typeOTransactionDao;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private WalletDao walletDao;

    @Autowired
    private CategoryDao categoryDao;

    static final String USER_FORM = "/user-form";
    static final String USER_DATA = "/userdata";

    @JsonView(Wallet.class)
    @RequestMapping(value = "/wallets", method = RequestMethod.GET)
    public ResponseEntity<List<Wallet>> getWallets() {

        return new ResponseEntity<List<Wallet>>(walletDao.getListWallets(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<String> getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        return new ResponseEntity<String>(name, HttpStatus.OK);
    }


    @JsonView(Category.class)
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategoriesOUT(@RequestParam("type") String type) {
        List<TypeOfTransaction> typeOfTransactions = typeOTransactionDao.getAllTypes();
        List<TypeOfTransaction> transactions = new ArrayList<>();

        for (TypeOfTransaction typeOfTransaction: typeOfTransactions
             ) {
            if(typeOfTransaction.getTypeOfTransaction().equals(type)){
                transactions.add(typeOfTransaction);
            }
        }
        return new ResponseEntity<List<Category>>(categoryDao.getCategoryOUT(), HttpStatus.OK);
    }

    @RequestMapping(value = "/update")
    public String update(@RequestParam("userId") long id, Model model) {
        if (id == -1) {
            User user = new User();
            model.addAttribute("user", user);
            return USER_FORM;
        }
        List<Role> roles = roleRepository.findAll();
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return USER_FORM;
    }

    @RequestMapping(value = "/updateUser")
    public String updateUser(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("roles") Set<Role> role) {
        User updateUser = userRepository.getOne(id);
        updateUser.setName(name);
        updateUser.setPassword(password);
        updateUser.setRoles(role);
        userRepository.save(updateUser);
        return "redirect:/mainPage";
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestParam("userId") long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/mainPage";
    }

    @RequestMapping(value = "/addUser")
    public String addUser(@RequestParam("userId") long id, Model model) {
        Set<Role> userRoles = new HashSet<>(roleRepository.findAll());
        List<User> userList = userRepository.findAll();
        int i = userList.size();
        Long idLast = userList.get(i - 1).getId();
        User user = new User();
        user.setId(idLast + 1);
        user.setName("");
        user.setPassword("");
        user.setRoles(userRoles);

        model.addAttribute("user", user);
        model.addAttribute("roles", userRoles);
        return USER_FORM;
    }

    @RequestMapping(value = "/userdata")
    public String userdata(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findAllByName(auth.getName());
        model.addAttribute("user", user);
        return USER_DATA;
    }

    @JsonView(Transaction.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transaction>> getTransactionList() {
        return new ResponseEntity<List<Transaction>>(transactionDao.getAllTransactions(), HttpStatus.OK);
    }

    @JsonView(User.class)
    @RequestMapping(value = "/listuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUserList() {
        return new ResponseEntity<List<User>>(userDao.getListUsers(), HttpStatus.OK);
    }
}
