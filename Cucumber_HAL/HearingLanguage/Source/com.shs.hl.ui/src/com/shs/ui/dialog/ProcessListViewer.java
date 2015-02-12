package com.shs.ui.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class ProcessListViewer extends Dialog {
	private Table				table;
	private CheckboxTableViewer	checkboxTableViewer;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ProcessListViewer(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
		checkboxTableViewer.setAllChecked(false);
		table = checkboxTableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		checkboxTableViewer.setContentProvider(ArrayContentProvider.getInstance());

		TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnProcId = tableViewerColumn.getColumn();
		tblclmnProcId.setWidth(100);
		tblclmnProcId.setText("Proc Id");

		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Process process = (Process) element;
				return String.valueOf(process.getProcID());
			}

		});

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnProcessName = tableViewerColumn_1.getColumn();
		tblclmnProcessName.setWidth(100);
		tblclmnProcessName.setText("Process Name");

		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Process process = (Process) element;
				return process.getName();
			}
		});

		initInput();
		return container;
	}

	private void initInput() {
		List<Process> procs = ProcessLister.getAllProcess();
		getCheckboxTableViewer().setInput(procs.toArray(new Process[procs.size()]));

	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	protected CheckboxTableViewer getCheckboxTableViewer() {
		return checkboxTableViewer;
	}
}
