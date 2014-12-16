program simple_3()
    type line is array of 10 integer;
    type grid is array of 9 line;
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

    function quick(a,g,n)
        var a is line;
        var g is grid;
        var n is integer;
        return integer;
    is
        var i is integer;
        var j is line;
        var b is grid;
    begin
        i := 0 + 1;
        quick(a,g,n);
        return i;
    end function quick;

is
    var i is integer;
    var a is line;
    var g is grid;
begin
    i:=isodd(1);
    quick(a,g,i);
    return n;
end