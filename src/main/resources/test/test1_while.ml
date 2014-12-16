program test()
is
    var n is integer;
    var m is integer;
begin
    n := 3;
    m:=n*2+1;
    print m;
    if n>1&&m<4 then
      print n;
    end if
    while n<5 do
       print n;
    end while
    print n;
end