package com.ddd.tw.dddworkshop.quote;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ddd.tw.dddworkshop.quote.command.CarDetailCommand;
import com.ddd.tw.dddworkshop.quote.command.HomeDetailCommand;
import com.ddd.tw.dddworkshop.quote.model.PolicyQuote;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    @Autowired
    private QuoteApplicationService applicationService;

    @PostMapping("/home")
    @ResponseStatus(CREATED)
    public HttpEntity<PolicyQuote> generateHomePolicyQuote(@RequestBody HomeDetailCommand homeDetail) {
        PolicyQuote quotation = applicationService.generateQuote(homeDetail);
        return new HttpEntity<>(quotation);
    }

    @PostMapping("/car")
    @ResponseStatus(CREATED)
    public HttpEntity<PolicyQuote> generateCarPolicyQuote(@RequestBody CarDetailCommand carDetailCommand) {
        PolicyQuote quotation = applicationService.generateQuote(carDetailCommand);
        return new HttpEntity<>(quotation);
    }

}
