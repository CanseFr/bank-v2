package org.bank.bankv2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ObjectValidationException extends RuntimeException {
    private final Set<String> violations;
    private final String violationSource;

}