package com.wipro.Monolithic.App.register;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TradeController {

	User user = new User();

	Map<String, Double> companies = new HashMap<String, Double>();

	Map<String, Trade> trades = new HashMap<String, Trade>();

	public TradeController() {
		companies.put("WIPRO", 298.45);
		companies.put("INFY", 998.45);
		companies.put("TCS", 2798.45);
		companies.put("TECHM", 495.40);
	}

	@PostMapping(value = "/trade/do")
	@ResponseBody
	public String registerUser(@ModelAttribute("ticker") String ticker, @ModelAttribute("qty") int qty,
			HttpServletRequest request) {
		Double price = companies.get(ticker);
		Trade t = new Trade(ticker, price, qty);
		double total = price * qty;
		t.setTotal_cost(total);

		trades.put(ticker, t);
		user = (User) request.getSession().getAttribute("user");

		double bal = user.getBalance() - total;
		user.setBalance(bal);

		return "<html><body bgcolor='coral'>Traded Successfully" + user.getUserid() + " Your balance now is:"
				+ user.getBalance() + "<br><a href='http://localhost:8080/index.html'>Exit</a><br>"
				+ "<a href='http://localhost:8080/Trade.html'>Trade Again</a><br><body><html>";
	}

	@GetMapping(value = "/trade/all")
	@ResponseBody
	public Map<String, Trade> getAllRegisteredTrades() {

		return trades;
	}

	@GetMapping(value = "/trade/{ticker}")
	@ResponseBody
	public Trade getTrade(@PathVariable("ticker") String ticker) {
		return trades.get(ticker);
	}
}
