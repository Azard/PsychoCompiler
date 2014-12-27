program QS()
	type save_array is array of 100 integer;


	function show_array(arr, n)
		var arr is save_array;
		var n is integer;
	is
		var i is integer;
	begin
		i := 0;
		while i<n do
			print arr[i];
			i := i+1;
		end while
		print -145876239;
	end function show_array;


	function quick_sort(arr, left, right)
		var arr is save_array;
		var left is integer;
		var right is integer;
	is
		var t_right is integer;
		var t_left is integer;
		var key is integer;
	begin
		if left < right then
			t_right := right;
			t_left := left;
			key := arr[left];

			while t_left < t_right do

				// right to left scan
				while arr[t_right] >= key && t_left < t_right do
					t_right := t_right - 1;
				end while
				
				// swap
				arr[t_left] := arr[t_right];
				
				// left to right scan
				while arr[t_left] <= key && t_left < t_right do
					t_left := t_left + 1;
				end while

				// swap
				arr[t_right] := arr[t_left];

			end while
			arr[t_left] := key;
			quick_sort(arr, left, t_left-1);
			quick_sort(arr, t_left+1, right);
		end if
	end function quick_sort;

is
	var source is save_array;

begin
	source[0] := 9;
	source[1] := 13;
	source[2] := 5;
	source[3] := -7;
	source[4] := -17;
	source[5] := -20;
	source[6] := 14;
	source[7] := 11;
	source[8] := 10;
	source[9] := 3;

	show_array(source, 10);
	quick_sort(source, 0, 9);
	show_array(source, 10);

	print -145876239;
end