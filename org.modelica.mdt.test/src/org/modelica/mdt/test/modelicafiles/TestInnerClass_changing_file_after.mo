// funny comment removed
class EquationComponent
  /* records are nice */
  record R
    Real x,y;
  end R;

  R a,b,c;
equation
  a = if true then b else c;
end EquationComponent;