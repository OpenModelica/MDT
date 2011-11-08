model import_rich_model
  // qualified imports
  import Modelica;
  // another qualified import
  import Modelica.Math.sin;
  // unqualified import
  import Modelica.*;
  // renaming import
  import mm = Modelica.Math;

  // import some local packages, defined in the same project
  import foo = hepp;
  import hepp.hopp;
  import hepp.*;
  import root_package.root_package_model;

  constant Real x;

  class bar
    // do some imports that are local to the class bar
    import Modelica.SIunits;
    import Modelica.Blocks;
    import cp = childless_package;
    Real y;
  equation

  // its easier to write this comment then to fix the bug
  end bar;
  // an encapsulated class for testing lookup scope encapsulation
  encapsulated class foo
    Real foo_local_var;

  end foo;

end import_rich_model;
 // an area outside of any class definitions