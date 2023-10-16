package com.resellerapp.controller;

import com.resellerapp.model.OfferCreateBindingModel;
import com.resellerapp.service.LoggedUser;
import com.resellerapp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final LoggedUser loggedUser;
    private final OfferService offerService;

    public OfferController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("offer-add");
    }

    @PostMapping("/create")
    public ModelAndView create(OfferCreateBindingModel offerCreateBindingModel) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }

        boolean isCreated = offerService.create(offerCreateBindingModel);

        String view = isCreated ? "redirect:/home" : "create";
        return new ModelAndView(view);
    }

    @PostMapping("/buy/{id}")
    public ModelAndView buyOffer(@PathVariable("id") UUID id) {
        if (!loggedUser.isLogged()) {
            return new ModelAndView("redirect:/login");
        }
        
        offerService.buy(id);
        return new ModelAndView("redirect:/home");
    }
}
