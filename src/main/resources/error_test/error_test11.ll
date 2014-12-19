program simple_3()
    type line is array of 10 integer;
    type grid is array of 9 line;
    function isodd(n,m)
        var n is integer;
        var m is line;
        return boolean;
    is
        var i is boolean;
        var j is integer;
    begin
        i := 0 + 1;
        return yes;
    end function isodd;

is
    var i is integer;
    var a is line;
    var g is grid;
begin
    i:=isodd(1,g);
    a[0]:=0;
    a[1]:=1;
    print a[0];
    print a[1];
end
