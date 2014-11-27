program example()
	function isodd(n)
		var n is integer;
		return boolean;
	is
	begin
		if n % 2 == 0 then
			return yes;
		end if
		return no;
	end function isodd;

	function ackerman(m, n)
		var m is integer;
		var n is integer;
		return integer;
	is
	begin
		if m == 0 then
			return n + 1;
		elif m > 0 and n == 0 then
			return ackerman(m - 1, 1);
		else
			return ackerman(m - 1, ackerman(m, n - 1));
		end if
	end function ackerman;

	type line is array of 10 integer;
	type grid is array of 10 line;

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
				g[i][j] := i * j;
			until j >= 10;
			i := i + 1;
		end while
		foreach l in g do
			foreach i in l do
				print i;
			end foreach
		end foreach
	end function multy;

	type a is class
		var v is integer;
		function f(x)
			var x is integer;
			return integer;
		is
			var t is integer;
		begin
			t := x + v;
			v := x;
			return t;
		end function f;
	end class;


	type b is class extends c
		function f(x)
			var x is integer;
			return integer;
		is
		begin
			v := v + x;
			return v;
		end function f;
	end class;

is
	var p is a;
	var q is b;
begin
	print p.f(5);
	print q.f(4);
	p.v := 3;
	print p.f(3);
	multy();
	print ackerman(3, 4);
	if isodd(3) then
		print 3;
	end if
end
