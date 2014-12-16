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
    elif m>5&&n<2&&m>4 ||m>7 then
       print m;
       if m>5 then
         print n;
       end if
    end if
    print n;
end