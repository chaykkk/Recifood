package com.ezen.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.entity.Cart;
import com.ezen.entity.CartDetail;
import com.ezen.entity.Funding;
import com.ezen.entity.Member;
import com.ezen.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping("/cartList")
	public String cartList(HttpSession session, Model model, Cart cart, Funding funding) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else  {
			List<CartDetail> cartList = cartService.getCartList(loginMember.getUsername());
			
			model.addAttribute("cartList", cartList);
			//model.addAttribute("totalPrice", cart.getQuantity()*cart.getPrice());
			//System.out.println(cart.getQuantity()*cart.getPrice());
			return "sign/cartList";
		}
	}
	
	
	@PostMapping(value="/insertCart", consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void insertCart(@RequestBody Map<String, String> map,
							Cart cart) {
			Member member = new Member();
			member.setUsername(map.get("username"));
			Funding funding = new Funding();
			funding.setFunding_seq(Long.valueOf(map.get("funding_seq")));
			
			cart.setMember(member);
			cart.setFunding(funding);
			cart.setQuantity(Integer.parseInt(map.get("quantity")));
			cart.setTotalprice(Integer.parseInt(map.get("quantity"))*Integer.parseInt(map.get("price")));
			cartService.insertCart(cart);
		}
	
	@PostMapping("/updateCart")
	public @ResponseBody void updateCart(@RequestBody Map<String, String> map,
							HttpSession session, Cart cart) {
		System.out.println("cart_seq="+ map.get("cart_seq"));
		System.out.println("funding_seq="+ map.get("funding_seq"));
		System.out.println("member=" + map.get("username"));
		System.out.println("quantity=" + map.get("quantity"));
		System.out.println("price=" + map.get("price"));
		
		Member member = new Member();
		member.setUsername(map.get("username"));
		Funding funding = new Funding();
		funding.setFunding_seq(Long.valueOf(map.get("funding_seq")));
		
		cart.setCart_seq(Long.valueOf(map.get("cart_seq")));
		cart.setMember(member);
		cart.setFunding(funding);
		cart.setQuantity(Integer.parseInt(map.get("quantity")));
		cart.setTotalprice(Integer.parseInt(map.get("quantity"))*Integer.parseInt(map.get("price")));
		System.out.println(cart.getTotalprice());
		cartService.updateCart(cart);
	}
	
	@GetMapping("/deleteCart")
	public String deleteCart(HttpSession session, Cart cart) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "sign/login";
		} else {
			cartService.deleteCart(cart);
			
			return "redirect:cartList";
		}
	}
	
	
	@PostMapping("/deleteCartCheck")
	public @ResponseBody void deleteCartCheck(HttpServletRequest request,
									@RequestBody Map<String, String[]> map,
				//					@RequestParam(value="checkBoxArr[]") List<String> checkBoxArr,
									Cart cart) throws Exception {
		
		System.out.println("deleteCartCheck()........");
		int data = 0;
		
		String[] checkBoxArr = map.get("checkBoxArr");
		
		for(String cseq : checkBoxArr) {
			System.out.println("삭제할 cart_seq="+cseq);
			data = Integer.parseInt(cseq);
			cart.setCart_seq(Long.valueOf(data));
			cartService.deleteCart(cart);
		}
	}
	
}

