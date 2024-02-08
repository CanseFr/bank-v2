package org.bank.bankv2.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException{

    private final String errorMsg;
    private final String operationId;
    private final String source;
    private final String dependency;


}