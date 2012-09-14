package org.eclipse.mylyn.targetprocess.core.entityperformer;

public class EntityWasDeletedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1138101697173076315L;

	public EntityWasDeletedException(String entityKind) {
		super(entityKind);
	}
}
