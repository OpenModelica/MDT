package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.modelica.mdt.internal.core.CorePlugin;

public class ModelicaUnusedView extends ViewPart
{
	private ModelicaUnusedViewTab unusedVarsTabItem;
	private ModelicaUnusedViewTab unusedFuncTabItem;
	private ModelicaUnusedViewTab wrongDocTabItem;
	private CTabFolder folder;
	private IEditorPart activeEditor;
	private String documentationRegEx = CorePlugin.getDefault().getPreferenceStore().getString("FUNCDocTestRegEx"); // ".+author:.+\\d{4}-\\d{2}.+";
	private String documentationText = "Search for wrongly documented functions. The used Regex for the check: ";
	
	private class TableSelectionAdapter extends SelectionAdapter
	{
		private int index;
		private ModelicaUnusedViewTab unusedTab;

		public TableSelectionAdapter(ModelicaUnusedViewTab unusedTab, int index)
		{
			this.unusedTab = unusedTab;
			this.index = index;
		}

		public void widgetSelected(SelectionEvent event)
		{
			unusedTab.setLastSortedColumn(sortTableByColumnIndex(unusedTab.getTable(), index, unusedTab.getLastSortedColumn()));
		}
	}

	public enum TabHeaderType {
		UNUSED_FUNCTIONS, UNUSED_VARIABLES, WRONG_DOCUMENTATION
	};

	public ModelicaUnusedView()
	{
		super();
		activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}

	public void setFocus()
	{
		refreshTables();
	}

	protected void refreshTables()
	{
		refreshViewTab(unusedVarsTabItem, TabHeaderType.UNUSED_VARIABLES);
		refreshViewTab(unusedFuncTabItem, TabHeaderType.UNUSED_FUNCTIONS);
		refreshViewTab(wrongDocTabItem, TabHeaderType.WRONG_DOCUMENTATION);
	}

	@Override
	public void createPartControl(Composite parent)
	{
		Composite shell = new Composite(parent, SWT.NONE);
		shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		shell.setLayout(new GridLayout(4, true));

		folder = new CTabFolder(shell, SWT.TOP);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		folder.setLayoutData(data);
		
		String[] varsHeader = { "Name", "Line", "Type", "Function" };
		unusedVarsTabItem = new ModelicaUnusedViewTab(folder, SWT.NONE, varsHeader, "Search for unused variables.", "");
		for (int i = 0; i < unusedVarsTabItem.getTable().getColumnCount(); i++) {
			TableSelectionAdapter adapt = new TableSelectionAdapter(unusedVarsTabItem, i);
			unusedVarsTabItem.getTable().getColumn(i).addSelectionListener(adapt);
		}
		unusedVarsTabItem.setText("Unused Variables (0)");
		
		String[] funcsHeader = { "Name", "Startline", "Endline" };
		unusedFuncTabItem = new ModelicaUnusedViewTab(folder, SWT.NONE, funcsHeader, "Search for unused protected functions.", "");
		for (int i = 0; i < unusedFuncTabItem.getTable().getColumnCount(); i++) {
			TableSelectionAdapter adapt = new TableSelectionAdapter(unusedFuncTabItem, i);
			unusedFuncTabItem.getTable().getColumn(i).addSelectionListener(adapt);
		}
		unusedFuncTabItem.setText("Unused Functions (0)");
		
		
		String[] wrongDocHeader = { "Name", "Startline", "Endline" };
		wrongDocTabItem = new ModelicaUnusedViewTab(folder, SWT.NONE, wrongDocHeader, documentationText + documentationRegEx, "example: \"gets the stateVars from the list of vars. author:Waurich TUD 2013-06\"");
		for (int i = 0; i < wrongDocTabItem.getTable().getColumnCount(); i++) {
			TableSelectionAdapter adapt = new TableSelectionAdapter(wrongDocTabItem, i);
			wrongDocTabItem.getTable().getColumn(i).addSelectionListener(adapt);
		}
		wrongDocTabItem.setText("Wrong documented Functions (0)");

		Button refreshButton = new Button(shell, 0);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gridData.widthHint = 85;
		gridData.heightHint = 25;
		refreshButton.setLayoutData(gridData);
		refreshButton.setText("Refresh");

		Listener refreshListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				refreshTables();
			}
		};

		refreshButton.addListener(SWT.Selection, refreshListener);
		folder.setSelection(0);
		refreshTables();
	}

	public void updateTabItemHeader(TabHeaderType headerType, int numberOfWrongPlaces)
	{
		switch (headerType) {
		case UNUSED_VARIABLES:
			unusedVarsTabItem.setText("Unused Variables (" + Integer.toString(numberOfWrongPlaces) + ")");
			break;
		case UNUSED_FUNCTIONS:
			unusedFuncTabItem.setText("Unused Functions (" + Integer.toString(numberOfWrongPlaces) + ")");
			break;
		case WRONG_DOCUMENTATION:
			wrongDocTabItem.setText("Wrong Documentation (" + Integer.toString(numberOfWrongPlaces) + ")");
			break;
		}
	}

	protected void refreshViewTab(ModelicaUnusedViewTab viewTab, TabHeaderType tableType)
	{
		Table table = viewTab.getTable();
		table.removeAll();
		activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		if (!(activeEditor instanceof org.modelica.mdt.ui.editor.ModelicaEditor)) {
			updateTabItemHeader(tableType, 0);
			return;
		}

		List<String[]> list = new ArrayList<String[]>();
		int i;
		TableItem item;
		switch (tableType) {
		case UNUSED_FUNCTIONS:
			list = ((org.modelica.mdt.ui.editor.ModelicaEditor) activeEditor).getUnusedFunctions();
			for (i = 0; i < list.size(); i++) {
				item = new TableItem(table, SWT.NONE);
				item.setText(0, list.get(i)[0]);
				item.setText(1, list.get(i)[1]);
				item.setText(2, list.get(i)[2]);
			}
			break;
		case UNUSED_VARIABLES:
			list = ((org.modelica.mdt.ui.editor.ModelicaEditor) activeEditor).getUnusedVariables();
			for (i = 0; i < list.size(); i++) {
				item = new TableItem(table, SWT.NONE);
				item.setText(0, list.get(i)[0]);
				item.setText(1, Integer.toString(Integer.parseInt(list.get(i)[1]) + 1));
				item.setText(2, list.get(i)[2]);
				item.setText(3, list.get(i)[3]);
			}
			break;
		case WRONG_DOCUMENTATION:
			
			documentationRegEx = CorePlugin.getDefault().getPreferenceStore().getString("FUNCDocTestRegEx");
			wrongDocTabItem.updateHint(documentationText + documentationRegEx);
			
			list = ((org.modelica.mdt.ui.editor.ModelicaEditor) activeEditor).getWrongFunctionDoc(documentationRegEx);
			for (i = 0; i < list.size(); i++) {
				item = new TableItem(table, SWT.NONE);
				item.setText(0, list.get(i)[0]);
				item.setText(1, list.get(i)[1]);
				item.setText(2, list.get(i)[2]);
			}
			
			break;
		default:
			break;
		}
		
		updateTabItemHeader(tableType, list.size());

		for (i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(i).pack();
		}
	}

	public void dispose()
	{
		super.dispose();
	}

	public static int sortTableByColumnIndex(Table table, final int columnIndex, int lastSortedColumnIndex)
	{
		int sortedColumnIndex = columnIndex;
		TableItem[] items = table.getItems();
		
		if(items.length == 0)
			return sortedColumnIndex;
		
		// we need the int check, otherwise "145" is greater than "1453"
		final boolean isInt = items[0].getText(columnIndex).matches("^-?\\d+$");
		List<TableItem> sortedItems = Arrays.asList(items);
		Collections.sort(sortedItems, new Comparator<TableItem>()
			{
				@Override
				public int compare(TableItem o1, TableItem o2)
				{
					if(o1 == null)
						return -1;
					if(o2 == null)
						return 1;
					
					if(isInt)
						return Integer.valueOf(o1.getText(columnIndex)).compareTo(Integer.valueOf(o2.getText(columnIndex)));
					else
						return o1.getText(columnIndex).compareTo(o2.getText(columnIndex));
				}	
			});
		if(columnIndex == lastSortedColumnIndex)
		{
			Collections.reverse(sortedItems);
			sortedColumnIndex *= -1;
		}
		
		for (TableItem tableItem : sortedItems) {
			TableItem newItem = new TableItem(table, SWT.NONE);
			for (int c = 0; c < table.getColumnCount(); c++) {
				newItem.setText(c, tableItem.getText(c));
			}
		}
		
		table.remove(0, items.length);
		
		return sortedColumnIndex;
	}
}