package org.modelica.mdt.ui.view;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public class ModelicaUnusedViewTab extends CTabItem
{
	private Table table;
	private TableViewer tableViewer;
	private int lastSortedColumn;
	private Label hintLabel;
	
	public Table getTable()
	{
		return table;
	}

	public ModelicaUnusedViewTab(CTabFolder parent, int style, String[] tableHeader, String hint, String tooltip)
	{
		super(parent, style);
		
		lastSortedColumn = -1;
		
		parent.setLayout(new GridLayout(4, false));

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
		composite.setLayout(new GridLayout(2, false));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.horizontalSpacing = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.verticalSpacing = 0;
		composite_1.setLayout(gl_composite_1);
		
		hintLabel = new Label(composite, SWT.NONE);
		hintLabel.setText(hint);
		hintLabel.setToolTipText(tooltip);
		
		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);

		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		// listener to jump to line in representing column in editor when table
		// is clicked

		table.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event e)
			{
				TableItem item = (TableItem) e.item;
				String s = item.getText(1);
				int pos = Integer.parseInt(s);

				IEditorPart actEdit = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
				if (!(actEdit instanceof org.modelica.mdt.ui.editor.ModelicaEditor))
					return;
				
				ISelectionProvider sel = ((org.modelica.mdt.ui.editor.ModelicaEditor) actEdit).getSelectionProvider();
				IDocument doc = ((org.modelica.mdt.ui.editor.ModelicaEditor) actEdit).getDocumentProvider().getDocument(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput());
				TextSelection sel2;
				try {
					sel2 = new TextSelection(doc.getLineOffset(pos), doc.getLineLength(pos));
					sel.setSelection(sel2);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		for (int i = 0; i < tableHeader.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(tableHeader[i]);
		}
		
		setControl(composite);
	}
	
	public int getLastSortedColumn()
	{
		return lastSortedColumn;
	}

	public void setLastSortedColumn(int lastSortedColumn)
	{
		this.lastSortedColumn = lastSortedColumn;
	}

	public void clearTable()
	{
		table.removeAll();
	}
	
	public void updateHint(String s)
	{
		hintLabel.setText(s);
	}
}
