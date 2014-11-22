program QS()
  type A is array of 100 integer;

  function quick_sort(arr,le,ri)
    var arr is A;
    var le is integer;
    var ri is integer;
  is
    var tmp is integer;
    var right is integer;
    var left is integer;
  begin
    if le < ri then
      right := ri;
      left := le;
      tmp := arr[ri];
      while left < right do
        while arr[left] <= tmp and left <= right do
          left := left + 1;
        end while
        tmp := arr[left];
        arr[left] := arr[right];
        arr[right] := tmp;
        tmp := arr[left];
        while arr[right] >= tmp and left <= right do
          right := right - 1;
        end while
        tmp := arr[right];
        arr[right] := arr[left];
        arr[left] := tmp;
        tmp := arr[right];
      end while
      quick_sort(arr,le,left);
      quick_sort(arr,left,ri);
    end if
  end function quick_sort;

is
  var arr is A;
  var le is integer;
  var ri is integer;
begin
  // data init
  quick_sort(arr,le,ri);
end
			




			


