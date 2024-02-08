package org.bank.bankv2.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currencies {
    Float baseAmountFrom;
    String from;
    Float convertedAmountTo;
    String to;
}

