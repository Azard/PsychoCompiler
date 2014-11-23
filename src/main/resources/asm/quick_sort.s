; ModuleID = 'quick_sort.cc'
target datalayout = "e-p:64:64:64-i1:8:8-i8:8:8-i16:16:16-i32:32:32-i64:64:64-f32:32:32-f64:64:64-v64:64:64-v128:128:128-a0:0:64-s0:64:64-f80:128:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%"class.std::ios_base::Init" = type { i8 }
%"class.std::basic_ostream" = type { i32 (...)**, %"class.std::basic_ios" }
%"class.std::basic_ios" = type { %"class.std::ios_base", %"class.std::basic_ostream"*, i8, i8, %"class.std::basic_streambuf"*, %"class.std::ctype"*, %"class.std::num_put"*, %"class.std::num_get"* }
%"class.std::ios_base" = type { i32 (...)**, i64, i64, i32, i32, i32, %"struct.std::ios_base::_Callback_list"*, %"struct.std::ios_base::_Words", [8 x %"struct.std::ios_base::_Words"], i32, %"struct.std::ios_base::_Words"*, %"class.std::locale" }
%"struct.std::ios_base::_Callback_list" = type { %"struct.std::ios_base::_Callback_list"*, void (i32, %"class.std::ios_base"*, i32)*, i32, i32 }
%"struct.std::ios_base::_Words" = type { i8*, i64 }
%"class.std::locale" = type { %"class.std::locale::_Impl"* }
%"class.std::locale::_Impl" = type { i32, %"class.std::locale::facet"**, i64, %"class.std::locale::facet"**, i8** }
%"class.std::locale::facet" = type { i32 (...)**, i32 }
%"class.std::basic_streambuf" = type { i32 (...)**, i8*, i8*, i8*, i8*, i8*, i8*, %"class.std::locale" }
%"class.std::ctype" = type { %"class.std::locale::facet", %struct.__locale_struct*, i8, i32*, i32*, i16*, i8, [256 x i8], [256 x i8], i8 }
%struct.__locale_struct = type { [13 x %struct.__locale_data*], i16*, i32*, i32*, [13 x i8*] }
%struct.__locale_data = type opaque
%"class.std::num_put" = type { %"class.std::locale::facet" }
%"class.std::num_get" = type { %"class.std::locale::facet" }

@_ZStL8__ioinit = internal global %"class.std::ios_base::Init" zeroinitializer, align 1
@__dso_handle = external global i8
@_ZZ4mainE5array = private unnamed_addr constant [10 x i32] [i32 11, i32 25, i32 11, i32 4, i32 88, i32 2, i32 108, i32 3, i32 2, i32 21], align 16
@_ZSt4cout = external global %"class.std::basic_ostream"
@.str = private unnamed_addr constant [2 x i8] c" \00", align 1
@llvm.global_ctors = appending global [1 x { i32, void ()* }] [{ i32, void ()* } { i32 65535, void ()* @_GLOBAL__I_a }]

define internal void @__cxx_global_var_init() section ".text.startup" {
  call void @_ZNSt8ios_base4InitC1Ev(%"class.std::ios_base::Init"* @_ZStL8__ioinit)
  %1 = call i32 @__cxa_atexit(void (i8*)* bitcast (void (%"class.std::ios_base::Init"*)* @_ZNSt8ios_base4InitD1Ev to void (i8*)*), i8* getelementptr inbounds (%"class.std::ios_base::Init"* @_ZStL8__ioinit, i32 0, i32 0), i8* @__dso_handle) nounwind
  ret void
}

declare void @_ZNSt8ios_base4InitC1Ev(%"class.std::ios_base::Init"*)

declare void @_ZNSt8ios_base4InitD1Ev(%"class.std::ios_base::Init"*)

declare i32 @__cxa_atexit(void (i8*)*, i8*, i8*) nounwind

define i32 @main() uwtable {
  %1 = alloca i32, align 4
  %array = alloca [10 x i32], align 16
  %i = alloca i32, align 4
  store i32 0, i32* %1
  %2 = bitcast [10 x i32]* %array to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* bitcast ([10 x i32]* @_ZZ4mainE5array to i8*), i64 40, i32 16, i1 false)
  %3 = getelementptr inbounds [10 x i32]* %array, i32 0, i32 0
  call void @_Z10quick_sortPiii(i32* %3, i32 0, i32 9)
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4                                       ; preds = %14, %0
  %5 = load i32* %i, align 4
  %6 = icmp slt i32 %5, 10
  br i1 %6, label %7, label %17

; <label>:7                                       ; preds = %4
  %8 = load i32* %i, align 4
  %9 = sext i32 %8 to i64
  %10 = getelementptr inbounds [10 x i32]* %array, i32 0, i64 %9
  %11 = load i32* %10, align 4
  %12 = call %"class.std::basic_ostream"* @_ZNSolsEi(%"class.std::basic_ostream"* @_ZSt4cout, i32 %11)
  %13 = call %"class.std::basic_ostream"* @_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc(%"class.std::basic_ostream"* %12, i8* getelementptr inbounds ([2 x i8]* @.str, i32 0, i32 0))
  br label %14

; <label>:14                                      ; preds = %7
  %15 = load i32* %i, align 4
  %16 = add nsw i32 %15, 1
  store i32 %16, i32* %i, align 4
  br label %4

; <label>:17                                      ; preds = %4
  %18 = call %"class.std::basic_ostream"* @_ZNSolsEPFRSoS_E(%"class.std::basic_ostream"* @_ZSt4cout, %"class.std::basic_ostream"* (%"class.std::basic_ostream"*)* @_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_)
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind

define void @_Z10quick_sortPiii(i32* %ptr, i32 %begin, i32 %end) uwtable {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %temp = alloca i32, align 4
  %i = alloca i32, align 4
  %j = alloca i32, align 4
  %curPosition = alloca i32, align 4
  %direction = alloca i8, align 1
  store i32* %ptr, i32** %1, align 8
  store i32 %begin, i32* %2, align 4
  store i32 %end, i32* %3, align 4
  %4 = load i32** %1, align 8
  %5 = load i32* %2, align 4
  %6 = sext i32 %5 to i64
  %7 = getelementptr inbounds i32* %4, i64 %6
  %8 = load i32* %7, align 4
  store i32 %8, i32* %temp, align 4
  %9 = load i32* %2, align 4
  %10 = add nsw i32 %9, 1
  store i32 %10, i32* %i, align 4
  %11 = load i32* %3, align 4
  store i32 %11, i32* %j, align 4
  %12 = load i32* %2, align 4
  store i32 %12, i32* %curPosition, align 4
  store i8 0, i8* %direction, align 1
  br label %13

; <label>:13                                      ; preds = %64, %0
  %14 = load i32* %i, align 4
  %15 = load i32* %j, align 4
  %16 = icmp sle i32 %14, %15
  br i1 %16, label %17, label %65

; <label>:17                                      ; preds = %13
  %18 = load i8* %direction, align 1
  %19 = trunc i8 %18 to i1
  br i1 %19, label %20, label %42

; <label>:20                                      ; preds = %17
  %21 = load i32** %1, align 8
  %22 = load i32* %i, align 4
  %23 = sext i32 %22 to i64
  %24 = getelementptr inbounds i32* %21, i64 %23
  %25 = load i32* %24, align 4
  %26 = load i32* %temp, align 4
  %27 = icmp sgt i32 %25, %26
  br i1 %27, label %28, label %39

; <label>:28                                      ; preds = %20
  %29 = load i32** %1, align 8
  %30 = load i32* %i, align 4
  %31 = sext i32 %30 to i64
  %32 = getelementptr inbounds i32* %29, i64 %31
  %33 = load i32* %32, align 4
  %34 = load i32** %1, align 8
  %35 = load i32* %curPosition, align 4
  %36 = sext i32 %35 to i64
  %37 = getelementptr inbounds i32* %34, i64 %36
  store i32 %33, i32* %37, align 4
  %38 = load i32* %i, align 4
  store i32 %38, i32* %curPosition, align 4
  store i8 0, i8* %direction, align 1
  br label %39

; <label>:39                                      ; preds = %28, %20
  %40 = load i32* %i, align 4
  %41 = add nsw i32 %40, 1
  store i32 %41, i32* %i, align 4
  br label %64

; <label>:42                                      ; preds = %17
  %43 = load i32** %1, align 8
  %44 = load i32* %j, align 4
  %45 = sext i32 %44 to i64
  %46 = getelementptr inbounds i32* %43, i64 %45
  %47 = load i32* %46, align 4
  %48 = load i32* %temp, align 4
  %49 = icmp slt i32 %47, %48
  br i1 %49, label %50, label %61

; <label>:50                                      ; preds = %42
  %51 = load i32** %1, align 8
  %52 = load i32* %j, align 4
  %53 = sext i32 %52 to i64
  %54 = getelementptr inbounds i32* %51, i64 %53
  %55 = load i32* %54, align 4
  %56 = load i32** %1, align 8
  %57 = load i32* %curPosition, align 4
  %58 = sext i32 %57 to i64
  %59 = getelementptr inbounds i32* %56, i64 %58
  store i32 %55, i32* %59, align 4
  %60 = load i32* %j, align 4
  store i32 %60, i32* %curPosition, align 4
  store i8 1, i8* %direction, align 1
  br label %61

; <label>:61                                      ; preds = %50, %42
  %62 = load i32* %j, align 4
  %63 = add nsw i32 %62, -1
  store i32 %63, i32* %j, align 4
  br label %64

; <label>:64                                      ; preds = %61, %39
  br label %13

; <label>:65                                      ; preds = %13
  %66 = load i32* %temp, align 4
  %67 = load i32** %1, align 8
  %68 = load i32* %curPosition, align 4
  %69 = sext i32 %68 to i64
  %70 = getelementptr inbounds i32* %67, i64 %69
  store i32 %66, i32* %70, align 4
  %71 = load i32* %curPosition, align 4
  %72 = load i32* %2, align 4
  %73 = sub nsw i32 %71, %72
  %74 = icmp sgt i32 %73, 1
  br i1 %74, label %75, label %80

; <label>:75                                      ; preds = %65
  %76 = load i32** %1, align 8
  %77 = load i32* %2, align 4
  %78 = load i32* %curPosition, align 4
  %79 = sub nsw i32 %78, 1
  call void @_Z10quick_sortPiii(i32* %76, i32 %77, i32 %79)
  br label %80

; <label>:80                                      ; preds = %75, %65
  %81 = load i32* %3, align 4
  %82 = load i32* %curPosition, align 4
  %83 = sub nsw i32 %81, %82
  %84 = icmp sgt i32 %83, 1
  br i1 %84, label %85, label %90

; <label>:85                                      ; preds = %80
  %86 = load i32** %1, align 8
  %87 = load i32* %curPosition, align 4
  %88 = add nsw i32 %87, 1
  %89 = load i32* %3, align 4
  call void @_Z10quick_sortPiii(i32* %86, i32 %88, i32 %89)
  br label %90

; <label>:90                                      ; preds = %85, %80
  ret void
}

declare %"class.std::basic_ostream"* @_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc(%"class.std::basic_ostream"*, i8*)

declare %"class.std::basic_ostream"* @_ZNSolsEi(%"class.std::basic_ostream"*, i32)

declare %"class.std::basic_ostream"* @_ZNSolsEPFRSoS_E(%"class.std::basic_ostream"*, %"class.std::basic_ostream"* (%"class.std::basic_ostream"*)*)

declare %"class.std::basic_ostream"* @_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_(%"class.std::basic_ostream"*)

define internal void @_GLOBAL__I_a() section ".text.startup" {
  call void @__cxx_global_var_init()
  ret void
}
