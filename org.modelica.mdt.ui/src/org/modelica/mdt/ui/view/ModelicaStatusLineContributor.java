
package org.modelica.mdt.ui.view;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.texteditor.StatusLineContributionItem;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.internal.core.CompilerProxy;
import org.modelica.mdt.ui.ModelicaImages;

public class ModelicaStatusLineContributor extends StatusLineContributionItem
{
	Image mdtOnline  = ModelicaImages.getImageDescriptor(ModelicaImages.IMG_MDT_STATUS_ONLINE).createImage();
	Image mdtOffline = ModelicaImages.getImageDescriptor(ModelicaImages.IMG_MDT_STATUS_OFFLINE).createImage();

	boolean fDirty = false;

	public ModelicaStatusLineContributor()
	{
		super("MDT-Status", true, 50);
		try
		{
			String name = CompilerProxy.getCompilerName();
			if (name != null && !name.equalsIgnoreCase(""))
			{
				setText(name + " Online ");
				if (!name.equalsIgnoreCase("Empty Compiler"))
				{
					setImage(mdtOnline);
				}
				else
				{
					setImage(mdtOffline);
				}
			}
			else
			{
				setText("OpenModelica Compiler is Offline.");
				setImage(mdtOffline);
			}
		}
		catch(CompilerInstantiationException e)
		{
			// do nothing
			setText("OpenModelica Compiler is Offline");
			setImage(mdtOffline);
		}
		setToolTipText("OpenModelica Compiler Status.\n" +
				"If 'Empty Compiler' is online, check the Modelica preferences:\n" +
				"Window->Preferences->Modelica: 'Start OMC from MDT'");
	}

	@Override
	public void fill(Composite parent)
	{
		// TODO Auto-generated method stub
		super.fill(parent);
	}

	@Override
	public void setActionHandler(IAction actionHandler)
	{
		// TODO Auto-generated method stub
		super.setActionHandler(actionHandler);
	}

	@Override
	public void setErrorImage(Image image)
	{
		// TODO Auto-generated method stub
		super.setErrorImage(image);
	}

	@Override
	public void setErrorText(String text)
	{
		// TODO Auto-generated method stub
		super.setErrorText(text);
	}

	@Override
	public void setImage(Image image)
	{
		// TODO Auto-generated method stub
		super.setImage(image);
	}

	@Override
	public void setText(String text)
	{
		// TODO Auto-generated method stub
		super.setText(text);
	}

	@Override
	public void setToolTipText(String string)
	{
		// TODO Auto-generated method stub
		super.setToolTipText(string);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		super.update();
		try
		{
			String name = CompilerProxy.getCompilerName();
			if (name != null && !name.equalsIgnoreCase(""))
			{
				setText(name + " is Online");
				if (!name.equalsIgnoreCase("Empty Compiler"))
				{
					setImage(mdtOnline);
				}
				else
				{
					setImage(mdtOffline);
				}

			}
			else
			{
				setText("OpenModelica Compiler is Offline");
				setImage(mdtOffline);
			}
		}
		catch(CompilerInstantiationException e)
		{
			// do nothing
			setText("OpenModelica Compiler is Offline");
			setImage(mdtOffline);
		}
		fDirty = true;
	}

	public boolean isDirty()
	{
		return fDirty;
	}

	@Override
	public boolean isDynamic()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return super.isEnabled();
	}

	@Override
	public boolean isVisible()
	{
		// TODO Auto-generated method stub
		return super.isVisible();
	}
}
