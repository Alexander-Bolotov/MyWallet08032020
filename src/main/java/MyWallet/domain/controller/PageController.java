package MyWallet.domain.controller;

import MyWallet.domain.dao.TransactionDao;
import MyWallet.domain.model.Transaction;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    TransactionDao transactionDao;

    @GetMapping("/index")
    public String index() {
        return "/login";
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "/login";
    }

    @RequestMapping(value = "/wallet")
    public String getWalletPage(Model model) {
        return "/wallet";
    }

}
