package com.shs.ui.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.osgi.framework.Bundle;

import com.shs.hl.ui.utils.BundleHelper;

public class VersionsViewer extends Dialog {
	private Table versionsTable;
	private Bundle selectedBundle;
	private final List<Bundle> input;
	private CheckboxTableViewer checkboxTableViewer;
	String shellTitle = "";

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public VersionsViewer(final Shell parentShell, final List<Bundle> bundles,
			String title) {
		super(parentShell);
		parentShell.setText("Change of Version");
		setShellStyle(SWT.BORDER | SWT.TITLE | SWT.APPLICATION_MODAL);
		input = bundles;
		this.shellTitle = title;

	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		getShell().setText(shellTitle);
		final Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		checkboxTableViewer = CheckboxTableViewer.newCheckList(container,
				SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		versionsTable = checkboxTableViewer.getTable();
		versionsTable.setLinesVisible(true);
		versionsTable.setHeaderVisible(true);

		checkboxTableViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		final TableViewerColumn plattform = new TableViewerColumn(
				checkboxTableViewer, SWT.NONE);
		 
		final TableColumn tblclmnVersion = plattform.getColumn();
		//TODO resize
		tblclmnVersion.setWidth(300);
		plattform.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Bundle bundle = (Bundle) element;
				return BundleHelper.getStdlibVersion(bundle.getSymbolicName().toString());
			}
					});
		tblclmnVersion.setWidth(162);
		tblclmnVersion.setText("Plattform");
       /*
		TableViewerColumn supplierNameColumn = new TableViewerColumn(
				checkboxTableViewer, SWT.NONE);
		TableColumn tblclmnSuppliedBy = supplierNameColumn.getColumn();
		tblclmnSuppliedBy.setWidth(273);
		tblclmnSuppliedBy.setText("Contained In");

		supplierNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Bundle bundle = (Bundle) element;
				return bundle.getSymbolicName();
			}
		}); */

		checkboxTableViewer.setInput(input);

		// workaround for single-check
		checkboxTableViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(final CheckStateChangedEvent event) {
				checkboxTableViewer.setCheckedElements(new Object[] { event
						.getElement() });
			}
		});
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(final Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	@Override
	protected void okPressed() {
		if (checkboxTableViewer.getCheckedElements().length > 0) {
			selectedBundle = (Bundle) checkboxTableViewer.getCheckedElements()[0]; // single
	 }
		super.okPressed();
	}

	public Bundle getSelectedBundle() {
		return selectedBundle;
	}

}
