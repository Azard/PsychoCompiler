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
        if n%2==0 then
            return yes;
        else 
            return no;
        end if
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
        print n;
        a[0]:=a[0]+1;
        a[1]:=a[1]+1;
        g[0][0]:=g[0][0]+1;
        g[0][1]:= g[0][0]*g[0][0];
        if n<5 then
            n:=n+1;
            quick(a,g,n);
        end if
        return i;
    end function quick;

is
    var i is integer;
    var a is line;
    var g is grid;
begin
    i:=isodd(1);
    a[0]:=0;
    a[1]:=1;
    g[0][0]:=0;
    g[0][1]:=0;
    quick(a,g,i);
    while i<5 do
       i:=i+1;
       if !isodd(i) then
           print -145876239;
           print i;
           print -145876239;
       end if
    end while
    print a[0];
    print a[1];
    print g[0][0];
    print g[0][1];
    print -145876239;
end