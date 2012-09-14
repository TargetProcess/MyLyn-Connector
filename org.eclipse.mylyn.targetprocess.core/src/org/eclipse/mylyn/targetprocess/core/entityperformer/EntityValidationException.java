package org.eclipse.mylyn.targetprocess.core.entityperformer;

public class EntityValidationException extends Exception {

	public EntityValidationException(String targetProcessClientValidationBugNameIsEmpty) {
		super(targetProcessClientValidationBugNameIsEmpty);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4713509850042947022L;

}
