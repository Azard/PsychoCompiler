; ModuleID = 'qsort.cc'
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
@_ZZ4mainE1a = private unnamed_addr constant [10 x i32] [i32 11, i32 25, i32 11, i32 4, i32 88, i32 2, i32 108, i32 3, i32 2, i32 21], align 16
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

define void @_Z5QsortPiii(i32* %a, i32 %low, i32 %high) uwtable {
  %1 = alloca i32*, align 8
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %first = alloca i32, align 4
  %last = alloca i32, align 4
  %key = alloca i32, align 4
  store i32* %a, i32** %1, align 8
  store i32 %low, i32* %2, align 4
  store i32 %high, i32* %3, align 4
  %4 = load i32* %2, align 4
  %5 = load i32* %3, align 4
  %6 = icmp sge i32 %4, %5
  br i1 %6, label %7, label %8

; <label>:7                                       ; preds = %0
  br label %89

; <label>:8                                       ; preds = %0
  %9 = load i32* %2, align 4
  store i32 %9, i32* %first, align 4
  %10 = load i32* %3, align 4
  store i32 %10, i32* %last, align 4
  %11 = load i32* %first, align 4
  %12 = sext i32 %11 to i64
  %13 = load i32** %1, align 8
  %14 = getelementptr inbounds i32* %13, i64 %12
  %15 = load i32* %14, align 4
  store i32 %15, i32* %key, align 4
  br label %16

; <label>:16                                      ; preds = %65, %8
  %17 = load i32* %first, align 4
  %18 = load i32* %last, align 4
  %19 = icmp slt i32 %17, %18
  br i1 %19, label %20, label %75

; <label>:20                                      ; preds = %16
  br label %21

; <label>:21                                      ; preds = %35, %20
  %22 = load i32* %first, align 4
  %23 = load i32* %last, align 4
  %24 = icmp slt i32 %22, %23
  br i1 %24, label %25, label %33

; <label>:25                                      ; preds = %21
  %26 = load i32* %last, align 4
  %27 = sext i32 %26 to i64
  %28 = load i32** %1, align 8
  %29 = getelementptr inbounds i32* %28, i64 %27
  %30 = load i32* %29, align 4
  %31 = load i32* %key, align 4
  %32 = icmp sge i32 %30, %31
  br label %33

; <label>:33                                      ; preds = %25, %21
  %34 = phi i1 [ false, %21 ], [ %32, %25 ]
  br i1 %34, label %35, label %38

; <label>:35                                      ; preds = %33
  %36 = load i32* %last, align 4
  %37 = add nsw i32 %36, -1
  store i32 %37, i32* %last, align 4
  br label %21

; <label>:38                                      ; preds = %33
  %39 = load i32* %last, align 4
  %40 = sext i32 %39 to i64
  %41 = load i32** %1, align 8
  %42 = getelementptr inbounds i32* %41, i64 %40
  %43 = load i32* %42, align 4
  %44 = load i32* %first, align 4
  %45 = sext i32 %44 to i64
  %46 = load i32** %1, align 8
  %47 = getelementptr inbounds i32* %46, i64 %45
  store i32 %43, i32* %47, align 4
  br label %48

; <label>:48                                      ; preds = %62, %38
  %49 = load i32* %first, align 4
  %50 = load i32* %last, align 4
  %51 = icmp slt i32 %49, %50
  br i1 %51, label %52, label %60

; <label>:52                                      ; preds = %48
  %53 = load i32* %first, align 4
  %54 = sext i32 %53 to i64
  %55 = load i32** %1, align 8
  %56 = getelementptr inbounds i32* %55, i64 %54
  %57 = load i32* %56, align 4
  %58 = load i32* %key, align 4
  %59 = icmp sle i32 %57, %58
  br label %60

; <label>:60                                      ; preds = %52, %48
  %61 = phi i1 [ false, %48 ], [ %59, %52 ]
  br i1 %61, label %62, label %65

; <label>:62                                      ; preds = %60
  %63 = load i32* %first, align 4
  %64 = add nsw i32 %63, 1
  store i32 %64, i32* %first, align 4
  br label %48

; <label>:65                                      ; preds = %60
  %66 = load i32* %first, align 4
  %67 = sext i32 %66 to i64
  %68 = load i32** %1, align 8
  %69 = getelementptr inbounds i32* %68, i64 %67
  %70 = load i32* %69, align 4
  %71 = load i32* %last, align 4
  %72 = sext i32 %71 to i64
  %73 = load i32** %1, align 8
  %74 = getelementptr inbounds i32* %73, i64 %72
  store i32 %70, i32* %74, align 4
  br label %16

; <label>:75                                      ; preds = %16
  %76 = load i32* %key, align 4
  %77 = load i32* %first, align 4
  %78 = sext i32 %77 to i64
  %79 = load i32** %1, align 8
  %80 = getelementptr inbounds i32* %79, i64 %78
  store i32 %76, i32* %80, align 4
  %81 = load i32** %1, align 8
  %82 = load i32* %2, align 4
  %83 = load i32* %first, align 4
  %84 = sub nsw i32 %83, 1
  call void @_Z5QsortPiii(i32* %81, i32 %82, i32 %84)
  %85 = load i32** %1, align 8
  %86 = load i32* %first, align 4
  %87 = add nsw i32 %86, 1
  %88 = load i32* %3, align 4
  call void @_Z5QsortPiii(i32* %85, i32 %87, i32 %88)
  br label %89

; <label>:89                                      ; preds = %75, %7
  ret void
}

define i32 @main() uwtable {
  %1 = alloca i32, align 4
  %a = alloca [10 x i32], align 16
  %i = alloca i32, align 4
  store i32 0, i32* %1
  %2 = bitcast [10 x i32]* %a to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* bitcast ([10 x i32]* @_ZZ4mainE1a to i8*), i64 40, i32 16, i1 false)
  %3 = getelementptr inbounds [10 x i32]* %a, i32 0, i32 0
  call void @_Z5QsortPiii(i32* %3, i32 0, i32 9)
  store i32 0, i32* %i, align 4
  br label %4

; <label>:4                                       ; preds = %15, %0
  %5 = load i32* %i, align 4
  %6 = sext i32 %5 to i64
  %7 = icmp ult i64 %6, 10
  br i1 %7, label %8, label %18

; <label>:8                                       ; preds = %4
  %9 = load i32* %i, align 4
  %10 = sext i32 %9 to i64
  %11 = getelementptr inbounds [10 x i32]* %a, i32 0, i64 %10
  %12 = load i32* %11, align 4
  %13 = call %"class.std::basic_ostream"* @_ZNSolsEi(%"class.std::basic_ostream"* @_ZSt4cout, i32 %12)
  %14 = call %"class.std::basic_ostream"* @_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc(%"class.std::basic_ostream"* %13, i8* getelementptr inbounds ([2 x i8]* @.str, i32 0, i32 0))
  br label %15

; <label>:15                                      ; preds = %8
  %16 = load i32* %i, align 4
  %17 = add nsw i32 %16, 1
  store i32 %17, i32* %i, align 4
  br label %4

; <label>:18                                      ; preds = %4
  %19 = call %"class.std::basic_ostream"* @_ZNSolsEPFRSoS_E(%"class.std::basic_ostream"* @_ZSt4cout, %"class.std::basic_ostream"* (%"class.std::basic_ostream"*)* @_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_)
  ret i32 0
}

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture, i64, i32, i1) nounwind

declare %"class.std::basic_ostream"* @_ZStlsISt11char_traitsIcEERSt13basic_ostreamIcT_ES5_PKc(%"class.std::basic_ostream"*, i8*)

declare %"class.std::basic_ostream"* @_ZNSolsEi(%"class.std::basic_ostream"*, i32)

declare %"class.std::basic_ostream"* @_ZNSolsEPFRSoS_E(%"class.std::basic_ostream"*, %"class.std::basic_ostream"* (%"class.std::basic_ostream"*)*)

declare %"class.std::basic_ostream"* @_ZSt4endlIcSt11char_traitsIcEERSt13basic_ostreamIT_T0_ES6_(%"class.std::basic_ostream"*)

define internal void @_GLOBAL__I_a() section ".text.startup" {
  call void @__cxx_global_var_init()
  ret void
}
