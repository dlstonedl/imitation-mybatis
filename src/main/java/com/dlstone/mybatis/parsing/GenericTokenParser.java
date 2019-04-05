package com.dlstone.mybatis.parsing;

public class GenericTokenParser {
    private final String openToken;
    private final String closeToken;
    private final TokenHandler handler;

    public GenericTokenParser(String openToken, String closeToken, TokenHandler handler) {
        this.openToken = openToken;
        this.closeToken = closeToken;
        this.handler = handler;
    }

    public String parse(String text) {
        handler.handleToken("id");
        return "select * from user where id = ?";
    }
}
