@.str = private unnamed_addr constant [5 x i8] c"%d  \00", align 1
@.str1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
define i32 @abs(i32 %n) nounwind {
entry:
  %n.addr = alloca i32, align 4
  store i32 %n, i32* %n.addr, align 4
  %absn = alloca i32, align 4
  %0 = load i32* %n.addr, align 4
  %cmp0 = icmp slt i32 %0, 0
  br i1 %cmp0, label %if.then0, label %if.else0

if.then0:
  %1 = load i32* %n.addr, align 4
  %sub0 = sub nsw i32 0, %1
  store i32 %sub0, i32* %absn, align 4
  br label %if.end0
if.else0:
  %2 = load i32* %n.addr, align 4
  store i32 %2, i32* %absn, align 4
  br label %if.end0
if.end0:
  %3 = load i32* %absn, align 4
  ret i32 %3
  ret i32 0
}
define i32 @isLegal([9 x i32]* %m, i32 %p, i32 %q) nounwind {
entry:
  %m.addr = alloca [9 x i32]*, align 4
  store [9 x i32]* %m, [9 x i32]** %m.addr, align 4
  %p.addr = alloca i32, align 4
  store i32 %p, i32* %p.addr, align 4
  %q.addr = alloca i32, align 4
  store i32 %q, i32* %q.addr, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %abs1 = alloca i32, align 4
  %abs2 = alloca i32, align 4
  store i32 1, i32* %i, align 4
  store i32 1, i32* %j, align 4
  br label %while.cond0

while.cond0:
  %0 = load i32* %i, align 4
  %1 = load i32* %p.addr, align 4
  %cmp0 = icmp slt i32 %0, %1
  br i1 %cmp0, label %while.body0, label %while.end0

while.body0:
    br label %while.cond1

  while.cond1:
    %2 = load i32* %j, align 4
    %cmp1 = icmp slt i32 %2, 9
    br i1 %cmp1, label %while.body1, label %while.end1

  while.body1:
      %3 = load i32* %i, align 4
      %4 = load i32* %j, align 4
      %5 = load [9 x i32]** %m.addr, align 4
      %arrayidx0 = getelementptr inbounds [9 x i32]* %5, i32 %3
      %arrayidx1 = getelementptr inbounds [9 x i32]* %arrayidx0, i32 0, i32 %4
      %6 = load i32* %arrayidx1, align 4
      %cmp2 = icmp eq i32 %6, 1
      br i1 %cmp2, label %if.then0, label %if.end0

    if.then0:
      %7 = load i32* %p.addr, align 4
      %8 = load i32* %i, align 4
      %sub0 = sub nsw i32 %7, %8
      %call0 = call i32 @abs(i32 %sub0)
      store i32 %call0, i32* %abs1, align 4
      %9 = load i32* %q.addr, align 4
      %10 = load i32* %j, align 4
      %sub1 = sub nsw i32 %9, %10
      %call1 = call i32 @abs(i32 %sub1)
      store i32 %call1, i32* %abs2, align 4
      %11 = load i32* %j, align 4
      %12 = load i32* %q.addr, align 4
      %cmp3 = icmp eq i32 %11, %12
      br i1 %cmp3, label %if.then1, label %lor.lhs.false0

    lor.lhs.false0:
      %13 = load i32* %abs1, align 4
      %14 = load i32* %abs2, align 4
      %cmp4 = icmp eq i32 %13, %14
      br i1 %cmp4, label %if.then1, label %if.end1

    if.then1:
      ret i32 0
      br label %if.end1
    if.end1:
      br label %if.end0
    if.end0:
      %16 = load i32* %j, align 4
      %add0 = add nsw i32 %16, 1
      store i32 %add0, i32* %j, align 4
    br label %while.cond1

  while.end1:
    %17 = load i32* %i, align 4
    %add1 = add nsw i32 %17, 1
    store i32 %add1, i32* %i, align 4
    store i32 1, i32* %j, align 4
  br label %while.cond0

while.end0:
  ret i32 1
  ret i32 0
}
define void @initMatrix([9 x i32]* %m) nounwind {
entry:
  %m.addr = alloca [9 x i32]*, align 4
  store [9 x i32]* %m, [9 x i32]** %m.addr, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store i32 0, i32* %i, align 4
  store i32 0, i32* %j, align 4
  br label %while.cond0

while.cond0:
  %0 = load i32* %i, align 4
  %cmp0 = icmp slt i32 %0, 9
  br i1 %cmp0, label %while.body0, label %while.end0

while.body0:
    br label %while.cond1

  while.cond1:
    %1 = load i32* %j, align 4
    %cmp1 = icmp slt i32 %1, 9
    br i1 %cmp1, label %while.body1, label %while.end1

  while.body1:
      %2 = load i32* %i, align 4
      %3 = load i32* %j, align 4
      %4 = load [9 x i32]** %m.addr, align 4
      %arrayidx0 = getelementptr inbounds [9 x i32]* %4, i32 %2
      %arrayidx1 = getelementptr inbounds [9 x i32]* %arrayidx0, i32 0, i32 %3
      store i32 0, i32* %arrayidx1, align 4
      %5 = load i32* %j, align 4
      %add0 = add nsw i32 %5, 1
      store i32 %add0, i32* %j, align 4
    br label %while.cond1

  while.end1:
    %6 = load i32* %i, align 4
    %add1 = add nsw i32 %6, 1
    store i32 %add1, i32* %i, align 4
    store i32 0, i32* %j, align 4
  br label %while.cond0

while.end0:
  ret void
}
define void @show([9 x i32]* %m) nounwind {
entry:
  %m.addr = alloca [9 x i32]*, align 4
  store [9 x i32]* %m, [9 x i32]** %m.addr, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  store i32 1, i32* %i, align 4
  store i32 1, i32* %j, align 4
  br label %while.cond0

while.cond0:
  %0 = load i32* %i, align 4
  %cmp0 = icmp slt i32 %0, 9
  br i1 %cmp0, label %while.body0, label %while.end0

while.body0:
    br label %while.cond1

  while.cond1:
    %1 = load i32* %j, align 4
    %cmp1 = icmp slt i32 %1, 9
    br i1 %cmp1, label %while.body1, label %while.end1

  while.body1:
      %2 = load i32* %i, align 4
      %3 = load i32* %j, align 4
      %4 = load [9 x i32]** %m.addr, align 4
      %arrayidx0 = getelementptr inbounds [9 x i32]* %4, i32 %2
      %arrayidx1 = getelementptr inbounds [9 x i32]* %arrayidx0, i32 0, i32 %3
      %5 = load i32* %arrayidx1, align 4
      %cmp2 = icmp eq i32 %5, 1
      br i1 %cmp2, label %if.then0, label %if.else0

    if.then0:
      %call0 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* @.str,     i32 0, i32 0), i32 1)
      br label %if.end0
    if.else0:
      %call1 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* @.str,     i32 0, i32 0), i32 0)
      br label %if.end0
    if.end0:
      %6 = load i32* %j, align 4
      %add0 = add nsw i32 %6, 1
      store i32 %add0, i32* %j, align 4
    br label %while.cond1

  while.end1:
    %call2 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([2 x i8]* @.str1, i32 0, i32 0))
    %7 = load i32* %i, align 4
    %add1 = add nsw i32 %7, 1
    store i32 %add1, i32* %i, align 4
    store i32 1, i32* %j, align 4
  br label %while.cond0

while.end0:
  %call3 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([2 x i8]* @.str1, i32 0, i32 0))
  ret void
}
define void @search([9 x i32]* %m, i32 %p, i32* %count) nounwind {
entry:
  %m.addr = alloca [9 x i32]*, align 4
  store [9 x i32]* %m, [9 x i32]** %m.addr, align 4
  %p.addr = alloca i32, align 4
  store i32 %p, i32* %p.addr, align 4
  %count.addr = alloca i32*, align 4
  store i32* %count, i32** %count.addr, align 4
  %j = alloca i32, align 4
  %0 = load i32* %p.addr, align 4
  %cmp0 = icmp sgt i32 %0, 8
  br i1 %cmp0, label %if.then0, label %if.else0

if.then0:
  %1 = load i32** %count.addr, align 4
  %arrayidx0 = getelementptr inbounds i32* %1, i32 0
  %2 = load i32* %arrayidx0, align 4
  %add0 = add nsw i32 %2, 1
  %3 = load i32** %count.addr, align 4
  %arrayidx1 = getelementptr inbounds i32* %3, i32 0
  store i32 %add0, i32* %arrayidx1, align 4
  %4 = load [9 x i32]** %m.addr, align 4
  call void @show([9 x i32]* %4)
  br label %if.end0
if.else0:
  store i32 1, i32* %j, align 4
  br label %while.cond0

while.cond0:
  %5 = load i32* %j, align 4
  %cmp1 = icmp slt i32 %5, 9
  br i1 %cmp1, label %while.body0, label %while.end0

while.body0:
    %6 = load i32* %p.addr, align 4
    %7 = load i32* %j, align 4
    %8 = load [9 x i32]** %m.addr, align 4
    %arrayidx2 = getelementptr inbounds [9 x i32]* %8, i32 %6
    %arrayidx3 = getelementptr inbounds [9 x i32]* %arrayidx2, i32 0, i32 %7
    store i32 1, i32* %arrayidx3, align 4
    %9 = load [9 x i32]** %m.addr, align 4
    %10 = load i32* %p.addr, align 4
    %11 = load i32* %j, align 4
    %call0 = call i32 @isLegal([9 x i32]* %9, i32 %10, i32 %11)
    %cmp2 = icmp ne i32 %call0, 0
    br i1 %cmp2, label %if.then1, label %if.end1

  if.then1:
    %12 = load [9 x i32]** %m.addr, align 4
    %13 = load i32* %p.addr, align 4
    %add1 = add nsw i32 %13, 1
    %14 = load i32** %count.addr, align 4
    call void @search([9 x i32]* %12, i32 %add1, i32* %14)
    br label %if.end1
  if.end1:
    %15 = load i32* %p.addr, align 4
    %16 = load i32* %j, align 4
    %17 = load [9 x i32]** %m.addr, align 4
    %arrayidx4 = getelementptr inbounds [9 x i32]* %17, i32 %15
    %arrayidx5 = getelementptr inbounds [9 x i32]* %arrayidx4, i32 0, i32 %16
    store i32 0, i32* %arrayidx5, align 4
    %18 = load i32* %j, align 4
    %add2 = add nsw i32 %18, 1
    store i32 %add2, i32* %j, align 4
  br label %while.cond0

while.end0:
  br label %if.end0
if.end0:
  ret void
}
define i32 @main() nounwind {
entry:
  %m = alloca [9 x [9 x i32]], align 4
  %count = alloca [9 x i32], align 4
  %arrayidx0 = getelementptr inbounds [9 x i32]* %count, i32 0, i32 0
  store i32 0, i32* %arrayidx0, align 4
  %arraydecay0 = getelementptr inbounds [9 x [9 x i32]]* %m, i32 0, i32 0
  call void @initMatrix([9 x i32]* %arraydecay0)
  %arraydecay1 = getelementptr inbounds [9 x [9 x i32]]* %m, i32 0, i32 0
  %arraydecay2 = getelementptr inbounds [9 x i32]* %count, i32 0, i32 0
  call void @search([9 x i32]* %arraydecay1, i32 1, i32* %arraydecay2)
  %arrayidx1 = getelementptr inbounds [9 x i32]* %count, i32 0, i32 0
  %0 = load i32* %arrayidx1, align 4
  %call0 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([5 x i8]* @.str,     i32 0, i32 0), i32 %0)
  %call1 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([2 x i8]* @.str1, i32 0, i32 0))
  ret i32 0
}
declare i32 @printf(i8*, ...)