/*
 * However, don't ever expect you can parse
 * the error message from the compiler.
 *    -- Adrian Pop
 */
class EquationComponent
  record R
    Real x,y;
  end R;
  R a,b,c;
equation
  a = if true then b else c;
end EquationComponent;