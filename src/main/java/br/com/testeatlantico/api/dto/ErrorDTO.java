package br.com.testeatlantico.api.dto;

import java.time.LocalDate;

public class ErrorDTO {
    private String name;
    private String msg;
    private LocalDate instant;

    public ErrorDTO(String name, String msg, LocalDate instant) {
        this.name = name;
        this.msg = msg;
        this.instant = instant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDate getInstant() {
        return instant;
    }

    public void setInstant(LocalDate instant) {
        this.instant = instant;
    }
}
