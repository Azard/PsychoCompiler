program test()
type line is array of 10 integer;
type gg is array of 10 line;
is
    var b is boolean;
    var n is integer;
    var m is integer;
    var a is line;
    var g is gg;
    
begin
    n := 3;
    b := 1;
    a[n]:=a[0]+1;
    m:=n*2+1;
    print m;
    g[1][1]:=0;
    if n>1&&m<4 then
      print n;
    end if
    print n;
end