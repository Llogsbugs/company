package telran.net.dto;

import java.io.Serializable;

public record Request(String requestType, Serializable requestData) implements Serializable {

}
