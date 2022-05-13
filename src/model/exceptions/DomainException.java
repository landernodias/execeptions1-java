package model.exceptions;

public class DomainException extends  RuntimeException {
	private static final long serialVersionUID = 1L;

	//contrutor que recebe uma mensagem para o construtor da superclasse
	public DomainException(String msg) {
		super(msg);
	}
}