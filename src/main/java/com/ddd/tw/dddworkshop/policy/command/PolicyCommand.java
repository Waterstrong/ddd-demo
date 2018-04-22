package com.ddd.tw.dddworkshop.policy.command;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.ddd.tw.dddworkshop.policy.model.HolderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyCommand {
    @NotBlank
    private String policyStartDate;
    @NotBlank
    private String quoteId;
    @Valid
    private HolderDetail holderDetail;
}
