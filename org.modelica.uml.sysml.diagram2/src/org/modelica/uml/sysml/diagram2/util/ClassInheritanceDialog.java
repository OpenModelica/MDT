package org.modelica.uml.sysml.diagram2.util;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.custom.MovementEvent;
import org.eclipse.swt.custom.MovementListener;
import org.eclipse.swt.custom.PaintObjectListener;
import org.eclipse.swt.custom.PaintObjectEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.awt.*;
import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.ModelicaParserException;
import org.modelica.mdt.core.compiler.ModelicaParser;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.uml.sysml.diagram2.part.SysmlInheritanceDiagramAction;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.tree.*;
import java.util.Hashtable;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class ClassInheritanceDialog extends Dialog{
	
	private static org.eclipse.swt.graphics.Image swtIcon = null;
	
	private static DirectoryDialog dirDialog = null;

	private static ModelicaClassLocation[] selClasses = null;
	
	public ClassInheritanceDialog(Shell parent, int style) {
		super(parent, style);
		this.setText("Select classes to generate inheritance hierarchy for.");
	}

	 /**
	   * Opens the dialog and returns the selected class
	   * 
	   * @return String
	   */
	  public ModelicaClassLocation[] open() {
	    // Create the dialog window
	    Shell shell = new Shell(getParent());
	    selClasses = null;
//	    selClasses = {""};
	    String[] retValues = {""};
	    GridLayout layout = new GridLayout(-1, true);
	    layout.makeColumnsEqualWidth = true;
//	    layout.
	    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	    
//	 	shell.setLayoutData(gridData);
	 	
//	    shell.setLayout(layout);
//	    shell.setSize (4000, 2000);
	    shell.setBounds(100, 100, 800, 500);
	    shell.setText(getText());
	    
	    createContents(shell);

	    shell.open();
	    Display display = getParent().getDisplay();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	
	    return selClasses;
	  }
	  
	  
	  /**	
	   * Creates the dialog's contents
	   * 
	   * @param shell the dialog window
	   */
	  private void createContents(final Shell shell) {
	
		  FileSystemView view = FileSystemView.getFileSystemView();
		  
		  String str = "C:\\OpenModelica1.4.4\\ModelicaLibrary";
		  File file = null;
		  File pptFile = null, pdfFile = null;	  
		  
		  try {
		  file = new File(str);
//		  pptFile = File.createTempFile("test", ".ppt");
//		  pdfFile = File.createTempFile("test", ".pdf");
		  }
		  catch(Exception e) {}
		 
		  swtIcon = getSystemSwtIcon(file);
		  int adressX = 0, adressY = 0;
		  int adressWidth = 430, adressHeight = 3*swtIcon.getBounds().height/2;

		  final StyledText dirPath = new StyledText(shell, SWT.SINGLE|SWT.BORDER);
		  dirPath.setEditable(false);
		  dirPath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	
		
		  dirPath.setText(" " + str);
		  dirPath.setBounds(adressX,adressY,adressWidth,adressHeight);
//		  dirPath.setLineSpacing(0);
//		  dirPath.setTopIndex(0);
//		  dirPath.setTopPixel(10);
		  dirDialog = new DirectoryDialog(shell);
		  dirDialog.setFilterPath(str);
		  
		  final StyleRange style = new StyleRange ();
		  style.start = 0;
		  style.length = 1;
		  Rectangle rect = swtIcon.getBounds();
		  style.metrics = new GlyphMetrics(rect.height, 0, rect.width);
		  dirPath.setStyleRange(style);

		  dirPath.addPaintObjectListener(new PaintObjectListener() {
			  public void paintObject(PaintObjectEvent event) {
				  GC gc = event.gc;
				  StyleRange style = event.style;
				  int start = style.start;
				  org.eclipse.swt.graphics.Image image = swtIcon;
				  int x = event.x;
				  int y = event.y + event.ascent - style.metrics.ascent;						
				  gc.drawImage(image, x, y);
//				  dirPath.setTopPixel(10);
			  }
		  });
			
		  final DefaultMutableTreeNode top = new DefaultMutableTreeNode("");
		  
		  int adressChooserW = adressHeight, adressChooserH = adressHeight;
		  Button dropButton = new Button(shell, SWT.ARROW|SWT.DOWN);
		  dropButton.setBounds(adressX + adressWidth, adressY, adressChooserW, adressChooserH);
		  
		  File[] files = getFiles(file); 
		  
		  top.removeAllChildren();
		  for (int i = 0; i < files.length; i++)
			  top.add(new FileNode(files[i]));

		  DefaultTreeCellRenderer renderer = new FileNodeRenderer();

		  final JTree directoryView = new JTree(top);
		  directoryView.setCellRenderer(renderer);
		  directoryView.setRootVisible(false);

		  JScrollPane dirViewPane = new JScrollPane(directoryView);
		  final Composite composite = new Composite(shell,SWT.EMBEDDED);
		  int compositeW = 280 + adressChooserW, compositeH = 400;
		  composite.setBounds(adressX, adressY + adressHeight + 5, compositeW, compositeH);
		  Frame frame = SWT_AWT.new_Frame(composite);
		  Panel panel = new Panel(new java.awt.GridLayout(1,1)) ;
		  int dvH = 300, dvW = 300;
		  panel.setSize(dvW, dvH);
		  JRootPane root = new JRootPane();
		  root.setSize(dvW, dvH);
		  Container contentPane = root.getContentPane();

		  contentPane.setLayout(new java.awt.GridLayout(1,1));
		  contentPane.setSize(dvW, dvH);

		  contentPane.add(dirViewPane);
		  dirViewPane.setSize(dvW, dvH);

		  panel.add(root);
		  frame.add(panel);
		  
		  Label directoryFiles = new Label(shell, SWT.CENTER);
		  int dfX = adressX + 5, dfY = adressY + adressHeight + compositeH + 8;
		  int dfW = compositeW, dfH = 15; 
		  directoryFiles.setBounds(dfX, dfY, dfW, dfH);
		  directoryFiles.setText("Directory files");
		  
		  dropButton.addSelectionListener(new SelectionAdapter() {
			  public void widgetSelected(SelectionEvent e) {		
				  String path = dirDialog.open();
				  File folder = new File(path);
				  File[] files = getFiles(folder); 
				  
				  top.removeAllChildren();
				  for (int i = 0; i < files.length; i++)
					  top.add(new FileNode(files[i]));

				  directoryView.updateUI();
				  composite.redraw();
				  org.eclipse.swt.graphics.Image image = getSystemSwtIcon(folder);
				  if (image != null)
					  swtIcon = image;
				  
				  String text = "t " + path;
				  dirPath.setText(text);
				  dirPath.setStyleRange(style);
			  }
		  });

		  final List fileClasses = new List(shell, SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL|SWT.BORDER);
		  int fcX = adressX + dvW + 5, fcY = adressY + adressHeight + 5, fcW = 3*dvW/4, fcH = dvH;
		  fileClasses.setBounds(fcX, fcY, fcW, fcH);
		  
		  Label fileClassesLabel = new Label(shell, SWT.CENTER);
		  int fclX = fcX + 5, fclY = fcY + fcH + 3, fclW = fcW, fclH = 15; 
		  fileClassesLabel.setBounds(fclX, fclY, fclW, fclH);
		  fileClassesLabel.setText("File classes");
		  
		  directoryView.addTreeSelectionListener(new TreeSelectionListener() {
			  public void valueChanged(TreeSelectionEvent e) {
				  DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				  directoryView.getLastSelectedPathComponent();

				  if (node == null) return;

				  Object nodeInfo = node.getUserObject();

				  /* React to the node selection. */
				  if (node instanceof FileNode) {
					  final FileNode newNode = (FileNode)node;
					  String path = "", fileName = "";
					  path = newNode.getFile().getPath();
					  fileName = newNode.getFile().getName();
					  path = SysmlInheritanceDiagramAction.convertPath(path);
					  String command = "loadFile(\"" + path +"\")";
					  boolean errorLoad = false;

					  try{
						  ICompilerResult compilerResult;
						  compilerResult = CompilerProxy.sendExpression(command, true);
						  String[] result = compilerResult.getResult();


						  if(Boolean.parseBoolean(result[0].trim()) != true){
							  errorLoad = true;
						  }
					  }
					  catch(Exception ee){
					  }

					  if(!errorLoad) {
						  command = "getClassNames(" + fileName.substring(0, fileName.length() - 3) + ")";
						  String retval = "";
						  try{
							  retval = CompilerProxy.sendExpression(command, true).getFirstResult();
						  }
						  catch(Exception ee){

						  }

						  org.modelica.mdt.core.List list = null;
						  try{
							  try
							  {
								  list = ModelicaParser.parseList(retval);
							  }
							  catch(ModelicaParserException ee)
							  {
								  throw new UnexpectedReplyException("Unable to parse list: " 
										  + ee.getMessage());
							  }
						  }
						  catch(Exception ee){

						  }
						  
						  final  org.modelica.mdt.core.List nList = list;
						  shell.getDisplay().asyncExec(new Runnable() {
							  public void run()
							  { 
								  fileClasses.removeAll();
								  for(int j =0;j<nList.size();j++){
									  fileClasses.add(nList.elementAt(j).toString());
								  }  
								  fileClasses.setData(newNode.getFile());
							  }
						  });
					  }

				  }

			  }
		  });

		  directoryView.addMouseListener(new java.awt.event.MouseAdapter() {
			  public void mousePressed(java.awt.event.MouseEvent e) {
				  int selRow = directoryView.getRowForLocation(e.getX(), e.getY());
				  TreePath selPath = directoryView.getPathForLocation(e.getX(), e.getY());

				  if(selRow != -1) {			        
					  if(e.getClickCount() == 2) {						  
						  Object node = selPath.getLastPathComponent();
						  if (node instanceof FileNode) {
							  FileNode newNode = (FileNode)node;
							  final File folder = newNode.getFile();
							  if (folder.isDirectory()) {
								  File[] files = getFiles(folder); 
								  final String path = folder.getAbsolutePath();

								  top.removeAllChildren();
								  for (int i = 0; i < files.length; i++)
									  top.add(new FileNode(files[i]));

								  shell.getDisplay().asyncExec(new Runnable() {
									  public void run()
									  { 
										  directoryView.repaint();
										  directoryView.updateUI();
										  composite.redraw();
										  org.eclipse.swt.graphics.Image image = getSystemSwtIcon(folder);
										  if (image != null)
											  swtIcon = image;
										  dirDialog.setFilterPath(path);
										  String text = "t " + path;
										  dirPath.setText(text);
										  dirPath.setStyleRange(style);
									  }
								  });
//								 
							  }
						  }
					  }
				  }
			  }
		  });
		  
		  Button insert = new Button(shell, SWT.ARROW|SWT.RIGHT);
		  int insertX = fcX + fcW, insertY = fcY + 50, insertW = 20, insertH = 20;
		  insert.setBounds(insertX, insertY, insertW, insertH);

		  Button remove = new Button(shell, SWT.ARROW|SWT.LEFT);
		  int removeX = fcX + fcW, removeY = fcY + 80, removeW = 20, removeH = 20;
		  remove.setBounds(removeX, removeY, removeW, removeH);
				 
		  final Table selectedClasses = new Table(shell, SWT.MULTI|SWT.H_SCROLL|SWT.V_SCROLL|SWT.BORDER);
		  int scX = fcX + fcW + insertW, scY = fcY, scW = 3*dvW/4, scH = dvH;
		  selectedClasses.setBounds(scX, scY, scW, scH);
		  
		  Label selectedClassesLabel = new Label(shell, SWT.CENTER);
		  int sclX = scX + 5, sclY = scY + scH + 3, sclW = scW, sclH = 15; 
		  selectedClassesLabel.setBounds(sclX, sclY, sclW, sclH);
		  selectedClassesLabel.setText("Selected classes");
		  
		  selectedClasses.addKeyListener(new KeyListener () {
			  public void keyPressed(KeyEvent e) {
				  int[] selected = selectedClasses.getSelectionIndices();
				  int prevIndex = selected[0] - 1;
				  if (prevIndex < 0) prevIndex = 0;
				  
				  if (e.keyCode == SWT.DEL)
					  selectedClasses.remove(selected);
				  selectedClasses.select(prevIndex);
			  }

			  public void keyReleased(KeyEvent e) {
				  
			  }
		  });
		  		  
		  fileClasses.addKeyListener(new KeyListener() {
			  public void keyPressed(KeyEvent e) {
				 if (e.character== SWT.CR)
					 moveSelected(fileClasses, selectedClasses);				
			  }

			  public void keyReleased(KeyEvent e) {
				  
			  }
		  });
	
		  fileClasses.addMouseListener(new MouseAdapter() {
			  public void mouseDoubleClick(MouseEvent e) {				  
				  moveSelected(fileClasses, selectedClasses);
			  }
		  });
		  
		  insert.addListener (SWT.Selection, new Listener () {
			  public void handleEvent 	(Event event) {
				  moveSelected(fileClasses, selectedClasses);
			  }			  
		  });
		  
		  remove.addListener (SWT.Selection, new Listener () {
			  public void handleEvent 	(Event event) {
				  int[] selected = selectedClasses.getSelectionIndices();
				  int prevIndex = selected[0] - 1;
				  if (prevIndex < 0) prevIndex = 0;
				  
				  selectedClasses.remove(selected);
				  selectedClasses.select(prevIndex);
			  }			  
		  });
		  
		  Button okButton = new Button(shell, SWT.PUSH);
		  okButton.setText("OK");
		  int okX = scX, okY = scY + scH + 20, okW = 50, okH = 20;
		  okButton.setBounds(okX, okY, okW, okH);
		  
		  Button closeButton = new Button(shell, SWT.PUSH);
		  closeButton.setText("CLOSE");
		  int closeX = scX + okW, closeY = okY, closeW = okW, closeH = okH;
		  closeButton.setBounds(closeX, closeY, closeW, closeH);
		  
		  closeButton.addListener (SWT.Selection, new Listener () {
			  public void handleEvent 	(Event event) {
				  shell.close();
			  }
		  });
		  
		  okButton.addListener (SWT.Selection, new Listener () {
			  public void handleEvent 	(Event event) {
				  TableItem[] items = selectedClasses.getItems();
				  String[] retValues = new String[items.length];
				  ModelicaClassLocation[] classes = new ModelicaClassLocation[items.length];
				  for (int i = 0; i < items.length;i++) {
					  Object data = items[i].getData();
					  String path = null;					 
					  if(data instanceof File) {
						  path = ((File)data).getPath();
						  classes[i] = new ModelicaClassLocation();
						  classes[i].setFilePath(((File)data).getPath());
						  classes[i].setClassName(items[i].getText());
					  }
					  retValues[i] = items[i].getText();
				  }
				  selClasses = classes;
				  shell.close();
			  }
		  });
	  }

	  public org.eclipse.swt.graphics.Image getSystemSwtIcon(File file) {
		  org.eclipse.swt.graphics.Image image = null;
		  FileSystemView view = FileSystemView.getFileSystemView();
		  Icon icon = view.getSystemIcon(file);
		  java.awt.Image iconImage = null;
		  org.eclipse.swt.graphics.ImageData swtIconData = null;

		  if (icon instanceof ImageIcon) { 
			  iconImage = ((ImageIcon)icon).getImage();
			  if (iconImage instanceof BufferedImage)
				  swtIconData = ImageConvertUtil.convertToSWT((BufferedImage)iconImage);
		  }
		  Display display = getParent().getDisplay();
		  if (swtIconData != null) image =new org.eclipse.swt.graphics.Image(display,
				  swtIconData);

		  return image;
	  }

	  public File[] getFiles(File folder) {

		  File[] files = folder.listFiles(new FileFilter() {
			  public boolean accept(File file) {
				  String name = file.getName();
				  if (file.isFile()) {
					  if(name.endsWith(".mo"))
						  return true;
				  }
				  else if (file.isDirectory()) return true;

				  return false;
			  }
		  });
		  return files;
	  }

	  public void moveSelected(List source, Table target) {
		  String[] selClasses = source.getSelection();
		  for (int i = 0; i<selClasses.length; i++) {
			  TableItem[] items = target.getItems();
			  boolean canAdd = true;
			  for (int j = 0; j<items.length; j++) {
				  String text = items[j].getText();
				  Object data = items[j].getData();
				  if (text.equals(selClasses[i]) & 
						  data.equals(source.getData()))
						  canAdd = false;
			  }
			  if (canAdd) {
				  TableItem item = new TableItem(target, SWT.NONE);
				  item.setText(selClasses[i]);
				  if (source.getData() instanceof File)
					  item.setData(source.getData());
			  }
		  }
	  }
	  
}




class FileNode extends DefaultMutableTreeNode {

	  protected Icon icon;

	  protected File file;
	  
	  public FileNode() {
		  super();
	  }
	  
	  public FileNode(File file) {
		  super(file.getName(), false);
		  this.file = file;
	  }

	  public FileNode(File file, Icon icon) {
		  super(file.getName(), false);
		  this.icon = icon;
		  this.file = file;
	  }

	  public void setIcon(Icon icon) {
		  this.icon = icon;
	  }

	  public Icon getIcon() {
		  return icon;
	  }
	  
	  public String getNodeName() {
		  return file != null ? file.getName() : null;
	  }
	  
	  public File getFile() {
		  return file;
	  }

}

class FileNodeRenderer extends DefaultTreeCellRenderer {

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		FileSystemView view = FileSystemView.getFileSystemView();	
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);

		if (value instanceof FileNode) {
			File file = ((FileNode) value).getFile();
			Icon icon = view.getSystemIcon(file);

			setIcon(icon);
		}
		return this;
	}
}
