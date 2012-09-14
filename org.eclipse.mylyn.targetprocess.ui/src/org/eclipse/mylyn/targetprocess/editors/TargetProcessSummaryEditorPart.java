package org.eclipse.mylyn.targetprocess.editors;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.mylyn.internal.tasks.ui.editors.EditorUtil;
import org.eclipse.mylyn.internal.tasks.ui.editors.TaskEditorSummaryPart;
import org.eclipse.mylyn.targetprocess.core.TargetProcessAttribute;
import org.eclipse.mylyn.tasks.core.data.TaskAttribute;
import org.eclipse.mylyn.tasks.ui.editors.AbstractAttributeEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TargetProcessSummaryEditorPart extends TaskEditorSummaryPart {

	@Override
	public void createControl(Composite parent, FormToolkit toolkit) {
		Composite composite = toolkit.createComposite(parent);
		GridLayout layout = EditorUtil.createSectionClientLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginTop = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 3;
		composite.setLayout(layout);

		createHeaderLayout(composite, toolkit);

		toolkit.paintBordersFor(composite);

		setControl(composite);
	}

	private AbstractAttributeEditor addAttribute(Composite composite, FormToolkit toolkit, TaskAttribute attribute, int width) {
		
		AbstractAttributeEditor editor = createAttributeEditor(attribute);
		
		if (editor != null) {
			toolkit.paintBordersFor(composite);
			editor.createLabelControl(composite, toolkit);
			editor.createControl(composite, toolkit);
			GridData gridData = new GridData();
			if(width != -1){
				gridData.widthHint = width;
			}
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = true  ;
			gridData.horizontalAlignment = GridData.BEGINNING;
			gridData.verticalAlignment = GridData.CENTER;
			editor.getControl().setLayoutData(gridData);
		}
		return editor;
	}

	@Override
	protected Composite createHeaderLayout(Composite composite, FormToolkit toolkit) {
		Composite headerComposite = toolkit.createComposite(composite);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = 1;
		
		
		GridData headerGridData = new GridData();
		headerGridData.grabExcessHorizontalSpace = true;
		headerGridData.grabExcessVerticalSpace = true;
		headerGridData.minimumWidth = 200;
		headerGridData.horizontalAlignment = GridData.FILL;
		headerGridData.verticalAlignment = GridData.FILL;
		headerComposite.setLayoutData(headerGridData);
		headerComposite.setLayout(layout);

		Composite leftPartComposite = toolkit.createComposite(headerComposite);
		GridLayout leftPartLayout = new GridLayout(1, false);
		leftPartLayout.marginRight = 0;
		leftPartLayout.verticalSpacing = 5;		
		leftPartComposite.setLayout(leftPartLayout);

		GridData leftPartGridData = new GridData();
		leftPartGridData.grabExcessHorizontalSpace = false;
		leftPartGridData.grabExcessVerticalSpace = false;
		leftPartGridData.horizontalAlignment = GridData.BEGINNING;
		leftPartGridData.verticalAlignment = GridData.BEGINNING;
		leftPartComposite.setLayoutData(leftPartGridData);
		
		addAttributeControl(toolkit, leftPartComposite, TaskAttribute.SUMMARY, 407);		
		addAttributeControl(toolkit, leftPartComposite, TaskAttribute.STATUS, 200);		

		Composite rightPartComposite = toolkit.createComposite(headerComposite);
		GridLayout rightPartLayout = new GridLayout(2, false);
		rightPartLayout.verticalSpacing = 0;
		rightPartLayout.horizontalSpacing = 0;
		rightPartLayout.marginTop = 0;
		rightPartLayout.marginLeft = 0;

		
		GridData rightPartGridData = new GridData();
		rightPartGridData.grabExcessHorizontalSpace = true;
		rightPartGridData.grabExcessVerticalSpace = true;		
		rightPartGridData.horizontalAlignment = GridData.FILL;
		rightPartGridData.verticalAlignment = GridData.FILL;
		
		rightPartComposite.setLayout(rightPartLayout);
		rightPartComposite.setLayoutData(rightPartGridData);
		
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.FILL;
		
		
		
		Composite firstColumn = toolkit.createComposite(rightPartComposite);
		firstColumn.setLayout(rightPartLayout);		
		Composite secondColumn = toolkit.createComposite(rightPartComposite);
		secondColumn.setLayout(rightPartLayout);

		secondColumn.setLayoutData(gridData2);
		firstColumn.setLayoutData(gridData);
		
		
		addAttributeControl(toolkit, firstColumn, "projectName", -1);
		addAttributeControl(toolkit, firstColumn, "owner", -1);
		addAttributeControl(toolkit, firstColumn, "userStory", -1);
		addAttributeControl(toolkit, firstColumn, "assignedPeople", -1);
		addAttributeControl(toolkit, firstColumn, "assignedPeople2", -1);
		
		addAttributeControl(toolkit, secondColumn, "severity", -1);
		addAttributeControl(toolkit, secondColumn, TaskAttribute.PRIORITY, -1);
		addAttributeControl(toolkit, secondColumn, "timeSpent", -1);
		addAttributeControl(toolkit, secondColumn, "timeRemaining", -1);

		return headerComposite;
	}
	
	private AbstractAttributeEditor addAttributeControl(FormToolkit toolkit, Composite headerComposite, String attributeName, int width) {
		TaskAttribute attribute = getTaskData().getRoot().getMappedAttribute(attributeName);
		return addAttribute(headerComposite, toolkit, attribute, width);
	}
}
