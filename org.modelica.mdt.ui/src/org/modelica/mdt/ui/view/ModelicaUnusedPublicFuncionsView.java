package org.modelica.mdt.ui.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.text.ModelicaDocumentProvider;

public class ModelicaUnusedPublicFuncionsView extends ViewPart
{
	private Button filterButton, fastScanButton;
	private Button completeScanButton;
	private StyledText field, pathField;
	private String filterInput; // input from the textfield
	private static String pathInput;
	private boolean filterActive = false;
	/**
	 * [funcName,FileName] , functionpos
	 */
	private HashMap<Tuple<String, String>, functionPos> functionMap; // all
																		// functions
																		// are
																		// listed
																		// here
	/**
	 * list with all the *.mo files
	 */
	private ArrayList<IResource> res;
	private Table table, funcCallTable, funcGetCalledTable;
	private TableViewer tableViewer;
	private Label functionLabel, functionLabel2;
	private Group functionDetails;
	// private int lastSort = -1; //needed for sorting the table
	private Tuple<String, String> activeFunctionID = new Tuple<String, String>("", "");
	private int colcount; // needed for sorting the table
	private Button checkBox;
	private boolean showOnlyEmptyFunctions = false;

	public ModelicaUnusedPublicFuncionsView()
	{

		super();
		functionMap = new HashMap<Tuple<String, String>, functionPos>();
	}
	
	private class TableSelectionAdapter extends SelectionAdapter
	{
		private int index;
		private Table table;

		public TableSelectionAdapter(Table table, int index)
		{
			this.table = table;
			this.index = index;
		}

		public void widgetSelected(SelectionEvent event)
		{
			ModelicaUnusedView.sortTableByColumnIndex(table,index,-1);
		}
	}

	@Override
	public void createPartControl(Composite parent)
	{
		// create visible objects like buttons and so on
		parent.setLayout(new GridLayout(1, false));
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.HORIZONTAL;
		parent.setLayout(fillLayout);

		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayoutData(new FillLayout(SWT.HORIZONTAL | SWT.VERTICAL));
		composite.setLayout(new GridLayout(1, false));
	
		Composite leftComposite = new Composite(composite, SWT.BORDER);
		leftComposite.setLayoutData(new GridData(GridData.FILL, SWT.TOP, false, false));
		leftComposite.setLayout(new GridLayout(3, false));
		
		Composite leftComposite2 = new Composite(composite, SWT.BORDER);
		GridData tmpGridData = new GridData(GridData.FILL, GridData.FILL, true, true);
		tmpGridData.widthHint = 5000;
		tmpGridData.heightHint = 5000;
		leftComposite2.setLayoutData(tmpGridData);
		leftComposite2.setLayout(new GridLayout(1, false));
		
		Composite leftComposite3 = new Composite(composite, SWT.BORDER);
		leftComposite3.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, false, false));
		leftComposite3.setLayout(new GridLayout(3, false));
		
		functionDetails = new Group(parent, SWT.BORDER);
		functionDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		functionDetails.setLayout(new GridLayout(1, false));
		functionDetails.setText("Functiondetails");

		//-----------------------------------------------------FilterLabel
		Label filterLabel = new Label(leftComposite, SWT.NONE);
		filterLabel.setText("Filterstring: ");
		
		// ----------------------------------------------------INPUT

		field = new StyledText(leftComposite, SWT.BORDER);
		GridData textGrid = new GridData(SWT.BORDER);
		textGrid.widthHint = 85;
		textGrid.heightHint = 20;
		field.setLayoutData(textGrid);
		field.setMargins(2, 2, 2, 0);
		field.setMarginColor(new Color(null, 255, 0, 255));
		field.setToolTipText("the FilterAttribute");
		// ----------------------------------------------------PathInput

		pathField = new StyledText(leftComposite3, SWT.BORDER);
		GridData pathGrid = new GridData(SWT.BORDER);
		pathGrid.widthHint = 85;
		pathGrid.heightHint = 20;
		pathField.setLayoutData(pathGrid);
		pathField.setMargins(2, 2, 2, 0);
		pathField.setMarginColor(new Color(null, 255, 0, 255));
		pathField.setToolTipText("restrict the searchpath or all *.mo files in the project will be matched against each other wich will take a lot of time");
		pathField.setText("Compiler");

		// ----------------------------------------------------BUTTONS
		// ---%%%%%%%%%%%%%%%%%%%%%%---filterButton
		filterButton = new Button(leftComposite, 0);
		GridData filterGridData = new GridData();
		filterGridData.widthHint = 95;
		filterGridData.heightHint = 25;
		filterButton.setLayoutData(filterGridData);
		filterButton.setText("Refresh");
		
		Listener filterListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				filterActive = true;
				filterInput = field.getText().trim();
				refreshTable();
			}
		};
		
		filterButton.addListener(SWT.Selection, filterListener);
		
		// ---%%%%%%%%%%%%%%%%%%%%%%---fastScanButton
		fastScanButton = new Button(leftComposite3, 0);
		GridData fastGridData = new GridData();
		fastGridData.widthHint = 95;
		fastGridData.heightHint = 25;
		fastScanButton.setLayoutData(fastGridData);
		fastScanButton.setText("Fast Scan");
		fastScanButton.setToolTipText("Performs a fast search of all *.mo Files in the specified Path. A fast search will not check for comments, strings ect. so be careful how to use the results.");
		
		Listener fastListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				// createList();
				functionMap.clear();
				pathInput = pathField.getText().trim();
				searchFiles();
				fastUpdateFunctionDetails();
				addFunctionCalls();
				refreshTable();
			}
		};

		fastScanButton.addListener(SWT.Selection, fastListener);
			
		// ---%%%%%%%%%%%%%%%%%%%%%%---completeScanButton
		completeScanButton = new Button(leftComposite3, 0);
		GridData completeGridData = new GridData();
		completeGridData.widthHint = 125;
		completeGridData.heightHint = 25;
	
		completeScanButton.setLayoutData(completeGridData);
		completeScanButton.setText("Complete Scan");
		completeScanButton.setToolTipText("Performs a complete search of all *.mo Files in the specified Path.");
		
		Listener completeListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				// createList();
				functionMap.clear();
				pathInput = pathField.getText().trim();
				searchFiles();
				updateFunctionDetails();
				addFunctionCalls();
				refreshTable();
			}
		};

		completeScanButton.addListener(SWT.Selection, completeListener);

		// ----------------------------------------------------CheckBox

		checkBox = new Button(leftComposite, SWT.CHECK);
		checkBox.setText("show unused functions only");

		Listener checkBoxListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				showOnlyEmptyFunctions = !showOnlyEmptyFunctions;
			}
		};
		checkBox.addListener(SWT.Selection, checkBoxListener);

		
		// ----------------------------------------------------TABLE

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.horizontalSpacing = 0;
		gl_composite_1.marginHeight = 0;
		gl_composite_1.marginWidth = 0;
		gl_composite_1.verticalSpacing = 0;
		composite_1.setLayout(gl_composite_1);

		
		
		tableViewer = new TableViewer(leftComposite2, SWT.BORDER | SWT.FULL_SELECTION );

		tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			@Override
			public void doubleClick(DoubleClickEvent event)
			{
				event.getSelection().getClass();
				TableItem item = null;
				if (table.getSelectionIndex() != -1)
					item = table.getItem(table.getSelectionIndex());
				else
					return;
				Tuple<String, String> tpl = new Tuple<String, String>(item.getText(0), item.getText(1));
				functionPos pos = functionMap.get(tpl);
				activeFunctionID = new Tuple<String, String>(item.getText(0), pos.editorFile.getName());
//				if (pos == null)
//					return;
				jumpToFilePos(pos.editorFile,  pos.defStart + 1);
			}
		}); // end doubleClickListener

		GridData tableGrid = new GridData(SWT.FILL, SWT.FILL, true, true);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(tableGrid);

		// listener for onClick-events in the table
		table.addListener(SWT.Selection, new Listener()
		{
			public void handleEvent(Event e)
			{
				TableItem item = (TableItem) e.item;
				String s = item.getText(0);
				if (table.getSelectionIndex() != -1)
					item = table.getItem(table.getSelectionIndex());
				else
					return;
				Tuple<String, String> tpl = new Tuple<String, String>(item.getText(0), item.getText(1));
				functionPos pos = functionMap.get(tpl);
				activeFunctionID = new Tuple<String, String>(item.getText(0), pos.editorFile.getName());
				functionDetails.setText("Functiondetails - " + s);

				showFunctionData(pos);
			}
		});

		//tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		
		// -----------------------------------------------calledFunctions
		functionLabel = new Label(functionDetails, SWT.NONE);
		functionLabel.setText("Functions called:");
		functionLabel.setToolTipText("The list of all functions that are called in this function");
		// -----------------------------------------------Table for the functions called

		tableViewer = new TableViewer(functionDetails, SWT.FILL | SWT.FULL_SELECTION);

		tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			@Override
			public void doubleClick(DoubleClickEvent event)
			{
				TableItem item = null;
				if (funcCallTable.getSelectionIndex() != -1)
					item = funcCallTable.getItem(funcCallTable.getSelectionIndex());
				else
					return;

				functionPos pos = functionMap.get(activeFunctionID);
				int line = Integer.valueOf(item.getText(1));
				if (pos == null)
					return;
				jumpToFilePos(pos.editorFile, line + 1 );

			}
		});
		funcCallTable = tableViewer.getTable();


		funcCallTable.setHeaderVisible(true);
		funcCallTable.setLinesVisible(true);
		funcCallTable.setLayoutData(tableGrid);
		// -----------------------------------------------isCalled
		functionLabel2 = new Label(functionDetails, SWT.NONE);
		functionLabel2.setText("Calls to function:");
		functionLabel2.setToolTipText("All functions that call this function");
		// -----------------------------------------------Table for the
		// functions that call this function

		tableViewer = new TableViewer(functionDetails, SWT.FILL | SWT.FULL_SELECTION);

		tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			@Override
			public void doubleClick(DoubleClickEvent event)
			{

				event.getSelection().getClass();
				TableItem item = null;
				if (funcGetCalledTable.getSelectionIndex() != -1)
					item = funcGetCalledTable.getItem(funcGetCalledTable.getSelectionIndex());
				else
					return;

				String[] tmp = item.getText(0).split("\\.");
				Tuple<String, String> tpl;
				if (tmp.length == 1)
					tpl = new Tuple<String, String>(item.getText(0), activeFunctionID.y);
				else
					tpl = new Tuple<String, String>(tmp[tmp.length - 1], tmp[0] + ".mo");
				functionPos pos = functionMap.get(tpl);
				if (pos == null)
					return;
				int line = Integer.parseInt(item.getText(1));
				jumpToFilePos(pos.editorFile, line+1);
			}
		}); // end doubleClickListener

		funcGetCalledTable = tableViewer.getTable();
		funcGetCalledTable.setHeaderVisible(true);
		funcGetCalledTable.setLinesVisible(true);
		funcGetCalledTable.setLayoutData(tableGrid);

	}

	/**
	 * jump to the line in the file (has to be a *.mo file)
	 * 
	 * @param file
	 * @param line
	 */
	
	private void jumpToFilePos(IFile file, int line)
	{
		if ( (file == null) || (line < 0) )
			return;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(IMarker.LINE_NUMBER, line);
		map.put(IDE.EDITOR_ID_ATTR, "org.eclipse.ui.ModellicaEditor");
		try {
			IMarker marker;
			marker = file.createMarker(IMarker.TEXT);
			marker.setAttributes(map);
			IDE.openEditor(getSite().getWorkbenchWindow().getActivePage(), marker);
			marker.delete();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * search for mo files and then search all files for functions and add them
	 * to the functionMap
	 */
	public void searchFiles()
	{
		functionMap.clear();
		IProject activeProject = null;
		// get all Lines of all Files and search for specific function
		// get Name of active Project
		IEditorPart editorPart = getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
		if (editorPart != null) {
			IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
			IFile tmpFile = input.getFile();
			activeProject = tmpFile.getProject();
			res = new ArrayList<IResource>();
			res = getMoFiles(activeProject);

			// got all Files from the project, iterate through them

			ModelicaEditor edit = new ModelicaEditor();
			IDocumentProvider provider = new ModelicaDocumentProvider();
			
			for (int i = 0; i < res.size(); i++) {
				// Start with check if the file is a *.mo file
				if (!res.get(i).getName().endsWith(".mo"))
					continue;
				
				IFile file = ((IFile) res.get(i));
				// all Lines of the files are loaded in the list
				// start searching for function
				try {
					provider.connect(file);
				} catch (CoreException e) {
					e.printStackTrace();
				}
				IDocument doc = provider.getDocument(file);

				ArrayList<String[]> StringList = edit.getFunctions(doc);
				for (int j = 0; j < StringList.size(); j++) {
					String[] tmpVal = StringList.get(j);
					functionPos func = new functionPos(tmpVal[0], res.get(i).getName(), Integer.valueOf(tmpVal[1]), Integer.valueOf(tmpVal[2]));
					func.editorFile = file;
					try {
						func.text = doc.get(doc.getLineOffset(Integer.valueOf(tmpVal[1])), doc.getLineOffset(Integer.valueOf(tmpVal[2])) - doc.getLineOffset(Integer.valueOf(tmpVal[1])));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					// check if only special chars
					if (tmpVal[0].replaceAll("[^a-zA-Z0-9]", "").equals(""))
						continue;
					functionMap.put(new Tuple<String, String>(tmpVal[0], file.getName()), func);
				}

			}
		}
	}

	/**
	 * returns a list with all the .mo files in the project, uses the public
	 * variable pathInput to specific the searchpath
	 * 
	 * @param activeProject
	 * @return
	 */
	public static ArrayList<IResource> getMoFiles(IProject activeProject)
	{
		ArrayList<IResource> files = new ArrayList<IResource>();
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		IPath path = activeProject.getLocation();

		if (path.toString().contains(pathInput))
			findFiles(files, path, myWorkspaceRoot, true);
		else
			findFiles(files, path, myWorkspaceRoot, false);
		return files;
	}

	/**
	 * find all the files in the specific directory that end with .mo
	 * 
	 * @param files
	 * @param path
	 * @param myWorkspaceRoot
	 * @param subPath
	 */
	private static void findFiles(ArrayList<IResource> files, IPath path, IWorkspaceRoot myWorkspaceRoot, boolean subPath)
	{
		IContainer container = myWorkspaceRoot.getContainerForLocation(path);

		boolean wasFound = subPath;
		try {
			IResource[] iResources;
			iResources = container.members();
			for (IResource iR : iResources) {

				if (("mo".equalsIgnoreCase(iR.getFileExtension())) && (subPath == true))
					files.add(iR);
				if (iR.getType() == IResource.FOLDER) {
					IPath tempPath = iR.getLocation();
					if (tempPath.toString().contains(pathInput)) {
						wasFound = true;
						findFiles(files, tempPath, myWorkspaceRoot, true);
					} else if (wasFound) // when specific folder was already
											// found -> break out of the loop
						break;
					else
						findFiles(files, tempPath, myWorkspaceRoot, false);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setFocus()
	{

	}

	/**
	 * shows the data of the function that was clicked on in the function table
	 * (the one on the left) the data is displayed in the two small tables on
	 * the right side of the view
	 * 
	 * @param func
	 */
	private void showFunctionData(functionPos func)
	{
		funcCallTable.removeAll();
		String[] titles = { "Function", "Line" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(funcCallTable, SWT.NONE);
			column.setText(titles[i]);
			TableSelectionAdapter adapt = new TableSelectionAdapter(funcCallTable, i);
			column.addSelectionListener(adapt);
		}

		for (int i = 0; i < func.functionCalls.size(); i++) {
			TableItem item = new TableItem(funcCallTable, SWT.NONE);
			item.setText(0, ((String) func.functionCalls.get(i).x));
			item.setText(1, Integer.toString(func.functionCalls.get(i).y));
		}

		for (int i = 0; i < titles.length; i++) {
			funcCallTable.getColumn(i).pack();
		}

		funcGetCalledTable.removeAll();
		// other table
		String[] titles2 = { "Function", "Line", "file" };
		for (int i = 0; i < titles2.length; i++) {
			TableColumn column = new TableColumn(funcGetCalledTable, SWT.NONE);
			column.setText(titles2[i]);
			TableSelectionAdapter adapt = new TableSelectionAdapter(funcGetCalledTable, i);
			column.addSelectionListener(adapt);
		}

		Iterator<Entry<String, ArrayList<Integer>>> it = func.occurrencies.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, ArrayList<Integer>> pair = it.next();

			for (int i = 0; i < pair.getValue().size(); i++) {
				TableItem item = new TableItem(funcGetCalledTable, SWT.NONE);
				item.setText(0, pair.getKey());

				item.setText(1, Integer.toString(pair.getValue().get(i)));

				item.setText(2, pair.getKey().split("\\.")[0] + ".mo");
			}
		}

		for (int i = 0; i < titles2.length; i++) {
			funcGetCalledTable.getColumn(i).pack();
		}
	}

	/**
	 * searches for functions that are called in all functions, faster but not
	 * as precise
	 */
	private void fastUpdateFunctionDetails()
	{
		if (((functionMap == null) || (functionMap.isEmpty())))
			return;

		Pattern p = Pattern.compile("\n");

		Iterator<Entry<Tuple<String, String>, functionPos>> it = functionMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Tuple<String, String>, functionPos> pair = it.next();
			int offset = pair.getValue().defStart;
			String text = pair.getValue().text;
			String[] parts = text.split("\\(");
			if (parts != null)
				for (int i = 1; i < parts.length; i++) {
					String tmpPart = parts[i - 1].replaceAll("\\s+", "");

					Matcher m = p.matcher(parts[i - 1]);
					while (m.find()) {
						offset += 1;
					}

					// check for keywords
					if ((tmpPart.endsWith(";")) || (tmpPart.endsWith("algorithm")) || (tmpPart.endsWith("case")) || (tmpPart.endsWith("then")) || (tmpPart.endsWith("else")) || (tmpPart.endsWith("if")) || (tmpPart.endsWith("annotation")) || (tmpPart.endsWith("NONE") || (tmpPart.endsWith("SOME"))) || (tmpPart.endsWith("matchcontinue")) || (tmpPart.endsWith("match")) || (tmpPart.endsWith("fail")) || (tmpPart.endsWith("failure")))
						continue;
					// last word is function, add to list
					String[] tmpParts = parts[i - 1].trim().split("\\s+");

					String funcName = tmpParts[tmpParts.length - 1];
					if (!funcName.matches("^[a-zA-Z0-9_.-]*$") || (funcName.equals("")))
						continue;

					if (funcName.replaceAll("[^a-zA-Z0-9]", "").equals(""))
						continue;

					Matcher matcher = Pattern.compile("//.*" + funcName + "\\n").matcher(text);
					int pos2 = matcher.find() ? matcher.start() : -1;

					if ((pos2 != -1))
						continue;

					pair.getValue().functionCalls.add(new Triple<String, Integer, functionPos>(funcName, offset, null));
				}

		}
	}

	/**
	 * searches for functions that are called in all functions
	 */
	private void updateFunctionDetails()
	{
		if (((functionMap == null) || (functionMap.isEmpty())))
			return;

		Iterator<Entry<Tuple<String, String>, functionPos>> it = functionMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Tuple<String, String>, functionPos> pair = it.next();
			String text = pair.getValue().text;

			// [\.A-Za-z0-9_]+\s*\([^\)]*\)
			Matcher matcher = Pattern.compile("[\\.A-Za-z0-9_]+\\s*\\(").matcher(text);
			while (matcher.find()) {

				int offset = pair.getValue().defStart;

				String s1 = text.substring(matcher.start(), matcher.end() - 1);
				s1 = s1.trim();
				if ((s1.equals(";")) || (s1.equals("algorithm")) || (s1.equals("case")) || (s1.equals("then")) || (s1.equals("else")) || (s1.equals("if")) || (s1.equals("annotation")) || (s1.equals("NONE") || (s1.equals("SOME"))) || (s1.equals("matchcontinue")) || (s1.equals("match")) || (s1.equals("fail")) || (s1.equals("failure")))
					continue;

				// check for comments, pattern for multiline comment
				Matcher commentMatcher = Pattern.compile("(//.*|\"[^\"]*\")").matcher(text);
				boolean comment = false;
				while (commentMatcher.find() && (!comment)) {
					if ((commentMatcher.start() < matcher.start()) && (commentMatcher.end() > matcher.end()))
						comment = true;
				}
				if (comment)
					continue;

				// get line
				boolean found = false;
				Matcher lineMatcher = Pattern.compile("\n").matcher(text);
				while (lineMatcher.find() && (found == false)) {
					if (lineMatcher.end() > matcher.end())
						found = true;
					else
						offset++;
				}
				pair.getValue().functionCalls.add(new Triple<String, Integer, functionPos>(s1, offset, null));
			}

		}

	}

	/**
	 * creates the input for the table
	 */

	public void refreshTable()
	{
		table.removeAll();

		String[] titles = { "Name", "File" };
		colcount = titles.length;
		for (int i = 0; i < colcount; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			TableSelectionAdapter adapt = new TableSelectionAdapter(table, i);
			column.addSelectionListener(adapt);
		}

		if (!((functionMap == null) || (functionMap.isEmpty()))) {
			Iterator<Entry<Tuple<String, String>, functionPos>> it = functionMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Tuple<String, String>, functionPos> pair = it.next();
				if (filterInput != null)
					if (!(filterInput.equals("")) && (filterActive) && (!pair.getValue().name.contains(filterInput)) && (!pair.getValue().defFile.contains(filterInput)))
						continue;
				if (showOnlyEmptyFunctions) {
					if (!pair.getValue().occurrencies.isEmpty())
						continue;
				}
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(0, pair.getValue().name);
				item.setText(1, pair.getValue().defFile);
			}
		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}

		filterActive = false;
	}

	/**
	 * for each function inn the functionMap create the List with from where it
	 * is called
	 */
	private void addFunctionCalls()
	{

		Iterator<Entry<Tuple<String, String>, functionPos>> it = functionMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Tuple<String, String>, functionPos> pair = it.next();

			// if functions are called
			if (!pair.getValue().functionCalls.isEmpty()) {
				for (int i = 0; i < pair.getValue().functionCalls.size(); i++) {
					String[] tmp = pair.getValue().functionCalls.get(i).x.split("\\.");
					Tuple<String, String> tpl;

					if (tmp.length <= 1)
						tpl = new Tuple<String, String>(tmp[0], pair.getValue().editorFile.getName());
					else
						tpl = new Tuple<String, String>(tmp[tmp.length - 1], tmp[0] + ".mo");

					functionPos calledFunc = functionMap.get(tpl);
					if (calledFunc == null)
						continue;

					calledFunc.addOccurrencey(pair.getValue().editorFile.getName().split("\\.")[0] + "." + pair.getValue().name, pair.getValue().functionCalls.get(i).y);

				}

			}
		}
	}

	/**
	 * this class will handle all informations about where and how often a
	 * function is called. It stores the name, the file where the function is
	 * defined and the offests in this file as well as all the occurencies
	 */
	public class functionPos
	{
		/**
		 * name of the function
		 */
		public String name;
		/**
		 * the file where the function is defined
		 */
		public String defFile;
		/**
		 * the offset in the file where the function definition starts
		 */
		public int defStart;
		/**
		 * the end of the function definition in the file where the function is
		 * defined (offset)
		 */
		public int defEnd;
		/**
		 * mapping all occurrencies of the function to the name of the file
		 */
		public HashMap<String, ArrayList<Integer>> occurrencies;

		/**
		 * a list with all the functions that are called in this function
		 * functionPos can be NULL, if a fast search was used or the called
		 * function was not found in the searched files
		 */
		public ArrayList<Triple<String, Integer, functionPos>> functionCalls;

		public IFile editorFile;

		/**
		 * The text of the function
		 */
		public String text;

		/**
		 * initialises a new object for a function
		 * 
		 * @param n
		 *            - name of the function
		 * @param f
		 *            - name of the file where the function is found
		 * @param sOffset
		 *            - startLine in the file (of the function definition)
		 * @param eOffset
		 *            - endLine in the file (of the function definition)
		 */
		public functionPos(String n, String f, int sLine, int eLine)
		{
			name = n;
			defFile = f;
			defStart = sLine;
			defEnd = eLine;
			occurrencies = new HashMap<String, ArrayList<Integer>>();
			functionCalls = new ArrayList<Triple<String, Integer, functionPos>>();
		}

		/**
		 * adds an occurrence to the list
		 * 
		 * @param file
		 *            - the name of the file
		 * @param offset
		 *            - the offset
		 */
		public void addOccurrencey(String func, int offset)
		{
			// if the occurence is the definition -> return
			String[] split = func.split("\\.");
			if (split.length > 1) {
				if (name.equals(split[1]))
					return;
			} else if (func.equals(name))
				return;
			// if not existing, create new key
			if (!occurrencies.containsKey(func)) {
				ArrayList<Integer> tmpColect = new ArrayList<Integer>();
				tmpColect.add(offset);
				occurrencies.put(func, tmpColect);
			}
			// if existing, add to colection
			else {
				ArrayList<Integer> tmpColect = occurrencies.get(func);
				tmpColect.add(offset);
			}
		}
	}

	public class Tuple<X, Y>
	{
		public final X x;
		public final Y y;

		public Tuple(X x, Y y)
		{
			this.x = x;
			this.y = y;
		}

		public int hashCode()
		{

			if ((y.getClass().equals(String.class)) && (x.getClass().equals(String.class))) {
				int r = 0;
				for (int i = 0; i < ((String) x).length(); i++) {
					r += ((String) x).charAt(i);
				}
				for (int j = 0; j < ((String) y).length(); j++) {
					r += ((String) y).charAt(j);
				}

				return r;
			}
			return x.hashCode() + y.hashCode();
		}

		public boolean equals(Object o)
		{
			if (!o.getClass().equals(this.getClass()))
				return false;

			@SuppressWarnings("unchecked")
			Tuple<X,Y> tpl = ((Tuple<X,Y>) o);
			return (tpl.x.equals(x) && tpl.y.equals(y));

		}

		public int compareTo(Object o)
		{
			if (!o.getClass().equals(this.getClass()))
				return -1;

			return 1;
		}
	}

	public class Triple<X, Y, Z>
	{
		public final X x;
		public final Y y;
		public final Z z;

		public Triple(X x, Y y, Z z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}