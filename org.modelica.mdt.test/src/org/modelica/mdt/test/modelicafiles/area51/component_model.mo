model components_bananza
    Real a_real "with doc string";
    Real an_undocumented_real;
    package a_package end a_package;
    class a_class end a_class;
    model a_model end a_model;
    connector a_connector end a_connector;
    record a_record end a_record;
    block a_block end a_block;
    function a_function end a_function;
    type a_type end a_type;
protected
    Real a_protected_real;
    Integer a_protected_integer "with a comment";
equation
    b = time;
    der(a) = b;
end components_bananza;