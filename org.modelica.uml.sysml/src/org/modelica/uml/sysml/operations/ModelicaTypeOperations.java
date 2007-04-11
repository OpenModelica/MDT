package org.modelica.uml.sysml.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Artifact;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.internal.operations.TypeOperations;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.modelica.uml.sysml.AccessKind;
import org.modelica.uml.sysml.FlowDirection;
import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaComposition;
import org.modelica.uml.sysml.ModelicaProperty;
import org.modelica.uml.sysml.SysmlPackage;

@SuppressWarnings("restriction")
public class ModelicaTypeOperations extends TypeOperations {

	public static ModelicaComposition createModelicaComposition(Type type,
			String end1Name, Type end1Type, String end2Name) {

		org.eclipse.uml2.uml.Package package_ = type.getNearestPackage();

		if (package_ == null) {
			throw new IllegalStateException();
		}

		ModelicaComposition composition = (ModelicaComposition) package_
				.createOwnedType(null,
						SysmlPackage.Literals.MODELICA_COMPOSITION);

		createAssociationEnd(type, composition, false,
				AggregationKind.COMPOSITE_LITERAL, end1Name, 0, -1, end1Type);

		createAssociationEnd(end1Type, composition, false,
				AggregationKind.NONE_LITERAL, end2Name, 0, -1, type);
		
		composition.setAccess(AccessKind.PUBLIC_LITERAL);
		composition.setDirection(FlowDirection.IN_LITERAL);
		
		return composition;
	}

	
	
	public static List getCompositionProperties(ModelicaClass modelicaClass) {
		List properties = new ArrayList();

		org.eclipse.uml2.uml.Package package_ = modelicaClass
				.getNearestPackage();

		if (package_ == null) {
			throw new IllegalStateException();
		}

		List ownedTypes = package_.getOwnedTypes();
		for (Iterator typesIter = ownedTypes.iterator(); typesIter.hasNext();) {
			Type type = (Type) typesIter.next();

			if (type instanceof ModelicaComposition) {
				Property classEnd = (Property) ((ModelicaComposition) type)
						.getOwnedEnds().get(1);

				// checking if modelicaClass participates in composition
				// association
				if (classEnd.getType().equals(modelicaClass)) {
					// save another end
					properties.add(((ModelicaComposition) type).getOwnedEnds()
							.get(0));
				}
			}
		}

		return properties;
	}

}
