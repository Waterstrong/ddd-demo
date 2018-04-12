package com.ddd.tw.dddworkshop.webapi.quote;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ddd.tw.dddworkshop.quote.QuoteApplicationService;
import com.ddd.tw.dddworkshop.quote.domain.CarDetails;
import com.ddd.tw.dddworkshop.quote.domain.HomeDetails;
import com.ddd.tw.dddworkshop.quote.domain.Quotation;

@RestController
public class QuoteController {
    @Autowired
    private QuoteApplicationService applicationService;

    @ResponseStatus(OK)
    public HttpEntity<Quotation> getHomePolicyQuote(String constructionMaterial, String buildingType, String bedroomsType) {
        HomeDetails homeDetails = new HomeDetails(constructionMaterial, buildingType, bedroomsType);
        Quotation quotation = applicationService.calculateQuote(homeDetails);
        return new HttpEntity<>(quotation);
    }

    @ResponseStatus(OK)
    public HttpEntity<Quotation> getCarPolicyQuote(String yearOfMake, String brand, String model,
                                                   String parkingAddress, String kilosEachYear) {
        CarDetails carDetails = new CarDetails(yearOfMake, brand, model, parkingAddress, kilosEachYear);
        Quotation quotation = applicationService.calculateQuote(carDetails);
        return new HttpEntity<>(quotation);
    }

}
