program eightQueens()
	type line is array of 9 integer;
	type matrix is array of 9 line;
	
	function abs(n)
		var n is integer;
		return integer;
	is
		var absn is integer;
	begin
		if n < 0 then
			absn := 0 - n;
		else
			absn := n;
		end if
		return absn;
	end function abs;
	
	function isLegal(m,p,q)
		var m is matrix;
		var p is integer;
		var q is integer;
		return boolean;
	is
		var i is integer;
		var j is integer;
		var abs1 is integer;
		var abs2 is integer;
	begin
		i := 1;
		j := 1;
		while i < p do
			while j < 9 do
				if m[i][j] == 1 then
					abs1 := abs(p - i);
					abs2 := abs(q - j);
					if j == q or abs1 == abs2 then
						return no;
					end if
				end if
				j := j + 1;
			end while
			i := i + 1;
			j := 1;
		end while
		return yes;
	end function isLegal;
	
	function initMatrix(m)
		var m is matrix;
	is
		var i is integer;
		var j is integer;
	begin
		i := 0;
		j := 0;
		while i < 9 do
			while j < 9 do
				m[i][j] := 0;
				j := j + 1;
			end while
			i := i + 1;
			j := 0;
		end while
	end function initMatrix;		
	
	function show(m)
		var m is matrix;
	is
		var i is integer;
		var j is integer;
	begin
		i := 1;
		j := 1;
		while i < 9 do
			while j < 9 do
				if m[i][j] == 1 then
					print 1;
				else
					print 0;
				end if
				j := j + 1;
			end while
			print -145876239;
			i := i + 1;
			j := 1;
		end while
		print -145876239;
	end function show;
	
	function search(m,p,count)
		var m is matrix;
		var p is integer;
		var count is line;
	is
		var j is integer;
	begin
		if p > 8 then
		    count[0]:=count[0]+1;
			show(m);
		else
			j := 1;
			while j < 9 do
				m[p][j] := 1;
				if isLegal(m,p,j) then
					search(m,p + 1,count);
				end if
				m[p][j] := 0;
				j := j + 1;
			end while
		end if
	end function search;
is
	var m is matrix;
	var count is line;
begin
    count[0]:=0;
	initMatrix(m);
	search(m,1,count);
	print count[0];
	print -145876239;
end