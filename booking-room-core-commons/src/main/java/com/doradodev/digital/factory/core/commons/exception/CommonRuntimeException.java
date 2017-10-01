/**
 * 
 */
package com.doradodev.digital.factory.core.commons.exception;

/**
 * @author cristiandorado
 *
 */
public class CommonRuntimeException extends RuntimeException{

	private static final long serialVersionUID = 270945273328095858L;
	
	public CommonRuntimeException() {
		super();
	}

	public CommonRuntimeException(String message) {
		super(message);
	}
	
    public CommonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
