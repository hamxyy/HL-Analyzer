package com.shs.ui.dialog;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.Bundle;

import com.shs.hl.ui.utils.BundleHelper;

public class PlatformViewer extends Shell {

	private List<Bundle> bundles;
	private Composite contentContainer;
	private boolean extendedToggled = false;
	public static final int OK = 0;
	private int returnCode = OK;

	public int getReturnCode() {
		return returnCode;
	}

	private String preselection;
	private Bundle selectedBundle;

	public Bundle getSelectedBundle() {
		return selectedBundle;
	}

	public static final int CANCEL = 1;

	private Map<String, Bundle> nameBundleMap = new HashMap<String, Bundle>();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			PlatformViewer shell = new PlatformViewer(display,
					new LinkedList<Bundle>(), "ss");
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param shell
	 */
	public PlatformViewer(Display display, List<Bundle> list, String fCurrentLib) {

		super(display, SWT.TITLE | SWT.APPLICATION_MODAL);
		this.bundles = list;
		initMap();
		this.preselection = fCurrentLib;
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite compositeAll = new Composite(this, SWT.NONE);
		compositeAll.setLayout(new FormLayout());

		contentContainer = new Composite(compositeAll, SWT.NONE);
		FormData fd_contentContainer = new FormData();
		fd_contentContainer.bottom = new FormAttachment(0, 237);
		fd_contentContainer.right = new FormAttachment(0, 444);
		fd_contentContainer.top = new FormAttachment(0, 10);
		fd_contentContainer.left = new FormAttachment(0);
		contentContainer.setLayoutData(fd_contentContainer);
		contentContainer.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button btnCheckButton = new Button(compositeAll, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!extendedToggled) {
					showExtended();
					extendedToggled = true;
				} else {
					extendedToggled = false;
					showSimple();
				}

			}
		});

		FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.top = new FormAttachment(contentContainer, 6);
		fd_btnCheckButton.left = new FormAttachment(contentContainer, 10, SWT.LEFT);
		fd_btnCheckButton.bottom = new FormAttachment(0, 269);
		fd_btnCheckButton.right = new FormAttachment(0, 121);
		btnCheckButton.setLayoutData(fd_btnCheckButton);
		btnCheckButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		btnCheckButton.setText("Extended");

		Button btnNewButton = new Button(compositeAll, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO set selected
				okPressed();
			}
		});
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(contentContainer, 6);
		fd_btnNewButton.left = new FormAttachment(btnCheckButton, 91);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("OK");

		Button btnCancel = new Button(compositeAll, SWT.NONE);
		fd_btnNewButton.right = new FormAttachment(btnCancel, -6);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// todo close
				cancelPressed();
			}
		});
		FormData fd_btnCancel = new FormData();
		fd_btnCancel.top = new FormAttachment(contentContainer, 6);
		fd_btnCancel.right = new FormAttachment(100, -10);
		fd_btnCancel.left = new FormAttachment(0, 329);
		btnCancel.setLayoutData(fd_btnCancel);
		btnCancel.setText("Cancel");
		addLayoutForMainContent();
		addButtonsContainer();
		createContents();
		showSimple();
	}

	private void initMap() {
		for (Bundle bundle : bundles) {
			nameBundleMap.put(
					BundleHelper.getStdlibVersion(bundle.getSymbolicName()),
					bundle);
		}
	}

	protected void showSimple() {
		deleteContentContainer();
		Group grpPlatform = new Group(contentContainer, SWT.SHADOW_IN);
		grpPlatform.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		grpPlatform.setText("Platform");
		creatGroupContainer(grpPlatform);
		contentContainer.layout(true);
	}

	private void creatGroupContainer(Group grpPlattorm) {
		int y = 25;
		for (Bundle bundle : bundles) {
			String shortName = BundleHelper.getStdlibVersion(bundle
					.getSymbolicName());
			Button btnRadioButton = new Button(grpPlattorm, SWT.RADIO);
			btnRadioButton.setBounds(26, y, 90, 20);
			btnRadioButton.setFont(SWTResourceManager.getFont("Segoe UI", 10,
					SWT.NORMAL));
			btnRadioButton.setSelection(false);
			if (shortName.equalsIgnoreCase(preselection)) {
				btnRadioButton.setSelection(true);
			}
			btnRadioButton.setText(shortName);
			btnRadioButton.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					selectedBundle = nameBundleMap.get(((Button) e.getSource())
							.getText());
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
			y += 25;
		}

	}

	protected void showExtended() {
		deleteContentContainer();
		Table table;

		final TableViewer plattformCheckTableViewer = new TableViewer(
				contentContainer, SWT.BORDER);
		table = plattformCheckTableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableViewerColumn tableViewerColumn = new TableViewerColumn(
				plattformCheckTableViewer, SWT.NONE);
		TableColumn tblclmnPlattform = tableViewerColumn.getColumn();
		tblclmnPlattform.setWidth(100);
		tblclmnPlattform.setText("Platform");
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Bundle bundle = (Bundle) element;
				return BundleHelper.getStdlibVersion(bundle.getSymbolicName());
			}
		});

		TableViewerColumn tableViewerVersionColumn = new TableViewerColumn(
				plattformCheckTableViewer, SWT.NONE);
		TableColumn versionCol = tableViewerVersionColumn.getColumn();
		versionCol.setWidth(100);
		versionCol.setText("Version");
		tableViewerVersionColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Bundle bundle = (Bundle) element;
				return BundleHelper.getStdlibVersion(bundle.getVersion()
						.toString());
			}
		});

		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				plattformCheckTableViewer, SWT.NONE);
		TableColumn tblclmnBundle = tableViewerColumn_1.getColumn();
		tblclmnBundle.setWidth(200);
		tblclmnBundle.setText("Bundle / Package");
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(final Object element) {
				final Bundle bundle = (Bundle) element;
				return bundle.getSymbolicName();
			}
		});		
		plattformCheckTableViewer.setContentProvider(ArrayContentProvider
				.getInstance());
		plattformCheckTableViewer.setInput(bundles);
		contentContainer.layout(true);
	}

	protected void addButtonsContainer() {

	}

	protected void addLayoutForMainContent() {

	}

	protected void deleteContentContainer() {
		for (Control cnt : getContentContainer().getChildren()) {
			cnt.dispose();
		}

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Change Hi Platform (STDLIB) version");
		setSize(450, 300);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	protected Composite getContentContainer() {
		return contentContainer;
	}

	protected void okPressed() {
		setReturnCode(OK);
		close();
	}

	public void close() {
		super.close();

	}

	protected void setReturnCode(int code) {
		returnCode = code;
	}

	protected void cancelPressed() {
		setReturnCode(CANCEL);
		close();
	}
}
