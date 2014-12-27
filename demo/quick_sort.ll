@.str = private unnamed_addr constant [5 x i8] c"%d  \00", align 1
@.str1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
define void @show_array(i32* %arr, i32 %n) nounwind {
entry:
  %arr.addr = alloca i32*, align 4
  store i32* %arr, i32** %arr.addr, align 4
  %n.addr = alloca i32, align 4
  store i32 %n, i32* %n.addr, align 4
  %i = alloca i32, align 4
  store i32 0, i32* %i, align 4
  br label %while.cond0

while.cond0:
  %0 = load i32* %i, align 4
  %1 = load i32* %n.addr, align 4
  %cmp0 = icmp slt i32 %0, %1
  br i1 %cmp0, label %while.body0, label %while.end0

while.body0:
    %2 = load i32* %i, align 4
    %3 = load i32** %arr.addr, align 4
    %arrayidx0 = getelementptr inbounds i32* %3, i32 %2
    %4 = load i32* %arrayidx0, align 4
    %call0 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* @.str,     i32 0, i32 0), i32 %4)
    %5 = load i32* %i, align 4
    %add0 = add nsw i32 %5, 1
    store i32 %add0, i32* %i, align 4
  br label %while.cond0

while.end0:
  %call1 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([2 x i8]* @.str1, i32 0, i32 0))
  ret void
}
define void @quick_sort(i32* %arr, i32 %left, i32 %right) nounwind {
entry:
  %arr.addr = alloca i32*, align 4
  store i32* %arr, i32** %arr.addr, align 4
  %left.addr = alloca i32, align 4
  store i32 %left, i32* %left.addr, align 4
  %right.addr = alloca i32, align 4
  store i32 %right, i32* %right.addr, align 4
  %t_right = alloca i32, align 4
  %t_left = alloca i32, align 4
  %key = alloca i32, align 4
  %0 = load i32* %left.addr, align 4
  %1 = load i32* %right.addr, align 4
  %cmp0 = icmp slt i32 %0, %1
  br i1 %cmp0, label %if.then0, label %if.end0

if.then0:
  %2 = load i32* %right.addr, align 4
  store i32 %2, i32* %t_right, align 4
  %3 = load i32* %left.addr, align 4
  store i32 %3, i32* %t_left, align 4
  %4 = load i32* %left.addr, align 4
  %5 = load i32** %arr.addr, align 4
  %arrayidx0 = getelementptr inbounds i32* %5, i32 %4
  %6 = load i32* %arrayidx0, align 4
  store i32 %6, i32* %key, align 4
  br label %while.cond0

while.cond0:
  %7 = load i32* %t_left, align 4
  %8 = load i32* %t_right, align 4
  %cmp1 = icmp slt i32 %7, %8
  br i1 %cmp1, label %while.body0, label %while.end0

while.body0:
    br label %while.cond1

  while.cond1:
    %9 = load i32* %t_right, align 4
    %10 = load i32** %arr.addr, align 4
    %arrayidx1 = getelementptr inbounds i32* %10, i32 %9
    %11 = load i32* %arrayidx1, align 4
    %12 = load i32* %key, align 4
    %cmp2 = icmp sge i32 %11, %12
    br i1 %cmp2, label %land.lhs.true0, label %while.end1

  land.lhs.true0:
    %13 = load i32* %t_left, align 4
    %14 = load i32* %t_right, align 4
    %cmp3 = icmp slt i32 %13, %14
    br i1 %cmp3, label %while.body1, label %while.end1

  while.body1:
      %15 = load i32* %t_right, align 4
      %sub0 = sub nsw i32 %15, 1
      store i32 %sub0, i32* %t_right, align 4
    br label %while.cond1

  while.end1:
    %16 = load i32* %t_right, align 4
    %17 = load i32** %arr.addr, align 4
    %arrayidx2 = getelementptr inbounds i32* %17, i32 %16
    %18 = load i32* %arrayidx2, align 4
    %19 = load i32* %t_left, align 4
    %20 = load i32** %arr.addr, align 4
    %arrayidx3 = getelementptr inbounds i32* %20, i32 %19
    store i32 %18, i32* %arrayidx3, align 4
    br label %while.cond2

  while.cond2:
    %21 = load i32* %t_left, align 4
    %22 = load i32** %arr.addr, align 4
    %arrayidx4 = getelementptr inbounds i32* %22, i32 %21
    %23 = load i32* %arrayidx4, align 4
    %24 = load i32* %key, align 4
    %cmp4 = icmp sle i32 %23, %24
    br i1 %cmp4, label %land.lhs.true1, label %while.end2

  land.lhs.true1:
    %25 = load i32* %t_left, align 4
    %26 = load i32* %t_right, align 4
    %cmp5 = icmp slt i32 %25, %26
    br i1 %cmp5, label %while.body2, label %while.end2

  while.body2:
      %27 = load i32* %t_left, align 4
      %add0 = add nsw i32 %27, 1
      store i32 %add0, i32* %t_left, align 4
    br label %while.cond2

  while.end2:
    %28 = load i32* %t_left, align 4
    %29 = load i32** %arr.addr, align 4
    %arrayidx5 = getelementptr inbounds i32* %29, i32 %28
    %30 = load i32* %arrayidx5, align 4
    %31 = load i32* %t_right, align 4
    %32 = load i32** %arr.addr, align 4
    %arrayidx6 = getelementptr inbounds i32* %32, i32 %31
    store i32 %30, i32* %arrayidx6, align 4
  br label %while.cond0

while.end0:
  %33 = load i32* %key, align 4
  %34 = load i32* %t_left, align 4
  %35 = load i32** %arr.addr, align 4
  %arrayidx7 = getelementptr inbounds i32* %35, i32 %34
  store i32 %33, i32* %arrayidx7, align 4
  %36 = load i32** %arr.addr, align 4
  %37 = load i32* %left.addr, align 4
  %38 = load i32* %t_left, align 4
  %sub1 = sub nsw i32 %38, 1
  call void @quick_sort(i32* %36, i32 %37, i32 %sub1)
  %39 = load i32** %arr.addr, align 4
  %40 = load i32* %t_left, align 4
  %add1 = add nsw i32 %40, 1
  %41 = load i32* %right.addr, align 4
  call void @quick_sort(i32* %39, i32 %add1, i32 %41)
  br label %if.end0
if.end0:
  ret void
}
define i32 @main() nounwind {
entry:
  %source = alloca [100 x i32], align 4
  %arrayidx0 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 0
  store i32 9, i32* %arrayidx0, align 4
  %arrayidx1 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 1
  store i32 13, i32* %arrayidx1, align 4
  %arrayidx2 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 2
  store i32 5, i32* %arrayidx2, align 4
  %arrayidx3 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 3
  store i32 -7, i32* %arrayidx3, align 4
  %arrayidx4 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 4
  store i32 -17, i32* %arrayidx4, align 4
  %arrayidx5 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 5
  store i32 -20, i32* %arrayidx5, align 4
  %arrayidx6 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 6
  store i32 14, i32* %arrayidx6, align 4
  %arrayidx7 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 7
  store i32 11, i32* %arrayidx7, align 4
  %arrayidx8 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 8
  store i32 10, i32* %arrayidx8, align 4
  %arrayidx9 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 9
  store i32 3, i32* %arrayidx9, align 4
  %arraydecay0 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 0
  call void @show_array(i32* %arraydecay0, i32 10)
  %arraydecay1 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 0
  call void @quick_sort(i32* %arraydecay1, i32 0, i32 9)
  %arraydecay2 = getelementptr inbounds [100 x i32]* %source, i32 0, i32 0
  call void @show_array(i32* %arraydecay2, i32 10)
  %call0 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([2 x i8]* @.str1, i32 0, i32 0))
  ret i32 0
}
declare i32 @printf(i8*, ...)