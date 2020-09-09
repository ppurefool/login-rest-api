package io.kwangsik.loginrestapi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter /* Object Mapper 에서 이용한다. */
public class ApiOne {
    private final String resultMessage;
    private Object resultObject;

    public ApiOne(String newResultMessage) {
        this.resultMessage = newResultMessage;
    }
}