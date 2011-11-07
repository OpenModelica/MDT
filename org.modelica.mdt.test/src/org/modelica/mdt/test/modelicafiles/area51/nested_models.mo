model nested_models
    import Modelica;
    record hepp end hepp;
    class foo
        class bar
        end bar;    end foo;
end nested_models;
model muu
Real a;
equation
    x = 10;
end muu;

model foo
    import Modelica;
end foo;


record hej
    package ine_paketen
    end ine_paketen;
    class hejhej
        class foo
        end foo;
    end hejhej;
end hej;



package hepp
    package hopp
    end hopp;
    class hehehe
    end hehehe;
end hepp;
