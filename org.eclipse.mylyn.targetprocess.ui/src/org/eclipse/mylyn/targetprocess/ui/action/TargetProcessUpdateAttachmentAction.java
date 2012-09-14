package org.eclipse.mylyn.targetprocess.ui.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

public class TargetProcessUpdateAttachmentAction extends BaseSelectionListenerAction implements IViewActionDelegate {

	
	private boolean obsolete;	
	
	private ISelection currentSelection;

	public TargetProcessUpdateAttachmentAction(boolean obsolete) {
		super("UpdateAttachmentAction"); //$NON-NLS-1$
		this.obsolete = obsolete;
	}
	
	protected TargetProcessUpdateAttachmentAction(String text) {
		super(text);

	}

	@Override
	public void init(IViewPart arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(IAction arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

}
