package com.shs.hl.debug.ui.views;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

public class WatchWindow extends ViewPart {

	public static final String ID = "com.shs.hl.debug.ui.views.WatchWindow"; //$NON-NLS-1$
	private Table variableTable;
	private final List<DebugVariable> finput = new ArrayList<DebugVariable>();
	private TableViewer checkboxTableViewer;
	private final Map<String, DebugVariable> tableInputMap = new HashMap<String, DebugVariable>();

	public WatchWindow() {

	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		checkboxTableViewer = new TableViewer(container, SWT.BORDER
				| SWT.FULL_SELECTION);
		variableTable = checkboxTableViewer.getTable();
		variableTable.setHeaderVisible(true);
		variableTable.setLinesVisible(true);
		{
			TableViewerColumn tableViewerColumn = new TableViewerColumn(
					checkboxTableViewer, SWT.NONE);
			TableColumn tblclmnVariable = tableViewerColumn.getColumn();
			tblclmnVariable.setWidth(241);
			tblclmnVariable.setText("Variable");
			tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					return ((DebugVariable) element).getName();
				}
			});
			tableViewerColumn.setEditingSupport(new ColumnEditingSupport(
					checkboxTableViewer));
		}
		{
			TableViewerColumn tableViewerColumn = new TableViewerColumn(
					checkboxTableViewer, SWT.NONE);
			TableColumn tblclmnValue = tableViewerColumn.getColumn();
			tblclmnValue.setWidth(253);
			tblclmnValue.setText("Value");
			tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(Object element) {
					return ((DebugVariable) element).getValue();
				}
			});
		}

		Menu menu = new Menu(variableTable);
		variableTable.setMenu(menu);

		MenuItem mntmAddVariable = new MenuItem(menu, SWT.NONE);
		mntmAddVariable.setText("Add Variable");
		mntmAddVariable.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewVariable();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		createCellEditors();
		createActions();
		initializeToolBar();
		initializeMenu();
		setupDummyData();
		initDataBindings();
		addDataListeners(parent);
	}

	private void addDataListeners(final Composite parent) {
		BundleContext ctx = FrameworkUtil.getBundle(WatchWindow.class)
				.getBundleContext();
		EventHandler handler = new EventHandler() {
			@Override
			public void handleEvent(final Event event) {
				parent.getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						for (Entry<String, DebugVariable> var : tableInputMap
								.entrySet()) {
							String key = var.getKey();
							if (event.containsProperty(key)) {
								var.getValue().setValue(
										(String) event.getProperty(key));
							}
						}
						getVariableTableViewer().refresh();
					}
				});
			}

		};

		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(EventConstants.EVENT_TOPIC, "dataUpdate/*");
		ctx.registerService(EventHandler.class, handler, properties);

	}

	private void addNewVariable() {
		finput.add(new DebugVariable("changeMe", ""));
		getVariableTableViewer().refresh();
	}

	private void createCellEditors() {
		TextCellEditor[] editors = new TextCellEditor[2];
		TextCellEditor nameEditor = new TextCellEditor(variableTable);
		editors[0] = nameEditor;
		TextCellEditor valueEditor = new TextCellEditor(variableTable);
		editors[1] = valueEditor;
		getVariableTableViewer().setCellEditors(editors);

	}

	private void setupDummyData() {
		tableInputMap.put("env:HiDriverWorkMode", new DebugVariable(
				"env:HiDriverWorkMode", "value1"));
		tableInputMap.put("env:Prog", new DebugVariable("env:Prog", "value90"));
		tableInputMap
				.put("env:Sides", new DebugVariable("env:Sides", "value7"));
		tableInputMap.put("hi:d8PFcoSpcEn", new DebugVariable("hi:d8PFcoSpcEn",
				"value4"));
		tableInputMap.put("cap:PricePoint", new DebugVariable("cap:PricePoint",
				"300"));
		finput.addAll(tableInputMap.values());
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		getViewSite().getActionBars().getToolBarManager();

	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	public TableViewer getVariableTableViewer() {
		return checkboxTableViewer;
	}

	protected DataBindingContext initDataBindings() {
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(
				listContentProvider.getKnownElements(), DebugVariable.class,
				new String[] { "name", "value" });
		checkboxTableViewer.setLabelProvider(new ObservableMapLabelProvider(
				observeMaps));
		checkboxTableViewer.setContentProvider(listContentProvider);

		IObservableList selfList = Properties.selfList(DebugVariable.class)
				.observe(finput);
		checkboxTableViewer.setInput(selfList);

		return null;
	}

	class ColumnEditingSupport extends EditingSupport {

		public ColumnEditingSupport(TableViewer viewer) {
			super(viewer);

		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(((TableViewer) getViewer()).getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			return ((DebugVariable) element).getName();
		}

		@Override
		protected void setValue(Object element, Object value) {
			((DebugVariable) element).setName((String) value);
			getViewer().refresh();
		}

	}

}
