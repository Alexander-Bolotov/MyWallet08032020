package MyWallet.domain.controller;

import MyWallet.domain.dao.*;
import MyWallet.domain.model.*;
import MyWallet.domain.repository.RoleRepository;
import MyWallet.domain.repository.TransactionRepository;
import MyWallet.domain.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import javafx.scene.chart.CategoryAxis;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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



    @RequestMapping(value = "/transaction")
    public ResponseEntity<String> addTransaction(@RequestParam("data") String data, @RequestParam("typeOfTransaction") String typeOfTransaction, @RequestParam("outWallet") String outWallet, @RequestParam("incomeWallet") String incomeWallet, @RequestParam("summ") int summ, @RequestParam("user") String userName, @RequestParam("comment") String comment, @RequestParam("catOUT") String catOUT, @RequestParam("catIN") String catIN ) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-mm-dd");
        Date docDate= format.parse(data);// docDate - дата транзакции

        TypeOfTransaction typeTrans = typeOTransactionDao.getTypeOfTransactionById(Long.parseLong(typeOfTransaction));

        List<Wallet> wallets = walletDao.getListWallets();

        Wallet walletOut = walletDao.getFirstByWallet(outWallet);
        Wallet walletIn = walletDao.getFirstByWallet(incomeWallet);

        User user = userRepository.findAllByName(userName);

        Category categoryIn = categoryDao.getFirstByCategory(catIN);
        Category categoryOut = categoryDao.getFirstByCategory(catOUT);
        Category category;
//        if (typeOfTransaction.equals("Перевод")){
//            category=
//        }
        if (categoryIn!=null){
            category=categoryIn;
        }else {
            category=categoryOut;
        }
        Transaction transaction =new Transaction(docDate,typeTrans, summ,walletIn, walletOut, user,comment, category);

        transactionDao.addTransaction(transaction);

        return new ResponseEntity<String>("wallet", HttpStatus.OK);
    }


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
    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategories(@RequestParam("id") Long id) {
        TypeOfTransaction typeOfTransaction = typeOTransactionDao.getTypeOfTransactionById(id);
        List<Category> category = categoryDao.getCategoriesByType(typeOfTransaction);

        return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
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
