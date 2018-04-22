package com.ddd.tw.dddworkshop.quote;

import static com.ddd.tw.dddworkshop.utils.constant.Constants.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ddd.tw.dddworkshop.quote.command.GenerateCarPolicyQuoteCommand;
import com.ddd.tw.dddworkshop.quote.command.GenerateHomePolicyQuoteCommand;
import com.ddd.tw.dddworkshop.quote.model.PolicyQuote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/quote", consumes = CONTENT_TYPE, produces = CONTENT_TYPE)
@Api(tags = "Quote", description = "Quote Resource", consumes = CONTENT_TYPE, produces = CONTENT_TYPE)
public class QuoteController {
    @Autowired
    private QuoteApplicationService applicationService;

    @PostMapping("/home")
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Generate Home Policy Quote", notes = "This is for generating home policy quote")
    public HttpEntity<PolicyQuote> generateHomePolicyQuote(@RequestBody GenerateHomePolicyQuoteCommand homeDetail) {
        PolicyQuote quotation = applicationService.generateQuote(homeDetail);
        return new HttpEntity<>(quotation);
    }

    @PostMapping("/car")
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Generate Car Policy Quote", notes = "This is for generating car policy quote")
    public HttpEntity<PolicyQuote> generateCarPolicyQuote(@RequestBody GenerateCarPolicyQuoteCommand carDetail) {
        PolicyQuote quotation = applicationService.generateQuote(carDetail);
        return new HttpEntity<>(quotation);
    }

}
