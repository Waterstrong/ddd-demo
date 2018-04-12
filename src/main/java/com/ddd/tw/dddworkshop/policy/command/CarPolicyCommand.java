package com.ddd.tw.dddworkshop.policy.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarPolicyCommand {
    private String policyStartDate;
    private String quoteId;
    private Double quotePremium;
    private String yearOfMake;
    private String brand;
    private String model;
    private String parkingAddress;
    private String kilosEachYear;
    private String driverDateOfBirth;
    private String driverGender;
    private String policyHolderId;
    private String policyHolderName;
    private String policyHolderEmail;
    private String policyHolderDateOfBirth;
}
