package com.ezen.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.entity.Cart;
import com.ezen.entity.Funding;
import com.ezen.entity.Member;
import com.ezen.entity.Purchase;
import com.ezen.entity.Search;
import com.ezen.service.FundingService;
import com.ezen.service.MemberService;
import com.ezen.service.PurchaseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FundingService fundingService;

    @PostMapping("/purchase")
    public String insertPurchaseForm(Funding funding, @RequestParam("quantity") int quantity,
                                     Model model, HttpSession session) {

        Member loginMember = (Member) session.getAttribute("loginMember");

        if(loginMember == null) {
            return "sign/login";
        } else {

            Map<String, String> addrMap = new HashMap<>();
            String[] addressArr = null;

            Funding findFunding = fundingService.getFunding(funding);
            Member findMember = memberService.getMember(loginMember);
            addressArr = findMember.getAddress().split(",");

            addrMap.put("addr1", addressArr[0]);
            addrMap.put("addr2", addressArr[1]);
            addrMap.put("addr3", addressArr[2]);

            model.addAttribute("funding", findFunding);
            model.addAttribute("quantity", quantity);
            model.addAttribute("member", findMember);
            model.addAttribute("address", addrMap);

            return "purchase/insertPurchase";
        }
    }

    @PostMapping("/insertPurchase")
    public @ResponseBody void insertPurchase(@RequestBody Map<String, String> map, Purchase purchase, Cart cart) {
        System.out.println("주문자 정보: " + map.entrySet());

        Funding funding = new Funding();
        funding.setFunding_seq(Long.valueOf(map.get("funding_seq")));
        Member member = new Member();
        member.setUsername(map.get("username"));

        System.out.println("펀딩: " + funding);
        System.out.println("멤버: " + member);

        purchase.setName(map.get("name"));
        purchase.setPhone(map.get("phone"));
        purchase.setEmail(map.get("email"));
        purchase.setZip_num(map.get("zip_num"));
        purchase.setAddress(map.get("address"));
        purchase.setPurchase_uid(map.get("purchase_uid"));
        purchase.setPrice(Integer.parseInt(map.get("price")));
        purchase.setQuantity(Integer.parseInt(map.get("quantity")));
        purchase.setPayment(Integer.parseInt(map.get("payment")));
        purchase.setPayer(map.get("payer"));
        purchase.setP_comment(map.get("p_comment"));
        purchase.setAgree(map.get("agree"));
        purchase.setFunding(funding);
        purchase.setMember(member);

        purchaseService.insertPurchase(purchase);
       // cart.setCart_seq(cart.getCart_seq());
        //cartService.deleteCart(cart);
    }

    @GetMapping("/success")
    public String successPurchase(@ModelAttribute("purchase") Purchase purchase, Model model) {

        System.out.println("결제 주문번호: " + purchase);
        model.addAttribute("purchase", purchase);

        return "purchase/successPurchase";
    }

    @GetMapping("/fail")
    public String failPurchase() {
        return "purchase/failPurchase";
    }

    @RequestMapping("/purchaseList")
    public String myPurchaseList(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @SessionAttribute("member") Member member, Model model) {

        Page<Purchase> purchaseList = purchaseService.findByMember_Username(page, member.getUsername());

        model.addAttribute("purchaseList", purchaseList);

        return "purchase/myPurchaseList";
    }

    @RequestMapping("/allPurchaseList")
    public String allPurchaseList(@RequestParam(value = "page", defaultValue = "1") int page, Search search, Model model) {
        if(search.getSearchCondition() == null) {
            search.setSearchCondition("USERNAME");
        }
        if(search.getSearchKeyword() == null) {
            search.setSearchKeyword("");
        }
        System.out.println("검색어" + search);
        Page<Purchase> purchaseList = purchaseService.getPurchaseList(page, search);

        model.addAttribute("purchaseList", purchaseList);

        return "admin/purchaseList";
    }

    @GetMapping("/getPurchase")
    public String getPurchase(Purchase purchase, Model model) {

        model.addAttribute("purchase", purchaseService.getPurchase(purchase));
        return "purchase/getPurchase";
    }
}
