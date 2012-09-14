package org.eclipse.mylyn.targetprocess.core;

import java.io.IOException;

public class UnrecognizedReponseException extends IOException {
	private static final long serialVersionUID = 3937064234233432464L;

	public UnrecognizedReponseException(String message) {
		super(message);
	}

}
