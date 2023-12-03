package telran.net.dto;

import java.io.Serializable;

public record Response(String responseCode, Serializable responseData) implements Serializable{
public static final String OK = "ok";
public static final String WRONG_TYPE = "wrong type";
public static final String WRONG_DATA = "wrong data";

}
