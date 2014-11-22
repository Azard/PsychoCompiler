program simple_3(a)
    var a is integer;
    return integer;

    function isodd(n)
        var n is integer;
        return boolean;
    is
        var i is boolean;
        var j is integer;
    begin
        i := 0 + 1;
        return yes;
    end function isodd;



    function isodd2(n)
        var n is integer;
        return boolean;
    is
        var i is boolean;
        var j is integer;
    begin
        if n % 2 == 0 then
            return yes;
        end if
        return no;
    end function isodd2;


    type line is array of 5 boolean;
    type grid is array of 10 line;


    type a is class
        var v is line;
        function f(x)
            var x is integer;
            return integer;
        is
            var t is integer;
        begin
        end function f;
    end class;

    type b is class extends c
        function f(x)
            var x is integer;
            return integer;
        is
            var y is integer;
        begin
            a := ackerman(m-1, ackerman(1,m));
            return ackerman(a, ackerman(1,m));
        end function f;
    end class;

    function multy(n)
    var n is integer;

    is
        var i is integer;
        var j is integer;
        var g is grid;
        var l is line;
    begin
        i := 0;
        while i < 10 do
            j := 0;
            repeat
                a[2].s[b] := 1;
            until j >= 10;
            i := i + 1;
        end while
        foreach l in g do
            foreach i in l do
                print i;
            end foreach
        end foreach
        a := 1;
        multy(1,2,3,a);
    end function multy;

is
    var i is grid;
begin
    print 1;
    print i;
    multy();
    multy(2,i);
    multy(s[1],p.s);
    p.v := ackerman(ackerman(3,4),4);
    print ackerman(3,4);
    print p.f(3);
    if isodd(1) then
        print 3;
    end if
end